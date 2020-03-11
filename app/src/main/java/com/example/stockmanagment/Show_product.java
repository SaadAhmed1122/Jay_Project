package com.example.stockmanagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_product extends AppCompatActivity {

    RecyclerView RV;
    EditText serch;
    SearchView sv1;
    DBHelper dbHelper;
    String title;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ref:
                showrecords();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refrashmenu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        RV = findViewById(R.id.recyclerView_show);
        serch = findViewById(R.id.tet_search);
        sv1 = findViewById(R.id.search_bar1);

        dbHelper = new DBHelper(this);

        Intent i = getIntent();
        title = i.getStringExtra("C_nme");
        //Toast.makeText(this, title, Toast.LENGTH_SHORT).show();

        showrecords();

        sv1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // dbHelper.search(query);
                RVAdaptor adaptor = new RVAdaptor(Show_product.this, dbHelper.search(query));
                RV.setHasFixedSize(true);
                RV.setAdapter(adaptor);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        if(serch != null){
            serch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                      }

                @Override
                public void afterTextChanged(Editable s) {
//                    String a= serch.getText().toString();
                   // dbHelper.search(s.toString());

                }
            });
        }
    }
    public void showrecords(){
        RVAdaptor adaptor = new RVAdaptor(Show_product.this, dbHelper.search_pro_b_c(title));
        RV.setHasFixedSize(true);

        RV.setLayoutManager(new LinearLayoutManager(this));
        RV.setAdapter(adaptor);
    }


    public void add_pro_open(View view) {
        startActivity(new Intent(Show_product.this, MainActivity.class));
    }
}
