package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "casino_catagory")
public class CasinoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    @Column(name = "cname")
    private String cName;

    private int enable;

    private int sort;

    private String template;

    @Column(name = "c_type")
    private int cType;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="game_type")
    private CasinoGame casinoGame;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="hall_id")
    private CasinoHall casinoHall;

    @Column(name = "hall_name")
    private String hallName;

}
