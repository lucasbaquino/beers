package mobi.lucasborges.mrbeer.features.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.domain.usecases.BeerDetailUseCase
import mobi.lucasborges.mrbeer.domain.usecases.BeersUseCase
import mobi.lucasborges.mrbeer.features.beers.BeersViewModel
import javax.inject.Inject

/**
 * Created by Lucas Borges on 27/11/2017.
 */
class BeerDetailViewModelFactory @Inject constructor(private val application : MrBeerApplication, private val beerDetailUseCase : BeerDetailUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeerDetailViewModel(application, beerDetailUseCase) as T
    }

}