package mobi.lucasborges.mrbeer.features.beers

import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle

/**
 * Created by Lucas Borges on 26/11/2017.
 */
interface OnListBeerInteractionListener {
    fun onBeerSelected(item : Beer)
}