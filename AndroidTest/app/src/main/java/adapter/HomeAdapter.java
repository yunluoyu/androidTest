package adapter;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yunlu.androidtest.R;

import java.util.ArrayList;

import bean.AppInfo;
import butterknife.BindView;
import butterknife.ButterKnife;
import utils.HttpHelper;
import utils.NetUrl;

/**
 * Created by yunlu on 2019/9/4.
 */

public class HomeAdapter extends BaseAdapter {


    private ArrayList<AppInfo.AppMsg> list;

    public HomeAdapter(ArrayList<AppInfo.AppMsg> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.home_listview_item,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);


        }else
            vh = (ViewHolder) convertView.getTag();

        AppInfo.AppMsg info = list.get(position);

        HttpHelper.create().displayImageView(vh.homeListviewIcon, NetUrl.HOST+info.iconUrl);
        vh.homeListviewName.setText(info.name);
        vh.homeListviewStarts.setRating(info.stars);
        vh.homeListviewDesc.setText(info.name+" 走进你的生活");

        convertView.setRotationX(0);
        convertView.setScaleX(0.2f);
        convertView.setScaleY(0.2f);

        ViewCompat.animate(convertView)
                .rotationXBy(360)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator())//会让运动轨迹超过一点再回来
                .setDuration(1000)
                .start();

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.home_listview_icon)
        ImageView homeListviewIcon;
        @BindView(R.id.home_listview_name)
        TextView homeListviewName;
        @BindView(R.id.home_listview_starts)
        RatingBar homeListviewStarts;
        @BindView(R.id.home_listview_desc)
        TextView homeListviewDesc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
