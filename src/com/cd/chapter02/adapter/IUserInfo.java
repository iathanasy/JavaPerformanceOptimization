package com.cd.chapter02.adapter;

/**
 * @description: 用户信息对象
 * @author: Mr.Wang
 * @create: 2019-04-01 21:04
 **/
public interface IUserInfo {
    //获得用户姓名
    String getUserName();
    //地址
    String getHomeAddress();
    //手机号码
    String getMobileNumber();
    //办公室电话 一般式座机
    String getOfficeTelNumber();
    //职位
    String getJobPosition();
    //家庭电话
    String getHomeTelNumber();
}
