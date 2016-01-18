package ru.olegslobodin.intentfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityDate extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        textView=(TextView)findViewById(R.id.textView);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        textView.setText(sdf.format(new Date(System.currentTimeMillis())));
    }
}
