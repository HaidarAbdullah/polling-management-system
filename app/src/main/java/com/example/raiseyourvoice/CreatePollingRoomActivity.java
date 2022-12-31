package com.example.raiseyourvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.raiseyourvoice.Api.Api;
import com.example.raiseyourvoice.model.CheckoutResult;
import com.example.raiseyourvoice.model.CreatePollingRoomRequest;
import com.example.raiseyourvoice.model.CreatePollingRoomResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreatePollingRoomActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    EditText date_time_in1,date_time_in2;
    DatePickerDialog.OnDateSetListener setListener;
    TextInputLayout il1,il2,il3;
    Button btn;
    RadioButton rb1,rb2;
    public static final String EXTRA_TEXT = "com.example.raiseyourvoice.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_polling_room);

        Intent intent = getIntent();
        String token = intent.getStringExtra(HomeActivity.EXTRA_TEXT);

        bnv=findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.create);
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
                        startActivity(new Intent(getApplicationContext(),LogoutActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        //************************************************************//

        date_time_in1=findViewById(R.id.et1);
        date_time_in2=findViewById(R.id.et2);

        date_time_in1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(date_time_in1);
            }
        });

        date_time_in2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(date_time_in2);
            }
        });


        il1=findViewById(R.id.input_layout1);
        il2=findViewById(R.id.input_layout2);
        il3=findViewById(R.id.input_layout3);

        btn=findViewById(R.id.btn);

        rb1=findViewById(R.id.conditional);
        rb2=findViewById(R.id.not_conditional);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = il1.getEditText().getText().toString();
                String s2 = il2.getEditText().getText().toString();
                String s3 = il3.getEditText().getText().toString();

                boolean isConditional = rb1.isSelected()?true:false;

                String time1=date_time_in1.getText().toString();
                String time2=date_time_in2.getText().toString();
                if (s1.length()!=0 &&  s2.length()!=0 && s3.length()!=0 && time1.length()!=0 && time2.length()!=0)
                {
                   CreatePollingRoomRequest createPollingRoomRequest = new CreatePollingRoomRequest(s1,time1,time2,s2,isConditional,s3);
                    Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
                    Api api = retrofit.create(Api.class);
                    Call<CreatePollingRoomResult> call = api.createPollingRoom(token,createPollingRoomRequest);
                    call.enqueue(new Callback<CreatePollingRoomResult>() {
                        @Override
                        public void onResponse(Call<CreatePollingRoomResult> call, Response<CreatePollingRoomResult> response) {
                            Toast.makeText(CreatePollingRoomActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            if (response.body().isSuccess() == true) {
                                Intent intent = new Intent(CreatePollingRoomActivity.this,CreatePollingBoxActivity.class );
                                intent.putExtra(EXTRA_TEXT,token);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<CreatePollingRoomResult> call, Throwable t) {
                            Toast.makeText(CreatePollingRoomActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent intent = new Intent(CreatePollingRoomActivity.this,CreatePollingBoxActivity.class );
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(CreatePollingRoomActivity.this, "All fields required", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void showDateTimeDialog(EditText date_time_in) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE , minute);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(CreatePollingRoomActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY)
                                                                                   ,calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(CreatePollingRoomActivity.this,dateSetListener,calendar.get(Calendar.YEAR)
                                                                                  ,calendar.get(Calendar.MONTH)
                                                                                  ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimeDialog(EditText time_in) {
        Calendar calendar=Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE , minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(CreatePollingRoomActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY)
                                                                           ,calendar.get(Calendar.MINUTE)
                                                                           ,false).show();
    }

    private void showDateDialog(EditText date_in) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               calendar.set(Calendar.YEAR,year);
               calendar.set(Calendar.MONTH,month);
               calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(CreatePollingRoomActivity.this,dateSetListener,calendar.get(Calendar.YEAR)
                                                                                  ,calendar.get(Calendar.MONTH)
                                                                                  ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}