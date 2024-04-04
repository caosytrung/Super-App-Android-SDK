package com.trungcs.webminiappmanager.ui.webview.utils

import com.google.gson.GsonBuilder
import com.trungcs.webminiappmanager.ui.webview.model.response.StatusCode
import com.trungcs.webminiappmanager.webview.model.response.Response

object ResponseUtils {
    private val gson = GsonBuilder().create()

    fun createSuccessResponse(result: Any): Response {
        return Response(
            statusCode = StatusCode.SUCCESS.value,
            result = gson.toJson(result),
        )
    }

    fun createFailureResponse(error: String?): Response {
        return Response(
            statusCode = StatusCode.FAILURE.value,
            result = null,
        )
    }
}