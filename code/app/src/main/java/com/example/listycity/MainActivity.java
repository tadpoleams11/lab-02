package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //name the things we're going to use
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addBtn;
    Button delBtn;
    EditText userIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); //links the main to the xml like serving css to html kinda
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //stuff above is main func stuff
        //add loigc here under the oncrate function within it lowkey
        cityList = findViewById(R.id.city_list);


        String []cities = {"Edmonton", "Edson", "Calgary", "Moscow", "Sydney", "Berlin", "Vienna", "Vancouver", "Tokyo", "Beijing", "Osaka", "coolTown"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));


        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList); //define the cityadapter
        cityList.setAdapter(cityAdapter); //set anf use the adapter

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //these lines from stack overflow on Listview deletion
                AlertDialog.Builder adb =new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                dataList.remove(position);
                cityAdapter.notifyDataSetChanged();
            }
        });

        addBtn = findViewById(R.id.add_btn);
        userIn = findViewById(R.id.user_in);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //following 4 code lines adapted from GeeksforGeeks "How to Dynamically Add Elements to a ListView in Android?" tutorial
                String item = userIn.getText().toString();
                // make sure item is not empty
                if (!item.isEmpty()) {
                    dataList.add(item);
                    cityAdapter.notifyDataSetChanged();

                }
            }
        });
    }
}