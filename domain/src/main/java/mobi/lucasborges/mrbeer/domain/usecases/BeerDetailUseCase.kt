package mobi.lucasborges.mrbeer.domain.usecases

import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.repository.IBeersRepository

/**
 * Created by Lucas Borges on 27/11/2017.
 */
class BeerDetailUseCase(private val beersRepository : IBeersRepository) {

    fun retrieveBeer(beerId : String, callback : BeerDetailUseCaseCallback){
        beersRepository.getBeer(beerId, object : IBeersRepository.LoadBeerCallback {
            override fun onBeerLoaded(resource: Resource<Beer>) {
                callback.onBeerLoaded(resource)
            }

            override fun onBeerNotAvailable(resource: Resource<Beer>) {
                callback.onBeerNotAvailable(resource)
            }
        })
    }

    interface BeerDetailUseCaseCallback {
        fun onBeerLoaded(resource : Resource<Beer>)
        fun onBeerNotAvailable(resource : Resource<Beer>)
    }

}