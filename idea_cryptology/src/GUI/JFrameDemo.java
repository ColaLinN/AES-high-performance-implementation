package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;

public class JFrameDemo extends JFrame{
    JList list;
    JLabel label;
    public JFrameDemo()
    {
//        setTitle("GUI_swing");
//        setTitle("Java 第一个 GUI 程序");    //设置显示窗口标题
//        setSize(4000,200);    //设置窗口显示尺寸
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关闭
////        getContentPane().add(jl);    //将标签组件添加到内容窗格上
//        setVisible(true);    //设置窗口是否可见
        setTitle("监听列表项选择事件");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,400,200);
        JPanel contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);
        label=new JLabel(" ");
        contentPane.add(label,BorderLayout.NORTH);
        contentPane.add(label,BorderLayout.SOUTH);

        JScrollPane scrollPane=new JScrollPane();
        contentPane.add(scrollPane,BorderLayout.CENTER);
        list=new JList();
        scrollPane.setViewportView(list);
        String[] listData=new String[7];
        listData[0]="《一点就通学Java》";
        listData[1]="《一点就通学PHP》";
        listData[2]="《一点就通学Visual Basic）》";
        listData[3]="《一点就通学Visual C++）》";
        listData[4]="《Java编程词典》";
        listData[5]="《PHP编程词典》";
        listData[6]="《C++编程词典》";
        list.setListData(listData);
        list.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                do_list_valueChanged(e);

            }
        });
    }
    protected void do_list_valueChanged(ListSelectionEvent e)
    {
        label.setText("感谢您购买："+list.getSelectedValue());
    }
    public static void main(String[] agrs)
    {
//        JFrameDemo jFrameDemo =new JFrameDemo();    //创建一个实例化对象
//        jFrameDemo.setBounds(0,0,1000,500);//出现位置，以及宽高
////        JPanel jPanel=new JPanel();//新建面板
////        JLabel jLabel=new JLabel("hello world");//新建标签
////        jPanel.setBackground(Color.white);//设置背景色
////        jPanel.add(jLabel);// 把标签加入面板
//        jFrameDemo.setLayout(new BorderLayout());    //为Frame窗口设置布局为BorderLayout
//        JButton button1=new JButton ("上");
//        JButton button2=new JButton("左");
//        JButton button3=new JButton("中");
//        JButton button4=new JButton("右");
//        JButton button5=new JButton("下");
//        jFrameDemo.add(button1,BorderLayout.NORTH);
//        jFrameDemo.add(button2,BorderLayout.WEST);
//        jFrameDemo.add(button3,BorderLayout.CENTER);
//        jFrameDemo.add(button4,BorderLayout.EAST);
//        jFrameDemo.add(button5,BorderLayout.SOUTH);
//        JLabel jl=new JLabel("这是使用JFrame类创建的窗口");    //创建一个标签
//        jFrameDemo.getContentPane().add(jl);
//        jFrameDemo.setBounds(300,200,600,300);
//        jFrameDemo.add(jPanel);//把面板加入窗口

//        JFrame frame=new JFrame("Java第五个程序");    //创建Frame窗口
//        JPanel p1=new JPanel();    //面板1
//        JPanel p2=new JPanel();    //面板2
//        JPanel cards=new JPanel(new CardLayout());    //卡片式布局的面板
//        p1.add(new JButton("登录按钮"));
//        p1.add(new JButton("注册按钮"));
//        p1.add(new JButton("找回密码按钮"));
//        p2.add(new JTextField("用户名文本框",20));
//        p2.add(new JTextField("密码文本框",20));
//        p2.add(new JTextField("验证码文本框",20));
//        cards.add(p1,"card1");    //向卡片式布局面板中添加面板1
//        cards.add(p2,"card2");    //向卡片式布局面板中添加面板2
//        CardLayout cl=(CardLayout)(cards.getLayout());
//        cl.show(cards,"card2");    //调用show()方法显示面板2
//        frame.add(cards);
//        frame.setBounds(300,200,400,200);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JFrameDemo frame=new JFrameDemo();
//        frame.setVisible(true);
//        JTabbedPane tabbedPane=new JTabbedPane();
//        ImageIcon icon=createImageIcon("tab.jp1g");
//        JComponent panel1=makeTextPanel("计算机名");
//        tabbedPane.addTab("计算机名",icon, panel1,"Does nothing");
//        tabbedPane.setMnemonicAt(0,KeyEvent.VK_1);
//        JComponent panel2=makeTextPanel("硬件");
//        tabbedPane.addTab("硬件",icon,panel2,"Does twice as much nothing");
//        tabbedPane.setMnemonicAt(1,KeyEvent.VK_2);
//        JComponent panel3=makeTextPanel("高级");
//        tabbedPane.addTab("高级",icon,panel3,"Still does nothing");
//        tabbedPane.setMnemonicAt(2,KeyEvent.VK_3);
//        JComponent panel4=makeTextPanel("系统保护");
//        panel4.setPreferredSize(new Dimension(410,50));
//        tabbedPane.addTab("系统保护",icon,panel4,"Does nothing at all");
//        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
//        frame.add(tabbedPane);
    }
    protected JComponent makeTextPanel(String text)
    {
        JPanel panel=new JPanel(false);
        JLabel filler=new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1,1));
        panel.add(filler);
        return panel;
    }
}
