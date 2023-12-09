package br.com.infnet.mhwlib.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weaknesses {
    private String element;
    private int stars;
    private String condition;
}
