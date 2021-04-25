package com.example.activitiescommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sendMessage_button)
    AppCompatButton sendMessage;

    @BindView(R.id.message_textview)
        TextView viewMessage;

    @BindView(R.id.text_message_edittext)
    EditText textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sendMessage.setOnClickListener(view -> {
            Toast.makeText(this,"Message sent.", Toast.LENGTH_SHORT).show();
        });
    }
}