package anh.excam.com.testapp.fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import anh.excam.com.testapp.R;
import anh.excam.com.testapp.adapter.ListWeekAdapter;
import anh.excam.com.testapp.Model.DataWeek;
import anh.excam.com.testapp.Support.*;


/**
 * Created by Anh on 5/14/2016.
 */

public class FragmentWorkOuts extends Fragment {

    public RCVOnItemClick callBack;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callBack =(RCVOnItemClick)activity;
    }
    public FragmentWorkOuts() {
    }

    private List<DataWeek> createList() {
        List<DataWeek> result = new ArrayList<>();
        result.add(new DataWeek(R.drawable.section_week1,"Week 1","Full ahead","It's just beginning.And for the new ones these workouts aer frist but confident steps on the way to strong and beautiful abs."));
        result.add(new DataWeek(R.drawable.section_week2,"Week 2","Get your skates on","The first week was only warm up.The level of workouts increases gradually.And remember,if it's difficult for you to perfoem exercises,stop,have a rest and continue."));
        result.add(new DataWeek(R.drawable.section_week3,"Week 3","Fly into a rage","Progress is progress whether it be fast or slow, a little or a lot.A good abs or beautiful body - it's a long way.Don't give up, no matter what."));
        result.add(new DataWeek(R.drawable.section_week4,"Week 4","Full blast","It's almost month of trainning behind you.Are you ready to have this test of character and go all the way?"));
        result.add(new DataWeek(R.drawable.section_week5,"Week 5","Fuel to the fire","Your bidy becomes stronger and it means that your workouts have to be more ddifficult and harder.Real development lies beyond the comfort zone."));
        result.add(new DataWeek(R.drawable.section_week6,"Week 6","Bear with some thing","5 weeks of workouts are behind of you,it's a good beginning.And if you ever want to give up just remember why you started.And move again - day by day , step by step."));
        result.add(new DataWeek(R.drawable.section_week7,"Week 7","Breaking the neck","You went a long way .Workouts are becoming much harder, you have to put more effort, your abs burning.Futher-more..."));
        result.add(new DataWeek(R.drawable.section_week8,"Week 8","Soild as a rock","Congraulations! You went all the workout's way and came to the most difficult level.Here you find the test not only for your mind."));
        return result;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fr_workouts, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_workout);
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FragmentWorkOuts.this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ListWeekAdapter listWeekAdapter = new ListWeekAdapter(createList(), getContext());
        recyclerView.setAdapter(listWeekAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        callBack.clickItemFragmentWorkOuts(position);
                      }
                })
        );


        return view;

    }
    public interface RCVOnItemClick{
        void clickItemFragmentWorkOuts(int position);
    }


}



