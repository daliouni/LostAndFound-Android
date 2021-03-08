package com.example.projetandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.PostFound;
import com.example.projetandroid.R;
import com.example.projetandroid.Update;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class profilfragment extends Fragment {
    public TextView nomprenom;
    public TextView numero;
    public Button update;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_profil,container,false);

        nomprenom=V.findViewById(R.id.txtnomprenom);
        numero=V.findViewById(R.id.numtel);
        update=V.findViewById(R.id.update);
        Bundle b = Objects.requireNonNull(getActivity()).getIntent().getExtras();
        String nom=  b.getString("nom");
        String prenom=b.getString("prenom");
        String numero=b.getString("numero");
        final int id=b.getInt("iduser");

        nomprenom.setText(nom+" "+prenom);
        this.numero.setText(numero);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Update.class);
                Bundle b= new Bundle();
                b.putInt("iduser",id);
                intent.putExtras(b);
                startActivity(intent);

            }
        });



        return  V;


}
}
