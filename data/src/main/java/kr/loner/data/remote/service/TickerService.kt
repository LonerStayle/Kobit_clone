package kr.loner.data.remote.service

import kr.loner.data.remote.model.TickerResponse
import retrofit2.http.GET

interface TickerService {
    @GET("v1/ticker/detailed/all")
    suspend fun getTickerAll(): Map<String, TickerResponse>
}