package services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String BASE_URL = "https://demoqa.com";
    private HttpLoggingInterceptor logging;
    private OkHttpClient client;
    private Retrofit retrofitInstance;

    //constructor to initialize el-s
    public ServiceBuilder() {
        logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofitInstance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public <T> T createService(Class<T> serviceClass) {
        return retrofitInstance.create(serviceClass);
    }
}
