package anh.excam.com.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import anh.excam.com.testapp.R;
import anh.excam.com.testapp.Model.DataWeek;


public class ListWeekAdapter extends RecyclerView.Adapter<ListWeekAdapter.WorkOutsViewHolder> {

    private List<DataWeek> dataWeekInfoList;
    private Context context;

    public ListWeekAdapter(List<DataWeek> dataWeekList, Context context) {
        this.dataWeekInfoList = dataWeekList;
        this.context = context;
    }

    @Override
    public WorkOutsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_week, parent, false);
        return new WorkOutsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkOutsViewHolder holder, final int position) {
        final DataWeek dataWeekInfo = dataWeekInfoList.get(position);
        holder.title.setText(dataWeekInfo.getWeek());
        holder.week.setText(dataWeekInfo.getTitle());
        holder.content.setText(dataWeekInfo.getContent());
        Picasso.with(context).load(dataWeekInfo.getBackground()).resize(1280,500).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataWeekInfoList.size();
    }

    public class WorkOutsViewHolder extends RecyclerView.ViewHolder {
        protected TextView title, week,content;
        private ImageView imageView;

        public WorkOutsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleWeek);
            week = (TextView) itemView.findViewById(R.id.week);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            content = (TextView) itemView.findViewById(R.id.contentWeek);
        }
    }


}

