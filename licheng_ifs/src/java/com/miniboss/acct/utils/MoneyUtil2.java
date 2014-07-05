package com.miniboss.acct.utils;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: shj
 * Date: 2009-7-30
 * Time: 17:38:30
 * To change this template use File | Settings | File Templates.
 */
public class MoneyUtil2 {

    public static void main(String[] args) {
        System.out.println(amountToChinese(100000));
    }

    public static String[] chineseDigits = new String[]{"��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��"};

    /**
     * �ѽ��ת��Ϊ���ֱ�ʾ��������С������������뱣����λ
     *
     * @param amount
     * @return
     */
    public static String amountToChinese(double amount) {

        if (amount > 99999999999999.99 || amount < -99999999999999.99)
            throw new IllegalArgumentException("����ֵ��������Χ (-99999999999999.99 �� 99999999999999.99)��");

        boolean negative = false;
        if (amount < 0) {
            negative = true;
            amount = amount * (-1);
        }

        long temp = Math.round(amount * 100);
        int numFen = (int) (temp % 10); // ��
        temp = temp / 10;
        int numJiao = (int) (temp % 10); //��
        temp = temp / 10;
        //temp Ŀǰ�ǽ�����������

        int[] parts = new int[20]; // ���е�Ԫ���ǰ�ԭ������������ַָ�Ϊֵ�� 0~9999 ֮������ĸ�������
        int numParts = 0; // ��¼��ԭ������������ַָ�Ϊ�˼������֣�ÿ���ֶ��� 0~9999 ֮�䣩
        for (int i = 0; ; i++) {
            if (temp == 0)
                break;
            int part = (int) (temp % 10000);
            parts[i] = part;
            numParts++;
            temp = temp / 10000;
        }

        boolean beforeWanIsZero = true; // ��־��������һ���ǲ��� 0

        String chineseStr = "";
        for (int i = 0; i < numParts; i++) {

            String partChinese = partTranslate(parts[i]);
            if (i % 2 == 0) {
                if ("".equals(partChinese))
                    beforeWanIsZero = true;
                else
                    beforeWanIsZero = false;
            }

            if (i != 0) {
                if (i % 2 == 0)
                    chineseStr = "��" + chineseStr;
                else {
                    if ("".equals(partChinese) && !beforeWanIsZero)   // ������򡱶�Ӧ�� part Ϊ 0������������һ����Ϊ 0���򲻼ӡ��򡱣����ӡ��㡱
                        chineseStr = "��" + chineseStr;
                    else {
                        if (parts[i - 1] < 1000 && parts[i - 1] > 0) // ���"��"�Ĳ��ֲ�Ϊ 0, ��"��"ǰ��Ĳ���С�� 1000 ���� 0�� �������Ӧ�ø����㡱
                            chineseStr = "��" + chineseStr;
                        chineseStr = "��" + chineseStr;
                    }
                }
            }
            chineseStr = partChinese + chineseStr;
        }

        if ("".equals(chineseStr))  // ��������Ϊ 0, ����Ϊ"��Ԫ"
            chineseStr = chineseDigits[0];
        else if (negative) // �������ֲ�Ϊ 0, ����ԭ���Ϊ����
            chineseStr = "��" + chineseStr;

        chineseStr = chineseStr + "Ԫ";

        if (numFen == 0 && numJiao == 0) {
            chineseStr = chineseStr + "��";
        } else if (numFen == 0) { // 0 �֣�������Ϊ 0
            chineseStr = chineseStr + chineseDigits[numJiao] + "��";
        } else { // ���֡�����Ϊ 0
            if (numJiao == 0)
                chineseStr = chineseStr + "��" + chineseDigits[numFen] + "��";
            else
                chineseStr = chineseStr + chineseDigits[numJiao] + "��" + chineseDigits[numFen] + "��";
        }

        return chineseStr;

    }


    /**
     * ��һ�� 0~9999 ֮�������ת��Ϊ���ֵ��ַ���������� 0 �򷵻� ""
     *
     * @param amountPart
     * @return
     */
    private static String partTranslate(int amountPart) {

        if (amountPart < 0 || amountPart > 10000) {
            throw new IllegalArgumentException("���������Ǵ��ڵ��� 0��С�� 10000 ��������");
        }


        String[] units = new String[]{"", "ʰ", "��", "Ǫ"};

        int temp = amountPart;

        String amountStr = new Integer(amountPart).toString();
        int amountStrLength = amountStr.length();
        boolean lastIsZero = true; //�ڴӵ�λ����λѭ��ʱ����¼��һλ�����ǲ��� 0
        String chineseStr = "";

        for (int i = 0; i < amountStrLength; i++) {
            if (temp == 0)  // ��λ��������
                break;
            int digit = temp % 10;
            if (digit == 0) { // ȡ��������Ϊ 0
                if (!lastIsZero)  //ǰһ�����ֲ��� 0�����ڵ�ǰ���ִ�ǰ�ӡ��㡱��;
                    chineseStr = "��" + chineseStr;
                lastIsZero = true;
            } else { // ȡ�������ֲ��� 0
                chineseStr = chineseDigits[digit] + units[i] + chineseStr;
                lastIsZero = false;
            }
            temp = temp / 10;
        }
        return chineseStr;
    }

}