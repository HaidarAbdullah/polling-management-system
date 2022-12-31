package com.example.raiseyourvoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.Toast;

import com.example.raiseyourvoice.Api.Api;
import com.example.raiseyourvoice.RVmodels.Room;
import com.example.raiseyourvoice.RVmodels.Supervisor;
import com.example.raiseyourvoice.adapter.RoomsAdapter;
import com.example.raiseyourvoice.adapter.SearchSupervisorsAdapter;
import com.example.raiseyourvoice.model.HomeResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;

public class SetSupervisorsActivity extends AppCompatActivity {
    ArrayList<Supervisor> supervisors=new ArrayList<>();
    RecyclerView rvSupervisors;
    SearchSupervisorsAdapter searchSupervisorsAdapter= new SearchSupervisorsAdapter();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_supervisors);

        searchView=findViewById(R.id.searchView);
        /*
        supervisors.add(new Supervisor("Ahmad" , "ahmad@gmail.com"));
        supervisors.add(new Supervisor("Ali" ," ali@gamil.com"));
        supervisors.add(new Supervisor("Zaid" ," zaid@gamil.com"));
        supervisors.add(new Supervisor("Haidar" ," haidar@gamil.com"));
        supervisors.add(new Supervisor("Nazeer" ," nazeer@gamil.com"));
        supervisors.add(new Supervisor("Issam" ," issam@gamil.com"));
        */

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