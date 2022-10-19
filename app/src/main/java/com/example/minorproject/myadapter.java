package com.example.minorproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model model) {
      holder.nametext.setText(model.getName());
      holder.coursetext.setText(model.getEnrollment());
      holder.emailtext.setText(model.getBranch());
      holder.email1text.setText(model.getSemester());
      Glide.with(holder.img1.getContext()).load(model.getPurl()).into(holder.img1);



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView nametext,coursetext,emailtext,email1text;

        public myviewholder(@NonNull View itemView) {
            super(itemView);


            nametext=itemView.findViewById(R.id.nametext);
            coursetext=itemView.findViewById(R.id.coursetext);
            emailtext=itemView.findViewById(R.id.emailtext);
            email1text=itemView.findViewById(R.id.email1text);
            img1=itemView.findViewById(R.id.img1);
        }
    }

}
