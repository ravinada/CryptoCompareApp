package com.ravinada.cryptocompare.room;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "portfolios")
public class Portfolio  {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String currency;
    private String description;
    private int access;
    private Boolean selected;

    public Portfolio(String name, String currency, String description, int access, Boolean selected) {
        this.name = name;
        this.currency = currency;
        this.description = description;
        this.access = access;
        this.selected = selected;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
