package mobi.lucasborges.mrbeer.data;

import android.content.Context;
import android.content.res.Resources;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;

import java.io.InputStream;
import java.util.List;

import mobi.lucasborges.mrbeer.data.api.BreweryApi;
import mobi.lucasborges.mrbeer.data.api.ResponseBeerStyles;
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.BeerStyleEntityDataMapper;
import mobi.lucasborges.mrbeer.data.repositories.beerstyle.remote.BeerStyleRepositoryRemote;
import mobi.lucasborges.mrbeer.domain.Resource;
import mobi.lucasborges.mrbeer.domain.Status;
import mobi.lucasborges.mrbeer.domain.entities.BeerStyle;
import mobi.lucasborges.mrbeer.domain.repository.IBeerStyleRepository;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucas Borges on 27/11/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class RepositoryRemoteTest extends InstrumentationTestCase {

    private MockWebServer mockWebServer;
    private BeerStyleRepositoryRemote remoteRepository;

    @Before
    public void setup() throws Exception {
        // Configura o mockServer, define a resposta e inicia a execução.
        mockWebServer = new MockWebServer();
        remoteRepository = new BeerStyleRepositoryRemote(new BeerStyleEntityDataMapper());
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(RestServiceTestHelper.getStringFromFile(getClass().getClassLoader().getResourceAsStream("retrieve_styles_200_with_content.json"))));
        mockWebServer.start();
    }

    private BreweryApi provideServiceInstanceForTest(HttpUrl baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .create()))
                .build()
                .create(BreweryApi.class);
    }

    @Test
    public void test_remote_repository_should_return_data() throws Exception {
        HttpUrl baseUrl = mockWebServer.url("/styles/");
        remoteRepository.breweryApi = provideServiceInstanceForTest(baseUrl);

        retrofit2.Response<ResponseBeerStyles> response = remoteRepository.breweryApi.retrieveStyles().execute();
        assertEquals(response.body().getBeerStyles().size() > 0, true);
    }

    @After
    public void release() throws Exception {
        mockWebServer.shutdown();
    }


}
