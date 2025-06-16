package com.medicalappointment.ui;

import com.medicalappointment.model.User;
import com.medicalappointment.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private UserService userService = new UserService();

    public RegistrationFrame() {
        setTitle("医疗预约管理系统 - 注册");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 居中

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(50, 30, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(140, 30, 200, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(50, 70, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(140, 70, 200, 25);
        panel.add(passwordField);

        JLabel roleLabel = new JLabel("角色:");
        roleLabel.setBounds(50, 110, 80, 25);
        panel.add(roleLabel);

        String[] roles = {"patient", "doctor", "admin"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(140, 110, 200, 25);
        panel.add(roleComboBox);

        JButton registerButton = new JButton("注册");
        registerButton.setBounds(140, 150, 100, 30);
        panel.add(registerButton);

        getContentPane().add(panel);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerAction();
            }
        });
    }

    private void registerAction() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空！");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        boolean success = userService.register(user);
        if (success) {
            JOptionPane.showMessageDialog(this, "注册成功，请登录！");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "注册失败，用户名可能已存在！");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationFrame().setVisible(true);
        });
    }
}