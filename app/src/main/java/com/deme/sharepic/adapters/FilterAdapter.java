package com.deme.sharepic.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.deme.sharepic.R;
import com.deme.sharepic.models.Filter;
import com.deme.sharepic.models.FilterData;
import com.deme.sharepic.utils.FilterUtil;


import java.util.ArrayList;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

import static com.deme.sharepic.utils.Constants.THUMBNAIL_SIZE;

/**
 * Created by Dima on 9/16/2018.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder> {

    Context mContext;
    ArrayList<Filter> itemData = new ArrayList<>();
    Bitmap bitmap;
    Bitmap thumbnail;
    public FilterAdapter(Context mContext, Bitmap bitmap, ArrayList<Filter> arrayData) {
        super();
        this.mContext = mContext;
        this.itemData = arrayData;
        this.bitmap = bitmap;
        this.thumbnail = Bitmap.createScaledBitmap(this.bitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);
    }
    @Override
    public FilterHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_filter, parent, false);
        FilterHolder viewHolder = new FilterHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FilterHolder holder, int position) {
        String filterTitle = itemData.get(position).getTitle();
        GPUImageFilter filter = FilterUtil.createFilterForType(mContext, itemData.get(position).getType());
        holder.title.setText(filterTitle);
        Bitmap imageBitmap;
        GPUImage image = new GPUImage(mContext);
        image.setFilter(filter);
        imageBitmap = image.getBitmapWithFilterApplied(this.thumbnail);
        holder.imFilter.setImageBitmap(imageBitmap);
    }


    @Override
    public int getItemCount() {
        return this.itemData.size();
    }

    public class FilterHolder extends RecyclerView.ViewHolder {
        public ImageView imFilter;
        public TextView title;

        public FilterHolder(View itemView) {
            super(itemView);
            imFilter = (ImageView) itemView.findViewById(R.id.btn_filter_item);
            title = (TextView) itemView.findViewById(R.id.text_filter_item);
        }
    }

}

