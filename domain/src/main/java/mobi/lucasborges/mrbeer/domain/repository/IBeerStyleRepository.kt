package mobi.lucasborges.mrbeer.domain.repository

import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle


/**
 * Created by Lucsa Borges on 25/11/2017.
 */
interface IBeerStyleRepository {

    interface LoadBeerStyleCallback {
        fun onBeerStylesLoaded(resource : Resource<List<BeerStyle>>)
        fun onBeerStylesNotAvailable(resource : Resource<List<BeerStyle>>)
    }

    fun getBeerStyles(callback : LoadBeerStyleCallback)

}