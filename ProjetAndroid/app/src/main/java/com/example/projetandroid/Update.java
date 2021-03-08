package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class Update extends AppCompatActivity {
    public EditText email;
    public EditText numero;
    public EditText password;
    public Button save;
    public TextView alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        email=findViewById(R.id.email);
        numero=findViewById(R.id.numero);
        password=findViewById(R.id.password);
        final Bundle a =getIntent().getExtras();
        final int id= a.getInt("iduser");
        save=findViewById(R.id.save);
        alert=findViewById(R.id.alert);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().equals("")) {
                    alert.setText("email Required");
                } else if (numero.getText().toString().equals("")) {
                    alert.setText("numero Required");
                } else if (password.getText().toString().equals("")) {
                    alert.setText(" Password Required");
                }

                {
                    User u = new User();

                    u.setId(id);
                    u.setEmail(email.getText().toString());
                    u.setNumtel(numero.getText().toString());
                    u.setPassword(password.getText().toString());
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(ServiceUser.url).addConverterFactory(GsonConverterFactory.create()).build();
                    ServiceUser s = retrofit.create(ServiceUser.class);
                    Call<User> call = s.update(u);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("erreur",t.toString());
                        }
                    });
                    Intent intent =new Intent(Update.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}


