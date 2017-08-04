package com.cds.recyclerviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by fazal on 8/3/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<Data> dataList;

    public ListAdapter(List<Data> dataList){
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutID = R.layout.item_list;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutID,null,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(dataList.size() == 0) return;

        try {
            holder.product_name_tv.setText(dataList.get(position).getProduct_name());
            holder.description_tv.setText(dataList.get(position).getProduct_description());

            Bitmap bmp = dataList.get(position).getImage();
            holder.image.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView product_name_tv;
        TextView description_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.icon_doctor);
            product_name_tv = (TextView) itemView.findViewById(R.id.product_name_tv);
            description_tv = (TextView) itemView.findViewById(R.id.product_description_tv);

        }
    }
}
