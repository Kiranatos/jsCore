package net.kiranatos.e31localization;

import java.io.UnsupportedEncodingException;
import java.text.*;
import java.util.*;

public class Unsorted {
    public static void main(String[] args) throws UnsupportedEncodingException {       
        
        String str1 = "Тестування перекодування символів";
		System.out.println(str1);

		byte[] b = str1.getBytes("Cp1251");
		
		String str2 = new String(b, "KOI8-R");
		System.out.println(str2);
		
		String str3 = new String(b, "Cp866");
		System.out.println(str3);

		String str4 = new String(b, "ISO8859-5");
		System.out.println(str4);
        
        // Contains localized text or objects
        ResourceBundle bundle;
        // Use to format numbers/currencies as per the locale
        NumberFormat nf;
        // Use to format numbers as per customized format and as per locale
        DecimalFormat df; 
        // Use to format dates as per locale
        DateFormat dformat;
        // Use to format dates as per customized format and as per locale
        SimpleDateFormat sdf;
        char c = '\u65e5';
        System.out.println(c);
        System.out.println("\\u65e5\\u672c\\u8a9e");
        
        Date date = new Date();
        double value = 12345.6789;
        Number d;
        
        Locale locale = Locale.getDefault();
        System.out.println(locale);
//        
////        bundle = ResourceBundle.getBundle("net.kiranatos.e18localization.res.prop_uk_UA.properties", local);
        //bundle = ResourceBundle.getBundle("resources.prop.properties");
        bundle = ResourceBundle.getBundle("prop.properties");
        System.out.println(bundle.getString("main.name"));

//		nf = NumberFormat.getNumberInstance();
//		System.out.println(nf.format(value));
//		nf = NumberFormat.getCurrencyInstance();
//		System.out.println(nf.format(value));
//		d = nf.parse("$1234.567");
//		System.out.println(d);
//
//		df = DateFormat.getDateInstance(DateFormat.SHORT);
//		System.out.println(df.format(date));
//		df = DateFormat.getDateInstance(DateFormat.MEDIUM);
//		System.out.println(df.format(date));
//		df = DateFormat.getDateInstance(DateFormat.LONG);
//		System.out.println(df.format(date));
//		df = DateFormat.getDateInstance(DateFormat.FULL);
//		System.out.println(df.format(date));
//
//		str = df.format(date);
//		parseDate = df.parse(str);
//		System.out.println(parseDate);
//		System.out.println();
//
//		locale = new Locale("uk", "UA");
//
//		System.out.println(locale);
//
//		nf = NumberFormat.getNumberInstance(locale);
//		System.out.println(nf.format(value));
//		nf = NumberFormat.getCurrencyInstance(locale);
//		System.out.println(nf.format(value));
//
//		d = nf.parse(nf.format(value));
//		System.out.println(d);
//
//		df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
//		System.out.println(df.format(date));
//		df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
//		System.out.println(df.format(date));
//		df = DateFormat.getDateInstance(DateFormat.LONG, locale);
//		System.out.println(df.format(date));
//		df = DateFormat.getDateInstance(DateFormat.FULL, locale);
//		System.out.println(df.format(date));
//		str = df.format(date);
//		parseDate = df.parse(str);
//		System.out.println(parseDate);
//		System.out.println();
//        
        
    }    
}
/*
i18n
• Language
• Dates
• Times
• Numbers
• Currencies
• Measurements
• Phone numbers
• Personal titles
• Postal addresses
• Messages
• Colors
• Graphics

Standart Language Codes
https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
Standart Countries Codes
https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes
Latin-1
https://ru.wikipedia.org/wiki/ISO_8859-1
*/


/*
String encodeWithUTF = new String (bundleDefault.getString ("somevalue").getBytes ("ISO-8859-1"),"windows-1251");

System.out.println (encodeWithUTF);

как писать в проперти:
https://www.youtube.com/watch?v=Kq1FEHI2gTA
*/

/*
Date date = new Date();
		DateFormat df;
		Locale locale;
		String str;
		Date parseDate;
		NumberFormat nf;
		double value = 12345.6789;
		Number d;

		locale = Locale.getDefault();
		locale = new Locale("ua", "UA");
		System.out.println(locale);

		ResourceBundle bundle;
//		bundle = ResourceBundle.getBundle("resources.prop");
		bundle = ResourceBundle.getBundle("resources.Prop", locale);
		System.out.println(bundle.getString("main.name"));

		nf = NumberFormat.getNumberInstance();
		System.out.println(nf.format(value));
		nf = NumberFormat.getCurrencyInstance();
		System.out.println(nf.format(value));
		d = nf.parse("$1234.567");
		System.out.println(d);

		df = DateFormat.getDateInstance(DateFormat.SHORT);
		System.out.println(df.format(date));
		df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		System.out.println(df.format(date));
		df = DateFormat.getDateInstance(DateFormat.LONG);
		System.out.println(df.format(date));
		df = DateFormat.getDateInstance(DateFormat.FULL);
		System.out.println(df.format(date));

		str = df.format(date);
		parseDate = df.parse(str);
		System.out.println(parseDate);
		System.out.println();

		locale = new Locale("uk", "UA");

		System.out.println(locale);

		nf = NumberFormat.getNumberInstance(locale);
		System.out.println(nf.format(value));
		nf = NumberFormat.getCurrencyInstance(locale);
		System.out.println(nf.format(value));

		d = nf.parse(nf.format(value));
		System.out.println(d);

		df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		System.out.println(df.format(date));
		df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		System.out.println(df.format(date));
		df = DateFormat.getDateInstance(DateFormat.LONG, locale);
		System.out.println(df.format(date));
		df = DateFormat.getDateInstance(DateFormat.FULL, locale);
		System.out.println(df.format(date));
		str = df.format(date);
		parseDate = df.parse(str);
		System.out.println(parseDate);
		System.out.println();


//        ResourceBundle rb = ResourceBundle.getBundle("test.res");
        ResourceBundle rb = ResourceBundle.getBundle("test.res", Locale.forLanguageTag("locale en_US"));
        System.out.println(rb.getString("print"));
*/