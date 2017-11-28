package mobi.lucasborges.mrbeer.data.api

import mobi.lucasborges.mrbeer.data.BuildConfig
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Lucas Borges on 26/11/2017.
 */
interface BreweryApi {

    @GET("styles/?key=" + BuildConfig.BEER_API_KEY)
    fun retrieveStyles(): Call<ResponseBeerStyles>

    @GET("beers/?key=" + BuildConfig.BEER_API_KEY)
    fun retrieveBeers(@Query("styleId") styleId : Int): Call<ResponseBeers>

    @GET("beer/{beerId}?key=" + BuildConfig.BEER_API_KEY)
    fun retrieveBeer(@Path("beerId") beerId: String): Call<ResponseBeer>

}