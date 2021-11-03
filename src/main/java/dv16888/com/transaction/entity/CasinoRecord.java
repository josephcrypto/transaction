package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "casino_records")
public class CasinoRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;

    @Column(name = "xuehao")
    private int xuehao;

    @Column(name = "game_type")
    private int gameType;

    @Column(name = "juhao")
    private int juhao;

    @Column(name = "result")
    private int result;

    @Column(name = "draw_time")
    private Date drawTime;

    @Column(name = "banker_1_poke")
    private String bankerOnePoke;

    @Column(name = "banker_2_poke")
    private String bankerTwoPoke;

    @Column(name = "banker_3_poke")
    private String bankerThreePoke;

    @Column(name = "player_1_poke")
    private String playerOnePoke;

    @Column(name = "player_2_poke")
    private String playerTwoPoke;

    @Column(name = "player_3_poke")
    private String playerThreePoke;

    @Column(name = "banker6")
    private byte banker6;

    @Column(name = "circle_postion",columnDefinition = "json")
    private String circlePosition;

    @Column(name = "dot_postion",columnDefinition = "json")
    private String dotPosition;

    @Column(name = "sanxing_postion",columnDefinition = "json")
    private String sanxingPosition;

    @Column(name = "virgule_postion",columnDefinition = "json")
    private String virgulePosition;

    @Column(name="dalu_postion",columnDefinition = "json")
    private String daluPosition;

    @Column(name = "next_show")
    private String nextShow;

    @Column(name = "coor1")
    private String coorOne;

    @Column(name = "coor2")
    private String coorTwo;

//    @Column(name = "table_type")
//    private String tableType;
//    @Column(name = "is_canceled")
//    private byte isCanceled;


}
