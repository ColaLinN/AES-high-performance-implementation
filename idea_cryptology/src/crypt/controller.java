package crypt;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class controller {
    public static void main(String[] args) {
        byte[][] key=
                {{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}, {(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}
                 ,{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00},{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}};
        //生成轮密钥
        byte[] newkey={(byte)0,(byte)0,(byte)0,(byte)0,
                (byte)0,(byte)0,(byte)0,(byte)0,
                (byte)0,(byte)0,(byte)0,(byte)0,
                (byte)0,(byte)0,(byte)0,(byte)0};
        AES aes=new AES();
            long startTime =  System.currentTimeMillis();
        controller controller=new controller();
        //记得把key优化以下，输入为一串字符串
        controller.file_encrypt("F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\test.txt","F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\write.txt",newkey);
            long endTime =  System.currentTimeMillis();long usedTime = (endTime-startTime);System.out.println("加密时间："+usedTime+"毫秒");
            startTime=System.currentTimeMillis();
        controller.file_decode("F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\write.txt","F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\AESddd.swf",newkey);
            endTime =  System.currentTimeMillis();usedTime = (endTime-startTime);System.out.println("解密时间："+usedTime+"毫秒");
    }
    public byte[] file_encrypt(String inputpath,String outputpath,byte[] key){
        try{
            //计时
            AES aes=new AES();
            aes.KeyExpansion(key);
            long startTime =  System.currentTimeMillis();
            FileInputStream file_inputStream =new FileInputStream(new File(inputpath));
            BufferedInputStream inputStream=new BufferedInputStream(file_inputStream);
            byte[] inputbyte=new byte[inputStream.available()];
            inputStream.read(inputbyte);//把所有字节流读出
            inputStream.close();
            //计时file_
            long endTime =  System.currentTimeMillis();
            long usedTime = (endTime-startTime);
            System.out.println("读明文文件时间："+usedTime+"毫秒");
            FileOutputStream file_outputStream=new FileOutputStream(new File(outputpath));
            BufferedOutputStream outputStream=new BufferedOutputStream(file_outputStream);
//            System.out.println("\ninputbyte的内容:"+new String(inputbyte));//字节转字符串

//            int j=1;
//            for(byte X:inputbyte)//inputbyte的字节流
//            {
//                System.out.printf("%2x",X);
//                if(j%4==0)
//                {
//                    System.out.print("|");
//                }
//                if(j%16==0)
//                {
//                    System.out.print("\n");
//                }
//                j++;
//            }
//            System.out.print("\n以上读的数据");
//            System.out.println("\n读数据的字节长度："+inputbyte.length);
            byte[] inputbyte_for_encrypt=new  byte[16];//最终拼接成的第三方byte
            startTime=System.currentTimeMillis();
            for(int i=0;i<inputbyte.length;){
                if(inputbyte.length-i>=16) {
                    System.arraycopy(inputbyte,i,inputbyte_for_encrypt,0,16);
                    i=i+16;
                }
                else{//填充
                    System.arraycopy(inputbyte,i,inputbyte_for_encrypt,0,inputbyte.length-i);
                    Arrays.fill(inputbyte_for_encrypt,inputbyte.length-i,16,(byte)(0));//16-inputbyte.length-i
                    i=inputbyte.length;
                }
//                j=1;//j是上面定义的，要用就重写变量名
//                System.out.print("\n-------------------before:"+new String(inputbyte_for_encrypt)+"");//字节转字符串
//                System.out.println("\n读的state如下");
//                for(byte X:inputbyte_for_encrypt)//输出读的128（也可能是填充成的）位的字节流
//                {
//                    System.out.printf("%02x",X);
//                    if(j%16==0)
//                    {
////                        System.out.print("\n");
//                        break;
//                    }
//                    if(j%4==0)
//                    {
//                        System.out.print("|");
//                    }
//                    j++;
//                }
                byte[] encryptdata=aes.Rijndael(inputbyte_for_encrypt);//加密
                outputStream.write(encryptdata);
//                encryptdata=aes.decode(encryptdata,key);
//                System.out.println("--------------------after:"+new String(encryptdata));//字节转字符串
            }
            endTime =  System.currentTimeMillis();
            usedTime = (endTime-startTime);
            System.out.println("加密锁时间："+usedTime+"毫秒");
            outputStream.close();
            return null;
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
    public byte[] file_decode(String inputpath,String outputpath,byte[] key){
        try{
            AES aes=new AES();
            aes.inv_KeyExpansion(key);
            long startTime =  System.currentTimeMillis();//计时开始
            FileInputStream file_inputStream =new FileInputStream(new File(inputpath));
            BufferedInputStream inputStream=new BufferedInputStream(file_inputStream);
            byte[] inputbyte=new byte[inputStream.available()];
            inputStream.read(inputbyte);//把所有字节流读出
            inputStream.close();
            //计时
            long endTime =  System.currentTimeMillis();
            long usedTime = (endTime-startTime);
            System.out.println("读密文文件时间："+usedTime+"毫秒");
            //Buffer牛逼，可以大幅提高读写文件速度
            FileOutputStream file_outputStream=new FileOutputStream(new File(outputpath));
            BufferedOutputStream outputStream=new BufferedOutputStream(file_outputStream);

//            System.out.println("\ninputbyte的内容:"+new String(inputbyte));//字节转字符串
//            int j=1;
//            for(byte X:inputbyte)//输出读的内容的字节流
//            {
//                System.out.printf("%2x",X);
//                if(j%4==0)
//                {
//                    System.out.print("|");
//                }
//                if(j%16==0)
//                {
//                    System.out.print("\n");
//                }
//                j++;
//            }
//            System.out.print("\n以上读的数据");
//            System.out.println("\n读数据的字节长度："+inputbyte.length);
            byte[] inputbyte_for_encrypt=new  byte[16];//最终拼接成的第三方byte
            for(int i=0;i<inputbyte.length;){
                if(inputbyte.length-i>16) {
                    System.arraycopy(inputbyte,i,inputbyte_for_encrypt,0,16);
                    i=i+16;
                    byte[] decodedata =aes.decode(inputbyte_for_encrypt);
                    outputStream.write(decodedata);
                }
                else{
                    System.arraycopy(inputbyte,i,inputbyte_for_encrypt,0,inputbyte.length-i);
                    Arrays.fill(inputbyte_for_encrypt,inputbyte.length-i,16,(byte)(00));//16-inputbyte.length-i
                    i=inputbyte.length;
                    System.out.println("--------------------after:"+new String(inputbyte_for_encrypt));//字节转字符串
                    byte[] decodedata =aes.decode(inputbyte_for_encrypt);
                    System.out.println("--------------------after:"+new String(decodedata));//字节转字符串
                    int mark=0;//标记0
                    int j=1;
                     for(byte X:decodedata)
                    {
                        System.out.printf("%02x",X);
                        if(j%4==0)
                        {
                            System.out.print("|");
                        }
                        if(j%16==0)
                        {
                            System.out.print("\n");

                        }
                        j++;
                    }
                    for(j=0;j<decodedata.length;j++)
                    {
                        if(decodedata[j]==0x00);
                        else mark++;
                    }
                    System.out.println("\n--------------------after:"+new String(decodedata));//字节转字符串
                    byte[] decodedata1=new byte[mark];
                    System.arraycopy(decodedata,0,decodedata1,0,mark);
                    j=1;
                    for(byte X:decodedata1)
                    {
                        System.out.printf("%02x",X);
                        if(j%16==0)
                        {
                            System.out.print("\n");

                        }
                        if(j%4==0)
                        {
                            System.out.print("|");
                        }
                        j++;
                    }
                    System.out.println("\n--------------------after:"+new String(decodedata1));//字节转字符串
                    outputStream.write(decodedata1);
                }
//                j=1;//j是上面用的
//                System.out.print("\n-------------------before:"+new String(inputbyte_for_encrypt)+"");//字节转字符串
//                System.out.println("\n读的state如下");
//                for(byte X:inputbyte_for_encrypt)
//                {
//                    System.out.printf("%02x",X);
//                    if(j%16==0)
//                    {
////                        System.out.print("\n");
//                        break;
//                    }
//                    if(j%4==0)
//                    {
//                        System.out.print("|");
//                    }
//                    j++;
//                }
//                System.out.println("--------------------after:"+new String(decodedata));//字节转字符串
            }
            outputStream.close();
            inputStream.close();
            return null;
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
    public void test(){
//        //        System.out.println("hello world");
//        String input="12345678蜗牛h";//一个汉字有三个字节，英文一个字节，测试凑个128字节
//        byte[] byteinput=input.getBytes();//转化为字节
//        for(byte X:byteinput)
//        {
////            System.out.printf("%x",X);
//        }
////        System.out.printf("\n寻址%x",byteinput[0]);//寻址
////        System.out.println("\n字节转字符串"+new String(byteinput));//字节转字符串
////        System.out.println("默认字节集为"+ Charset.defaultCharset().name());//默认字节集为UTF-8
////        System.out.println(byteinput.length);//二进制流长度
//        byte[] hello=new byte[16-byteinput.length];//创建一个全零的
//        byte[] inputbyte=new  byte[16];//最终拼接成的第三方byte
//        if(byteinput.length<16)//填充
//        {
////            System.arraycopy(byteinput,0,inputbyte,0,byteinput.length);
////            System.arraycopy(hello,0,inputbyte,byteinput.length,hello.length);
//        }
////        System.out.println();
//        for(byte X:inputbyte)//逐个字节输出
//        {
////            System.out.printf("%x",X);
//        }
//        System.out.println("\n"+inputbyte.length);
//        byte[][] key=
//                {{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}, {(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}
//                        ,{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00},{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}};
//        //生成轮密钥
//        AES aes=new AES();
//        //aes.KeyExpansion(key);
//        //aes.inv_KeyExpansion(key);
////        System.out.println("\n字节转字符串:"+new String(inputbyte));//字节转字符串
////        byte[] helloworld=aes.decode(aes.Rijndael(inputbyte,key),key);
////        System.out.println("\n字节转字符串:"+new String(helloworld));//字节转字符串
//
//        /** 获取当前系统时间*/
//        long startTime =  System.currentTimeMillis();
//        controller controller=new controller();
//        //记得把key优化以下，输入为一串字符串
//        controller.file_encrypt("F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\test.txt","F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\write.txt",key);
//        long endTime =  System.currentTimeMillis();
//        long usedTime = (endTime-startTime);
//        System.out.println("加密时间："+usedTime+"毫秒");
//        startTime=System.currentTimeMillis();
//        controller.file_decode("F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\write.txt","F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\decode.txt",key);
//        endTime =  System.currentTimeMillis();
//        usedTime = (endTime-startTime);
//        System.out.println("解密时间："+usedTime+"毫秒");
    }
}
