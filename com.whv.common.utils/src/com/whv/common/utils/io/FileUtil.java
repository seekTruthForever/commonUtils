package com.whv.common.utils.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.List;

import com.whv.common.utils.MathUtil;

/**
 * �ļ�����������
 * @author huawei
 *
 */
public class FileUtil {
	/**
	 * ��ȡĿ¼�µ������ļ��б�
	 * @param strPath Ŀ¼·��
	 * @param filelist ��ǰ�ļ��б�
	 * @return �ļ��б�
	 */
		public static List<File> getFileList(String strPath,List<File> filelist) {
	        File dir = new File(strPath);
	        File[] files = dir.listFiles(); // ���ļ�Ŀ¼���ļ�ȫ����������
	        if (files != null) {
	            for (int i = 0; i < files.length; i++) {
	                if (files[i].isDirectory()) { // �ж����ļ������ļ���
	                    getFileList(files[i].getAbsolutePath(),filelist); // ��ȡ�ļ�����·��
	                } else { 
	                    filelist.add(files[i]);
	                }
	            }

	        }
	        return filelist;
	    }
/**
 * ��ȡĿ¼�µ������ļ��б�
 * @param strPath Ŀ¼·��
 * @param filelist ��ǰ�ļ��б�
 * @param type �ļ���׺
 * @param nameRegex ����������ʽ
 * @return �ļ��б�
 */
	public static List<File> getFileList(String strPath,List<File> filelist,String type,String nameRegex) {
        File dir = new File(strPath);
        FilesFilter fileFilter = new  FilesFilter(type, nameRegex);
        File[] files = dir.listFiles(fileFilter); // ���ļ�Ŀ¼���ļ�ȫ����������
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) { // �ж����ļ������ļ���
                    getFileList(files[i].getAbsolutePath(),filelist,type, nameRegex); // ��ȡ�ļ�����·��
                } else { 
                    filelist.add(files[i]);
                }
            }

        }
        return filelist;
    }
	/**
	 * ��ȡ����������ļ�
	 * @param fileName �ļ���
	 * @param path �ļ�����Ŀ¼
	 * @return File
	 */
	public static File getRenameFile(String fileName,String path) {
		if(path==null) path="";
		if(path.endsWith(fileName)) {
			path = path.substring(0, path.lastIndexOf(fileName));
		}
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}else {
			if(!dir.isDirectory()) {
				dir = new File(path+"_DIR");
				if(!dir.exists()) {
					dir.mkdirs();
				}
			}
		}
		String type = fileName.substring(fileName.lastIndexOf("."));
		String fileNamePre = fileName.substring(0, fileName.lastIndexOf("."));
		String nameRegex = "\\Q"+fileNamePre+"\\E(\\(\\d+\\))?\\Q"+type+"\\E";
		NamesFilter namesFilter = new  NamesFilter(type, nameRegex);
		File[] files = dir.listFiles(namesFilter);
		String fileNameStr = fileName;
		int i=0;
		for(File file :files) {
			String subName = file.getName();
			if(subName.equals(fileName)) {
				if(i==0) {
					i=1;
				}
			}else {
				int fileNum = Integer.valueOf(subName.substring(subName.lastIndexOf("(")+1, subName.lastIndexOf(")")));
				if(i<=fileNum) {
					i=fileNum+1;
				}
			}
		}
		if(i>0) {
			fileNameStr = fileNamePre+"("+i+")"+type;
		}
		return new File(dir.getPath()+File.separator+fileNameStr);
	}
	/**
	 * ��ȡ��ʽ������ļ���С
	 * @param file �ļ�
	 * @param format ��ʽ��MB��KB��B��GB
	 * @param scale ��ȷλ��
	 * @return ��ʽ������ַ���
	 */
	public static String getFileSize(File file,String format,int scale) {
		format = format==null||format.isEmpty()?"MB":format;
		String fileSize = "0";
		if(file != null && file.exists()) {
			long fileLength = file.length();
			double unitVal = 1024;
			if("MB".equals(format)) {
				fileSize = MathUtil.div(fileLength+"", Math.pow(unitVal, 2)+"", scale);
			}else if("KB".equals(format)) {
				fileSize = MathUtil.div(fileLength+"", unitVal+"", scale);
			}else if("GB".equals(format)) {
				fileSize = MathUtil.div(fileLength+"", Math.pow(unitVal, 3)+"", scale);
			}else if("B".equals(format)){
				fileSize = fileLength+"";
			}else {
				fileSize = MathUtil.div(fileLength+"", Math.pow(unitVal, 2)+"", scale);
			}
		}
		return fileSize;
	}
	/**
	 * ��ȡ��ʽ������ļ���С����ȷ��С�������λ
	 * @param file �ļ�
	 * @param format ��ʽ��MB��KB��B��GB
	 * @return ��ʽ������ַ���
	 */
	public static String getFileSize(File file,String format) {
		return getFileSize(file, format, 2);
	}
	/**
	 * �ļ������б������
	 * @author huawei
	 *
	 */
	protected static class NamesFilter implements FilenameFilter{  
        private String type;  
        private String nameRegex;
        public NamesFilter(){  
            this.type = "";  
            this.nameRegex = ".*";
        }  
        /**
         * �ļ������б���˹��췽��
         * @param type �ļ���չ��
         * @param nameRegex �ļ�����ƥ������
         */
        public NamesFilter(String type,String nameRegex){  
            this.type = type==null?"":type;  
            this.nameRegex = nameRegex==null?".*":nameRegex;
        }  
        public boolean accept(File dir,String name){  
            return name.endsWith(type)&&name.matches(nameRegex);  
        }  
    }  
	/**
	 * �ļ��б������
	 * @author huawei
	 *
	 */
	protected static class FilesFilter implements FileFilter{  
        private String type;  
        private String nameRegex;
        public FilesFilter(){  
            this.type = "";  
            this.nameRegex = ".*";
        }  
        /**
         * �ļ��б���˹��췽��
         * @param type �ļ���չ��
         * @param nameRegex �ļ�����ƥ������
         */
        public FilesFilter(String type,String nameRegex){  
            this.type = type==null?"":type;  
            this.nameRegex = nameRegex==null?".*":nameRegex;
        }  
		public boolean accept(File pathname) {
			return pathname.isDirectory()||
					(pathname.getName().endsWith(type)&&pathname.getName().matches(nameRegex));
		}  
    }  
}
