package com.example.yoshizawarei.supermangame;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView spiderman;
    private ImageView spiderman2;
    private LinearLayout linearLayout;
    private int character;

    // Size
    private int frameHeight;
    private int boxSize;
    private int screenWidth;
    private int screenHeight;

    // Position
    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int spidermanX;
    private int spidermanY;
    private int spiderman2X;
    private int spiderman2Y;

    // Score
    private int score;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    // Status Check
    private boolean action_flg = false;
    private boolean start_flg = false;

    // BGM
    BgmPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgm = new BgmPlayer(this);
        bgm.start();

        linearLayout = findViewById(R.id.main_layout);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        box = (ImageView) findViewById(R.id.box);

        character = getIntent().getIntExtra("character", 0);
        switch (character) {
            case 0:
                box.setImageResource(R.drawable.superman);
                linearLayout.setBackgroundResource(R.drawable.bg);
                break;
            case 1:
                box.setImageResource(R.drawable.captain_america);
                linearLayout.setBackgroundResource(R.drawable.bg_captain_america);
                break;
            case 2:
                box.setImageResource(R.drawable.wolverine);
                linearLayout.setBackgroundResource(R.drawable.bg_wolverine);
                break;
            case 3:
                box.setImageResource(R.drawable.batman);
                linearLayout.setBackgroundResource(R.drawable.bg_batman);
                break;
        }

        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        spiderman = (ImageView) findViewById(R.id.spiderman);
        spiderman2 = (ImageView) findViewById(R.id.spiderman2);

        // Get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        // Move to out of screen
        orange.setX(-180);
        orange.setY(-80);
        pink.setX(-180);
        pink.setY(-80);
        spiderman.setX(-300);
        spiderman.setY(-80);
        spiderman2.setX(-300);
        spiderman2.setY(-80);

        scoreLabel.setText("Score : 0");
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm.stop();
    }

    public void changePos() {

        hitCheck();

        // Orange
        orangeX -= 12;

        if (orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        // Black
        Intent intent = getIntent();
        String level = intent.getStringExtra("LEVEL");
        if (level.equals("1")) {

            spidermanX -= 16;
            // spiderman2
            spiderman2X = -300;
            spiderman2Y = -300;

//            if (spiderman2X < 0) {
//                spiderman2X = screenWidth + 20;
//                spiderman2Y = (int) Math.floor(Math.random() * (frameHeight - spiderman2.getHeight()));
//            }
            spiderman2.setX(spiderman2X);
            spiderman2.setY(spiderman2Y);
        } else if (level.equals("2")) {

            character = getIntent().getIntExtra("character", 0);
            switch (character) {
                case 0:
                    box.setImageResource(R.drawable.superman);
                    linearLayout.setBackgroundResource(R.drawable.bg);
                    break;
                case 1:
                    box.setImageResource(R.drawable.captain_america);
                    linearLayout.setBackgroundResource(R.drawable.bg_captain_america);
                    break;
                case 2:
                    box.setImageResource(R.drawable.wolverine);
                    linearLayout.setBackgroundResource(R.drawable.bg_wolverine);
                    break;
                case 3:
                    box.setImageResource(R.drawable.batman);
                    linearLayout.setBackgroundResource(R.drawable.bg_batman);
                    break;
            }

            spidermanX -= 30;
            // spiderman2
            spiderman2X -= 12;

            if (spiderman2X < 0) {
                spiderman2X = screenWidth + 20;
                spiderman2Y = (int) Math.floor(Math.random() * (frameHeight - spiderman2.getHeight()));
            }
            spiderman2.setX(spiderman2X);
            spiderman2.setY(spiderman2Y);
        } else {

            box = (ImageView) findViewById(R.id.box);

            spidermanX -= 60;
            // spiderman2
            spiderman2X -= 40;

            if (spiderman2X < 0) {
                spiderman2X = screenWidth + 20;
                spiderman2Y = (int) Math.floor(Math.random() * (frameHeight - spiderman2.getHeight()));
            }
            spiderman2.setX(spiderman2X);
            spiderman2.setY(spiderman2Y);

        }
        if (spidermanX < 0) {
            spidermanX = screenWidth + 10;
            spidermanY = (int) Math.floor(Math.random() * (frameHeight - spiderman.getHeight()));
        }
        spiderman.setX(spidermanX);
        spiderman.setY(spidermanY);

        // Pink
        pinkX -= 25;
        if (pinkX < 0) {
            pinkX = screenWidth + 2400;
            pinkY = (int) Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);


        // Move Box
        if (action_flg == true) {
            // Touching
            boxY -= 20;
        } else {
            // Releasing
            boxY += 20;
        }

        // Check box position.
        if (boxY < 0) boxY = 0;

        if (boxY > frameHeight - boxSize) boxY = frameHeight - boxSize;

        box.setY(boxY);

        scoreLabel.setText("Score : " + score);
    }

    public void hitCheck() {

        // if the center of the ball is in the box, it counts as a hit

        // Orange
        int orangeCenterX = orangeX + orange.getWidth() / 2;
        int orangeCenterY = orangeY + orange.getHeight() / 2;

        if (0 <= orangeCenterX && orangeCenterX <= boxSize &&
                boxY <= orangeCenterY && orangeCenterY <= boxY + boxSize) {

            score += 10;
            orangeX = -10;
        }

        // Pink
        int pinkCenterX = pinkX + pink.getWidth() / 2;
        int pinkCenterY = pinkY + pink.getHeight() / 2;

        if (0 <= pinkCenterX && pinkCenterX <= boxSize &&
                boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize) {

            score += 50;
            pinkX= -10;
        }

        // spiderman
        int spidermanCenterX = spidermanX + spiderman.getWidth() / 2;
        int spidermanCenterY = spidermanY + spiderman.getHeight() / 2;
        int spiderman2CenterX = spiderman2X + spiderman2.getHeight() / 2;
        int spiderman2CenterY = spiderman2Y + spiderman2.getHeight() / 2;

        if ((0 <= spidermanCenterX && spidermanCenterX <= boxSize &&
                boxY <= spidermanCenterY && spidermanCenterY <= boxY + boxSize) ||
                (0 <= spiderman2CenterX && spiderman2CenterX <= boxSize &&
                boxY <= spiderman2CenterY && spiderman2CenterY <= boxY + boxSize)){

            // Stop Timer!!
            timer.cancel();
            timer = null;

            // Show Result
//            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//            String email = intent.getStringExtra("email");
//            intent.putExtra("email",email);
//            intent.putExtra("SCORE", score);
//            startActivity(intent);

            Intent intent = getIntent();
            String email = intent.getStringExtra("email");
            Intent intent2 = new Intent(getApplicationContext(), ResultActivity.class);
            intent2.putExtra("email",email);
            intent2.putExtra("SCORE", score);
            intent2.putExtra("character", character);
            startActivity(intent2);
        }
    }


    public boolean onTouchEvent(MotionEvent me) {

        if (start_flg == false) {

            start_flg = true;

            // Why get frame height and box height here?
            // Because the UI has not been set on the screen in OnCreate()!!

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            boxY = (int) box.getY();

            // The box is a square. (height and width are the same.)
            boxSize = box.getHeight();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }


        return true;
    }
}
