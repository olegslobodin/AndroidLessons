package ru.olegslobodin.contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewColor, textViewSize;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewColor = (TextView) findViewById(R.id.textViewColor);
        textViewSize = (TextView) findViewById(R.id.textViewSize);

        registerForContextMenu(textViewColor);
        registerForContextMenu(textViewSize);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        switch (view.getId()) {
            case R.id.textViewColor:
                menu.add(0, MENU_COLOR_RED, 0, "Red");
                menu.add(0, MENU_COLOR_GREEN, 0, "Green");
                menu.add(0, MENU_COLOR_BLUE, 0, "Blue");
                break;
            case R.id.textViewSize:
                menu.add(0, MENU_SIZE_22, 0, "22");
                menu.add(0, MENU_SIZE_26, 0, "26");
                menu.add(0, MENU_SIZE_30, 0, "30");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_COLOR_RED:
                textViewSize.setTextColor(Color.RED);
                textViewColor.setTextColor(Color.RED);
                break;
            case MENU_COLOR_GREEN:
                textViewSize.setTextColor(Color.GREEN);
                textViewColor.setTextColor(Color.GREEN);
                break;
            case MENU_COLOR_BLUE:
                textViewSize.setTextColor(Color.BLUE);
                textViewColor.setTextColor(Color.BLUE);
                break;
            case MENU_SIZE_22:
                textViewSize.setTextSize(22);
                textViewColor.setTextSize(22);
                break;
            case MENU_SIZE_26:
                textViewSize.setTextSize(26);
                textViewColor.setTextSize(26);
                break;
            case MENU_SIZE_30:
                textViewSize.setTextSize(30);
                textViewColor.setTextSize(30);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
