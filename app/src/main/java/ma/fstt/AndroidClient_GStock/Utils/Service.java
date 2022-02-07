package ma.fstt.AndroidClient_GStock.Utils;

import java.util.List;

import ma.fstt.AndroidClient_GStock.models.Article;
import ma.fstt.AndroidClient_GStock.models.Categorie;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {
//    @GET("Articles")
//    Call<List<Article>> getArticle();

    @GET("Categories")
    Call<List<Categorie>> getCategorie();

    @GET("Categories/{id}/articles")
    Call<List<Article>> getArticlesById(@Path("id") int ido);

    @POST("Articles")
    Call<Article> AjouterArticle(@Body Article art);
}
