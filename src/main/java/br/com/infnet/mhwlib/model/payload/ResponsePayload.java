package br.com.infnet.mhwlib.model.payload;

import br.com.infnet.mhwlib.util.MonsterUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
public class ResponsePayload {
    private String message;
    private LocalDateTime dataHora;
    private int lastPossibleId;

    public ResponsePayload(String message) {
        MonsterUtil monsterUtil = new MonsterUtil();
        this.message = message;
        this.dataHora = LocalDateTime.now();
        this.lastPossibleId = monsterUtil.getSize();
    }
}
