package ma.fstt.AndroidClient_GStock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.fstt.AndroidClient_GStock.MainActivity;
import ma.fstt.AndroidClient_GStock.R;
import ma.fstt.AndroidClient_GStock.models.Article;


public class ArticleAdapter extends ArrayAdapter<Article> {

    private Context context;
    private ArrayList<Article> articles;
    private int resource;

    public ArticleAdapter(@NonNull Context context,
                          int resource,
                          @NonNull ArrayList<Article> objects) {
        super(context, resource, objects);
        this.context=context;
        this.articles=objects;
        this.resource=resource;
    }




    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
//        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        View rowView=layoutInflater.inflate(R.layout.activity_main,parent,false);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View rowView=layoutInflater.inflate(resource,parent,false);
        Long id = getItem(position).getId();
        double prix=getItem(position).getPu();
        String libelle=getItem(position).getLibelle();

        TextView idArticleView=(TextView) rowView.findViewById(R.id.prodNum);
        TextView libelleArticleView=(TextView) rowView.findViewById(R.id.libelle);
        TextView PUArticleView=(TextView) rowView.findViewById(R.id.PU);

        idArticleView.setText(String.valueOf(id));
        PUArticleView.setText(String.valueOf(prix));
        libelleArticleView.setText(libelle);

        return rowView;
    }
}
