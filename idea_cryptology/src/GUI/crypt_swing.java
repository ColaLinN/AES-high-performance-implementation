package GUI;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import crypt.*;

import static crypt.AES_keygenerator.*;

public class crypt_swing {
    public static void main(String[] args) {
        int i1=1;
        JFrame frame=new JFrame("Java示例程序");
        Box box=Box.createVerticalBox();

        Box box1=Box.createHorizontalBox();
        box.setBorder(BorderFactory.createTitledBorder("加密"));
//        box1.setPreferredSize(new Dimension(100,10));
//        box1.add(Box.createVerticalStrut(200));
        JLabel label =new JLabel("选择文件：");
        JTextField textField1=new JTextField();
        JButton button=new JButton("1.选择文件");
        //
        box1.add(Box.createVerticalStrut(20));
        box1.add(label);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(textField1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(button);

        //生成密钥板块
        JLabel label_key_generator =new JLabel("随机生成：");
        JTextField textField_key_generator=new JTextField("lFL");
        JButton button_key=new JButton("2.生成密钥");
        Box box2_generator_key=Box.createHorizontalBox();
        box2_generator_key.add(label_key_generator);
        box2_generator_key.add(Box.createHorizontalStrut(20));
        box2_generator_key.add(textField_key_generator);
        box2_generator_key.add(Box.createHorizontalStrut(20));
        box2_generator_key.add(button_key);

        JLabel label_key =new JLabel("生成密钥：");
        JTextField textField_key=new JTextField();
        JButton button_key_time=new JButton("2.加上时间");
        Box box2__key=Box.createHorizontalBox();
        box2__key.add(label_key);
        box2__key.add(Box.createHorizontalStrut(20));
        box2__key.add(textField_key);
        box2__key.add(Box.createHorizontalStrut(20));
        box2__key.add(button_key_time);

        Box box_keyall= Box.createVerticalBox();
        box_keyall.add(box2_generator_key);
        box_keyall.add(Box.createVerticalStrut(10));
        box_keyall.add(box2__key);

        Box box2=Box.createHorizontalBox();
        box2.add(box_keyall);
        //生成密钥板块

        //加密按钮板块
        Box box_encrypt=Box.createHorizontalBox();
        JLabel labelB_encrypt =new JLabel("加密地址：");
        JTextField textField_encrypt=new JTextField();
        JButton button_encrypt_input=new JButton("3.输入加密");
        JButton button_encrypt=new JButton("3.文件加密");
        Box box_encryptbottom=Box.createVerticalBox();//这是加密的两个框
        box_encryptbottom.add(button_encrypt_input);
        box_encryptbottom.add(Box.createVerticalStrut(10));
        box_encryptbottom.add(button_encrypt);

        box_encrypt.add(labelB_encrypt);
        box_encrypt.add(Box.createHorizontalStrut(20));
        box_encrypt.add(textField_encrypt);
        box_encrypt.add(Box.createHorizontalStrut(20));
        box_encrypt.add(box_encryptbottom);
        //加密按钮板块

        //原文展示板块
        JLabel label_input =new JLabel("原文内容：");
        JTextArea jTextArea=new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font(null,Font.PLAIN,15));
        JScrollPane scrollPane=new JScrollPane(jTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Box box_input=Box.createHorizontalBox();
        box_input.add(Box.createVerticalStrut(80));
        box_input.add(label_input);
        box_input.add(Box.createHorizontalStrut(20));
        box_input.add(scrollPane);
        //原文展示板块

        //密文展示板块
        JLabel label_input2 =new JLabel("密文内容：");
        JTextArea jTextArea2=new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font(null,Font.PLAIN,15));
//        jTextArea.add(Box.createVerticalStrut(100));
        JScrollPane scrollPane2=new JScrollPane(jTextArea2,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setPreferredSize(new Dimension(00,200));
        //密文展示板块

        Box box_input2=Box.createHorizontalBox();
        box_input2.add(label_input2);
        box_input2.add(Box.createHorizontalStrut(20));
        box_input2.add(scrollPane2);
//        box_input2.add(Box.createVerticalStrut(100));
        box_input2.setPreferredSize(new Dimension(00,100));


        box.add(box_input);
        box.add(Box.createVerticalStrut(20));
        box.add(box_input2);
        box.add(Box.createVerticalStrut(20));
        box.add(box1);
        box.add(Box.createVerticalStrut(20));
        box.add(box2);
        box.add(Box.createVerticalStrut(20));
        box.add(box_encrypt);
        //以上为AES加密板块————————————————————————————————————————————————

        //以下为AES加密板块————————————————————————————————————————————————
        //密文内容box
        JLabel label_inputd =new JLabel("密文内容：");
        JTextArea jTextAread=new JTextArea();
        jTextAread.setLineWrap(true);
        jTextAread.setFont(new Font(null,Font.PLAIN,15));
        JScrollPane scrollPaned=new JScrollPane(jTextAread,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaned.setPreferredSize(new Dimension(00,150));
        Box box_Cipher=Box.createHorizontalBox();
        box_Cipher.add(label_inputd);
        box_Cipher.add(Box.createHorizontalStrut(20));
        box_Cipher.add(scrollPaned);

        //解密内容box
        JLabel label_output =new JLabel("解密内容：");
        JTextArea jTextAread_output=new JTextArea();
        jTextAread.setLineWrap(true);
        jTextAread.setFont(new Font(null,Font.PLAIN,15));
        JScrollPane scrollPaned_output=new JScrollPane(jTextAread_output,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaned_output.setPreferredSize(new Dimension(00,150));
        Box box_Cipher_output=Box.createHorizontalBox();
        box_Cipher_output.add(label_output);
        box_Cipher_output.add(Box.createHorizontalStrut(20));
        box_Cipher_output.add(scrollPaned_output);


        //选择解密文件
        JLabel label_decode =new JLabel("解密密钥：");
        JTextField textField_decode=new JTextField("C.txt");
        JButton button_decode=new JButton("2.选择文件");
        Box box2_decode=Box.createHorizontalBox();
        box2_decode.add(label_decode);
        box2_decode.add(Box.createHorizontalStrut(20));
        box2_decode.add(textField_decode);
        box2_decode.add(button_decode);

        //解密密钥
        JLabel label_key_decode =new JLabel("加密密钥：");
        JTextField textField_key_decode=new JTextField("0071da60010179152098173501ae1494");
        Box box2_key_decode=Box.createHorizontalBox();
        box2_key_decode.add(label_key_decode);
        box2_key_decode.add(Box.createHorizontalStrut(20));
        box2_key_decode.add(textField_key_decode);

        //解密地址
        JLabel label_decode_addr =new JLabel("解密文件：");
        JTextField textField_decode_addr=new JTextField("Decode.txt");
        JButton button_dinput=new JButton("2.输入解密");
        JButton button_decode_addr=new JButton("2.文件解密");
        Box box_dbutton=Box.createVerticalBox();//这是加密的两个框
        box_dbutton.add(button_dinput);
        box_dbutton.add(Box.createVerticalStrut(10));
        box_dbutton.add(button_decode_addr);

        Box box2_decode_addr=Box.createHorizontalBox();
        box2_decode_addr.add(label_decode_addr);
        box2_decode_addr.add(Box.createHorizontalStrut(20));
        box2_decode_addr.add(textField_decode_addr);
        box2_decode_addr.add(box_dbutton);

        //加密总box
        Box box_decode=Box.createVerticalBox();
        box_decode.setBorder(BorderFactory.createTitledBorder("解密"));
        box_decode.add(box_Cipher);
        box_decode.add(Box.createVerticalStrut(20));
        box_decode.add(box_Cipher_output);
        box_decode.add(Box.createVerticalStrut(20));
        box_decode.add(box2_decode);
        box_decode.add(Box.createVerticalStrut(20));
        box_decode.add(box2_key_decode);
        box_decode.add(Box.createVerticalStrut(20));
        box_decode.add(box2_decode_addr);

        //分析区
//        JTextField jTextArea_analyse=new JTextField("加密测速");
        JButton jButton_analyse= new JButton("开始加密");
        JProgressBar jProgressBar=new JProgressBar(0,100);
        JButton jButton_analyse_4= new JButton("java自带");
        Box box_analyse=Box.createVerticalBox();
//        box_analyse.add(jTextArea_analyse);
//        box_analyse.add(Box.createHorizontalStrut(100));
        box_analyse.add(jButton_analyse);
        box_analyse.add(jProgressBar);
        box_analyse.add(jButton_analyse_4);

        //底部加密时间等信息
        JTextPane jTextPane = new JTextPane();
        jTextPane.setText("AES加密");
        jTextArea.setFont(new Font(null,Font.PLAIN,20));
        jTextPane.setBackground(Color.decode("#c8ddf2"));
        box.add(Box.createVerticalStrut(10));
        frame.add(jTextPane, "South");

        JTabbedPane tabbedPane=new JTabbedPane();
        ImageIcon icon=new ImageIcon("temp.gif");
        tabbedPane.addTab("AES分析",icon,box_analyse,"选我进行分析");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);    //设置快捷键
        tabbedPane.addTab("AES加密",icon,box,"选我进行加密");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);    //设置快捷键
        tabbedPane.addTab("AES解密",icon,box_decode,"选我进行解密");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_1);    //设置快捷键
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // 获得被选中选项卡的索引
                int selectedIndex = tabbedPane.getSelectedIndex();
                // 获得指定索引的选项卡标签
                String title = tabbedPane.getTitleAt(selectedIndex);
                if(selectedIndex==1)
                    jTextPane.setText("AES加密");
                else if(selectedIndex==2)
                    jTextPane.setText("AES解密");
                else  jTextPane.setText("AES分析");
            }
        });


        //初始化的值
        textField1.setText("M.txt");
        textField_key.setText("0071da60010179152098173501ae1494");
        textField_encrypt.setText("C.txt");


        //
        jButton_analyse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                byte[] newkey={(byte)0,(byte)0,(byte)0,(byte)0,
                        (byte)0,(byte)0,(byte)0,(byte)0,
                        (byte)0,(byte)0,(byte)0,(byte)0,
                        (byte)0,(byte)0,(byte)0,(byte)0};
                AES aes=new AES();
                aes.KeyExpansion(newkey);
                int i=1;
                long startTime = System.currentTimeMillis();
                for(;i<=128;i++)
                {
                    for(int j=1;j<=65536;j++)//每次16字节，总计2的20次方，1MB
                    {
                        aes.Rijndael(newkey);//这个key也正好当作16字节输入
                    }
                    System.out.println("第"+i+"MB");
                    jProgressBar.setValue(i);
                    jProgressBar.setString(i+"%");
                }
                long endTime = System.currentTimeMillis();
                long usedTime = (endTime - startTime);
                jTextPane.setText("耗时："+usedTime + "毫秒");
                System.out.println("加密时间：" + usedTime + "毫秒");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        });

        jButton_analyse_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    byte[] newkey={(byte)0,(byte)0,(byte)0,(byte)0,
                            (byte)0,(byte)0,(byte)0,(byte)0,
                            (byte)0,(byte)0,(byte)0,(byte)0,
                            (byte)0,(byte)0,(byte)0,(byte)0};
                    AES aes=new AES();
                    aes.KeyExpansion(newkey);
                    SecretKeySpec key = new SecretKeySpec(newkey, "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(1, key);
                    int i=1;
                    long startTime = System.currentTimeMillis();
                    for(;i<=128;i++)
                    {
                        for(int j=1;j<=65536;j++)//每次16字节，总计2的20次方，1MB
                        {
                            encrypt1(newkey,cipher);
                        }
                        System.out.println("第"+i+"MB");
                        jProgressBar.setValue(i);
                        jProgressBar.setString(i+"%");
                    }
                    long endTime = System.currentTimeMillis();
                    long usedTime = (endTime - startTime);
                    jTextPane.setText("耗时："+usedTime + "毫秒");
                    System.out.println("加密时间：" + usedTime + "毫秒");
                }catch (Exception ead){
                    ead.printStackTrace();
                }
            }
        });

        //选取文件
        button.addActionListener(new ActionListener()
        {
            //添加动作监听器
            public void actionPerformed(final ActionEvent arg0)
            {
                JFileChooser fc=new JFileChooser("");
                int val=fc.showOpenDialog(null);    //文件打开对话框
                if(val==fc.APPROVE_OPTION)
                {
                    try {
                    //正常选择文件
                    textField1.setText(fc.getSelectedFile().toString());
                        display_txt(textField1,jTextArea);
                        jTextPane.setText("选取成功");
                    }
                    catch(Exception e){
                            e.printStackTrace(System.out);
                    }
                }
                else
                {
                    jTextPane.setText("未选取文件");
                }
            }
        });

        //随机生成
        button_key.addActionListener(new ActionListener() {
              @Override
             public void actionPerformed(ActionEvent e) {
                  try{
                      String seed=textField_key_generator.getText();
                      String random_key= KeyGenerator_A(seed);
                      textField_key.setText(random_key);
                      textField_key_decode.setText(random_key);//设置解密界面的密钥
                      jTextPane.setText("把"+textField_key_generator.getText()+"做为key，使用SHA1PRNG随机生成密钥成功");
                  }catch (Exception ek){
                      ek.printStackTrace(System.out);
                  }
              }
        });
        //加上时间
        button_key_time.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    long time=System.currentTimeMillis();
                    String seed=textField_key_generator.getText()+time;
                    String random_key= KeyGenerator_A(seed);
                    textField_key.setText(random_key);
                    textField_key_decode.setText(random_key);//设置解密界面的密钥
                    jTextPane.setText("把输入"+textField_key_generator.getText()+"与当前时间(毫秒级)"+time+"做为key，使用SHA1PRNG随机生成密钥成功");
                }catch (Exception ek){
                    ek.printStackTrace(System.out);
                }
            }
        });

        //输入加密
        button_encrypt_input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextPane.setText("开始加密");

                try{
                    FileOutputStream file_outputStream=new FileOutputStream(new File(textField1.getText()));
                    BufferedOutputStream outputStream=new BufferedOutputStream(file_outputStream);
                    outputStream.write(jTextArea.getText().getBytes());
                    outputStream.close();

                    byte[] keybyte=AES_keygenerator.parseHexStr2Byte(textField_key.getText());
                    long startTime = System.currentTimeMillis();
                    controller controller = new controller();
                    controller.file_encrypt(textField1.getText(), textField_encrypt.getText(),keybyte);
                    long endTime = System.currentTimeMillis();
                    long usedTime = (endTime - startTime);
                    System.out.println("加密时间：" + usedTime + "毫秒");
                    display_txt(textField1,jTextArea);
                    display_txt(textField_encrypt,jTextArea2);
                    display_txt(textField_encrypt,jTextAread);//放到解密区
                    jTextPane.setText("加密成功，已把输入框内容写入"+textField1.getText()+"中，并且把加密内容写入"+textField_encrypt.getText()+"。用时"+usedTime+"ms。");
                    textField_decode.setText(textField_encrypt.getText());//设置解密区的密钥

                }catch (Exception ebe){
                    ebe.printStackTrace();
                }
            }
        });
        //文件加密
        button_encrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jTextPane.setText("开始加密");
                    byte[] keybyte=AES_keygenerator.parseHexStr2Byte(textField_key.getText());
                    long startTime = System.currentTimeMillis();
                    controller controller = new controller();

                    controller.file_encrypt(textField1.getText(), textField_encrypt.getText(),keybyte);
                    long endTime = System.currentTimeMillis();
                    long usedTime = (endTime - startTime);
                    System.out.println("加密时间：" + usedTime + "毫秒");
                    display_txt(textField1,jTextArea);
                    display_txt(textField_encrypt,jTextArea2);
                    display_txt(textField_encrypt,jTextAread);//放到解密区
                    jTextPane.setText("加密成功，已把"+textField1.getText()+"加密，并且把加密内容写入"+textField_encrypt.getText()+"。用时"+usedTime+"ms");
                    textField_decode.setText(textField_encrypt.getText());

                }catch (Exception eb){
                    eb.printStackTrace(System.out);
                }
            }
        });

        //解密选择文件
        button_decode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd=new JFileChooser("");
                int val=fd.showOpenDialog(null);    //文件打开对话框
                if(val==fd.APPROVE_OPTION)
                {
                    try {
                        //正常选择文件
                        textField_decode.setText(fd.getSelectedFile().toString());
                        display_txt(textField_decode,jTextAread);
                        jTextPane.setText("选择解密文件成功");
                    }
                    catch(Exception ed){
                        ed.printStackTrace(System.out);
                    }
                }
                else
                {
                    jTextPane.setText("未选取文件");
                }
            }
        });
        //解密输入
        button_dinput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    FileOutputStream file_outputStream=new FileOutputStream(new File(textField_decode.getText()));
                    BufferedOutputStream outputStream=new BufferedOutputStream(file_outputStream);
                    outputStream.write(jTextAread.getText().getBytes());
                    outputStream.close();

                    jTextPane.setText("开始解密");
                    byte[] keybyte_decode=AES_keygenerator.parseHexStr2Byte(textField_key_decode.getText());
                    long startTime = System.currentTimeMillis();
                    controller controller = new controller();
                    controller.file_decode(textField_decode.getText(), textField_decode_addr.getText(),keybyte_decode);
                    long endTime = System.currentTimeMillis();
                    long usedTime = (endTime - startTime);
                    System.out.println("加密时间：" + usedTime + "毫秒");
                    display_txt(textField_decode_addr,jTextAread_output);
                    jTextPane.setText("\"加密成功，已把输入框内容写入"+textField_decode.getText()+"中，并且把解密内容写入"+textField_decode_addr.getText()+"。用时"+usedTime+"ms");
                }catch (Exception ed2){
                    ed2.printStackTrace();
                }
            }
        });

        //解密成功
        button_decode_addr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jTextPane.setText("开始解密");
                    byte[] keybyte_decode=AES_keygenerator.parseHexStr2Byte(textField_key_decode.getText());
                    long startTime = System.currentTimeMillis();
                    controller controller = new controller();
                    controller.file_decode(textField_decode.getText(), textField_decode_addr.getText(),keybyte_decode);
                    long endTime = System.currentTimeMillis();
                    long usedTime = (endTime - startTime);
                    System.out.println("加密时间：" + usedTime + "毫秒");
                    display_txt(textField_decode,jTextAread);
                    display_txt(textField_decode_addr,jTextAread_output);
                    jTextPane.setText("解密"+textField_decode.getText()+"成功，已把解密内容写入"+textField_decode_addr.getText()+"。用时"+usedTime+"ms");
                }catch (Exception ed2){
                    ed2.printStackTrace();
                }
            }
        });

        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,711,500);
        frame.setVisible(true);
    }
    public static void display_txt(JTextField textFieldin,JTextArea jTextAreaou){
        try{
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(textFieldin.getText())));
            byte[] inputbyte = new byte[inputStream.available()];
            inputStream.read(inputbyte);//把所有字节流读出
            inputStream.close();
            if(inputbyte.length>1024*1024)
            {
                jTextAreaou.setText(new String("文件过大不予展示"));
            }
            else  jTextAreaou.setText(new String(inputbyte));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
