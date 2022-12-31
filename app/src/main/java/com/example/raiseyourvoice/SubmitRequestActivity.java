package com.example.raiseyourvoice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raiseyourvoice.Api.Api;
import com.example.raiseyourvoice.model.CheckoutRequest;
import com.example.raiseyourvoice.model.CheckoutResult;
import com.example.raiseyourvoice.model.RegisterRequestResult;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;

public class SubmitRequestActivity extends AppCompatActivity {

    TextInputLayout ilname;
    TextInputLayout ilcemail;
    TextInputLayout ilremail;
    TextInputLayout ilpassword;
    TextInputLayout ilconfirmpassword;
    TextView tv ;
    Button btn1,btn2 ;
    ImageView img;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_request);

        ilname=findViewById(R.id.name_input_layout);
        ilcemail=findViewById(R.id.contact_email_input_layout);
        ilremail=findViewById(R.id.registeration_email_input_layout);
        ilpassword=findViewById(R.id.password_input_layout);
        ilconfirmpassword=findViewById(R.id.confirm_password_input_layout);
        btn1=findViewById(R.id.upload_image);
        btn2=findViewById(R.id.send_request);
        img=findViewById(R.id.image);
        tv=findViewById(R.id.tv);

        //recieve number from checkout activity
        Intent intent = getIntent();
        String number = intent.getStringExtra(CheckoutActivity.EXTRA_TEXT);

        tv.setText(number);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(ContextCompat.checkSelfPermission(getApplicationContext(),
                         Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                 {
                     //if the permission ok , we use Intent to choose the image from gallery
                     Intent intent = new Intent();
                     intent.setType("image/*");
                     intent.setAction(Intent.ACTION_GET_CONTENT);
                     startActivityForResult(intent,10);
                 }
                 else
                 {
                     ActivityCompat.requestPermissions(SubmitRequestActivity.this
                             ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                 }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ilname.getEditText().getText().toString();
                String contact_email = ilcemail.getEditText().getText().toString();
                String registeration_email=ilremail.getEditText().getText().toString();
                String password=ilpassword.getEditText().getText().toString();

                submitRrequest(name,contact_email,registeration_email,password,number);

                Intent intent = new Intent(SubmitRequestActivity.this , RequestConfirmationActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10 && resultCode== Activity.RESULT_OK)
        {
            Uri uri = data.getData();
            Context context = SubmitRequestActivity.this;
            //we will save the path of the image in the variable "path"
            path = RealPathUtil.getRealPath(context , uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            img=findViewById(R.id.image);
            img.setImageBitmap(bitmap);
        }
    }

    public void submitRrequest(String uname , String cemail , String remail , String upassword , String number)
    {
        //networking
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

        RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"),uname);
        RequestBody contact_email = RequestBody.create(MediaType.parse("multipart/form-data"),cemail);
        RequestBody registeration_email = RequestBody.create(MediaType.parse("multipart/form-data"),remail);
        RequestBody password = RequestBody.create(MediaType.parse("multipart/form-data"),upassword);
        RequestBody national_number = RequestBody.create(MediaType.parse("multipart/form-data"),number);

        File file = new File(path) ;
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image",file.getName(),requestFile);

        Api api = retrofit.create(Api.class);

        Call<RegisterRequestResult> call = api.submitRegisterRequest(name,contact_email,registeration_email,password,national_number,body);

        call.enqueue(new Callback<RegisterRequestResult>() {
            @Override
            public void onResponse(Call<RegisterRequestResult> call, Response<RegisterRequestResult> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(SubmitRequestActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterRequestResult> call, Throwable t) {
                Toast.makeText(SubmitRequestActivity.this,"error !!!",Toast.LENGTH_LONG).show();
            }
        });


    }
}