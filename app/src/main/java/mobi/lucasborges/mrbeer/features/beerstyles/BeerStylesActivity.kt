package mobi.lucasborges.mrbeer.features.beerstyles

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
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.toolbar.*
import mobi.lucasborges.mrbeer.MrBeerApplication
import mobi.lucasborges.mrbeer.R
import mobi.lucasborges.mrbeer.domain.Status
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle
import mobi.lucasborges.mrbeer.features.beers.BeersActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class BeerStylesActivity : AppCompatActivity(), OnListBeerStyleInteractionListener {

    @Inject
    lateinit var beerStylesViewModelFactory : BeerStylesViewModelFactory
    lateinit var beerStylesViewModel : BeerStylesViewModel

    lateinit var dividerItemDecorationHorizontal : DividerItemDecoration
    lateinit var dividerItemDecorationVertical : DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        (application as MrBeerApplication).appComponent.inject(this)
        beerStylesViewModel = ViewModelProviders.of(this, beerStylesViewModelFactory).get(BeerStylesViewModel::class.java)

        beerStylesViewModel.msgError.observe(this, Observer { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        })

        beerStylesViewModel.resourceState.observe(this, Observer { resourceState ->
            when(resourceState!!.status){
                Status.LOADING -> {
                    showLoadingViews()
                }
                Status.SUCCESS -> {
                    hideLoadingViews()
                    setupAdapter(resourceState!!.data)
                }
                Status.CONNECTION_ERROR -> {
                    hideLoadingViews()
                    setupAdapter(resourceState!!.data)
                    showConnectionError()
                }
                Status.ERROR -> {
                    hideLoadingViews()
                    showGenericMessageError()
                }
            }
        })

        beerStylesViewModel.retrieveBeerStyles()
        btn_change_view.setOnClickListener {
            if(recyclerview.layoutManager is GridLayoutManager){
                changeLayoutManagerLinearLayout()
            } else {
                changeLayoutManagerToGridView()
            }
        }
    }

    private fun setupAdapter(beers: List<BeerStyle>?, layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)) {
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
        recyclerview.adapter = BeerStylesRecyclerViewAdapter(beers!!, this)
    }

    private fun changeLayoutManagerToGridView(){
        setupAdapter((recyclerview.adapter as BeerStylesRecyclerViewAdapter).mValues, GridLayoutManager(this, 2))
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn_change_view.setImageDrawable(resources.getDrawable(R.drawable.ic_list_black_18dp))
        } else {
            btn_change_view.setImageDrawable(resources.getDrawable(R.drawable.ic_list_black_18dp))
        }
    }

    private fun changeLayoutManagerLinearLayout(){
        setupAdapter((recyclerview.adapter as BeerStylesRecyclerViewAdapter).mValues)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn_change_view.setImageDrawable(getDrawable(R.drawable.ic_grid_on_black_18dp))
        } else {
            btn_change_view.setImageDrawable(resources.getDrawable(R.drawable.ic_grid_on_black_18dp))
        }
    }

    override fun onBeerStyleSelected(item: BeerStyle) {
        startActivity(intentFor<BeersActivity>("id" to item.id))
    }

    private fun showGenericMessageError() {
        animation_error.visibility = View.VISIBLE
        animation_error.setAnimation("empty_status.json")
        animation_error.loop(true)
        animation_error.playAnimation()
        txt_error.text = getString(R.string.erro_inesperado)
        txt_error.visibility = View.VISIBLE
        animation_background.visibility = View.GONE
        animation_cups.visibility = View.GONE
    }

    private fun showLoadingViews(){
        animation_background.visibility = View.VISIBLE
        animation_cups.visibility = View.VISIBLE
        recyclerview.visibility = View.GONE
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

    private fun hideLoadingViews(){
        animation_background.visibility = View.GONE
        animation_cups.visibility = View.GONE
        recyclerview.visibility = View.VISIBLE
    }
}
