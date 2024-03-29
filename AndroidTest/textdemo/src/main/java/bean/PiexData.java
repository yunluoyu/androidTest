package bean;

import android.graphics.Color;
import android.support.annotation.NonNull;

import java.util.Random;

/**
 * Created by yunlu on 2019/9/9.
 */

public class PiexData {

    private String name;        // 名字
    private float value;        // 数值
    private float percentage;   // 百分比

    // 非用户关心数据
    private int color = 0;      // 颜色
    private float angle = 0;    // 角度


    public PiexData(@NonNull String name, @NonNull float value) {
        this.name = name;
        this.value = value;
        Random random = new Random();
        int r = random.nextInt(200)+20;
        int g = random.nextInt(200)+20;
        int b = random.nextInt(200)+20;
        this.color = Color.rgb(r,g,b);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
