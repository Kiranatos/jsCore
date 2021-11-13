package net.kiranatos.e19annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.Method;

public class Demo02MetaAnnotation {
    public static void main(String[] args) {
        D02E03.staticMethod();
    }
}

/******************************************************************************/
/* annotation @Inherited разрешает наследовать аннотации классам. Класс D01E04_B
получил аннотацию @Demo01Example04 от родителя, благодаря тому, что у @interface Demo01Example04
есть аннотация @Inherited */
@Inherited
@interface Demo02Example01{ }

@Demo02Example01
class D02E01_A{}
class D02E01_B extends D02E01_A{}

/******************************************************************************/
/* @Target define where I can use annotation in classes*/
@Target(ElementType.METHOD)
@interface Demo02Example02v1{ }

@Target({ElementType.TYPE_USE, ElementType.PACKAGE}) // Using target annotation to annotate a type
@interface Demo02Example02v2{ } // Declaring a simple type annotation

//@Demo02Example02v1 <-Erorr
@Demo02Example02v2
class D02E02{
    //@Demo02Example02v1 <-Erorr
    @Demo02Example02v2
    int a;
    
    @Demo02Example02v1
    //@Demo02Example02v2 <-Erorr    
    private void method1(@Demo02Example02v2 String str){}
    
    @Demo02Example02v2
    String method2(){ return "";}
}
/******************************************************************************/
/* Repeatable annotation */

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyContainerForWords.class) // Make Words annotation repeatable
@interface Words{
    String word() default "Hello";
    int value() default 0;
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyContainerForWords{ // Create container for repeatable annotation
    Words[] value();
}

class D02E03 {           
    @Words(word = "ichi", value = 1)
    @Words(word = "ni", value = 2)
    @Words(word = "san", value = 3)
    @Words(word = "yon", value = 4)
    public static void staticMethod(){
        D02E03 obj = new D02E03();  
        try{
            Class<?> clazz = obj.getClass();              
            Method m = clazz.getMethod("staticMethod");
            Annotation anno = m.getAnnotation(MyContainerForWords.class);
            System.out.println(anno);
        } catch (NoSuchMethodException e){
            System.out.println(e);
        }
    }
}
/******************************************************************************/

