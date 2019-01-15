package com.xuecheng.framework.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URLEncoder;

public class StringUtil {
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Pattern p_html = Pattern.compile("<.*?>");
    private static final int aA = 32;

    public StringUtil() {
    }

    public static ArrayList<String> token2List(StringTokenizer st) {
        if(st != null && st.countTokens() != 0) {
            ArrayList resultList = new ArrayList();

            while(st.hasMoreTokens()) {
                String token = st.nextToken();
                resultList.add(token);
            }

            return resultList;
        } else {
            return new ArrayList();
        }
    }

    /*public static String getCamelStyleString(String from, String delimiter) {
        if(from == null) {
            return null;
        } else if(from.isEmpty()) {
            return "";
        } else if(delimiter != null && !delimiter.isEmpty()) {
            char[] chars = from.toCharArray();
            StringBuilder sbFrom = new StringBuilder("");
            sbFrom.append(chars);
            String copyFrom = sbFrom.toString();
            StringBuilder sbTo = new StringBuilder("");
            if(copyFrom.contains(delimiter)) {
                String[] segments = copyFrom.split("\\" + delimiter);
                String[] var7 = segments;
                int var8 = segments.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    String segment = var7[var9];
                    sbTo.append(segment.substring(0, 1).toUpperCase()).append(segment.substring(1).toLowerCase());
                }
            } else {
                sbTo.append(copyFrom.substring(0, 1).toUpperCase()).append(copyFrom.substring(1).toLowerCase());
            }

            return sbTo.toString();
        } else {
            return from;
        }
    }*/

    /*public static String replace(String strSource, String strFrom, String strTo) {
        if(strSource == null) {
            return null;
        } else {
            int i = 0;
            int i;
            if((i = strSource.indexOf(strFrom, i)) < 0) {
                return strSource;
            } else {
                char[] cSrc = strSource.toCharArray();
                char[] cTo = strTo.toCharArray();
                int len = strFrom.length();
                StringBuilder buf = new StringBuilder(cSrc.length);
                buf.append(cSrc, 0, i).append(cTo);
                i += len;

                int j;
                for(j = i; (i = strSource.indexOf(strFrom, i)) > 0; j = i) {
                    buf.append(cSrc, j, i - j).append(cTo);
                    i += len;
                }

                buf.append(cSrc, j, cSrc.length - j);
                return buf.toString();
            }
        }
    }*/

