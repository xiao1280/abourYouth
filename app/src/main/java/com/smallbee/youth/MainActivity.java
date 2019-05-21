package com.smallbee.youth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            arithTest();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void arithTest() throws IllegalAccessException {
//        double value1=1.2345678912311;
//        double value2=9.1234567890123;
//        double value1=1.111222333444;
//        double value2=2.111111111111;
//        BigDecimal value3=new BigDecimal(Double.toString(value1));
//        BigDecimal value4=new BigDecimal(Double.toString(value2));
//        System.out.println("精确加法================="+ ArithTest.add(value1, value2));
//        System.out.println("精确减法================="+ArithTest.sub(value1, value2));
//        System.out.println("精确乘法================="+ArithTest.mul(value1, value2));//==2.345913815048321
//        System.out.println("精确除法 使用默认精度 ================="+ArithTest.div(value1, value2));//使用默认精度 ================0.5263684737
//        System.out.println("精确除法  设置精度================="+ArithTest.div(value1, value2,20));//设置精度     =================0.5263684737366593
//        System.out.println("四舍五入   小数点后保留几位 ================="+ArithTest.round(value1, 10)); //四舍五入   小数点后保留几位 =================1.1112223334
//        System.out.println("比较大小 ================="+ArithTest.equalTo(value3, value4));//==false



        List<Integer> listA=new ArrayList<>();
        listA.add(1);
        listA.add(2);
        listA.add(3);
        listA.add(4);
        listA.add(5);

        System.out.println("xiao ========= xiao");

        List<Integer> listB=listA;
        System.out.println(listA.size()+":1========="+listB.size());

        listA.remove(2);
        System.out.println(listA.size()+":2========="+listB.size());

        listB.remove(2);
        System.out.println(listA.size()+":3========="+listB.size());

        listB.clear();
        System.out.println(listA.size()+":4========="+listB.size());


        System.out.println("xiao ========= xiao");


    }
}
