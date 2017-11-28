package mobi.lucasborges.mrbeer.data.entities
import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName


/**
 * Created by Lucsa Borges on 27/11/2017.
 */

data class LabelEntity(
		@SerializedName(ICON)
        @ColumnInfo(name = ICON)
        var icon: String = "",

        @ColumnInfo(name = MEDIUM)
		@SerializedName("medium")
        var medium: String = "",

		@SerializedName(LARGE)
        @ColumnInfo(name = LARGE)
        var large : String = ""){

    companion object {
        const val ICON = "icon"
        const val MEDIUM = "medium"
        const val LARGE = "largeLabelBeer"
    }

}