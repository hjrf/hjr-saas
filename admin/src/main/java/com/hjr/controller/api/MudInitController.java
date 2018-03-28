package com.hjr.controller.api;

import com.hjr.controller.BaseController;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/mud_init")
public class MudInitController extends BaseController {

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public List<Map<String, Object>> getMapDataByPost() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(0);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/map", method = RequestMethod.POST)
	public List<Map<String, Object>> getMapDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(0);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/room_templet", method = RequestMethod.GET)
	public List<Map<String, Object>> getRoomTempletDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(1);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/character", method = RequestMethod.GET)
	public List<Map<String, Object>> getCharacterDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(2);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public List<Map<String, Object>> getItemDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(3);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/skill", method = RequestMethod.GET)
	public List<Map<String, Object>> getSkillDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(4);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public List<Map<String, Object>> getRoleDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(5);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/cha_have_item", method = RequestMethod.GET)
	public List<Map<String, Object>> getChaHaveItemDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(6);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/work", method = RequestMethod.GET)
	public List<Map<String, Object>> getWorkDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(7);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}
		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/talk", method = RequestMethod.GET)
	public List<Map<String, Object>> getTalkDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(8);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}
		return returnMsg;
	}
	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/cha_have_skill", method = RequestMethod.GET)
	public List<Map<String, Object>> getChaHaveSkillByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(9);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}
		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public List<Map<String, Object>> getTaskByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(10);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}
		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/all_map", method = RequestMethod.GET)
	public List<Map<String, Object>> getAllMapDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_map.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			for (int n = 0; n < sheetNum; n++) {
				Sheet sheet = workbook.getSheet(6);
				String sheetName = sheet.getName();
				int sheetRows = getRightRows(sheet);
				int sheetColumns = getRightCols(sheet);
				for (int i = 0; i < sheetRows; i++) {
					Map<String, Object> map = new HashMap<>();
					for (int j = 0; j < sheetColumns; j++) {
						map.put(j + "," + i, sheet.getCell(j, i).getContents());
					}
					returnMsg.add(map);
				}
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	// 返回去掉空行的记录数
	private static int getRightRows(Sheet sheet) {
		int rsCols = sheet.getColumns(); // 列数
		int rsRows = sheet.getRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 1; i < rsRows; i++) { // 统计行中为空的单元格数
			nullCellNum = 0;
			for (int j = 0; j < rsCols; j++) {
				String val = sheet.getCell(j, i).getContents();
				if (val == null || val.equals("")) {
					nullCellNum++;
				}
			}
			if (nullCellNum >= rsCols) { // 如果nullCellNum大于或等于总的列数
				afterRows--; // 行数减一
			}
		}
		return afterRows;
	}

	// 返回去掉空列的记录数
	private static int getRightCols(Sheet sheet) {
		int rsCols = sheet.getColumns(); // 列数
		int rsRows = sheet.getRows(); // 行数
		int nullCellNum;
		int afterCols = rsCols;
		for (int i = 0; i < rsCols; i++) { // 统计列中为空的单元格数
			nullCellNum = 0;
			for (int j = 0; j < rsRows; j++) {
				String val = sheet.getCell(i, j).getContents();
				if (val == null || val.equals("")) {
					nullCellNum++;
				}
			}
			if (nullCellNum >= rsRows) { // 如果nullCellNum大于或等于总的行数
				afterCols--; // 列数减一
			}
		}
		return afterCols;
	}






	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/putToMongo", method = RequestMethod.GET)
	public boolean putToMongoByGet() {
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_int.xls");
			Workbook workbook = Workbook.getWorkbook(in);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			for (int n = 0; n < sheetNum; n++) {
				List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
				Sheet sheet = workbook.getSheet(n);
				String sheetName = sheet.getName();
				int sheetRows = getRightRows(sheet);
				int sheetColumns = getRightCols(sheet);
				for (int i = 1; i < sheetRows; i++) {
					Map<String, Object> map = new HashMap<>();
					for (int j = 0; j < sheetColumns; j++) {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
					returnMsg.add(map);
					mongoTemplate.save(map, sheetName);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}
}
