package com.example.yujun.myapplication.control;

/**
 * Created by Administrator on 2018/1/3.
 */

public class Fruit {
    private String name;
    private  int imageId;
    private String text;
    public Fruit(String name, int imageId,String text){
        this.name = name;
        this.imageId=imageId;
        this.text=text;
    }
    public Fruit(String name, int imageId){
        this.name = name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
    public String getText() { return text;
    }
}
