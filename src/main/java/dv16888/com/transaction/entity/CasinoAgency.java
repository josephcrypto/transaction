package dv16888.com.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "casino_agency")
public class CasinoAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;

    @Column(name = "username")
    private String userName;

    private String password;
//    @Column(name = "authKey")
//    private String authKey;
//    @Column(name = "access_token")
//    private String access_token;
    @Column(name = "is_online")
    private Integer isOnline;

    private Integer enable;

    private Integer tick;

    private Float balance;

    @Column(name = "parentid")
    private Integer parentId;
    private String mobile;

    private String email;

    private String qq;

    private String wechat;

    @Column(columnDefinition = "TEXT")
    private String other;

    private Float zc;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "ximalv_single")
    private Float ximalvSingle;

    @Column(name = "ximalv_double")
    private Float ximalvDouble;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "children_agent_id",columnDefinition = "TEXT")
    private String childrenAgentId;

    @Column(name = "children_users_id",columnDefinition = "TEXT")
    private String childrenUsersId;

    @Column(name = "min_xh")
    private Integer minXh;

    @Column(name = "max_xh")
    private Integer maxXh;

    @Column(name = "levels_id" ,columnDefinition = "TEXT")
    private String levelsId;

    @Column(name = "all_users" ,columnDefinition = "TEXT")
    private String allUsers;

    @Column(name = "all_agents",columnDefinition = "TEXT")
    private String allAgents;

    @Column(name = "allow_charge")
    private Integer allowCharge;

    @Column(name = "updated_at")
    private Date updagedAt;

    @Column(name = "h5_key")
    private String h5Key;

    @Column(name = "h5_iv")
    private String h5Iv;

    @Column(name = "qrcode_url")
    private String qrcodeUrl;

    @Column(name = "hallid")
    private Integer hallId;

    @Column(name = "allow_discount")
    private Byte allowDiscount;
}
