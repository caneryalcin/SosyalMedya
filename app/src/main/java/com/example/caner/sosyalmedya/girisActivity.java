package com.example.caner.sosyalmedya;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class girisActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        mAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        //Assign current user to user object that created with FirebaseUser class
        FirebaseUser user = mAuth.getCurrentUser();

        //If user signed in once before, go directly to mainActivity page.
        if(user !=null){

            Intent intent = new Intent(getApplicationContext(), mainActivity.class);
            startActivity(intent);

        }

    }

    public void signIn (View view){

            //Making signIn with exist user
            mAuth.signInWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                    .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Intent intent = new Intent(getApplicationContext(), mainActivity.class);
                                startActivity(intent);

                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(girisActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });

    }

    public void signUp(View view){

        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //kaydolma işlemi başarılı olursa ne olacağını söyler.
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //İf mission is successful , it will go to mainActivity page
                        if(task.isSuccessful()) {

                            Toast.makeText(girisActivity.this, "Kullanıcı Oluşturuldu", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), mainActivity.class);
                            startActivity(intent);

                        }




                    }
                }).addOnFailureListener(this, new OnFailureListener() {//kaydolma işlemi başarısız olursa ne olacağını söyler.
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(girisActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}


