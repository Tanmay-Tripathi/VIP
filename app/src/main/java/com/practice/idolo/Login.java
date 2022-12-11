package com.practice.idolo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Login extends AppCompatActivity {

    String str = "";
    Button callSignUp, loginbutton;
    ImageView img;
    TextView slogan;
    TextInputLayout username, password;

    Socket s;
    OutputStreamWriter dos;
    InputStream is;
    BufferedReader bf;
    BufferedWriter bw;
    InputStreamReader in;
    PrintWriter pw;
    Mythread myThread;

    String msg1 = "";
    String msg2 = "";
    String msg = "";
    String output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        myThread = new Mythread();
        new Thread(myThread).start();


        //Hooks
        callSignUp = findViewById(R.id.callSignup);
        img = findViewById(R.id.img1);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        slogan = findViewById(R.id.slogan_name);
        loginbutton = findViewById(R.id.login_button1);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                Pair[] pairs = new Pair[5];

                pairs[0] = new Pair<View, String>(img, "logo_image");
                pairs[1] = new Pair<View, String>(slogan, "logo_desc");
                pairs[2] = new Pair<View, String>(username, "logo_username");
                pairs[3] = new Pair<View, String>(password, "logo_password");
                pairs[4] = new Pair<View, String>(loginbutton, "button_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());


            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                msg1 = username.getEditText().getText().toString();
                msg2 = password.getEditText().getText().toString();


                msg = "login%"+msg1 + "#" + msg2 ;
                myThread.sendMsgParam(msg);

                if(output.equals("True"))
                {
                    Intent intent = new Intent(Login.this,Data.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "Login Failed,Try again :( ", Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    class Mythread implements Runnable {

        @Override
        public void run() {
            try {

                s = new Socket("192.168.153.250", 65433);
                is = s.getInputStream();
                pw = new PrintWriter(s.getOutputStream());
                pw.print(msg);
                pw.flush();
                byte[] buffer = new byte[1024];
                int read;
                while((read = is.read(buffer)) != 1) {
                    output = new String(buffer, 0, read);
                    System.out.flush();
                };
                s.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        }

        public void sendMsgParam(String v) {
            msg = v;
            run();
        }

    }
}