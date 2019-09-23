package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yunlu on 2019/9/19.
 */

public class FragmentA extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView tv  = new TextView(getActivity());
        tv.setText("Fragment A");
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.parseColor("#FF0000"));

        return tv;
    }
}
