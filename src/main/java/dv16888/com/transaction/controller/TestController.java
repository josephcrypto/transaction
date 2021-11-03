package dv16888.com.transaction.controller;

import com.alibaba.fastjson.JSONObject;
import dv16888.com.transaction.services.reposervices.GameTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private GameTableService gameTableService;

    @GetMapping("/game_table")
    public String getGameTable(@RequestParam("tableNo") String tableNo){
        return JSONObject.toJSONString(gameTableService.getByTableNo(tableNo));
    }
}
