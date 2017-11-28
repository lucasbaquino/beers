package mobi.lucasborges.mrbeer.features.beers

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.toolbar.*
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.R
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.Beer
import mobi.lucasborges.mrbeer.features.detail.BeerDetailActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class BeersActivity : AppCompatActivity(), OnListBeerInteractionListener {

    @Inject
    lateinit var beerStylesViewModelFactory : BeersViewModelFactory
    lateinit var beerStylesViewModel : BeersViewModel

    lateinit var dividerItemDecorationHorizontal : DividerItemDecoration
    lateinit var dividerItemDecorationVertical : DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        (application as MrBeerApplication).appComponent.inject(this)
        beerStylesViewModel = ViewModelProviders.of(this, beerStylesViewModelFactory).get(BeersViewModel::class.java)

        beerStylesViewModel.resourceState.observe(this, Observer { resourceState ->
            when(resourceState!!.status){
                Status.LOADING -> {
                    showLoadingViews()
                }
                Status.SUCCESS -> {
                    hideLoadingViews()
                    setupAdapter(resourceState.data!!)
                }
                Status.CONNECTION_ERROR -> {
                    showConnectionError()
                }
            }
        })

        beerStylesViewModel.retrieveBeers(intent.getIntExtra("id", 0))
        btn_change_view.setOnClickListener {
            if(recyclerview.layoutManager is GridLayoutManager){
                changeLayoutManagerLinearLayout()
            } else {
                changeLayoutManagerToGridView()
            }
        }
    }

    private fun hideLoadingViews(){
        animation_background.visibility = View.GONE
        animation_cups.visibility = View.GONE
        recyclerview.visibility = View.VISIBLE
    }

    private fun setupAdapter(beers: List<Beer>?, layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this), layoutId : Int = R.layout.beer_item) {
        if(beers == null || beers.isEmpty()){
            showNoContent()
        } else {
            hideErrorViews()
        }

        if(recyclerview.adapter != null){
            recyclerview.removeItemDecoration(dividerItemDecorationVertical)
            recyclerview.removeItemDecoration(dividerItemDecorationHorizontal)
        } else {
            dividerItemDecorationVertical = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            dividerItemDecorationHorizontal = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        }
        recyclerview.layoutManager = layoutManager
        recyclerview.addItemDecoration(dividerItemDecorationVertical)
        recyclerview.addItemDecoration(dividerItemDecorationHorizontal)
        recyclerview.adapter = BeersRecyclerViewAdapter(beers!!, this, layoutId)
    }

    private fun changeLayoutManagerToGridView(){
        setupAdapter((recyclerview.adapter as BeersRecyclerViewAdapter).mValues, GridLayoutManager(this, 2), R.layout.beer_grid_item)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn_change_view.setImageDrawable(getDrawable(R.drawable.ic_list_black_18dp))
        } else {
            btn_change_view.setImageDrawable(resources.getDrawable(R.drawable.ic_list_black_18dp))
        }
    }

    private fun changeLayoutManagerLinearLayout(){
        setupAdapter((recyclerview.adapter as BeersRecyclerViewAdapter).mValues, LinearLayoutManager(this))
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn_change_view.setImageDrawable(getDrawable(R.drawable.ic_grid_on_black_18dp))
        } else {
            btn_change_view.setImageDrawable(resources.getDrawable(R.drawable.ic_grid_on_black_18dp))
        }
    }

    private fun showLoadingViews(){
        animation_background.visibility = View.VISIBLE
        animation_cups.visibility = View.VISIBLE
        recyclerview.visibility = View.GONE
        hideErrorViews()
    }

    private fun showNoContent() {
        animation_error.setAnimation("empty_status.json")
        txt_error.visibility = View.VISIBLE
        animation_error.visibility = View.VISIBLE
        animation_background.visibility = View.GONE
        animation_cups.visibility = View.GONE
    }

    private fun hideErrorViews() {
        txt_error.visibility = View.GONE
        animation_error.visibility = View.GONE
    }

    private fun showConnectionError() {
        animation_error.visibility = View.VISIBLE
        animation_error.setAnimation("no_internet_connection.json")
        animation_error.loop(true)
        animation_error.playAnimation()
        txt_error.text = getString(R.string.falha_de_conexao)
        txt_error.visibility = View.VISIBLE
        animation_background.visibility = View.GONE
        animation_cups.visibility = View.GONE
    }

    override fun onBeerSelected(item: Beer) {
        startActivity(intentFor<BeerDetailActivity>().putExtra(BeerDetailActivity.BEER_ID, item.id))
    }

    companion object {
        const val ID = "id"
    }
}
