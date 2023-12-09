package br.com.infnet.mhwlib.util;

import br.com.infnet.mhwlib.controller.MonsterController;
import br.com.infnet.mhwlib.exception.ResourceNotFoundException;
import br.com.infnet.mhwlib.model.Monster;
import br.com.infnet.mhwlib.model.MonsterFormatted;
import br.com.infnet.mhwlib.model.Weaknesses;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class MonsterUtil {
    Logger logger = LoggerFactory.getLogger(MonsterController.class);
    private List<Monster> getAllFromAPI(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("https://mhw-db.com/monsters"))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            Monster[] monstersArray = objectMapper.readValue(response.body(), Monster[].class);
            logger.info("Status Code: " + response.statusCode());
            return Arrays.stream(monstersArray).toList();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Long, MonsterFormatted> monsters = initMonsters();

    private Map<Long, MonsterFormatted> initMonsters() {
        Map<Long, MonsterFormatted> monsters = new HashMap<>();
        List<Monster> monstersNotFormatted = getAllFromAPI();
        List<MonsterFormatted> monstersFormatted = monstersNotFormatted.stream()
                .map(monster -> new MonsterFormatted((long) monster.getId(), monster.getName(), monster.getSpecies(), monster.getWeaknesses()))
                .toList();

        int count = 0;
        for(MonsterFormatted monster : monstersFormatted){
            count++;
            String name = monster.getName();
            String species = monster.getSpecies();
            List<Weaknesses> weaknesses = monster.getWeaknesses();
            MonsterFormatted newMonster = MonsterFormatted.builder().name(name).species(species).id((long) count).weaknesses(weaknesses).build();
            monsters.put((long) count, newMonster);
        }
        return monsters;
    }

    public List<MonsterFormatted> getAll(){
        return monsters.values().stream().toList();
    }

    public MonsterFormatted getById(Long id){
        MonsterFormatted monster = monsters.get(id);
        if(monster == null){
            throw new ResourceNotFoundException("Monstro Inexistente");
        }
        return monsters.get(id);
    }

    public Optional<MonsterFormatted> getByIdWithOptional(Long id){
        MonsterFormatted monster = monsters.get(id);
        if(monster == null) {
            return Optional.empty();
        }
        return Optional.of(monster);
    }

    public int getSize(){
        return monsters.size();
    }

    public MonsterFormatted deleteById(Long id) {
       if(!monsters.containsKey(id)) {
           throw new ResourceNotFoundException("Monstro Inexistente");
       }
       MonsterFormatted removedMonster = monsters.remove(id);
       return removedMonster;
    }

    public MonsterFormatted createNewMonster(String name, String species, List<Weaknesses> weaknesses) {
        MonsterFormatted createdMonster = new MonsterFormatted();
        Long createMonsterId = monsters.size() + 1L;
        createdMonster.setId(createMonsterId);
        createdMonster.setName(name);
        createdMonster.setSpecies(species);
        createdMonster.setWeaknesses(weaknesses);
        monsters.put(createMonsterId, createdMonster);

        return createdMonster;
    }


    public MonsterFormatted updateMonsterById(Long id, String name, String species, List<Weaknesses> weaknesses) {
        if(!monsters.containsKey(id)) {
            throw new ResourceNotFoundException("Monstro Inexistente");
        }
        MonsterFormatted oldMonster = getById(id);
        MonsterFormatted newMonster = new MonsterFormatted();
        newMonster.setId(id);
        newMonster.setName(name);
        newMonster.setSpecies(species);
        newMonster.setWeaknesses(weaknesses);
        monsters.replace(id, oldMonster, newMonster);

        return newMonster;
    }

    public List<MonsterFormatted> getAll(Integer size) {
        List<MonsterFormatted> monsterList = monsters.values().stream().toList();
        List<MonsterFormatted> subsizedMonsterList = monsterList.subList(0, size);
        return subsizedMonsterList;
    }
}