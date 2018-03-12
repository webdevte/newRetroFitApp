package com.example.ayabeltran.firstproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ayabeltran on 01/02/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<ImgRepo> places = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(ArrayList<ImgRepo> places, Context context) {
        this.places = places;
        this.context = context;
    }

    public ArrayList<ImgRepo> getPlaces() {
        return this.places;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.activity_list_display, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
//        gInflater = LayoutInflater.from(context);
//        View view1 = gInflater.inflate(R.layout.gridview,parent,false);
//        MyViewHolder holder1 = new MyViewHolder(view1);
        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

         TextView name;
         TextView des;
         ImageView photo;
         ImgRepo selectedPlace;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.photo = itemView.findViewById(R.id.displayimage);
            this.name = itemView.findViewById(R.id.textname);
            this.des = itemView.findViewById(R.id.textdetails);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent preview = new Intent(context, ListDisplay.class);
                    preview.putExtra("Key", selectedPlace.getImgname());
                    preview.putExtra("Key2", selectedPlace.getDesc());
                    preview.putExtra("Key3", selectedPlace.getImgstring());
                    context.startActivity(preview);
                }
            });
        }
    }
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        getting the original photo from the list
        String originalPhoto = places.get(position).getImgstring();

//        converting the photo bytes to usable image
//        Bitmap decodedPhoto = BitmapFactory.decodeByteArray(originalPhoto, 0, originalPhoto.length);
        byte[] decodedString = Base64.decode(originalPhoto.getBytes(), Base64.DEFAULT);
     Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.photo.setImageBitmap(decodedByte);
        holder.name.setText(places.get(position).getImgname());
        holder.des.setText(places.get(position).getDesc());
        holder.selectedPlace = places.get(position);
        Glide.with(context).load(places.get(position).getImgstring()).into(holder.photo);
    }

    public int getItemCount() {

        return places.size();
    }

    public void clearData() {
        places.clear();
        notifyDataSetChanged();
    }

    public void clear() {
        places.clear();
        notifyDataSetChanged();
    }

    public void add(ImgRepo s) {
        places.add(s);
        notifyDataSetChanged();
    }

}



