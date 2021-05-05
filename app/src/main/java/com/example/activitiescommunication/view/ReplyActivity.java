package com.example.activitiescommunication.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitiescommunication.R;
import com.example.activitiescommunication.model.CustomMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.activitiescommunication.view.MainActivity.REQUEST_CODE;
import static com.example.activitiescommunication.view.MainActivity.messages;

public class ReplyActivity extends AppCompatActivity {

    @BindView(R.id.sendMessage_button)
    AppCompatButton sendMessage;

    @BindView(R.id.message_textview)
    TextView viewMessage;

    @BindView(R.id.message_edittext)
    EditText textMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        ButterKnife.bind(this);

        CustomMessage message = (CustomMessage) getIntent().getSerializableExtra(READ_KEY);
        StringBuilder messagesString = new StringBuilder();

        for(int i = 0; i < messages.size(); i++) {
            if (messages.get(i) != null) {
                messagesString.append(messages.get(i).toString()).append("\n");
                viewMessage.setText(messagesString.toString());
            }
        }

    }

    public static String READ_KEY = "READ";

    @OnClick(R.id.sendMessage_button)
    public void onClick(View view) {
        String timeStamp = new SimpleDateFormat("HH:mm", Locale.US).format(new Date());
        CustomMessage customMessage = new CustomMessage(timeStamp, "Alexa", textMessage.getText().toString());

        messages.add(customMessage);

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(READ_KEY, customMessage);
        setResult(REQUEST_CODE, intent);

        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, REQUEST_CODE);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (REQUEST_CODE == requestCode) {
                String message = data.getStringExtra(READ_KEY);
                viewMessage.setText(message);
            }
    }
}