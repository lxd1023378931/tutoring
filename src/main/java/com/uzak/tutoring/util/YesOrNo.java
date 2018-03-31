package com.uzak.tutoring.util;
/**
 * Y/N
 * @author 梁秀斗
 * @mail 1023378931@qq.com
 * @date 2018年4月1日
 */
public class YesOrNo {
	public static final String YES = "Y";
	public static final String NO = "N";

	public boolean isYes(String str) {
		return YES.equals(str);
	}

	public boolean isNo(String str) {
		return NO.equals(str);
	}
}
