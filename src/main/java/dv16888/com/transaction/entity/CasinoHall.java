package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name ="casino_halls")
public class CasinoHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id")
    private int hallId;

    @Column(name = "hall_name")
    private String hallName;

    private String alias;

    private int enable;

}
