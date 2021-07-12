package com.example.asus.maniakfitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsMember extends AppCompatActivity{
    DbAdapter db;
    String id,name,number,nomoru,address,gender,masa;
    //disini menggunakan string agar terpanggil id ke APKnya
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_member);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        number = intent.getStringExtra("NUMBER");
        nomoru = intent.getStringExtra("NOMORU");
        address = intent.getStringExtra("ADDRESS");
        gender = intent.getStringExtra("GENDER");
        masa = intent.getStringExtra("MASA");
        ((TextView) findViewById(R.id.named)).setText(name);
        ((TextView) findViewById(R.id.numberd)).setText(number);
        ((TextView) findViewById(R.id.emaild)).setText(nomoru);
        ((TextView) findViewById(R.id.addressd)).setText(address);
        ((TextView) findViewById(R.id.genderd)).setText(gender);
        ((TextView) findViewById(R.id.masad)).setText(masa);
        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        //kode diatas semua adalah pemanggilan dari id sampai masaaktif agar id satu persatu layout tersebut terpanggil dan dapat diisi setelah aplikasinya dites
    }
    public void Edit(View v){
        //go to EditContact page
        Intent edit = new Intent(DetailsMember.this, EditMember.class);
        edit.putExtra("ID", id);
        edit.putExtra("NAME", name);
        edit.putExtra("NUMBER", number);
        edit.putExtra("NOMORU", nomoru);
        edit.putExtra("ADDRESS",address);
        edit.putExtra("GENDER", gender);
        edit.putExtra("MASA", masa);
        startActivity(edit);
        //kode diatas untuk perintah edit dalam data membernya
    }
    public void Delete(View v){
        db.delete(Integer.parseInt(id));
        Toast.makeText(getApplicationContext(),"deleted", Toast.LENGTH_SHORT).show();
    }
    //kita view agar menampilkan hasilnya
    //disini adalah perintah untuk mendelete data membernya

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    //kemudian kita intent dan kita lempar ke mainactivity agar menjadi satu dengan yang lainnya
}
