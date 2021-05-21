package swu.cx.sunnyweather.logic

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import swu.cx.sunnyweather.logic.model.Place
import swu.cx.sunnyweather.logic.network.SunnyWeatherNetwork
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {
    fun searchPlaces(query:String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if(placeResponse.status=="ok"){
            val places = placeResponse.places
            Result.success(places)
        }else{
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    private fun <T> fire(context: CoroutineContext,block:suspend ()->Result<T>)=
    liveData<Result<T>>(context) {
        val result = try{
            block()
        }catch (e:Exception){
            Result.failure<T>(e)
        }
        emit(result)
    }
}