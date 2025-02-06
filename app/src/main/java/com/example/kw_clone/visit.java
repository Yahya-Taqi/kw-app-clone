package com.example.kw_clone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class visit extends AppCompatActivity {

    // Declare the views and intents
    Button signupBtn, loginBtn;
    TextView skipBtn;
    Intent intent_to_login_scrn, intent_to_sign_scrn, intent_to_main_scrn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_visit);

        // Initialize views
        signupBtn = findViewById(R.id.visit_sign_up_button);
        loginBtn = findViewById(R.id.visit_login_button);
        skipBtn = findViewById(R.id.visit_skip_login);

        // Initialize intents
        intent_to_login_scrn = new Intent(visit.this, login.class);
        intent_to_sign_scrn = new Intent(visit.this, signup.class);
        intent_to_main_scrn = new Intent(visit.this, MainActivity.class);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Attach functionalities to views
        signupBtn.setOnClickListener(this::HandleSignupClick);
        loginBtn.setOnClickListener(this::HandleLoginClick);
        skipBtn.setOnClickListener(this::HandleSkipBtn);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Functions
    private void HandleSignupClick(View v)
    {
        startActivity(intent_to_sign_scrn);
    }

    private void HandleLoginClick(View v)
    {
        startActivity(intent_to_login_scrn);
    }

    private void HandleSkipBtn(View v)
    {
        startActivity(intent_to_main_scrn);
        //Toast.makeText(visit.this, "Main Screen Under-development!", Toast.LENGTH_LONG).show();
    }

}