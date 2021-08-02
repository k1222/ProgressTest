package com.example.progresstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.progresstest.view.ProgressBarTest;
import com.example.progresstest.view.SliderProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBarTest progressBar = findViewById(R.id.progress_bar);
        SliderProgressBar progressBar2 = findViewById(R.id.slide_progress_bar);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> new Thread(() -> {
            for (int i = 0;i<=100;i++) {
                try {
                    Thread.sleep(50);
                    progressBar.setProgress(i);
                    progressBar2.setProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start());

    }
}