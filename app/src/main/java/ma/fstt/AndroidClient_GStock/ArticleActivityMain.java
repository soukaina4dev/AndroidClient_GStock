package ma.fstt.AndroidClient_GStock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import ma.fstt.AndroidClient_GStock.Utils.Service;
import ma.fstt.AndroidClient_GStock.models.Article;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleActivityMain extends AppCompatActivity {
//    private Button valider;
    private EditText libelle;
    private TextView cat_designation;
    private EditText prix;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.9.1:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Service service = retrofit.create(Service.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_article);
        Button valider =findViewById(R.id.valider);
        cat_designation =findViewById(R.id.addProdName);

        libelle = findViewById(R.id.libelleProd);
        prix = findViewById(R.id.addprodQte);
        Bundle valeurs=getIntent().getExtras();
        String idCategorie=valeurs.getString("id").toString();
        cat_designation.setText(valeurs.getString("cat"));
        Button btn2=findViewById(R.id.retour);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il =new Intent(ArticleActivityMain.this,MainActivity.class);
                startActivity(il);

            }
        });


        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterUneArticle(idCategorie);

            }
        });
    }

    public void AjouterUneArticle(String idCategorie){
        Article art = new Article(Double.parseDouble(prix.getText().toString())
                ,Integer.parseInt(idCategorie),
                libelle.getText().toString());
        Call<Article> call  = service.AjouterArticle(art);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (!response.isSuccessful()) {
                    Toast("Code: " + response.code());
                    return;
                }
                Article artResponse=response.body();
                String content="";
                content+="Une Article avec Id :"+artResponse.getId()+" a été ajoutée avec succes :) ";
                Toast(content);
                libelle.setText("");
                prix.setText("");
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast(t.getMessage());
            }
        });
    }


    public void Toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

    }
}
