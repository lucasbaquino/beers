package mobi.lucasborges.mrbeer.features.beers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import mobi.lucasborges.mrbeer.R
import mobi.lucasborges.mrbeer.domain.entities.Beer

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class BeersRecyclerViewAdapter(val mValues: List<Beer>, private val mListener : OnListBeerInteractionListener?, private val resourceLayoutId : Int) : RecyclerView.Adapter<BeersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(resourceLayoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.txtBeerName.text = mValues[position].name
        holder.txtDescription.text = mValues[position].description

        if(mValues[position].ibu.isNotEmpty()) {
            holder.txtIbu.text = holder.mView.context.getString(R.string.ibu, mValues[position].ibu)
        } else {
            holder.txtIbu.text = holder.mView.context.getString(R.string.ibu, "-")
        }

        if(mValues[position].abv.isNotEmpty()) {
            holder.txtAbv.text = holder.mView.context.getString(R.string.abv, mValues[position].abv)
        } else {
            holder.txtAbv.text = holder.mView.context.getString(R.string.ibu, "-")
        }


        holder.mView.setOnClickListener {
            mListener?.onBeerSelected(holder.mItem!!)
        }

        var urlLabel = ""
        if(mValues[position].label.large.isNotEmpty() && mValues[position].label.large.isNotBlank()){
            urlLabel = mValues[position].label.large
        } else if(mValues[position].label.medium.isNotEmpty() && mValues[position].label.medium.isNotBlank()){
            urlLabel = mValues[position].label.medium
        } else if(mValues[position].label.icon.isNotEmpty() && mValues[position].label.icon.isNotBlank()){
            urlLabel = mValues[position].label.icon
        }
        if(urlLabel.isNotEmpty()){
            Picasso.with(holder.mView.context).load(urlLabel).into(holder.imgLabel)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val txtBeerName: TextView = mView.findViewById(R.id.txt_beer_name)
        val txtDescription: TextView = mView.findViewById(R.id.txt_description)
        val txtIbu: TextView = mView.findViewById(R.id.txt_ibu)
        val txtAbv: TextView = mView.findViewById(R.id.txt_ibu)
        val imgLabel : ImageView = mView.findViewById(R.id.imgLabel)
        var mItem : Beer? = null

        override fun toString(): String {
            return super.toString() + " '" + txtBeerName.text + "'"
        }
    }
}