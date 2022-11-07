package com.example.state_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName;

    private static final String KEY_FIRSTNAME= "firstname_key";
    private static final String KEY_LASTNAME = "lastname_key";

    private String mFirstName, mLastName;
    TextView first, last;

    /*I have used savedInstances for restoring the state of UI.
      All the seven state methods can be used for the app to behave in a particular manner
      Using those methods, I am just displaying the message on a toast about which state the app has entered.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText) findViewById(R.id.edit_firstname);
        lastName = (EditText) findViewById(R.id.edit_lastname);

        first = (TextView) findViewById(R.id.First);
        last = (TextView) findViewById(R.id.Last);

        configureNextButton();


        if (savedInstanceState != null) {
            String savedFirstName = savedInstanceState.getString(KEY_FIRSTNAME);
            first.setText(savedFirstName);

            String savedLastName = savedInstanceState.getString(KEY_LASTNAME);
            last.setText(savedLastName);

        }else{
            Toast.makeText(this, "New entry", Toast.LENGTH_SHORT).show();
        }


    }

    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "on start", Toast.LENGTH_SHORT).show();

    }

    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "on restart", Toast.LENGTH_SHORT).show();

    }

    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "on resume", Toast.LENGTH_SHORT).show();

    }

    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "on pause", Toast.LENGTH_SHORT).show();

    }

    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "on stop", Toast.LENGTH_SHORT).show();
    }

    protected void ondestroy(){
        super.onDestroy();
        Toast.makeText(this, "on destroy", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(KEY_FIRSTNAME, first.getText().toString());
        savedInstanceState.putString(KEY_LASTNAME, last.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        firstName.setText(savedInstanceState.getString(KEY_FIRSTNAME));
        lastName.setText(savedInstanceState.getString(KEY_LASTNAME));
        first.setText(savedInstanceState.getString(KEY_FIRSTNAME));
        last.setText(savedInstanceState.getString(KEY_LASTNAME));

    }

    public void saveView(View view){
        first.setText(firstName.getText().toString().trim());
        last.setText(lastName.getText().toString().trim());
    }

    private void configureNextButton()
    {
        Button nextButton = (Button) findViewById(R.id.nextbutton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }


}
