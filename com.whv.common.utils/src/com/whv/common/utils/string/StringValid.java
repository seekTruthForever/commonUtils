package com.whv.common.utils.string;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * �ַ�����֤�����ࣺ
 * 1��java������ʽ��ƥ����������䣬�ֻ����������ǳƣ����֤�ţ����п��ŵȣ�

2������6λ�������

3����url���ַ������б���ͽ���

4����ȡ�ͻ���ip��ַ

5����ȡϵͳ��ǰʱ��

6������32λ���벻������

7������MD5����

8��ͨ�����֤��ȡ�Ա�

9��ͨ�����֤��ȡ����

10���ֻ����м�4λ�滻���Ǻ�

11�������ַ���Ǻ�

12�������������
 */
public class StringValid {
    private static SecureRandom random = new SecureRandom();

    public static final Pattern MAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    public static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");

    public static final Pattern NAME_PATTERN = Pattern.compile("^[\\u4E00-\\u9FBF][\\u4E00-\\u9FBF(.|��)]{0,13}[\\u4E00-\\u9FBF]$");

    public static final Pattern NICKNAME_PATTERN = Pattern.compile("^((?!\\d{5})[\\u4E00-\\u9FBF(.|��)|0-9A-Za-z_]){2,11}$");

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[\\s\\S]{6,30}$");

    public static final Pattern CODE_PATTERN = Pattern.compile("^0\\d{2,4}$");

    public static final Pattern POSTCODE_PATTERN = Pattern.compile("^\\d{6}$");

    public static final Pattern ID_PATTERN = Pattern.compile("^\\d{6}(\\d{8}|\\d{11})[0-9a-zA-Z]$");

    public static final Pattern BANK_CARD_PATTERN = Pattern.compile("^\\d{16,30}$");

    /**
     * ����6λ�������, �����ֻ�������֤��.
     *
     * @return 6λ�������
     */
    public static int random() {
        int x = Math.abs(random.nextInt(899999));

        return x + 100000;
    }

    /**
     * ��url�ַ������б���.
     *
     * @param url Ҫ�����url�ַ���
     * @return �������ַ���
     */
    public static String urlEncoder(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        try {
            return java.net.URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��url�ַ������н���.
     *
     * @param url Ҫ�����url�ַ���
     * @return �������ַ���
     */
    public static String urlDecoder(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��֤�ַ����ǲ�������.
     *
     * @param email Ҫ��֤������
     * @return �Ƿ���ȷ����
     */
    public static boolean validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        Matcher m = MAIL_PATTERN.matcher(email);
        return m.matches();
    }

    /**
     * ��֤�ַ����ǲ����ֻ���.
     *
     * @param mobile Ҫ��֤���ֻ���
     * @return �Ƿ���ȷ�ֻ���
     */
    public static boolean validateMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(mobile);
        return m.matches();
    }

    /**
     * ��֤���֤�Ƿ���Ч.
     *
     * @param idCardNumber Ҫ��֤�����֤
     * @return �Ƿ���ȷ���֤
     */
    public static boolean validateId(String idCardNumber) {
        if (StringUtils.isEmpty(idCardNumber)) {
            return false;
        }
        Matcher m = ID_PATTERN.matcher(idCardNumber);
        return m.matches() && IdCardUtils.validateCard(idCardNumber);
    }

    /**
     * ��֤�����Ƿ���Ч.
     *
     * @param name Ҫ��֤������
     * @return �Ƿ���ȷ����
     */
    public static boolean validateName(String name) {
        if (StringUtils.isEmpty(name) || name.replaceAll("[^.��]", "").length() > 1) {
            return false;
        }
        Matcher m = NAME_PATTERN.matcher(name);
        return m.matches();
    }

    /**
     * ��֤�ǳ��Ƿ���Ч.
     *
     * @param nickname Ҫ��֤���ǳ�
     * @return �Ƿ���ȷ�ǳ�
     */
    public static boolean validateNickname(String nickname) {

        //���� ���ܰ���5������ ������Ӣ�ĺ����� 2-11λ
        if (StringUtils.isEmpty(nickname) || nickname.replaceAll("[^0-9]", "").length() > 4) {
            return false;
        }
        Matcher m = NICKNAME_PATTERN.matcher(nickname);
        return m.matches();
    }

    /**
     * ��֤�����ʽ�Ƿ���Ч.
     *
     * @param password Ҫ��֤������
     * @return �Ƿ���ȷ�����ʽ
     */
    public static boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        Matcher m = PASSWORD_PATTERN.matcher(password);
        return m.matches();
    }

