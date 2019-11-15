package com.example.demojpa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: zp
 * @Date: 2019-10-08 11:31
 * @Description:
 */
public class PingUtils {

    public static boolean ping02(String ipAddress){
        // 读取的行信息
        String line = null;
        // 相当于cmd服务
        Process exec = null;
        // ping 的结果
        boolean res = false;
        try {
            exec = Runtime.getRuntime().exec("ping " + ipAddress);
            BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            // 最多执行三秒
            long endTime = System.currentTimeMillis()+3000;
            // 测试输出行中是否有ttl字符串，有就说明ping通了
            while ((res=true)==true&&(line = br.readLine()).indexOf("ttl")<0){
                res = false;
                // 三秒还是ping不通则放弃尝试
                if(System.currentTimeMillis()>endTime){
                    break;
                }
                if(res == true){
                    System.out.println("true line = " + line);
                }else {
                    System.out.println("line = " + line);
                }
            }
            System.out.println("line = " + line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            exec.destroy();
            return res;
        }
    }

}
