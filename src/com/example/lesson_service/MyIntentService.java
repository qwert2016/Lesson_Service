package com.example.lesson_service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.IntentService;
import android.content.Intent;
import android.os.Message;

/**
 * 
 * @author Administrator
 *
 */
public class MyIntentService extends IntentService {

	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {//此处放耗时操作
		String srcTxt="/storage/emulated/0/abc.txt";
		String tgtTxt="/storage/emulated/0/ddd/abc.txt";
		try {
			copyData(srcTxt, tgtTxt, 1);
			System.out.println("MyIntentService.onHandleIntent()文件拷贝完成"+Thread.currentThread().getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 拷贝数据的方法
	 * 
	 * @param srcTxt
	 *            源文件地址
	 * @param tgtTxt
	 *            目标文件地址
	 * @param bufferSize
	 *            “水杯”大小
	 * @throws IOException
	 */
	private void copyData(String srcTxt, String tgtTxt, int bufferSize)
			throws IOException {
		// 创建源文件流
		InputStream is = new FileInputStream(srcTxt);

		File tgtFile = new File(tgtTxt);// 创建一个目标文件的file对象
		if (!tgtFile.getParentFile().exists()) {
			// 如果目标的父路径不存在创建一个，确保拷贝能成功
			tgtFile.getParentFile().mkdirs();
		}
		// 创建目标文件流
		OutputStream os = new FileOutputStream(tgtFile);
		// 获得文件总长度
		int totalAvailable = is.available();
		int hasCopyLen = 0;// 存储已经拷贝的字节数量
		// 创建一个读取字节数组的“小水杯”
		byte buffer[] = new byte[bufferSize];
		int hasRead = -1;// 每次读取的字节数量
		while ((hasRead = is.read(buffer)) != -1) {
			// 将从源文件读取的数据写到目标文件中
			os.write(buffer, 0, hasRead);
			// 累计统计当前拷贝的数据数量
			hasCopyLen += hasRead;
		}
		// 关闭流
		is.close();
		os.close();
	}

}
