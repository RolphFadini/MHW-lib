package br.com.infnet.mhwlib.model;

import lombok.Data;

@Data
public class Item {
    private int id;
    private int rarity;
    private int carryLimit;
    private int value;
    private String name;
    private String description;
}
