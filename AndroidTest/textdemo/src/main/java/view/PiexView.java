package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import bean.PiexData;

/**
 * Created by yunlu on 2019/9/9.
 */

public class PiexView extends View {

    private Paint mPaint = new Paint();
    private Random mRandom = new Random();
    private ArrayList<PiexData> mList = new ArrayList<>();

    private float mStartAngle = 0;

    private int mHight;
    private int mWidth;

    public PiexView(Context context) {
        this(context,null);
    }

    public PiexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PiexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    //测量view的大小 暂时不用
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    // 确定view的大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHight = h;
        mWidth = w;
    }

    // 确定子View的布局， 无子View, 暂时不用
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    // 实际绘制的内容
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mStartAngle = 0;
        if(mList != null){
            //
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.GREEN);
            int r = 300;
            canvas.translate(mWidth/2,mHight/2);
            RectF rectF = new RectF(-r,-r,r,r);
            canvas.drawRoundRect(rectF,r,r,mPaint);

            mPaint.setStyle(Paint.Style.FILL);
            for(int i = 0; i < mList.size(); i++){
                PiexData data = mList.get(i);
                mPaint.setColor(data.getColor());
                Log.d("AAA","现在 = "+mStartAngle+"  绘图"+(mStartAngle+data.getAngle()));
                canvas.drawArc(rectF,mStartAngle,data.getAngle(),true,mPaint);
                mStartAngle += data.getAngle();
            }
        }

    }

    public void addData(PiexData data){

        mList.add(data);
        initData();
        invalidate();
    }
    public void initData(){
        if(mList == null)
            return;

        float sumValue = 0;
        for(int i = 0; i < mList.size(); i++){
            PiexData data = mList.get(i);
            sumValue += data.getValue();
        }
        Log.d("AAA",sumValue+" ");
        for(int i = 0; i < mList.size(); i++){
            PiexData data = mList.get(i);
            data.setAngle((data.getValue() / sumValue)*360);
        }
        for(int i = 0; i < mList.size(); i++){
            PiexData data = mList.get(i);
            Log.d("AAA","第"+i+"个 = "+data.getAngle());
        }


    }


}
