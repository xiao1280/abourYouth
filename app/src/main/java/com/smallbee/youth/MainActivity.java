package com.smallbee.youth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.smallbee.youth.utils.CalculatorUtils;
import com.smallbee.youth.utils.TimeUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.smallbee.youth.utils.RSAGCUtils.encrypt;


/**
 * 演示数量的计算
 */
public class MainActivity extends AppCompatActivity {
    TextView tv_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            arithTest();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
       tv_state = (TextView) findViewById(R.id.tv_state);

        /**
         * 测试倒计时
         */
        findViewById(R.id.bot_num).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeStr="2019-06-17 10:00";
                String hour_number = timeStr.substring(11, 13);
                Log.e("xiao -->","hour_number:"+hour_number);
                int hourNumber=2;

                Long timeDifference_time = TimeUtil.timeStrToSecond_SeckillDate(timeStr);
                Long timeDifference_endTime=timeDifference_time + (1*1000*60*60*hourNumber) ;  //加上小时
                Long spaceTime = TimeUtil.getSpaceTime(timeDifference_endTime) * 1000;

                String dateTimeFromMillisecond_two = TimeUtil.getDateTimeFromMillisecond_two(timeDifference_endTime);
                Log.e("xiao -->","timeStr 111:"+timeStr);
                Log.e("xiao -->","timeStr 222:"+dateTimeFromMillisecond_two);

                if (spaceTime < 0){
                    //时间过期了
                    tv_state.setText("过期");
                }else{
                    //秒杀时间比较
                    Long time = TimeUtil.getSpaceTime(timeDifference_time) * 1000;
                    if (time < 0){
                        //秒杀正在进行中
                       // tv_state.text="抢购中";
                        tv_state.setText("抢购中");
                    }else{
                        //秒杀还未开始
                        tv_state.setText("即将开始");
                       // tv_state.text="即将开始";
                    }
                }


            }
        });
       // initUI();

        findViewById(R.id.but_jiaMi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String WALLET_PAY_publicKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMW0OJ0I8gwP9R9xCHsXxyb+i9s7wWK2NneWWVCr2JlaNercpGg1gxLGVhgFlwpcuotOyVjBY7T40Y7LPu/B7+k76ajWZACes174s3d0kk8Hqim7J6DM9icvcqQqJp8DbrFy9R+jzncH37AJMAHedA0Hlb06Hews4cYlWuSwkEcwIDAQAB";
                String publikey = encrypt("123456", WALLET_PAY_publicKEY);//公钥加密
                System.out.println("加密后:" + publikey);
                Log.e("xiao -->","加密后:" + publikey);
            }
        });

    }




    /**
     * 测试随机数
     */
    private void randomNumber(){
        Random random = new Random();
        int i2 = random.nextInt(55)+5 ;//获取[0,55)的随机整数
        Log.e("xiao 随机整数->",""+i2);
    }




    private void initUI() {
        String str = "123456";
        char c0 = str.charAt(0);
        char c1 = str.charAt(1);
        char c2 = str.charAt(2);
        char c3 = str.charAt(3);
        char c4 = str.charAt(4);
        char c5 = str.charAt(5);

        Log.e("xiao c0-->", "" + c0);
        Log.e("xiao c1-->", "" + c1);
        Log.e("xiao c2-->", "" + c2);
        Log.e("xiao c3-->", "" + c3);
        Log.e("xiao c4-->", "" + c4);
        Log.e("xiao c5-->", "" + c5);
    }

    private void arithTest() throws IllegalAccessException {
        double value1 = 1.2345678912311;
        double value2 = 9.1234567890123;
        BigDecimal value3 = new BigDecimal(Double.toString(value1));
        BigDecimal value4 = new BigDecimal(Double.toString(value2));
        System.out.println("精确加法=================" + CalculatorUtils.add(value1, value2));
        System.out.println("精确减法=================" + CalculatorUtils.sub(value1, value2));
        System.out.println("精确乘法=================" + CalculatorUtils.mul(value1, value2));//==2.345913815048321
        System.out.println("精确除法 使用默认精度 =================" + CalculatorUtils.div(value1, value2));//使用默认精度 ================0.5263684737
        System.out.println("精确除法  设置精度=================" + CalculatorUtils.div(value1, value2, 20));//设置精度     =================0.5263684737366593
        System.out.println("四舍五入   小数点后保留几位 =================" + CalculatorUtils.round(value1, 10)); //四舍五入   小数点后保留几位 =================1.1112223334
        System.out.println("比较大小 =================" + CalculatorUtils.equalTo(value3, value4));//==false


    }

    /**
     *list 集合的面试题
     */
    public void testList(){
        List<Integer> listA = new ArrayList<>();
        listA.add(1);
        listA.add(2);
        listA.add(3);
        listA.add(4);
        listA.add(5);

        System.out.println("xiao ========= xiao");

        List<Integer> listB = listA;
        System.out.println(listA.size() + ":1=========" + listB.size());

        listA.remove(2);
        System.out.println(listA.size() + ":2=========" + listB.size());

        listB.remove(2);
        System.out.println(listA.size() + ":3=========" + listB.size());

        listB.clear();
        System.out.println(listA.size() + ":4=========" + listB.size());


        System.out.println("xiao ========= xiao");
    }
}
