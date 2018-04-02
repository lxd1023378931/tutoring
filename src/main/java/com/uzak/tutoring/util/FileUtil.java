package com.uzak.tutoring.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 处理文件工具类
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年4月2日
 */
public class FileUtil {
	public String read(String fileName) {
		BufferedReader br = null;
		try {
			File file = new File(fileName);
			if (!file.exists() || !file.isFile()) {
				return "";
			}
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public boolean write(String fileName, String content) {
		return write(fileName, content, false);
	}

	public boolean write(String fileName, String content, boolean append) {
		boolean flag = false;
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(fileName, append));
			bos.write(content.getBytes());
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
