package com.example.autoregistros.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.autoregistros.ListEmotions;
import com.example.autoregistros.R;
import com.example.autoregistros.entidades.Emotion;
import com.example.autoregistros.entidades.User;

import java.util.ArrayList;
import java.util.List;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.ViewHolder> implements View.OnClickListener{

    public List<Emotion> emotionList;
    public int id_user;

    private View.OnClickListener listener;

    public EmotionAdapter(List<Emotion> emotionList){
        this.emotionList = emotionList;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);

        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(params);

        view.setOnClickListener(this);

        return new EmotionAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTitulo.setText(emotionList.get(position).getEmotion_type());
        holder.txtDescripcion.setText(emotionList.get(position).getEmotion_reason());

        id_user = emotionList.get(position).getId_emocion();

    }

    @Override
    public int getItemCount() {
        return emotionList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

        if(listener!= null){
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitulo;
        public TextView txtDescripcion;

        public ViewHolder(View itemView){
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.tituloEmotion);
            txtDescripcion = itemView.findViewById(R.id.descriptionEmotion);
        }

    }
}
