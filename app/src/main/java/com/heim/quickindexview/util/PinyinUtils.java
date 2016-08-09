package com.heim.quickindexview.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音转换工具
 * @author Poplar
 *
 */
public class PinyinUtils {

	/**
	 * 将一个包含汉字的字符串转成拼音
	 * @param string 
	 * @return 拼音
	 */
	public static String getPinyin(String string) {
		//   黑   马 -> HEIMA
		
		// 输出配置
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		// 去掉拼音
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		// 输出大写
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		
		StringBuffer sb = new StringBuffer();
		// 拆分成字符数组
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if(Character.isWhitespace(c)){
				// 去掉空格, 跳过当前循环
				continue;
			}
			// 78ujbg^&*I
			
			if(c >= -128 && c < 127){
				// 肯定不是汉字, 直接拼接
				sb.append(c);
			}else {
				// 将一个字符转换成拼音
				try {
					// 黑 -> HEI , 单 -> DAN, SHAN
					String s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					sb.append(s);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
