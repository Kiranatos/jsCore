try {	}    
catch(SQLException | FileNotFoundException ex) { ex.printStackTrace(); }
									
try (с ресурсами) {	}    catch    (SQLException | FileNotFoundException ex)     { ex.printStackTrace(); }        								
try (FileInputStream input = new FileInputStream("file.txt");)									
	В конце блока try FileInputStream будет закрыт автоматически.								
	Это возможно потому, что FileInputStream реализует интерфейс java.lang.AutoCloseable.								
	Все классы, реализующие этот интерфейс, могут быть использованы в конструкции try с ресурсами.								
									
try { …; return;	}    finally    { ... }        	Блок finally всегда выполняется вне зависимости будет выброшено исключение в блоке try или нет.							
		Также если в блоке есть return, блок finally также будет выполняется							
									
									


IOException	java.io	
FileNotFoundException	java.io	
NumberFormatException		
ClassCastException		
ClassNotFoundException		
IndexOutOfBoundsException - If off is negative, or len is negative, or off+len is negative or greater than the length of the given string
SQLException		
InstantiationException		
IllegalAccessException		
InterruptedException	


Throwable t;
        t.fillInStackTrace();
        t.getLocalizedMessage();
        t.getMessage();
        t.printStackTrace();
        t.printStackTrace(new PrintStream());
        t.printStackTrace(new PrintWriter());
        t.toString();										