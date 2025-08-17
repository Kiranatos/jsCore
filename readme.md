Конспект: приклади, яким не потрібен Maven і підключення зовнішніх бібліотек 

1) e01core
Inheritance, bit operations, Enum

2) e02sort
Sortings: Bubble Sort, Quick Sort
task: убрать StartSorts
 
3) e03generics
Arrays, Generics, ArrayList, HashMap, HashSet

4) e04lamb
Lambda, Functional Interfaces

5) e05stre : Stream API

6) e06inne : Inner classes(Anonymous,Inner,Nested,Local inner)
Demo01AnonymousClasses: Anonymous Class
Demo03InterfaceWithClassInside: Class Inside Interface, Death diamond, info about interface's methods
Demo02LocalInnerClasses: Local inner class(Локальный внутренний класс [внутри методов])
OuterClass: Inner class(Внутренний нестатический класс) & Nested class(Внутренний cтатический класс)
Demo04AbstractClass: Abstract Class
DemoAll: неотредактированная попытка продемонстрировать все вышеперечисленные
task: привести DemoAll в более призентабельный вид

Inner=non-static nested

17) e17rege : Regular Expressions







e04 e05 e09 e17 e30 e31


   e11zip: Работа с ZIP-архивами
Examples: NetCourses.net.kiranatos.javarush.q3threads.lvl1.ThreadsL1L10T1Demo

Logs, Serialization, Classloader, System, Runtime



****************
переменная объявлена статической, то она существует в единственном экземпляре			
Обычные методы втихаря хранят ссылку на объект, а статические – нет. То же и со статическими классами			
Статический метод может обращатся только к статическим переменным			
НеСтатический метод может обращатся и к статическим и к нестатическим переменным. Метод скрытно хранит ссылку(this) на объект класса.			
Статический вложенный класс Mouse, как и статический метод, может обращаться к статическим переменным класса Zoo, но не может обращаться к нестатическим.			
спокойно создавать объекты класса Mouse, даже когда нет ни одного созданного объекта класса Zoo			
Zoo.Mouse mouse = new Zoo.Mouse();			
1) При создании объектов вложенного класса (как класс Mouse) вне внешнего класса-родителя, надо еще указывать через точку и имя внешнего класса.			
	Например так: Zoo.Mouse.		
2) Класс Zoo.Mouse и его объекты имеют доступ к private static переменным и методам класса Zoo ( класс Mouse ведь тоже объявлен внутри класса Zoo).			
*****************