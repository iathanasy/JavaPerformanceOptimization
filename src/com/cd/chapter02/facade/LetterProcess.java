package com.cd.chapter02.facade;

/**
 * @description: 定义写信的过程
 * @author: Mr.Wang
 * @create: 2019-04-01 20:48
 **/
public interface LetterProcess {
	// 首先是写信的内容
	void writeContext(String context);

	// 其次是写信封
	void fillEnvelope(String address);

	// 把信放在信封里
	void letterInotoEnvelope();

	// 然后邮寄
	void sendLetter();
}
