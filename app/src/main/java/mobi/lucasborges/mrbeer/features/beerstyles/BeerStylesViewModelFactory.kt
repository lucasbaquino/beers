package mobi.lucasborges.mrbeer.features.beerstyles

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.domain.usecases.BeerStylesUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeerStylesViewModelFactory @Inject constructor(private val application : MrBeerApplication, private val beerStylesUseCase: BeerStylesUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeerStylesViewModel(application, beerStylesUseCase) as T
    }

}