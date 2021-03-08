package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.projetandroid.Entities.User;
import com.example.projetandroid.Service.ServiceUser;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailObjet extends FragmentActivity implements OnMapReadyCallback {
    public TextView usertxt;
    public TextView objettxt;
    public TextView etattxt;
    public TextView lieutxt;
    public TextView descriptiontxt;
    public Button btncontacter;
    public GoogleMap map;
    public SupportMapFragment mapFragment;
    public SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_objet);
        searchView = findViewById(R.id.sv_location);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location= searchView.getQuery().toString();
                List<Address> addressList = null;
                if(map!=null && (location != null || !location.equals("")))
                {
                    Geocoder geocoder = new Geocoder(DetailObjet.this);
                    try {
                        addressList = geocoder.getFromLocationName(location,1);

                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
        usertxt=findViewById(R.id.txtuser);
        objettxt=findViewById(R.id.txtobjet);
        etattxt=findViewById(R.id.txtetat);
        lieutxt=findViewById(R.id.txtlieu);
        descriptiontxt=findViewById(R.id.txtdescription);
        btncontacter=findViewById(R.id.btncontacter);
        final Bundle b =getIntent().getExtras();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceUser.url).addConverterFactory(GsonConverterFactory.create()).build();
        ServiceUser s=retrofit.create(ServiceUser.class);
        Call<List<User>> call=s.getAll();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User>userlist=response.body();
                for (User p:userlist){
                    if (p.getId()== b.getInt("user")) {
                        usertxt.setText(p.getNom());
                    }


                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Log.e("erreur",t.toString())      ;      }
        });

        objettxt.setText(b.getString("objet"));
        etattxt.setText(b.getString("etat"));
        lieutxt.setText(b.getString("lieu"));
        descriptiontxt.setText(b.getString("description"));
        btncontacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(DetailObjet.this,Contacter.class);
                Bundle a=new Bundle();
                a.putInt("idreceived",b.getInt("user"));
                a.putInt("idsender",b.getInt("sender"));
                intent.putExtras(a);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;


    }
}
