package com.cd.chapter05.proxy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static void main(String[] args) {
		initLogRecord();
		ProxyHttpClient.getInstance().startCrawl();
	}
	
	private static void initLogRecord(){
        Properties props = null;  
        FileInputStream fis = null;  
        try {  
            props = new Properties();  
            fis = new FileInputStream("log4j.properties");  
            props.load(fis);  
            PropertyConfigurator.configure(props);
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (fis != null)  
                try {  
                    fis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            fis = null;  
        }  
}
}
