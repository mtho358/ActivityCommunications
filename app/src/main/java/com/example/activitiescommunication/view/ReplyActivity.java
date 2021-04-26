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
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.activitiescommunication.view.MainActivity.REQUEST_CODE;

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

        if (message != null) {
            viewMessage.setText(message.getMessage() + " " + message.getTimeStamp());
        }

    }

    public static String READ_KEY = "READ";

    @OnClick(R.id.sendMessage_button)
    public void onClick(View view) {
        String timeStamp = new SimpleDateFormat("HH:mm", Locale.US).format(new Date());
        CustomMessage customMessage = new CustomMessage(timeStamp, textMessage.getText().toString());

        Intent intent = new Intent();
        intent.putExtra(READ_KEY, customMessage);
        setResult(REQUEST_CODE, intent);
        finish();
    }
}