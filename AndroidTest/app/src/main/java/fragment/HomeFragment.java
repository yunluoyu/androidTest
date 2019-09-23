package fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yunlu.androidtest.DetailActivity;

import adapter.HomeAdapter;
import bean.AppInfo;
import utils.GsonHelper;
import utils.HttpHelper;
import utils.LogHelper;
import utils.NetUrl;

/**
 * 显示首页的fragment
 * Created by yunlu on 2019/8/30.
 */

public class HomeFragment extends BaseFragment {

    private ListView listView;

    @Override
    public void initData() {

        HttpHelper helper = HttpHelper.create();
        helper.doGet(NetUrl.HOMEURL);
        helper.setOnRequestListener(new HttpHelper.OnRequestListener() {
            @Override
            public void onSucceed(String result) {
                LogHelper.d(result);

               final AppInfo appinfo = GsonHelper.parseJsonToBean(result,AppInfo.class);
                for(AppInfo.AppMsg appmsg : appinfo.list){
                    LogHelper.d(appmsg.toString());
                }
                listView.setAdapter(new HomeAdapter(appinfo.list));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent in = new Intent(getActivity(),DetailActivity.class);
                        startActivity(in);
                    }
                });
                stateLyaout.showSuccessView();
            }

            @Override
            public void onFailed(String cause) {
                stateLyaout.showErrorView();
                LogHelper.d(cause);
            }
        });

    }

    @Override
    public View getSuccessView() {

        listView = new ListView(getActivity());
        listView.setDivider(null);

        return listView;
    }


}
