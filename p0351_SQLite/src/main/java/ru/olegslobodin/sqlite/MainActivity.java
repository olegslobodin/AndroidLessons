package ru.olegslobodin.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etEmail;
    Button btnAdd, btnDeleteAll, btnSave, btnCancel;
    LinearLayout listLayout;
    NameCard selectedNameCard;
    final int MENU_EDIT = 1, MENU_DELETE = 2;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDeleteAll = (Button) findViewById(R.id.btnWipe);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        listLayout = (LinearLayout) findViewById(R.id.listLayout);

        btnAdd.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);

        //Загружаем всю базу в список
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        refreshList(db);
        dbHelper.close();
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        cv.put("name", name);
        cv.put("email", email);

        switch (v.getId()) {
            case R.id.btnAdd:
                long rowID = db.insert("mytable", null, cv);
                Toast.makeText(this, "Added record #" + rowID, Toast.LENGTH_SHORT).show();
                addToList(rowID, name, email);
                break;
            case R.id.btnSave:
                String id = String.valueOf(selectedNameCard.getContactId());
                db.update("mytable", cv, "id = ?", new String[]{id});
                Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();
                animLayoutClose();
                selectedNameCard.setName(name);
                selectedNameCard.setEmail(email);
                break;
            case R.id.btnCancel:
                animLayoutClose();
                break;
            case R.id.btnWipe:
                int clearCount = db.delete("mytable", null, null);
                Toast.makeText(this, clearCount + " records removed", Toast.LENGTH_SHORT).show();
                listLayout.removeAllViews();
                break;
        }
        etName.setText("");
        etEmail.setText("");
        // закрываем подключение к БД
        dbHelper.close();
    }

    void refreshList(SQLiteDatabase db) {
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int emailColIndex = c.getColumnIndex("email");
            listLayout.removeAllViews();
            do {
                addToList(c.getLong(idColIndex), c.getString(nameColIndex), c.getString(emailColIndex));
            } while (c.moveToNext());
        } else {
            Toast.makeText(this, "No contacts finded", Toast.LENGTH_SHORT).show();
        }
        c.close();

    }

    void addToList(long id, String name, String email) {
        NameCard card = new NameCard(this);
        card.init(id, name, email);
        listLayout.addView(card);
    }

    void animLayoutOpen() {
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }

    void animLayoutClose() {
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        selectedNameCard = (NameCard) v;
        menu.add(0, MENU_EDIT, 0, "Edit");
        menu.add(0, MENU_DELETE, 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_EDIT:
                animLayoutOpen();
                etName.setText(selectedNameCard.getName());
                etEmail.setText(selectedNameCard.getEmail());
                break;
            case MENU_DELETE:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String id = String.valueOf(selectedNameCard.getContactId());
                db.delete("mytable", "id = ?", new String[]{id});
                dbHelper.close();
                listLayout.removeView(selectedNameCard);
                break;
        }
        return super.onContextItemSelected(item);
    }

    class NameCard extends TextView {

        long contactId;
        String name;
        String email;

        public NameCard(Context context) {
            super(context);
            this.setOnCreateContextMenuListener((OnCreateContextMenuListener) context);
        }

        public void init(long id, String name, String email) {
            contactId = id;
            this.setText("Name: " + name + "\r\nEmail: " + email);
            this.name = name;
            this.email = email;
        }

        public long getContactId() {
            return contactId;
        }

        public String getEmail() {
            return email;
        }

        public void setName(String name) {
            this.name = name;
            this.setText("Name: " + name + "\r\nEmail: " + email);
        }

        public void setEmail(String email) {
            this.email = email;
            this.setText("Name: " + name + "\r\nEmail: " + email);
        }

        public String getName() {
            return name;
        }

        //this method will update info from SQLite after editing
        public void update() {

        }
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // создаем таблицу с полями
            db.execSQL("create table mytable (id integer primary key autoincrement,name text,email text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
