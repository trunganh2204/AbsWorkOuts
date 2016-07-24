package anh.excam.com.testapp.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import anh.excam.com.testapp.MainActivity;
import anh.excam.com.testapp.Model.DataWorkOut;
import anh.excam.com.testapp.Model.ExecisesOfDay;
import anh.excam.com.testapp.R;
import anh.excam.com.testapp.adapter.ListExercisesAdapter;
import anh.excam.com.testapp.data.DatabaseAccess;

public class ExercisesOfDayActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_NAME_EXECISES_1 = "EXECISES_1";
    public static final String KEY_NAME_EXECISES_2 = "EXECISES_2";
    public static final String KEY_NAME_EXECISES_3 = "EXECISES_3";
    public static final String KEY_ROUND = "ROUND";

    private final String MONDAY = "Monday";
    private final String TUESDAY = "Tuesday";
    private final String WEDNESDAY = "Wednesday";
    private final String THURSDAY = "Thursday";
    private final String FRIDAY = "Friday";
    private final String SATURDAY = "Saturday";
    private final String SUNDAY = "Sunday";

    private final int POSITION_MONDAY = 1;
    private final int POSITION_TUESDAY = 2;
    private final int POSITION_WEDNESDAY = 3;
    private final int POSITION_THURSDAY = 4;
    private final int POSITION_FRIDAY = 5;
    private final int POSITION_SATURDAY = 6;
    private final int POSITION_SUNDAY = 7;

    public static final String NUMBER_EXERCISES = "NUMBER_EXERCISES";


    private TextView contentWeek, week, day;
    private ImageButton btBack, btAdd, btReduced, btStart;
    private TextView tvRound;
    private TabLayout mTabLayout;
    private List<ExecisesOfDay> listResult;
    private RecyclerView recyclerView;
    private int positionWeek;
    private int round = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execises_of_day);
        init();

        Intent i = getIntent();
        positionWeek = i.getIntExtra(MainActivity.KEY_POSITION_ITEM_FRAGMENT_WORKOUTS, -1) + 1;

        getDataDay(POSITION_MONDAY);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int positionDay = tab.getPosition() + 1;
                getDataDay(positionDay);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void init() {
        contentWeek = (TextView) findViewById(R.id.titleWeek);
        week = (TextView) findViewById(R.id.tvWeek);
        day = (TextView) findViewById(R.id.tvDay);


        btStart = (ImageButton) findViewById(R.id.btStart);
        btBack = (ImageButton) findViewById(R.id.btnBack);
        tvRound = (TextView) findViewById(R.id.txtRound);
        btAdd = (ImageButton) findViewById(R.id.add);
        btReduced = (ImageButton) findViewById(R.id.ic_remove);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_fragment_workout);

        tvRound.setText(round + "Round");

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExercisesOfDayActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        btReduced.setOnClickListener(this);
        btStart.setOnClickListener(this);
        btBack.setOnClickListener(this);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btStart:

                int count = 0;

                Intent intent = new Intent(ExercisesOfDayActivity.this, PlayWorkOutActivity.class);
                Bundle bundle = new Bundle();
                while (count < listResult.size()) {
                    bundle.putString(String.valueOf(count), listResult.get(count).getNameEx());
                    Log.d("Count", "" + listResult.get(count).getNameEx());
                    Log.d("Count", "" + listResult.size());
                    count++;
                }
                bundle.putInt(this.NUMBER_EXERCISES, listResult.size());
                bundle.putInt(KEY_ROUND,round);

                intent.putExtras(bundle);

                this.startActivity(intent);
                break;
            case R.id.btnBack:
                Intent mIntent = new Intent(ExercisesOfDayActivity.this, MainActivity.class);
                startActivity(mIntent);
                break;
            case R.id.add:
                round++;
                tvRound.setText(round + " Round");
                break;
            case R.id.ic_remove:
                if (round > 1) {
                    round--;
                    tvRound.setText(round + " Round");
                }
                break;
        }
    }


    public void getDataDay(int positionDay) {

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        listResult = databaseAccess.getExecisesDay(positionWeek, positionDay);
        List<DataWorkOut> dataWorkOuts = databaseAccess.getDataWorkOut(positionWeek);
        databaseAccess.close();

        contentWeek.setText(dataWorkOuts.get(0).getName());
        week.setText(dataWorkOuts.get(0).getPosition());


        ListExercisesAdapter listExercisesAdapter = new ListExercisesAdapter(listResult, ExercisesOfDayActivity.this);
        recyclerView.setAdapter(listExercisesAdapter);

        switch (positionDay) {
            case POSITION_MONDAY:
                day.setText(MONDAY);
                break;
            case POSITION_TUESDAY:
                day.setText(TUESDAY);
                break;
            case POSITION_WEDNESDAY:
                day.setText(WEDNESDAY);
                break;
            case POSITION_THURSDAY:
                day.setText(THURSDAY);
                break;
            case POSITION_FRIDAY:
                day.setText(FRIDAY);
                break;
            case POSITION_SATURDAY:
                day.setText(SATURDAY);
                break;
            case POSITION_SUNDAY:
                day.setText(SUNDAY);
                break;
            default:
                break;
        }

    }

}
