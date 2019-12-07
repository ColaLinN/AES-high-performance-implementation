import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

public class controller {
    public static void main(String[] args) {
        System.out.println("hello world");
        String input="12345678蜗牛h";//一个汉字有三个字节，英文一个字节，测试凑个128字节
        byte[] byteinput=input.getBytes();//转化为字节
        for(byte X:byteinput)
        {
            System.out.printf("%x",X);
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
        System.out.println();
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
        controller.readfile_encrypt("F:\\桌面\\学科\\大三上\\专必-密码学\\大作业\\test.txt");
    }
    public byte[] readfile_encrypt(String path){
        try{
            FileInputStream inputStream =new FileInputStream(new File(path));
            byte[] inputbyte=new byte[inputStream.available()];
            inputStream.read(inputbyte);//把所有字节流读出
            inputStream.close();
            //-----------
            System.out.println("\n字节转字符串:"+new String(inputbyte));//字节转字符串
            int j=1;
            for(byte X:inputbyte)
            {
                System.out.printf("%2x",X);
                if(j%4==0)
                {
                    System.out.print("|");
                }
                if(j==16)
                {
                    System.out.print("\n");
                }
                j++;
            }
            System.out.println("\n"+inputbyte.length);
            byte[] inputbyte_for_encrypt=new  byte[16];//最终拼接成的第三方byte
            for(int i=0;i<inputbyte.length;){
                if(inputbyte.length-i>=16) {
                    System.arraycopy(inputbyte,i,inputbyte_for_encrypt,0,16);
                    i=i+16;
                }
                else{

                    System.arraycopy(inputbyte,i,inputbyte_for_encrypt,0,inputbyte.length-i);
                    Arrays.fill(inputbyte_for_encrypt,inputbyte.length-i,16,(byte)(inputbyte.length-i));
                    i=inputbyte.length;
                }
                j=1;
                for(byte X:inputbyte_for_encrypt)
                {
                    System.out.printf("%2x",X);
                    if(j==16)
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
                byte[][] key=
                        {{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}, {(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}
                                ,{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00},{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}};
                AES aes=new AES();
                System.out.print("\n-------------------before:"+new String(inputbyte_for_encrypt));//字节转字符串
                byte[] helloworld=aes.decode(aes.Rijndael(inputbyte_for_encrypt,key),key);
                System.out.println("-------------------after:"+new String(helloworld));//字节转字符串
            }
            return null;
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
    public byte[] writefile_encrypt(){
        return null;
    }
}
