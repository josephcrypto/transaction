package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "casino_finances")
public class CasinoFinances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fid;

    private Float amount;

    private Byte direction;

    @Column(name = "finance_type")
    private Byte financeType;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="userid")
    private CasinoUser casinoUser;

    @Column(name = "tableno")
    private Integer tableNo;

    private Integer xuehao;

    private Integer juhao;

    @Column(name = "finance_time")
    private Date financeTime;

    private Float balance;

    @Column(name = "user_type")
    private Byte userType;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="agency_id")
    private CasinoAgency casinoAgency;

    @Column(name = "finance_flag")
    private String financeFlag;

    private Byte faqi;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="bid")
    private CasinoBetRecords casinoBetRecords;
}
