package com.hong.excel.controller;

import com.google.gson.Gson;
import com.hong.excel.dto.ExcelSampleDto;
import com.lannstark.excel.ExcelFile;
import com.lannstark.excel.sxssf.onesheet.OneSheetExcelFile;
import com.lannstark.excel.xssf.onesheet.OneSheetXSSFExcel;
import com.lannstark.resource.collection.HeaderNode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ExcelController {

    private Gson gson = new Gson();

    @PostMapping(value = "/export-excel-via-dto")
    public void exportExcel(HttpServletResponse response, @RequestBody List<ExcelSampleDto> data) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        ExcelFile excelFile = new OneSheetExcelFile<>(data, ExcelSampleDto.class);
        excelFile.write(response.getOutputStream());
    }

    @PostMapping(value = "/import-excel-via-dto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<ExcelSampleDto> importExcel(@RequestPart(name = "file") MultipartFile multipartFile) throws IOException {
        ExcelFile<ExcelSampleDto> excelFile = new OneSheetXSSFExcel<>(multipartFile.getInputStream(), ExcelSampleDto.class);
        List<ExcelSampleDto> importedData = excelFile.read(ExcelSampleDto.class);
        return importedData;
    }

    @PostMapping(value = "/export-via-header-node", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void exportViaHeaderNode(
            HttpServletResponse response,
            @RequestParam(name = "data") String dataJson,
            @RequestParam(name = "headerNode") String headerNodeJson
    ) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        List<Map<String, Object>> data = gson.fromJson(dataJson, ArrayList.class);
        HeaderNode headerNode = gson.fromJson(headerNodeJson, HeaderNode.class);
        ExcelFile excelFile = new OneSheetExcelFile(data, headerNode);
        excelFile.write(response.getOutputStream());
    }

    @PostMapping(value = "/import-via-header-node", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Map<String, Object>> exportViaHeaderNode(@RequestPart(name = "file") MultipartFile multipartFile, @RequestPart(name = "headerNode") String headerNodeJson) throws IOException {
        HeaderNode headerNode = gson.fromJson(headerNodeJson, HeaderNode.class);
        ExcelFile<Object> excelFile = new OneSheetXSSFExcel<>(multipartFile.getInputStream(), headerNode);
        List<Map<String, Object>> importedData = excelFile.readFlat();
        return importedData;
    }
}
