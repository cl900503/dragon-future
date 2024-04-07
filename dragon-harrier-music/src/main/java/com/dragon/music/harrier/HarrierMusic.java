package com.dragon.music.harrier;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.dragon.music.utils.FileTools;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;

public class HarrierMusic {

	private static final List<String> FILE_EXTENSION_LIST = Arrays.asList("mp3");
	private static final int DIR_MAX_FILE_COUNTS = 128;

	public static void main(String[] args) {
		sort("D:\\music", "D:\\harrier-music");
	}

	public static void sort(String source, String target) {

		try {

			ListMultimap<String, String> listMultimap = ArrayListMultimap.create();

			Path sourcePath = Paths.get(source);
			Files.walk(sourcePath).filter(Files::isRegularFile).forEach(path -> {
				String fileExtension = FileTools.getFileExtension(path.toString()).toLowerCase();
//				System.out.println(path);
				// 根据文件扩展名过滤文件
				if (FILE_EXTENSION_LIST.contains(fileExtension)) {

					File file = path.toFile();
					// 文件名
					String name = file.getName().replace("." + fileExtension, "");
					// 文件名中文首字母
					String firstChar = FileTools.getFirstChar(name);

					// 按首字母分类
					listMultimap.put(firstChar, path.toString());
				}

			});

			Set<String> keySet = listMultimap.keySet();
			for (String key : keySet) {

				List<String> list = listMultimap.get(key);

				List<List<String>> partition = Lists.partition(list, DIR_MAX_FILE_COUNTS);

				for (int i = 0; i < partition.size(); i++) {
					List<String> filePaths = partition.get(i);
					for (String filePath : filePaths) {
						Path path = Paths.get(filePath);

						String targetPath = filePath.replace(path.getParent().toString(),
								target + "\\" + key + "-" + (i + 1));

						FileTools.copy(filePath, targetPath);

//						System.out.println(i + ":" + path.getParent());
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
