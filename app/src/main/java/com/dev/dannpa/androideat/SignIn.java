package com.dev.dannpa.androideat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.dannpa.androideat.Common.Common;
import com.dev.dannpa.androideat.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = findViewById(R.id.editPassword);
        edtPhone = findViewById(R.id.editPhone);
        btnSignIn = findViewById(R.id.btnSignIn);

        // Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            // Get User Information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString()); //set Phone
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Intent homeIntent = new Intent(SignIn.this, Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Sign in failed !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignIn.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
