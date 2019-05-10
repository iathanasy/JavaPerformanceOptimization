package com.cd.chapter02.facade;

/**
 * @description:
 * @author: Mr.Wang
 * @create: 2019-04-01 20:52
 **/
public class LetterProceessImpl implements LetterProcess {
	@Override
	public void writeContext(String context) {
		System.out.println("填写信内容：" + context);
	}

	@Override
	public void fillEnvelope(String address) {
		System.out.println("填写收件人地址及姓名：" + address);
	}

	@Override
	public void letterInotoEnvelope() {
		System.out.println("把信放在信封中...");
	}

	@Override
	public void sendLetter() {
		System.out.println("邮递信件...");
	}
}
