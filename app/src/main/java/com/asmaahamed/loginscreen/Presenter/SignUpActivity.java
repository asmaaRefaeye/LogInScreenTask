package com.asmaahamed.loginscreen.Presenter;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asmaahamed.loginscreen.Entity.User;
import com.asmaahamed.loginscreen.R;
import com.asmaahamed.loginscreen.Usecase.UserDataBase;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class SignUpActivity extends AppCompatActivity {

    EditText fristname , lastname , Email, password , confirmPassword;
    Button CreatAccount;
    AwesomeValidation awesomeValidation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        iniate();
    }

    public void iniate(){
        fristname=findViewById(R.id.signup_fristname_edittext);
        lastname=findViewById(R.id.signup_lastname_edittext);
        Email=findViewById(R.id.signup_email_editText);
        password=findViewById(R.id.signup_pass_editText);
        confirmPassword=findViewById(R.id.confirmPass_editText);
        CreatAccount=findViewById(R.id.creatAcount_button);

        String password_validation = "^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})";
        awesomeValidation.addValidation(SignUpActivity.this,R.id.signup_fristname_edittext,"[a-zA-Z\\s]+",R.string.fistName);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.signup_lastname_edittext,"[a-zA-Z\\s]+",R.string.lastName);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.signup_email_editText, Patterns.EMAIL_ADDRESS,R.string.Email);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.signup_pass_editText,password_validation,R.string.password);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.confirmPass_editText,R.id.password_edittext,R.string.confirmPassword);

        CreatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(awesomeValidation.validate()) {


                    UserDataBase database;
                    database= Room.databaseBuilder(SignUpActivity.this, UserDataBase.class,"mydb")
                            .allowMainThreadQueries()
                            .build();
                    User user=new User(fristname.getText().toString(),
                            lastname.getText().toString(),
                            Email.getText().toString(),
                            password.getText().toString());
                    database.getUserDao().insert(user);



                    SharedPreferences sh= getSharedPreferences("myprefs", MODE_PRIVATE); ;
                    SharedPreferences.Editor editor=sh.edit();
                   // editor.putString("currentloggedInUser",Email.getText().toString()).apply();
                    editor.putString("userdisplayName",fristname.getText().toString()).apply();
                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                    finish();



                   // Toast.makeText(SignUpActivity.this, " you creat account successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this, " you need to fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
