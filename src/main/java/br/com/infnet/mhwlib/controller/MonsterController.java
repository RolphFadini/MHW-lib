package br.com.infnet.mhwlib.controller;

import br.com.infnet.mhwlib.exception.ResourceNotFoundException;
import br.com.infnet.mhwlib.model.MonsterFormatted;
import br.com.infnet.mhwlib.model.Weaknesses;
import br.com.infnet.mhwlib.model.payload.ResponsePayload;
import br.com.infnet.mhwlib.util.HttpUtil;
import br.com.infnet.mhwlib.util.MonsterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monsters")
public class MonsterController {
    @Autowired
    MonsterUtil monsterUtil;
    @Autowired
    HttpUtil httpUtil;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "58") Integer size,
                                 @RequestParam(required = false, defaultValue = "1") int page){

        List<MonsterFormatted> monsters = monsterUtil.getByPageAndSize(page, size);
        HttpHeaders httpHeaders = httpUtil.getHttpHeaders(size, page);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(monsters);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        try{
            MonsterFormatted monster = monsterUtil.getByIdWithOptional(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Monstro Inexistente"));
            return ResponseEntity.ok(monster);
        }catch(ResourceNotFoundException ex){
            ResponsePayload responsePayload = new ResponsePayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayload);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try{
            MonsterFormatted removedMonster = monsterUtil.deleteById(id);
            return ResponseEntity.ok(removedMonster);
        }catch(ResourceNotFoundException ex){
            ResponsePayload responsePayload = new ResponsePayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayload);
        }
    }

    @PostMapping MonsterFormatted newMonster(@RequestBody MonsterFormatted monsterFormatted){
        String name = monsterFormatted.getName();
        String species = monsterFormatted.getSpecies();
        List<Weaknesses> weaknesses = monsterFormatted.getWeaknesses();
        return monsterUtil.createNewMonster(name, species, weaknesses);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Long id,
                                     @RequestBody MonsterFormatted monsterFormatted){
        try{
            String name = monsterFormatted.getName();
            String species = monsterFormatted.getSpecies();
            List<Weaknesses> weaknesses = monsterFormatted.getWeaknesses();
            MonsterFormatted updatedMonster = monsterUtil.updateMonsterById(id, name, species, weaknesses);
            return ResponseEntity.ok(updatedMonster);
        }catch(ResourceNotFoundException ex){
            ResponsePayload responsePayload = new ResponsePayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayload);
        }
    }
}
