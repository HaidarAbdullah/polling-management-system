package com.example.raiseyourvoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raiseyourvoice.Api.Api;
import com.example.raiseyourvoice.RVmodels.Option;
import com.example.raiseyourvoice.RVmodels.Supervisor;
import com.example.raiseyourvoice.adapter.SearchSupervisorsAdapter;
import com.example.raiseyourvoice.model.LoginRequest;
import com.example.raiseyourvoice.model.LoginResult;
import com.example.raiseyourvoice.model.PollingBoxRequest;
import com.example.raiseyourvoice.model.PollingBoxResult;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreatePollingBoxActivity extends AppCompatActivity {
    ArrayList<Option> options=new ArrayList<>();
    Button btn;
    TextInputLayout il1,ilop1,ilop2,ilop3,ilop4,ilop5;

    ArrayList<Supervisor> supervisors=new ArrayList<>();
    RecyclerView rvSupervisors;
    SearchSupervisorsAdapter searchSupervisorsAdapter= new SearchSupervisorsAdapter();
    SearchView searchView;
    public static final String EXTRA_TEXT = "com.example.raiseyourvoice.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_polling_box);

        Intent intent = getIntent();
        String token = intent.getStringExtra(CreatePollingRoomActivity.EXTRA_TEXT);

        btn=findViewById(R.id.btn);
        il1=findViewById(R.id.il1);
        ilop1=findViewById(R.id.ilop1);
        ilop2=findViewById(R.id.ilop2);
        ilop3=findViewById(R.id.ilop3);
        ilop4=findViewById(R.id.ilop4);
        ilop5=findViewById(R.id.ilop5);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (il1.getEditText().getText().toString().length() > 9 && ilop1.getEditText().getText().toString().length() > 5
                        && ilop2.getEditText().getText().toString().length() > 5
                        && ilop3.getEditText().getText().toString().length() > 3
                        && ilop4.getEditText().getText().toString().length() > 5
                        && ilop5.getEditText().getText().toString().length() > 5) {
                    String desc = il1.getEditText().getText().toString();
                    String option1 = ilop1.getEditText().getText().toString();
                    String option2 = ilop2.getEditText().getText().toString();
                    String option3 = ilop3.getEditText().getText().toString();
                    String option4 = ilop4.getEditText().getText().toString();
                    String option5 = ilop5.getEditText().getText().toString();

                    //networking
                    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

                    PollingBoxRequest pollingBoxRequest = new PollingBoxRequest(desc, option1, option2, option3, option4, option5);
                    Api api = retrofit.create(Api.class);
                    Call<PollingBoxResult> call = api.setPollingBox(token,pollingBoxRequest);
                    call.enqueue(new Callback<PollingBoxResult>() {
                        @Override
                        public void onResponse(Call<PollingBoxResult> call, Response<PollingBoxResult> response) {
                            Toast.makeText(CreatePollingBoxActivity.this, response.body().getSuccess() + "    " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                            if (response.body().getSuccess() == true) {
                                // String token = response.body().getToken();
                                Intent intent = new Intent(CreatePollingBoxActivity.this, HomeActivity.class);
                                intent.putExtra(EXTRA_TEXT,token);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<PollingBoxResult> call, Throwable t) {
                            Toast.makeText(CreatePollingBoxActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent intent = new Intent(CreatePollingBoxActivity.this,SetSupervisorsActivity.class);
                    Toast.makeText(CreatePollingBoxActivity.this, "successfully created", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else
                    {
                    Toast.makeText(CreatePollingBoxActivity.this, "Please Enter Correct Values", Toast.LENGTH_LONG).show();
                    }
            }
        });


        searchView=findViewById(R.id.searchView);

        supervisors.add(new Supervisor("Ahmad" , "ahmad@gmail.com"));
        supervisors.add(new Supervisor("Ali" ," ali@gamil.com"));
        supervisors.add(new Supervisor("Zaid" ," zaid@gamil.com"));
        supervisors.add(new Supervisor("Haidar" ," haidar@gamil.com"));
        supervisors.add(new Supervisor("Nazeer" ," nazeer@gamil.com"));
        supervisors.add(new Supervisor("Issam" ," issam@gamil.com"));

        rvSupervisors=findViewById(R.id.rvsupervisors);
        rvSupervisors.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false));
        rvSupervisors.setAdapter(searchSupervisorsAdapter);

        searchSupervisorsAdapter.addItems(supervisors);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return true;
            }
        });
    }
    private void fileList(String text) {
        ArrayList<Supervisor> filteredList = new ArrayList<>();
        for (Supervisor supervisor : supervisors)
        {
            if(supervisor.getName().toLowerCase().contains(text.toLowerCase())
                    || supervisor.getContact_email().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(supervisor);
            }
            if (filteredList.isEmpty())
            {
                Toast.makeText(this,"No data Found",Toast.LENGTH_SHORT).show();
            }
            else
            {
                searchSupervisorsAdapter.setFilteredList(filteredList);
            }
        }
    }
}