package mobi.lucasborges.mrbeer.domain.entities

/**
 * Created by Lucas Borges on 25/11/2017.
 */
data class BeerStyle(
		val id: Int = -1,
		val categoryId: Int = 1,
		val name: String = "",
		val shortName: String = "",
		val description: String = "",
		val ibuMin: String = "",
		val ibuMax: String = "",
		val abvMin: String = "",
		val abvMax: String = "",
		val srmMin: String = "",
		val srmMax: String = "",
		val ogMin: String = "",
		val fgMin: String = "",
		val fgMax: String = "",
		val createDate: String = "",
		val updateDate: String = ""
)