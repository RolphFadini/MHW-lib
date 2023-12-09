package br.com.infnet.mhwlib.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Protection {
    private List<Skills> skills;
}
