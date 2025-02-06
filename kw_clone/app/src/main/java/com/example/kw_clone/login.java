package com.example.kw_clone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity
{

    TextView signupBtn, forgotPassBtn;
    Button loginBtn;
    ImageView loginWithG, loginWithIp, loginWithFb;
    Intent intent_to_signup, intent_to_main;

    EditText emailField, passwordField;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


//        Initialize the views
        signupBtn = findViewById(R.id.login_signup_btn);
        forgotPassBtn = findViewById(R.id.login_forgotpass_btn);
        loginBtn = findViewById(R.id.login_btn);
        loginWithG = findViewById(R.id.login_withG_btn);
        loginWithIp = findViewById(R.id.login_withip_btn);
        loginWithFb = findViewById(R.id.login_withfb_btn);

        emailField = findViewById(R.id.login_email);
        passwordField = findViewById(R.id.login_passwd);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

//        Initializing the intent
        intent_to_signup = new Intent(login.this, signup.class);
        intent_to_main = new Intent(login.this, MainActivity.class);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Attach functionalities to the views
        signupBtn.setOnClickListener(this::HandleSignUpBtn);
        forgotPassBtn.setOnClickListener(this::HandleForgotPassBtn);
        loginBtn.setOnClickListener(this::HandleLoginBtn);
        loginWithG.setOnClickListener(this::HandleLoginWithGBtn);
        loginWithIp.setOnClickListener(this::HandleLoginWithIpBtn);
        loginWithFb.setOnClickListener(this::HandleLoginWithFbBtn);
    }
    private void HandleSignUpBtn(View v)
    {
        startActivity(intent_to_signup);
    }

    private void HandleForgotPassBtn(View v)
    {
        Toast.makeText(login.this, "Further Features Under-development", Toast.LENGTH_LONG).show();
    }

    private void HandleLoginBtn(View v)
    {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(login.this, "Please enter both email and password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (validateUser(email, password))
        {
            Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
            startActivity(intent_to_main);
        } else
        {
            Toast.makeText(login.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND passwd = ?", new String[]{email, password});

        boolean exists = cursor.moveToFirst(); // If data exists, login is valid

        cursor.close();
        db.close();
        return exists;
    }

    private void HandleLoginWithGBtn(View v)
    {
        Toast.makeText(login.this, "Server busy at the moment", Toast.LENGTH_SHORT).show();
    }

    private void HandleLoginWithIpBtn(View v)
    {
        Toast.makeText(login.this, "Server busy at the moment", Toast.LENGTH_SHORT).show();
    }

    private void HandleLoginWithFbBtn(View v)
    {
        Toast.makeText(login.this, "Server busy at the moment", Toast.LENGTH_SHORT).show();
    }

}