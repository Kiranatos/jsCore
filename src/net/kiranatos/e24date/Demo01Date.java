package net.kiranatos.e24date;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 1 second = 1000 milliseconds
 * Unix-час: 1 січня 1970 р
 * January index = 0
 * 
 * Almost all Date methods are deprecated. That's why better to use LocalDate and Calendar classes
 
 * Problems Getting a Date class: 
 *  - Conceptually an instant, not a date
 *  - Properties have random offsets:
 *      1. Some zero-based, like month and hours
 *      2. Some one-based, like day of the month
 *      3. Year has an offset of 1900
 *  - Mutable, not thread-safe
 *  - Not internationalizable
 *  - Millisecond granularity
 *  - Does not reflect UTC
 * 
 */

public class Demo01Date {    
    private static final int YEAR_1970 = 1970;
    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){
        @Override
        public String[] getMonths() {
            return new String[]{"ichi-gatsu", "	ni-gatsu", "san-gatsu", "shi-gatsu", "go-gatsu", "roku-gatsu",
                "shichi-gatsu", "hachi-gatsu", "ku-gatsu", "juu-gatsu", "juuichi-gatsu", "juuni-gatsu"};
        }        
    };
    
    public static void main(String[] args) throws ParseException {  
        System.out.println("\n********** TIME TEST DEMONSTRATION **********");
        
        Date now = new Date();
        Date newYear2000 = new SimpleDateFormat("dd-MM-yyyy").parse("1-1-2000");
        Date randomYearAfter1970 = getRandomDate(YEAR_1970);
        
        System.out.println("Time is now: " + now);
        System.out.println("Time in milliseconds (1st variant): " + now.getTime());
        System.out.println("Time in milliseconds (2nd variant): " + System.currentTimeMillis());
        System.out.println("Last New Year: " + newYear2000);
        System.out.printf("Random year from %d: %s%n", YEAR_1970, randomYearAfter1970);
        
        if (now.after(newYear2000)) { // перевіряє чи один час після іншого
            System.out.println(".after = TRUE"); 
        } 
        if (newYear2000.before(now)) { // перевіряє чи один час перед іншим
            System.out.println(".before = TRUE");
        }
        

        SimpleDateFormat dayOfTheTemperature = new SimpleDateFormat("dd MMMM yyyy, EEEE");
        System.out.printf("Today is %s (demonstration output via SimpleDateFormat)%n",
                dayOfTheTemperature.format(new Date()));
        
        DateFormat dateInFrance = DateFormat.getDateInstance(DateFormat.LONG, Locale.JAPANESE);
        System.out.println("Time in Japanese: " + dateInFrance.format(new Date()));
        
        // ###############################################################
        System.out.println("\n***** Examples with class Calendar *****");
        Calendar calendar = new GregorianCalendar(2017, 0 , 25);
        Date date = calendar.getTime();
        // calendar.setTime(Date date)
        System.out.println("Calendar: " + calendar);
        System.out.println("Date: " + date);
        
        /* returns a GregorianCalendar initialized with the current
        date and time in the default locale and time zone.  */
        Calendar calendarNow = Calendar.getInstance();
        System.out.println("Via getInstance method: " + calendarNow.getTime());
        
        System.out.println("\nmetod set:");
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.MARCH); 
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 42);
        calendar.set(Calendar.SECOND, 12);        
        System.out.println("[changed] Calendar: " + calendar);
        System.out.println("calendar.getTime(): " + calendar.getTime());
        System.out.println("[not changed] Date: " + date);
        
        System.out.println("\nmetod add:");
        calendar.add(Calendar.MONTH, -5); // откат на 5 месяцев назад с изменением всех параметров.
        System.out.println("Calendar: " + calendar);
        System.out.println("calendar.getTime(): " + calendar.getTime());            
        
        System.out.println("\nmetod roll:");
        calendar.roll(Calendar.MONTH, -13); // откат на 13 месяцев назад. Меняется только MONTH.
        System.out.println("Calendar: " + calendar);
        System.out.println("calendar.getTime(): " + calendar.getTime());
        
        System.out.println("\nmetod get:");
        System.out.println("Date: " + calendar.get(Calendar.YEAR) +
                "." + calendar.get(Calendar.MONTH) +
                "." + calendar.get(Calendar.DAY_OF_MONTH));            
        System.out.println("Порядковый номер недели в месяце: " + calendar.get(Calendar.WEEK_OF_MONTH));            
        System.out.println("Time: " + calendar.get(Calendar.HOUR) + 
                ":" + calendar.get(Calendar.MINUTE) + 
                ":" + calendar.get(Calendar.SECOND) +
                ":" + calendar.get(Calendar.MILLISECOND));
        
        System.out.println("\nERA (BC - before Christ, AC - After Christ):");
        Calendar cannes = new GregorianCalendar(216, Calendar.AUGUST, 2);
        cannes.set(Calendar.ERA, GregorianCalendar.BC);
        DateFormat df = new SimpleDateFormat("dd MMM yyy GG"); // GG - вывод эры
        System.out.println(df.format(cannes.getTime()));
        
        // ###############################################################
        // SimpleDateFormat подходит для форматирования и Date и Calendar
        System.out.println("\n***** Examples with class SimpleDateFormat *****");
        calendar = new GregorianCalendar();        
        System.out.println("Time is now: " + calendar.getTime());
        System.out.println("Patterns: ");
        System.out.printf("1) %s%n", new SimpleDateFormat("EEEE, d MMMM yyyy").format(calendar.getTime()));    
        System.out.printf("2) %s%n", new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(calendar.getTime()));
                
        /* Конструктор по умолчанию использует паттерн времени и формат символов по умолчанию для текущей локализации. 
        То есть, для русской локализации стандартным паттерном времени является паттерн "dd.MM.yy HH:mm". */
        System.out.printf("3) %s%n", new SimpleDateFormat().format(new Date()));
        
        /* Конструктор принимает паттерн даты, в котором будет отдавать результат метод format().         
        В данном случае используется название месяца по умолчанию для текущей локализации, т.е., "Февраль".        */
        System.out.printf("4) %s%n", new SimpleDateFormat("dd MMMM").format(calendar.getTime()));
        
        /*Создает SimpleDateFormat, используя заданные паттерн времени и формат символов.
        Название месяца используется не по умолчанию, а те, которые возвращает переменная myDateFormatSymbols. 
        В свою очередь, в переменной myDateFormatSymbols мы переопределили метод getMonths() 
        чтобы он возвращал названия месяцев на nihongo.         */
        System.out.println(new SimpleDateFormat("dd MMMM", myDateFormatSymbols).format(calendar.getTime()));
        
        /* Конструктор использует заданную english локализацию       */
        System.out.println(new SimpleDateFormat("dd MMMM", Locale.ENGLISH).format(calendar.getTime()));
    }
   
    private static Date getRandomDate(int startYear) {							
        int year = startYear + (int) (Math.random() * 30);							
        int month = (int) (Math.random() * 12);							
        int day = (int) (Math.random() * 28);							
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);							
        return calendar.getTime();							
    }    
}

