package mobi.lucasborges.mrbeer.data.entities

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity.Companion.CATEGORY_ID
import mobi.lucasborges.mrbeer.data.entities.BeerStyleEntity.Companion.TABLE

/**
 * Created by Lucas Borges on 25/11/2017.
 */
@Entity(tableName = TABLE)
data class BeerStyleEntity @JvmOverloads constructor(

        @SerializedName(ID)
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = ID) var id : Int = 0,

        @SerializedName(CATEGORY_ID)
        @ColumnInfo(name = CATEGORY_ID) var categoryId : Int = 0,

        @SerializedName(NAME)
        @ColumnInfo(name = NAME) var name : String = "",

        @SerializedName(SHORT_NAME)
        @ColumnInfo(name = SHORT_NAME) var shortName : String = "",

        @SerializedName(DESCRIPTION)
        @ColumnInfo(name = DESCRIPTION) var description : String = "",

        @SerializedName(IBU_MIN)
        @ColumnInfo(name = IBU_MIN) var ibuMin : String = "",

        @SerializedName(IBU_MAX)
        @ColumnInfo(name = IBU_MAX) var ibuMax : String = "",

        @SerializedName(ABV_MIN)
        @ColumnInfo(name = ABV_MIN) var abvMin : String = "",

        @SerializedName(ABV_MAX)
        @ColumnInfo(name = ABV_MAX) var abvMax : String = "",

        @SerializedName(SRM_MIN)
        @ColumnInfo(name = SRM_MIN) var srmMin : String = "",

        @SerializedName(SRM_MAX)
        @ColumnInfo(name = SRM_MAX) var srmMax : String = "",

        @SerializedName(OG_MIN)
        @ColumnInfo(name = OG_MIN) var ogMin : String = "",

        @SerializedName(FG_MIN)
        @ColumnInfo(name = FG_MIN) var fgMin : String = "",

        @SerializedName(FG_MAX)
        @ColumnInfo(name = FG_MAX) var fgMax : String = "",

        @SerializedName(CREATE_DATE)
        @ColumnInfo(name = CREATE_DATE) var createDate : String = "",

        @SerializedName(UPDATE_DATE)
        @ColumnInfo(name = UPDATE_DATE) var updateDate : String = ""){


    companion object {
        const val TABLE = "beer_styles"
        const val ID = "id"
        const val CATEGORY_ID = "categoryId"
        const val NAME = "name"
        const val SHORT_NAME = "shortName"
        const val DESCRIPTION = "description"
        const val IBU_MIN = "ibuMin"
        const val IBU_MAX = "ibuMax"
        const val ABV_MIN = "abvMin"
        const val ABV_MAX = "abvMax"
        const val SRM_MIN = "srmMin"
        const val SRM_MAX = "srmMax"
        const val OG_MIN = "ogMin"
        const val FG_MIN = "fgMin"
        const val FG_MAX = "fgMax"
        const val CREATE_DATE = "createDate"
        const val UPDATE_DATE = "updateDate"
    }
}