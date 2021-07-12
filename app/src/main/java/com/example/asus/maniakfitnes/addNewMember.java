package com.example.asus.maniakfitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addNewMember extends AppCompatActivity{
    DbAdapter db;
    //1. memanggil nama atau id database (db) yg telah dibuat
    EditText etname,etnumber,etnomoru,etaddress,etgender,etmasa;
    //2. memanggil nama atau id pada layout.xml yg sudah dibuat agar terpanggil dalam java dan bisa di edit atau diisi
    //menggunakan EditText agar nama2 tersebut dapat diisi
    String name,number,nomoru,address,gender,masa;
    //3. string nama atau id untuk memanggil kolom yg sudah dibuat agar dapat diisi dengan angka maupun huruf
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_contact);
        // kodingan untuk membundle dari panggilan edit text, string dan databasenya

    etname =(EditText)findViewById(R.id.names);
    //pemanggollan agar kolom layout dengan id atau nama "etname" dapat diisi maupun diedit
    etnumber =(EditText)findViewById(R.id.numbers);
    //pemanggollan agar kolom layout dengan id atau nama "etnumber" dapat diisi maupun diedit
    etnomoru =(EditText)findViewById(R.id.emails);
    //pemanggollan agar kolom layout dengan id atau nama "etnomoru" dapat diisi maupun diedit
    etaddress = (EditText)findViewById(R.id.addresss);
    //pemanggollan agar kolom layout dengan id atau nama "etaddress" dapat diisi maupun diedit
    etgender =(EditText)findViewById(R.id.genders);
    //pemanggollan agar kolom layout dengan id atau nama "etgender" dapat diisi maupun diedit
    etmasa =(EditText)findViewById(R.id.masas);
    //pemanggollan agar kolom layout dengan id atau nama "etmasa" dapat diisi maupun diedit

    db = new DbAdapter(this);
        db.open();
}
//database dikeluarkan dan dipanggil disini
    public void Save(View v){
        if(db.isExist(number)){
            Toast.makeText(getApplicationContext(),"already exist", Toast.LENGTH_SHORT).show();
        }else{
            name=etname.getText().toString();
            //setelah sudah terpanggil dan terpakai, panggilan selanjutnya adalah id string nama agar masuk dalam databse dan terbaca dalam apk
            number=etnumber.getText().toString();
            //setelah nama selanjutnya nomor, agar nomor telpon ini masuk dalam database
            nomoru=etnomoru.getText().toString();
            //selanjutnya nomoru, agar nomor urut jg masuk dalam data base
            address=etaddress.getText().toString();
            //kemudian alamat
            gender=etgender.getText().toString();
            //gender
            masa=etmasa.getText().toString();
            //kemudian masa aktif member
            //memilih string alesannya karena agar daoat ditulis secara huruf dan angka secara bersamaan
            db.insert(name,number,nomoru,address,gender,masa);
            //disini kita insert database agar memasukkan id yg kita mau, secara urut agar hasilnya jg urut
            Toast.makeText(getApplicationContext(),"Contact added", Toast.LENGTH_SHORT).show();
            //ini untuk balasan dari databsenya setelah kita mengisi data tersebut
        }

    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        // kita inten agar dan kita masukkan dalam main activity
    }
}
