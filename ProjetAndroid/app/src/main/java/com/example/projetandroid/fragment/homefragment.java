package com.example.projetandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projetandroid.APIClient;
import com.example.projetandroid.DetailObjet;
import com.example.projetandroid.Entities.Objet;
import com.example.projetandroid.Entities.User;
import com.example.projetandroid.ObjectListAdapter;
import com.example.projetandroid.Service.ServiceObjet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.projetandroid.PostFound;

import com.example.projetandroid.R;
import com.example.projetandroid.Service.ServiceUser;

public class homefragment extends Fragment  {
    private ListView Listviewobj;
    private List<Objet> objets;
    public Button ADD;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,  Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_home,container,false);
        ADD=V.findViewById(R.id.ADD);
        Bundle b =getActivity().getIntent().getExtras();
    //    Log.e("bndle",savedInstanceState.toString());
        assert b != null;
        final int id=b.getInt("iduser");
        final String nom=b.getString("nom");
        final String prenom=b.getString("prenom");
        final String numero=b.getString("numero");
        final int ss=b.getInt("iduser");


        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),PostFound.class);
                Bundle b =new Bundle();
                b.putInt("iduser",id);
                b.putString("nom",nom);
                b.putString("prenom",prenom);
                b.putString("numero",numero);
                intent.putExtras(b);
                startActivity(intent);

            }
        });
        Listviewobj = (ListView) V.findViewById(R.id.Listviewobj);
        ServiceObjet serviceObjet = APIClient.getClient().create(ServiceObjet.class) ;
        Call call = serviceObjet.getAll();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                objets = (List<Objet>) response.body();
                Listviewobj.setAdapter(new ObjectListAdapter(getActivity().getApplicationContext(),objets));

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                Log.e("erreur",t.toString());
            }


        });
        Listviewobj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Objet listItem = (Objet) Listviewobj.getItemAtPosition(position);
                Bundle b=new Bundle();
                b.putInt("user",listItem.getIduser());
                b.putString("objet",listItem.getObjet());
                b.putString("etat",listItem.getEtat());
                b.putString("lieu",listItem.getLieu());
                b.putString("description",listItem.getDescription());
                b.putInt("sender",ss );
                Intent intent=new Intent(view.getContext(), DetailObjet.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });


        return V ;

    }

}

