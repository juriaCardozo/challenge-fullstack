package com.mars.robot.demo.model;

public class Robot{
    private int xCoord;
    private int yCoord;
    private String orientation;

    public Robot(int xCoord, int yCoord, String orientation){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.orientation = orientation;
    }
    
    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}