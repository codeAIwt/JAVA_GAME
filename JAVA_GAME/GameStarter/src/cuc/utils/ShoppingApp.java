package cuc.utils;

import javax.swing.*;

import cuc.shopPicture;

import java.awt.*;  
import java.awt.event.*;  
  
public class ShoppingApp extends JFrame {  
//    private JLabel productImageLabel;  
    private JLabel statusLabel;  
    private JButton viewProductsButton;  
    private JButton close;
    private JButton buyButton; // 假设有多个商品，这里仅展示一个购买按钮  
    public static boolean isPurchased = false;  
  
    public ShoppingApp() {  
        setTitle("购物应用");  
        setSize(800, 200);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(new BorderLayout());  
  
        // 商品图片展示  
//        JLabel productImageLabel = new JLabel(new ImageIcon("house2.jpg"));  
//        productImageLabel.setVisible(false);  
//        
        // 状态显示  
        statusLabel = new JLabel("未购买");  
  
        // 按钮  
        viewProductsButton = new JButton("查看商品");  
        viewProductsButton.addActionListener(e -> {  
//            productImageLabel.setVisible(true); 
        	new shopPicture();
            // 假设这里只展示一个商品，实际可能需要根据用户选择来显示  
            // 可以添加更多商品展示逻辑  
        });  
        close = new JButton("关闭");
        close.addActionListener(e ->{
        	setVisible(false);
        });
        buyButton = new JButton("购买");  
        buyButton.addActionListener(e -> {  
            isPurchased = true;  
            statusLabel.setText("已购买，下次将不会触发与货郎的对话，请点击close后请点击1进入房间，点击2可以返回桃花岛");  
            enableSceneSwitch();  
        });  
  
        // 布局  
        JPanel buttonPanel = new JPanel();  
        buttonPanel.add(viewProductsButton);  
        buttonPanel.add(buyButton);  
        buttonPanel.add(close);
  
        add(buttonPanel, BorderLayout.NORTH);
  //      add(productImageLabel, BorderLayout.CENTER);  
        add(statusLabel, BorderLayout.SOUTH);  
  
//        // 键盘监听  
//        addKeyListener(new KeyAdapter() {  
//            @Override  
//            public void keyPressed(KeyEvent e) {  
//                if (e.getKeyCode() == KeyEvent.VK_1 && isPurchased) {  
//                    // 切换场景的逻辑  
//                    System.out.println("场景已切换");  
//                    // 这里可以关闭窗口、更换面板内容等  
//                }  
//            }  
//        });  
//  
        // 确保窗口获得焦点以接收键盘事件  
        setFocusable(true);  
        requestFocusInWindow();  
    }  
  
    private void enableSceneSwitch() {  
        // 这里可以添加更多逻辑，比如显示切换场景的按钮等  
    }  
  
    /*public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
            ShoppingApp app = new ShoppingApp();  
            app.setVisible(true);  
        });  
    }  */
}