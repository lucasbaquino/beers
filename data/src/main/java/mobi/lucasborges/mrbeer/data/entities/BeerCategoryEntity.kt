package mobi.lucasborges.mrbeer.data.entities
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import mobi.lucasborges.mrbeer.data.entities.BeerCategoryEntity.Companion.TABLE


/**
 * Created by Lucsa Borges on 26/11/2017.
 */
@Entity(tableName = TABLE)
data class BeerCategoryEntity @JvmOverloads constructor(
		@PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = ID) var id : Int = 0,
		@ColumnInfo(name = NAME) var name : String = "",
		@ColumnInfo(name = CREATE_DATE) var createDate : String = "") {


	companion object {
		const val TABLE = "beer_categories"
		const val ID = "id"
		const val NAME = "name"
		const val CREATE_DATE = "createDate"
	}
}