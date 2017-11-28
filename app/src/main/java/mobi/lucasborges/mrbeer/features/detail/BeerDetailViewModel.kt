package mobi.lucasborges.mrbeer.features.detail

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.usecases.BeerDetailUseCase
import mobi.lucasborges.mrbeer.domain.usecases.BeersUseCase

/**
 * Created by Lucas Borges on 27/11/2017.
 */
class BeerDetailViewModel constructor(application : MrBeerApplication, private val beerDetailUseCase : BeerDetailUseCase) : AndroidViewModel(application) {

    val resourceState = MutableLiveData<Resource<Beer>>()

    fun loadBeer(beerId: String) {
        beerDetailUseCase.retrieveBeer(beerId, object : BeerDetailUseCase.BeerDetailUseCaseCallback{
            override fun onBeerLoaded(resource: Resource<Beer>) {
                resourceState.value = resource
            }

            override fun onBeerNotAvailable(resource: Resource<Beer>) {
                resourceState.value = resource
            }
        })
    }


}