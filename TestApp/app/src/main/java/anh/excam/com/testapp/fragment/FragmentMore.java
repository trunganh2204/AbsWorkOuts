package anh.excam.com.testapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import anh.excam.com.testapp.Model.MoreApps;
import anh.excam.com.testapp.R;
import anh.excam.com.testapp.Support.RecyclerItemClickListener;
import anh.excam.com.testapp.adapter.MoreAdapter;



/**
 * Created by Anh on 5/14/2016.
 */
public class FragmentMore extends Fragment {

    private List<MoreApps> result;


    private   final int TYPE_ITEM_1 = 1;
    private   final int TYPE_ITEM_2 = 2;





    private onItemMoreClick mCallback;
    public interface onItemMoreClick{
        public void changeViewPagerItemMore(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (onItemMoreClick) activity;

    }

    public FragmentMore (){

    }

    private List<MoreApps> createListMore() {
        result = new ArrayList<>();
        result.add(new MoreApps(this.TYPE_ITEM_1,10,"More usefull apps",null));
        result.add(new MoreApps(this.TYPE_ITEM_1,10,"Feedback",null));
        result.add(new MoreApps(this.TYPE_ITEM_1,10,"Send email to developer",null));
        result.add(new MoreApps(this.TYPE_ITEM_1,10,"Rate this app",null));
        result.add(new MoreApps(this.TYPE_ITEM_1,10,"Share With Friends",null));

        result.add(new MoreApps(this.TYPE_ITEM_1,R.drawable.image_facebook,"Facebook",null));
        result.add(new MoreApps(this.TYPE_ITEM_1,R.drawable.image_instagram,"Instagram",null));
        result.add(new MoreApps(this.TYPE_ITEM_1,R.drawable.image_twitter,"Twitter",null));

        result.add(new MoreApps(this.TYPE_ITEM_1,10,"WORKOUT SERIES",null));

        result.add(new MoreApps(this.TYPE_ITEM_2,R.drawable.barbell, "Barbell", "If you're just getting tired repeating the same kettlebell exercises and you want to learn new workout moves."));
        result.add(new MoreApps(this.TYPE_ITEM_2,R.drawable.arm_workout, "Arm workout", "Workouts for arms and shoulder. Bodyweight, dumbbels, kettlebells, horizontal bar."));
        result.add(new MoreApps(this.TYPE_ITEM_2,R.drawable.leg_workout, "Leg workout", "80 exercises and 120 workouts for all the muscles of the legs."));
        result.add(new MoreApps(this.TYPE_ITEM_2,R.drawable.daily_yoga, "Daily yoga", "Let's do Daily Yoga  Lose weight, shape your perfect body on your mobile studio"));
        result.add(new MoreApps(this.TYPE_ITEM_2,R.drawable.squats, "Squats", "8 week program of functional training with a focus on squats"));
        result.add(new MoreApps(this.TYPE_ITEM_2,R.drawable.kettlebell, "Kettlebell", "More than 150 training and exercises with kettlebell "));
        result.add(new MoreApps(this.TYPE_ITEM_2,R.drawable.burpee, "Burpee", "contain very exercises for leg and finger"));
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_more, container, false);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RecycleListApps);
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager linearLayout = new LinearLayoutManager(FragmentMore.this.getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);

        MoreAdapter moreAdapter  = new MoreAdapter(getContext(), createListMore());
        recyclerView.setAdapter(moreAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mCallback.changeViewPagerItemMore(position);
            }
        }));

//        LinearLayout linearLayoutSendEmail = (LinearLayout) view.findViewById(R.id.linearSendEmail);
//        linearLayoutSendEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "trunganh2204@gmail.com"));
//                intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
//                intent.putExtra(Intent.EXTRA_TEXT, CONTENT_EMAIL);
//                try {
//                    startActivity(intent);
//                } catch (android.content.ActivityNotFoundException exception) {
//                    Toast.makeText(FragmentMore.this.getActivity(), "No email clients installed on device!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        LinearLayout linearLayoutMoreUsefullApps = (LinearLayout)view.findViewById(R.id.linearMoreUsefullApps);
//        linearLayoutMoreUsefullApps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=abs%20workout"));
//                try{
//                    startActivity(browserIntent);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        });

        return view;
    }

}
