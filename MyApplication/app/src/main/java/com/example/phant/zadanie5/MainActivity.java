package com.example.phant.zadanie5;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton ctc = (FloatingActionButton) findViewById(R.id.ctc);
        ctc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               wybierzKontakt();

            }
        });

    }

//wybieranie kontaktow
    private void wybierzKontakt() {

        Intent wybKontakt = new Intent(Intent.ACTION_PICK);
        wybKontakt.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        startActivityForResult(wybKontakt,1);
    }
    //wyszukiwarka jako strona internetowa
    public void search (View v){
        Intent intent;
        switch (v.getId()){
            case R.id.search:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.pl/?gfe_rd=cr&ei=_4KIWLvpLvKv8wfGnpUo#q=wikipedia"));
                startActivity(intent);
                break;

        }
    }

    @Override
    //wyświetlanie listy kontaktow
    //a po wybraniu jednego wyswietla go - nazwe kontaktu i numer
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri dane = data.getData();
            String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

            Cursor c = getContentResolver().query(dane, projection,null, null, null);
            c.moveToFirst();
            int numberColumnIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = c.getString(numberColumnIndex);

            int nameColumnIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String name = c.getString(nameColumnIndex);
            tt.setText(name + ": " + number);

        }
    }



}
