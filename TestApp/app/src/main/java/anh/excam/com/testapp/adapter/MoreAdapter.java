package anh.excam.com.testapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import anh.excam.com.testapp.MainActivity;
import anh.excam.com.testapp.Model.MoreApps;
import anh.excam.com.testapp.R;
import anh.excam.com.testapp.fragment.FragmentMore;

/**
 * Created by Admin on 7/18/2016.
 */
public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MoreViewHolder> {


    private final int TYPE_ITEM_1 = 1;
    private final int TYPE_ITEM_2 = 2;


    private List<MoreApps> listMoreApps;
    private Context context;


    public MoreAdapter(Context context, List<MoreApps> listMoreApps) {
        this.context = context;
        this.listMoreApps = listMoreApps;
    }

    @Override
    public MoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_workout, parent, false);
        return new MoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreViewHolder holder, int position) {
        final MoreApps moreApps = listMoreApps.get(position);

        if (moreApps.getTYPE_ITEM() == this.TYPE_ITEM_1) {
            holder.title.setText(moreApps.getTitle());
            holder.detail.setText(moreApps.getDetail());
            Picasso.with(context).load(moreApps.getImageIcon()).resize(80,80).into(holder.image);
        } else if (moreApps.getTYPE_ITEM() == this.TYPE_ITEM_2) {
            holder.title.setText(moreApps.getTitle());
            holder.detail.setText(moreApps.getDetail());
            Picasso.with(context).load(moreApps.getImageIcon()).resize(120,120).into(holder.image);
        }


    }

    @Override
    public int getItemCount() {
        return listMoreApps.size();
    }

    public class MoreViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView detail;
        private ImageView image;

        public MoreViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textViewTitle);
            detail = (TextView) itemView.findViewById(R.id.textViewDetail);
            image = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
