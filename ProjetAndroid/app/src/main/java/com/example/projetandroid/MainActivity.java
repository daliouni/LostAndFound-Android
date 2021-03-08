package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetandroid.Entities.User;
import com.example.projetandroid.Service.ServiceUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
public EditText txtemail;
public EditText txtpassword;
public Button signin;
public TextView signup;
    private SharedPreferences mPreferences;
    public static final String sharedPrefFile = "com.example.projetandroid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtemail=findViewById(R.id.txtemail);
        txtpassword=findViewById(R.id.txtpassword);
        signin=findViewById(R.id.button);
        signup=findViewById(R.id.txtsignup);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent intent =new Intent(MainActivity.this,Register.class);
startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceUser.url).addConverterFactory(GsonConverterFactory.create()).build();
                ServiceUser s=retrofit.create(ServiceUser.class);
                Call<List<User>> call=s.getAll();
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        List<User>userlist=response.body();
                        for (User p:userlist){
                            if (p.getEmail().equals(txtemail.getText().toString())&& p.getPassword().equals(txtpassword.getText().toString())){
                                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                                Bundle a =new Bundle();
                                SharedPreferences.Editor prefencesEditor =mPreferences.edit();
                                p.getId();
                                String strI = String.valueOf(p.getId());

                                prefencesEditor.putString("iduserc",strI);
                                prefencesEditor.apply();
                                a.putString("nom",p.getNom());
                                a.putString("prenom",p.getPrenom());
                                a.putString("numero",p.getNumtel());
                                a.putInt("iduser",p.getId());
                                intent.putExtras(a);
                                startActivity(intent);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.e("erreur",t.toString());

                    }
                });

            }
        });

    }
}
