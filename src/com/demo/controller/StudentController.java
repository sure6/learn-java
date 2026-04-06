package com.demo.controller;

import com.demo.model.Student;
import com.demo.model.StudentDAO;
import com.demo.view.StudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentController {
    private StudentView view;
    private StudentDAO model;

    public StudentController(StudentView view, StudentDAO model) {
        this.view = view;
        this.model = model;

        // 初始化视图数据
        refreshStudentTable();

        // 添加事件监听器
        view.addAddButtonListener(new AddButtonListener());
        view.addEditButtonListener(new EditButtonListener());
        view.addDeleteButtonListener(new DeleteButtonListener());
        view.addRefreshButtonListener(new RefreshButtonListener());
    }

    // 刷新表格数据
    private void refreshStudentTable() {
        List<Student> students = model.getAllStudents();
        view.setTableData(students);
    }

    // 添加按钮事件处理
    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Student student = view.showStudentDialog(null);
            if (student != null) {
                if (model.getStudentById(student.getId()) != null) {
                    view.showError("学号已存在！");
                } else {
                    model.addStudent(student);
                    refreshStudentTable();
                    view.showSuccess("学生添加成功！");
                }
            }
        }
    }

    // 编辑按钮事件处理
    class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getSelectedStudentId();
            if (id == null) {
                view.showError("请先选择要编辑的学生！");
                return;
            }

            Student student = model.getStudentById(id);
            if (student == null) {
                view.showError("找不到选中的学生！");
                return;
            }

            Student updatedStudent = view.showStudentDialog(student);
            if (updatedStudent != null) {
                model.updateStudent(updatedStudent);
                refreshStudentTable();// 刷新表格
                view.showSuccess("学生信息更新成功！");
            }
        }
    }

    // 删除按钮事件处理
    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getSelectedStudentId();
            if (id == null) {
                view.showError("请先选择要删除的学生！");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "确定要删除这个学生吗？",
                    "确认删除",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                if (model.deleteStudent(id)) {
                    refreshStudentTable();
                    view.showSuccess("学生删除成功！");
                } else {
                    view.showError("删除学生失败！");
                }
            }
        }
    }

    // 刷新按钮事件处理
    class RefreshButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshStudentTable();
        }
    }
}