package com.whv.common.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateTool {
	/**
	 * ��õ�ǰ����+ʱ�䣨wuhui 20101227 add��
	 * @return yyyy-mm-dd hh24:mi:ss
	 */
	public static String getCurrDate()
	{
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	//HH��д��������24Сʱ�ƣ������е�MM�����д	
		return sdf.format(d);
	}
	
	/**
	 * ��õ�ǰ����+ʱ�䣨wuhui 20101227 add��
	 * @return yyyy-mm-dd hh24:mi
	 */
	public static String getCurrDateEnPai()
	{
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");	//HH��д��������24Сʱ�ƣ������е�MM�����д	
		return sdf.format(d);
	}
	
	
	
	/**
	 * ��ȡ��ǰ����+ʱ��
	 * @return yyyy��mm��dd�� hh:MM:ss
	 */
	public static String getCurrStrDate()
	{
		String datim = getCurrDate();
		datim = datim.substring(0,4)+"��"+datim.substring(5,7)+"��"+datim.substring(8,10)+"��"+datim.substring(10);
		return datim;
	}
	/**
	 * ��������ݿ��ֶ�����ΪDate��ʱ�������ʱ��
	 * @return
	 */
	public static java.sql.Date getCurrSqlDate()
	{
		return new java.sql.Date(new java.util.Date().getTime());
	}
	/**
	 * ������Ӧ��ʽ�ķ����������ַ���[Ҳ���Է����·�],���Ϊ�գ�Ĭ��ȡyyyyMMdd
	 * @param style like yyyyMMdd/yyyy-MM-dd/yyyy��MM��dd��  /yyyyMM/yyyy-MM��
	 * @return ��Ӧ��ʽ�������ַ���
	 */
	public static String getCurrDate(String style) 
	{	
		if("".equals(style)||style==null){style="yyyyMMdd";}
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return sdf.format(d);
	}
	/**
	 * ��ȡ��ǰ�µĵ�һ��
	 * @param style
	 * @return
	 * @author Wangwanxin
	 */
	public static String getMonthFirstDay(String style) 
	{	
		if("".equals(style)||style==null){style="yyyyMMdd";}
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		String firstday= sdf.format(d).substring(0, 7).concat("-01");
		return firstday;
	}	
	/**
	 * �����ӳ��������8λ�������ַ������磺20031225
	 * getDateByDate("20030101", 1)  -> 20030102
	 * getDateByDate("20030101", -2) -> 20021230
	 */
	public static String getDateByDate(String date, int deltaDay) 
	{
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			c.setTime(sdf.parse(date));
			c.add(Calendar.DATE, deltaDay);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * �����ӳ��������8λ�������ַ������磺20031225
	 * getDateByDate("20030101", 1)  -> 20030102
	 * getDateByDate("20030101", -2) -> 20021230
	 */
	public static String getBeforDateByDate(String date) 
	{
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			c.setTime(sdf.parse(date));
			c.add(Calendar.DATE, -1);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	  * ���������յ�������ʾ���磺2004��2��2��	
	  *
	 */
	public static String getDateExpression(String date) 
	{
		String returnDate = null;
		try {
			if (date.length() != 8 || !date.substring(0, 3).equals("201")) 
			{
				returnDate = date;
			} else {
				String year = date.substring(0, 4);
				String month = date.substring(4, 6);
				if (month.substring(0, 1).equals("0")) 
				{
					month = month.substring(1, 2);
				}
				String day = date.substring(6, 8);
				if (day.substring(0, 1).equals("0")) 
				{
					day = day.substring(1, 2);
				}
				returnDate = year + "��" + month + "��" + day + "��";
			}
			return returnDate;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * ��yyyymmddת���ɲ�ͬ��ʽ������
	 * @param date like 20090101
	 * @param style like yyyy-MM-dd/yyyy��MM��dd��,���Ϊ�գ�Ĭ��ȡyyyyMMdd
	 * @return ��Ӧ��ʽ�������ַ���
	 */
	public static String getStyleDate(String date,String tostyle) 
	{
		if("".equals(tostyle)||tostyle==null){tostyle="yyyyMMdd";}
		String sYear = date.substring(0, 4);
		String sMonth = date.substring(4, 6);
		String sDay = date.substring(6, 8);
		int year = Integer.parseInt(sYear);		
		int monthint = Integer.parseInt(sMonth);		
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, monthint - 1, dday);
		SimpleDateFormat df = new SimpleDateFormat(tostyle);
		return df.format(cal.getTime());		
	}	
	/**
	 * ��ȡ�������ڵ��� n ������ڵ���Ӧ��ʽ�������ַ���,���nΪ��ֵ�����Ƿ����������ڵ�ǰn��
	 * @param day like 20090101
	 * @param n
	 * @param style like yyyyMMdd/yyyy-MM-dd/yyyy��MM��dd��,���Ϊ�գ�Ĭ��ȡyyyyMMdd
	 * @return
	 */
	public static String getNextDay(String day, int n,String style) 
	{
		if (day == null || "".equals(day) || day.length() != 8) 
		{
			throw new RuntimeException("����ȱ�ٱ�Ҫ�Ĳ�����ϵͳ�޷������ƶ������ڻ���.");
		}
		if("".equals(style)||style==null){style="yyyyMMdd";}
		try {
			String sYear = day.substring(0, 4);
			String sMonth = day.substring(4, 6);
			String sDay = day.substring(6, 8);
			int year = Integer.parseInt(sYear);			
			int month = Integer.parseInt(sMonth);			
			int dday = Integer.parseInt(sDay);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, dday);
			cal.add(Calendar.DATE, n);
			SimpleDateFormat df = new SimpleDateFormat(style);
			return df.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException("������������ʱ����ò���������ϵͳ���." + e);
		}
	}
	/**
	 * ��ȡ�������ڵ�ǰdays ������ 
	 * @param startDate �������� ��ʽ��pattern�������
	 * @param days      ��Ҫǰ�����Ƶ�����
	 * @param pattern   �������ڡ���������ڵĸ�ʽ��Ĭ��ΪyyyyMMdd
	 * @return ���� 8λ like 20090101
	 */
	public static String workDays(String startDate, int days,String style)
	{
		if("".equals(style)||style==null){style="yyyyMMdd";}
		if (startDate == null || "".equals(startDate)) 
		{
			throw new RuntimeException("����ȱ�ٱ�Ҫ�Ĳ�����ϵͳ�޷������ƶ������ڻ���.");
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Date date = new Date();
		try{
			date = sdf.parse(startDate);//�Ӹ����ַ����Ŀ�ʼ�����ı���������һ�����ڡ�����Date
		}catch (Exception e){}
        
		Date endDate = date;
		cal.setTime(endDate);
		int count = 0;
		while (count >= days)
		{			
			int day = cal.get(Calendar.DAY_OF_WEEK);//�ж��ǲ�������   �塢			
			if (day < 7 && day > 1)//1: week = "������";7: week = "������"; 
				days++;
			cal.add(Calendar.DATE, -1);//��ǰ��һ��
			endDate = cal.getTime();			
		}
		String datenow=sdf.format(endDate);	
		return datenow;
	}
	/**
	 * ��ȡ�������ڵ�ǰn��ͬ�ڣ���ȥ��ͬ��
	 * @param date like 2009/200901/20090101
	 * @param n
	 * @return �磺2009-->2008��200901-->200801,20090101-->20080101
	 */
	public static String getPreYearSamePeriod(String date,int n)
	{
		String datenow="";
		if(date.length()==6||date.length()==8)
		{
			String dateyear=date.substring(0, 4);			
			int year=Integer.parseInt(dateyear);
			int yeartemp=year-n;
			datenow=String.valueOf(yeartemp)+date.substring(4);
		}else if(date.length()==4){
			int year=Integer.parseInt(date);
			int yeartemp=year-n;
			datenow=String.valueOf(yeartemp);
		}else{
			throw new RuntimeException("����ȱ�ٱ�Ҫ�Ĳ�����ϵͳ�޷�����ָ���Ļ���.");
		}
		return datenow;
	}
	/**
	 * ��ȡ���� �·ݵ���[ǰ] n �·� ���� 6λ like 200901
	 * @param month  like 200901
	 * @param n
	 * @param style �·ݸ�ʽ ��yyyyMMdd yyyy-MM�� Ĭ��ΪyyyyMMdd
	 * @return
	 */
	public static String getNextMonth(String month, int n,String style) 
	{
		if("".equals(style)||style==null){style="yyyyMMdd";}
		if (month == null || "".equals(month) || month.length() != 6) {
			throw new RuntimeException("����ȱ�ٱ�Ҫ�Ĳ�����ϵͳ�޷�����ָ�����·ݻ���.");
		}
		try {
			String sYear = month.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = month.substring(4, 6);
			int mon = Integer.parseInt(sMonth);
			Calendar cal = Calendar.getInstance();
			cal.set(year, mon - 1, 1);
			cal.add(Calendar.MARCH, n);
			SimpleDateFormat df = new SimpleDateFormat(style);
			return df.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException("�����·�����ʱ����ò���������ϵͳ���." + e);
		}
	}
	
	/**
	 * �ж���������Ϊ���ڼ���0�������� 6��������
	 * @param date like 20090101
	 * @return ��������Ӧ������
	 */
	public static int getWeekday(String date) 
	{
		if (date == null || date.length() != 8) {
			throw new RuntimeException("����ȱ�ٱ�Ҫ�Ĳ�����ϵͳ�޷�����ָ���Ļ���.");
		}
		String sYear = date.substring(0, 4);
		String sMonth = date.substring(4, 6);
		String sDay = date.substring(6, 8);
		int year = Integer.parseInt(sYear);		
		int mon = Integer.parseInt(sMonth);		
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekday;
	}
	/**
	 * �õ�������������Ӧ��ũ������
	 * @param date like 20090101
	 * @return
	 */
	public static Lunar getLunar(String date) 
	{
		Date d = getDate(date);
		Lunar lunar = new Lunar();
		lunar.Lunar1(d);
		return lunar;
	}
	/**
	 * �õ�������������Ӧ��Date����
	 * @param date like 20090101
	 * @return
	 */
	private static Date getDate(String date) 
	{
		if (date == null || date.length() != 8) {
			throw new RuntimeException("����ȱ�ٱ�Ҫ�Ĳ�����ϵͳ�޷�����ָ���Ļ���.");
		}
		String sYear = date.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = date.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = date.substring(6, 8);
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		return cal.getTime();
	}
	/**
	 * ��ȡcurrentMonthָ���·�ǰn�����ȵļ���������2006��1�µ�ǰһ������Ϊ2005��4���ȣ�getPreviousQuarter("200702",
	 * 1)��������"20064"��
	 * 
	 * @param currentMonth
	 *            ָ���·ݣ�yyyyMMdd
	 * @param n
	 * @return yyyyQ
	 */
	public static String getPreviousQuarter(String currentMonth, int n) 
	{
		String yearMonth = getNextMonth(currentMonth, -n * 3,"");
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(4, 6);
		String quarter = null;
		if ("09".compareTo(month) < 0)
			quarter = "4";
		else if ("06".compareTo(month) < 0)
			quarter = "3";
		else if ("03".compareTo(month) < 0)
			quarter = "2";
		else
			quarter = "1";

		return year + quarter;
	}
	/**
	 * ��ȡ������ѯ�·�֮�䣨����ʼ�·ݺͽ����·ݣ��������·��б�
	 * @author 
	 * @return
	 */
	public static List InterzoneMonth(String BeginMonth,String EndMonth)
	{
		List MonthList = new ArrayList();
		if(BeginMonth.equals(EndMonth)){
			MonthList.add(BeginMonth);
		}else{
			String beginyear = BeginMonth.substring(0, 4);
			String endyear = EndMonth.substring(0, 4);
			String beginmonth = BeginMonth.substring(4, 6);
			String endmonth = EndMonth.substring(4, 6);
			String month = "";
			int k = 0;
			if(beginyear.equals(endyear)){
				k = Integer.parseInt(endmonth)-Integer.parseInt(beginmonth);
			}else{
				k = (12-Integer.parseInt(beginmonth))+ 
				(12*(Integer.parseInt(endyear)-Integer.parseInt(beginyear)-1))+Integer.parseInt(endmonth);
			}
			for(int i=0;i<=k;i++){
				month = getNextMonth(BeginMonth,i,"");
				MonthList.add(month);
			}
		}
		return MonthList;
	}
	/**
	 * ȡ��һ������ǰforward��Ŀ�ʼ��������
	 * @author 
	 * @return
	 */
	public static String[] getMonthForwardBeginEnd(String month,int forward)
	{
		String[] result=new String[]{"","'"};
		String sYear = month.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = month.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = "01";
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		result[1]=sYear+sMonth+String.valueOf(days-forward);
		cal.add(Calendar.DATE,-forward);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		result[0]=df.format(cal.getTime());
		return result;
	}
	/**
	 * ��ȡ�뼾����ص��·����ڣ���ʼ�£���ʼ���ڣ������£���������
	 * 
	 * @param quarter
	 *            1����һ���ȣ�2���ڶ����ȣ�3���������ȣ�4�����ļ��ȣ�
	 * @return ��ֵ�ֱ�ΪbeginMonth(MM), beginDate(MMdd), endMonth(MM),
	 *         endDate��String(MMdd)
	 */
	public static Map getQuarterStartEndMonthDate(int quarter) {
		HashMap rtn = new HashMap();
		String beginMonth, beginDate, endMonth, endDate;
		switch (quarter) {
		case 1:
			beginMonth = "01";
			beginDate = "0101";
			endMonth = "03";
			endDate = "0331";
			break;
		case 2:
			beginMonth = "04";
			beginDate = "0401";
			endMonth = "06";
			endDate = "0630";
			break;
		case 3:
			beginMonth = "07";
			beginDate = "0701";
			endMonth = "09";
			endDate = "0930";
			break;
		case 4:
			beginMonth = "10";
			beginDate = "1001";
			endMonth = "12";
			endDate = "1231";
			break;
		default:
			throw new RuntimeException("����������ȷ�����뼾����Ϊ��" + quarter + "��");
		}
		rtn.put("beginMonth", beginMonth);
		rtn.put("beginDate", beginDate);
		rtn.put("endMonth", endMonth);
		rtn.put("endDate", endDate);
		return rtn;
	}

//=================����Ϊʱ����صķ���===================================//
	
	/**
	 * ��ȡ��ǰϵͳʱ�� ���� 12:12:12 ���ʷ���
	 * 
	 * @return
	 */
	public static long getCurrentTimeMillis() 
	{
		return System.currentTimeMillis();
	}
	/**
	 * ����8λ�ķ�����ʱ���ַ������磺23595900
	 */
	public static String getCurrTime() 
	{
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss00");
		return sdf.format(d);
	}	
	/**
	 * ��ȡ��ǰϵͳʱ�� ���� format yyyyMMddHHmmss ���еĲ��� ������ format��ʽ��ͬ��ʱ��
	 * 
	 * @return
	 */
	public static String getCurrentTimeMillisAsString(String format) 
	{
		long currTimeM = getCurrentTimeMillis();
		Date date = new Date(currTimeM);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	/**
	 * �õ�һ��ʱ����ӳ�ʱ����ֵ������HHmmss��ʽ��6λ
	 * @param time
	 * @param delay_time
	 * @return
	 */
	public static String getTimeByTime(String time ,int delay_time)
	{
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			c.setTime(sdf.parse(time));
			c.add(Calendar.MINUTE, delay_time);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * ����ʱ�䷵����Ӧ�ĸ�ʽ�ַ���
	 * @param time��8λ��ʱ���ַ���
	 * @param pattern
	 * @return
	 * add by wzq
	 * pattern:��ʽ
	 * 12:10:20(1...24Сʱ��)		hh:mm:ss
	 * 00:10:20(0...23Сʱ��)		HH:mm:ss
	 * 12:10:20	PM(/AM)				hh:mm:ss a
	 * 00:10:20 PM(/AM) 			HH:mm:ss a
	 * 12ʱ10��20�� 				hhʱmm��ss��
	 * 00ʱ10��20��					HHʱmm��ss��
	 */
	public static String formatTime(String time, String pattern) 
	{
		if (time == null)
			return "";
		time = time.trim();
		if (time.length() > 6)
			time = time.substring(0, 6);
		else {
			for (int i = 0; i < 6 - time.trim().length(); i++) {
				time = time.concat("0");
			}
		}
		if (pattern == null || pattern.trim().equals(""))
			return time;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			Date dd = sdf.parse(time);
			sdf = new SimpleDateFormat(pattern);
			return sdf.format(dd);
		} catch (Throwable t) {
			//log.info("HelpTool.formatTime exception ", t);
			return time;
		}
	}
	/**  
     * ��֤ĳһʱ���Ƿ���ĳһʱ���  
     * @param currTime ĳһʱ��  
     * @param String[] timeSlot ĳһʱ���  ��{"8:00", "16:00"}
     * @return true/false  
     */  
    public static boolean isShift(final long currTime, String[] timeSlot)
	{   
		Calendar tempCalendar = Calendar.getInstance(); //���Calendarʵ��		
		tempCalendar.setTimeInMillis(currTime);//currTime����ʱ�䣬�Դ���Ԫ�������������� UTC ��������ʽ  
		
		String[] tmpArray = timeSlot[0].split(":");   //timeSlot[0]:"8:00"
		long startTime, stopTime;   
		//�õ���ʼʱ����ֵ
		//����������ֶ�:Сʱ������
		//Calendar.HOUR_OF_DAY:ָʾһ���е�Сʱ��HOUR_OF_DAY ���� 24 Сʱ��ʱ�ӡ����磬�� ����10:04:15.250 PM ��һʱ�̣�HOUR_OF_DAY Ϊ 22�� 
		tempCalendar.clear(Calendar.HOUR_OF_DAY);   
		//Calendar.MINUTE:ָʾһСʱ�еķ��ӡ����磬�� 10:04:15.250 PM ��һʱ�̣�MINUTE Ϊ 4�� 
		tempCalendar.clear(Calendar.MINUTE);   
		//����Сʱ������
		tempCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tmpArray[0]));   
		tempCalendar.set(Calendar.MINUTE, Integer.parseInt(tmpArray[1]));   

		startTime = tempCalendar.getTimeInMillis();//�õ���ʼʱ����ֵ
		
		//�õ�����ʱ����ֵ
		tmpArray = timeSlot[1].split(":");   //"16:00"
		int stopHour  = Integer.parseInt(tmpArray[0]), stopMinute = Integer.parseInt(tmpArray[1]);   
		if (stopHour == 0) 
		{   
			tempCalendar.add(Calendar.DAY_OF_MONTH, 1);   
		}   
		tempCalendar.clear(Calendar.HOUR_OF_DAY);   
		tempCalendar.clear(Calendar.MINUTE);   
		tempCalendar.set(Calendar.HOUR_OF_DAY, stopHour);   
		tempCalendar.set(Calendar.MINUTE, stopMinute);   
		stopTime = tempCalendar.getTimeInMillis(); 
		
		return ((startTime < currTime && currTime <= stopTime) ? true : false);   
    }   
    
    
//=================����Ϊ���ڱȽϵ���ط���===================================//	
	/**
	 * �ж�ʱ��date1�Ƿ���ʱ��date2֮ǰ
	 * @param time,date2��ʱ���ʽ 2005-4-21 16:16:34
	 * @param boolean
	 * @return
	 */
	public static boolean isDateBefore(String date1,String date2)
	{
	   try{
		   DateFormat df = DateFormat.getDateTimeInstance();
		   return df.parse(date1).before(df.parse(date2));
	   }catch(ParseException e){
		   //System.out.print("[SYS] " + e.getMessage());
		   return false;
	   }
	}
	/**
	 * �жϵ�ǰʱ���Ƿ���ʱ��date2֮ǰ
	 * @param date2��ʱ���ʽ 2005-4-21 16:16:34
	 * @param boolean
	 * @return
	 */	
	public static boolean isDateBefore(String date2)
	{
	   try{
		   Date date1 = new Date();
		   DateFormat df = DateFormat.getDateTimeInstance();
		   return date1.before(df.parse(date2));
	   }catch(ParseException e){
		   //System.out.print(\"[SYS] \" + e.getMessage());
		   return false;
	   }
	}	
	/**
	 * �жϵ�ǰʱ���Ƿ���ʱ��date2֮������
	 * @param date2��ʱ���ʽ 2005-4-21
	 * @param boolean
	 * @return
	 */	
	public static boolean isDateAfterOrEqual(String date2)
	{
	   boolean flag=false;
	   try{
		   DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
		   Date date1 = new Date();
		   if(fmt.parse(fmt.format(date1)).equals(fmt.parse(date2))||fmt.parse(fmt.format(date1)).after(fmt.parse(date2)))
			   flag=true;
	   }catch(ParseException e){
		   //System.out.print("[SYS] " + e.getMessage());
		   flag = false;
	   }
	   return flag;
	}
	
	/**
	 * ���ַ�����ʽ���ڽ���Ϊ���ڶ���
	 * 
	 * @author ��ΰƽ
	 * @date 2011-01-05
	 * @param source ��ʽ��yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getDateTime(String source)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try
		{
			return df.parse(source);
		}
		catch (ParseException e)
		{
			return null;
		}
	}
	
	/**
	 * �����ڸ�ʽ���ַ��������ɸ��������ڵĸ�ʽ
	 * 
	 * @author ���ڿ�
	 * @date 2011-03-08
	 * @param style ���ڵĸ�ʽ��dateString ���ڸ�ʽ���ַ���
	 * @return
	 */
	public static String getDateString(String style,String dateString) 
	{	
		Calendar c = Calendar.getInstance();
		String restr = dateString;
		
		if("".equals(style)||style==null)
		{
			style="yyyy-MM-dd"; 
		}
		if(dateString==null||"".equalsIgnoreCase(dateString)){
			Date d = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat(style);
			restr = sdf.format(d);
		}
		
		return restr;
	}
	
	/**
	 * �õ�ĳ��ĳ�µ�����һ��,���ѡ��ĵ�ǰ���ڣ��򷵻ص�ǰ���ڡ�
	 * ���������ò�
	 */ 
	public static String getLastDayOfMonth(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4));
		
		String new_date = new SimpleDateFormat("yyyyMM").format(new Date());
		
		if(date.equals(new_date)){
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	}
	
	/**
	 * �õ�ָ�����ڵ�������
	 */ 
	public static String getDaysOfMonth(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4));
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()).substring(8);

	}
	
	/**
	 * �õ�ĳ��ĳ�µĵ�һ��,���ѡ��ĵ�ǰ���ڣ��򷵻ص�ǰ���ڡ�
	 * ���������ò�
	 */ 
	public static String getfirstDayOfMonth(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4));
		
		 Calendar cal = Calendar.getInstance();     
         cal.set(Calendar.YEAR, year);     
         cal.set(Calendar.MONTH, month-1);  
         cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));  

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	}

	/**
	 * ����ָ�������ӳ��������ں�ָ����ʽ����������
	 * @param date ָ������
	 * @param format ָ����ʽ ���Ϊ����Ĭ�ϡ�yyyy-MM-dd HH:mm:ss��
	 * @param range ָ������ 1:��,2:����,3:����,4:��,5:ʱ,6:��,7:��
	 * @param rangeNum ������
	 * @return
	 */
	public static String addDate(String date, String format, int range, int rangeNum) 
	{
		int feild = 0;
		if (null == date || "".equals(date)) {
			return "";
		}
		if (rangeNum == 0) {
			return date;
		}
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		// ����
		switch (range) {
			case 1:		// YEAR
				feild = Calendar.YEAR;
				break;

			case 2:		// ����
				feild = Calendar.MONTH;
				rangeNum = rangeNum * 3;
				break;

			case 3:		// MONTH
				feild = Calendar.MONTH;
				break;

			case 4:		// DATE
				feild = Calendar.DATE;
				break;

			case 5:		// HOUR
				feild = Calendar.HOUR;
				break;

			case 6:		// MINUTE
				feild = Calendar.MINUTE;
				break;

			case 7:		// SECOND
				feild = Calendar.SECOND;
				break;

			default:
				return "";
		}
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			c.setTime(sdf.parse(date));
			c.add(feild, rangeNum);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * ��ȡ��ǰ����ǰ�����µ���������
	 * yyyy-MM-dd
	 * ����List����
	 */
	public static List getAllDatesBefore(int month){
		Date dEnd = new Date();//��ǰʱ��
		Calendar cal = Calendar.getInstance();//�õ�����
		cal.setTime(dEnd);//��ǰʱ�丳������
		cal.add(cal.MONTH, month);//����Ϊǰ������
		Date dBegin = new Date();
		dBegin = cal.getTime();//�õ�ǰ�����µ�ʱ��
		List<String> IDate = findDates(dBegin,dEnd);
		return IDate;
	}
	/**
	 * ��ȡ��������֮�����������
	 * yyyy-MM-dd
	 * ����List����
	 */
	public static List<String> findDates(Date dBegin,Date dEnd){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> IDate = new ArrayList();
		//ʹ�ø�����Date���ô�Calendar��ʱ��
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		//���Դ������Ƿ���ָ������֮��
		while(dEnd.compareTo(calBegin.getTime())>=0){
			IDate.add(sdf.format(calBegin.getTime()));
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
		}
		return IDate;
	}
}
