package com.example.dbdemo.adapter;

import android.content.Context;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbdemo.R;
import com.example.dbdemo.model.Coontact;

import java.util.ArrayList;
import java.util.List;

import static com.example.dbdemo.R.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Coontact> contactList;

    public RecyclerViewAdapter(Context context, ArrayList<Coontact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }




    //where to get the single card as single viewHolder object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);


        return new ViewHolder(view);
    }

    //what will happen after we create ViewHolder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Coontact contact = contactList.get(position);

        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());

    }


    // how many items?

    @Override
    public int getItemCount() {
        return contactList.size();

    }


    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener
    {

        public TextView contactName;
        public TextView phoneNumber;
        public ImageView iconButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            contactName = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(id.phone_number);
           iconButton= itemView.findViewById(id.icon_button);

           iconButton.setOnClickListener(this);


        }

        public void onClick(View view)
        {
            Log.d("clickFromHolderTag","clicked");

        }
    }
}
