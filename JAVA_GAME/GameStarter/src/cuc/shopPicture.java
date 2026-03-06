package cuc;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.io.IOException;
import java.awt.*;  
import java.awt.image.BufferedImage;  
  
public class shopPicture extends JFrame{
//	private JLabel productImageLabel; 
	
	public shopPicture(){
//	    setTitle("购物应用");  
        setSize(400, 400);  
	    setLocationRelativeTo(null); // 窗口居中显示  
//	    
//	    productImageLabel = new JLabel(new ImageIcon("house2.jpg"));
//	    productImageLabel.setVisible(true); 
//	    
	    JButton close = new JButton("关闭");
        close.addActionListener(e ->{
        	setVisible(false);
        });
		setTitle("图片查看器");  
        // 设置窗口关闭时的操作  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        // 设置窗口的布局管理器  
        setLayout(new FlowLayout());  
      
  
        // 加载图片  
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(close); 
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);  
        try {  
            BufferedImage image = ImageIO.read(shopPicture.class.getResourceAsStream("/images/房子.jpg"));  
            ImageIcon icon = new ImageIcon(image);  
  
            // 使用JLabel来显示图片  
            JLabel label = new JLabel(icon);  
            // 添加JLabel到窗口  
            add(label);  
            label.setText("姓名：吴桐；    学号：202312033033"); 
            // 根据图片大小调整窗口大小  
            pack();  
  
            // 设置窗口可见  
            setVisible(true);  
  
        } catch (IOException e) {  
            e.printStackTrace();  
            // 图片加载失败时，可以在这里处理，比如显示一个错误消息  
            JOptionPane.showMessageDialog(null, "无法加载图片", "错误", JOptionPane.ERROR_MESSAGE);  
        }
 
        }  
	}
