package com.miniboss.acct.utils;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: xgz
 * Date: 2009-7-30
 * Time: 17:38:30
 * To change this template use File | Settings | File Templates.
 */
public class MoneyUtil {

    public static BigDecimal long2Decimal(long money) {
        BigDecimal decimalMoney = BigDecimal.valueOf(money, 2);

        return decimalMoney;
    }

    //有两位小数的钱转换为以分为单位的钱

    public static Long decimal2Long(BigDecimal money) {
        long longMoney = (money.multiply(BigDecimal.valueOf(100))).longValue();
        return longMoney;
    }

    //字符串元转Long分 todo 可以用其他方式来实现 

    public static Long string2Long(String str) {
        long l = 0;
        if (str != null) {
            String s[] = str.split("\\.");
            String temp = s[0];
            if (s.length == 2) {
                String fen = s[1];
                if (fen.length() == 2) {
                    temp += fen;
                } else if (fen.length() == 1) {
                    temp += fen + "0";
                } else if (fen.length() == 0) {
                    temp += "00";
                }
            } else {
                temp += "00";
            }
            l = Long.parseLong(temp);
        }
        return l;
    }

    public static void main(String[] args) {
        System.err.println(changeToBig(new Double("100000")));
    }

    /**
     * 生成人民币大写
     *
     * @param value
     * @return
     */
    public static String changeToBig(double value) {
        String strPrix = "";
        if (value < 0) {
            strPrix = "负";
            value = (-1) * value;
        }
        char[] hunit = {'拾', '佰', '仟'};
        char[] vunit = {'万', '亿'};
        char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};

        BigDecimal big = new BigDecimal(String.valueOf(value));
        String valStr = String.valueOf(big.multiply(new BigDecimal(100))
                .toBigInteger());

        // 转化成字符串
        String head = "0";
        String rail = valStr;
        if (valStr.length() > 2)
            head = valStr.substring(0, valStr.length() - 2);
        if (valStr.length() > 2)
            rail = valStr.substring(valStr.length() - 2);
        rail = String.valueOf(Integer.parseInt(rail));

        // 整数部分
        StringBuffer prefix = new StringBuffer("");
        // 小数部分
        StringBuffer suffix = new StringBuffer("");

        // 处理小数点前面的数
        char[] chDig = head.toCharArray();
        char zero = '0';
        byte zeroSerNum = 0;
        for (int i = 0; i < chDig.length; i++) {
            int idx = (chDig.length - i - 1) % 4;
            int vidx = (chDig.length - i - 1) / 4;
            if (chDig[i] == '0') {
                zeroSerNum++;
                if (zero == '0') {
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix.append(vunit[vidx - 1]);
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0;
            if (zero != '0') {
                prefix.append(zero);
                zero = '0';
            }
            prefix.append(digit[chDig[i] - '0']);
            if (idx > 0)
                prefix.append(hunit[idx - 1]);
            if ((idx == 0 || idx == 1) && vidx > 0) {
                prefix.append(vunit[vidx - 1]);
            }
        }

        if (prefix.length() > 0)
            prefix.append('圆');

        // 处理小数点后面的数
        if (("00".equals(rail) || "0".equals(rail)) && prefix.length() > 0)
            suffix.append("整");
        else {
            if (rail.length() == 1) {
                if (prefix.length() > 0)
                    suffix.append("零")
                            .append(digit[rail.charAt(0) - '0'] + "分");
                else
                    suffix.append(digit[rail.charAt(0) - '0'] + "分");
            } else if ("0".equals(String.valueOf(rail.charAt(1))))
                suffix.append(digit[rail.charAt(0) - '0'] + "角");
            else
                suffix.append(digit[rail.charAt(0) - '0'] + "角"
                        + digit[rail.charAt(1) - '0'] + "分");
        }

        return (strPrix + prefix.append(suffix).toString());
    }

}
