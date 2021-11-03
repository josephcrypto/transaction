package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "casino_games")
public class CasinoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gid;

    @Column(name = "game_name")
    private String gameName;

    private String abbreviation;

    @Column(name = "rules",  columnDefinition = "TEXT")
    private String rules;

}
