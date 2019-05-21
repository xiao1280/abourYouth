package com.smallbee.youth.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author xiaoqiao
 * @time 2019/5/20  15:41
 * @describe 计算
 */
public class CalculatorUtils {
    /**
     * BigDecimal的加减乘除
     * <p>
     * double d=1.5;
     * BigDecimal a = new BigDecimal("4.5");
     * BigDecimal b = new BigDecimal(Double.toString(d));
     * <p>
     * System.out.println("a + b =" + a.add(b));       //加
     * System.out.println("a - b =" + a.subtract(b));  //减
     * System.out.println("a * b =" + a.multiply(b));  //乘
     * System.out.println("a / b =" + a.divide(b));    //除   divide(BigDecimal divisor, int scale, int roundingMode)
     */

    // 除法运算默认精度
    private static final int DEF_DIV_SCALE = 10;


    /**
     * 精确加法
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 精确减法
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 精确乘法
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.multiply(b2).doubleValue();
    }
    /**
     * 精确乘法
     */
    public static String mul(String value1, String value2,int num) {
        BigDecimal a = new BigDecimal(value1);
        BigDecimal b = new BigDecimal(value2);
        BigDecimal multiply = a.multiply(b);//乘
        String string = bigDecimalToString(multiply,num);
        return string;
    }

    /**
     * 精确除法 ,不四舍五入
     */
    public static double div(double value1, double value2) throws IllegalAccessException {
        return div(value1, value2, DEF_DIV_SCALE);
    }

    /**
     * 精确除法 -->直省多余小数
     * <p>
     * BigDecimal.ROUND_DOWN
     * 直省多余小数（1.28保留1位小数即1.2）---> 使用这种
     * <p>
     * BigDecimal.ROUND_UP
     * 直接进位（1.21保留1位小数即1.3）
     * <p>
     * BigDecimal.ROUND_HALF_DOWN
     * 四舍五入（2.35保留1位即2.3）
     * <p>
     * BigDecimal.ROUND_HALF_UP
     * 四舍五入（2.35保留1位即2.4）
     *
     * @param scale 精度
     */
    public static double div(double value1, double value2, int scale) throws IllegalAccessException {
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        // return b1.divide(b2, scale).doubleValue();
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     *   * 精确除法 ,不四舍五入
     * @param value1
     * @param value2
     * @param scale
     * @return
     */
    public static String  div(String value1, String value2,int scale)throws IllegalAccessException{
        BigDecimal a = new BigDecimal(value1);
        BigDecimal b = new BigDecimal(value2);
        BigDecimal divide = a.divide(b, scale, BigDecimal.ROUND_DOWN);
        String string = bigDecimalToString(divide);
        return string;
    }

    /**
     * 直省多余小数
     *
     * @param scale 小数点后保留几位
     */
    public static double round(double v, int scale) throws IllegalAccessException {
        return div(v, 1, scale);
    }

    /**
     * 比较大小
     */
    public static boolean equalTo(BigDecimal b1, BigDecimal b2) {
        if (b1 == null || b2 == null) {
            return false;
        }
        return 0 == b1.compareTo(b2);
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }


    /**
     * double转成String,  ,可能有科学计数法显示,( 123456789.123456789   九位数以内能保证精度)
     *
     * @param dou 传入的数字
     * @param num 保留小数的位数,, 去掉多余的0
     * @param b   是否  不四舍五入
     * @return
     */
    public static String doubleToString(double dou, int num, boolean b) {
        NumberFormat nf = NumberFormat.getInstance();
        //小数点后位数,保留3位小数
        nf.setMaximumFractionDigits(num);
        if (b) {
            nf.setRoundingMode(RoundingMode.FLOOR); //不四舍五入
        }
        //设置数的小数部分所允许的最小位数，避免小数位有多余的0
        nf.setMinimumFractionDigits(0);
        //去掉科学计数法显示，避免显示为111,111,111,111
        nf.setGroupingUsed(false);
        String dou_str = nf.format(dou);
        return dou_str;
    }

    /**
     * String 转成 double,可能有科学计数法显示
     *
     * @param str
     * @return
     */
    public static double stringToDouble(String str) {
        BigDecimal maxlng1 = new BigDecimal(str);
        double minlat = maxlng1.doubleValue();
        return minlat;
    }


    /**
     * @param str
     * @param num * 保留几位小数
     * @return
     */
    public static String stringToString(String str, int num) {
        BigDecimal bd = new BigDecimal(str);
        BigDecimal bd2 = new BigDecimal("1");
        BigDecimal bd3 = bd.divide(bd2, num, BigDecimal.ROUND_DOWN);
        String string = bigDecimalToString(bd3);
        return string;
    }

    public static String bigDecimalToString(BigDecimal bd) {
        //BigDecimal BigDecimal(String s); //常用,推荐使用
        //BigDecimal valueOf(Double.toString(double)); //常用,推荐使用
        //BigDecimal bd=new BigDecimal(Double.toString(value1));

        //stripTrailingZeros()是去掉无用的0位，toPlainString()是避免写成科学计数法。
        String strBigDecimal = bd.stripTrailingZeros().toPlainString();
        String s = subZeroAndDot(strBigDecimal);
        return s;
    }

    /**
     * @param bd
     * @param num 设置小数位数
     * @return
     */
    public static String bigDecimalToString(BigDecimal bd, int num) {
        //设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
        bd = bd.setScale(num, BigDecimal.ROUND_DOWN); //ROUND_DOWN  不四舍五入
        //stripTrailingZeros()是去掉无用的0位，toPlainString()是避免写成科学计数法。
        String strBigDecimal = bd.stripTrailingZeros().toPlainString();
        String s = subZeroAndDot(strBigDecimal);
        return s;
    }

    /**
     * String转换成double 保留4位小数。 四舍五入了
     *
     * @param a
     * @return
     */
    public static double stringToDouble_4(String a) {
        double b = Double.valueOf(a);
        // DecimalFormat df = new DecimalFormat("#.0");//此为保留1位小数，若想保留2位小数，则填写#.00  ，以此类推
        DecimalFormat df = new DecimalFormat("#.0000");
        String temp = df.format(b);
        b = Double.valueOf(temp);
        return b;
    }






}
