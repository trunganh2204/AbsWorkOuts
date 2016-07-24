package anh.excam.com.testapp;

import android.content.Intent;
import android.graphics.Color;


import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import anh.excam.com.testapp.activity.ExercisesOfDayActivity;
import anh.excam.com.testapp.adapter.ViewPagerAdapter;
import anh.excam.com.testapp.fragment.FragmentMore;
import anh.excam.com.testapp.fragment.FragmentWorkOuts;

public class MainActivity extends AppCompatActivity implements FragmentWorkOuts.RCVOnItemClick, FragmentMore.onItemMoreClick {

    public static final String KEY_POSITION_ITEM_FRAGMENT_WORKOUTS = "POSITION_ITEM_FRAGMENT_WORKOUTS";
    private final String TITLE_FRAGMENT_WORKOUTS = "WORKOUTS";
    private final String TITLE_FRAGMENT_EXECISES = "EXECISES";
    private final String TITLE_FRAGMENT_SETTING = "SETTING";
    private final String TITLE_FRAGMENT_MORE = "MORE";

    private ViewPager viewPager;
    private AHBottomNavigation bottomNavigation;
    private TextView titleFragment;

    private String[] listLink = {
            "https://play.google.com/store/search?q=abs%20workout",
            "mailto:" + "trunganh2204@gmail.com",
            "https://play.google.com/store/apps/details?id=me.akhil.barbellplates",
            "https://play.google.com/store/apps/details?id=com.tr3x.armworkout",
            "https://play.google.com/store/apps/details?id=com.runtastic.android.legtrainer.lite",
            "https://play.google.com/store/apps/details?id=com.dailyyoga.inc",
            "https://play.google.com/store/apps/details?id=com.northpark.squats",
            "https://play.google.com/store/apps/details?id=com.andromo.dev522101.app483207",
            "https://play.google.com/store/apps/details?id=com.shvagerfm.Burpy",
            "https://www.facebook.com/sharer/sharer.php?u="
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottombar);
        titleFragment = (TextView) findViewById(R.id.titltBottomBar);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.WorkOuts
                , R.drawable.ic_alarm_black_24dp, R.color.colorBlack);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.Exercises
                , R.drawable.ic_chrome_reader_mode_black_24dp, R.color.colorBlack);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.Setting
                , R.drawable.ic_settings_black_24dp, R.color.colorBlack);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.more
                , R.drawable.ic_error_outline_black_24dp, R.color.colorBlack);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.setBehaviorTranslationEnabled(false);
// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
// Force the titles to be displayed (against Material Design guidelines!)
        bottomNavigation.setForceTitlesDisplay(true);
// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);
// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
//ham bat su kien
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position);
                switch (position) {
                    case 0:
                        titleFragment.setText(TITLE_FRAGMENT_WORKOUTS);
                        break;
                    case 1:
                        titleFragment.setText(TITLE_FRAGMENT_EXECISES);
                        break;
                    case 2:
                        titleFragment.setText(TITLE_FRAGMENT_SETTING);
                        break;
                    case 3:
                        titleFragment.setText(TITLE_FRAGMENT_MORE);
                        break;
                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void clickItemFragmentWorkOuts(int position) {
        Intent intent = new Intent(MainActivity.this, ExercisesOfDayActivity.class);

        intent.putExtra(KEY_POSITION_ITEM_FRAGMENT_WORKOUTS, position);
        startActivity(intent);
        finish();
    }

    private String url;
    private final String CONTENT_EMAIL = "Your_text";

    @Override
    public void changeViewPagerItemMore(int position) {


        switch (position) {
            case 0:
                url = listLink[0];
                break;
            case 5:
                url = listLink[9];
                break;
            case 9:
                url = listLink[2];
                break;
            case 10:
                url = listLink[3];
                break;
            case 11:
                url = listLink[4];
                break;
            case 12:
                url = listLink[5];
                break;
            case 13:
                url = listLink[6];
                break;
            case 14:
                url = listLink[7];
                break;
            case 15:
                url = listLink[8];
                break;
        }

        if (url != null && position != 2) {
            Uri uri = Uri.parse(url);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        }

        if (position == 2) {
            url = listLink[1];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "trunganh2204@gmail.com"));

            try {
                intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                intent.putExtra(Intent.EXTRA_TEXT, CONTENT_EMAIL);
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException exception) {
                Toast.makeText(this, "No email clients installed on device!", Toast.LENGTH_LONG).show();
            }
        }

    }
}


