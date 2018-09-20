package com.deme.sharepic.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.deme.sharepic.R;
import com.deme.sharepic.models.AlbumData;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dima on 9/16/2018.
 */

public class GalleryAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Activity activity;
    private ArrayList<AlbumData> arrayList;
    public GalleryAdapter(Activity context, ArrayList<AlbumData> array) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = context;
        this.arrayList = array;
    }

    public int getCount() {
        return arrayList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        GalleryAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new GalleryAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.item_grid_main, null);
            holder.imageview = (ImageView) convertView.findViewById(R.id.image_grid_main_item);

            convertView.setTag(holder);
        } else {
            holder = (GalleryAdapter.ViewHolder) convertView.getTag();
        }

        Bitmap bitmap = arrayList.get(position).getBitmap();
        if (bitmap != null) {
            holder.imageview.setImageBitmap(bitmap);
        } else {
            holder.imageview.setImageResource(R.drawable.question_mark);
        }
        return convertView;
    }


    class ViewHolder {
        ImageView imageview;
    }
}
