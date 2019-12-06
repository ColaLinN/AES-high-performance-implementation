import java.nio.charset.Charset;

public class controller {
    public static void main(String[] args) {
        System.out.println("hello world");
        String input="12345678";//一个汉字有三个字节，英文一个字节，测试凑个128字节
        String o="dd林";
        input=o+input;
        byte[] byteinput=input.getBytes();//转化为字节
        for(byte X:byteinput)
        {
            System.out.printf("%x",X);
        }
        System.out.printf("\n寻址%x",byteinput[0]);//寻址
        System.out.println("\n字节转字符串"+new String(byteinput));//字节转字符串
        System.out.println("默认字节集为"+ Charset.defaultCharset().name());//默认字节集为UTF-8
        System.out.println(byteinput.length);//二进制流长度
        byte[] hello=new byte[16-byteinput.length];
        byte[] inputbyte=new  byte[16];
        if(byteinput.length<16)//填充
        {
            System.arraycopy(byteinput,0,inputbyte,0,byteinput.length);
            System.arraycopy(hello,0,inputbyte,byteinput.length,hello.length);
        }
        System.out.println();
        for(byte X:inputbyte)
        {
            System.out.printf("%x",X);
        }
        System.out.println("\n"+inputbyte.length);
        byte[][] key=
                {{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}, {(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}
                 ,{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00},{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00}};
        //生成轮密钥
        AES aes=new AES();
        //aes.KeyExpansion(key);
        //aes.inv_KeyExpansion(key);

        aes.decode(aes.Rijndael(inputbyte,key),key);
    }
}
