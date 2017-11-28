package mobi.lucasborges.mrbeer.data.repositories.beerstyle.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle

/**
 * Created by Lucsa Borges on 25/11/2017.
 */
@Dao
interface BeerStyleDao {

    @Query("SELECT * FROM " + BeerStyleEntity.TABLE)
    fun getBeerStyles() : List<BeerStyleEntity>

    @Query("SELECT * FROM " + BeerStyleEntity.TABLE + " WHERE " + BeerStyleEntity.NAME + " LIKE :arg0")
    fun getBeerStylesByName(name : String) : List<BeerStyleEntity>

    @Insert(onConflict = REPLACE)
    fun insert(beerStyle : BeerStyleEntity)

}