    /**
     * ��֤�����Ƿ���Ч.
     *
     * @param code Ҫ��֤������
     * @return �Ƿ���ȷ���֤
     */
    public static boolean validateCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return false;
        }
        Matcher m = CODE_PATTERN.matcher(code);
        return m.matches();
    }

    /**
     * ��֤���������Ƿ���Ч.
     *
     * @param postcode Ҫ��֤����������
     * @return �Ƿ���ȷ��������
     */
    public static boolean validatePostcode(String postcode) {
        if (StringUtils.isEmpty(postcode)) {
            return false;
        }
        Matcher m = POSTCODE_PATTERN.matcher(postcode);
        return m.matches();
    }

    /**
     * ��֤���п��Ƿ���Ч.
     *
     * @param bankCardNumber Ҫ��֤�����п���
     * @return �Ƿ���ȷ���п���
     */
    public static boolean validateBankCardNumber(String bankCardNumber) {
        if (StringUtils.isEmpty(bankCardNumber)) {
            return false;
        }
        Matcher m = BANK_CARD_PATTERN.matcher(bankCardNumber);
        return m.matches();
    }

    /**
     * ��ȡ�ͻ���IP��ַ.
     *
     * @param request request����
     * @return ip��ַ
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("Cdn-Src-Ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.hasText(ip)) {
            return StringUtils.tokenizeToStringArray(ip, ",")[0];
        }
        return "";
    }

    /**
     * ��ȡ��ǰϵͳʱ��,��java.sql.Timestamp���ͷ���.
     *
     * @return ��ǰʱ��
     */
    public static Timestamp getTimestamp() {
        Timestamp d = new Timestamp(System.currentTimeMillis());
        return d;
    }

    /**
     * ����32λ����,��������
     *
     * @return uuid��
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid.toUpperCase();
    }

    /**
     * ͨ�����֤��ȡ�Ա�
     *
     * @param idNumber ���֤��
     * @return �����Ա�, 0 ���� , 1 �� 2 Ů
     */
    public static Integer getGenderByIdNumber(String idNumber) {

        int gender = 0;

        if (idNumber.length() == 15) {
            gender = Integer.parseInt(String.valueOf(idNumber.charAt(14))) % 2 == 0 ? 2 : 1;
        } else if (idNumber.length() == 18) {
            gender = Integer.parseInt(String.valueOf(idNumber.charAt(16))) % 2 == 0 ? 2 : 1;
        }

        return gender;

    }

    /**
     * ͨ�����֤��ȡ����
     *
     * @param idNumber ���֤��
     * @return ��������, ��ʽΪ yyyy-MM-dd ���ַ���
     */
    public static String getBirthdayByIdNumber(String idNumber) {

        String birthday = "";

        if (idNumber.length() == 15) {
            birthday = "19" + idNumber.substring(6, 8) + "-" + idNumber.substring(8, 10) + "-" + idNumber.substring(10, 12);
        } else if (idNumber.length() == 18) {
            birthday = idNumber.substring(6, 10) + "-" + idNumber.substring(10, 12) + "-" + idNumber.substring(12, 14);
        }

        return birthday;

    }


    /**
     * ͨ�����֤��ȡ����
     *
     * @param idNumber ���֤��
     * @return ��������
     */
    public static Integer getAgeByIdNumber(String idNumber) {

        String birthString = getBirthdayByIdNumber(idNumber);
        if (StringUtils.isEmpty(birthString)) {
            return 0;
        }

        return getAgeByBirthString(birthString);

    }

    /**
     * ͨ�����֤��ȡ����
     *
     * @param idNumber     ���֤��
     * @param isNominalAge �Ƿ�Ԫ�������䣬����1��1�ռ�һ�� true : �� false : ��
     * @return ��������
     */
    public static Integer getAgeByIdNumber(String idNumber, boolean isNominalAge) {

        String birthString = getBirthdayByIdNumber(idNumber);
        if (StringUtils.isEmpty(birthString)) {
            return 0;
        }

        return getAgeByBirthString(birthString, isNominalAge);

    }

    /**
     * ͨ���������ڻ�ȡ����
     *
     * @param birthDate ��������
     * @return ��������
     */
    public static Integer getAgeByBirthDate(Date birthDate) {

        return getAgeByBirthString(new SimpleDateFormat("yyyy-MM-dd").format(birthDate));

    }


    /**
     * ͨ�������ַ�����ȡ����
     *
     * @param birthString �����ַ���
     * @return ��������
     */
    public static Integer getAgeByBirthString(String birthString) {

        return getAgeByBirthString(birthString, "yyyy-MM-dd");

    }

    /**
     * ͨ�������ַ�����ȡ����
     *
     * @param birthString  �����ַ���
     * @param isNominalAge �Ƿ�Ԫ�������䣬����1��1�ռ�һ�� true : �� false : ��
     * @return ��������
     */
    public static Integer getAgeByBirthString(String birthString, boolean isNominalAge) {

        return getAgeByBirthString(birthString, "yyyy-MM-dd", isNominalAge);

    }

    /**
     * ͨ�������ַ�����ȡ����
     *
     * @param birthString �����ַ���
     * @param format      �����ַ�����ʽ,Ϊ����Ĭ��"yyyy-MM-dd"
     * @return ��������
     */
    public static Integer getAgeByBirthString(String birthString, String format) {
        return getAgeByBirthString(birthString, "yyyy-MM-dd", false);
    }


    /**
     * ͨ�������ַ�����ȡ����
     *
     * @param birthString  �����ַ���
     * @param format       �����ַ�����ʽ,Ϊ����Ĭ��"yyyy-MM-dd"
     * @param isNominalAge �Ƿ�Ԫ�������䣬����1��1�ռ�һ�� true : �� false : ��
     * @return ��������
     */
    public static Integer getAgeByBirthString(String birthString, String format, boolean isNominalAge) {

        int age = 0;

        if (StringUtils.isEmpty(birthString)) {
            return age;
        }

        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }

        try {

            Calendar birthday = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            birthday.setTime(sdf.parse(birthString));
            age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
            if (!isNominalAge) {
                if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH) ||
                        (today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH) &&
                                today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH))) {
                    age = age - 1;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return age;

    }

    /**
     * �ֻ����м���λ�滻���Ǻ�
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (validateMobile(mobile)) {
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return mobile;
    }

    /**
     * �ֻ����м���λ�Զ����滻
     *
     * @param mobile
     * @param transCode �м���λĿ��ֵ ��GXJF ��136GXJF1111
     * @return
     */
    public static String maskMobile(String mobile, String transCode) {
        if (validateMobile(mobile)) {
            transCode = StringUtils.isEmpty(transCode) ? "****" : transCode;
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", String.format("$1%s$2", transCode));
        }
        return mobile;
    }

    /**
     * �����ַ���Ǻ�
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (validateEmail(email)) {
            String userName = email.substring(0, email.indexOf("@"));
            int len = userName.length();
            if (len >= 5) {
                int total = len - 3;
                int half = total / 2;
                int start = half;
                int end = len - half;
                if (total % 2 != 0) {
                    end = end - 1;
                }
                StringBuilder sb = new StringBuilder(email);
                for (int i = start; i < end; i++) {
                    sb.setCharAt(i, '*');
                }
                return sb.toString();
            }
        }
        return email;
    }

    /**
     * �˺��м���λ�Զ����滻
     *
     * @param account
     * @return
     */
    public static String maskTradeAccount(String account) {
        return account.replaceAll("(\\d{7})\\d*(\\d{4})", "$1****$2");
    }


    /**
     * ��֤�Ƿ�Ϊ����
     *
     * @param date
     * @return
     */
    public static boolean validateDate(String date) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * ��ȡʱ���,��Ϊ������ID
     */
    private static final Lock lock = new ReentrantLock();   //������

    public static long getUniqueLong() {
        long l;
        lock.lock();
        try {
            l = System.currentTimeMillis();
        } finally {
            lock.unlock();
        }
        return l;
    }

    /**
     * ������url�����еļ�ֵ��
     * �� "index.jsp?Action=del&id=123"��������Action:del,id:123����map��
     *
     * @param URL url��ַ
     * @return url�����������
     */
    public static String getUrlParams(String URL, String key) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = null;
        java.net.URL aURL = null;
        try {
            aURL = new URL(URL);
            strUrlParam = aURL.getQuery();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (strUrlParam == null) {
            return "";
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            if (arrSplitEqual.length > 1) {
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (!StringUtils.isEmpty(arrSplitEqual[0])) {
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        if (mapRequest.containsKey(key)) {
            try {
                return URLDecoder.decode(mapRequest.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e) {

            }
        }
        return "";
    }



    /**
     * �����������
     *
     * @param pwd_len ���ɵ�������ܳ���
     * @return ������ַ���
     */
    public static String genRandomNum(int pwd_len) {
        // 35����Ϊ�����Ǵ�0��ʼ�ģ�26����ĸ+10������
        final int maxNum = 36;
        int i; // ���ɵ������
        int count = 0; // ���ɵ�����ĳ���
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // �����������ȡ����ֵ����ֹ���ɸ�����
            i = Math.abs(r.nextInt(maxNum)); // ���ɵ������Ϊ36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }



}
