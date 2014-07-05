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

    //����λС����Ǯת��Ϊ�Է�Ϊ��λ��Ǯ

    public static Long decimal2Long(BigDecimal money) {
        long longMoney = (money.multiply(BigDecimal.valueOf(100))).longValue();
        return longMoney;
    }

    //�ַ���ԪתLong�� todo ������������ʽ��ʵ�� 

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
     * ��������Ҵ�д
     *
     * @param value
     * @return
     */
    public static String changeToBig(double value) {
        String strPrix = "";
        if (value < 0) {
            strPrix = "��";
            value = (-1) * value;
        }
        char[] hunit = {'ʰ', '��', 'Ǫ'};
        char[] vunit = {'��', '��'};
        char[] digit = {'��', 'Ҽ', '��', '��', '��', '��', '½', '��', '��', '��'};

        BigDecimal big = new BigDecimal(String.valueOf(value));
        String valStr = String.valueOf(big.multiply(new BigDecimal(100))
                .toBigInteger());

        // ת�����ַ���
        String head = "0";
        String rail = valStr;
        if (valStr.length() > 2)
            head = valStr.substring(0, valStr.length() - 2);
        if (valStr.length() > 2)
            rail = valStr.substring(valStr.length() - 2);
        rail = String.valueOf(Integer.parseInt(rail));

        // ��������
        StringBuffer prefix = new StringBuffer("");
        // С������
        StringBuffer suffix = new StringBuffer("");

        // ����С����ǰ�����
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
            prefix.append('Բ');

        // ����С����������
        if (("00".equals(rail) || "0".equals(rail)) && prefix.length() > 0)
            suffix.append("��");
        else {
            if (rail.length() == 1) {
                if (prefix.length() > 0)
                    suffix.append("��")
                            .append(digit[rail.charAt(0) - '0'] + "��");
                else
                    suffix.append(digit[rail.charAt(0) - '0'] + "��");
            } else if ("0".equals(String.valueOf(rail.charAt(1))))
                suffix.append(digit[rail.charAt(0) - '0'] + "��");
            else
                suffix.append(digit[rail.charAt(0) - '0'] + "��"
                        + digit[rail.charAt(1) - '0'] + "��");
        }

        return (strPrix + prefix.append(suffix).toString());
    }

}
