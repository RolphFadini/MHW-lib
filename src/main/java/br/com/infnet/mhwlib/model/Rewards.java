package br.com.infnet.mhwlib.model;

import lombok.Data;

import java.util.List;

@Data
public class Rewards {
    private int id;
    private Item item;
    private List<Conditions> conditions;
}
