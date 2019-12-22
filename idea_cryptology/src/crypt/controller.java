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
            aes.KeyExpansion(key);//密钥扩展
            long startTime =  System.currentTimeMillis();
            //读文件成字节流
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
            byte[] inputbyte_for_encrypt=new  byte[16];//最终拼接成的第三方byte
            //startTime=System.currentTimeMillis();
//            byte[] miwenlianjie={(byte)0,(byte)0,(byte)0,(byte)0,
//                    (byte)0,(byte)0,(byte)0,(byte)0,
//                    (byte)0,(byte)0,(byte)0,(byte)0,
//                    (byte)0,(byte)0,(byte)0,(byte)0};
//            int j=0;
            //开始正式加密
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
                byte[] encryptdata=aes.Rijndael(inputbyte_for_encrypt);//加密
                //以下为密文链接
//                if(i>17) {
//                    for(j=0;j<16;j++)
//                    {
//                        encryptdata[j] = (byte)((int)encryptdata[j] ^ (int)miwenlianjie[j]);
//                        System.out.printf("%2x",miwenlianjie[j]);
//                    }
//                    System.out.println("hjjhhjidad");
//                }
//                miwenlianjie=encryptdata;
                outputStream.write(encryptdata);
            }
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
//            byte[] miwenlianjie=new  byte[16];
//            byte[] miwenlianjie_midle=new  byte[16];
//            int miwenlianjie_i=0;
            byte[] inputbyte_for_encrypt=new  byte[16];//最终拼接成的第三方byte
            for(int i=0;i<inputbyte.length;) {
                if (inputbyte.length - i > 16) {
                    System.arraycopy(inputbyte, i, inputbyte_for_encrypt, 0, 16);
                    i = i + 16;
                    byte[] decodedata = aes.decode(inputbyte_for_encrypt);
                    outputStream.write(decodedata);
                } else {
                    System.arraycopy(inputbyte, i, inputbyte_for_encrypt, 0, inputbyte.length - i);
                    Arrays.fill(inputbyte_for_encrypt, inputbyte.length - i, 16, (byte) (00));//16-inputbyte.length-i
                    i = inputbyte.length;
                    byte[] decodedata = aes.decode(inputbyte_for_encrypt);
                    int mark = 0;//标记0
                    int j = 1;
                    for (j = 0; j < decodedata.length; j++) {
                        if (decodedata[j] == 0x00) ;
                        else mark++;
                    }
                    byte[] decodedata1 = new byte[mark];
                    System.arraycopy(decodedata, 0, decodedata1, 0, mark);
                    outputStream.write(decodedata1);
                }
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
