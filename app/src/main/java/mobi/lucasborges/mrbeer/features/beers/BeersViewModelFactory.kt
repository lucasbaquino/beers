package mobi.lucasborges.mrbeer.features.beers

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.domain.usecases.BeerStylesUseCase
import mobi.lucasborges.mrbeer.domain.usecases.BeersUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeersViewModelFactory @Inject constructor(private val application : MrBeerApplication, private val beersUseCase : BeersUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeersViewModel(application, beersUseCase) as T
    }

}