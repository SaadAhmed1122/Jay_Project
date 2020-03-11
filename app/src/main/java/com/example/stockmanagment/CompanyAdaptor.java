package com.example.stockmanagment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CompanyAdaptor extends RecyclerView.Adapter<CompanyAdaptor.Holder> {
    private Context context;
    private ArrayList<Companymodel> arrayList;

    public CompanyAdaptor(Context context, ArrayList<Companymodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CompanyAdaptor.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.company_layout,parent,false);
        return new CompanyAdaptor.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdaptor.Holder holder, int position) {
        Companymodel cm = arrayList.get(position);
    String c_name= cm.getComapny_name();
    holder.tv_cm.setText(c_name);

       }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    String getItem(int id) {
        return arrayList.get(id).getComapny_name();
    }


    public class Holder extends RecyclerView.ViewHolder{
        TextView tv_cm;
        public Holder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Companymodel cm = arrayList.get(getAdapterPosition());
                    Intent ii = new Intent(v.getContext(),Show_product.class);
                    ii.putExtra("C_nme", String.valueOf(cm.getComapny_name()));
                    v.getContext().startActivity(ii);
                }
            });
            tv_cm = (TextView) itemView.findViewById(R.id.cm);
            }

    }
}
