import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

public class controller {
    public static void main(String[] args) {
//        System.out.println("hello world");
        String input="12345678蜗牛h";//一个汉字有三个字节，英文一个字节，测试凑个128字节
        byte[] byteinput=input.getBytes();//转化为字节
        for(byte X:byteinput)
        {
//            System.out.printf("%x",X);
        }
//        System.out.printf("\n寻址%x",byteinput[0]);//寻址
//        System.out.println("\n字节转字符串"+new String(byteinput));//字节转字符串
//        System.out.println("默认字节集为"+ Charset.defaultCharset().name());//默认字节集为UTF-8
//        System.out.println(byteinput.length);//二进制流长度
        byte[] hello=new byte[16-byteinput.length];//创建一个全零的
        byte[] inputbyte=new  byte[16];//最终拼接成的第三方byte
        if(byteinput.length<16)//填充
        {
//            System.arraycopy(byteinput,0,inputbyte,0,byteinput.length);
//            System.arraycopy(hello,0,inputbyte,byteinput.length,hello.length);
        }
//        System.out.println();
        for(byte X:inputbyte)//逐个字节输出
        {
//            System.out.printf("%x",X);
        }
        System.out.println("\n"+inputbyte.length);
        byte[][] key=
                {{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}, {(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}
                 ,{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00},{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}};
        //生成轮密钥
        AES aes=new AES();
        //aes.KeyExpansion(key);
        //aes.inv_KeyExpansion(key);
//        System.out.println("\n字节转字符串:"+new String(inputbyte));//字节转字符串
//        byte[] helloworld=aes.decode(aes.Rijndael(inputbyte,key),key);
//        System.out.println("\n字节转字符串:"+new String(helloworld));//字节转字符串

        controller controller=new controller();
        //记得把key优化以下，输入为一串字符串
        controller.file_encrypt("F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\test.txt","F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\write.txt",key);
        controller.file_decode("F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\write.txt","F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\decode.txt",key);
    }
    public byte[] file_encrypt(String inputpath,String outputpath,byte[][] key){
        try{
            FileInputStream inputStream =new FileInputStream(new File(inputpath));
            byte[] inputbyte=new byte[inputStream.available()];
            inputStream.read(inputbyte);//把所有字节流读出
            inputStream.close();
            FileOutputStream outputStream=new FileOutputStream(new File(outputpath));

            System.out.println("\ninputbyte的内容:"+new String(inputbyte));//字节转字符串
            int j=1;
            for(byte X:inputbyte)
            {
                System.out.printf("%2x",X);
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
            System.out.print("\n以上读的数据");
            System.out.println("\n读数据的字节长度："+inputbyte.length);
            byte[] inputbyte_for_encrypt=new  byte[16];//最终拼接成的第三方byte
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
                j=1;
                System.out.print("\n-------------------before:"+new String(inputbyte_for_encrypt)+"");//字节转字符串
                System.out.println("\n读的state如下");
                for(byte X:inputbyte_for_encrypt)
                {
                    System.out.printf("%02x",X);
                    if(j%16==0)
                    {
//                        System.out.print("\n");
                        break;
                    }
                    if(j%4==0)
                    {
                        System.out.print("|");
                    }
                    j++;
                }
                AES aes=new AES();
                byte[] encryptdata=aes.Rijndael(inputbyte_for_encrypt,key);
                outputStream.write(encryptdata);
                encryptdata=aes.decode(encryptdata,key);
                System.out.println("--------------------after:"+new String(encryptdata));//字节转字符串
            }
            outputStream.close();
            inputStream.close();
            return null;
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
    public byte[] file_decode(String inputpath,String outputpath,byte[][] key){
        try{
            FileInputStream inputStream =new FileInputStream(new File(inputpath));
            byte[] inputbyte=new byte[inputStream.available()];
            inputStream.read(inputbyte);//把所有字节流读出
            inputStream.close();
            FileOutputStream outputStream=new FileOutputStream(new File(outputpath));

            System.out.println("\ninputbyte的内容:"+new String(inputbyte));//字节转字符串
            int j=1;
            for(byte X:inputbyte)
            {
                System.out.printf("%2x",X);
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
            System.out.print("\n以上读的数据");
            System.out.println("\n读数据的字节长度："+inputbyte.length);
            byte[] inputbyte_for_encrypt=new  byte[16];//最终拼接成的第三方byte
            for(int i=0;i<inputbyte.length;){
                if(inputbyte.length-i>=16) {
                    System.arraycopy(inputbyte,i,inputbyte_for_encrypt,0,16);
                    i=i+16;
                }
                else{

                    System.arraycopy(inputbyte,i,inputbyte_for_encrypt,0,inputbyte.length-i);
                    Arrays.fill(inputbyte_for_encrypt,inputbyte.length-i,16,(byte)(0));//16-inputbyte.length-i
                    i=inputbyte.length;
                }
                j=1;
                System.out.print("\n-------------------before:"+new String(inputbyte_for_encrypt)+"");//字节转字符串
                System.out.println("\n读的state如下");
                for(byte X:inputbyte_for_encrypt)
                {
                    System.out.printf("%02x",X);
                    if(j%16==0)
                    {
//                        System.out.print("\n");
                        break;
                    }
                    if(j%4==0)
                    {
                        System.out.print("|");
                    }
                    j++;
                }
                AES aes=new AES();
                byte[] decodedata =aes.decode(inputbyte_for_encrypt,key);
                outputStream.write(decodedata);
                System.out.println("--------------------after:"+new String(decodedata));//字节转字符串
            }
            outputStream.close();
            inputStream.close();
            return null;
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
}
