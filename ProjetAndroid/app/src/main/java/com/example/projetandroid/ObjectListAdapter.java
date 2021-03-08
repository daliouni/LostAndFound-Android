package com.example.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projetandroid.Entities.Objet;
import com.example.projetandroid.Entities.User;
import com.example.projetandroid.Service.ServiceObjet;
import com.example.projetandroid.Service.ServiceUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ObjectListAdapter extends ArrayAdapter<Objet> {

    private Context context;
    private List<Objet> objets;
    public  ObjectListAdapter(Context context,List<Objet> objets){
    super(context,R.layout.country_list_item,objets);
    this.context=context;
    this.objets=objets;

}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater LayoutInflater = android.view.LayoutInflater.from(context);
        convertView = LayoutInflater.inflate(R.layout.country_list_item,parent,false);
        final Objet objet = objets.get(position);
        final TextView textViewuser = convertView.findViewById(R.id.textViewuser);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceUser.url).addConverterFactory(GsonConverterFactory.create()).build();
        ServiceUser s=retrofit.create(ServiceUser.class);
        Call<List<User>> call=s.getAll();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User>userlist=response.body();
                for (User p:userlist){
                    if (p.getId()== objet.getIduser()) {
                        textViewuser.setText(p.getNom());
                    }


                }

                }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Log.e("erreur",t.toString())      ;      }
        });

        TextView textviewobjname = convertView.findViewById(R.id.textviewobjname);
        textviewobjname.setText(objet.getObjet());
        TextView textViewetat = convertView.findViewById(R.id.textviewetat);
        textViewetat.setText(objet.getEtat());

        TextView textViewlieu = convertView.findViewById(R.id.textViewlieu);
        textViewlieu.setText(objet.getLieu());

return  convertView;

    }
}
