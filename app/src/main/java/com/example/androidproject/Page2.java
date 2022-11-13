package com.example.androidproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.text.DateFormat;
import java.util.Calendar;

public class Page2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextView current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        Button button1 = findViewById(R.id.dateBtn1);
        button1.setOnClickListener(v -> {
            current = findViewById(R.id.emptyTextView1);
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });

        Button button2 = findViewById(R.id.dateBtn2);
        button2.setOnClickListener(v -> {
            current = findViewById(R.id.emptyTextView2);
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });

        Button nextBtn = findViewById(R.id.page2_nextBtn);
        nextBtn.setOnClickListener(v -> next());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        current.setText(currentDateString);
    }

    public void next(){
        Intent intent = new Intent(this, Page3.class);
        Bundle bundle = new Bundle();
        TextView departure = findViewById(R.id.emptyTextView1);
        String departureDate = departure.getText().toString();
        TextView Return = findViewById(R.id.emptyTextView2);
        String returnDate = Return.getText().toString();
        EditText passenger = findViewById(R.id.page2_textInput2);
        String number = passenger.getText().toString();
        EditText add = findViewById(R.id.page2_textInput);
        String address = add.getText().toString();
        if(departure.length() == 0 || departureDate.matches(" ")||departureDate.trim().matches("")){
            Toast.makeText(getApplicationContext(),"Please select departure Date", Toast.LENGTH_LONG).show();
        }
        else if(Return.length() == 0 || returnDate.matches(" ")||returnDate.trim().matches("")){
            Toast.makeText(getApplicationContext(),"Please select return Date", Toast.LENGTH_LONG).show();
        }

        else if(add.length() == 0 || address.matches(" ")||address.trim().matches("")){
            Toast.makeText(getApplicationContext(),"Please enter valid address", Toast.LENGTH_LONG).show();
        }
        else if(passenger.length() == 0 || number.matches(" ")||number.trim().matches("") ){
            Toast.makeText(getApplicationContext(),"Please enter valid number of passengers", Toast.LENGTH_LONG).show();
        }
        else {
            bundle.putString("start", departureDate);
            bundle.putString("end", returnDate);
            bundle.putString("address", address);
            bundle.putString("passengers", number);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }
    }
}