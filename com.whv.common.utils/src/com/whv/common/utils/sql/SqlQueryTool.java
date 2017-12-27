package com.whv.common.utils.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.whv.common.utils.db.DBUtils;
/**
 * jdbc��ѯ������
 * @author huawei
 *
 */
public class SqlQueryTool {
	/**
	 * ��ѯ
	 * @param selectSql ��ѯsql
	 * @param params ����
	 * @return List
	 */
	public static List query(String selectSql,Object[] params) 
	{
		List<Map<String,Object>> rsltList =new ArrayList<Map<String,Object>>();
		if (selectSql != null && !"".equals(selectSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection();
				pstmt = conn.prepareStatement(selectSql);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData md = rs.getMetaData(); //��ý�����ṹ��Ϣ,Ԫ����  
		        int columnCount = md.getColumnCount();   //�������   
				while(rs.next()){
					 Map<String,Object> rowData = new HashMap<String,Object>();  
			            for (int i = 1; i <= columnCount; i++) {  
			                rowData.put(md.getColumnName(i), rs.getObject(i));  
			            }  
			            rsltList.add(rowData);  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.close(conn, pstmt, rs);
			}          
		}
		return rsltList;
	}
	/**
	 * ��ѯ
	 * @param dataSource ����Դ
	 * @param selectSql ��ѯsql
	 * @param params ��ѯ����
	 * @return List
	 */
	public static List query(DataSource dataSource,String selectSql,Object[] params) 
	{
		List<Map<String,Object>> rsltList =new ArrayList<Map<String,Object>>();
		if (selectSql != null && !"".equals(selectSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection(dataSource);
				pstmt = conn.prepareStatement(selectSql);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData md = rs.getMetaData(); //��ý�����ṹ��Ϣ,Ԫ����  
		        int columnCount = md.getColumnCount();   //�������   
				while(rs.next()){
					 Map<String,Object> rowData = new HashMap<String,Object>();  
			            for (int i = 1; i <= columnCount; i++) {  
			                rowData.put(md.getColumnName(i), rs.getObject(i));  
			            }  
			            rsltList.add(rowData);  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.close(conn, pstmt, rs);
			}          
		}
		return rsltList;
	}
	/**
	 * ��ѯ
	 * @param selectSql ��ѯsql
	 * @param params ����
	 * @param fetchSize ÿ��ȡ����������
	 * @return List
	 */
	public static List query(String selectSql,Object[] params,int fetchSize) 
	{
		List<Map<String,Object>> rsltList =new ArrayList<Map<String,Object>>();
		if (selectSql != null && !"".equals(selectSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(selectSql);
				pstmt.setFetchSize(fetchSize);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData md = rs.getMetaData(); //��ý�����ṹ��Ϣ,Ԫ����  
		        int columnCount = md.getColumnCount();   //�������   
				while(rs.next()){
					 Map<String,Object> rowData = new HashMap<String,Object>();  
			            for (int i = 1; i <= columnCount; i++) {  
			                rowData.put(md.getColumnName(i), rs.getObject(i));  
			            }  
			            rsltList.add(rowData);  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.close(pstmt, rs);
				DBUtils.close(conn, true);
			}          
		}
		return rsltList;
	}
	/**
	 * ��ѯ
	 * @param dataSource ����Դ
	 * @param selectSql ��ѯsql
	 * @param params ��ѯ����
	 * @param fetchSize ÿ��ȡ����������
	 * @return List
	 */
	public static List query(DataSource dataSource,String selectSql,Object[] params,int fetchSize) 
	{
		List<Map<String,Object>> rsltList =new ArrayList<Map<String,Object>>();
		if (selectSql != null && !"".equals(selectSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection(dataSource);
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(selectSql);
				pstmt.setFetchSize(fetchSize);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData md = rs.getMetaData(); //��ý�����ṹ��Ϣ,Ԫ����  
		        int columnCount = md.getColumnCount();   //�������   
				while(rs.next()){
					 Map<String,Object> rowData = new HashMap<String,Object>();  
			            for (int i = 1; i <= columnCount; i++) {  
			                rowData.put(md.getColumnName(i), rs.getObject(i));  
			            }  
			            rsltList.add(rowData);  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.close(pstmt, rs);
				DBUtils.close(conn, true);
			}          
		}
		return rsltList;
	}
	/**
	 * ��ѯǰ������¼
	 * @param selectSql ��ѯsql
	 * @param params ��ѯ����
	 * @param num ��ѯ������
	 * @return List
	 */
	public static List queryFew(String selectSql,Object[] params,int num) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (");
		sb.append(selectSql);
		sb.append(") where rownum < ").append(num+1);
		return query(sb.toString(), params);
	}
	/**
	 * ��ѯǰ������¼
	 * @param dataSource ����Դ
	 * @param selectSql ��ѯsql
	 * @param params ��ѯ����
	 * @param num ��ѯ������
	 * @return List
	 */
	public static List queryFew(DataSource dataSource,String selectSql,Object[] params,int num) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (");
		sb.append(selectSql);
		sb.append(") where rownum < ").append(num+1);
		return query(dataSource,sb.toString(), params);
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param selectSql ��ѯsql
	 * @param params ��ѯ����
	 * @param page ��ҳ��Ϣ
	 * @return List
	 */
	public static List queryPage(String selectSql,Object[] params,PageInfo page) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM (SELECT T.*,ROWNUM RN FROM (");
		sb.append(selectSql);
		sb.append(") T  where rownum<")
		.append(page.getCurrPage()*page.getPageSize()+1)
		.append(") WHERE RN>")
		.append((page.getCurrPage()-1)*page.getPageSize());
		return query(sb.toString(), params);
	}
	/**
	 * ��ҳ��ѯ
	 * @param dataSource ����Դ
	 * @param selectSql ��ѯsql
	 * @param params ��ѯ����
	 * @param page ��ҳ��Ϣ
	 * @return List
	 */
	public static List queryPage(DataSource dataSource,String selectSql,Object[] params,PageInfo page) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM (SELECT T.*,ROWNUM RN FROM (");
		sb.append(selectSql);
		sb.append(") T  where rownum<")
		.append(page.getCurrPage()*page.getPageSize()+1)
		.append(") WHERE RN>")
		.append((page.getCurrPage()-1)*page.getPageSize());
		return query(dataSource,sb.toString(), params);
	}
}
