package com.trungcs.base.utils

sealed class Result<out V : Any, out E : Throwable> {

    /**
     * Returns `true` if this [Result] represents successful outcome.
     * In this case [isFailure] returns `false`.
     */
    fun isSuccess() = this !is Failure

    /**
     * Returns `true` if this [Result] represents failed outcome.
     * In this case [isSuccess] returns `false`.
     */
    fun isFailure() = this is Failure

    /**
     * Returns the encapsulated value if this instance represents [success][Result.isSuccess] or `null`
     * if it is [failure][Result.isFailure].
     */
    fun getOrNull(): V? = when {
        isSuccess() -> (this as Success).value
        else -> null
    }

    /**
     * Returns the encapsulated value if this instance represents [success][Result.isSuccess]
     * or throw an [IllegalStateException] if it is [failure][Result.isFailure].
     */
    fun get(): V {
        check(isSuccess()) { "Could not get value of Failure Result" }
        return (this as Success).value
    }

    /**
     * Returns the encapsulated exception if this instance represents [failure][isFailure] or `null`
     * if it is [success][isSuccess].
     */
    fun exceptionOrNull(): E? = when {
        isFailure() -> (this as Failure).error
        else -> null
    }

    /**
     * Returns the encapsulated exception if this instance represents [failure][isFailure]
     * or throw an [IllegalStateException] if it is [failure][Result.isFailure].
     */
    fun exception(): E {
        check(isFailure()) { "Could not get exception of Success Result" }
        return (this as Failure).error
    }

    class Success<out V : Any> internal constructor(internal val value: V) : Result<V, Nothing>() {

        override fun toString() = "[Success: $value]"

        override fun hashCode() = value.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Success<*> && value == other.value
        }

    }

    class Failure<out E : Throwable> internal constructor(internal val error: E) :
        Result<Nothing, E>() {

        override fun toString() = "[Failure: $error]"

        override fun hashCode(): Int = error.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Failure<*> && error == other.error
        }

    }

    /**
     * Companion object for [Result] class that contains its constructor functions
     * [success] and [failure].
     */
    companion object {

        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */
        fun <V : Any> success(value: V) = Success(value)

        /**
         * Returns an instance that encapsulates the given [error] as failure.
         */
        fun <E : Throwable> failure(error: E) = Failure(error)

    }

}

suspend fun <V : Any> result(
    block: suspend () -> V
) = try {
    Result.success(block())
} catch (error: Throwable) {
    Result.failure(error)
}