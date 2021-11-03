package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "casino_bet_records")
public class CasinoBetRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="userid")
    private CasinoUser casinoUser;

    @Column(name = "bet_money")
    private Float betMoney;

    @Column(name = "bet_content")
    private String betContent;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="tableno")
    private CasinoGameTable casinoGameTable;

    @Column(name = "is_jiesuan")
    private Byte isJiesuan;

    @Column(name = "js_time")
    private Date jsTime;

    @Column(name = "win_lose")
    private Byte winLose;

    private Integer xuehao;

    private Integer juhao;

    @Column(name = "without_fee")
    private Byte withoutFee;

    @Column(name = "bet_time")
    private Date betTime;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "before_balance")
    private Float beforeBalance;

    @Column(name = "xima_money")
    private Float ximaMoney;

    private Float benefit;

    @Column(name = "xima_fee")
    private Float ximaFee;

}
