package com.example.raiseyourvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.raiseyourvoice.Api.Api;
import com.example.raiseyourvoice.model.LoginRequest;
import com.example.raiseyourvoice.model.LoginResult;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextInputLayout ilemail;
    TextInputLayout ilpassword;
    Button btn1;
    Button btn2;
    public static final String EXTRA_TEXT = "com.example.raiseyourvoice.EXTRA_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ilemail=findViewById(R.id.email_input_layout);
        ilpassword=findViewById(R.id.password_input_layout);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ilemail.getEditText().getText().toString().length() > 9 && ilpassword.getEditText().getText().toString().length() > 3) {
                    String email = ilemail.getEditText().getText().toString();
                    String password = ilpassword.getEditText().getText().toString();

                    //networking
                    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

                    LoginRequest loginRequest = new LoginRequest(email, password);
                    Api api = retrofit.create(Api.class);
                    Call<LoginResult> call = api.login(loginRequest);

                    call.enqueue(new Callback<LoginResult>() {
                        @Override
                        public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                            Toast.makeText(MainActivity.this, response.body().getSuccess() + "    " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                            if (response.body().getSuccess() == true) {
                               // String token = response.body().getToken();
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                               // intent.putExtra(EXTRA_TEXT,token);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResult> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Enter Correct Values", Toast.LENGTH_LONG).show();
                }
            }
            });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , CheckoutActivity.class);
                startActivity(intent);
            }
        });



}
}