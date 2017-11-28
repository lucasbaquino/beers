package mobi.lucasborges.mrbeer.data.di;

import dagger.Component;
import mobi.lucasborges.mrbeer.data.repositories.beers.remote.BeerRepositoryRemote;
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.remote.BeerStyleRepositoryRemote;

/**
 * Created by Lucas Borges on 26/11/2017.
 */
@Component(modules = {DataModule.class})
public interface DataComponent {

    void inject(BeerStyleRepositoryRemote remoteRepository);
    void inject(BeerRepositoryRemote remoteRepository);

}
