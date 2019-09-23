package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yunlu on 2019/9/12.
 */

public class RemoteControlMenu extends View{

    private Path upPath;
    private Path downPath;
    private Path leftPath;
    private Path rightPath;
    private Path centerPath;
    private Paint mDefaultPaint;
    int CENTER = 0;
    int UP = 1;
    int RIGHT = 2;
    int DOWN = 3;
    int LEFT = 4;
    int touchFlag = -1;
    int currentFlag = -1;


    int mDefauColor = 0xFF4E5268;
    int mTouchedColor = 0xFFDF9C81;
    private Region upRegion;
    private Region downRegion;
    private Region rightRegion;
    private Region leftRegion;
    private int mWidth;
    private int mHeight;


    public RemoteControlMenu(Context context) {
        this(context,null);
    }

    public RemoteControlMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RemoteControlMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        upPath = new Path();
        downPath = new Path();
        leftPath = new Path();
        rightPath = new Path();
        centerPath = new Path();

        upRegion = new Region();
        downRegion = new Region();
        rightRegion = new Region();
        leftRegion = new Region();

        mDefaultPaint = new Paint();
        mDefaultPaint.setColor(mDefauColor);
        mDefaultPaint.setAntiAlias(true);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        int minWidth = w > h ? h:w ;
        minWidth *= 0.8;
        int br = minWidth / 2 ;

        Region globalRegion = new Region(-w,-h,w,h);
        RectF bigCircle = new RectF(-br,-br,br,br);
        RectF smallCircle = new RectF((-br/2),(-br/2),br/2,br/2);
        rightPath.addArc(bigCircle, -40, 84);
        rightPath.arcTo(smallCircle, 40, -84);
        rightPath.close();
        rightRegion.setPath(rightPath, globalRegion);


       // leftPath.addArc();



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        mDefaultPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(rightPath,mDefaultPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
