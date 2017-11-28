package mobi.lucasborges.mrbeer.domain.usecases

import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeerStylesUseCase(private val beerStylesRepository : IBeerStyleRepository) {

    fun retrieveBeerStyles(callback : BeerStylesUseCaseCallback){
        beerStylesRepository.getBeerStyles(object : IBeerStyleRepository.LoadBeerStyleCallback{
            override fun onBeerStylesLoaded(resource: Resource<List<BeerStyle>>) {
                callback.onBeersLoaded(resource)
            }

            override fun onBeerStylesNotAvailable(resource: Resource<List<BeerStyle>>) {
                callback.onBeerStylesNotAvailable(resource)
            }
        })
    }

    interface BeerStylesUseCaseCallback {
        fun onBeersLoaded(resource : Resource<List<BeerStyle>>)
        fun onBeerStylesNotAvailable(resource : Resource<List<BeerStyle>>)
    }

}