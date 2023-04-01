package com.hong.excel.dto;

import com.lannstark.ExcelColumn;
import lombok.Getter;

@Getter
public class PersonalInfo {
    //
    @ExcelColumn(headerName = "Name", columnIndex = 0)
    private String name;
    @ExcelColumn(headerName = "Age", columnIndex = 1)
    private int age;
    @ExcelColumn(headerName = "Gender", columnIndex = 2)
    private String gender;
}
