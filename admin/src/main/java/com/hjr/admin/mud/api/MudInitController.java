package com.hjr.admin.mud.api;

import jxl.Image;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import util.BaseController;

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

	//		@ResponseBody // 表示返回的是json对象
	//	@RequestMapping(value = "/room_templet", method = RequestMethod.POST)
//	public List<Map<String, Object>> getRoomTempletDataByPost() {


	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<Map<String, Object>> test() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			File excel = new File("D://excel/a.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(0);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						Image image = sheet.getDrawing(0);
						int row = (int) image.getRow();//拿到圖片所在的行索引
						int colums = (int) image.getColumn();//拿到圖片所在的行索引
						map.put(sheet.getCell(j, 0).getContents()+"("+row+","+colums+")",sheet.getDrawing(0).getImageData());
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
	@RequestMapping(value = "/room_templet", method = RequestMethod.GET)
	public List<Map<String, Object>> getRoomTempletDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(0);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(1);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(2);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(3);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(4);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}

		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/ite_have_skill", method = RequestMethod.GET)
	public List<Map<String, Object>> getIteHaveSkillDataByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(5);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(6);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(7);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(8);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(9);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
				}
				returnMsg.add(map);
			}
		} catch (Exception e) {
			return null;
		}
		return returnMsg;
	}


	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/attr", method = RequestMethod.GET)
		public List<Map<String, Object>> getAttrByGet() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_init.xls");
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			Sheet sheet = workbook.getSheet(10);
			String sheetName = sheet.getName();
			int sheetRows = getRightRows(sheet);
			int sheetColumns = getRightCols(sheet);
			for (int i = 1; i < sheetRows; i++) {
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < sheetColumns; j++) {
					if("".equals(sheet.getCell(j, i).getContents())) {
						
					}else {
						map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
					}
				}
				returnMsg.add(map);
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
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//			File excel = new File(path+"/excel/all_init.xls");//参数为空
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			for (int n = 0; n < sheetNum; n++) {
				List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
				Sheet sheet = workbook.getSheet(n);
				String sheetName = sheet.getName();
				int sheetRows = getRightRows(sheet);
				int sheetColumns = getRightCols(sheet);
				switch (sheetName){
					case "":
						for (int i = 1; i < sheetRows; i++) {
							Map<String, Object> map = new HashMap<>();
							for (int j = 0; j < sheetColumns; j++) {
								if("".equals(sheet.getCell(j, i).getContents())) {

								}else {

									map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
								}
							}
							returnMsg.add(map);
							mongoTemplate.save(map, "mud_"+sheetName);
						}
						break;
					default:
						for (int i = 1; i < sheetRows; i++) {
							Map<String, Object> map = new HashMap<>();
							for (int j = 0; j < sheetColumns; j++) {
								if("".equals(sheet.getCell(j, i).getContents())) {

								}else {
									map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
								}
							}
							returnMsg.add(map);
							mongoTemplate.save(map, "mud_"+sheetName);
						}


						break;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}



	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/putToMongoRoom", method = RequestMethod.GET)
	public boolean putToMongoRoomByGet() {
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_map.xls");
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//			File excel = new File(path+"/excel/all_init.xls");//参数为空
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
			int sheetNum = workbook.getNumberOfSheets();// sheet的数目
			for (int n = 0; n < sheetNum; n++) {
				List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
				Sheet sheet = workbook.getSheet(n);
				String sheetName = sheet.getName();
				int sheetRows = getRightRows(sheet);
				int sheetColumns = getRightCols(sheet);
				if (sheetName.contains("country")) {
					for (int i = 0; i < sheetRows; i++) {
						Map<String, Object> map = new HashMap<>();
						for (int j = 0; j < sheetColumns; j++) {
							if(!"".equals(sheet.getCell(j, i).getContents())) {
								map.put("name", sheet.getCell(j, i).getContents());
								map.put("mapXY",j+","+i);
								map.put("countryName",sheetName.split(",")[1]);
								mongoTemplate.save(map, "mud_map");
							}
						}
					}
				}else{
					for (int i = 1; i < sheetRows; i++) {
						Map<String, Object> map = new HashMap<>();
						for (int j = 0; j < sheetColumns; j++) {
							if(!"".equals(sheet.getCell(j, i).getContents())) {
								map.put("name", sheet.getCell(j, i).getContents());
								map.put("mapXY",j+","+i);
								map.put("Name",sheetName.split(",")[1]);
								mongoTemplate.save(map, "mud_room");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/putToMongoAndProcess", method = RequestMethod.GET)
	public boolean putToMongoAndProcessByGet() {
		try {
			InputStream in = this.getClass().getResourceAsStream("/excel/all_int.xls");
			File excel = new File("D://excel/all_init.xls");//参数为空
			Workbook workbook = Workbook.getWorkbook(excel);
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
						if("".equals(sheet.getCell(j, i).getContents())) {

						}else {
							map.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
						}
					}
					returnMsg.add(map);
					mongoTemplate.save(map, "mud_"+sheetName);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}
}
