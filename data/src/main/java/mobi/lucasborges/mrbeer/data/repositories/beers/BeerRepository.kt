package mobi.lucasborges.mrbeer.data.repositories.beers

import mobi.lucasborges.mrbeer.data.repositories.beers.local.BeersRepositoryLocal
import mobi.lucasborges.mrbeer.data.repositories.beers.remote.BeerRepositoryRemote
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.repository.IBeersRepository
import javax.inject.Singleton

/**
 * Created by Lucas Borges on 26/11/2017.
 */
@Singleton
class BeerRepository(private val beerStylesRepositoryLocal: BeersRepositoryLocal, private val beerStyleRepositoryRemote: BeerRepositoryRemote) : IBeersRepository {

    override fun getBeers(styleId : Int, callback: IBeersRepository.LoadBeersCallback) {
        beerStylesRepositoryLocal.getBeers(styleId, object : IBeersRepository.LoadBeersCallback {
            override fun onBeersLoaded(resource: Resource<List<Beer>>) {
                callback.onBeersLoaded(resource)
            }

            override fun onBeersNotAvailable(resource: Resource<List<Beer>>) {
                // Se não encontrou nenhum dado no repositório local realiza a chamada no repositório remoto
                getBeersFromRemoteRepository(styleId, callback)
            }
        })
    }

    override fun getBeer(beerId: String, callback: IBeersRepository.LoadBeerCallback) {
        beerStylesRepositoryLocal.getBeer(beerId, object : IBeersRepository.LoadBeerCallback {
            override fun onBeerLoaded(resource: Resource<Beer>) {
                callback.onBeerLoaded(resource)
            }

            override fun onBeerNotAvailable(resource: Resource<Beer>) {
                getBeerFromRemoteRepository(beerId, callback)
            }
        })
    }

    fun getBeersFromRemoteRepository(styleId : Int, callback: IBeersRepository.LoadBeersCallback){
        beerStyleRepositoryRemote.getBeers(styleId, object : IBeersRepository.LoadBeersCallback {
            override fun onBeersLoaded(resource: Resource<List<Beer>>) {
                refreshLocalRepository(resource.data)
                callback.onBeersLoaded(Resource(Status.SUCCESS, resource.data))
            }

            override fun onBeersNotAvailable(resource: Resource<List<Beer>>) {
                callback.onBeersNotAvailable(resource)
            }
        })
    }

    fun getBeerFromRemoteRepository(beerId : String, callback: IBeersRepository.LoadBeerCallback){
        beerStyleRepositoryRemote.getBeer(beerId, object : IBeersRepository.LoadBeerCallback {
            override fun onBeerLoaded(resource: Resource<Beer>) {
                refreshLocalRepository(resource.data)
                callback.onBeerLoaded(Resource(Status.SUCCESS, resource.data))
            }

            override fun onBeerNotAvailable(resource: Resource<Beer>) {
                callback.onBeerNotAvailable(resource)
            }
        })
    }

    private fun refreshLocalRepository(beers: List<Beer>?) {
        beers?.forEach { beer ->
            beerStylesRepositoryLocal.saveBeer(beer)
        }
    }

    private fun refreshLocalRepository(beer : Beer?) {
        beer?.let{
            beerStylesRepositoryLocal.saveBeer(beer)
        }
    }


}