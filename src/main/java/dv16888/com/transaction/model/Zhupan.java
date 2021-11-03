package dv16888.com.transaction.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Zhupan {

    int juhao;
    int result;

    public Zhupan(int juhao, int result){
        this.juhao = juhao;
        this.result = result;
    }
}
