package dv16888.com.transaction.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dalu {

    int x;
    int y;
    int result;
    int tie;

    public Dalu(int x, int y, int result, int tie){
        this.x = x;
        this.y = y;
        this.result = result;
        this.tie = tie;
    }
}
