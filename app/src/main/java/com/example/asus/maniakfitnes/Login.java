package com.example.asus.maniakfitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btn;
    TextView un, pw;
    String nuname, npass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        un = (TextView) findViewById(R.id.uname);
        pw = (TextView) findViewById(R.id.passw);
        btn = (Button) findViewById(R.id.btnLogin);
        //kode untuk pemanggilan layout dan agar layoutnya dapat diisi saat APKnya dirun

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(eduser.getText().toString().length() ==0 && edpass.getText().toString().length() ==0){
                // eduser.setError("Perlu Diisi");
                // edpass.setError("hehe ");}
                //  else{
                //     user = eduser.getText().toString();
                //     pass = edpass.getText().toString();
                //     TxHasil.setText(user + " " + pass); }


                if(un.getText().toString().length()==0){
                    un.setError("Harap diisi");
                }
                if (pw.getText().toString().length() == 0) {
                    pw.setError("Harap diisi");
                }
                else if (!un.getText().toString().isEmpty() || (!pw.getText().toString().isEmpty())) {
                    if (!un.getText().toString().equals("Sayang") || !pw.getText().toString().equals("123")) {
                        Toast.makeText(getApplicationContext(), "username/password salah", Toast.LENGTH_SHORT).show();
                    } else {
                        nuname = un.getText().toString();
                        npass = pw.getText().toString();
                        Bundle b = new Bundle(); //Menyiapkan Paket
                        b.putString("u", nuname); //buat menyimpan barang ke kardus
                        b.putString("p", npass); //buat menyimpan barang ke kardus
                        //dikode ini kita peintahkan agar penjaga tempat gym harus mengisi id dan password untuk masuk, id dan passwordnya sudah dibuat oleh pembuat apk
                        //jika penjaga tidak mengisi maka akan muncul tanda alert dan tidak bisa masuk ke dalam apk nya


                        Intent in = new Intent(Login.this, Home.class);
                        in.putExtras(b); //memberi kardus ke kuriri
                        startActivity(in);
                        //disini kita intent tetapi kita jadikan atau kita buat login dan home ini dapat terintegrasi
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
        //kita panggil kembali menu loginnya agar dapat bekerja tp login ini tidak perlu kita lempar ke main activity krn kita ingin dia ada akses khususnya
    }
}
