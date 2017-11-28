package mobi.lucasborges.mrbeer.features.beerstyles

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.R
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import mobi.lucasborges.mrbeer.domain.usecases.BeerStylesUseCase

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeerStylesViewModel constructor(application : MrBeerApplication, val beerStylesUseCase : BeerStylesUseCase) : AndroidViewModel(application) {

    val msgError = MutableLiveData<String>().apply { "" }
    val msgNetworkError = MutableLiveData<String>()
    val resourceState = MutableLiveData<Resource<List<BeerStyle>>>()

    fun retrieveBeerStyles(){
        resourceState.value = Resource(Status.LOADING)
        beerStylesUseCase.retrieveBeerStyles(object : BeerStylesUseCase.BeerStylesUseCaseCallback{
            override fun onBeersLoaded(resource: Resource<List<BeerStyle>>) {
                if(resource.status == Status.SUCCESS && resource.data != null){
                    // Atualiza a lista
                    resourceState.value = resource
                }
            }

            override fun onBeerStylesNotAvailable(resource: Resource<List<BeerStyle>>) {
                resourceState.value = resource
            }
        })
    }

}