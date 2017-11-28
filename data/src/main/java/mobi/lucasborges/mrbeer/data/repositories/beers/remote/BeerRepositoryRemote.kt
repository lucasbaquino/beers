package mobi.lucasborges.mrbeer.data.repositories.beers.remote

import mobi.lucasborges.mrbeer.data.api.BreweryApi
import mobi.lucasborges.mrbeer.data.api.ResponseBeer
import mobi.lucasborges.mrbeer.data.api.ResponseBeerStyles
import mobi.lucasborges.mrbeer.data.api.ResponseBeers
import mobi.lucasborges.mrbeer.data.di.DaggerDataComponent
import mobi.lucasborges.mrbeer.data.di.DataModule
import mobi.lucasborges.mrbeer.data.repositories.beers.BeerEntityDataMapper
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.BeerStyleEntityDataMapper
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository
import mobi.lucasborges.mrbeer.domain.repository.IBeersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Lucas Borges on 27/11/2017.
 */
class BeerRepositoryRemote (val beerEntityDataMapper: BeerEntityDataMapper) : IBeersRepository {

    @Inject
    lateinit var breweryApi : BreweryApi

    init {
        DaggerDataComponent.builder().dataModule(DataModule()).build().inject(this)
    }

    override fun getBeers(styleId : Int, callback : IBeersRepository.LoadBeersCallback) {
        breweryApi.retrieveBeers(styleId).enqueue(object : Callback<ResponseBeers> {
            override fun onResponse(call: Call<ResponseBeers>?, response: Response<ResponseBeers>?) {
                // Se tiver retornado algum dado atualiza o repositório local
                if(response != null){
                    if(response.isSuccessful && response.body() != null && response.body()!!.beerStyles.isNotEmpty()){
                        // Atualiza o repositório local
                        callback.onBeersLoaded(Resource(Status.SUCCESS, beerEntityDataMapper.transformDataListToDomainData(response.body()!!.beerStyles)))
                    } else {
                        // A chamada foi feita com sucesso mas não retornou nenhum dado
                        callback.onBeersNotAvailable(Resource(Status.SUCCESS, null))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBeers>?, t: Throwable?) {
                callback.onBeersNotAvailable(Resource(Status.CONNECTION_ERROR, null))
            }
        })
    }

    override fun getBeer(beerId: String, callback: IBeersRepository.LoadBeerCallback) {
        breweryApi.retrieveBeer(beerId).enqueue(object : Callback<ResponseBeer> {
            override fun onResponse(call: Call<ResponseBeer>?, response: Response<ResponseBeer>?) {
                if(response != null){
                    if(response.isSuccessful && response.body() != null && response.body()!!.beer != null){
                        // Atualiza o repositório local
                        callback.onBeerLoaded(Resource(Status.SUCCESS, beerEntityDataMapper.transformDataToDomain(response.body()!!.beer)))
                    } else {
                        // A chamada foi feita com sucesso mas não retornou nenhum dado
                        callback.onBeerNotAvailable(Resource(Status.SUCCESS, null))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBeer>?, t: Throwable?) {
                callback.onBeerNotAvailable(Resource(Status.CONNECTION_ERROR, null))
            }
        })
    }
}