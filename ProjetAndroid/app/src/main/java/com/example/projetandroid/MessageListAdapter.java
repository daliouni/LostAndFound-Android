package com.example.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.projetandroid.Entities.Message;
import com.example.projetandroid.Service.ServiceUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.projetandroid.MainActivity.sharedPrefFile;

public class MessageListAdapter extends ArrayAdapter<Message> {
    private Context context;
    private List<Message> messages;
    private SharedPreferences mPreferences;
    private Context mContext;


    public  MessageListAdapter(Context context,List<Message> messages){
        super(context,R.layout.list_messages,messages);
        this.context=context;
        this.messages=messages;
        this.mContext = context;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater LayoutInflater = android.view.LayoutInflater.from(context);
        convertView = LayoutInflater.inflate(R.layout.list_messages,parent,false);
        final Message message = messages.get(position);
        final TextView textViewsender = convertView.findViewById(R.id.textViewsender);
        final   TextView textviewmessage = convertView.findViewById(R.id.textViewmsg);
        mPreferences =  mContext.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);


        Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceUser.url).addConverterFactory(GsonConverterFactory.create()).build();
        ServiceUser s=retrofit.create(ServiceUser.class);
        Call<List<User>> call=s.getAll();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User>userlist=response.body();

                for (User p:userlist){
                    String str = String.valueOf(message.getIdreceived());

                    if(p.getId()== message.getIdsender())
                    {

                        textViewsender.setText(p.getNom());
                        textviewmessage.setText(message.getMessage());
                    }



                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Log.e("erreur",t.toString())      ;      }
        });




        return  convertView;

    }
}