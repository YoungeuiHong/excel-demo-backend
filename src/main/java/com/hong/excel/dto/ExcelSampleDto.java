package com.hong.excel.dto;

import com.lannstark.*;
import com.lannstark.style.DefaultExcelCellStyle;
import lombok.Getter;

@Getter
@DefaultHeaderStyle(
        style = @ExcelColumnStyle(excelCellStyleClass = DefaultExcelCellStyle.class, enumName = "BLUE_HEADER")
)
@DefaultBodyStyle(
        style = @ExcelColumnStyle(excelCellStyleClass = DefaultExcelCellStyle.class, enumName = "BODY")
)
@ExcelImport(
        isColumnIndexAssigned = true
)
public class ExcelSampleDto {
    @ExcelColumn(headerName = "Personal Info")
    private PersonalInfo personalInfo;
    @ExcelColumn(headerName = "Grade")
    private Grade grade;
}
