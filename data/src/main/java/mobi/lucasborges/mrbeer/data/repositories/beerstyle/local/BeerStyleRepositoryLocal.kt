package mobi.lucasborges.mrbeer.data.repositories.beerstyle.local

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.BeerStyleEntityDataMapper
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeerStyleRepositoryLocal(val beerStyleDao : BeerStyleDao, val beerStyleEntityDataMapper : BeerStyleEntityDataMapper) : IBeerStyleRepository {

    /**
     * Método que realiza a consulta no banco local em background e despacha o resultado via callback na thread da UI.
     */
    override fun getBeerStyles(callback : IBeerStyleRepository.LoadBeerStyleCallback) {
        async(UI) {
            val result = bg {
                beerStyleDao.getBeerStyles()
            }
            val beerStyles = result.await()
            if(beerStyles.isEmpty()){
                callback.onBeerStylesNotAvailable(Resource(Status.SUCCESS, beerStyleEntityDataMapper.transformDataListToDomainData(beerStyles)))
            } else {
                callback.onBeerStylesLoaded(Resource(Status.SUCCESS, beerStyleEntityDataMapper.transformDataListToDomainData(beerStyles)))
            }
        }
    }

    fun saveBeerStyle(beerStyle: BeerStyle) {
        async(UI) {
            bg {
                beerStyleDao.insert(beerStyleEntityDataMapper.transformDomainToData(beerStyle))
            }.await()
        }
    }

}