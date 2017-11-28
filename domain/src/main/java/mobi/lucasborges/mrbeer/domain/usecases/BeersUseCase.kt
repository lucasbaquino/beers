package mobi.lucasborges.mrbeer.domain.usecases

import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository
import mobi.lucasborges.mrbeer.domain.repository.IBeersRepository

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeersUseCase(private val beersRepository : IBeersRepository) {

    fun retrieveBeers(styleId : Int, callback : BeersUseCaseCallback){
        beersRepository.getBeers(styleId, object : IBeersRepository.LoadBeersCallback {
            override fun onBeersLoaded(resource: Resource<List<Beer>>) {
                callback.onBeersLoaded(resource)
            }

            override fun onBeersNotAvailable(resource: Resource<List<Beer>>) {
                callback.onBeerStylesNotAvailable(resource)
            }
        })
    }

    interface BeersUseCaseCallback {
        fun onBeersLoaded(resource : Resource<List<Beer>>)
        fun onBeerStylesNotAvailable(resource : Resource<List<Beer>>)
    }

}