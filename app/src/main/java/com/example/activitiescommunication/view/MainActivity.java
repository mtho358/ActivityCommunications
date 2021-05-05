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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.activitiescommunication.view.ReplyActivity.READ_KEY;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sendMessage_button)
    AppCompatButton sendMessage;

    @BindView(R.id.message_textview)
    TextView viewMessage;

    @BindView(R.id.message_edittext)
    EditText textMessage;

    public static List<CustomMessage> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        CustomMessage message = (CustomMessage) getIntent().getSerializableExtra(READ_KEY);

        for(int i = 0; i < messages.size(); i++) {
            StringBuilder messagesString = new StringBuilder();
            if (messages.get(i) != null) {
                messagesString.append(messages.get(i).toString() + "\n");

                Log.d("TAG_M", messages.get(i).getSenderName() + ": " + messages.get(i).getMessage());
                viewMessage.setText(messagesString.toString() + "\n");
            }
        }
    }


    public static final int REQUEST_CODE = 777;
    @OnClick(R.id.sendMessage_button)
    public void onClick(View view){
        String timeStamp = new SimpleDateFormat("HH:mm", Locale.US).format(new Date());
        CustomMessage customMessage = new CustomMessage(timeStamp, "Serie", textMessage.getText().toString());

        messages.add(customMessage);

        Intent intent = new Intent(this, ReplyActivity.class);

        intent.putExtra(READ_KEY, customMessage);
        startActivityForResult(intent, REQUEST_CODE);

        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
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