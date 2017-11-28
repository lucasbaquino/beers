package mobi.lucasborges.mrbeer.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobi.lucasborges.mrbeer.MrBeerApplication;
import mobi.lucasborges.mrbeer.data.MrBeerDatabase;
import mobi.lucasborges.mrbeer.data.repositories.beers.BeerEntityDataMapper;
import mobi.lucasborges.mrbeer.data.repositories.beers.BeerRepository;
import mobi.lucasborges.mrbeer.data.repositories.beers.local.BeersRepositoryLocal;
import mobi.lucasborges.mrbeer.data.repositories.beers.remote.BeerRepositoryRemote;
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.BeerStyleEntityDataMapper;
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.BeerStyleRepository;
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.local.BeerStyleRepositoryLocal;
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.remote.BeerStyleRepositoryRemote;
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository;
import mobi.lucasborges.mrbeer.domain.repository.IBeersRepository;
import mobi.lucasborges.mrbeer.domain.usecases.BeerDetailUseCase;
import mobi.lucasborges.mrbeer.domain.usecases.BeerStylesUseCase;
import mobi.lucasborges.mrbeer.domain.usecases.BeersUseCase;
import mobi.lucasborges.mrbeer.features.beers.BeersViewModelFactory;
import mobi.lucasborges.mrbeer.features.beerstyles.BeerStylesViewModelFactory;
import mobi.lucasborges.mrbeer.features.detail.BeerDetailViewModelFactory;

/**
 * Created by Lucsa Borges on 26/11/2017.
 */
@Module
public class AppModule {

    MrBeerApplication application;

    public AppModule(MrBeerApplication application){
        this.application = application;
    }

    @Provides
    @Singleton
    MrBeerApplication provideApplication(){
        return application;
    }

    @Provides
    BeerStylesUseCase provideBeerStyles(IBeerStyleRepository repository){
        return new BeerStylesUseCase(repository);
    }

    @Provides
    BeersUseCase provideBeersUseCase(IBeersRepository repository){
        return new BeersUseCase(repository);
    }

    @Provides
    BeerDetailUseCase provideBeerDetailUseCase(IBeersRepository repository){
        return new BeerDetailUseCase(repository);
    }

    @Provides
    BeerStylesViewModelFactory provideBeerStylesViewModelFactory(MrBeerApplication application, BeerStylesUseCase beerStylesUseCase){
        return new BeerStylesViewModelFactory(application, beerStylesUseCase);
    }

    @Provides
    BeersViewModelFactory provideBeersViewModelFactory(MrBeerApplication application, BeersUseCase beersUseCase){
        return new BeersViewModelFactory(application, beersUseCase);
    }

    @Provides
    BeerDetailViewModelFactory provideBeerDetailViewModelFactory(MrBeerApplication application, BeerDetailUseCase beerDetailUseCase){
        return new BeerDetailViewModelFactory(application, beerDetailUseCase);
    }

    @Provides
    @Singleton
    BeerStyleRepositoryLocal provideBeerStyleRepositoryLocal(MrBeerApplication application, BeerStyleEntityDataMapper beerStyleEntityDataMapper){
        return new BeerStyleRepositoryLocal(MrBeerDatabase.Companion.getInstance(application).beerStyleDao(), beerStyleEntityDataMapper);
    }

    @Provides
    @Singleton
    BeersRepositoryLocal provideBeerRepositoryLocal(MrBeerApplication application, BeerEntityDataMapper beerEntityDataMapper){
        return new BeersRepositoryLocal(MrBeerDatabase.Companion.getInstance(application).beersDao(), beerEntityDataMapper);
    }

    @Provides
    BeerStyleEntityDataMapper provideBeerStyleEntityDataMapper(){
        return new BeerStyleEntityDataMapper();
    }

    @Provides
    BeerEntityDataMapper provideBeerEntityDataMapper(){
        return new BeerEntityDataMapper();
    }

    @Provides
    @Singleton
    BeerStyleRepositoryRemote provideBeerStyleRepositoryRemote(BeerStyleEntityDataMapper beerStyleEntityDataMapper){
        return new BeerStyleRepositoryRemote(beerStyleEntityDataMapper);
    }

    @Provides
    @Singleton
    BeerRepositoryRemote provideBeerRepositoryRemote(BeerEntityDataMapper beerEntityDataMapper){
        return new BeerRepositoryRemote(beerEntityDataMapper);
    }

    @Provides
    @Singleton
    IBeerStyleRepository provideIBeerStyleRepository(BeerStyleRepositoryLocal beerStyleRepositoryLocal, BeerStyleRepositoryRemote beerStyleRepositoryRemote){
        return new BeerStyleRepository(beerStyleRepositoryLocal, beerStyleRepositoryRemote);
    }

    @Provides
    @Singleton
    IBeersRepository provideIBeersRepository(BeersRepositoryLocal beerRepositoryLocal, BeerRepositoryRemote beerRepositoryRemote){
        return new BeerRepository(beerRepositoryLocal, beerRepositoryRemote);
    }

}