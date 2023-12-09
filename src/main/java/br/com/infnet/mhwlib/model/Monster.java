package br.com.infnet.mhwlib.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Monster {
    private int id;
    private String type;
    private String species;
    private List<String> elements;
    private String name;
    private String description;
    private List<Ailments> ailments;
    private List<Location> locations;
    private List<Resistences> resistences;
    private List<Weaknesses> weaknesses;
    private List<Rewards> rewards;
}
