package com.ch10;

import com.ch10.controller.StudentController;
import com.ch10.model.StudentDAO;
import com.ch10.view.StudentView;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        // 使用SwingUtilities.invokeLater确保GUI创建在事件分派线程中
        SwingUtilities.invokeLater(() -> {
            // 创建模型和视图
            StudentDAO model = StudentDAO.getInstance();
            StudentView view = new StudentView();

            // 创建控制器
            new StudentController(view, model);

            // 显示视图
            view.setVisible(true);
        });
    }
}
