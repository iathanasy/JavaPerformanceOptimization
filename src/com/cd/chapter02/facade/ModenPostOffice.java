package com.cd.chapter02.facade;

/**
 * @description: 添加门面
 * @author: Mr.Wang
 * @create: 2019-04-01 20:58
 **/
public class ModenPostOffice {
    private LetterProcess letterProcess = new LetterProceessImpl();

    //写信 封装 投递 一体化
    public void sendLetter(String context, String address){
        //开始写信
        letterProcess.writeContext(context);
        //信封
        letterProcess.fillEnvelope(address);
        //放到信封中，并封装好
        letterProcess.letterInotoEnvelope();
        //跑到邮局把信塞到邮箱，投递
        letterProcess.sendLetter();
    }
}

