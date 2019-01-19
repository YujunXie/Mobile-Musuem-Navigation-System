package com.example.yujun.myapplication.control;

/**
 * Created by zhuoyuan on 2018/1/6.
 */

public class ShowItem {
    private int imageId;
    private String name1;
    private String name2;
    //private String button1;
//    private String button2;
    public ShowItem(String name1,String name2,int imageId){
        this.name1 = name1;
        this.name2 = name2;
     //  this.button1 = button1;
//        this.button2 = button2;
        this.imageId = imageId;
    }
    public String getName1() {
        return name1;
    }
    public String getName2() {
        return name2;
    }
//     public String getButton1() {
//      return button1;
//    }
//    public String getButton2() {
//        return button2;
//    }
    public int getImageId() {
        return imageId;
    }
}
