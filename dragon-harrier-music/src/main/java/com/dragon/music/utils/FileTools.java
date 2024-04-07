package com.dragon.music.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class FileTools {

	/**
	 * 拷贝文件（目标文件已经存在则覆盖）
	 * 
	 * @param sourceFile
	 * @param targetFile
	 */
	public static void copy(String sourceFile, String targetFile) {
		try {
			Path source = Paths.get(sourceFile);
			Path target = Paths.get(targetFile);

			// 确保目标文件的父目录存在
			Files.createDirectories(target.getParent());

			// 拷贝文件
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			System.out.println(sourceFile + " copy to " + targetFile + " : success!");
		} catch (Exception e) {
			System.out.println("copy file error!");
			e.printStackTrace();
		}
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileExtension(String file) {
		return FilenameUtils.getExtension(file);
	}

	/**
	 * 获取中文首字母
	 * 
	 * @param name
	 * @return
	 */
	public static String getFirstChar(String name) {
		String firstLetter = null;
		if (StringUtils.isNotBlank(name)) {
			char[] charArray = name.toCharArray();
			if (charArray != null && charArray.length > 0) {
				String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(charArray[0]);
				if (pinyinArray != null && pinyinArray.length > 0) {
					firstLetter = String.valueOf(pinyinArray[0].charAt(0)).toUpperCase();
				}
			}
		}
		return firstLetter;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 1000; i++) {
			String str = "D:\\music\\A\\爱谁谁.mp3";
			copy(str, str.replace("爱谁谁", "爱谁谁" + i));
		}

//		String extension = getFileExtension("D:\\music\\A\\爱谁谁.mp3");
//		System.out.println(extension);
//
//		File file = new File("D:\\music\\A\\爱谁谁.mp3");
//
//		// 爱谁谁.mp3
//		System.out.println(file.getName());
//
//		// D:\music\A\爱谁谁.mp3
//		System.out.println(file.getPath());
//		System.out.println(file.getAbsolutePath());
//		// D:\music\A
//		System.out.println(file.getParent());
//
//		String firstCharacter = getFirstChar("陈龙");
//		System.out.println(firstCharacter);

	}

}
