package com.hong.excel.dto;

import com.lannstark.ExcelColumn;
import lombok.Getter;

@Getter
public class Grade {
    //
    @ExcelColumn(headerName = "Korean", columnIndex = 5)
    private int korean;
    @ExcelColumn(headerName = "English", columnIndex = 4)
    private int english;
    @ExcelColumn(headerName = "Math", columnIndex = 3)
    private int math;
}
