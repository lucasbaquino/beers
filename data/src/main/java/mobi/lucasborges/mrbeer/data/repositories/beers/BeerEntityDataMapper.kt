package mobi.lucasborges.mrbeer.data.repositories.beers

import mobi.lucasborges.mrbeer.data.entities.BeerEntity
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity
import mobi.lucasborges.mrbeer.data.entities.LabelEntity
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import mobi.lucasborges.mrbeer.domain.entities.Label
import javax.inject.Singleton

/**
 * Created by Lucsa Borges on 26/11/2017.
 */
@Singleton
class BeerEntityDataMapper {

    fun transformDataListToDomainData(beerEntieis : List<BeerEntity>?) : List<Beer> {
        val beers = mutableListOf<Beer>()
        beerEntieis?.forEach { beer ->
            beers.add(transformDataToDomain(beer))
        }
        return beers
    }

    fun transformDomainToData(beer : Beer) : BeerEntity {
        return BeerEntity(beer.id, beer.name, beer.nameDisplay, beer.description, beer.abv, beer.ibu, beer.availableId, beer.styleId, beer.isOrganic, beer.status, beer.statusDisplay, beer.createDate, beer.updateDate, LabelEntity(beer.label.icon, beer.label.medium, beer.label.large))
    }

    fun transformDataToDomain(beer : BeerEntity) : Beer {
        return Beer(beer.id, beer.name, beer.nameDisplay, beer.description, beer.abv, beer.ibu, beer.availableId, beer.styleId, beer.organic, beer.status, beer.statusDisplay, beer.createDate, beer.updateDate, Label(beer.labels.icon, beer.labels.medium, beer.labels.large))
    }

}