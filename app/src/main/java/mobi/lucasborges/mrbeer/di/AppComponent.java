package mobi.lucasborges.mrbeer.di;

import javax.inject.Singleton;

import dagger.Component;
import mobi.lucasborges.mrbeer.MrBeerApplication;
import mobi.lucasborges.mrbeer.features.beers.BeersActivity;
import mobi.lucasborges.mrbeer.features.beerstyles.BeerStylesActivity;
import mobi.lucasborges.mrbeer.features.detail.BeerDetailActivity;
import mobi.lucasborges.mrbeer.features.detail.BeerDetailViewModel;

/**
 * Created by Lucsa Borges on 26/11/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(BeersActivity beerActivity);
    void inject(BeerStylesActivity beerStylesActivity);
    void inject(BeerDetailActivity beerStylesActivity);
    void inject(MrBeerApplication application);

}
