package dv16888.com.transaction.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Xiaolu {

    int x;
    int y;
    int result;

    public Xiaolu(int x, int y, int result){
        this.x = x;
        this.y = y;
        this.result = result;
    }
}
