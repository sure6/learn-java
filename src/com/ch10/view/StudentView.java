package com.ch10.view;


import com.ch10.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentView extends JFrame {
    private JTable studentTable;
    private StudentTableModel tableModel;
    private JButton addButton, editButton, deleteButton, refreshButton;
    private JTextField searchField;

    public StudentView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("学生信息管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 主面板
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 表格面板
        JPanel tablePanel = new JPanel(new BorderLayout());
        tableModel = new StudentTableModel(null);
        studentTable = new JTable(tableModel);
        tablePanel.add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("添加");
        editButton = new JButton("编辑");
        deleteButton = new JButton("删除");
        refreshButton = new JButton("刷新");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        // 搜索面板
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("搜索");

        searchPanel.add(new JLabel("学号:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // 添加组件到主面板
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // 设置表格数据
    public void setTableData(List<Student> students) {
        tableModel.setStudents(students);
    }

    // 获取选中的学生ID
    public String getSelectedStudentId() {
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            return (String) tableModel.getValueAt(row, 0);
        }
        return null;
    }

    // 获取搜索框内容
    public String getSearchText() {
        return searchField.getText().trim();
    }

    // 添加按钮监听器
    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    // 添加编辑按钮监听器
    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    // 添加删除按钮监听器
    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    // 添加刷新按钮监听器
    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    // 显示添加/编辑对话框
    public Student showStudentDialog(Student student) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField majorField = new JTextField();

        if (student != null) {
            idField.setText(student.getId());
            nameField.setText(student.getName());
            ageField.setText(String.valueOf(student.getAge()));
            genderField.setText(student.getGender());
            majorField.setText(student.getMajor());
            idField.setEditable(false); // 编辑时不允许修改ID
        }

        panel.add(new JLabel("学号:"));
        panel.add(idField);
        panel.add(new JLabel("姓名:"));
        panel.add(nameField);
        panel.add(new JLabel("年龄:"));
        panel.add(ageField);
        panel.add(new JLabel("性别:"));
        panel.add(genderField);
        panel.add(new JLabel("专业:"));
        panel.add(majorField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                student == null ? "添加学生" : "编辑学生",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String gender = genderField.getText().trim();
                String major = majorField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || gender.isEmpty() || major.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "所有字段都必须填写", "错误", JOptionPane.ERROR_MESSAGE);
                    return null;
                }

                return new Student(id, name, age, gender, major);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "年龄必须是数字", "错误", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return null;
    }

    // 显示错误消息
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "错误", JOptionPane.ERROR_MESSAGE);
    }

    // 显示成功消息
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "成功", JOptionPane.INFORMATION_MESSAGE);
    }
}