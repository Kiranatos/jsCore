package net.kiranatos.e06inner;

public class Demo02LocalInnerClasses {
    private int id = 1, m1 = 888; // 1)
    
    // Local inner class / Локальный внутренний класс (внутри методов)
    public void methodNonStatic(int id, int m3) { // 2)  id метода?
        int m2 = 777;
        int temp = id; // [#2]
        class LocalInnerClass {
            int id = 3; // 3)            
            LocalInnerClass(int id, int m4){ // 4) 
                /******************* Как обращаться к полям ******************/
                System.out.println(LocalInnerClass.this.id); // 3)
                System.out.println(this.id); // 3)
                System.out.println(id); // 3) or 4)
                System.out.println(Demo02LocalInnerClasses.this.id); // 1
                
                System.out.println(m1 + " " + m2 + " " + m3 + " " + m4);
            }
        }
        new LocalInnerClass(4, 999);
    }
    
    public static void methodStatic() {
        class LocalInnerClass { }        
    }
    
    public static void main(String[] args) {
        new Demo02LocalInnerClasses().methodNonStatic(2, 666);
    }
}

// Практический пример как получить доступ к inner local class : скрыта реализация класса + доступ к реализованному методу
// снаружи нельзя получить доступ
class Student{}

abstract class AbstractTeacher{
    private int id;
    public AbstractTeacher(int id) { this.id = id; }
    public abstract boolean excludeStudent(Student student);
}

class Teacher extends AbstractTeacher{    
    public Teacher(int id) { super(id); }
    @Override    
    public boolean excludeStudent(Student student) { return false;};
}

class TeacherCreator{
    public static AbstractTeacher createTeacher(int id) {
        // inner local class
        class Rector extends AbstractTeacher{
            public Rector(int id) { super(id); }
            @Override
            public boolean excludeStudent(Student student) {
                return true;
            }
        }
        return id > 0 ? new Rector(id) : new Teacher(id);
    }
    
    public static void someMethod() {
        class Rector extends AbstractTeacher{ // показать как отображаются одинаково названные классы в панеле 1,2
            public Rector(int id) { super(id); }
            @Override
            public boolean excludeStudent(Student student) {
                return true;
            }
        }
    }
}