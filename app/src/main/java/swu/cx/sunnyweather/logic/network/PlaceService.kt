package swu.cx.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import swu.cx.sunnyweather.SunnyWeatherApplication
import swu.cx.sunnyweather.logic.model.PlaceResponse

interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query:String): Call<PlaceResponse>
}