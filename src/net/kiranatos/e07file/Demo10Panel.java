package net.kiranatos.e07file;

public class Demo10Panel {
    // ANSI escape code
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    public static void main(String[] args) {        
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "This text is cyan!" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "This text is yellow!" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "This text is blue!" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "This text is purple!" + ANSI_RESET);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}

        String greenBold = "\033[32;1m";
        String reset = "\033[0m";
        System.out.println("before" + greenBold + " green " + reset + "after");

        //System.out.print("\033\143");
        System.out.println("\033[H\033[2J");
        //System.out.print("\033[2J");
        System.out.flush();
    }    
}
/*
Scanner scan=new Scanner(System.in);
n = scan.nextInt()
.nextLine() 

Сохранили поток вывода на консоль в отдельную переменную.
Выводим в поток строку.
PrintStream console = System.out;
console.println("Hello");

Создали динамический (растягивающийся) массив байт в памяти.
Связали его с новым потоком вывода – объектов PrintStream
Выводим в поток строку.
ByteArrayOutputStream stream = new ByteArrayOutputStream();
PrintStream console = new PrintStream(stream);
console.println("Hello")
*/