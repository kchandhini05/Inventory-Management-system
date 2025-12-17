package com.inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class InventoryGUI extends JFrame {

    JTextField txtId, txtName, txtQty, txtPrice;
    JTable table;
    DefaultTableModel model;
    ProductDAO dao = new ProductDAO();

    public InventoryGUI() {
        setTitle("Inventory Management System");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        // ===== HEADER =====
        JLabel title = new JLabel("Inventory Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(40, 40, 40));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.add(title, BorderLayout.WEST);
        mainPanel.add(header, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        formPanel.setBackground(Color.WHITE);

        txtId = createField();
        txtName = createField();
        txtQty = createField();
        txtPrice = createField();

        formPanel.add(createLabel("Product ID"));
        formPanel.add(txtId);
        formPanel.add(createLabel("Product Name"));
        formPanel.add(txtName);
        formPanel.add(createLabel("Quantity"));
        formPanel.add(txtQty);
        formPanel.add(createLabel("Price"));
        formPanel.add(txtPrice);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnAdd = createButton("Add");
        JButton btnUpdate = createButton("Update");
        JButton btnDelete = createButton("Delete");
        JButton btnClear = createButton("Clear");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Quantity", "Price"}, 0
        );
        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Inventory List"));
        mainPanel.add(scroll, BorderLayout.CENTER);

        // ===== ACTIONS =====
        btnAdd.addActionListener(e -> addProduct());
        btnUpdate.addActionListener(e -> updateProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnClear.addActionListener(e -> clearFields());

        loadTable();
        setVisible(true);
    }

    // ===== UI HELPERS =====
    JTextField createField() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return f;
    }

    JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return l;
    }

    JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setBackground(new Color(45, 118, 232));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        return b;
    }

    // ===== FUNCTIONS =====
    void addProduct() {
        try {
            Product p = new Product(
                    txtName.getText(),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble(txtPrice.getText())
            );
            dao.addProduct(p);
            loadTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Product Added Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input");
        }
    }

    void updateProduct() {
        try {
            Product p = new Product(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble(txtPrice.getText())
            );
            dao.updateProduct(p);
            loadTable();
            JOptionPane.showMessageDialog(this, "Product Updated Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input");
        }
    }

    void deleteProduct() {
        try {
            dao.deleteProduct(Integer.parseInt(txtId.getText()));
            loadTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Product Deleted Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Product ID");
        }
    }

    void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtQty.setText("");
        txtPrice.setText("");
    }

    void loadTable() {
        model.setRowCount(0);
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM products")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new InventoryGUI();
    }
}

