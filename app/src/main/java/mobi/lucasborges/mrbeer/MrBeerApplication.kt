package mobi.lucasborges.mrbeer

import android.app.Application
import mobi.lucasborges.mrbeer.di.AppComponent
import mobi.lucasborges.mrbeer.di.AppModule
import mobi.lucasborges.mrbeer.di.DaggerAppComponent

/**
 * Created by Lucas Borges on 26/11/2017.
 */
class MrBeerApplication : Application(){

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        // appComponent.inject(this)
    }

}