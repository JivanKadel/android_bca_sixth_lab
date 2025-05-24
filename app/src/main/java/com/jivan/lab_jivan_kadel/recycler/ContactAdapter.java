package com.jivan.lab_jivan_kadel.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jivan.lab_jivan_kadel.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> contacts;

    private ContactClickListener contactClickListener;

    interface ContactClickListener {
        void onContactClick(int position);
    }

    public ContactAdapter(List<Contact> contacts, ContactClickListener contactCLickListener) {
        this.contacts = contacts;
        this.contactClickListener = contactCLickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String contactName = contacts.get(position).getName();
        String contactNumber = contacts.get(position).getContact();

        holder.getTvContactName().setText(contactName);
        holder.getTvContactNumber().setText(contactNumber);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvContactName;
        private final TextView tvContactNumber;

        public TextView getTvContactName() {
            return tvContactName;
        }

        public TextView getTvContactNumber() {
            return tvContactNumber;
        }

        public ViewHolder(@NonNull View itemView, ContactClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onContactClick(getAdapterPosition());
                }
            });
            tvContactName = itemView.findViewById(R.id.tvContactName);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
        }


    }
}
