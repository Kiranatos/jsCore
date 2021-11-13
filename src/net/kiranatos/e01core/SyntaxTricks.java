package net.kiranatos.e01core;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class SyntaxTricks {
    public static void main(String[] args) {
        // var позволяет не писать тип, java автоматически подставит нужный при инициализации.
        var m01 = 1; // int
        var m02 = Integer.valueOf("20"); // Integer        
        var m03 = "".split(""); // String[]
        // Пример некрасивого использования:
        var res = List.of(1, "2", 3.14, new StringBuilder("time"));
        List<Object> lst1 = Collections.singletonList(res);
        List<? extends Serializable> lst2 = res;
        /*********************************************************************/
        byte x1 = 10, y1 = 30;
        byte z1 = (byte)(x1 + y1);
        z1 = (byte)(z1 + 1);    z1 = (byte)(z1 / 2.3);
        z1++;                   z1 /= 2.3;
        final byte x2 = 10, y2 = 30;
        byte z2 = x2 + y2;
        /*********************************************************************/
    }
}
