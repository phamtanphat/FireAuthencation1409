package com.ptp.phamtanphat.fireauthencation1409;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText edtuser,edtpassword;
    Button btndangky,btndangnhap;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        mAuth = FirebaseAuth.getInstance();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(getmail(),getpassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                            Log.d("BBB",task.getException().toString());
                        }
                    }
                });
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(getmail(),getpassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Khong co tai khoan nay", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //Get email
    public String getmail(){
        return edtuser.getText().toString();
    }
    public String getpassword(){
        return edtpassword.getText().toString();
    }

    private void Anhxa() {
        edtuser = findViewById(R.id.edittextusername);
        edtpassword = findViewById(R.id.edittextpassword);
        btndangky = findViewById(R.id.buttondangky);
        btndangnhap = findViewById(R.id.buttondangnhap);
    }
}
