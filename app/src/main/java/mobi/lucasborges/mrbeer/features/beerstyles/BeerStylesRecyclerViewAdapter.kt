package mobi.lucasborges.mrbeer.features.beerstyles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mobi.lucasborges.mrbeer.R
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeerStylesRecyclerViewAdapter(val mValues: List<BeerStyle>, private val mListener : OnListBeerStyleInteractionListener?) : RecyclerView.Adapter<BeerStylesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.beer_style_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mText.text = mValues[position].name

        holder.mView.setOnClickListener {
            mListener?.onBeerStyleSelected(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mText: TextView = mView.findViewById(R.id.txt_beer_name)
        var mItem: BeerStyle? = null

        override fun toString(): String {
            return super.toString() + " '" + mText.text + "'"
        }
    }
}