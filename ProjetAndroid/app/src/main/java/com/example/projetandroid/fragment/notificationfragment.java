package com.example.projetandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.APImsg;
import com.example.projetandroid.reponsemsg;
import com.example.projetandroid.Entities.Message;
import com.example.projetandroid.Entities.Objet;
import com.example.projetandroid.MessageListAdapter;
import com.example.projetandroid.ObjectListAdapter;
import com.example.projetandroid.PostFound;
import com.example.projetandroid.R;
import com.example.projetandroid.Service.ServiceMessage;
import com.example.projetandroid.Service.ServiceObjet;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class notificationfragment extends Fragment {
    private ListView listmessages;
    private List<Message> messages;
    public Button ADD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View V= inflater.inflate(R.layout.fragment_notification,container,false);
        Bundle b = Objects.requireNonNull(getActivity()).getIntent().getExtras();
        final int id=b.getInt("iduser");

        listmessages = (ListView) V.findViewById(R.id.listmessages);
        ServiceMessage serviceMessage = APImsg.getClient().create(ServiceMessage.class) ;
        Call call = serviceMessage.getAll(id);
        Log.e("hello", String.valueOf(id));

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                messages = (List<Message>) response.body();
                listmessages.setAdapter(new MessageListAdapter(getActivity().getApplicationContext(),messages));

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                Log.e("erreur",t.toString());
            }


        });
        listmessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message listItem = (Message) listmessages.getItemAtPosition(position);
                Bundle b=new Bundle();
                b.putInt("idsender",listItem.getIdsender());
                b.putInt("idreceiver",listItem.getIdreceived());
                b.putString("message",listItem.getMessage());

                Intent intent=new Intent(view.getContext(), reponsemsg.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });


        return V ;

    }


}

