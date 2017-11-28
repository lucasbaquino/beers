package mobi.lucasborges.mrbeer.data.api

import com.google.gson.annotations.SerializedName
import mobi.lucasborges.mrbeer.data.entities.BeerEntity
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity

/**
 * Created by Lucsa Borges on 26/11/2017.
 */
data class ResponseBeers(@SerializedName("message") val message : String, @SerializedName("data") val beerStyles : List<BeerEntity>, @SerializedName("status") val status : String)