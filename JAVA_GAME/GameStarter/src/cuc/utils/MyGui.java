package cuc.utils;
import javax.swing.*;

import javafx.stage.WindowEvent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.time.Instant; 


public class MyGui extends JFrame {  
		  
	    // 文本标签，用于显示文字  
	    private JLabel label; 
	    
	    // 静态字段，用于跟踪窗口最后关闭的时间（毫秒）  
	    private static long lastClosedTime = 0;  
	  
	    // 静态常量，定义窗口关闭后不再显示的时间间隔（毫秒）  
	    private static final long DISPLAY_INTERVAL = 30000; // 30秒  
	  
	    public MyGui() {  
	        // 设置窗口的基本属性  
	        setTitle("MyGui Window"); // 窗口标题  
	        setSize(1000, 200); // 窗口大小  
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作（关闭窗口时退出程序）  
	        setLocationRelativeTo(null); // 窗口居中显示  
	  
	        // 初始化组件  
	        label = new JLabel("你好，这里是货郎，你有什么想买的吗？", SwingConstants.CENTER); // 初始文字  
	  
	        // 创建按钮  
	        JButton updateButton = new JButton("Enter"); // 更新文字的按钮  
	        JButton closeButton = new JButton("Close"); // 关闭窗口的按钮  
	        //JButton buyButton = new JButton("buy"); 
	        // 为按钮添加事件监听器  
	        updateButton.addActionListener(new ActionListener() {  
	            @Override  
	            public void actionPerformed(ActionEvent e) {  
	    	        SwingUtilities.invokeLater(() -> {  
	    	            ShoppingApp app = new ShoppingApp();  
	    	            app.setVisible(true);  
	    	        }); 
	                //label.setText("哈哈，支付成功！！");  
	            }  
	        });  
	  
	        closeButton.addActionListener(new ActionListener() {  
	            @Override  
	            public  void actionPerformed(ActionEvent e) {  
	                // 关闭窗口  
	                 setVisible(false);
	            }  
	        });  
	  
	        // 设置布局管理器 
	        // 并添加组件到窗口  
	        getContentPane().add(label, BorderLayout.CENTER); // 标签居中  
	  
	        // 创建一个面板来放置按钮，并使用FlowLayout布局  
	        JPanel buttonPanel = new JPanel();  
	        buttonPanel.add(updateButton);  
	        buttonPanel.add(closeButton);  
	  
	        // 将按钮面板添加到窗口的底部  
	        getContentPane().add(buttonPanel, BorderLayout.SOUTH);  
	  
	        // 设置窗口为可见  
	        setVisible(true);  
	    }  

	  
	    // 静态方法，用于检查是否可以显示窗口  
	    private static boolean canDisplay() {  
	        long currentTime = Instant.now().toEpochMilli();  
	        return currentTime - lastClosedTime >= DISPLAY_INTERVAL;  
	    }  
	  
	    // 静态方法，用于显示窗口（如果时间间隔已过）  
	    public static void showIfAllowed() {  
	        if (canDisplay()) {  
	            // 创建并显示窗口  
	            MyGui gui = new MyGui();  
	  
	            // 这里可以添加一个窗口关闭监听器来更新lastClosedTime  
	            gui.addWindowListener(new WindowAdapter() {  
	                public void windowClosed(WindowEvent e) {  
	                    lastClosedTime = Instant.now().toEpochMilli();  
	                }  
	            });  
	        } else {  
	            // 如果时间间隔未过，可以选择显示一个消息或什么也不做  
	            JOptionPane.showMessageDialog(null, "MyGui cannot be displayed within the specified interval.");  
	        }  
	    }  
	  
}
