package com.bkash.demoandriodsqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2ActivitySqLite extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_sq_lite);
        //sq lite exmaple
        SQLiteDatabase db =this.openOrCreateDatabase("NahidDb",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS mytable (name VARTCHAR,age INT(3))");
        db.execSQL("DELETE FROM MYTABLE");
        db.execSQL("INSERT INTO mytable(name,age) VALUES('NAHID',3)");
        db.execSQL("INSERT INTO mytable(name,age) VALUES('NAHID2',4)");
        db.execSQL("INSERT INTO mytable(name,age) VALUES('NAHID3',5)");
        db.execSQL("INSERT INTO mytable(name,age) VALUES('NAHID4',6)");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,R.layout.activity_list_view,R.id.textView,listItems);

        ListView myListView=(ListView) findViewById(R.id.myListView2);
        myListView.setAdapter(adapter);

        Cursor cursor = db.rawQuery("SELECT * FROM mytable",null);
        int nameIndex = cursor.getColumnIndex("name");
        int ageIndex =cursor.getColumnIndex("age");
        try {
            while (cursor.moveToNext()) {
                Log.i("name",cursor.getString(nameIndex));
                Log.i("age",Integer.toString(cursor.getInt(ageIndex)));
                listItems.add(cursor.getString(nameIndex));
            }
        } finally {
            cursor.close();
        }

        // add a handler

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                    String name = adapter.getItem(position);

                    Toast.makeText(getApplicationContext(), "You clicked " + name, Toast.LENGTH_SHORT).show();


            }
        });




    }

}
