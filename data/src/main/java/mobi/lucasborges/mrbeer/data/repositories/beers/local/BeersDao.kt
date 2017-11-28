package mobi.lucasborges.mrbeer.data.repositories.beers.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import mobi.lucasborges.mrbeer.data.entities.BeerEntity
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity

/**
 * Created by Lucsa Borges on 25/11/2017.
 */
@Dao
interface BeersDao {

    @Query("SELECT * FROM " + BeerEntity.TABLE)
    fun getBeers() : List<BeerEntity>

    @Query("SELECT * FROM " + BeerEntity.TABLE + " WHERE " + BeerEntity.STYLE_ID + " LIKE :arg0")
    fun getBeersByStyle(styleId : Int) : List<BeerEntity>

    @Query("SELECT * FROM " + BeerEntity.TABLE + " WHERE " + BeerEntity.ID + " = :arg0")
    fun getBeerById(beerId : String) : BeerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(beer : BeerEntity)

}