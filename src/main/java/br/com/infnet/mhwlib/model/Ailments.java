package br.com.infnet.mhwlib.model;

import lombok.Data;

@Data
public class Ailments {
    private int id;
    private String name;
    private String description;
    private Recovery recovery;
    private Protection protection;
}
