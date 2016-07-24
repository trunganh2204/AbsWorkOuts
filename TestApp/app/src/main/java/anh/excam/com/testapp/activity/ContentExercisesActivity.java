package anh.excam.com.testapp.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import anh.excam.com.testapp.Model.ExecisesPlay;
import anh.excam.com.testapp.R;
import anh.excam.com.testapp.adapter.ListExercisesAdapter;
import anh.excam.com.testapp.data.DatabaseAccess;

public class ContentExercisesActivity extends AppCompatActivity{
    private ImageView imageAnimation;
    private String imageExercises;
    private String description;
    private String nameExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_execises);
        init();
        createAnimation();
    }

    public void init() {
        TextView nameExercises = (TextView) findViewById(R.id.nameExercises);
        TextView contentExercises = (TextView) findViewById(R.id.description);
        ImageButton btBack = (ImageButton) findViewById(R.id.back_play_ex);
        imageAnimation = (ImageView) findViewById(R.id.animation);

        getData();

        nameExercises.setText(this.nameExercises);
        contentExercises.setText(description);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getData() {

        Intent i = getIntent();
        String nameEx = i.getStringExtra(ListExercisesAdapter.KEY_NAME_EXERCISES);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<ExecisesPlay> plays = databaseAccess.getEXST(nameEx);
        databaseAccess.close();

        nameExercises = plays.get(0).getTitle();
        imageExercises = plays.get(0).getImage();
        description = plays.get(0).getDescription();
    }

    private void createAnimation() {
        AnimationDrawable myAnimationDrawable
                = animationByName();
        imageAnimation.setImageDrawable(myAnimationDrawable);
        myAnimationDrawable.start();
    }

    private AnimationDrawable animationByName() {
        AnimationDrawable newAnim = new AnimationDrawable();
        String[] stringArray = imageExercises.split(",");
        int count = 0;
        while (count < stringArray.length) {
            newAnim.addFrame(getResources().getDrawable(getResources().getIdentifier(stringArray[count], "drawable",
                    this.getPackageName()),null), 500);
            count++;
        }
        newAnim.setOneShot(false);
        return newAnim;
    }
}