package anh.excam.com.testapp.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import anh.excam.com.testapp.Model.ExecisesPlay;
import anh.excam.com.testapp.R;
import anh.excam.com.testapp.data.DatabaseAccess;

public class PlayWorkOutActivity extends AppCompatActivity {

    private final int timeOneStep = 30;
    private final int timeFinish = 0;
    private int timeStep = 30;

    private AHBottomNavigation bottomNavigation;
    private ImageView mExercises;
    private TextView mNameExercises, mTextTotalTime, mTextTime, mNameExercisesNext, mStep;
    private Timer mT;
    private AnimationDrawable myAnimationDrawable;


    private int second;
    private int mintue;
    private int positionAnimation = 0;
    private int numberAnimation;
    private int round;

    private int step = 1;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_workouts);

        bundle = this.getIntent().getExtras();
        numberAnimation = bundle.getInt(ExercisesOfDayActivity.NUMBER_EXERCISES);
        round = bundle.getInt(ExercisesOfDayActivity.KEY_ROUND);

        init();
        setAnimation(positionAnimation);
        myAnimationDrawable.stop();
        action();
    }

    public void init() {
        mNameExercises = (TextView) findViewById(R.id.tvNameExecises);
        mNameExercisesNext = (TextView) findViewById(R.id.tvNameExecisesNext);
        mTextTotalTime = (TextView) findViewById(R.id.tvTotalTime);
        mTextTime = (TextView) findViewById(R.id.tvTime);
        mStep = (TextView) findViewById(R.id.tvStep);

        mStep.setText("Step " + step + "/" + numberAnimation * round);
        mTextTotalTime.setText(mintue + ":0" + second);

        mExercises = (ImageView) findViewById(R.id.imageAnimation);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottomBar);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.Exit
                , R.drawable.ic_crop_din_black_24dp, android.R.color.black);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.Pause
                , R.drawable.ic_pause_black_24dp, android.R.color.black);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.Fast
                , R.drawable.ic_fast_forward_black_24dp, android.R.color.black);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        bottomNavigation.setBehaviorTranslationEnabled(false);
// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
// Force the titles to be displayed (against Material Design guidelines!)
        bottomNavigation.setForceTitlesDisplay(true);
// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);
// Set current item programmatically
        bottomNavigation.setCurrentItem(0);
// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
    }

    public void update() {
        mT = new Timer();
        mT.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        second++;
                        timeStep--;
                        if (second == 60) {
                            second = timeFinish;
                            mintue = mintue + 1;
                        }
                        if (timeStep == timeFinish) {
                            if (step > numberAnimation * round) {
                                finish();
                            }
                            timeStep = timeOneStep;
                            step++;
                            positionAnimation++;


                            if (positionAnimation == numberAnimation + 1) {
                                positionAnimation = 0;
                            }
                            setAnimation(positionAnimation);
                        }
                        if (second < 10) {
                            mTextTotalTime.setText(mintue + ":0" + second);
                        } else {
                            mTextTotalTime.setText(mintue + ":" + second);
                        }
                        mTextTime.setText("" + timeStep);
                        mStep.setText("Step " + step + "/" + numberAnimation * round);
                        Log.d("CC", " " + positionAnimation + "," + step + "/" + (numberAnimation * round));
                    }
                });
            }
        }, 1000, 1000);
    }

    public void action() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        finish();
                        break;
                    case 1:
                        if (!myAnimationDrawable.isRunning()) {
                            myAnimationDrawable.start();
                            update();

                        } else {
                            myAnimationDrawable.stop();
                            mT.cancel();
                        }
                        break;
                    case 2:
                        break;
                }
                return true;
            }
        });
    }

    public void setAnimation(int position) {

        String nameExercises;
        String nameExercisesNext;

        nameExercises = bundle.getString(String.valueOf(position));

        if (bundle.getString(String.valueOf(position + 1)) != null) {
            nameExercisesNext = bundle.getString(String.valueOf(position + 1));
        } else {
            nameExercisesNext = "Finish";
        }

        String textNameExercisesNext = "Next: " + nameExercisesNext;
        mNameExercises.setText(nameExercises);
        mNameExercisesNext.setText(textNameExercisesNext);
        myAnimationDrawable
                = createAnimationDrawable(nameExercises);
        mExercises.setImageDrawable(myAnimationDrawable);
        myAnimationDrawable.start();
    }

    public AnimationDrawable createAnimationDrawable(String nameExercises) {

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<ExecisesPlay> plays = databaseAccess.getEXST(nameExercises);
        databaseAccess.close();

        String image = plays.get(0).getImage();

        String[] stringArray = image.split(",");
        Resources res = this.getResources();
        AnimationDrawable newAnim = new AnimationDrawable();

        int i = 0;
        while (i < stringArray.length) {
            newAnim.addFrame(getResources().getDrawable(res.getIdentifier(stringArray[i], "drawable",
                    this.getPackageName())), 500);
            i++;
        }
        newAnim.setOneShot(false);
        return newAnim;
    }

    @Override
    protected void onPause() {
        super.onPause();
        myAnimationDrawable = null;
        if(mT != null) {
            mT.cancel();
        }
    }

}

