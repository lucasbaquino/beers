package mobi.lucasborges.mrbeer.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import mobi.lucasborges.mrbeer.data.entities.BeerEntity
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity
import mobi.lucasborges.mrbeer.data.repositories.beers.local.BeersDao
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.local.BeerStyleDao

/**
 * Created by Lucas Borges on 25/11/2017.
 */
@Database(entities = arrayOf(BeerStyleEntity::class, BeerEntity::class), version = 1)
abstract class MrBeerDatabase : RoomDatabase() {

    abstract fun beerStyleDao() : BeerStyleDao
    abstract fun beersDao() : BeersDao

    companion object {
        private val lock = Any()
        private var INSTANCE: MrBeerDatabase? = null

        fun getInstance(context: Context): MrBeerDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MrBeerDatabase::class.java, "MrBeer.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }

}