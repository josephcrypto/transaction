package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "casino_bet_records_calculation")
public class CasinoBetRecordsCalculation {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;
    @Column(name = "userid")
    private Integer userId;

    @Column(name = "bet_money")
    private Float betMoney;

    @Column(name = "bet_content")
    private String betContent;

    @Column(name = "tableno")
    private Integer tableNo;

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

    @Column(name = "xima_money")
    private Float ximaMoney;

    private Float benefit;

    @Column(name = "xima_fee")
    private Float ximaFee;
}
