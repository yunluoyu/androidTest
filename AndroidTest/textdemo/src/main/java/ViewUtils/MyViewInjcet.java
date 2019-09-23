package ViewUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yunlu on 2019/9/18.
 */

@Retention(RetentionPolicy.RUNTIME) // 设置注解存在的时间
@Target(ElementType.FIELD) // 设置注解类型
public @interface MyViewInjcet {
    int value();
}
