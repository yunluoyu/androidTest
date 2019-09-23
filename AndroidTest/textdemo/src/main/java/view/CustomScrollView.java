package view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by yunlu on 2019/9/12.
 */

public class CustomScrollView extends ViewGroup {


    private int mScreenHeight;
    private Scroller mScroller;

    public CustomScrollView(Context context) {
        this(context,null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mScreenHeight = wm.getDefaultDisplay().getHeight();
        mScroller = new Scroller(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        // 设置ViewGroup的高度
        MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
        lp.height = mScreenHeight * childCount ;
        setLayoutParams(lp);

        for(int i = 0; i < childCount; i++){
            View cView = getChildAt(i);
            // 排放每个view的位置
            if(cView.getVisibility() != View.GONE){
                cView.layout(l, i*mScreenHeight, r, (i+1)*mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
