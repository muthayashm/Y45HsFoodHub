package com.y45h.y45hsfoodhub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.y45h.y45hsfoodhub.Common.Common;
import com.y45h.y45hsfoodhub.Model.User;

public class SignIn extends AppCompatActivity {
EditText edtPhone,edtPassword;
Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //Init of Objects
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword =findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btn_SignIn1);
        //init of firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Connecting to Database, Please Wait...");
                progressDialog.show();

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Checking the existance of user in the database
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            //Getting User Information
                            progressDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                //Toast.makeText(SignIn.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                Intent intentHome = new Intent(SignIn.this,Home.class);
                                Common.currentUser = user;
                                startActivity(intentHome);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(SignIn.this, "User Doesn't Exists.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
