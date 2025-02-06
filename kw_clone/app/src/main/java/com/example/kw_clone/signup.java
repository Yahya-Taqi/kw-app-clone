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

public class signup extends AppCompatActivity
{

    TextView loginBtn;
    Button next;
    ImageView loginWithG, loginWithIp, loginWithFb;
    Intent intent_to_login;

    EditText firstNameField, lastNameField, emailField, passField;

    // Access the database inside this activity
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);


        // Initialize the database
        dbHelper = new DatabaseHelper(this);
        //dbHelper.ResetDatabase();// For Testing Purpose only

        //        Initialize the views
        loginBtn = findViewById(R.id.singup_login_btn);
        next = findViewById(R.id.singup_next_btn);
        loginWithG = findViewById(R.id.signup_withG_btn);
        loginWithIp = findViewById(R.id.signup_withIp_btn);
        loginWithFb = findViewById(R.id.signup_withFb_btn);

        firstNameField = findViewById(R.id.ET_firstname);
        lastNameField = findViewById(R.id.ET_lastname);
        emailField = findViewById(R.id.ET_email);
        passField = findViewById(R.id.ET_pass);

        intent_to_login = new Intent(signup.this, login.class);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//        Attach functionalities to the views
        loginBtn.setOnClickListener(this::HandleLoginBtn);
        next.setOnClickListener(this::HandleNextBtn);
        loginWithG.setOnClickListener(this::HandleLoginWithGBtn);
        loginWithIp.setOnClickListener(this::HandleLoginWithIpBtn);
        loginWithFb.setOnClickListener(this::HandleLoginWithFbBtn);
    }

    private void HandleLoginBtn(View v)
    {
        startActivity(intent_to_login);
    }

    private void HandleNextBtn(View v)
    {
        // Check for empty fields
        if (IsFieldEmpty(firstNameField) || IsFieldEmpty(lastNameField) || IsFieldEmpty(emailField)) {
            Toast.makeText(signup.this, "Please Fill In All Fields", Toast.LENGTH_SHORT).show();

            if (IsFieldEmpty(firstNameField)) {
                firstNameField.setError("First Name is required!");
            }
            if (IsFieldEmpty(lastNameField)) {
                lastNameField.setError("Last Name is required!");
            }
            if (IsFieldEmpty(emailField)) {
                emailField.setError("Email is required!");
            }
            if (IsFieldEmpty(passField)) {
                passField.setError("Email is required!");
            }
            return;
        }

        // Checking if the user email is already registered
        SQLiteDatabase dbReadable = dbHelper.getReadableDatabase();
        String email = emailField.getText().toString().trim();

        Cursor cursor = dbReadable.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});

        if (cursor.moveToFirst())
        {
            Toast.makeText(signup.this, "Email Already Registered", Toast.LENGTH_SHORT).show();
            cursor.close();
            dbReadable.close();
            return;
        }
        cursor.close();
        dbReadable.close();

        // Insert new user into the database
        boolean result = dbHelper.InsertUser(
                firstNameField.getText().toString().trim(),
                lastNameField.getText().toString().trim(),
                email,
                passField.getText().toString().trim()
        );

        // Error handling if insertion failed
        if (!result)
        {
            Toast.makeText(signup.this, "Error: Database Insertion Failed!", Toast.LENGTH_SHORT).show();
            return;
        }

        // On Success
        Intent redirectToPass = new Intent(signup.this, PassConsent.class);
        startActivity(redirectToPass);
    }


    private void HandleLoginWithGBtn(View v)
    {
        Toast.makeText(signup.this, "Server busy at the moment", Toast.LENGTH_SHORT).show();
    }

    private void HandleLoginWithIpBtn(View v)
    {
        Toast.makeText(signup.this, "Server busy at the moment", Toast.LENGTH_SHORT).show();
    }

    private void HandleLoginWithFbBtn(View v)
    {
        Toast.makeText(signup.this, "Server busy at the moment", Toast.LENGTH_SHORT).show();
    }

    private boolean IsFieldEmpty(EditText field)
    {
        return field.getText().toString().trim().isEmpty();
    }
}