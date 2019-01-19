package com.example.yujun.myapplication.control;

/**
 * Created by liuhua on 2018/1/4.
 */
//柳华代码
public class exhibitionroom {
    private String name;
    private int imageId;
    private String number;

    public exhibitionroom(String name, int imageId, String num){
        this.name = name;
        this.imageId = imageId;
        this.number = num;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }
    public String getNumber() {
        return number;
    }
}
