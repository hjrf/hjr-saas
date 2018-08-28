package util;



import java.util.Random;


/**
 * Created by Admin on 2017/5/11.
 */

public class RandomCreate {


    public static String getRandomString(int length,String buffer) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i ++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }
    public static String getRandomArray(String buffer) {
        String[] items = buffer.split(",");
        return items[getNum(0,items.length-1)];
    }

    public static boolean getTrue(int v)
    {
        if(v>100){v = 100;}
        if(v<0){v=0;}
        if(RandomCreate.getNum(1,99)<v){return true;}
        else{return false;}
    }

    //生成>=start <=end的随机数
    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }


    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n 随机数个数
     */
    public static int[] getNum(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

}

