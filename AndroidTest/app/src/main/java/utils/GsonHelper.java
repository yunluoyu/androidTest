package utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yunlu on 2019/9/4.
 */

public class GsonHelper {


    public static <T> T parseJsonToBean(String json, Class<T> cls){

        Gson gson = new Gson();
        T t = null;
        t = gson.fromJson(json,cls);
        return t;
    }
    /**
     * 把json字符串变成集合
     * params: new TypeToken<List<yourbean>>(){}.getType(),
     *
     * @param json
     * @param type  new TypeToken<List<yourbean>>(){}.getType()
     * @return
     */
    public static List<?> parseJsonToList(String json, Type type) {
        Gson gson = new Gson();
        List<?> list = gson.fromJson(json,type);
        return list;
    }


}
