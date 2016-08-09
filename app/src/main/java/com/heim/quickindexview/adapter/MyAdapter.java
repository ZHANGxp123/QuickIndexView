package com.heim.quickindexview.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heim.quickindexview.R;
import com.heim.quickindexview.bean.Person;

import java.util.List;

/**
 * Created by zxp on 2016/8/9 0009.
 */
public class MyAdapter extends BaseAdapter {

    private List<Person> mList;

    public MyAdapter(List<Person> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.listview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String currentLetter = mList.get(position).letter; //当前首字母

        String letter = null;  //默认首字母为空
        if (position == 0) {
            letter = currentLetter;
        }else{
            String preLetter = mList.get(position - 1).letter; //上一个的首字母
            //上一个的首字跟当前的首字母不同 就让索引显示
            if (!TextUtils.equals(preLetter, currentLetter)) {
                letter = currentLetter;
            }
        }
        holder.tv_index.setVisibility(letter==null?View.GONE:View.VISIBLE);
        holder.tv_index.setText(mList.get(position).letter);
        holder.tv_name.setText(mList.get(position).name);

        return convertView;
    }

    static class ViewHolder {
        public TextView tv_index;
        public TextView tv_name;

        public ViewHolder(View view) {
            tv_index = (TextView) view.findViewById(R.id.tv_index);
            tv_name = (TextView) view.findViewById(R.id.tv_name);

        }

    }
}
