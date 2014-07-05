package com.miniboss.util

import java.text.NumberFormat
import java.text.DecimalFormat

/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2010-1-22
 * Time: 14:10:11
 * To change this template use File | Settings | File Templates.
 */

public class StringUtil {

    /**
     * ������###,###,###.00��ʽ���
     */
    public static String formatCurrency(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);

        String pattern = "###,##0.00";
        df.applyPattern(pattern);
		//Method formatDouble������������,�����##.#05����ȥ5������,������java��BUG
        return df.format(formatDouble(amount,2));
    }

    /**
     * ������###,###,###.00��ʽ���
     */
    public static String formatCurrency(double amount , int decimal) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);

        String pattern = "###,##0.0";
        for(int i = 1; i < decimal ; i++){
            pattern += '0';
        }
        df.applyPattern(pattern);
		//Method formatDouble������������,�����##.#05����ȥ5������,������java��BUG
        return df.format(formatDouble(amount,decimal));
    }

    /**
     * Method formatDouble.
     * @param value  Ҫ�����������ֵ
     * @param precision  ����
     * @return double
     */
    public static double formatDouble(double value, int precision) {
        return Math.round(value * Math.pow(10, (double) precision)) / Math.pow(10,
            (double) precision);
    }

    /**
	 * ��һ��ֵ�� String ת�� BigDecimal
	 * @param inValue
	 * @return  BigDecimal
	 */
	public static final BigDecimal str2BigDecimal(String inValue){
		return new BigDecimal(inValue);
	}

    public static def long2String(def value) {
        String v = ""
        if (value != null) {
            try {
                v = new DecimalFormat("#.##").format(value / (long) 100.00)
            } catch (Exception e) {
            }
        }
        return v
    }

    

}