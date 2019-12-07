package GUI;

import javax.swing.*;
import java.awt.*;

public class Swing_Frame extends JFrame{
    Box boxtop;
    Box boxbottom;
    private JLabel label =new JLabel("选择文件：");
    private JTextField textField1=new JTextField(38);
    private JButton button=new JButton("选择");

    private JLabel labelB =new JLabel("填写密钥：");
    private JTextField textField1B=new JTextField(38);
    private JButton buttonB=new JButton("选择");
    public static void main(String[] args) {
        Swing_Frame swing_frame=new Swing_Frame();
        swing_frame.addmenu();
        //swing_frame.top_encrypt();

        JTextPane jTextPane=new JTextPane();//添加底部
        jTextPane.setText("底部的信息");
        swing_frame.add(jTextPane,BorderLayout.SOUTH);

        swing_frame.boxtop=Box.createVerticalBox();
        swing_frame.boxtop.setPreferredSize(new Dimension(100,2));

        JPanel panelA=new JPanel();
        panelA.setPreferredSize(new Dimension(00,200));
        panelA.add(swing_frame.label);
        panelA.add(swing_frame.textField1);
        panelA.add(swing_frame.button);
        panelA.setBackground(Color.ORANGE);
//        swing_frame.boxtop.add(Box.createRigidArea(new Dimension(100,400)));
        JPanel panelB=new JPanel();
        panelB.add(swing_frame.labelB);
        panelB.add(swing_frame.textField1B);
        panelB.add(swing_frame.buttonB);
        panelB.setPreferredSize(new Dimension(100,00));
        panelB.setBackground(Color.white);

        JTextArea jTextArea=new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font(null,Font.PLAIN,18));
        JScrollPane scrollPane=new JScrollPane(jTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(100,00));

        swing_frame.boxtop.add(panelA);
        swing_frame.boxtop.add(scrollPane);
        swing_frame.boxtop.add(panelB);
        swing_frame.add(swing_frame.boxtop);

//        swing_frame.add(panelB);
        swing_frame.setVisible(true);

//        Box box1=Box.createHorizontalBox();
//        box1.setBackground(Color.black);
//        box1.add(Box.createRigidArea(new Dimension(30,30)));
//        box1.add(new JButton(("ifawee")));
//        Box box3=box1;
//        swing_frame.add(box3);

    }
    public Swing_Frame(){
        setTitle("密码学工具箱");
//        setSize(800,250);
        setBounds(100,100,600,500);
//        setLayout(new BorderLayout());
//        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
        setBackground(Color.white);
        getContentPane().setBackground(Color.white);

    }
    public void addmenu(){
        //创建菜单
        final JMenuBar jmenuBar=new JMenuBar();//菜单栏
        add(jmenuBar);
        setJMenuBar(jmenuBar);
        final JMenu aes_encrypt=new JMenu();//aes
        aes_encrypt.setText("AES加密");
        jmenuBar.add(aes_encrypt);
        final JMenu aes_decode=new JMenu();//aes
        aes_decode.setText("AES解密");
        jmenuBar.add(aes_decode);
    }
    public void top_encrypt(){
        boxtop=Box.createHorizontalBox();
        boxtop.setSize(new Dimension(30,30));
        boxtop.setBackground(Color.blue);
        JPanel panelA=new JPanel();
//        panelA.setLayout(new BoxLayout(panelA,BoxLayout.X_AXIS));
        panelA.setBorder(BorderFactory.createTitledBorder("新gaaaaaaaga"));
        TextField textField=new TextField("ddwadaad");
        textField.setSize(new Dimension(3,100));
        panelA.setSize(new Dimension(30,30));
        panelA.add(textField);
        boxtop.add(panelA);
        this.add(boxtop);
    }
}
