package ViewUtils;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by yunlu on 2019/9/18.
 */

public class ViewUtil {


    public static void inject(Activity activity) {

        try {
            bindView(activity);
            bindOnClick(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
    * 绑定控件的点击方法
    * */
    private static void bindOnClick(final Activity activity) {
        /*
        * 1. 获取字节码
        * 2. 获取所有的方法
        * 3. 绑定控件id
        * 4. 设置控件的监听
        * */
        // 1
        Class  clazz = activity.getClass();

        Method[] declaredMethods = clazz.getDeclaredMethods();
        // 遍历所有的方法，找到带有注解信息的方法
        for(final Method method : declaredMethods){
            //得到注解信息对象
            OnClick onClick = method.getAnnotation(OnClick.class);
            if(onClick != null){
                int resId = onClick.value();
                // 绑定控件ID ,设置监听器
                final View view = activity.findViewById(resId);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //当点击时，运行该方法，需要在次回调该外面的方法
                        //暴力反射调用 method 方法
                        method.setAccessible(true);
                        try {
                            method.invoke(activity,view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }

        }
    }

    // 绑定控件ID
    private static void bindView(Activity activity) throws IllegalAccessException {
        // 1. 获取字节码
        // 2. 获取带有注解的字段
        // 3. 给字段绑定view
        Class clazz = activity.getClass();
        // 获取所有的字段
        Field[] fields = clazz.getDeclaredFields();
        // 遍历所有的字段
        for(Field field : fields){
            MyViewInjcet annotation = field.getAnnotation(MyViewInjcet.class);
            // 当注解信息不为空时，获取的值 为 资源的ID
            if(annotation != null){
                int resId = annotation.value();
                // 绑定控件
                View view = activity.findViewById(resId);
                // 通过暴力反射 将控件赋值给字段
                field.setAccessible(true);
                // 将view 赋值给该字段
                field.set(activity,view);
            }
        }
    }
}
