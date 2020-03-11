package com.example.stockmanagment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText pro_name,Retail_p,Qty_pc,Qty_dz,Whole_r_dz,whole_r_cartoon,discount_dz,scheme,net_amount_pc,net_amount_dz,ex_discount1_dz,
    ex_discount2_dz, ex_discount3_dz, city,final_a_pc,final_a_dz;
    ImageView pro_img;
    public Bitmap photo;
    public Uri imagefilepath;
    private static final int CAMERA_REQUEST = 1888;
    private static final int PICK_IMAGE_REQUEST = 100;
    private final DBHelper mydb = DBHelper.getDBHelper(this);
    EditText Companyname;
    DBHelper dbHelper;


    Spinner coupen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Setupview();
        qty_measurment();
        settinnwholesalerate();
        netamount();

        //companymodel = new Companymodel("Samsung");
        registerForContextMenu(Companyname);

    }

    private void netamount() {
        scheme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Whole_r_dz.getText().toString().equals("") && discount_dz.getText().toString().equals("")){
                    Whole_r_dz.setError("Plz Enter Price");
                    discount_dz.setError("Plz Enter discount");
                    }
                else{
                float whole_price_d =Float.parseFloat(Whole_r_dz.getText().toString());
                float discount1 =Float.parseFloat(discount_dz.getText().toString());
                float scheme1 = Float.parseFloat(scheme.getText().toString());
                float b = 12 + scheme1;
                float c = whole_price_d / b;
                float d = (c * discount1) / 100;
                float net = c - d;
                float net2 = net * 12;
                float net3= net2 * 12;
                    net_amount_pc.setText(String.valueOf(net2));
                    net_amount_dz.setText(String.valueOf(net3));
            }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ex_discount3_dz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Float dis1= Float.parseFloat(ex_discount1_dz.getText().toString());
                Float dis2 = Float.parseFloat(ex_discount2_dz.getText().toString());
                Float dis3= Float.parseFloat(ex_discount3_dz.getText().toString());
                Float net_a_p = Float.parseFloat(net_amount_pc.getText().toString());

                Float final1_a = (net_a_p * dis1) / 100;
                Float aa = net_a_p - final1_a;

                Float finalb = (aa * dis2) / 100;
                Float aa2= aa - finalb;

                Float finalc= (aa2 * dis3) / 100;
                Float aa3 = aa2 - finalc;

                final_a_pc.setText(String.valueOf(aa3));

                Float bt = Float.parseFloat(final_a_pc.getText().toString());

                Float bt2 = bt * 12;

                final_a_dz.setText(String.valueOf(bt2));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    private void settinnwholesalerate() {
        Whole_r_dz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(Qty_pc.getText().toString())){
                    return;
                }
                else{
                    Float a = Float.parseFloat(String.valueOf(Whole_r_dz.getText()));
                    Float b = a * 12;
                    whole_r_cartoon.setText(String.valueOf(b));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void Setupview(){
        pro_name = (EditText) findViewById(R.id.editText_proname);
        Retail_p = (EditText) findViewById(R.id.editText_Retail_price);
            Qty_pc = (EditText) findViewById(R.id.editText_Quantiy_pc);
            Qty_dz = (EditText) findViewById(R.id.editText_Quantiy_darzan);
            whole_r_cartoon = (EditText) findViewById(R.id.editText_wholesalerate_pr_cartoon);
            Whole_r_dz = (EditText) findViewById(R.id.editText_wholesalerate_pr_derzan);
            discount_dz =(EditText) findViewById(R.id.editText_discount_per_d);
            scheme = (EditText) findViewById(R.id.editText_scheme);
            net_amount_dz = (EditText) findViewById(R.id.editText1_net_Amount_darzan);
            net_amount_pc = (EditText) findViewById(R.id.editText1_net_Amount_pc);
            ex_discount1_dz =(EditText) findViewById(R.id.editText1_extra_bonus);
            ex_discount2_dz = (EditText) findViewById(R.id.editText1_extra_dic2);
            ex_discount3_dz = (EditText) findViewById(R.id.editText1_extra_dic3);
            city = (EditText) findViewById(R.id.editText1_city);
            final_a_dz =(EditText) findViewById(R.id.editText1_finalAmount_dz);
            final_a_pc = (EditText) findViewById(R.id.editText1_finalAmount_pc);
            pro_img = (ImageView) findViewById(R.id.imageView_pro);
            Companyname =(EditText) findViewById(R.id.c_name);

        coupen = (Spinner) findViewById(R.id.spinner);

    }
    public void qty_measurment(){
        Qty_pc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(Qty_pc.getText().toString())){
                    Qty_dz.setText("");
                    return;
                }
                if(!Qty_pc.toString().equals("")){
                    float a = Float.parseFloat(String.valueOf(Qty_pc.getText()));
                    float b = a / 12;
                    Qty_dz.setText(String.valueOf(b));}

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public void dilog_open(View view) {
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.dialog_design, null, false);
        dialogbuilder.setView(dialogview);

        final ImageView galaryopen = (ImageView) dialogview.findViewById(R.id.layout_gallery);
        final ImageView cameraopne = (ImageView) dialogview.findViewById(R.id.layout_camera);

        final AlertDialog alertDialog = dialogbuilder.create();
        alertDialog.show();

        cameraopne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,CAMERA_REQUEST);
                alertDialog.dismiss();
            }
        });

        /*galaryopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent objectintent =new Intent();
                    objectintent.setType("image/*");

                    objectintent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(objectintent,PICK_IMAGE_REQUEST);
                }
                catch (Exception ei){}
            }
        });
*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            photo = (Bitmap) data.getExtras().get("data");
            pro_img.setImageBitmap(photo);
        }
//        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
//            try {
//                imagefilepath = data.getData();
//                Imagetostore = MediaStore.Images.Media.getBitmap(getContentResolver(),imagefilepath);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

    }

    public void banc(View view) {
        finish();
    }

    public void save_pro(View view) {
        String pro_name1= pro_name.getText().toString();
        String Retail_p1 = Retail_p.getText().toString();
        int p=Integer.parseInt(Retail_p1);
        String qty_p1 = Qty_pc.getText().toString();
        String qty_d = Qty_dz.getText().toString();
        String w_r_C = whole_r_cartoon.getText().toString();
        String w_r_dz = Whole_r_dz.getText().toString();
        String dic_dz = discount_dz.getText().toString();
        String sch = scheme.getText().toString();
        String n_a_d = net_amount_pc.getText().toString();
        String n_a_p = net_amount_pc.getText().toString();
        String ex_d1 = ex_discount1_dz.getText().toString();
        String ex_d2 = ex_discount2_dz.getText().toString();
        String ex_d3 = ex_discount3_dz.getText().toString();
        String city1 = city.getText().toString();
        String f_a_d = final_a_dz.getText().toString();
        String f_a_p = final_a_pc.getText().toString();
        String cname = Companyname.getText().toString();
        String copn = coupen.getSelectedItem().toString();
        mydb.storedata(new ModelAddProduct(photo,pro_name1,Integer.parseInt(Retail_p1),Integer.parseInt(qty_p1),Float.parseFloat(qty_d),Float.parseFloat(w_r_dz),Float.parseFloat(w_r_C),Float.parseFloat(dic_dz),Float.parseFloat(sch),copn,Float.parseFloat(n_a_p),Float.parseFloat(n_a_d),Integer.parseInt(ex_d1),Integer.parseInt(ex_d2),Integer.parseInt(ex_d3),city1,Float.parseFloat(f_a_p), Float.parseFloat(f_a_d),cname));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //dbHelper.getAllCompany();
        //Companymodel aa = new Companymodel();
        Cursor res = mydb.getCompanyforContext();
        menu.setHeaderTitle("Select Company Name");

        if(res != null && res.getCount()>0){
            while (res.moveToNext()){
                menu.add(0,v.getId(),0,res.getString(1));
            }
         menu.add(0,v.getId(),0,"{ +Add Company }");
        }
        else {
            Toast.makeText(this, "No data are there", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
       if(item.getTitle() == "{ +Add Company }"){
           AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(MainActivity.this);
           LayoutInflater inflater = getLayoutInflater();
           final View dialogview = inflater.inflate(R.layout.company_name_diloag, null, false);
           dialogbuilder.setView(dialogview);
           final EditText co_name = (EditText) dialogview.findViewById(R.id.editText_companyname);
           final Button save_btn = (Button) dialogview.findViewById(R.id.button_cv);

           final AlertDialog alertDialog = dialogbuilder.create();
           alertDialog.show();
           alertDialog.setCancelable(false);
           save_btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String cname = co_name.getText().toString();

                   mydb.companyinset(new Companymodel(cname));
                    co_name.setText("");
                    Companyname.setText(cname);
                    alertDialog.dismiss();
               }
           });
       }
       else{
        Companyname.setText(item.getTitle());}
        return true;
    }
}
