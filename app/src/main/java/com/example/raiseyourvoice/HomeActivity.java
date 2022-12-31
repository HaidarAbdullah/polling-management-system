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
import com.example.raiseyourvoice.model.HomeResult;
import com.example.raiseyourvoice.model.PollingRoom;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {
    ArrayList<PollingRoom> rooms=new ArrayList<>();
    RecyclerView rvRooms;
    RoomsAdapter roomsAdapter= new RoomsAdapter();

    BottomNavigationView bnv;
    public static final String EXTRA_TEXT = "com.example.raiseyourvoice.EXTRA_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String token = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        bnv=findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.home);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.create:
                        Intent intent1 = new Intent(getApplicationContext(),CreatePollingRoomActivity.class);
                        intent1.putExtra(EXTRA_TEXT,token);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.show_inv:
                        Intent intent2 = new Intent(getApplicationContext(),ShowSupervisingInvitationsActivity.class);
                        intent2.putExtra(EXTRA_TEXT,token);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.show_sup:
                        Intent intent3 = new Intent(getApplicationContext(),ShowSupervisedRoomsActivity.class);
                        intent3.putExtra(EXTRA_TEXT,token);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        Intent intent4 = new Intent(getApplicationContext(),LogoutActivity.class);
                        intent4.putExtra(EXTRA_TEXT,token);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });

        /*
        rooms.add(new Room(1,"IEE","2022-8-14 12:30","2022-8-14 6:30","this poll is for feeding back"));
        rooms.add(new Room(1,"IEE","2022-8-14 12:30","2022-8-14 6:30","this poll is for feeding back"));
        rooms.add(new Room(1,"IEE","2022-8-14 12:30","2022-8-14 6:30","this poll is for feeding back"));
        rooms.add(new Room(1,"IEE","2022-8-14 12:30","2022-8-14 6:30","this poll is for feeding back"));
        rooms.add(new Room(1,"IEE","2022-8-14 12:30","2022-8-14 6:30","this poll is for feeding back"));
        rooms.add(new Room(1,"IEE","2022-8-14 12:30","2022-8-14 6:30","this poll is for feeding back"));
        */

        //networking
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        Api api = retrofit.create(Api.class);
        Call<HomeResult> call = api.getRooms(token);
        call.enqueue(new Callback<HomeResult>() {
            @Override
            public void onResponse(Call<HomeResult> call, Response<HomeResult> response) {
                rvRooms=findViewById(R.id.rvRooms);
                rvRooms.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false));
                rvRooms.setAdapter(roomsAdapter);
                rooms.addAll(response.body().getPollingRooms());
                roomsAdapter.addItems(rooms);
            }

            @Override
            public void onFailure(Call<HomeResult> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}