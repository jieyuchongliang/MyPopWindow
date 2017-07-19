package com.example.fj.mypopwindow.factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fj.mypopwindow.R;
import com.example.fj.mypopwindow.callback.DataCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860617010 on 2017/7/19.
 */

public class PopupWindowFactory {
    private static final int SHOW = R.id.btn_show;
    private static final int AGE = R.id.btn_age;
    private static final int NAME = R.id.btn_name;
    private static final int ADDRESS = R.id.btn_address;
    private static final int PWD = R.id.btn_pwd;
    private static final int COUNTY = R.id.btn_county;
    private static final int SEX = R.id.btn_sex;
    private static final int HEIGHT = R.id.btn_height;
    private static List<String> array;
    private static DataCallBack dataCallBack;
    private static PopupWindow popupWindow;


    public static void creatPop(DataCallBack callBack,Context context, View view) {
        dataCallBack = callBack;
        switch (view.getId()) {
            case SHOW:
                createPopWindow(context, view);
                break;
            case AGE:
                createPopWindow(context, view);
                break;
            case NAME:
                createPopWindow(context, view);
                break;
            case ADDRESS:
                createPopWindow(context, view);
                break;
            case PWD:
                createPopWindow(context, view);
                break;
            case COUNTY:
                createPopWindow(context, view);
                break;
            case SEX:
                createPopWindow(context, view);
                break;
            case HEIGHT:
                createPopWindow(context, view);
                break;
        }
    }

    private static final String TAG = "PopupWindowFactory";
    private static void createPopWindow(Context context, View view) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        //获取布局
        View contentView = LayoutInflater.from(context).inflate(R.layout.popwindow, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        initData(contentView,view);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());//没有这句话的话，点击返回键不会关闭popwindow而是关闭pop所在的activity
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setTouchable(false);
        popupWindow.setFocusable(false);
        popupWindow.showAsDropDown(view);
    }

    private static void initData(View convertView,View view) {
        ListView listView = (ListView) convertView.findViewById(R.id.list_view);
        array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(i+".." + view.getId());
        }
        listView.setAdapter(new MyAdapter());
    }

    public static void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    /**
     * popwindow是否是显示状态
     * @return
     */
    public static boolean isShowing() {
        return popupWindow.isShowing();
    }

    static class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return array.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_show);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(array.get(position));
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), finalViewHolder.textView.getText(), Toast.LENGTH_SHORT).show();
                    dataCallBack.callBack((String) finalViewHolder.textView.getText());
                    popupWindow.dismiss();
                }
            });
            return convertView;
        }
        private class ViewHolder{
            TextView textView;
        }
    }
}
