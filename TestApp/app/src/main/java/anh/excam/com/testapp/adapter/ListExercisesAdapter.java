package anh.excam.com.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import anh.excam.com.testapp.Model.ExecisesOfDay;
import anh.excam.com.testapp.R;
import anh.excam.com.testapp.activity.ContentExercisesActivity;

/**
 * Created by MyPC on 10/07/2016.
 */
public class ListExercisesAdapter extends RecyclerView.Adapter<ListExercisesAdapter.WorkOutsViewHolder> {
    public static final String KEY_NAME_EXERCISES = "NAME_EXERCISES";

    private List<ExecisesOfDay> workOutsList;
    Context context;

    public ListExercisesAdapter(List<ExecisesOfDay> workOutsList, Context context) {
        this.context = context;
        this.workOutsList = workOutsList;
    }

    @Override
    public WorkOutsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_execises, parent, false);
        return new WorkOutsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkOutsViewHolder holder, int position) {
        final ExecisesOfDay workOuts = workOutsList.get(position);
        String imageName = workOuts.getImage();
        int globeId = context.getResources().getIdentifier(imageName, "drawable",
                context.getPackageName());

        try{
            Picasso.with(context).load(globeId).resize(640, 400).into(holder.imageExercises);
        }catch (android.content.ActivityNotFoundException exception) {
            Toast.makeText(context,"Loading....!", Toast.LENGTH_LONG).show();
        }
        holder.nameExercises.setText(workOuts.getNameEx());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ContentExercisesActivity.class);
                i.putExtra(KEY_NAME_EXERCISES, workOuts.getNameEx());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workOutsList.size();
    }

    public class WorkOutsViewHolder extends RecyclerView.ViewHolder {
        TextView nameExercises;
        ImageView imageExercises;

        public WorkOutsViewHolder(View itemView) {
            super(itemView);
            nameExercises = (TextView) itemView.findViewById(R.id.titleWeek);
            imageExercises = (ImageView) itemView.findViewById(R.id.background_ex);
        }
    }
}

