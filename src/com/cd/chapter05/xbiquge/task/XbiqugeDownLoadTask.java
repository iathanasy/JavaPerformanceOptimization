package com.cd.chapter05.xbiquge.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.jsoup.helper.StringUtil;

import com.cd.chapter05.proxy.common.utlis.Constants;
import com.cd.chapter05.xbiquge.entity.Chapter;

/**
 * 下载任务线程池
 * @author cd
 * @date 2019年5月10日 上午8:58:24
 * @desc
 */
public class XbiqugeDownLoadTask implements Runnable{
	
	
	private Chapter chapter;
	
	public XbiqugeDownLoadTask(Chapter chapter){
		this.chapter = chapter;
	}

	@Override
	public void run() {
		if(chapter != null){
			mkdirFile(chapter.getName(), chapter.getTitle(), chapter.getContext());
		}
	}

	
	/**
	 * 创建.txt
	 * 
	 * @param fileName 小说名称
	 *            文件名
	 */
	private void mkdirFile(String fileName, String title, String context) {
		// 创建文件夹
		File file = new File(Constants.downLoadDir + fileName);
		if (!file.exists()) {
			file.mkdirs();// 创建文件
		}
		file = new File(file.getPath()+"/"+ title +".txt");
		if(!file.exists()){
			 try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//写入文件
		FileOutputStream os = null;
		try {
			 os = new FileOutputStream(file);
			 try {
				os.write((title + "\n").getBytes());
				if(!StringUtil.isBlank(context)){
					os.write(context.getBytes());
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
