package com.blunderer.materialdesignlibrary.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerItemBottom;

import java.util.List;

public class NavigationDrawerBottomAdapter extends ArrayAdapter<NavigationDrawerItemBottom> {

    private int mLayoutResourceId;
    private int mCount;

    public NavigationDrawerBottomAdapter(Context context,
                                         int layoutResourceId,
                                         List<NavigationDrawerItemBottom> objects) {
        super(context, layoutResourceId, objects);

        mLayoutResourceId = layoutResourceId;
        mCount = objects.size();
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(mLayoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.navigation_drawer_row_title);
            holder.icon = (ImageView) convertView.findViewById(R.id.navigation_drawer_row_icon);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        NavigationDrawerItemBottom tool = getItem(position);

        if (tool.useTitleResource()) {
            holder.title.setVisibility(View.VISIBLE);
            try {
                holder.title.setText(tool.getTitleResource());
            } catch (Resources.NotFoundException e) {
                holder.title.setText("");
            }
        }

        if (tool.useIconResource()) {
            try {
                holder.icon.setImageResource(tool.getIconResource());
                holder.icon.setVisibility(View.VISIBLE);
            } catch (Resources.NotFoundException e) {
                holder.icon.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

    private class ViewHolder {

        private TextView title;
        private ImageView icon;

    }

}
