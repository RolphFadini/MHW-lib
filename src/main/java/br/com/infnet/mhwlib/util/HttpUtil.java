package br.com.infnet.mhwlib.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;

@Service
public class HttpUtil {
    @Autowired
    MonsterUtil monsterUtil;

    public HttpHeaders getHttpHeaders(Integer size, int page){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("total-size", String.valueOf(monsterUtil.getSize()));
        httpHeaders.set("total-pages", String.valueOf(monsterUtil.getTotalPaginas(size)));
        httpHeaders.set("current-page", String.valueOf(page));
        return httpHeaders;
    }
}
