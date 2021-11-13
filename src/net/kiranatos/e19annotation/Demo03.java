package net.kiranatos.e19annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo03 {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        D03E01 obj = new D03E01();
        Class<? extends D03E01> clazz1 = obj.getClass();
        Class<D03E01> clazz2 = D03E01.class;
        
        System.out.println("Target: Package = " + 
                clazz1.getPackage().isAnnotationPresent(Demo03Example01.class));
        System.out.println("Target: Type = " + 
                clazz1.isAnnotationPresent(Demo03Example01.class));
        Method method = clazz1.getMethod("doSomething");
        System.out.println("Target: Method = " + 
                method.isAnnotationPresent(Demo03Example01.class));
        Field field = clazz1.getField("koro");
        System.out.println("Target: Field = " + 
                field.isAnnotationPresent(Demo03Example01.class));
        
        // Class: Returns a specific annotation, if present, otherwise null.
        Demo03Example01 anno1 = D03E01.class.getAnnotation(Demo03Example01.class);
        System.out.println(anno1);
        
        // Class: Returns all annotations on a given type
        Annotation[] annoMatrix = clazz1.getAnnotations();
        for (Annotation a : annoMatrix) {
            System.out.println(" - " + a);
        }
        
        // Class: Returns all annotations of a given annotation type
        annoMatrix = clazz1.getAnnotationsByType(Demo03Example01.class);
        for (Annotation a : annoMatrix) {
            System.out.println(" + " + a);
        }
        
        // Method: Returns a specific annotation, if present, otherwise null.
        Deprecated dep = method.getAnnotation(Deprecated.class);
        
        // Method: Returns all annotations on the method.
        annoMatrix = method.getDeclaredAnnotations();
        
        // Method: Returns an two-dimensional array, containing the parameter annotations, in declaration order.
        Annotation[][] annoBiMatrix = method.getParameterAnnotations();
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Demo03Example01{ }

@Deprecated
@Demo03Example01
class D03E01{
    @Demo03Example01
    public int koro;    
    @Demo03Example01
    @Deprecated
    public void doSomething(){}
}