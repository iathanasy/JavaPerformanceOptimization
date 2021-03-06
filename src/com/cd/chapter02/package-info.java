/**
 *
 * 抽象类是对一种事物的抽象，即对类抽象，而接口是对行为的抽象
 *
 * 报警功能只是有的门才有
 * interface Alram {
 *     void alarm();
 * }
 * 门都有开关
 * abstract class Door {
 *     void open();
 *     void close();
 * }
 * 报警门
 * class AlarmDoor extends Door implements Alarm {
 *     void oepn() {
 *       //....
 *     }
 *     void close() {
 *       //....
 *     }
 *     void alarm() {
 *       //....
 *     }
 * }
 *
 *
 *
 * @author cd
 * @date 2019年3月25日 上午11:28:40
 * @desc Java程序性能优化第二章 设计优化
 */
package com.cd.chapter02;

/**
 * 
创建型模式(Creator)：5种

单例模式(Singleton)：某个类只能有一个实例，提供一个全局的访问点。

简单工厂(Factory)：一个工厂类根据传入的参量决定创建出那一种产品类的实例。

工厂方法(Factory method)：定义一个创建对象的接口，让子类决定实例化那个类。

抽象工厂(Abstract factory)：创建相关或依赖对象的家族，而无需明确指定具体类。

建造者模式(builder)：封装一个复杂对象的构建过程，并可以按步骤构造。

原型模式(Prototype)：通过复制现有的实例来创建新的实例。

 
结构型模式(Structure)：7种

适配器模式(Adapter)：将一个类的方法接口转换成客户希望的另外一个接口。

组合模式(Composite)：将对象组合成树形结构以表示“”部分-整体“”的层次结构。

装饰模式(Decorator)：动态的给对象添加新的功能。

代理模式(Proxy)：为其他对象提供一个代理以便控制这个对象的访问。

亨元（蝇量）模式(Flyweight)：通过共享技术来有效的支持大量细粒度的对象。

外观模式(Facade)：对外提供一个统一的方法，来访问子系统中的一群接口。

桥接模式(Bridge)：将抽象部分和它的实现部分分离，使它们都可以独立的变化。

 
行为型模式(Behavior)：11种

模板模式(Template method)：定义一个算法结构，而将一些步骤延迟到子类实现。

解释器模式(Interpreter)：给定一个语言，定义它的文法的一种表示，并定义一个解释器。

策略模式(Strategy)：定义一系列算法，把他们封装起来，并且使它们可以相互替换。

状态模式(State)：允许一个对象在其对象内部状态改变时改变它的行为。

观察者模式(Observer)：对象间的一对多的依赖关系。

备忘录模式(Memento)：在不破坏封装的前提下，保持对象的内部状态。

中介者模式(Mediator)：用一个中介对象来封装一系列的对象交互。

命令模式(Command)：将命令请求封装为一个对象，使得可以用不同的请求来进行参数化。

访问者模式(Visitor)：在不改变数据结构的前提下，增加作用于一组对象元素的新功能。

责任链模式(ChainOfResponsibility)：将请求的发送者和接收者解耦，使的多个对象都有处理这个请求的机会。

迭代器模式(Iterator)：一种遍历访问聚合对象中各个元素的方法，不暴露该对象的内部结构。
 
 */




