package dv16888.com.transaction.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sanxing {
    int index;
    int result;
    int tie;

    public Sanxing(int index, int result, int tie){
        this.index = index;
        this.result = result;
        this.tie = tie;
    }
}
