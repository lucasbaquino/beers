package mobi.lucasborges.mrbeer

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.content.Context
import android.content.res.Resources
import mobi.lucasborges.mrbeer.data.MrBeerDatabase
import mobi.lucasborges.mrbeer.data.repositories.beers.BeerRepository
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.BeerStyleEntityDataMapper
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.BeerStyleRepository
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.local.BeerStyleRepositoryLocal
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.remote.BeerStyleRepositoryRemote
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository
import mobi.lucasborges.mrbeer.domain.usecases.BeerStylesUseCase
import mobi.lucasborges.mrbeer.features.beerstyles.BeerStylesViewModel
import mobi.lucasborges.mrbeer.test.util.capture
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import android.R.attr.countDown
import android.arch.lifecycle.LiveData
import android.support.annotation.Nullable
import mobi.lucasborges.mrbeer.test.util.mock
import org.mockito.Mockito.verify
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


/**
 * Created by Lucsa Borges on 27/11/2017.
 */
class BeerStylesViewModelTest{


    private lateinit var beerStyleRepositoryLocal : BeerStyleRepositoryLocal
    @Mock private lateinit var beerRepository : BeerStyleRepository
    @Mock private lateinit var beerStyleEntityDataMapper : BeerStyleEntityDataMapper
    @Mock private lateinit var context: MrBeerApplication
    private lateinit var beerStylesViewModel : BeerStylesViewModel
    @Captor private lateinit var loadTasksCallbackCaptor: ArgumentCaptor<IBeerStyleRepository.LoadBeerStyleCallback>
    private val beerStyles = mutableListOf<BeerStyle>()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before fun setupTasksRepository() {
        MockitoAnnotations.initMocks(this)

        setupContext()

        val database = Room.inMemoryDatabaseBuilder(context, MrBeerDatabase::class.java).build()
        beerStyleRepositoryLocal = BeerStyleRepositoryLocal(database.beerStyleDao(), beerStyleEntityDataMapper)

        beerStyles.add(BeerStyle(0, 1, "Heineken"))
        beerStyles.add(BeerStyle(0, 2, "Stela"))
        beerStyles.add(BeerStyle(0, 3, "Franziskaner"))
        beerStyles.add(BeerStyle(0, 4, "Paulaner"))

        beerStylesViewModel = BeerStylesViewModel(context, BeerStylesUseCase(beerRepository))
    }

    private fun setupContext() {
        Mockito.`when`<Context>(context.applicationContext).thenReturn(context)
        Mockito.`when`(context.resources).thenReturn(Mockito.mock(Resources::class.java))
    }

    @Test
    fun retrieveBeerStyles_should_return_4_beerstyles() {
        with(beerStylesViewModel) {
            retrieveBeerStyles()
            org.junit.Assert.assertTrue(resourceState.value!!.status == Status.LOADING)
            Mockito.verify<BeerStyleRepository>(beerRepository).getBeerStyles(capture(loadTasksCallbackCaptor))
            loadTasksCallbackCaptor.value.onBeerStylesLoaded(Resource(Status.SUCCESS, beerStyles))

            org.junit.Assert.assertTrue(resourceState.value!!.status == Status.SUCCESS)
            org.junit.Assert.assertFalse(resourceState.value!!.data!!.isEmpty())
            org.junit.Assert.assertTrue(resourceState.value!!.data!!.size == 4)
        }
    }

    @Test
    fun retrieveBeerStyles_should_return_empty_list() {
        with(beerStylesViewModel) {
            retrieveBeerStyles()
            org.junit.Assert.assertTrue(resourceState.value!!.status == Status.LOADING)
            Mockito.verify<BeerStyleRepository>(beerRepository).getBeerStyles(capture(loadTasksCallbackCaptor))
            loadTasksCallbackCaptor.value.onBeerStylesLoaded(Resource(Status.SUCCESS, mutableListOf()))

            org.junit.Assert.assertTrue(resourceState.value!!.status == Status.SUCCESS)
            org.junit.Assert.assertTrue(resourceState.value!!.data!!.isEmpty())
        }
    }

    @Test
    fun retrieveBeerStyles_should_return_communication_error() {
        with(beerStylesViewModel) {
            retrieveBeerStyles()
            val observer = mock<Observer<String>>()
            msgNetworkError.observeForever(observer)
            org.junit.Assert.assertTrue(resourceState.value!!.status == Status.LOADING)
            Mockito.verify<BeerStyleRepository>(beerRepository).getBeerStyles(capture(loadTasksCallbackCaptor))
            loadTasksCallbackCaptor.value.onBeerStylesNotAvailable(Resource(Status.CONNECTION_ERROR))

            org.junit.Assert.assertTrue(resourceState.value!!.status == Status.CONNECTION_ERROR)
        }
    }

}