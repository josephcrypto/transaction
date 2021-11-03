package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name ="casino_game_tables")
public class CasinoGameTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="game_type")
    private CasinoGame casinoGame;

    @Column(name = "tablename")
    private String tableName;

    private int enable;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "is_xipai")
    private int isXipai;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="cid")
    private CasinoCategory casinoCategory;

    private int xuehao;

    private int juhao;

    @Column(name = "current_xuehao")
    private int currentXuehao;

    private String xianhong;

    private int sort;

    @Column(name = "is_show")
    private int isShow;

    @Column(name = "heguan_name")
    private String heguanName;

    @Column(name = "is_show_net")
    private int isShowNet;

    @Column(name = "is_show_phone")
    private int isShowPhone;

    private String template;

    @Column(name = "tableno")
    private String tableNo;

    @Column(name = "rtmp_far",  columnDefinition = "TEXT")
    private String rtmpFar;

    @Column(name = "rtmp_near",  columnDefinition = "TEXT")
    private String rtmpNear;

    @Column(name = "httpflv_far",  columnDefinition = "TEXT")
    private String httpflvFar;

    @Column(name = "httpflv_near",  columnDefinition = "TEXT")
    private String httpflvNear;

    @Column(name = "mpeg_far",  columnDefinition = "TEXT")
    private String mpegFar;

    @Column(name = "mpeg_near")
    private String mpegNear;

    @Column(name = "default_second")
    private int defaultSecond;

    @Column(name = "round_limited")
    private byte roundLimited;

}
