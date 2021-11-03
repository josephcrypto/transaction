package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "casino_users")
public class CasinoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name = "username")
    private String userName;

    private String password;

    @Column(name = "auth_key")
    private String authKey;

//    private String accessToken;
    private Float balance;

    private String xianhong;

    private Float fandian;

    private Float xima;
    @Column(name = "xima_bian")
    private Byte ximaBian;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="agency_id")
    private CasinoAgency casinoAgency;

    private Byte enable;

    @Column(name = "is_test")
    private Byte isTest;

    @Column(name = "is_online")
    private Byte isOnline;

    private Byte tick;

    private String peilv;

    @Column(name = "tables_permission")
    private String tablesPermission;

    @Column(name ="longhu_shahe")
    private Integer longhuShahe;

    @Column(name ="show_xima")
    private Integer showXima;

    @Column(name = "frozen_money")
    private Float frozenMoney;

    private String pic;

    @Column(name = "is_mianyong")
    private Byte isMiayong;

    @Column(name = "user_type")
    private Byte userType;

    private String mobile;

    @Column(name = "bankno")
    private String bankNo;

    @Column(name = "withdraw_password")
    private String withdrawPassword;

    private String bank;

    @Column(name = "bank_name")
    private String bankName;

    private String chips;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "levels_id", columnDefinition = "TEXT")
    private String levelsId;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="hall_id")
    private CasinoHall casinoHall;

    private Short currency;

}
