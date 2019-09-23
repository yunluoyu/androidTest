package bean;

import java.util.ArrayList;

/**
 * Created by yunlu on 2019/9/4.
 */

public class AppInfo {

    public ArrayList<AppMsg> list;
    public ArrayList<String> picture;
    public class AppMsg{
        public String des;
        public String downloadUrl;
        public String iconUrl;
        public String id;
        public String name;
        public String packageName;
        public long size;
        public float stars;

        @Override
        public String toString() {
            return this.name+"的大小="+size + "星级="+this.stars;
        }
    }

}
