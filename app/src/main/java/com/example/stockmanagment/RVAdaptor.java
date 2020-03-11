package com.example.stockmanagment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RVAdaptor extends RecyclerView.Adapter<RVAdaptor.Holder> {

    private Context context;
    private ArrayList<ModelAddProduct> arrayList;

    public RVAdaptor(Context context, ArrayList<ModelAddProduct> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ModelAddProduct modelAddProduct = arrayList.get(position);

        int id = modelAddProduct.getPro_id();
        Bitmap image= modelAddProduct.getPro_images();
        String name = modelAddProduct.getPro_name();
        int retail_p = modelAddProduct.getRetail_price();
        int qty = modelAddProduct.getQuantity_packet();
        Float net  = modelAddProduct.getNetAmount_p();

        holder.show_img.setImageBitmap(image);
        holder.show_pro_name.setText(name);
        holder.qty.setText(String.valueOf(qty));
        holder.retail.setText(String.valueOf(retail_p));
        holder.net.setText(String.valueOf(net));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView show_img ;
        TextView show_pro_name,retail,qty,net;

        public Holder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelAddProduct aa = arrayList.get(getAdapterPosition());
                    Intent ii = new Intent(v.getContext(),Showdetail.class);
                    Bitmap iipo = aa.getPro_images();
                    ii.putExtra("pimg", iipo);
                    ii.putExtra("pname", String.valueOf(aa.getPro_name()));
                    ii.putExtra("rprice", String.valueOf(aa.getRetail_price()));
                    ii.putExtra("hprice",String.valueOf(aa.getWhole_price_drzan()));
                    ii.putExtra("qty",String.valueOf(aa.getQuantity_packet()));
                    ii.putExtra("scheme",String.valueOf(aa.getScheme()));
                    ii.putExtra("copen",String.valueOf(aa.getCoupen()));
                    ii.putExtra("cty",String.valueOf(aa.getCity()));
                    ii.putExtra("dicc",String.valueOf(aa.getDiscount_pice()));
                    ii.putExtra("E.d1",String.valueOf(aa.getExtra_discount1()));
                    ii.putExtra("E.d2",String.valueOf(aa.getExtra_discount2()));
                    ii.putExtra("E.d3",String.valueOf(aa.getExtra_discount3()));
                    ii.putExtra("N_A", String.valueOf(aa.getNetAmount_p()));
                    v.getContext().startActivity(ii);
                }
            });

            show_img = itemView.findViewById(R.id.imageView_show_pro);
            show_pro_name = itemView.findViewById(R.id.pro_name_show);
            retail = itemView.findViewById(R.id.retail_price_show);
            qty = itemView.findViewById(R.id.qty_show);
            net = itemView.findViewById(R.id.netAmountshow);

        }

    }


}
