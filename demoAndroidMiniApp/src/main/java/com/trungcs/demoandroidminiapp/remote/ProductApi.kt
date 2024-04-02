package com.trungcs.demoandroidminiapp.remote

interface ProductApi {
    @GET(E.APPLICATION_DETAIL)
    suspend fun getApplicationDetail(
        @Path("clientId") clientId: String,
        @Path("appCode") appCode: String
    ): HestiaAppDetailResponse

    @GET(HestiaEndpoint.ASSETS)
    suspend fun getAssets(
        @Path("clientId") clientId: String,
        @Path("appCode") appCode: String
    ): AssetsResponse

}