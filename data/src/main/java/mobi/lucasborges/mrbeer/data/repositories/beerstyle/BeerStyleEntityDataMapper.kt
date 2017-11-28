package mobi.lucasborges.mrbeer.data.repositories.beerstyle

import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity
import mobi.lucasborges.mrbeer.data.entities.LabelEntity
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import javax.inject.Singleton

/**
 * Created by Lucsa Borges on 26/11/2017.
 */
@Singleton
class BeerStyleEntityDataMapper {

    fun transformDataListToDomainData(beerStyleEntity : List<BeerStyleEntity>?) : List<BeerStyle> {
        val beerStyles = mutableListOf<BeerStyle>()
        beerStyleEntity?.forEach { beerStyle ->
            beerStyles.add(BeerStyle(beerStyle.id, beerStyle.categoryId, beerStyle.name, beerStyle.shortName, beerStyle.description, beerStyle.ibuMin, beerStyle.ibuMax,
                    beerStyle.abvMin, beerStyle.abvMax, beerStyle.srmMin, beerStyle.srmMax, beerStyle.ogMin, beerStyle.fgMin, beerStyle.fgMax, beerStyle.createDate, beerStyle.updateDate))
        }
        return beerStyles
    }

    fun transformDomainToData(beerStyle : BeerStyle) : BeerStyleEntity {
        return BeerStyleEntity(beerStyle.id, beerStyle.categoryId, beerStyle.name, beerStyle.shortName, beerStyle.description, beerStyle.ibuMin, beerStyle.ibuMax,
                beerStyle.abvMin, beerStyle.abvMax, beerStyle.srmMin, beerStyle.srmMax, beerStyle.ogMin, beerStyle.fgMin, beerStyle.fgMax, beerStyle.createDate, beerStyle.updateDate)
    }

}