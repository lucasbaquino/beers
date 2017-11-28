package mobi.lucasborges.mrbeer.data.api

import com.google.gson.annotations.SerializedName
import mobi.lucasborges.mrbeer.data.entities.BeerEntity

/**
 * Created by Lucas Borges on 27/11/2017.
 */
data class ResponseBeer(@SerializedName("message") val message : String, @SerializedName("data") val beer : BeerEntity, @SerializedName("status") val status : String)