package io.github.tripguider.jsonp.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.tripguider.jsonp.R;

/**
 * Created by eduard on 23.02.2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<ListItems> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public Adapter(List<ListItems> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItems items = list.get(position);
        holder.title.setText(items.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
