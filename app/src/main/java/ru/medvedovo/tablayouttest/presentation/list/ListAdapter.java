package ru.medvedovo.tablayouttest.presentation.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.medvedovo.tablayouttest.App;
import ru.medvedovo.tablayouttest.R;
import ru.medvedovo.tablayouttest.data.dataclass.Item;
import ru.medvedovo.tablayouttest.data.dataclass.local.DataItem;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.PositionHolder> {

    public List<Item> data = new ArrayList<>();
    private IClickListener clickListener;

    ListAdapter(IClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public PositionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_position, parent, false);
        return new PositionHolder(view);
    }

    @Override
    public void onBindViewHolder(PositionHolder holder, int position) {
        if (data == null || data.size() == 0) {
            return;
        }

        Item item = data.get(position);
        Glide.with(App.INSTANCE.getApplicationContext())
                .load(item.url)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(holder.image);
        holder.number.setText(String.format(Locale.getDefault(), "%d", position + 1));
        holder.text.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    interface IClickListener {
        void onItemClick(DataItem item);
    }

    class PositionHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView number;
        TextView text;

        PositionHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.positionImage);
            number = itemView.findViewById(R.id.positionNumber);
            text = itemView.findViewById(R.id.positionText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataItem object = new DataItem();
                    Item item = data.get(getLayoutPosition());
                    object.url = item.url;
                    object.number = String.format(Locale.getDefault(), "%d", getLayoutPosition() + 1);
                    object.text = item.title;
                    clickListener.onItemClick(object);
                }
            });
        }
    }
}