/*
http://www.seostella.com/ru/article/2012/02/05/formatirovanie-daty-v-java.html

Рассмотрим подробно параметры, принимаемые классом SimpleDateFormat в качестве паттерна даты. 

Символ 	Что означает                                            Пример
G 	эра (в английской локализации - AD и BC)		н.э.
y 	год (4-х значное число)		2012
yy 	год (последние 2 цифры)		12
yyyy 	год (4-х значное число)		2012
M 	номер месяца без лидирующих нулей		2
MM 	номер месяца (с лидирующими нулями если номер месяца < 10)		02
MMM 	четырех буквенное сокращение месяца в русской локализации и трех буквенное - в английской (Feb)		фев
MMMM 	полное название месяца (в английской локализации - February)		Февраль
w 	неделя в году без лидирующих нулей		7
ww 	неделя в году с лидирующими нулями		07
W 	неделя в месяце без лидирующих нулей		2
WW 	неделя в месяце с лидирующим нулем (если это необходимо)		02
D 	день в году		38
d 	день месяца без лидирующих нулей		7
dd 	день месяца с лидирующими нулями		07
F 	день недели в месяце без лидирующих нулей		1
FF 	день недели в месяце с лидирующими нулями		01
E 	день недели (сокращение)		Вт
EEEE 	день недели (полностью)		вторник
a 	AM/PM указатель		AM
H 	часы в 24-часовом формате без лидирующих нулей		6
HH 	часы в 24-часовом формате с лидирующим нулем		06
k 	количество часов в 24-часовом формате		18
K 	количество часов в 12-часовом формате		6
h 	время в 12-часовом формате без лидирующих нулей		6
hh 	время в 12-часовом формате с лидирующим нулем		06
m 	минуты без лидирующих нулей		32
mm 	минуты с лидирующим нулем		32
s 	секунды без лидирующих нулей		11
ss 	секунды с лидирующим нулем		11
S 	миллисекунды		109
z 	часовой пояс		EET
Z 	часовой пояс в формате RFC 822		+0200
' 	символ экранирования для текста 	'Date='
'' 	кавычка 	'o''clock'


***********************************************************************


 Рассмотрим несколько примеров паттернов даты и времени, которые представлены в официальной документации. Русская локализация:
Паттерн даты и времени 	Результат
"e;yyyy.MM.dd G 'at' HH:mm:ss z"e;		2012.02.07 н.э. at 16:51:35 EET
"e;EEE, MMM d, ''yy"e;		Вт, фев 7, '12
"e;h:mm a"e;		4:51 PM
"e;hh 'o''clock' a, zzzz"e;		04 o'clock PM, Eastern European Time
"e;K:mm a, z"e;		4:51 PM, EET
"e;yyyyy.MMMMM.dd GGG hh:mm aaa"e;		02012.Февраль.07 н.э. 04:51 PM
"e;EEE, d MMM yyyy HH:mm:ss Z"e;		Вт, 7 фев 2012 16:51:35 +0200
"e;yyMMddHHmmssZ"e;		120207165135+0200

***********************************************************************

Английская локализация:
Паттерн даты и времени 	Результат
"e;yyyy.MM.dd G 'at' HH:mm:ss z"e;		2012.02.07 AD at 16:55:57 EET
"e;EEE, MMM d, ''yy"e;		Tue, Feb 7, '12
"e;h:mm a"e;		4:55 PM
"e;hh 'o''clock' a, zzzz"e;		04 o'clock PM, Eastern European Time
"e;K:mm a, z"e;		4:55 PM, EET
"e;yyyyy.MMMMM.dd GGG hh:mm aaa"e;		02012.February.07 AD 04:55 PM
"e;EEE, d MMM yyyy HH:mm:ss Z"e;		Tue, 7 Feb 2012 16:55:57 +0200
"e;yyMMddHHmmssZ"e;		120207165557+0200






*/