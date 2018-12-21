package com.derrick.park.assignment3_contacts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddContact extends DialogFragment implements View.OnClickListener {


    AddContactListener cListener;

    EditText nameText;
    EditText phoneText;

    boolean nameCorrect;
    boolean phoneCorrect;



    public AddContact() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);

        Button cancel = v.findViewById(R.id.cancel);
        final Button add = v.findViewById(R.id.add);

        add.setEnabled(false);

        nameText = v.findViewById(R.id.name_text);
        phoneText = v.findViewById(R.id.phone_text);

        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                nameCorrect = s.toString().matches("[a-zA-Z]+\\s[a-zA-Z]+");

                if(nameCorrect && phoneCorrect){
                    add.setEnabled(true);
                } else{
                    add.setEnabled(false);
                }
            }
        });

        phoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneCorrect = s.toString().matches("\\d{9,}");
                if(nameCorrect && phoneCorrect){
                    add.setEnabled(true);
                } else{
                    add.setEnabled(false);
                }
            }
        });

        cancel.setOnClickListener(this);
        add.setOnClickListener(this);

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cListener = (AddContactListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                String[] names = nameText.getText().toString().split(" ");
                cListener.AddContactResponse(names[0], names[1], phoneText.getText().toString());
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
        }

    }

    public interface AddContactListener{
        public void AddContactResponse(String firstName, String lastName, String phone);

    }
}
