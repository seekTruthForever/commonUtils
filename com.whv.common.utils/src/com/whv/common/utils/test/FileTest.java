package com.whv.common.utils.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.whv.common.utils.Mymap;
import com.whv.common.utils.io.FileTreeUtil;
import com.whv.common.utils.io.FileUtil;
import com.whv.common.utils.json.JsonUtil;

public class FileTest {

	@Test
	public void testFileList() {
		List<File> fileList = FileUtil.getFileList("F:\\PDF电子书", new ArrayList(), ".pdf", "(?i).*java.*");
		StringBuffer sb = new StringBuffer();
		for(File file : fileList) {
			sb.append(file.getParent()+"---"+file.getName()+"---"+FileUtil.getFileSize(file, "MB",4)+"MB");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	@Test
	public void testFileList2() {
		List<File> fileList = FileUtil.getFileList("F:\\PDF电子书", new ArrayList());
		StringBuffer sb = new StringBuffer();
		for(File file : fileList) {
			sb.append(file.getParent()+"---"+file.getName()+"---"+FileUtil.getFileSize(file, "MB")+"MB");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	@Test
	public void testFileList3() {
		List<File> fileList = FileUtil.getFileList("D:\\oracle11g\\oradata\\orcl", new ArrayList(),".DBF",null);
		StringBuffer sb = new StringBuffer();
		for(File file : fileList) {
			sb.append("recover datafile '");
			sb.append(file.getPath());
			sb.append("';");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	@Test
	public void testFileTree1() {
		Mymap mymap = FileTreeUtil.getFileTree("F:\\PDF电子书", null, null);
		System.out.println(JsonUtil.object2JsonStr(mymap.getMymap()));
		System.out.println(JsonUtil.object2JsonStr(mymap.getData("F:\\PDF电子书\\notes")));
		Mymap mymap2 = FileTreeUtil.getFileTree("F:\\PDF电子书", ".pdf", ".*java.*");
		System.out.println(JsonUtil.object2JsonStr(mymap2.getMymap()));
	}

}
