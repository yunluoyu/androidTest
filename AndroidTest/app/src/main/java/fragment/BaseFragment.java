package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import view.StateLayout;

/**
 * Created by yunlu on 2019/8/30.
 */

public abstract class BaseFragment extends Fragment implements StateLayout.OnReloadListener {

    protected StateLayout stateLyaout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(stateLyaout == null){
            stateLyaout = new StateLayout(getActivity());
            View successView = getSuccessView();
            stateLyaout.bindSucessView(successView);

            stateLyaout.setOnReloadListener(this);

            stateLyaout.showLoadingView();

            initData();
        }else{

            stateLyaout.showSuccessView();

        }
        return stateLyaout;
    }


    @Override
    public void onReload() {
        initData();
    }

    public abstract void initData();
    public abstract View getSuccessView();


}
