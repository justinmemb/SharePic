package com.deme.sharepic.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.deme.sharepic.R;
import com.deme.sharepic.models.Sticker;
import com.deme.sharepic.utils.StickerUtil;

import java.util.ArrayList;

/**
 * Created by Dima on 9/16/2018.
 */

public class RecentStickerAdapter extends RecyclerView.Adapter<RecentStickerAdapter.RecentHolder>{
    Context mContext;
    ArrayList<Sticker> itemData = new ArrayList<>();
    public Bitmap bitmap;

    public RecentStickerAdapter(Context mContext, ArrayList<Sticker> arrayData) {
        super();
        this.mContext = mContext;
        this.itemData = arrayData;
    }
    @Override
    public RecentStickerAdapter.RecentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_recent_sticker , parent, false);
        RecentStickerAdapter.RecentHolder viewHolder = new RecentStickerAdapter.RecentHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecentStickerAdapter.RecentHolder holder, final int position) {
        bitmap = StickerUtil.getBitmapFromAsset(mContext, itemData.get(position).filePath);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeFile(itemData.get(position).filePath); /// is downloaded stickers
        }
        holder.image.setImageBitmap(bitmap);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                    dropListener.onDropSticker(position,bitmap);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(null, shadowBuilder, v, 0);
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.itemData.size();
    }

    public class RecentHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public RecentHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_grid_main_recent);
        }
    }

    OnDropListener dropListener;
    public void setOnDropListener(OnDropListener dropListener){this.dropListener = dropListener;}
    public interface OnDropListener {
        public void onDropSticker(int position, Bitmap bitmap);
    }
}
