package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetandroid.Entities.Message;
import com.example.projetandroid.Entities.User;
import com.example.projetandroid.Service.ServiceMessage;
import com.example.projetandroid.Service.ServiceUser;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class reponsemsg extends  AppCompatActivity {
    public TextView TextViewsender;
    public TextView TextViewmsg;
    public TextView txtrepondre;
    public Button btnrepondre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponsemsg);
        TextViewsender = findViewById(R.id.sender);
        TextViewmsg = findViewById(R.id.textViewmsg);
        txtrepondre = findViewById(R.id.txtrepondre);
        btnrepondre = findViewById(R.id.btnrepondre);
        final Bundle b = getIntent().getExtras();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServiceUser.url).addConverterFactory(GsonConverterFactory.create()).build();
        ServiceUser s = retrofit.create(ServiceUser.class);
        Call<List<User>> call = s.getAll();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userlist = response.body();

                for (User p : userlist) {
                    if (p.getId() == b.getInt("idsender")) {
                        Log.e("heeeeeey", p.toString());
                        Log.e("yaaaaaas", String.valueOf(b.getInt("idsender")));
                        TextViewsender.setText(p.getNom());
                    }


                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Log.e("erreur", t.toString());
            }
        });
        TextViewmsg.setText(b.getString("message"));
        btnrepondre.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               ServiceMessage serviceObjet = APIClient.getClient().create(ServiceMessage.class);
                                               Message o = new Message();
                                               o.setIdreceived(b.getInt("idsender"));
                                               o.setIdsender(b.getInt("idreceiver"));
                                               o.setMessage(txtrepondre.getText().toString());

                                               Call call = serviceObjet.create(o);
                                               call.enqueue(new Callback() {
                                                   @Override
                                                   public void onResponse(Call call, Response response) {
                                                       ServiceUser s = APIClient.getClient().create(ServiceUser.class);
                                                       Call<List<User>> calll = s.getUser(b.getInt("idreceiver"));
                                                       calll.enqueue(new Callback<List<User>>() {
                                                           @Override
                                                           public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                                               List<User> userlist = response.body();
                                                               for (User p : userlist) {
                                                                   Intent intent = new Intent(reponsemsg.this, HomeActivity.class);
                                                                   Log.e("user", p.toString());

                                                                   Bundle a = new Bundle();
                                                                   a.putString("nom", p.getNom());
                                                                   a.putString("prenom", p.getPrenom());
                                                                   a.putString("numero", p.getNumtel());
                                                                   a.putInt("iduser", p.getId());

                                                                   intent.putExtras(a);
                                                                   startActivity(intent);
                                                               }
                                                           }

                                                           @Override
                                                           public void onFailure(Call<List<User>> call, Throwable t) {

                                                           }
                                                       });
                                                   }


                                                   @Override
                                                   public void onFailure(Call call, Throwable t) {

                                                   }
                                               });
                                           }


                                       }

        );

    }
}

