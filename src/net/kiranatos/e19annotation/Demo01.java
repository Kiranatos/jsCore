package net.kiranatos.e19annotation;

import java.io.IOException;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Demo01 { }

/******************************************************************************/
@interface Demo01Example01{}
// Where can be annotation in class:
@Demo01Example01
class D01E01{
    @Demo01Example01
    int a;

    @Demo01Example01
    public D01E01() { }

    @Demo01Example01
    public void method1(@Demo01Example01 int b) {
        @Demo01Example01
        int c;
    }
    
    public @Demo01Example01 int getA() { return a; }    
}

/******************************************************************************/
// defining "value" in classeses D01E02version1 and D01E02version2 is mandatory, bcs no default value
@interface Demo01Example02{
    boolean run() default true;
    int i() default 10;
    String value();
}
@interface Demo01Example03{
    String str();
}

@Demo01Example03(str="string")
@Demo01Example02(value="string", i = 20)
class D01E02version1{}

@Demo01Example02("string") // работает только если название переменной value
class D01E02version2{}

/******************************************************************************/
/* What I can define in annotation */
@interface Demo01Example05{
            // fields
    int k_field = 10;
    Object obj_filed = new Object();
            // enums:
    enum SomeStatus { OPTION_1, OPTION_2, OPTION_3 }
    SomeStatus aa() default SomeStatus.OPTION_2;
            // primitives:
    boolean a() default false;
    int b();
            // only some types:
    String c() default "";
    //String d() default "do not make any operations in default" + new String("");
    //String e() default null; null is forbidden too. 
    Class<?> f() default Void.class;
    Class<? extends Number> g() default Short.class;
    // Object o(); error and not possible
            // other anotations
    Inherited ref() default @Inherited();
            // arrays
    String[] h();
    int[] i();
}

/* How to init arrays: h - many element, i - one element */
@Demo01Example05(b = 1, h = {"a","b"}, i = 10, ref = @Inherited)
class D01E05{}

/******************************************************************************/
/* @Deprecated Annotation
    - It is a marker annotation. It indicates that a declaration is obsolete and has been replaced by a newer form.
    - The Javadoc @deprecated tag should be used when an element has been deprecated.
    - @deprecated tag is for documentation and @Deprecated annotation is for runtime reflection.
    - @deprecated tag has higher priority than @Deprecated annotation when both are together used. 
Since Java 9, this previous marker annotation becomes a configuration annotation. The values String since() default "" and boolean forRemoval() default false were added to provide even more info for compilers and IDE to work with. */
class D01E06{
    /**
     * @deprecated  As of release 1.3, replaced by {@link #getPreferredSize()}
     */
    @Deprecated(forRemoval=false, since="1990")
    public void preferredSize() {    }
}

/******************************************************************************/

