package com.practice.idolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.UserDataWriter;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    //variables
    TextInputLayout regUsername,regPassword,regInstagram,regTwitter,regYoutube;
    Button regButton,backtoLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;
    MyThread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        myThread = new MyThread();
        new Thread().start();

        //Hooks to all the XML elements of the signup page
        regUsername = findViewById(R.id.newusername);
        regPassword = findViewById(R.id.newPassword);
        regInstagram = findViewById(R.id.instagram);
        regTwitter = findViewById(R.id.twitter);
        regYoutube = findViewById(R.id.youtube);
        regButton = findViewById(R.id.login_button2);
        backtoLogin = findViewById(R.id.alreadylogin);


        backtoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });


    }

    private Boolean validateUsername(){

        String value = regUsername.getEditText().getText().toString();
        String noWhitespace = "(?=\\S+$)" ;

        if(value.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }else if(value.length()>=15)
        {
           regUsername.setError("Username too long");
           return false;
        }
        else{
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){

        String value = regPassword.getEditText().getText().toString();
        String passwordValidation = "^" + "(?=.*[a-zA-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{4,}"+"$";

        if(value.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        } else if(!value.matches(passwordValidation)){
            regPassword.setError("Password is too week");
            return false;
        }
        else{
            regPassword.setError(null);
            return true;
        }
    }

    private Boolean validateInstagram(){

        String value = regInstagram.getEditText().getText().toString();

        if(value.isEmpty()){
            regInstagram.setError("Field cannot be empty");
            return false;
        }
        else{
            regInstagram.setError(null);
            return true;
        }
    }

    private Boolean validateTwitter(){

        String value = regTwitter.getEditText().getText().toString();

        if(value.isEmpty()){
            regTwitter.setError("Field cannot be empty");
            return false;
        }
        else{
            regTwitter.setError(null);
            return true;
        }
    }

    private Boolean validateYoutube(){

        String value = regYoutube.getEditText().getText().toString();

        if(value.isEmpty()){
            regYoutube.setError("Field cannot be empty");
            return false;
        }
        else{
            regYoutube.setError(null);
            return true;
        }
    }

    public void registerUser(View view){

        if(!validateUsername() | !validatePassword() | !validateInstagram() | !validateTwitter() | !validateYoutube()){
            return;
        }


        //Save data in Firebase on button click
        String username = regUsername.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        String instagram = regInstagram.getEditText().getText().toString();
        String twitter = regTwitter.getEditText().getText().toString();
        String youtube = regYoutube.getEditText().getText().toString();

        String msg = "signup%"+username+"%"+password+"%"+instagram+"%"+twitter+"%"+youtube;
        myThread.sendMsgParam(msg);
    }

    class MyThread implements Runnable {
        private volatile String msg;

        @Override
        public void run() {
            try {
                s = new Socket("192.168.153.250", 65433);
                dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(msg);
                dos.close();
                dos.flush();
                s.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        }

        public void sendMsgParam(String msg) {
            this.msg = msg;
            run();
        }

    }

}



