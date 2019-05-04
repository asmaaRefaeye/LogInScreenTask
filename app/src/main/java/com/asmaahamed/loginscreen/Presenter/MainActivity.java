package com.asmaahamed.loginscreen.Presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asmaahamed.loginscreen.R;

public class MainActivity extends AppCompatActivity {

    TextView welcomeText , nameText ,recievedNameText;
    Button logout;
String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sh= getSharedPreferences("myprefs", MODE_PRIVATE); ;
        username=sh.getString("userdisplayName","null");// // w
        if (username.equals("null")) {
            startActivity(new Intent(this ,LogInActivity.class));
            finish();

        }else{

            String name=sh.getString("userdisplayName","User");
            TextView welcome=findViewById(R.id.welcome_textView);
            welcome.setText(name);
        }



        logout=findViewById(R.id.logout_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh= getSharedPreferences("myprefs", MODE_PRIVATE); ;
                SharedPreferences.Editor editor=sh.edit();
                editor.clear();
                Intent logoutIntent = new Intent(MainActivity.this, LogInActivity.class);
                finish();
            }
        });
    }
}
