package com.example.raiseyourvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.raiseyourvoice.Api.Api;
import com.example.raiseyourvoice.adapter.RoomsAdapter;
import com.example.raiseyourvoice.adapter.SupervisedRoomsAdapter;
import com.example.raiseyourvoice.model.HomeResult;
import com.example.raiseyourvoice.model.PollingRoom;
import com.example.raiseyourvoice.model.SupervisedRoom;
import com.example.raiseyourvoice.model.SupervisedRoomResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowSupervisedRoomsActivity extends AppCompatActivity {
    ArrayList<SupervisedRoom> rooms=new ArrayList<>();
    RecyclerView rvSupervisedRooms;
    SupervisedRoomsAdapter supervisedRoomsAdapter= new SupervisedRoomsAdapter();

    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_supervised_rooms);

        Intent intent = getIntent();
        String token = intent.getStringExtra(HomeActivity.EXTRA_TEXT);

        bnv=findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.show_sup);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.create:
                        startActivity(new Intent(getApplicationContext(),CreatePollingRoomActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.show_inv:
                        startActivity(new Intent(getApplicationContext(),ShowSupervisingInvitationsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.show_sup:
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(),LogoutActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        //networking
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        Api api = retrofit.create(Api.class);
        Call<SupervisedRoomResult> call = api.showSupervisedRooms(token);

        call.enqueue(new Callback<SupervisedRoomResult>() {
                         @Override
                         public void onResponse(Call<SupervisedRoomResult> call, Response<SupervisedRoomResult> response) {
                             rvSupervisedRooms = findViewById(R.id.rvRooms);
                             rvSupervisedRooms.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false));
                             rvSupervisedRooms.setAdapter(supervisedRoomsAdapter);
                             rooms.addAll(response.body().getPollingRooms());
                             supervisedRoomsAdapter.addItems(rooms);
                         }

                         @Override
                         public void onFailure(Call<SupervisedRoomResult> call, Throwable t) {
                             Toast.makeText(ShowSupervisedRoomsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
                     );




    }
}