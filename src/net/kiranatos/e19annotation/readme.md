Categories of Annotations:
 - Marker Annotations. These annotations contain no members and do not consist any data. Thus, its presence as an annotation is sufficient. Since, marker interface contains no members, simply determining whether it is present or absent is sufficient. Example: @Override.
 - Single-Value Annotations. These annotations contain only one member and allow a shorthand form of specifying the value of the member. We only need to specify the value for that member when the annotation is applied and don’t need to specify the name of the member. However in order to use this shorthand, the name of the member must be value.
 - Multi-Valued Annotations. These annotations consist of multiple data members/ name, value, pairs. Example: @TestAnnotation(owner=”Rahul”, value=”Class Geeks”)

Meta-annotation (it can only be used to annotate other annotations)
 - Type Annotations: @Target - Where we can we use the annotation.
 - @Inherited - If present, an annotated type will pass it on to any subtypes.
 - Repeating Annotations: @Repeatable
 - @Documented Annotations - It is a marker interface that tells a tool that an annotation is to be documented. Annotations are not included by Javadoc comments. Use of @Documented annotation in the code enables tools like Javadoc to process it and include the annotation type information in the generated document.
 - @Retention Annotation. In which lifecycle of our code the annotation will be available. It determines where and how long the annotation is retent. Где будет видна. The 3 values that the @Retention(RetentionPolicy.***) annotation can have:
        SOURCE: Annotations will be retained at the source level and ignored by the compiler.
        CLASS: Annotations will be retained at compile time and ignored by the JVM.
        RUNTIME: These will be retained at runtime.
Source Code
   ▼
   ▼ ◁ Compiler
   ▼
Class file
   ▼
   ▼ ◁ JVM
   ▼
Runtime

Java defines built-in annotations:
    java.lang.annotation: @Retention, @Documented, @Target, @Inherited.
    java.lang: @Deprecated, @Override, @SuppressWarnings({"checked", "deprecation"})
    @FunctionalInterface
    @SafeVarargs






https://www.baeldung.com/java-annotation-processing-builder
http://tutorials.jenkov.com/java/annotations.html
https://www.javatpoint.com/java-reflection
https://www.guru99.com/java-reflection-api.html
https://www.journaldev.com/1789/java-reflection-example-tutorial
https://cloudogu.com/en/blog/Java-Annotation-Processors_1-Intro
https://www.geeksforgeeks.org/java-targeted-annotations/?ref=rp
https://www.geeksforgeeks.org/inherited-annotations-in-java/?ref=rp
https://www.geeksforgeeks.org/jackson-annotations-for-java-application/?ref=rp
https://www.geeksforgeeks.org/java-documented-annotations/?ref=rp
https://www.geeksforgeeks.org/how-to-create-your-own-annotations-in-java/?ref=rp

https://www.geeksforgeeks.org/annotations-in-java/ (was read/summarized)
https://medium.com/better-programming/java-annotations-explained-f1c26580b839 (was read/summarized)


