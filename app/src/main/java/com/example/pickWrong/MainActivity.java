package com.example.pickWrong;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean PlayOrNot = true;

    MediaPlayer player,begin ;
    private Random rng = new Random();

    private int winPoints = 0;
    private int failsPoints = 0;
    private TextView TextViewPoint;
    private TextView TextViewFail;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       TextViewPoint = findViewById(R.id.text_view_point);
       TextViewFail = findViewById(R.id.text_view_fail);
       Button buttonRestart = findViewById(R.id.button_reset);
       buttonRestart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               reStart();
           }
       });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);

            }
        }

            begin = MediaPlayer.create(getApplicationContext(),R.raw.begininiin);
            begin.start();
        Button buttonMusic = findViewById(R.id.button_music);
        buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlayOrNot = !PlayOrNot;
                if (!PlayOrNot){
                    begin.pause();
                    Toast.makeText(MainActivity.this," I pause it for u <3 ",Toast.LENGTH_SHORT).show();
                }else
                    begin.start();

            }
        });
        Toast.makeText(MainActivity.this," Start in 5 seconds :) ",Toast.LENGTH_LONG).show();
            mHandler.postDelayed(mCheckForWin,5000);
            mHandler.postDelayed(mUpdatePointText,1);


    }

    private Runnable mCheckForWin = new Runnable() {
       @Override
       public void run() {
           int i = rng.nextInt(3);
           int j = rng.nextInt(3);
           int status = rng.nextInt(4) + 1;
           switch (status) {
               case 1:
                   buttons[i][j].setBackgroundResource(R.drawable.yucks);
                   buttons[i][j].setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           winPoints = winPoints + 1;
                           ((Button) v).setBackgroundResource(R.drawable.start);
                           ((Button) v).setOnClickListener(null);
                           player=MediaPlayer.create(getApplicationContext(),R.raw.volumnup);
                           player.start();
                       }
                   });

                   break;
               case 2:
                   buttons[i][j].setBackgroundResource(R.drawable.good);
                   buttons[i][j].setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Toast.makeText(MainActivity.this,"How dare u :(",Toast.LENGTH_SHORT).show();
                           failsPoints = failsPoints + 1;
                           ((Button) v).setBackgroundResource(R.drawable.start);
                           ((Button) v).setOnClickListener(null);
                       }
                   });

                   break;
               case 3:
                   buttons[i][j].setBackgroundResource(R.drawable.yucks);
                   buttons[i][j].setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           winPoints = winPoints + 1;
                           ((Button) v).setBackgroundResource(R.drawable.start);
                           ((Button) v).setOnClickListener(null);
                           player=MediaPlayer.create(getApplicationContext(),R.raw.volumnup);
                           player.start();
                       }
                   });

                   break;
               case 4:
                   buttons[i][j].setBackgroundResource(R.drawable.yucks);
                   buttons[i][j].setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           winPoints = winPoints + 1;
                           ((Button) v).setBackgroundResource(R.drawable.start);
                           ((Button) v).setOnClickListener(null);
                           player=MediaPlayer.create(getApplicationContext(),R.raw.volumnup);
                           player.start();
                       }
                   });

                   break;
           }
           //buttons[i][j].setOnClickListener(null);
           if (failsPoints >= 3){
               mHandler.removeCallbacks(this);
               Toast.makeText(MainActivity.this,"What did you do... WHAT DID YOU DO",Toast.LENGTH_LONG).show();
               reStart();

           }else {
               mHandler.postDelayed(this,200);
           }
       }

   } ;

    private Runnable mUpdatePointText = new Runnable() {
        @Override
        public void run() {
            TextViewPoint.setText("Score : " + winPoints);
            TextViewFail.setText("Fail :  " + failsPoints);
            mHandler.postDelayed(this,1);
        }
    };
    private void reStart(){
        failsPoints = 0;
        winPoints = 0;
        for (int i=0 ; i<3 ;i++){
            for (int j=0 ; j<3 ;j++){
                buttons[i][j].setBackgroundResource(R.drawable.start);
                buttons[i][j].setOnClickListener(null);
            }
        }
        Toast.makeText(MainActivity.this," Restart in 5 seconds, dude ",Toast.LENGTH_LONG).show();
        mHandler.postDelayed(mCheckForWin,5000);
    }

}









