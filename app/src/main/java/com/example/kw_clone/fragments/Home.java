package com.example.kw_clone.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kw_clone.R;
import com.example.kw_clone.visit;

public class Home extends Fragment {

    private View view;
    private Button signUpLoginBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the button
        signUpLoginBtn = view.findViewById(R.id.home_signup_login_btn);

        // Set onClickListener
        signUpLoginBtn.setOnClickListener(v -> goToVisitActivity());

        return view;
    }

    private void goToVisitActivity() {
        // Use getActivity() as the context
        Intent intentToVisit = new Intent(getActivity(), visit.class);
        startActivity(intentToVisit);
    }
}
