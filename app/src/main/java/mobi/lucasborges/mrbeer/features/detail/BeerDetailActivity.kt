package mobi.lucasborges.mrbeer.features.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.R
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.Beer
import javax.inject.Inject

/**
 * Created by Lucas Borges on 27/11/2017.
 */
class BeerDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var beerStylesViewModelFactory : BeerDetailViewModelFactory
    lateinit var beerDetailViewModel: BeerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        (application as MrBeerApplication).appComponent.inject(this)
        beerDetailViewModel = ViewModelProviders.of(this, beerStylesViewModelFactory).get(BeerDetailViewModel::class.java)

        beerDetailViewModel.resourceState.observe(this, Observer { resourceState ->
            when(resourceState!!.status){
                Status.SUCCESS -> {
                    showBeerDetail(resourceState.data!!)
                }
            }
        })
        beerDetailViewModel.loadBeer(intent.getStringExtra(BEER_ID))
    }

    private fun showBeerDetail(beer : Beer) {
        txt_name.text = beer.nameDisplay
        txt_description.text = beer.description
        txt_abv.text = beer.abv
        if(beer.ibu.isNotEmpty() && beer.ibu.isNotBlank()) {
            txt_ibu.text = beer.ibu
        }
        txt_organic.text = beer.isOrganic
        txt_status.text = beer.statusDisplay

        if(beer.label != null){
            var urlLabel = ""
            if(beer.label.large.isNotEmpty() && beer.label.large.isNotBlank()){
                urlLabel = beer.label.large
            } else if(beer.label.medium.isNotEmpty() && beer.label.medium.isNotBlank()){
                urlLabel = beer.label.medium
            } else if(beer.label.icon.isNotEmpty() && beer.label.icon.isNotBlank()){
                urlLabel = beer.label.icon
            }
            Picasso.with(this).load(urlLabel).into(img_label)
        }
    }

    companion object {
        const val BEER_ID = "beer_id"
    }
}