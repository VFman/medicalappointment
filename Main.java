package com.medicalappointment;

import com.medicalappointment.ui.*;
import com.medicalappointment.model.User;
import com.medicalappointment.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        // 设置系统风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> showLoginWindow());
    }

    private static void showLoginWindow() {
        JFrame frame = new JFrame("医疗预约管理系统 - 登录");
        frame.setSize(800, 600); // 调整窗口大小以适应图片和布局
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // 创建 JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();

        // 加载图片
        ImageIcon imageIcon = null;
        try {
            BufferedImage image = ImageIO.read(new File("D:/java/medicalappointment/OIP.jpg"));
            imageIcon = new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, imageIcon != null ? imageIcon.getIconWidth() : 0, imageIcon != null ? imageIcon.getIconHeight() : 0);
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        // 标题 JLabel
        JLabel titleLabel = new JLabel("医疗预约管理系统 - 登录");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titleLabel.setForeground(Color.CYAN);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 50, 800, 30);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);

        // 创建登录面板
        JPanel loginPanel = new RoundedPanel();
        loginPanel.setBackground(new Color(255, 255, 255, 180)); // 半透明白底
        loginPanel.setLayout(new GridLayout(4, 2, 10, 10));
        loginPanel.setBounds(300, 200, 200, 200); // 调整位置和大小
        layeredPane.add(loginPanel, JLayeredPane.PALETTE_LAYER);

        // 输入框和标签
        Font formFont = new Font("微软雅黑", Font.PLAIN, 14);
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(formFont);
        JTextField usernameField = new JTextField();
        usernameField.setFont(formFont);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });
        usernameField.setToolTipText("请输入用户名");

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(formFont);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(formFont);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });
        passwordField.setToolTipText("请输入密码");

        // 分隔线
        JSeparator separator = new JSeparator();
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // 占位
        loginPanel.add(separator);

        // 按钮
        JButton loginBtn = new JButton("登录");
        loginBtn.setFont(new Font("微软雅黑", Font.BOLD, 14));
        loginBtn.setBackground(new Color(70, 130, 180)); // 深海蓝，贴近医疗冷静感
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginBtn.setBackground(new Color(90, 150, 200)); // 亮一点的蓝色
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginBtn.setBackground(new Color(70, 130, 180)); // 恢复原色调
            }
        });

        JButton exitBtn = new JButton("退出");
        exitBtn.setFont(new Font("微软雅黑", Font.BOLD, 14));
        exitBtn.setBackground(new Color(70, 130, 180));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitBtn.setBackground(new Color(90, 150, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn.setBackground(new Color(70, 130, 180));
            }
        });

        loginPanel.add(loginBtn);
        loginPanel.add(exitBtn);

        frame.setContentPane(layeredPane);
        frame.setVisible(true);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            User user = userService.login(username, password);
            if (user == null) {
                JOptionPane.showMessageDialog(frame, "用户名或密码错误");
                return;
            }
            JOptionPane.showMessageDialog(frame, "登录成功，欢迎 " + user.getUsername());

            frame.dispose();

            if ("patient".equalsIgnoreCase(user.getRole())) {
                SwingUtilities.invokeLater(() -> new PatientMainFrame(user).setVisible(true));
            } else if ("doctor".equalsIgnoreCase(user.getRole())) {
                SwingUtilities.invokeLater(() -> new DoctorMainFrame(user).setVisible(true));
            } else if ("admin".equalsIgnoreCase(user.getRole())) {
                SwingUtilities.invokeLater(() -> new AdminMainFrame(user).setVisible(true));
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    static class RoundedPanel extends JPanel {
        private int radius = 15; // 圆角半径

        public RoundedPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 30)); // 黑色半透明阴影
            g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, radius, radius); // 偏移绘制阴影
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g2);
            g2.dispose();
        }
    }
}
