package view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.yunlu.textdemo.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yunlu on 2019/9/13.
 */

public class GuideView extends ViewGroup{

    private int mHeight,mWidth;
    private int mChildCount;
    private int mLineSpace = 50;
    private int mWidthSpace = 50;
    private int noPaddingWidth;
    private ArrayList<Line>lineList = new ArrayList<>();


    public GuideView(Context context) {
        this(context,null);
    }

    public GuideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHeight = 0;
        mWidth = 0;


    }
    Line line = new Line();
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth =  MeasureSpec.getSize(widthMeasureSpec);
        noPaddingWidth = mWidth -getPaddingRight()-getPaddingLeft();
        mHeight = 0;


        // 重点，因为onMeasure 会调用很多次，所以每次都必须clear一下
        if(lineList.size() > 0)
            lineList.clear();
        if(line != null)
            line.clear();
        mChildCount = getChildCount();

        for(int i = 0 ; i < mChildCount; i++){
            View child = getChildAt(i);
            child.measure(0,0);
            int current = line.right;
            // 当当前行可以塞下时，直接加入
            if(current+child.getMeasuredWidth()+mWidthSpace < noPaddingWidth){
                line.addViewToLine(child);
            }
            // 当当前行可以无法塞下时，重新new一个新行
            if(current+child.getMeasuredWidth()+mWidthSpace >= noPaddingWidth){
                lineList.add(line);
                line = new Line();
                line.addViewToLine(child);
            }
        }
        lineList.add(line);

        for(Line line : lineList){
            mHeight += line.lineHeight;
        }

        mHeight = mHeight + getPaddingTop() + getPaddingBottom() + (lineList.size()-1)*mLineSpace;
        setMeasuredDimension(mWidth,mHeight);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(lineList.size() > 0){
            int bottom;
            int top = getPaddingTop();
            Log.d("AAA","szie = " + lineList.size());

            for(Line line : lineList){
                int space = (noPaddingWidth - line.right)/line.childViews.size();

                int left = getPaddingLeft();
                int right;
                bottom = top + line.lineHeight;

                for(int i = 0; i < line.childViews.size() ;i++){
                    View child = line.childViews.get(i);
                    Log.d("AAA",child.getPaddingRight()+":"+child.getPaddingLeft());

                    child.setPadding(child.getPaddingLeft()+space/2,child.getPaddingTop(),child.getPaddingRight()+space/2,child.getPaddingBottom());
                    child.measure(0,0);
                     right = left+child.getMeasuredWidth();
                     child.layout(left, top, right,bottom);
                     //Log.d("AAA",child.getPaddingRight()+":"+child.getPaddingLeft());
                     left = right + mWidthSpace;
                     child.setClickable(true);
                     child.setBackground(getRandomDrawable());
                }
                top =  bottom + mLineSpace;
            }
        }
    }


    /*
    * 每一行需要的子view对象
    * */
    private class Line{
        ArrayList<View>childViews = new ArrayList<>();
        int lineHeight = 0;
        int right = 0;
        int left = 0;

        public void addViewToLine(View v){
            if(!childViews.contains(v)){
                childViews.add(v);
                //更新lineWidth
                if(childViews.size()==1){
                    //说明是第一个View，那么line的宽就是它的宽
                    right += v.getMeasuredWidth();
                }else {
                    //说明不是第一个，应该在line宽度的基础上+view的宽+水平间距
                    right += v.getMeasuredWidth() + mWidthSpace;
                }
                //更新lineHeight
                lineHeight = v.getMeasuredHeight();
            }
        }
        public void clear(){
            if(childViews.size() > 0)
                childViews.clear();
            lineHeight = 0;
            right = 0;
            left = 0;
        }
    }

    /*
    * 随机生成Drawbke
    * */

    private StateListDrawable getRandomDrawable(){
        StateListDrawable stateListDrawable = new StateListDrawable();
        Random random = new Random();

        int r = random.nextInt(150)+20;
        int g = random.nextInt(150)+20;
        int b = random.nextInt(150)+20;

        // 动态生成Shape
        GradientDrawable normal = new GradientDrawable();
        normal.setShape(GradientDrawable.RECTANGLE);
        normal.setColor(Color.rgb(r,g,b));
        normal.setCornerRadius(getResources().getDimension(R.dimen.dp12));

        GradientDrawable pressed = new GradientDrawable();
        pressed.setShape(GradientDrawable.RECTANGLE);
        pressed.setColor(Color.rgb(r+30,g+30,b+40));
        pressed.setCornerRadius(getResources().getDimension(R.dimen.dp12));

        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},pressed);
        stateListDrawable.addState(new int[]{},normal);

        return stateListDrawable;
    }


    private  OnGuideClickListener lis;
    public void setOnGuideClickListener(OnGuideClickListener ll){

        this.lis = ll;
        // 给子View绑定监听器
        for(int i = 0;i < getChildCount(); i++){
            View v = getChildAt(i);
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(lis!=null)
                        lis.onClick(v);
                }
            });
        }

    }



    public interface OnGuideClickListener{
        void onClick(View v);
    }

}