    public static String merger(String str1, String str2, String split) {
        String str = "";
        TreeSet<String> ts = new TreeSet();
        StringTokenizer st = new StringTokenizer(str1, split);

        while(st.hasMoreTokens()) {
            ts.add(st.nextToken());
        }

        st = new StringTokenizer(str2, split);

        while(st.hasMoreTokens()) {
            ts.add(st.nextToken());
        }

        for(Iterator it = ts.iterator(); it.hasNext(); str = str + (String)it.next() + split) {
            ;
        }

        if(!str.isEmpty()) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

    public static String getRepeated(String str1, String str2, String split) {
        String str = "";
        String[] strs1 = str1.split(split);
        String[] strs2 = str2.split(split);

        for(int i = 0; i < strs1.length; ++i) {
            for(int j = 0; j < strs2.length; ++j) {
                if(strs1[i].equals(strs2[j])) {
                    str = str + strs1[i];
                    break;
                }
            }
        }

        if(str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

    public static boolean contain(String[] array, String s) {
        if(array != null && array.length != 0) {
            if(s != null && !s.isEmpty()) {
                String[] var2 = array;
                int var3 = array.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    String str = var2[var4];
                    if(str.equalsIgnoreCase(s)) {
                        return true;
                    }
                }

                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static StringBuffer list2Strbuf(List<?> list, String delimiter) {
        if(list != null && !list.isEmpty()) {
            String defaultDelimiterChar = ",";
            String delimiterChar = StringUtils.isNotEmpty(delimiter)?delimiter:defaultDelimiterChar;
            StringBuffer sbuf = new StringBuffer("");
            Iterator it = list.iterator();

            while(it.hasNext()) {
                String item = it.next().toString();
                sbuf.append(item).append(delimiterChar);
            }

            sbuf.deleteCharAt(sbuf.length() - 1);
            return sbuf;
        } else {
            return new StringBuffer("");
        }
    }

    public static JSONArray strAry2JsonAry(String[] str_array, String attr) {
        if(str_array != null && str_array.length != 0) {
            JSONArray resultJsonAry = new JSONArray();
            JSONObject jsonItem = null;
            String[] var5 = str_array;
            int var6 = str_array.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String strItem = var5[var7];
                jsonItem = new JSONObject();
                jsonItem.put(attr, strItem);
                resultJsonAry.add(jsonItem);
            }

            return resultJsonAry;
        } else {
            return new JSONArray();
        }
    }

    public static String toUpperCaseFirstOne(String str) {
        StringBuilder sb = new StringBuilder(str);
        return toUpperCaseFirstOne(sb);
    }

    public static String toUpperCaseFirstOne(StringBuilder sb) {
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String toLowerCaseFirstOne(String str) {
        StringBuilder sb = new StringBuilder(str);
        return toLowerCaseFirstOne(sb);
    }

    public static String toLowerCaseFirstOne(StringBuilder sb) {
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String toLowerCamelCase(String str) {
        return toLowerCaseFirstOne(toUpperCamelCase(str)).toString();
    }

    public static String toUpperCamelCase(String str) {
        StringBuilder sb = new StringBuilder();
        String[] var2 = str.split("_");
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String buf = var2[var4];
            sb.append(toUpperCaseFirstOne(buf));
        }

        return sb.toString();
    }

    public static String toSnakeCaseWithPrefix(String prf, String str) {
        int prflen = prf.length();
        int strlen = str.length();
        char[] buf = new char[strlen * 2 + prflen];
        prf.getChars(0, prflen, buf, prflen);
        int bufptr = prflen;

        for(int strptr = 0; strptr < strlen; ++strptr) {
            char c = str.charAt(strptr);
            if(c >= 65 && c <= 90) {
                buf[bufptr++] = 95;
                buf[bufptr++] = (char)(c + 32);
            } else {
                buf[bufptr++] = c;
            }
        }

        return new String(buf, 0, bufptr);
    }

    public static String toSnakeCase(String str) {
        return toSnakeCaseWithPrefix("", str);
    }

    public static String concat(Object... args) {
        return newBuilder(args).toString();
    }

    public static StringBuilder newBuilder(Object... args) {
        StringBuilder sb = new StringBuilder();
        append(sb, args);
        return sb;
    }

    public static void append(StringBuilder sb, Object... args) {
        Object[] var2 = args;
        int var3 = args.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Object arg = var2[var4];
            sb.append(String.valueOf(arg));
        }

    }

    public static String getDoubleString(Object value, Integer n) {
        return getDoubleString(value, n.intValue());
    }

    public static String getDoubleString(Object value, int n) {
        if(value == null) {
            return "";
        } else {
            switch(n) {
            case 0:
                return (new DecimalFormat("#0")).format(value);
            case 1:
                return (new DecimalFormat("#0.0")).format(value);
            case 2:
                return (new DecimalFormat("#0.00")).format(value);
            case 3:
                return (new DecimalFormat("#0.000")).format(value);
            default:
                if(n < 0) {
                    return "";
                } else {
                    char[] cs = new char[n + 3];
                    cs[0] = 35;
                    cs[1] = 48;
                    cs[2] = 46;

                    for(int i = 3; i < cs.length; ++i) {
                        cs[i] = 48;
                    }

                    return (new DecimalFormat(new String(cs))).format(value);
                }
            }
        }
    }

    public static String getDoubleStringWithoutZero(Object value, int n) {
        return getDoubleString(value, n).replaceAll("0+?$", "").replaceAll("[\\.]$", "");
    }

    public static String escapeUrl(String url) {
        if(url == null) {
            return "";
        } else {
            try {
                return URLEncoder.encode(url, "utf-8").replace("+", "%20").replace("%2F", "/");
            } catch (UnsupportedEncodingException var2) {
                throw new AssertionError("utf-8 must be supported", var2);
            }
        }
    }

    public static String cleanUrl(String url) {
        return url == null?"":url.replaceAll("[\\\\\\|\\/\\_\\-\\%\\?\\s]+", " ").trim().replaceAll(" +", "-").toLowerCase();
    }

    public static String escapeXmlText(String xml) {
        return escapeXmlText(xml, false, false);
    }

    public static String escapeXmlText(String xml, boolean sp, boolean br) {
        xml = xml.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
        if(sp) {
            xml.replace(" ", "&nbsp;");
        }

        if(br) {
            xml.replace("\n", "<br>");
        }

        return xml;
    }

    public static boolean isNullor0(BigDecimal bigDecimal) {
        return bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isNullorEmpty(String val) {
        return val == null || val.trim().equals("");
    }

    public static BigDecimal convertToBigDecimal(Object object, Object defaultValue) {
        try {
            return new BigDecimal(String.valueOf(object));
        } catch (Exception var5) {
            try {
                return new BigDecimal(String.valueOf(defaultValue));
            } catch (Exception var4) {
                return null;
            }
        }
    }

    public static long parseLong(String source, long defaultValue) {
        try {
            return Long.parseLong(source);
        } catch (NumberFormatException var4) {
            return defaultValue;
        }
    }

    public static String round2digit(Object num) {
        return getDoubleString(num, 2);
    }

    public static String delHtmlTags(String htmlContent) {
        if(htmlContent == null) {
            return null;
        } else {
            Matcher m_html = p_html.matcher(htmlContent);
            htmlContent = m_html.replaceAll("");
            htmlContent = StringEscapeUtils.escapeHtml4(htmlContent);
            return htmlContent;
        }
    }

    /** @deprecated */
    @Deprecated
    public static String formatDate(Object date, String pattern, String local) {
        Locale loc;
        if("en".equals(local)) {
            loc = Locale.US;
        } else if("cn".equals(local)) {
            loc = Locale.CHINA;
        } else {
            loc = Locale.getDefault();
        }

        return DateUtil.format(DateUtil.parse(date), pattern, loc);
    }

    public static Vector<String> transform(Object src, char[] delimiter) {
        if(src != null && delimiter != null) {
            String _str_ = null;
            if(src instanceof String) {
                _str_ = (String)src;
            } else if(src instanceof StringBuilder) {
                _str_ = src.toString();
            } else {
                _str_ = src.toString();
            }

            StringTokenizer st = new StringTokenizer(_str_, String.valueOf(delimiter));
            if(st != null && st.countTokens() != 0) {
                Vector vec = new Vector(st.countTokens());

                while(st.hasMoreTokens()) {
                    vec.add(st.nextToken());
                }

                return vec;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}