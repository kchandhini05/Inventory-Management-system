package com.inventory;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginGUI extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;

    public LoginGUI() {
        setTitle("Inventory Management - Login");
        setSize(400, 300);
        setLocationRelativeTo(null); // center screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 118, 232));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(Color.WHITE);

        JLabel lblPass = new JLabel("Password");
        lblPass.setForeground(Color.WHITE);

        txtUser = new JTextField(15);
        txtPass = new JPasswordField(15);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        // ---- Layout ----
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblUser, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtUser, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblPass, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtPass, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        add(panel);

        // Button action
        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    void login() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(1, txtUser.getText());
            ps.setString(2, new String(txtPass.getPassword()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new DashboardGUI();
                dispose();   // close login window

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}
