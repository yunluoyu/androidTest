package com.yunlu.textdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by yunlu on 2019/9/9.
 */

public class CanvasView extends View {

    private Paint mPaint;
    private Random mRandom;
    private Bitmap dog;
    private int mWidth;
    private int mHeight;
    private int mDogWidth;
    private int mDogHeight;
    private Region circleRegion;
    private Path circlePath;

    public CanvasView(Context context) {
        this(context,null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mRandom = new Random();
        dog = BitmapFactory.decodeResource(getResources(), R.mipmap.dog);

        circleRegion = new Region();
        circlePath = new Path();

        initPaint();
    }
    int w = 1;
    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL); //设置填充
        mPaint.setStrokeWidth(10f);  // 设置画笔宽度

        mPaint.setColor(Color.BLUE);

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                w++;
//                invalidate();
//            }
//        },2000,1000);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d("AAA",w+": w  h : "+h);
        circlePath.addCircle(w/2,h/2,w/2,Path.Direction.CW);
        Region region = new Region(0,0,w,h);
        circleRegion.setPath(circlePath,region);

        mWidth = w;
        mHeight = h;
        mDogWidth = dog.getWidth();
        mDogHeight = dog.getHeight();
    }

    float []point = new float[]{500f,500f,900,600f,500f,700f,800,900};
    @Override
    protected void onDraw(Canvas canvas) {
//       canvas.drawPoint(200,400,mPaint);
//       canvas.drawPoints(point,mPaint);

//        // 画线，  四个数字，确定一条直线
//        canvas.drawLine(300,300,500,600,mPaint);
//        canvas.drawLines(point,mPaint);
//
//        //绘制圆角矩形
//        RectF rect = new RectF(200,200,500,500);
//        canvas.drawRoundRect(rect,30,30,mPaint);
//
//        //绘制椭圆
//        canvas.drawOval(600,600,800,900,mPaint);

        //绘制圆弧
       // RectF rectF = new RectF(100,100,800,800);
        // 绘制背景矩形
        //mPaint.setColor(Color.GRAY);
        //canvas.drawRect(rectF,mPaint);

//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        mPaint.setStrokeWidth(15);
//        // 描边
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawArc(rectF,0,360,false,mPaint);
//
//        // 填充
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawArc(rectF,0,360,false,mPaint);
          //canvas.translate(mWidth/2,mHeight/2);
          //canvas.drawBitmap(dog,new Rect(0,0,dog.getWidth(),dog.getHeight()),new RectF(-w,-w,w,w),mPaint);

//        String str = "ABCDEFG";
//        w = w % str.length();
//        canvas.drawText(str,0,w,200,500,mPaint);

//        canvas.translate(mWidth/2,mHeight/2);
//        Path path = new Path();
//        path.lineTo(200,200);
//        path.moveTo(200,100);
//        path.lineTo(200,0);

//        canvas.drawPath(path,mPaint);
//        Path path = circlePath;
//        canvas.drawPath(path,mPaint);
        canvas.drawCircle(downX,downY,20,mPaint);
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawCircle(downX,downY,20,mPaint);




    }


    int downX;
    int downY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                invalidate();

                break;
        }
        return true;
    }
}
