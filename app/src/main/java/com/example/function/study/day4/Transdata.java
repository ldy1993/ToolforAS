package com.example.function.study.day4;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Templates;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/8/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class Transdata implements Parcelable  {
    protected Transdata() {
    }


    protected Transdata(Parcel in) {
        name = in.readString();
        value = in.readString();
        in.readMap(stringMap,stringMap.getClass().getClassLoader());
    }

    public static final Creator<Transdata> CREATOR = new Creator<Transdata>() {
        @Override
        public Transdata createFromParcel(Parcel in) {
            return new Transdata(in);
        }

        @Override
        public Transdata[] newArray(int size) {
            return new Transdata[size];
        }
    };

    public String getName() {
        return name;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String value;
    private Map<String,String> stringMap=new ArrayMap<>();
    private List<String> stringList;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(value);
        dest.writeMap(stringMap);
        Iterator<String> iterator=stringMap.keySet().iterator();
        for(int i=0;i<stringMap.keySet().size();i++)
        {
            dest.writeString(stringMap.get(iterator.next()));
        }
        for(String s:stringMap.keySet())
        {
            dest.writeString(s);
        }
        for(int i=0;i<stringList.size();i++)
        {
            dest.writeString(stringList.get(i));
        }
        for(String s:stringList)
        {
            dest.writeString(s);
        }
    }
}
