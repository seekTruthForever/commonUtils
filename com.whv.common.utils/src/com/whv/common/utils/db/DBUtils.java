package com.whv.common.utils.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.whv.common.utils.io.PropOptUtil;
import com.whv.common.utils.string.StringUtils;
/**
 * ���ݿ����������
 * @author huawei
 *
 */
public class DBUtils {
	private static Properties jdbcProperties;
	private static DataSource dataSource = null;
	static{
		
	}
	private static void initPropDataSource(String propsFile) {
		if(propsFile==null||"".equals(propsFile)) {
			propsFile = "jdbc.properties";
		}
		PropOptUtil propUtil = new PropOptUtil(propsFile);
		jdbcProperties = propUtil.getProperties();
		String username = jdbcProperties.getProperty("username");
		String password = jdbcProperties.getProperty("password");
		String driver = jdbcProperties.getProperty("driver");
		String url = jdbcProperties.getProperty("url");
		String maxActive = jdbcProperties.getProperty("maxActive");
		maxActive = StringUtils.isInteger(maxActive)?"10":maxActive;
		String initialSize = jdbcProperties.getProperty("initialSize");
		initialSize = StringUtils.isInteger(initialSize)?"2":initialSize;
		//�������ӳض���
		BasicDataSource bds = new BasicDataSource();
		//�������Ӳ���
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setMaxActive(Integer.valueOf(maxActive));//���������
		bds.setInitialSize(Integer.valueOf(initialSize));//��ʼ������
		setDataSource(bds);
	}

	public DBUtils() {
	} 

	/**
	 * ��ȡӦ�ñ�������ݿ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() {
		if (getDataSource() == null)
			DBUtils.initPropDataSource(null);
		// ��¼��ȡ���ӵ������ͷ�����
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn ;
		
	}
	/**
	 * ��ȡӦ�ñ�������ݿ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(String propsFile) {
		if (getDataSource() == null)
			DBUtils.initPropDataSource(propsFile);
		// ��¼��ȡ���ӵ������ͷ�����
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn ;
		
	}
	/**
	 * ��ȡӦ�ñ�������ݿ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(DataSource ds) {
		if (ds == null) {
			DBUtils.initPropDataSource(null);
			ds = getDataSource();
		}
		// ��¼��ȡ���ӵ������ͷ�����
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn ;
		
	}
	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
	}

	public static void close(Statement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt = null;
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
	public static void close(Connection conn,boolean commit) {
		try {
			if (conn != null && !conn.isClosed()) {
				if(commit) conn.commit();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
	public static void close(Connection conn, Statement pstmt, ResultSet rs) {
		close(rs);
		close(pstmt);
		close(conn);
	}

	public static void close(Statement pstmt, ResultSet rs) {
		close(rs);
		close(pstmt);
	}

	public static  Properties getJdbcProperties() {
		return jdbcProperties;
	}

	public static void setJdbcProperties(Properties jdbcProperties) {
		DBUtils.jdbcProperties = jdbcProperties;
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(DataSource dataSource) {
		DBUtils.dataSource = dataSource;
	}
}
