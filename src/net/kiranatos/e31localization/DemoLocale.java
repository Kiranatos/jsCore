package net.kiranatos.e31localization;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/*
-Duser.language=ru -Duser.region=RU
*/

public class DemoLocale {
    public static void main(String[] args) {
        // Represents a language along with country/region
        // Локаль установленная в системе:
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        // Создать свою локаль в соответствии с ISO 639-1 и ISO 3166:
        locale = new Locale("uk", "UA");
        System.out.println(locale);
        
        locale = Locale.GERMANY;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        System.out.println(numberFormat.format(1000));
        
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale loc : locales) {
            System.out.print(loc);  
            System.out.print("\tExample: " + NumberFormat.getCurrencyInstance(loc).format(1000));
            System.out.println("\t" + DateFormat.getDateInstance(DateFormat.FULL, loc).format(new Date()));
        }
        
    }    
}
