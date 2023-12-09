package br.com.infnet.mhwlib.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recovery {
    private List<String> actions;
    private List<Item> items;
    private Protection protection;
}
