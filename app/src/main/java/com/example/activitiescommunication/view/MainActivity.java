package com.example.activitiescommunication.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
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

    public static List<CustomMessage> sentMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    public static final int REQUEST_CODE = 777;
    @OnClick(R.id.sendMessage_button)
    public void onClick(View view){
        String timeStamp = new SimpleDateFormat("HH:mm", Locale.US).format(new Date());
        CustomMessage customMessage = new CustomMessage(timeStamp, textMessage.getText().toString());
        sentMessages.add(customMessage);

        Intent intent = new Intent(this, ReplyActivity.class);

        intent.putExtra(READ_KEY, customMessage);
        startActivityForResult(intent, REQUEST_CODE);

        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for(CustomMessage sentMessage : sentMessages) {
            if (REQUEST_CODE == requestCode) {
                String message = data.getStringExtra(READ_KEY);
                viewMessage.setText(message);
            }
        }
    }
}