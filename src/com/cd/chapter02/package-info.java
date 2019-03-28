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