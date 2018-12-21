package com.derrick.park.assignment3_contacts.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;


import java.util.ArrayList;



public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactItemHolder> {

    private static final String TAG = "Contacts";
    SortedList<Contact> contactsList;
    LayoutInflater inflater;

    RecyclerView rv;

    public ContactsAdapter(final ArrayList<Contact> contacts, Context context, RecyclerView reView){
        rv = reView;

        inflater = LayoutInflater.from(context);
        contactsList = new SortedList<>(Contact.class, new SortedListAdapterCallback<Contact>(this) {
            @Override
            public int compare(Contact contact, Contact t21) {
                return contact.getName().getFirst().toLowerCase().compareTo(t21.getName().getFirst().toLowerCase());
            }

            @Override
            public boolean areContentsTheSame(Contact contact, Contact t21) {
                return contact.getName().getFirst().equals(t21.getName().getFirst()) &&
                        contact.getName().getLast().equals(t21.getName().getLast());
            }

            @Override
            public boolean areItemsTheSame(Contact contact, Contact t21) {
                return contact == t21;
            }

            @Override
            public void onInserted(int position, int count) {
                super.onInserted(position, count);

                if(count < 2){
                    CompareAboveAndBelow(position);
                }

                Log.d("Contacts", "onInserted: " + contactsList.get(position).getName().getFirst());
            }
        });

        contactsList.addAll(contacts);

        for (int i = 0; i<contactsList.size(); i++){
            if(i > 0){
                char thisFirst = contactsList.get(i).getName().getFirst().charAt(0);
                char previouschar = contactsList.get(i-1).getName().getFirst().charAt(0);
                if(thisFirst == previouschar){
                    contactsList.get(i).hasHeader = false;
                } else {
                    contactsList.get(i).hasHeader = true;
                }
            } else {
                contactsList.get(i).hasHeader = true;
            }
        }

    }

    @NonNull
    @Override
    public ContactItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.contact_layout, viewGroup, false);

        return new ContactItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactItemHolder contactItemHolder, int i) {
        contactItemHolder.mContactName.setText(contactsList.get(i).getName().toString());
        contactItemHolder.mContactNumber.setText(contactsList.get(i).getCell());
        contactItemHolder.mHeaderText.setText(contactsList.get(i).getName().getFirst().substring(0,1).toUpperCase());

        if( contactsList.get(i).hasHeader){
            contactItemHolder.mHeaderLayout.setVisibility(View.VISIBLE);
        } else {
            contactItemHolder.mHeaderLayout.setVisibility(View.GONE);
        }

    }
    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public void CompareAboveAndBelow(int i){
        if(i>0){
            Contact current = contactsList.get(i);
            Contact previous = contactsList.get(i-1);
            Contact next = null;

            if(i+1 < contactsList.size()){
                next = contactsList.get(i+1);
            }

            if(current.getName().getFirst().charAt(0) != previous.getName().getFirst().charAt(0)){
                current.hasHeader = true;
                if(null != next &&  next.getName().getFirst().toLowerCase().charAt(0) == current.getName().getFirst().toLowerCase().charAt(0)){
                    next.hasHeader = false;
                }
            }
        } else {
            Contact current = contactsList.get(i);
            current.hasHeader = true;

            if(contactsList.size()>1){
                Contact next = contactsList.get(i+1);
                next.hasHeader = false;
            }
        }

        notifyDataSetChanged();
    }

    public class ContactItemHolder extends RecyclerView.ViewHolder {
        TextView mContactName;
        TextView mContactNumber;
        View mHeaderLayout;
        TextView mHeaderText;

        public ContactItemHolder(@NonNull View itemView) {
            super(itemView);
            mContactName = itemView.findViewById(R.id.contactName);
            mContactNumber = itemView.findViewById(R.id.contactNumber);
            mHeaderLayout = itemView.findViewById(R.id.header);
            mHeaderText = itemView.findViewById(R.id.header_text);
        }
    }

}
