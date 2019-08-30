package com.y45h.y45hsfoodhub;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn,btnSignUp;
    TextView txtSlogan,txtTagline;
    ImageView imgLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init
        imgLogo = findViewById(R.id.logo);
        animator(imgLogo);
        btnSignIn = findViewById(R.id.btn_SignIn);
        btnSignUp = findViewById(R.id.btn_SignUp);
        txtSlogan = findViewById(R.id.txtSlogan);
        txtTagline=findViewById(R.id.txtTagline);

        setFont(txtTagline);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignIn.class);
                startActivity(intent);
            }
        });
    }
    public void animator(final ImageView myImageView){
        final Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        myImageView.startAnimation(myFadeInAnimation);

        new Thread(new Runnable() {
            private Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.w("handler", "received");
                    Animation myFadeOutAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);
                    myImageView.startAnimation(myFadeOutAnimation);
                    myImageView.startAnimation(myFadeInAnimation);
                    myImageView.setVisibility(View.VISIBLE);
                }
            };

            @Override
            public void run() {
                try{
                    Thread.sleep(2000); // your fadein duration
                }catch (Exception e){
                }
                handler.sendEmptyMessage(1);

            }
        }).start();
    }
    public void setFont(TextView textView){
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        textView.setTypeface(face);
    }
}
