package com.example.raiseyourvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.raiseyourvoice.Api.Api;
import com.example.raiseyourvoice.model.CheckoutRequest;
import com.example.raiseyourvoice.model.CheckoutResult;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckoutActivity extends AppCompatActivity {
    TextInputLayout ilnumber;
    Button btn ;
    public static final String EXTRA_TEXT = "com.example.raiseyourvoice.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //link layout items
        ilnumber = findViewById(R.id.number_input_layout);
        btn=findViewById(R.id.btn1);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ilnumber.getEditText().getText().toString() != "" &&  ilnumber.getEditText().getText().toString().length()==11) {
                    //networking
                    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

                    String number = ilnumber.getEditText().getText().toString();
                    CheckoutRequest checkoutRequest = new CheckoutRequest(number);
                    Api api = retrofit.create(Api.class);

                    Call<CheckoutResult> call = api.checkout(checkoutRequest);

                    call.enqueue(new Callback<CheckoutResult>() {
                        @Override
                        public void onResponse(Call<CheckoutResult> call, Response<CheckoutResult> response) {
                            Toast.makeText(CheckoutActivity.this, response.body().getSuccess() + "    " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                            if (response.body().getSuccess() == true) {
                                Intent intent = new Intent(CheckoutActivity.this, SubmitRequestActivity.class);
                                //here we pass the number to the next activity
                                intent.putExtra(EXTRA_TEXT, number);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<CheckoutResult> call, Throwable t) {
                            Toast.makeText(CheckoutActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    });
                }

                else
                {
                    Toast.makeText(CheckoutActivity.this, "Please Enter a Correct Number", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}