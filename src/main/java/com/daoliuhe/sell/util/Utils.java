package com.daoliuhe.sell.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

public class Utils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 验证
     *
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static final boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        List<String> params = new ArrayList<String>();
        params.add(token);
        params.add(timestamp);
        params.add(nonce);
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String temp = params.get(0) + params.get(1) + params.get(2);
        return DigestUtils.sha1Hex(temp).equals(signature);
    }

    /**
     * 读入输入流中的字符串
     *
     * @param in
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static final String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException {
        if (in == null)
            return "";

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }

    /***
     * 解析XML格式的命令
     * @param commandStr
     * @return 包含命令的map
     * @throws DocumentException
     */
    public static Map<String, String> parseCommand(String commandStr) {
        //LOG.debug("parseCommand(String commandStr) " + commandStr);
        Map<String, String> retMap = new HashMap<String, String>();
        Document doc = null;
        try {
            //去除首尾的空格
            commandStr = commandStr.trim();
            doc = DocumentHelper.parseText(commandStr); // 将字符串转为XML
            //LOG.debug("doc: " + doc);
            Element rootElt = doc.getRootElement(); // 获取根节点
            //LOG.debug("rootElt: " + rootElt);
            Iterator<?> it = rootElt.elementIterator();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                //是否该元素只含有text或是空元素
                //if(element.isTextOnly()){
                String name = element.getName();
                String value = element.getText();
                retMap.put(name, value);
                /*
                }else{
					Iterator<?> elementIt = element.elementIterator();
					while(elementIt.hasNext()){
						Element element2 = (Element) elementIt.next();
						//是否该元素只含有text或是空元素
						if(element2.isTextOnly()){
							String name = element2.getName();
							String value = element2.getText();
							retMap.put(name, value);
						}
					}
				}
				 */
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            //LOG.error(e.getMessage());
        }
        return retMap;
    }

    /***
     * 添加前缀和后缀
     * @param str
     * @return
     */
    public static String addCDATA(String str) {
        String PREFIX_CDATA = "<![CDATA[";
        String SUFFIX_CDATA = "]]>";
        StringBuffer retStr = new StringBuffer();
        retStr.append(PREFIX_CDATA).append(str).append(SUFFIX_CDATA);
        return retStr.toString();
    }

    /**
     * 获取开始时间和结束时间之间的日期集合
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> getInterval(String beginDate, String endDate) {
        List<String> ret = new ArrayList<String>();
        try {
            if (StringUtils.hasText(beginDate) && StringUtils.hasText(endDate)) {
                if (beginDate.equals(endDate)) {
                    ret.add(beginDate);
                } else {
                    Calendar startCalendar = Calendar.getInstance();
                    Calendar endCalendar = Calendar.getInstance();
                    Date begin = sdf.parse(beginDate);
                    startCalendar.setTime(begin);
                    Date end = sdf.parse(endDate);
                    endCalendar.setTime(end);
                    while (startCalendar.before(endCalendar)) {
                        String retStr = sdf.format(startCalendar.getTime());
                        ret.add(retStr);
                        startCalendar.add(Calendar.DATE, 1);
                    }

                    if (!ret.contains(endDate)) {
                        ret.add(endDate);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 获取前一天
     *
     * @param dateStr
     * @return
     */
    public static String getBeforeDateStr(String dateStr) {
        String ret = null;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = sdf.parse(dateStr);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            ret = sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        String ret = "";
        if (null != date) {
            ret = sdf1.format(date);
        }
        return ret;
    }

    /**
     * 解析字符串
     * @param dateStr
     * @return
     */
    public static Date pareDate(String dateStr){
        Date ret = null;
        try {
            ret = sdf1.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(pareDate("2017-01-29 10:43:58"));
    }

}
