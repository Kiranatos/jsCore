package net.kiranatos.e06inner;

public interface Demo03InterfaceWithClassInside {    
    static void staticMethodInsideInterface(){ System.out.println("Статический метод в интерфейсе"
            + "\n\t\t - can't be overridden"); }
    default void defaultMethodInsideInterface(){         
        System.out.println("Дефолтный метод в интерфейсе"
            + "\n\t\t - accessible through the instance of the implementing class"
            + "\n\t\t - public by default"
            + "\n\t\t - can be overridden"
            + "\n\t\t - can be redeclare as abstract, which force subclass to override it"
            + "\n\t\t - difference from abstract classes: abs.class has constructors, state, and behavior."); }
    
    class InnerClassInsideInterface{
        static void staticMethodInInnerClassInsideInterface(){ System.out.println("Статический метод в Inner Claas в интерфейсе"); }
        void nonStaticMethodInInnerClassInsideInterface(){ System.out.println("Нестатический метод в Inner Claas в интерфейсе"); }
    }    
}

// Решение для ромба смерти:
interface DiamondProblemWithDefaultMethods {
    default void defaultMethodInsideInterface(){ System.out.println("Diamond Problem"); }
}

class SolutionForDiamondProblem implements Demo03InterfaceWithClassInside, DiamondProblemWithDefaultMethods {
    @Override
    public void defaultMethodInsideInterface(){ 
        System.out.print("Resolved: "); 
        DiamondProblemWithDefaultMethods.super.defaultMethodInsideInterface();
        Demo03InterfaceWithClassInside.super.defaultMethodInsideInterface();
    }
}
