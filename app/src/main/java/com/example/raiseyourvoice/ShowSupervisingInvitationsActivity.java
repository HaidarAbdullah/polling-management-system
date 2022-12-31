package com.example.raiseyourvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShowSupervisingInvitationsActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_supervising_invitations);

        /*Intent intent = getIntent();
        String token = intent.getStringExtra(HomeActivity.EXTRA_TEXT);*/

        bnv=findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.show_inv);
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
                        return true;
                    case R.id.show_sup:
                        startActivity(new Intent(getApplicationContext(),ShowSupervisedRoomsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(),LogoutActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}