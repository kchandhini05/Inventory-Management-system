package com.inventory;

import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {

    public DashboardGUI() {
        setTitle("Dashboard");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header
        JLabel title = new JLabel("Inventory Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel subtitle = new JLabel("Dashboard");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Color.WHITE);
        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);

        mainPanel.add(header, BorderLayout.NORTH);

        // Cards panel
        JPanel cardPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        cardPanel.setBackground(Color.WHITE);

        JButton inventoryBtn = createCardButton(
                "Manage Inventory",
                "Add, update and view products",
                "/icons/inventory.png"
        );

        JButton logoutBtn = createCardButton(
                "Logout",
                "Sign out from system",
                "/icons/logout.png"
        );

        inventoryBtn.addActionListener(e -> {
            new InventoryGUI();
            dispose();
        });

        logoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out successfully");
            new LoginGUI();
            dispose();
        });

        cardPanel.add(inventoryBtn);
        cardPanel.add(logoutBtn);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(cardPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createCardButton(String title, String desc, String iconPath) {
        JButton btn = new JButton();
        btn.setLayout(new BorderLayout());
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(220, 140));
        btn.setBackground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblIcon = new JLabel();
        try {
            lblIcon.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        } catch (Exception e) {
            lblIcon.setText("📦");
            lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));
        }

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));

        JLabel lblDesc = new JLabel("<html><span style='font-size:11px;'>" + desc + "</span></html>");
        lblDesc.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(lblTitle);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(lblDesc);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBackground(Color.WHITE);
        content.add(lblIcon, BorderLayout.WEST);
        content.add(textPanel, BorderLayout.CENTER);

        btn.add(content, BorderLayout.CENTER);

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(245, 248, 252));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.WHITE);
            }
        });

        return btn;
    }

    public static void main(String[] args) {
        new DashboardGUI();
    }
}
