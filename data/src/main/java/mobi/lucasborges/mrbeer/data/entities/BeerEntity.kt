package mobi.lucasborges.mrbeer.data.entities
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import mobi.lucasborges.mrbeer.data.entities.BeerEntity.Companion.TABLE


/**
 * Created by Lucsa Borges on 27/11/2017.
 */
@Entity(tableName = TABLE)
data class BeerEntity @JvmOverloads constructor(

        @SerializedName(ID)
        @PrimaryKey(autoGenerate = false)
        var id: String = "",

        @SerializedName(NAME)
        @ColumnInfo(name = NAME)
        var name: String = "",

        @SerializedName(NAME_DISPLAY)
        @ColumnInfo(name = NAME_DISPLAY)
        var nameDisplay: String = "",

        @SerializedName(DESCRIPTION)
        @ColumnInfo(name = DESCRIPTION)
        var description: String = "",

        @SerializedName(ABV)
        @ColumnInfo(name = ABV)
        var abv: String = "",

        @SerializedName(IBU)
        @ColumnInfo(name = IBU)
        var ibu: String = "",

        @SerializedName(AVAILABLE_ID)
        @ColumnInfo(name = AVAILABLE_ID)
        var availableId: Int = 0,

        @SerializedName(STYLE_ID)
        @ColumnInfo(name = STYLE_ID)
        var styleId: Int = 0,

        @SerializedName(IS_ORGANIC)
        @ColumnInfo(name = IS_ORGANIC)
        var organic: String = "",

        @SerializedName(STATUS)
        @ColumnInfo(name = STATUS)
        var status: String = "",

        @SerializedName(STATUS_DISPLAY)
        @ColumnInfo(name = STATUS_DISPLAY)
        var statusDisplay: String = "",

        @SerializedName(CREATE_DATE)
        @ColumnInfo(name = CREATE_DATE)
        var createDate: String = "",

        @SerializedName(UPDATE_DATE)
        @ColumnInfo(name = UPDATE_DATE)
        var updateDate: String = "",

        @SerializedName(LABELS)
        @Embedded
        var labels : LabelEntity = LabelEntity("")){

	companion object {
		const val TABLE = "beers"
		const val ID = "id"
		const val NAME = "name"
		const val NAME_DISPLAY = "nameDisplay"
		const val DESCRIPTION = "description"
		const val ABV = "abv"
		const val IBU = "ibu"
		const val AVAILABLE_ID = "availableId"
		const val STYLE_ID = "styleId"
		const val IS_ORGANIC = "isOrganic"
		const val STATUS = "status"
		const val STATUS_DISPLAY = "statusDisplay"
		const val CREATE_DATE = "createDate"
		const val UPDATE_DATE = "updateDate"
        const val LABELS = "labels"
	}

}