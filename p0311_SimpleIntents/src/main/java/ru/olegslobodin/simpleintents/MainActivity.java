package ru.olegslobodin.simpleintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button btnCall = (Button) findViewById(R.id.btnCall);

        btnWeb.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnWeb:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
                break;
            case R.id.btnMap:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:53.158,26.007"));
                break;
            case R.id.btnCall:
                intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8045884"));
        }
        startActivity(intent);
    }
}
