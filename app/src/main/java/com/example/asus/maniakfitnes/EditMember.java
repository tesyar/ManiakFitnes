package com.example.asus.maniakfitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditMember extends AppCompatActivity {
    DbAdapter db;
    String id,name,number,nomoru,address,gender,masa;
    EditText etname,etnumber,etnomoru,etaddress,etgender,etmasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_contact);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        number = intent.getStringExtra("NUMBER");
        nomoru = intent.getStringExtra("NOMORU");
        address = intent.getStringExtra("ADDRESS");
        gender = intent.getStringExtra("GENDER");
        masa = intent.getStringExtra("MASA");
        ((EditText) findViewById(R.id.names)).setText(name);
        ((EditText) findViewById(R.id.numbers)).setText(number);
        ((EditText) findViewById(R.id.emails)).setText(nomoru);
        ((EditText) findViewById(R.id.addresss)).setText(address);
        ((EditText) findViewById(R.id.genders)).setText(gender);
        ((EditText) findViewById(R.id.masas)).setText(masa);
        //kode diatas semua adalah pemanggilan dari id sampai masaaktif agar id satu persatu layout tersebut terpanggil dan dapat diisi setelah aplikasinya dites

        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        //get data from text feld
        etname =(EditText)findViewById(R.id.names);
        etnumber =(EditText)findViewById(R.id.numbers);
        etnomoru =(EditText)findViewById(R.id.emails);
        etaddress = (EditText)findViewById(R.id.addresss);
        etgender =(EditText)findViewById(R.id.genders);
        etmasa =(EditText)findViewById(R.id.masas);
        //pemanggilan DB agar dapat diedit ke dalam proses edit member (java)
    }
    public void Save(View v){
        name=etname.getText().toString();
        number=etnumber.getText().toString();
        nomoru=etnomoru.getText().toString();
        gender=etgender.getText().toString();
        address=etaddress.getText().toString();
        db.update(Integer.parseInt(id),name, number, nomoru, address, gender, masa);
        Toast.makeText(getApplicationContext(),"Update success", Toast.LENGTH_SHORT).show();
        //kita view agar menampilkan hasilnya
        //ini adalah kode untuk mengesave data setelah didelete agar data dapat kembali ditampilkan
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        //disini difinish dan di intent kemudian dilempar ke main activity lg
    }
}
