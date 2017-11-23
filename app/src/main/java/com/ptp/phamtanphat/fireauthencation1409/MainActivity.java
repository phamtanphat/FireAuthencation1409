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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    EditText edtuser, edtpassword, edtemail;
    Button btndangky, btndangnhap, btngetuser, btncapnhat, btnxacthuc, btnresetpassword;
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
                mAuth.createUserWithEmailAndPassword(getmail(), getpassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                                    Log.d("BBB", task.getException().toString());
                                }
                            }
                        });
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(getmail(), getpassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Khong co tai khoan nay", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btngetuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, "Ten " + user.getDisplayName()
                            + "\nEmail " + user.getEmail()
                            + "\nPhoneNumber" + user.getPhoneNumber(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Khong co nguoi dung", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName("Pham Tan Phat")
                            .build();
                    user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        btnxacthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Da gui email xac thuc", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Khong gui duoc email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Khong co nguoi dung xac thuc", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnresetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();

                auth.sendPasswordResetEmail(user.getEmail())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Da gui mail reset password", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    //Get email
    public String getmail() {
        return edtuser.getText().toString();
    }

    public String getpassword() {
        return edtpassword.getText().toString();
    }

    private void Anhxa() {
        edtuser = findViewById(R.id.edittextusername);
        edtpassword = findViewById(R.id.edittextpassword);
        btndangky = findViewById(R.id.buttondangky);
        btndangnhap = findViewById(R.id.buttondangnhap);
        btngetuser = findViewById(R.id.buttongetuser);
        btncapnhat = findViewById(R.id.buttcapnhat);
        btnxacthuc = findViewById(R.id.buttonxacthuc);
        edtemail = findViewById(R.id.edittextemail);
        btnresetpassword = findViewById(R.id.buttonresetpassword);
    }
}
