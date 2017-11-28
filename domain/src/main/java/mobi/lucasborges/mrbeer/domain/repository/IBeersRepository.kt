package mobi.lucasborges.mrbeer.domain.repository

import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle

/**
 * Created by Lucsa Borges on 25/11/2017.
 */
interface IBeersRepository {

    interface LoadBeersCallback {
        fun onBeersLoaded(resource : Resource<List<Beer>>)
        fun onBeersNotAvailable(resource : Resource<List<Beer>>)
    }

    interface LoadBeerCallback {
        fun onBeerLoaded(resource : Resource<Beer>)
        fun onBeerNotAvailable(resource : Resource<Beer>)
    }

    fun getBeers(styleId : Int, callback : LoadBeersCallback)
    fun getBeer(beerId : String, callback : LoadBeerCallback)

}