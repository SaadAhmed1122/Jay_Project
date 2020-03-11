package com.example.stockmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Showdetail extends AppCompatActivity {
TextView pname,r_p,w_p,qty,disc,sch,cty,cpn,ED1,ED2,ED3,NA;
ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);
        iv=(ImageView) findViewById(R.id.iv_single_product_image);
        pname= (TextView) findViewById(R.id.pname);
        r_p= (TextView) findViewById(R.id.r_price);
        w_p= (TextView) findViewById(R.id.w_price);
        qty=(TextView) findViewById(R.id.qty_show2);
        sch=(TextView) findViewById(R.id.skm);
        disc= (TextView) findViewById(R.id.disc);
        cty = (TextView) findViewById(R.id.ctr);
        cpn=(TextView) findViewById(R.id.cpne);
        ED1 = (TextView) findViewById(R.id.ex1);
        ED2= (TextView) findViewById(R.id.ex2);
        ED3 = (TextView) findViewById(R.id.ex3);
        NA = (TextView) findViewById(R.id.nt);


        Intent i = getIntent();

        //iv.setImageBitmap(i.("pimg"));
        pname.setText(i.getStringExtra("pname"));
        r_p.setText(i.getStringExtra("rprice"));
        w_p.setText(i.getStringExtra("hprice"));
        qty.setText(i.getStringExtra("qty"));
        sch.setText(i.getStringExtra("scheme"));
        disc.setText(i.getStringExtra("dicc"));
        cty.setText(i.getStringExtra("cty"));
        cpn.setText(i.getStringExtra("copen"));
        ED1.setText(i.getStringExtra("E.d1"));
        ED2.setText(i.getStringExtra("E.d2"));
        ED3.setText(i.getStringExtra("E.d3"));
        NA.setText(i.getStringExtra("N_A"));

    }
}
