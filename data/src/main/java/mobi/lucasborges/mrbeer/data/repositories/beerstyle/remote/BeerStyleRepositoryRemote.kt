package mobi.lucasborges.mrbeer.data.repositories.beerstyle.remote

import mobi.lucasborges.mrbeer.data.api.BreweryApi
import mobi.lucasborges.mrbeer.data.api.ResponseBeerStyles
import mobi.lucasborges.mrbeer.data.di.DaggerDataComponent
import mobi.lucasborges.mrbeer.data.di.DataModule
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.BeerStyleEntityDataMapper
import mobi.lucasborges.mrbeer.domain.Resource
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Lucas Borges on 25/11/2017.
 */
class BeerStyleRepositoryRemote(val beerStyleEntityDataMapper : BeerStyleEntityDataMapper) : IBeerStyleRepository {

    @Inject
    lateinit var breweryApi : BreweryApi

    init {
        DaggerDataComponent.builder().dataModule(DataModule()).build().inject(this)
    }

    override fun getBeerStyles(callback : IBeerStyleRepository.LoadBeerStyleCallback) {
        breweryApi.retrieveStyles().enqueue(object : Callback<ResponseBeerStyles>{
            override fun onResponse(call: Call<ResponseBeerStyles>?, response: Response<ResponseBeerStyles>?) {
                // Se tiver retornado algum dado atualiza o repositório local
                if(response != null){
                    if(response.isSuccessful && response.body() != null && response.body()!!.beerStyles.isNotEmpty()){
                        // Atualiza o repositório local
                        callback.onBeerStylesLoaded(Resource(Status.SUCCESS, beerStyleEntityDataMapper.transformDataListToDomainData(response.body()!!.beerStyles)))
                    } else {
                        // A chamada foi feita com sucesso mas não retornou nenhum dado
                        callback.onBeerStylesLoaded(Resource(Status.SUCCESS, null))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBeerStyles>?, t: Throwable?) {
                callback.onBeerStylesNotAvailable(Resource(Status.CONNECTION_ERROR, null))
            }
        })
    }
}