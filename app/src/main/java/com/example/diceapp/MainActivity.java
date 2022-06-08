package com.example.diceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //TextViews
    private TextView txtRoll;
    private TextView txtTotal;
    //ImageViews
    private ImageView imgDiceOne;
    private ImageView imgDiceTwo;
    //Int array for images
    private int[] diceImages;
    //Media Player
    private MediaPlayer mp;
    //For Vibration
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextViews
        txtRoll = findViewById(R.id.txtRoll);
        txtTotal = findViewById(R.id.txtTotal);

        //ImageViews
        imgDiceOne = findViewById(R.id.imgDiceOne);
        imgDiceTwo = findViewById(R.id.imgDiceTwo);

        //Media Player for Dice sound
        mp = MediaPlayer.create(this, R.raw.dice_sound);

        //Vibrate
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        //Array of images because drawables resource type will be an integer
        diceImages = new int[]{R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

        txtRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollClicked();
            }
        });
    }
    //TODO: On CLick For Text View Roll
    public void rollClicked() {
        //To generate random number
        Random random = new Random();
        int randNum = random.nextInt(6); //will generate from 0 to 5
        int imgValueOne = randNum + 1; //adding +1 because (randNum value starts from 0 not 1)
        imgDiceOne.setImageResource(diceImages[randNum]); //setting random index

        randNum = random.nextInt(6);//Again generate another random
        imgValueOne += randNum + 1;
        imgDiceTwo.setImageResource(diceImages[randNum]);
        txtTotal.setText("Total is : " + imgValueOne);

        //Shake animation for dices
        YoYo.with(Techniques.Shake)
                .duration(500)
                .repeat(0)
                .playOn(imgDiceOne);
        YoYo.with(Techniques.Shake)
                .duration(500)
                .repeat(0)
                .playOn(imgDiceTwo);
        //To vibrate
        vibrator.vibrate(2000);
        mp.start();//For Dice Sound
    }
}