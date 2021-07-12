package com.example.asus.maniakfitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView tuname, slmtdtg;
    String suname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tuname = (TextView) findViewById(R.id.tname);
        slmtdtg = (TextView) findViewById(R.id.slmt);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        suname = b.getString("user");
        slmtdtg.setText("TESYAR Fitnes");
        tuname.setText(" Semangat bekerja, no pain no gain");
        //disini adalah kode untuk menampilkan text pada bagian homenya, sebagai ucapan selamat datang

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.off);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Home.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.item1:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
        //noinspection SimplifiableIfStatement

        //HOME.java ini dibuat untuk menampilkan latar utama setelah login, untuk proses selanjutnya sudah ditampilkan pada garis 3 pada pojok kanan atas
        //kemudian kita intent kembali dan kita lempar lg ke main activity nya

        return super.onOptionsItemSelected(item);
    }
}
