package mobi.lucasborges.mrbeer.data.repositories.beers.local

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import mobi.lucasborges.mrbeer.data.repositories.beers.BeerEntityDataMapper
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.repository.IBeersRepository
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeersRepositoryLocal(val beersDao : BeersDao, val beerEntityDataMapper : BeerEntityDataMapper) : IBeersRepository {

    /**
     * Método que realiza a consulta no banco local em background e despacha o resultado via callback na thread da UI.
     */
    override fun getBeers(styleId : Int, callback : IBeersRepository.LoadBeersCallback) {
        async(UI) {
            val result = bg {
                beersDao.getBeersByStyle(styleId)
            }
            val beerStyles = result.await()
            if (beerStyles.isEmpty()) {
                callback.onBeersNotAvailable(Resource(Status.SUCCESS, beerEntityDataMapper.transformDataListToDomainData(beerStyles)))
            } else {
                callback.onBeersLoaded(Resource(Status.SUCCESS, beerEntityDataMapper.transformDataListToDomainData(beerStyles)))
            }
        }
    }

    fun saveBeer(beer : Beer) {
        async(UI) {
            bg {
                beersDao.insert(beerEntityDataMapper.transformDomainToData(beer))
            }.await()
        }
    }

    override fun getBeer(beerId: String, callback: IBeersRepository.LoadBeerCallback) {
        async(UI) {
            val result = bg {
                beersDao.getBeerById(beerId)
            }
            val beer = result.await()
            if(beer != null){
                callback.onBeerLoaded(Resource(Status.SUCCESS, beerEntityDataMapper.transformDataToDomain(beer)))
            } else {
                callback.onBeerNotAvailable(Resource(Status.SUCCESS, beerEntityDataMapper.transformDataToDomain(beer)))
            }
        }
    }

}