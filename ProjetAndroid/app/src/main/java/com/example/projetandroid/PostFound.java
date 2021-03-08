package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

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

import android.os.Bundle;

    public class PostFound extends AppCompatActivity {
    public EditText Objet;
    public EditText Lieu;
    public EditText Description;
    public Button LOST;
    public Button FOUND;
    public TextView alert;
    public CheckBox Lost;
    public CheckBox Found;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_found);
        Objet=findViewById(R.id.Objet);
        Lieu=findViewById(R.id.Lieu);
        Description=findViewById(R.id.Description);
        LOST=findViewById(R.id.LOST);
        Lost=findViewById( R.id.Lost);
        Found=findViewById( R.id.Found);
        LOST.setEnabled(false);

        Lost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    Found.setChecked(false);
                    Lost.setChecked(true);
                    LOST.setEnabled(true);

                }else{
                    LOST.setEnabled(false);
                }

            }
        });
        Found.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    Lost.setChecked(false);
                    Found.setChecked(true);
                    FOUND.setEnabled(true);

                }else{
                    FOUND.setEnabled(false);
                }

            }
        });
        FOUND=findViewById(R.id.FOUND);
        FOUND.setEnabled(false);
        alert=findViewById(R.id.alert);
        Bundle b =getIntent().getExtras();
        final int id=b.getInt("iduser");
        final String nom=b.getString("nom");
        final String prenom=b.getString("prenom");
        final String numero=b.getString("numero");

        System.out.println("---------------------------------------- ID -----------------------");
        System.out.println(id);
        LOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceObjet.url).addConverterFactory(GsonConverterFactory.create()).build();
                ServiceObjet s=retrofit.create(ServiceObjet.class);
                com.example.projetandroid.Entities.Objet u =new Objet(id,Objet.getText().toString(),Lieu.getText().toString(),Description.getText().toString(),"LOST");

                Call<Objet> call=s.create(u);

                    call.enqueue(new Callback<Objet>() {
                        @Override
                        public void onResponse(Call<Objet> call, Response<Objet> response) {
                            Intent intent=new Intent(PostFound.this, HomeActivity.class);
                            Bundle b= new Bundle();
                            b.putInt("iduser",id);
                            b.putString("nom",nom);
                            b.putString("prenom",prenom);
                            b.putString("numero",numero);




                            intent.putExtras(b);
                            startActivity(intent);
                            Log.e("erreur",response.toString());
                        }

                        @Override
                        public void onFailure(Call<Objet> call, Throwable t) {
                            Log.e("erreur",t.toString());

                        }
                    });

                }
        });

        FOUND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceObjet.url).addConverterFactory(GsonConverterFactory.create()).build();
                ServiceObjet s=retrofit.create(ServiceObjet.class);
                com.example.projetandroid.Entities.Objet u =new Objet(id,Objet.getText().toString(),Lieu.getText().toString(),Description.getText().toString(),"FOUND");

                Call<Objet> call=s.create(u);

                call.enqueue(new Callback<Objet>() {
                    @Override
                    public void onResponse(Call<Objet> call, Response<Objet> response) {
                        Intent intent=new Intent(PostFound.this,HomeActivity.class);
                        startActivity(intent);Bundle b= new Bundle();
                        b.putInt("iduser",id);
                        b.putString("nom",nom);
                        b.putString("prenom",prenom);
                        b.putString("numero",numero);


                        intent.putExtras(b);
                        startActivity(intent);

                        Log.e("erreur",response.toString());
                    }

                    @Override
                    public void onFailure(Call<Objet> call, Throwable t) {
                        Log.e("erreur",t.toString());

                    }
                });

            }
        });
    }
}
