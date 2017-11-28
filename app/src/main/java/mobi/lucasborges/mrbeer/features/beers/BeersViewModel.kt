package mobi.lucasborges.mrbeer.features.beers

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.R
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import mobi.lucasborges.mrbeer.domain.usecases.BeerStylesUseCase
import mobi.lucasborges.mrbeer.domain.usecases.BeersUseCase

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeersViewModel constructor(application : MrBeerApplication, private val beersUseCase : BeersUseCase) : AndroidViewModel(application) {

    val resourceState = MutableLiveData<Resource<List<Beer>>>()

    fun retrieveBeers(styleId : Int){
        beersUseCase.retrieveBeers(styleId, object : BeersUseCase.BeersUseCaseCallback{

            override fun onBeersLoaded(resource: Resource<List<Beer>>) {
                resourceState.value = resource
            }

            override fun onBeerStylesNotAvailable(resource: Resource<List<Beer>>) {
                resourceState.value = resource
            }
        })
    }

}