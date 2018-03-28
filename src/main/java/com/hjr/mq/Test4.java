// package com.hjr.mq;
//
// import java.io.File;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import org.springframework.util.ResourceUtils;
//
// import jxl.Sheet;
// import jxl.Workbook;
//
// public class Test4 {
//
// public static void main(String[] args) throws Exception {
// List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
// File file = ResourceUtils.getFile("classpath:excel/美琪2017春升秋.xls");
// Workbook workbook = Workbook.getWorkbook(file);
// int sheetNum = workbook.getNumberOfSheets();// sheet的数目
// Sheet sheet = workbook.getSheet(0);
// String sheetName = sheet.getName();
// int sheetRows = getRightRows(sheet);
// int sheetColumns = getRightCols(sheet);
// for (int i = 1; i < sheetRows; i++) {
// Map<String, Object> map = new HashMap<>();
// for (int j = 0; j < sheetColumns; j++) {
// if (j == 2) {
//
// }
// map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j,
// i).getContents());
//
// }
// data.add(map);
// }
// }
//
// // 返回去掉空行的记录数
// private static int getRightRows(Sheet sheet) {
// int rsCols = sheet.getColumns(); // 列数
// int rsRows = sheet.getRows(); // 行数
// int nullCellNum;
// int afterRows = rsRows;
// for (int i = 1; i < rsRows; i++) { // 统计行中为空的单元格数
// nullCellNum = 0;
// for (int j = 0; j < rsCols; j++) {
// String val = sheet.getCell(j, i).getContents();
// if (val == null || val.equals("")) {
// nullCellNum++;
// }
// }
// if (nullCellNum >= rsCols) { // 如果nullCellNum大于或等于总的列数
// afterRows--; // 行数减一
// }
// }
// return afterRows;
// }
//
// // 返回去掉空列的记录数
// private static int getRightCols(Sheet sheet) {
// int rsCols = sheet.getColumns(); // 列数
// int rsRows = sheet.getRows(); // 行数
// int nullCellNum;
// int afterCols = rsCols;
// for (int i = 0; i < rsCols; i++) { // 统计列中为空的单元格数
// nullCellNum = 0;
// for (int j = 0; j < rsRows; j++) {
// String val = sheet.getCell(i, j).getContents();
// if (val == null || val.equals("")) {
// nullCellNum++;
// }
// }
// if (nullCellNum >= rsRows) { // 如果nullCellNum大于或等于总的行数
// afterCols--; // 列数减一
// }
// }
// return afterCols;
// }
// }