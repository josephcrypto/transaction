package dv16888.com.transaction.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
public class CasinoRecordModel {

    private int rid;

    private String tableNo;

    private int xuehao;

    private int juhao;

    private int result;

    @JSONField(name = "dalu_position")
    private String daluPosition;

    @JSONField(name = "sanxing_position")
    private String sanxingPosition;

    @JSONField(name = "circle_position")
    private String circlePosition;

    @JSONField(name = "dot_position")
    private String dotPosition;

    @JSONField(name = "virgule_position")
    private String virgulePosition;

    @JSONField(name = "banker_1_poke")
    private String bankerOnePoke;

    @JSONField(name = "banker_2_poke")
    private String bankerTwoPoke;

    @JSONField(name = "banker_3_poke")
    private String bankerThreePoke;

    @JSONField(name = "player_1_poke")
    private String playerOnePoke;

    @JSONField(name = "player_2_poke")
    private String playerTwoPoke;

    @JSONField(name = "player_3_poke")
    private String playerThreePoke;

    private byte banker6;

    @JSONField(name = "next_show")
    private String nextShow;

    private String tableType;

    private String xv, zv;

    @JSONField(name = "next_coor1")
    private String nextCoor1;

    @JSONField(name = "next_coor2")
    private String nextCoor2;

    private String status;

    private String tokens;

    private Date drawTime;

    private String key;
}
