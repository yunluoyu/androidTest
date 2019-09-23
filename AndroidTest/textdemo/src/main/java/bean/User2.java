package bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yunlu on 2019/9/21.
 */

public class User2 implements Parcelable {

    private String name;
    private int age;

    public User2(){

    }
    protected  User2(Parcel in){
        this.name = in.readString();
        this.age = in.readInt();

    }

    public static final Creator<User2> CREATOR = new Creator<User2>() {
        @Override
        public User2 createFromParcel(Parcel in) {
            return new User2(in);
        }

        @Override
        public User2[] newArray(int size) {
            return new User2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


    }



}
