package br.com.infnet.mhwlib;

import br.com.infnet.mhwlib.exception.ResourceNotFoundException;
import br.com.infnet.mhwlib.model.MonsterFormatted;
import br.com.infnet.mhwlib.util.MonsterUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MonsterTest {
    @Autowired
    MonsterUtil monsterUtil;

    Logger logger = LoggerFactory.getLogger(MonsterTest.class);

    @Test
    @DisplayName("deve retornar todos os monstros")
    public void testaTodosOsMonstros(){
        List<MonsterFormatted> monsters = monsterUtil.getAll();
        for(MonsterFormatted monster : monsters){
            logger.info("=======================");
            logger.info("ID     : " + monster.getId());
            logger.info("Name   : " + monster.getName());
            logger.info("Species: " + monster.getSpecies());
        }
        logger.info("=======================");
        assertEquals(58, monsters.size());
    }

    @Test
    @DisplayName("deve retonar um monstro pelo ID")
    public void testaMonstroPeloId(){
        MonsterFormatted monster = monsterUtil.getById(25L);

        logger.info("GetById Method");
        logger.info("Monster ID     : " + monster.getId());
        logger.info("Monster Name   : " + monster.getName());
        logger.info("Monster Species: " + monster.getSpecies());
        assertEquals(25, monster.getId());
    }

    @Test
    @DisplayName("deve testar se o monstro é null")
    public void testaMonstroNull(){
        Optional<MonsterFormatted> optionalMonsterInexistente = monsterUtil.getByIdWithOptional(-100L);
        assertTrue(optionalMonsterInexistente.isEmpty());

        Optional<MonsterFormatted> optionalMonsterExistente = monsterUtil.getByIdWithOptional(1L);
        assertTrue(optionalMonsterExistente.isPresent());

        MonsterFormatted monstroExistente = optionalMonsterExistente.get();
        assertEquals(1L, monstroExistente.getId());

        logger.info("ID     : " + monstroExistente.getId());
        logger.info("Name   : " + monstroExistente.getName());
        logger.info("Species: " + monstroExistente.getSpecies());
    }

    @Test
    @DisplayName("deve testar se a exception está sendo lançada")
    public void testaException(){
        MonsterUtil monstroInexistente = new MonsterUtil();

        assertThrows(ResourceNotFoundException.class, () -> {
            monstroInexistente.getById(-100L);
        });
    }

    @Test
    @DisplayName("deve testar o retorno do total de paginas")
    public void testaGetTotalPaginas(){
        int totalPaginas = monsterUtil.getTotalPaginas(20, 60);
        assertEquals(3, totalPaginas);
        totalPaginas = monsterUtil.getTotalPaginas(20, 58);
        assertEquals(3, totalPaginas);
    }
}
