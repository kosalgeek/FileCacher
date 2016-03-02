package com.kosalgeek.android.testfilecacher2;

import java.io.Serializable;

public class Product implements Serializable{

    public int id;
    public String name;

    public Product(int id, String name){
        this.id = id;
        this.name = name;
    }
}
