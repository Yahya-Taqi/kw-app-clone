package com.example.kw_clone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PassConsent extends AppCompatActivity {

    Button createAccBtn;
    CheckBox terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pass_consent);


        // Initializing views
        createAccBtn = findViewById(R.id.pass_createAccBtn);
        terms = findViewById(R.id.checkbox_terms);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createAccBtn.setOnClickListener(this::HandleCreateAccBtn);
    }

    private void HandleCreateAccBtn(View v)
    {
        if (!terms.isChecked())
        {
            Toast.makeText(PassConsent.this, "Please Accept the TOS.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intentToMainScrn = new Intent(PassConsent.this, MainActivity.class);
        startActivity(intentToMainScrn);
    }
}