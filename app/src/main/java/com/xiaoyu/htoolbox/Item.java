package com.xiaoyu.htoolbox;

public class Item {
    private String key;
    private String label;
    private int value;

    public Item(String label, String key, int value) {
        this.label = label;
        this.key = key;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
