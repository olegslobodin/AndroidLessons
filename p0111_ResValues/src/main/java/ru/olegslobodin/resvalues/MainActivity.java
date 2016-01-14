package ru.olegslobodin.resvalues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((LinearLayout)findViewById(R.id.llBottom)).setBackgroundResource(R.color.llBottomColor);
        ((TextView)findViewById(R.id.tvBottom)).setText(R.string.tvBottomText);
        ((Button)findViewById(R.id.btnBottom)).setText(R.string.btnBottomText);
    }
}
