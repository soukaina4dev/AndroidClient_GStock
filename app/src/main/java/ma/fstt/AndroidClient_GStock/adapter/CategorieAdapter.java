package ma.fstt.AndroidClient_GStock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ma.fstt.AndroidClient_GStock.R;
import ma.fstt.AndroidClient_GStock.models.Categorie;

public class CategorieAdapter extends ArrayAdapter<Categorie> {
    private Context context;
    private List<Categorie> categories;
    public CategorieAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Categorie> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context=context;
        this.categories=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.activity_main,parent,false);
/*
        TextView txtdesignation=(TextView)rowView.findViewById(R.id.designation);
        txtdesignation.setText(String.format("%s",categories.get(position).getDesignation()));
*/
        return rowView;
    }

}
