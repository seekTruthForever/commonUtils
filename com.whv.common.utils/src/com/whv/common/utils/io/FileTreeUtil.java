package com.whv.common.utils.io;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.whv.common.utils.Mymap;
/**
 * 目录树操作工具类
 * @author whv
 *
 */
public class FileTreeUtil {
	/**
	 * 获取文件目录树
	 * @param path 文件目录地址，必需参数
	 * @param type 文件类型后缀，可为空
	 * @param name 文件名称正则，可为空
	 * @return
	 */
    public static Mymap getFileTree(String path, String type, String name){
		List<File> fileList = FileUtil.getFileList(path,new ArrayList<File>(),type,name);
		Mymap mymap = new Mymap();
		mymap.init("\\"+File.separator);
		for(final File file:fileList){
		mymap.equipData(
		     file.getAbsolutePath(),
		     new HashMap(){{
		         put("fileName",file.getName());
		         put("fileSize",FileUtil.getFileSize(file));
		         put("filePath",file.getPath());
		     }}
		     );
		}
		return mymap;
    }
}
