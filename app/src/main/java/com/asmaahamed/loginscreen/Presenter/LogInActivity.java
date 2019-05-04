package com.asmaahamed.loginscreen.Presenter;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asmaahamed.loginscreen.Entity.User;
import com.asmaahamed.loginscreen.R;
import com.asmaahamed.loginscreen.Usecase.UserDataBase;

public class LogInActivity extends AppCompatActivity {

    Button SignIn , SignUp;
    EditText name , password;
    TextView signupMessage;
    UserDataBase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database= Room.databaseBuilder(this, UserDataBase.class,"mydb")
                .allowMainThreadQueries()
                .build();


        initiate();

    }



    public void initiate(){

        SignIn=findViewById(R.id.signin_button);
        SignUp=findViewById(R.id.signup_button);
        name=findViewById(R.id.username_edittext);
        password=findViewById(R.id.password_edittext);
        signupMessage=findViewById(R.id.signup_messsage_textView);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataBase database;
                database= Room.databaseBuilder(LogInActivity.this, UserDataBase.class,"mydb")
                        .allowMainThreadQueries()
                        .build();

              User user=  database.getUserDao().getUserByName(name.getText().toString().trim(),password.getText().toString());
                if (user==null){
                    Toast.makeText(LogInActivity.this, "wrong user or password", Toast.LENGTH_SHORT).show();

                }else{
                    SharedPreferences sh= getSharedPreferences("myprefs", MODE_PRIVATE); ;
                    SharedPreferences.Editor editor=sh.edit();
                    editor.putString("userdisplayName",user.getName()).apply();
                    startActivity(new Intent(LogInActivity.this,MainActivity.class));
                    finish();
                }

                Intent signInintent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(signInintent);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpintent = new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(signUpintent);
            }
        });

    }



}
