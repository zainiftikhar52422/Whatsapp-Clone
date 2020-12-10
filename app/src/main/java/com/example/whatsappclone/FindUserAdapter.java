package com.example.whatsappclone;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FindUserAdapter extends RecyclerView.Adapter<FindUserAdapter.ViewHolder> {
    private ArrayList<UserObject> users;
    private Context context;

    public FindUserAdapter(Context context) {
        this.context = context;
    }

    public void setUsers(ArrayList<UserObject> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtFindUserName.setText(users.get(position).getName());
        holder.txtFindUserPhone.setText(users.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    // ViewHolder Class
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtFindUserName,txtFindUserPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFindUserName=itemView.findViewById(R.id.txtFindUserName);
            txtFindUserPhone=itemView.findViewById(R.id.txtFindUserPhoneNumber);
        }
    }
}
