package mobi.lucasborges.mrbeer.domain.entities

/**
 * Created by Lucas Borges on 25/11/2017.
 */
data class Beer(
		val id: String,
		val name: String,
		val nameDisplay: String,
		val description: String,
		val abv: String,
		val ibu: String,
		val availableId: Int,
		val styleId: Int,
		val isOrganic: String,
		val status: String,
		val statusDisplay: String,
		val createDate: String,
		val updateDate: String,
		val label : Label
)