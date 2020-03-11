package com.example.stockmanagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.SearchView;
import android.widget.Toast;

public class show_company_list extends AppCompatActivity {

    RecyclerView rv ;
    SearchView sv;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_company_list);
        rv= (RecyclerView) findViewById(R.id.recycle_show_compnay);
        sv = (SearchView) findViewById(R.id.search_bar_c);
        dbHelper = new DBHelper(this);
        CompanyAdaptor adaptor = new CompanyAdaptor(show_company_list.this, dbHelper.getAllCompany());
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adaptor);
//        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                Toast.makeText(show_company_list.this,"Clicked", Toast.LENGTH_SHORT).show();
//
//                return true;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

    }

}
