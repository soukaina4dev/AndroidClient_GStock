package ma.fstt.AndroidClient_GStock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.fstt.AndroidClient_GStock.Utils.Service;
import ma.fstt.AndroidClient_GStock.adapter.ArticleAdapter;
import ma.fstt.AndroidClient_GStock.adapter.CategorieAdapter;
import ma.fstt.AndroidClient_GStock.models.Article;
import ma.fstt.AndroidClient_GStock.models.Categorie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView NbrArt;
    private TextView moyen;
//    list id pour categorie
    private ArrayList IdCats;
//    list designation categories
    private ArrayList DCats;
//    list pour items list
    private ArrayList<String> itemsOfList;
    private ArrayList<Article> ListOfarticles;
    private ArticleAdapter adapterList;
    private ListView listeView;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.9.1:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Service service= retrofit.create(Service.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.btnajouter);
        TextView designtion=findViewById(R.id.designtion);

//------------------------------------------------
        NbrArt=findViewById(R.id.nombre);
        moyen=findViewById(R.id.moyen);
//----------Remplissage de view Texte-------------
        getCategories();
//          spinner
        IdCats = new ArrayList();
        IdCats.add("Choisir");
        DCats=new ArrayList();
        Spinner dropdown = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, IdCats);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                String selectedValue= adapterView.getItemAtPosition(i).toString();
                designtion.setText(DCats.get(i-1)+"");
                    getArtByIdCat(Integer.parseInt(selectedValue));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast("il y a un problème !!\n");
            }
        });
//affichage de liste d'articles
        itemsOfList =new ArrayList();
        ListOfarticles=new ArrayList<>();
        adapterList = new ArticleAdapter(this, R.layout.articles_list,ListOfarticles);
         listeView=findViewById(R.id.articleList);
//         button
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setContentView(R.layout.layout_add_article);
                Intent il =new Intent(MainActivity.this,ArticleActivityMain.class);
                Bundle b=new Bundle();
                b.putString("cat",designtion.getText().toString());
                il.putExtras(b);
                Bundle c=new Bundle();
                c.putString("id",dropdown.getSelectedItem().toString());
                il.putExtras(c);
                startActivity(il);
            }
        });
    }
//    Fonction to get Categorie from api
    public void getCategories(){
        Call<List<Categorie>> call=service.getCategorie();
        call.enqueue(new Callback<List<Categorie>>(){
            @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {

                if (!response.isSuccessful()) {
                   Toast("Status : " + response.code());
                    return;
                }
                List<Categorie> category=response.body();
                for(Categorie cat :category){
                    IdCats.add(cat.getId());
                    DCats.add(cat.getDesignation());
                }

            }

            @Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
                Toast(t.getMessage());
                System.out.print("******** message ===> \n\n"+t.getMessage());
            }
        });
    }

    public void Toast(String msg){
       Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

    }


    //la fonction permet d'afficher les artices selon une categorie spécifié
    public  void getArtByIdCat(int Selected){
        Call<List<Article>> call2 = service.getArticlesById(Selected);
        call2.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (!response.isSuccessful()) {
                    Toast("il y a un problème :( ");
                    return;
                }
                List<Article> articles = response.body();
                adapterList.clear();
               NbrArt.setText(""+articles.size()+"");
                double somme=0.0;
                int i=0;
                for (Article myArt:articles){
                    itemsOfList.add(myArt.getId().toString()+"\t\t\t\t"+myArt.getLibelle()+"\t\t\t\t\t\t"+myArt.getPu());
                    ListOfarticles.add(myArt);
                    somme+=myArt.getPu();
                    i+=1;
                }
                listeView.setAdapter(adapterList);
               moyen.setText((somme/articles.size())+"");
            }
            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
               Toast(t.getMessage());
            }
        });
    }
}





