package com.example.raiseyourvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.raiseyourvoice.Api.Api;
import com.example.raiseyourvoice.model.LoginRequest;
import com.example.raiseyourvoice.model.LoginResult;
import com.example.raiseyourvoice.model.LogoutResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LogoutActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

       Intent intent = getIntent();
       String token = intent.getStringExtra(HomeActivity.EXTRA_TEXT);

        bnv=findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.home);
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
                        startActivity(new Intent(getApplicationContext(),ShowSupervisedRoomsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        return true;
                }
                return false;
            }
        });

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //networking
                Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                Api api = retrofit.create(Api.class);
                Call<LogoutResult> call = api.logout(token);
                call.enqueue(new Callback<LogoutResult>() {
                    @Override
                    public void onResponse(Call<LogoutResult> call, Response<LogoutResult> response) {
                        Toast.makeText(LogoutActivity.this, response.body().getSuccess() + "    " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LogoutActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<LogoutResult> call, Throwable t) {
                        Toast.makeText(LogoutActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LogoutActivity.this, LogoutActivity.class);
               startActivity(intent);

            }
        });


    }
}