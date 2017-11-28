package mobi.lucasborges.mrbeer.features.beerstyles

import mobi.lucasborges.mrbeer.domain.entities.BeerStyle

/**
 * Created by Lucas Borges on 26/11/2017.
 */
interface OnListBeerStyleInteractionListener {
    fun onBeerStyleSelected(item : BeerStyle)
}