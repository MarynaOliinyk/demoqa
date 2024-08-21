package api;

import com.google.gson.JsonObject;
import models.BooksResponse;
import models.TokenResponse;
import models.UserResponse;
import retrofit2.Call;
import retrofit2.Response;
import services.BookstoreApiService;
import services.ServiceBuilder;

import java.io.IOException;

public class BookstoreApiClient extends ServiceBuilder{
    private final BookstoreApiService api;

    public BookstoreApiClient() {
        api = createService(BookstoreApiService.class);
    }

    public TokenResponse generateToken(String userName, String password) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("userName", userName);
        requestBody.addProperty("password", password);
        Call<TokenResponse> call = api.generateToken(requestBody);
        return executeCall(call);
    }

    public UserResponse createUser(JsonObject requestBody) {
        Call<UserResponse> call = api.createUser(requestBody);
        return executeCall(call);
    }

    public BooksResponse.GetBooksResponse getBooks() {
        Call<BooksResponse.GetBooksResponse> call = api.getBooks();
        return executeCall(call);
    }

    public BooksResponse.AddBookResponse addBook(JsonObject requestBody) {
        Call<BooksResponse.AddBookResponse> call = api.addBook(requestBody);
        return executeCall(call);
    }

    private <T> T executeCall(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RuntimeException("API call failed with code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error executing API call", e);
        }
    }
}
