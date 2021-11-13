package net.kiranatos.e06inner;

public class DemoAll {
    public static void main(String[] args) {        
        System.out.println("\t\t\tExamples of inner NON-static classes:");
        OuterClass.InnerClass outNonStIn = new OuterClass().new InnerClass();
        OuterClass outer = new OuterClass();
        outNonStIn = outer.new InnerClass();
        System.out.println("Области видимости: "
                + "\n - внутренний НЕ статический клас не может содержать static fields/methods/blocks;"
                + "\n - внутренний НЕ статический клас имеет доступ к fields внешнего класса (потому-что он хранит ссылку на объект outer class);"
                + "\n - outer class не имеет доступа к fields внутреннего класса;"
                + "\n - не желательно вкладывать внутр.классы в др. внутр.классы");        
        outNonStIn.inNonStField1 = "доступ через ссылку на объект inner class: не стат.поле inner class";        
        outer.outNonStField3.inNonStField1 = "доступ через ссылку на объект Outer class -> поле с типом Inner class -> не стат.поле Inner class";
        outer.messageOut();
        outer.outNonStField3.messageInNonSt();
        outNonStIn.messageInNonSt();        
                
        System.out.println("\n\n\t\t\tExamples of inner static classes:");
        OuterClass.InnerStaticClass outStIn = new OuterClass.InnerStaticClass();
        System.out.println(OuterClass.InnerStaticClass.str);
        
        System.out.println("\n\n\t\t\tExamples of Local Inner Classes (Классы внутри методов):");
        Demo02LocalInnerClasses localInnerClass = new Demo02LocalInnerClasses();
        localInnerClass.methodNonStatic(2, 666);
        System.out.println("********как получить доступ к inner local class (Подумать, может сделать как-то короче): **************");
        AbstractTeacher teacher = TeacherCreator.createTeacher(10);
        System.out.println(teacher.getClass());
        System.out.println(teacher.excludeStudent(new Student()));
        teacher = TeacherCreator.createTeacher(-10);
        System.out.println(teacher.excludeStudent(new Student()));
/******************************************************************************/        
        
        System.out.println("\n\n\t\t\tInterface and inner class:");
        Demo03InterfaceWithClassInside.InnerClassInsideInterface obj1 = new Demo03InterfaceWithClassInside.InnerClassInsideInterface();        
        obj1.nonStaticMethodInInnerClassInsideInterface();
        Demo03InterfaceWithClassInside.InnerClassInsideInterface.staticMethodInInnerClassInsideInterface();
        Demo03InterfaceWithClassInside.staticMethodInsideInterface();
        new Demo03InterfaceWithClassInside.InnerClassInsideInterface().nonStaticMethodInInnerClassInsideInterface();
        new Demo03InterfaceWithClassInside(){}.defaultMethodInsideInterface();
        
        
    }    
}
