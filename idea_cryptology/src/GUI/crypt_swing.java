package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import crypt.*;

public class crypt_swing {
    public static void main(String[] args) {

        JFrame frame=new JFrame("Java示例程序");
        Box box=Box.createVerticalBox();


        Box box1=Box.createHorizontalBox();
        box.setBorder(BorderFactory.createTitledBorder("加密"));
//        box1.setPreferredSize(new Dimension(100,10));
//        box1.add(Box.createVerticalStrut(200));
        JLabel label =new JLabel("选择文件：");
        JTextField textField1=new JTextField(38);
        JButton button=new JButton("选择文件");
        button.addActionListener(new ActionListener()
        {
            //添加动作监听器
            public void actionPerformed(final ActionEvent arg0)
            {
                JFileChooser fc=new JFileChooser("");
                int val=fc.showOpenDialog(null);    //文件打开对话框
                if(val==fc.APPROVE_OPTION)
                {
                    //正常选择文件
                    //jtf.setText(fc.getSelectedFile().toString());
                    System.out.println("sss");
                    textField1.setText(fc.getSelectedFile().toString());
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
                }
                else
                {
                    //未正常选择文件，如选择取消按钮
                    //jtf.setText("未选择文件");
                    System.out.println("ddd");
                }
            }
        });
        box1.add(label);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(textField1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(button);

        Box box2=Box.createHorizontalBox();
//        box1.setPreferredSize(new Dimension(100,10));
//        box2.add(Box.createVerticalStrut(200));
        JLabel labelB =new JLabel("填写密钥：");
        JTextField textField1B=new JTextField(38);
        JButton buttonB=new JButton("随机生成");
        box2.add(labelB);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(textField1B);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(buttonB);

        JLabel label_input =new JLabel("原文内容：");
        JTextArea jTextArea=new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font(null,Font.PLAIN,24));
        JScrollPane scrollPane=new JScrollPane(jTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setPreferredSize(new Dimension(00,200));
        Box box_input=Box.createHorizontalBox();
        box_input.add(label_input);
        box_input.add(Box.createHorizontalStrut(20));
        box_input.add(scrollPane);
        box_input.setPreferredSize(new Dimension(00,100));

        JLabel label_input2 =new JLabel("加密内容：");
        JTextArea jTextArea2=new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font(null,Font.PLAIN,24));
        JScrollPane scrollPane2=new JScrollPane(jTextArea2,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setPreferredSize(new Dimension(00,200));
        Box box_input2=Box.createHorizontalBox();
        box_input2.add(label_input2);
        box_input2.add(Box.createHorizontalStrut(20));
        box_input2.add(scrollPane2);
        box_input2.setPreferredSize(new Dimension(00,100));

        box.add(box1);
        box.add(Box.createVerticalStrut(20));
        box.add(box2);
        box.add(Box.createVerticalStrut(20));
        box.add(box_input);
        box.add(Box.createVerticalStrut(20));
        box.add(box_input2);

        Box Box_decode=Box.createHorizontalBox();
        JLabel label_inputd =new JLabel("解密内容：");
        JTextArea jTextAread=new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font(null,Font.PLAIN,24));
        JScrollPane scrollPaned=new JScrollPane(jTextAread,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Box_decode.add(label_inputd);
        Box_decode.add(scrollPaned);

        JTabbedPane tabbedPane=new JTabbedPane();
        ImageIcon icon=new ImageIcon("temp.gif");
        tabbedPane.addTab("AES加密",icon,box,"Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);    //设置快捷键
        tabbedPane.addTab("AES解密",icon,Box_decode,"Does nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);    //设置快捷键
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // 获得被选中选项卡的索引
                int selectedIndex = tabbedPane.getSelectedIndex();
                // 获得指定索引的选项卡标签
                String title = tabbedPane.getTitleAt(selectedIndex);
                System.out.println(title);
                textField1.setText("ddddddddd");
            }
        });
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,600,400);
        frame.setVisible(true);
    }
}
