package br.com.infnet.mhwlib.model;

import lombok.Data;

import java.util.List;

@Data
public class Location {
    private int id;
    private String name;
    private int zoneCount;
    private List<Camps> camps;
}