package br.com.infnet.mhwlib.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor@NoArgsConstructor@Builder
public class MonsterFormatted {
    private Long id;
    private String name;
    private String species;
    private List<Weaknesses> weaknesses;
}
