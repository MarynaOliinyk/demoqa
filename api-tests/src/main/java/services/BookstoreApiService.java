package services;

import com.google.gson.JsonObject;
import models.BooksResponse;
import models.TokenResponse;
import models.UserResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static core.Endpoints.DemoQAEndpoins.ACCOUNT;
import static core.Endpoints.DemoQAEndpoins.AUTORIZED;
import static core.Endpoints.DemoQAEndpoins.BOOKS;
import static core.Endpoints.DemoQAEndpoins.BOOK_STORE;
import static core.Endpoints.DemoQAEndpoins.GENERATE_TOKEN;
import static core.Endpoints.DemoQAEndpoins.USER;
import static core.Endpoints.DemoQAEndpoins.V1;

public interface BookstoreApiService {
    @Headers("Content-Type: application/json")
    @POST(ACCOUNT + V1 + GENERATE_TOKEN)
    Call<TokenResponse> generateToken(@Body JsonObject requestBody);

    @Headers("Content-Type: application/json")
    @POST(ACCOUNT + V1 + USER)
    Call<UserResponse> createUser(@Body JsonObject requestBody);

    @Headers("Content-Type: application/json")
    @GET(BOOK_STORE + V1 + BOOKS)
    Call<BooksResponse.GetBooksResponse> getBooks();

    @Headers("Content-Type: application/json")
    @POST(BOOK_STORE + V1 + BOOKS)
    Call<BooksResponse.AddBookResponse> addBook(@Body JsonObject requestBody);

    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST(ACCOUNT + V1 + AUTORIZED)
    Call<ResponseBody> authorizeUser(@Field("userName") String username, @Field("password") String password);

    @Headers("Content-Type: application/json")
    @GET(BOOK_STORE + V1 + BOOKS + "?publisher={publisher}&author={author}")
    Call<BooksResponse.GetBooksResponse> filterBooks(@Path("publisher") String publisher, @Path("author") String author);
}

