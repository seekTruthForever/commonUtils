package com.whv.common.utils;
import java.io.*;
/**
 * ��������
 */
public class ExportCode {

    static FileWriter fw;
    static BufferedWriter writer;
    static String headTitle = "������������������������������������������������������������������������������";
    static String endline="\n";
    public ExportCode() {}
    public ExportCode(String outputPath)
    {
        String os=System.getProperties().getProperty("os.name");
        if(os.startsWith("win")||os.startsWith("Win"))endline="\r\n";
        try {
            // ���ó�β��׷�ӷ�ʽ
            fw = new FileWriter(outputPath, true);
            writer = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param path  ����·��
     * @param filename Ҫ�����ļ���
     */
    public void WriteToMyFile(String path, String filename) {
        if (!filename.endsWith(".java") )
            return;
        try {
            writer.write(endline+headTitle+endline);
            writer.write("��"+filename+"��");
            writer.write(endline+headTitle+endline);
            BufferedReader br = new BufferedReader(new FileReader(path));
            String buf = br.readLine();
            while (buf != null) {
                writer.write(buf + endline);
                buf = br.readLine();
            }
            // ������ļ�
            writer.flush();
            if (br != null)br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //�ݹ������ǰ�ļ����µ������ļ�
    public void showAllSubFile(String path) {
        File f = new File(path);
        String[] list = f.list();

        for (String s : list) {
            // System.out.println(s);
            File subf = new File(f.getPath() + File.separator + s);
            // �����ǰs����������ļ���
            if (subf.isDirectory())
                showAllSubFile(subf.getPath());
            else {
                WriteToMyFile(subf.getPath(), s);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Export start....");
        /*�����ļ���·��*/
        String inpath="F:\\demo";
        /*����ļ���·��*/
        String outpathString="F:\\exportCode\\demo.java";
        new ExportCode(outpathString).showAllSubFile(inpath);
        System.out.println("Export Complete.");
        // ���ص������
        try {
            if (writer != null)
                writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}