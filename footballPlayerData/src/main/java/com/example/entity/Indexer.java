package com.example.entity;

// File: Indexer.java

public class Indexer {
    private int id;
    private String name;
    private float valueMin;
    private float valueMax;

    // Constructors (nếu cần)
    public Indexer() {
    }

    // --- GETTERS AND SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Đây là định nghĩa của hàm getValueMin().
     * Nó trả về giá trị của trường valueMin.
     */
    public float getValueMin() {
        return valueMin;
    }

    public void setValueMin(float valueMin) {
        this.valueMin = valueMin;
    }

    public float getValueMax() {
        return valueMax;
    }

    public void setValueMax(float valueMax) {
        this.valueMax = valueMax;
    }
}
