package com.example.asus.maniakfitnes;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    DbAdapter db;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        //initially insert some data
        ListView lv = (ListView) findViewById(R.id.listView1);
        int layoutstyle = R.layout.liststyle;
        int[] xml_id = new int[]{
                R.id.txtname,
                R.id.txtnumber
        };
        //pemanggilan DB dengan menyebutkan id txtname dan txtnumber
        String[] column = new String[]{
                "name",
                "number"
        };
        //kita panggil kembali agar prosesnya sempurna
        Cursor row = db.fetchAllData();
        adapter = new SimpleCursorAdapter(this, layoutstyle, row, column, xml_id, 0);
        lv.setAdapter(adapter);
        //onClick function
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                Cursor row = (Cursor) adapterview.getItemAtPosition(position);
                String _id = row.getString(row.getColumnIndexOrThrow("_id"));
                String name = row.getString(row.getColumnIndexOrThrow("name"));
                String number = row.getString(row.getColumnIndexOrThrow("number"));
                String nomoru = row.getString(row.getColumnIndexOrThrow("nomoru"));
                String address = row.getString(row.getColumnIndexOrThrow("address"));
                String gender = row.getString(row.getColumnIndexOrThrow("gender"));
                String masa = row.getString(row.getColumnIndexOrThrow("masa"));
                //go to detailsContact page
                Intent todetais = new Intent(MainActivity.this, DetailsMember.class);
                todetais.putExtra("ID", _id);
                todetais.putExtra("NAME", name);
                todetais.putExtra("NUMBER", number);
                todetais.putExtra("NOMORU", nomoru);
                todetais.putExtra("ADDRESS", address);
                todetais.putExtra("GENDER", gender);
                todetais.putExtra("MASA", masa);
                startActivity(todetais);
            }
        });
        //kita panggoil semua yang ada pada DetailMember.java (idnya) yg kita oanggil disini untuk menampilkan hasilnya


        //dispay data by filter
        EditText et = (EditText) findViewById(R.id.myFilter);
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });
        //kemudian kita panggil edit textnya agar memfilter data2nya

        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return db.fetchdatabyfilter(constraint.toString(), "name");
            }
        });
    }

    public void addContact(View v) {
        Intent addNewMember = new Intent(MainActivity.this, addNewMember.class);
        startActivity(addNewMember);
    }
    //kita view agar menampilkan hasilnya
    //setelah semua terangkup jd satu kita intent kembali dan kita masukkan kembali ke addnewmember agar menjadi satu lg

}
