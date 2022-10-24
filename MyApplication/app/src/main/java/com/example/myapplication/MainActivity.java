package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity
{
    private View parentView;
    private SwitchMaterial themeChange;
    private TextView theme, title;

    private UserSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = (UserSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        initSwitchListener();

        configureNextButton();
    }

    private void initWidgets()
    {
        theme = findViewById(R.id.theme);
        title = findViewById(R.id.title);
        themeChange = findViewById(R.id.themeChange);
        parentView = findViewById(R.id.parentView);

    }

    private void loadSharedPreferences()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE);
        String theme = sharedPreferences.getString(UserSettings.CUSTOM_THEME, UserSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);
        updateView();
    }

    private void initSwitchListener()
    {
        themeChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                if(checked)
                    settings.setCustomTheme(UserSettings.DARK_THEME);
                else
                    settings.setCustomTheme(UserSettings.LIGHT_THEME);

                SharedPreferences.Editor editor = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(UserSettings.CUSTOM_THEME, settings.getCustomTheme());
                editor.apply();
                updateView();
            }
        });


    }

    private void updateView()
    {
        final int black = ContextCompat.getColor(this, R.color.black);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(settings.getCustomTheme().equals(UserSettings.DARK_THEME))
        {
            title.setTextColor(white);
            theme.setTextColor(white);
            theme.setText("Dark");
            parentView.setBackgroundColor(black);
            themeChange.setChecked(true);
        }
        else
        {
            title.setTextColor(black);
            theme.setTextColor(black);
            theme.setText("Light");
            parentView.setBackgroundColor(white);
            themeChange.setChecked(false);
        }
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