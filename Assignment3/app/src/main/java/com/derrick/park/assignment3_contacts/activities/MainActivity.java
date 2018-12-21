package com.derrick.park.assignment3_contacts.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.derrick.park.assignment3_contacts.AddContact;
import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AddContact.AddContactListener {

    RecyclerView mRecyclerView;

    private ArrayList<Contact> mContactList;
    ContactsAdapter adapter;


    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<ContactList> call = ContactClient.getContacts(25);


        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                     mContactList = response.body().getContactList();
                     for(Contact contact: mContactList) {
                         Log.d(TAG, "onResponse: " + mContactList.size());
                         Log.d(TAG, "onResponse: " + contact);
                     }
                     adapter = new ContactsAdapter(mContactList, getApplicationContext(), mRecyclerView);

                     mRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                // Error Handling
                Log.d(TAG, "onFailure: WHATTTTT");
            }
        });

        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void AddContactResponse(String firstName, String lastName, String phone) {
        adapter.contactsList.addAll(new Contact(firstName, lastName, phone));
    }

    public void onAddClicked(View view) {
        DialogFragment cont = new AddContact();
        cont.show(getSupportFragmentManager(), "contact");
    }
}

