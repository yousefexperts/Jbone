package cn.jbone.common.utils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


public class IdGenerator {

    /**
     *
     * @param targetEnum
     * @return
     */
    public static long getId(IdTargetEnum targetEnum){
        long time = new Date().getTime();
        int uuidHashCode = Math.abs(UUID.randomUUID().toString().hashCode()) % 100000;
        Random random = new Random();
        int rv = random.nextInt(999);
        DecimalFormat df=new DecimalFormat("000");
        String endVal = df.format(rv);
        String hashCode = df.format(uuidHashCode);
        String id = targetEnum.getTarget() + "" + time + endVal;
        return Long.parseLong(id);
    }

    public static void main(String[] args) {
        for (int i =0; i< 10;i++)System.out.println(getId(IdTargetEnum.MAX));
    }
}
