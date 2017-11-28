package mobi.lucasborges.mrbeer.data.repositories.beerstyle

import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.local.BeerStyleRepositoryLocal
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.remote.BeerStyleRepositoryRemote
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository
import javax.inject.Singleton

/**
 * Created by Lucas Borges on 26/11/2017.
 */
@Singleton
class BeerStyleRepository(private val beerStylesRepositoryLocal: BeerStyleRepositoryLocal, private val beerStyleRepositoryRemote: BeerStyleRepositoryRemote) : IBeerStyleRepository {

    override fun getBeerStyles(callback: IBeerStyleRepository.LoadBeerStyleCallback) {
        beerStylesRepositoryLocal.getBeerStyles(object : IBeerStyleRepository.LoadBeerStyleCallback{
            override fun onBeerStylesLoaded(resource: Resource<List<BeerStyle>>) {
                callback.onBeerStylesLoaded(resource)
            }

            override fun onBeerStylesNotAvailable(resource: Resource<List<BeerStyle>>) {
                // Se não encontrou nenhum dado no repositório local realiza a chamada no repositório remoto
                getBeerStylesFromRemoteRepository(callback)
            }
        })
    }

    fun getBeerStylesFromRemoteRepository(callback: IBeerStyleRepository.LoadBeerStyleCallback){
        beerStyleRepositoryRemote.getBeerStyles(object : IBeerStyleRepository.LoadBeerStyleCallback{
            override fun onBeerStylesLoaded(resource: Resource<List<BeerStyle>>) {
                // Atualiza o repositório local
                refreshLocalRepository(resource.data)
                callback.onBeerStylesLoaded(Resource(Status.SUCCESS, resource.data))
            }

            override fun onBeerStylesNotAvailable(resource: Resource<List<BeerStyle>>) {
                callback.onBeerStylesNotAvailable(Resource(Status.SUCCESS, null))
            }
        })
    }

    private fun refreshLocalRepository(beerStyles: List<BeerStyle>?) {
        beerStyles?.forEach { beerStyle ->
            beerStylesRepositoryLocal.saveBeerStyle(beerStyle)
        }
    }


}