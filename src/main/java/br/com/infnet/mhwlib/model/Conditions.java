package br.com.infnet.mhwlib.model;

import lombok.Data;

@Data
public class Conditions {
    private String type;
    private String rank;
    private int quantity;
    private int chance;
    private String subtype;
}
