package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


public class Register extends AppCompatActivity {
public EditText nom;
public EditText prenom;
public EditText email;
public EditText password;
public EditText numtel;
public Button save;
public EditText confirm;
public TextView alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nom=findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        numtel=findViewById(R.id.numero);
         save=findViewById(R.id.save);
         confirm=findViewById(R.id.txtconfirm);
         alert=findViewById(R.id.alert);
         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceUser.url).addConverterFactory(GsonConverterFactory.create()).build();
                 ServiceUser s=retrofit.create(ServiceUser.class);
                 User u =new User(nom.getText().toString(),prenom.getText().toString(),email.getText().toString(),password.getText().toString(),numtel.getText().toString());

                 Call<User> call=s.create(u);
                 if(!password.getText().toString().equals(confirm.getText().toString())){
                     alert.setText("password and onfirm password not the same");
                 }else {
                 call.enqueue(new Callback<User>() {
                     @Override
                     public void onResponse(Call<User> call, Response<User> response) {
                         Log.e("erreur",response.toString());
                     }

                     @Override
                     public void onFailure(Call<User> call, Throwable t) {
                         Log.e("erreur",t.toString());

                     }
                 });
                 Intent intent=new Intent(Register.this,MainActivity.class);
                 startActivity(intent);
             }}
         });

    }
}
