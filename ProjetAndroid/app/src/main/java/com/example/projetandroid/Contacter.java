package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetandroid.Entities.Message;
import com.example.projetandroid.Entities.Objet;
import com.example.projetandroid.Entities.User;
import com.example.projetandroid.Service.ServiceMessage;
import com.example.projetandroid.Service.ServiceObjet;
import com.example.projetandroid.Service.ServiceUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contacter extends AppCompatActivity {
        public EditText txtmessage;
        public Button btncontacter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacter);
final Bundle b=getIntent().getExtras();
        txtmessage=findViewById(R.id.txtmessage);
        btncontacter=findViewById(R.id.btnenvoyer);
        btncontacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceMessage serviceObjet = APIClient.getClient().create(ServiceMessage.class) ;
                Message o=new Message();
                o.setIdreceived(b.getInt("idreceived"));
                o.setIdsender(b.getInt("idsender"));
                o.setMessage(txtmessage.getText().toString());


                Call call = serviceObjet.create(o);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        ServiceUser s=APIClient.getClient().create(ServiceUser.class);
                        Call<List<User>> calll=s.getUser(b.getInt("idsender"));
                        calll.enqueue(new Callback<List<User>>() {
                            @Override
                            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                List<User>userlist=response.body();
                                    for (User p:userlist){
                                        Intent intent =new Intent(Contacter.this,HomeActivity.class);
                                        Log.e("user",p.toString());

                                        Bundle a =new Bundle();
                                        a.putString("nom",p.getNom());
                                        a.putString("prenom",p.getPrenom());
                                        a.putString("numero",p.getNumtel());
                                        a.putInt("iduser",p.getId());

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
