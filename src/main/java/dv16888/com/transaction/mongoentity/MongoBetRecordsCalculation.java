package dv16888.com.transaction.mongoentity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@Document(collection = "casino_bet_records_calculation")
public class MongoBetRecordsCalculation {

    private int bid;

    private String _id;

    @Field(name = "userid")
    private int userId;

    @Field(name = "bet_money")
    private float betMoney;

    @Field(name = "bet_content")
    private String betContent;

    @Field(name = "tableno")
    private int tableNo;

    @Field(name = "is_jiesuan")
    private byte isJiesuan;

    @Field(name = "js_time")
    private Date jsTime;

    @Field(name = "win_lose")
    private byte winLose;

    private Integer xuehao;

    private Integer juhao;

    @Field(name = "without_fee")
    private byte withoutFee;

    @Field(name = "bet_time")
    private Date betTime;

    @Field(name = "xima_money")
    private float ximaMoney;

    @Field(name = "benefit")
    private float benefit;

    @Field(name = "xima_fee")
    private float ximaFee;

}
