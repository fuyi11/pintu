package com.phsxy.sawpuzzle.bean;

/**
 * author: CC_0625
 * date: 2018/12/12
 * dec:
 **/
public class Pieces {

    private int pX = 0;
    private int pY = 0;
    private int originalResource;
    private int position = 0;

    private int Note ;

    public int getNote() {
        return Note;
    }

    public void setNote(int note) {
        Note = note;
    }


    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpY() {
        return pY;
    }

    public void setpY(int pY) {
        this.pY = pY;
    }

    public int getOriginalResource() {
        return originalResource;
    }

    public void setOriginalResource(int originalResource) {
        this.originalResource = originalResource;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
