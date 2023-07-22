package kr.loner.data

import retrofit2.http.GET

interface TickerApi {
    @GET("v1/ticker/detailed/all")
    suspend fun getTickerAll(): TickerAllDto
}