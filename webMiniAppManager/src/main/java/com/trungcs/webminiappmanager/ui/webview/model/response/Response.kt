package com.trungcs.webminiappmanager.webview.model.response

import com.google.gson.annotations.SerializedName

// Response will be convert to string to send data to mini-app-web
// There are currently two type of statusCode: success(200) and error (500)
data class Response(
    @SerializedName("status_code")
    val statusCode: Int,

    /**
     * The result from native after execution
     * In case of a subscription, to notice the rn app that it'll be
     *  no longer received any data, send a result = { event: "STREAM_TERMINATED" }
     */
    val result: String? = null,
)