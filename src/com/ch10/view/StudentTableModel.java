package com.ch10.view;


import com.ch10.model.Student;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private List<Student> students;
    private final String[] columnNames = {"学号", "姓名", "年龄", "性别", "专业"};

    public StudentTableModel(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0: return student.getId();
            case 1: return student.getName();
            case 2: return student.getAge();
            case 3: return student.getGender();
            case 4: return student.getMajor();
            default: return null;
        }
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        fireTableDataChanged();
    }
}