package com.example.todolistaapps;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateData extends AppCompatActivity {
    protected Cursor cursor;
    com.example.todolistaapps.DataHelper dbHelper;
    Button ton1, ton2;
    ImageButton btn5, btn6;
    EditText text1, text2, text3, text4, text5;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    RadioGroup rgjk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dbHelper = new com.example.todolistaapps.DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        myCalendar = Calendar.getInstance();
        rgjk = (RadioGroup) findViewById(R.id.jk);


        text5 = (EditText) findViewById(R.id.editText5);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM todo WHERE title = '" +
                getIntent().getStringExtra("title") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            rgjk.getCheckedRadioButtonId();
            text5.setText(cursor.getString(4).toString());
        }
        btn5= (ImageButton) findViewById(R.id.imageButton5);
        btn6= (ImageButton) findViewById(R.id.imageButton7);



        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        text3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UpdateData.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        // daftarkan even onClick pada btnSimpan
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int checkedButtonId = rgjk.getCheckedRadioButtonId();
                RadioButton checkedButton = findViewById(checkedButtonId);
                String ButtonText = checkedButton.getText().toString();

                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update todo set title='"+
                        text2.getText().toString() +"', date='" +
                        text3.getText().toString()+"', priority='"+
                        checkedButton.getText().toString()+"', description='" +
                        text5.getText().toString() + "' where no='" +
                        text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                com.example.todolistaapps.MainActivity.ma.RefreshList();
                finish();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        text3.setText(sdf.format(myCalendar.getTime()));
    }


}



