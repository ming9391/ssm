package com.ssm.utils;

import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * txt文件读写
 * 
 * @author swm
 *
 */
public class TxtUtil {
	
	public static void main(String[] args) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
			String dd = format.format(new Date());
			writeTxt("F:/csv/log/","hr_"+dd+".txt","我会写入文件啦\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 读TXT文件
	public static String readTxt() throws IOException {
		String pathname = "D:/input.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
		File filename = new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
		BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
		String line = br.readLine();
		while (line != null) {
			line = br.readLine(); // 一次读入一行数据
		}
		return line;
	}

	// 写TXT文件
	public static void writeTxt(String filePath,String fileName,String content) throws IOException {
		File path = new File(filePath);
		if(!path.exists()){
		 path.mkdirs();
		}
		File writename = new File(filePath+fileName);
		writename.createNewFile(); // 创建新文件
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		out.write(content); // \r\n即为换行
		out.flush(); // 把缓存区内容压入文件
		out.close(); 
		
	}
	

}
