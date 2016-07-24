package anh.excam.com.testapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anh.excam.com.testapp.Model.ExecisesOfDay;

import java.util.List;

import anh.excam.com.testapp.R;
import anh.excam.com.testapp.adapter.ListExercisesAdapter;
import anh.excam.com.testapp.data.DatabaseAccess;

/**
 * Created by Anh on 5/14/2016.
 */
public class FragmentExercises extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fr_exercises, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.exercises);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FragmentExercises.this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ListExercisesAdapter listExercisesAdapter = new ListExercisesAdapter(createData(), getContext());
        recyclerView.setAdapter(listExercisesAdapter);
        return view;
    }

    private List<ExecisesOfDay> createData() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();
        List<ExecisesOfDay> exercises = databaseAccess.getExecises();
        databaseAccess.close();
        return exercises;
    }
}
