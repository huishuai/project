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
     * 货币以###,###,###.00格式输出
     */
    public static String formatCurrency(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);

        String pattern = "###,##0.00";
        df.applyPattern(pattern);
		//Method formatDouble用于四舍五入,解决在##.#05的舍去5的问题,可能是java的BUG
        return df.format(formatDouble(amount,2));
    }

    /**
     * 货币以###,###,###.00格式输出
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
		//Method formatDouble用于四舍五入,解决在##.#05的舍去5的问题,可能是java的BUG
        return df.format(formatDouble(amount,decimal));
    }

    /**
     * Method formatDouble.
     * @param value  要四舍五入的数值
     * @param precision  精度
     * @return double
     */
    public static double formatDouble(double value, int precision) {
        return Math.round(value * Math.pow(10, (double) precision)) / Math.pow(10,
            (double) precision);
    }

    /**
	 * 将一个值从 String 转成 BigDecimal
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