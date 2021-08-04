package com.whv.common.utils.io;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.whv.common.utils.Mymap;
/**
 * Ŀ¼������������
 * @author whv
 *
 */
public class FileTreeUtil {
	/**
	 * ��ȡ�ļ�Ŀ¼��
	 * @param path �ļ�Ŀ¼��ַ���������
	 * @param type �ļ����ͺ�׺����Ϊ��
	 * @param name �ļ��������򣬿�Ϊ��
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
