package com.cqut.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StageStandard {

	public static List<Map<String, Object>> getResult(
			List<Map<String, Object>> searchList, int property, String Value,
			String name, String valueString, String date) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> resultlist2 = new ArrayList<Map<String, Object>>();
		List<String> entityList = new ArrayList<String>();
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> map = null;
		String titleName = "";
		Map<String, Object> indexMap = setIndex(property, Value);
		int startIndex = Integer
				.parseInt(indexMap.get("startIndex").toString());
		int endIndex = Integer.parseInt(indexMap.get("endIndex").toString());

		for (int i = 0; i < searchList.size(); i++) {
			String expe = searchList.get(i).toString()
					.substring(1, searchList.get(i).toString().length() - 1);
			String[] expeArray = expe.split(",");
			String[] entityNames = null;
			if (expeArray[0].contains(date)) {
				entityNames = expeArray[1].split("=")[1].split(";");
			} else {
				entityNames = expeArray[0].split("=")[1].split(";");
			}
			for (int j = 0; j < entityNames.length; j++) {
				String entityName = entityNames[j].split(":")[0];
				if (!entityList.contains(entityName)) {
					entityList.add(entityName);
				}
			}
		}

		for (int i = startIndex; i <= endIndex; i++) {
			int index = -1;
			int dates = 0;
			if (property == 0) {
				if (i < 10) {
					titleName = Value + "年0" + i + "月";
				} else {
					titleName = Value + "年" + i + "月";
				}
			} else if (property == 1) {
				int year = Integer.parseInt(Value.split("年")[0]);
				if (i < 10) {
					titleName = year + "年0" + i + "月";
				} else {
					titleName = year + "年" + i + "月";
				}

			} else if (property == 2) {
				int month = Integer.parseInt(Value.split("年")[1].split("月")[0]);
				String monthString = "";
				if (month < 10) {
					monthString = "0" + month;
				} else {
					monthString = "" + month;
				}
				if (i < 10) {
					titleName = monthString + "月0" + i + "日";
				} else {
					titleName = monthString + "月" + i + "日";
				}
			} else {
				if (i < 10) {
					titleName = "0" + i + "点";
				} else {
					titleName = i + "点";
				}
			}
			resultMap = setEntityMap(entityList, name, valueString, titleName);
			for (int j = 0; j < searchList.size() && dates != i; j++) {
				String expe = searchList
						.get(j)
						.toString()
						.substring(1, searchList.get(j).toString().length() - 1);
				String[] expeArray = expe.split(",");
				if (expeArray[0].contains(date)) {
					dates = Integer.parseInt(expeArray[0].split("=")[1]);
				} else {
					dates = Integer.parseInt(expeArray[1].split("=")[1]);
				}

				if (dates == i) {
					index = j;
				}
			}

			if (index > -1) {
				String oneRecord = searchList.get(index).toString();
				oneRecord = oneRecord.substring(1, oneRecord.length() - 1);
				String[] expeArray = oneRecord.split(",");
				String[] express = null;
				if (expeArray[0].contains(date)) {
					express = expeArray[1].split("=")[1].split(";");
				} else {
					express = expeArray[0].split("=")[1].split(";");
				}

				for (int j = 0; j < express.length; j++) {
					String propertyName = express[j].split(":")[0];
					String propertyValue = express[j].split(":")[1];
					resultMap
							.put(propertyName, Integer.parseInt(propertyValue));
				}
			}
			resultlist.add(resultMap);

		}

		return resultlist;
	}

	public static Map<String, Object> getResult2(
			List<Map<String, Object>> searchList, int property, String Value,
			String name, String valueString, String date, String tableName) {

		Map<String, Object> result = new LinkedHashMap<String, Object>();

		List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
		List<String> entityList = new ArrayList<String>();
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> map = null;
		String titleName = "";
		Map<String, Object> indexMap = setIndex(property, Value);
		int startIndex = Integer
				.parseInt(indexMap.get("startIndex").toString());
		int endIndex = Integer.parseInt(indexMap.get("endIndex").toString());

		List<Map<String, Object>> resultlist2 = new ArrayList<Map<String, Object>>();
		resultMap = new LinkedHashMap<String, Object>();
		resultMap.put("dataIndex", "Name");
		resultMap.put("title", tableName);
		resultlist2.add(resultMap);

		for (int i = startIndex; i <= endIndex; i++) {
			resultMap = new LinkedHashMap<String, Object>();
			titleName = getTitle(property, Value, i);
			resultMap.put("dataIndex", titleName);
			resultMap.put("title", titleName);
			resultlist2.add(resultMap);
		}

		result.put("column", resultlist2);
		for (int i = 0; i < searchList.size(); i++) {
			String expe = searchList.get(i).toString()
					.substring(1, searchList.get(i).toString().length() - 1);
			String[] expeArray = expe.split(",");
			String[] entityNames = null;
			if (expeArray[0].contains(date)) {
				if (expeArray.length > 1) {
					entityNames = expeArray[1].split("=")[1].split(";");
				}
			} else {
				if (expeArray.length > 1) {
					entityNames = expeArray[0].split("=")[1].split(";");
				}
			}
			if (entityNames != null) {
				for (int j = 0; j < entityNames.length; j++) {
					String entityName = entityNames[j].split(":")[0];
					if (!entityList.contains(entityName)) {
						entityList.add(entityName);
					}
				}
			}

		}

		for (int i = 0; i < entityList.size(); i++) {
			resultMap = new LinkedHashMap<String, Object>();
			resultMap.put("Name", entityList.get(i));

			for (int j = startIndex; j <= endIndex; j++) {
				int dates = 0;
				int index = -1;
				int entityValue = 0;
				titleName = getTitle(property, Value, j);

				for (int k1 = 0; k1 < searchList.size(); k1++) {
					String oneRecord = searchList.get(k1).toString();
					oneRecord = oneRecord.substring(1, oneRecord.length() - 1);
					String[] expeArray = oneRecord.split(",");
					String[] express = null;
					if (expeArray[0].contains(date)) {
						if (expeArray.length > 1) {
							express = expeArray[1].split("=")[1].split(";");
							dates = Integer.parseInt(expeArray[0].split("=")[1]);
						}
					} else {
						if (expeArray.length > 1) {
							express = expeArray[0].split("=")[1].split(";");
							dates = Integer.parseInt(expeArray[1].split("=")[1]);
						}
					}
					if (dates == j) {
						if (express!=null) {
							for (int k = 0; k < express.length && k != index; k++) {
								String name1 = express[k].split(":")[0];
								if (name1.equals(entityList.get(i).toString())) {
									entityValue = Integer.parseInt(express[k]
											.split(":")[1]);
									index = k + 1;
								}
							}
						}
					}
					resultMap.put(titleName, entityValue);
				}

			}
			resultlist.add(resultMap);
		}

		result.put("rowData", resultlist);
		// result.put("total", total);

		return result;
	}

	public static List<Map<String, Object>> getResult3(
			List<Map<String, Object>> searchList, int property, String Value,
			String name, String valueString, String date) {

		List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> singleList = new ArrayList<Map<String, Object>>();
		List<String> entityList = new ArrayList<String>();
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> map = null;
		String titleName = "";
		Map<String, Object> indexMap = setIndex(property, Value);
		int startIndex = Integer
				.parseInt(indexMap.get("startIndex").toString());
		int endIndex = Integer.parseInt(indexMap.get("endIndex").toString());

		for (int i = 0; i < searchList.size(); i++) {
			String expe = searchList.get(i).toString()
					.substring(1, searchList.get(i).toString().length() - 1);
			String[] expeArray = expe.split(",");
			String[] entityNames = null;
			if (expeArray[0].contains(date)) {
				entityNames = expeArray[1].split("=")[1].split(";");
			} else {
				entityNames = expeArray[0].split("=")[1].split(";");
			}
			for (int j = 0; j < entityNames.length; j++) {
				String entityName = entityNames[j].split(":")[0];
				if (!entityList.contains(entityName)) {
					entityList.add(entityName);
				}
			}
		}

		for (int i = startIndex; i <= endIndex; i++) {
			int index = -1;
			int dates = 0;
			if (property == 0) {
				if (i < 10) {
					titleName = Value + "年0" + i + "月";
				} else {
					titleName = Value + "年" + i + "月";
				}
			} else if (property == 1) {
				int year = Integer.parseInt(Value.split("年")[0]);
				if (i < 10) {
					titleName = year + "年0" + i + "月";
				} else {
					titleName = year + "年" + i + "月";
				}

			} else if (property == 2) {
				int month = Integer.parseInt(Value.split("年")[1].split("月")[0]);
				String monthString = "";
				if (month < 10) {
					monthString = "0" + month;
				} else {
					monthString = "" + month;
				}
				if (i < 10) {
					titleName = monthString + "月0" + i + "日";
				} else {
					titleName = monthString + "月" + i + "日";
				}
			} else {
				if (i < 10) {
					titleName = "0" + i + "点";
				} else {
					titleName = i + "点";
				}
			}

			singleList = new ArrayList<Map<String, Object>>();
			singleList = setEntityMap3(entityList, name, valueString);
			for (int j = 0; j < searchList.size() && dates != i; j++) {
				String expe = searchList
						.get(j)
						.toString()
						.substring(1, searchList.get(j).toString().length() - 1);
				String[] expeArray = expe.split(",");
				if (expeArray[0].contains(date)) {
					dates = Integer.parseInt(expeArray[0].split("=")[1]);
				} else {
					dates = Integer.parseInt(expeArray[1].split("=")[1]);
				}

				if (dates == i) {
					index = j;
				}
			}

			if (index > -1) {
				String oneRecord = searchList.get(index).toString();
				oneRecord = oneRecord.substring(1, oneRecord.length() - 1);
				String[] expeArray = oneRecord.split(",");
				String[] express = null;
				if (expeArray[0].contains(date)) {
					express = expeArray[1].split("=")[1].split(";");
				} else {
					express = expeArray[0].split("=")[1].split(";");
				}

				for (int j = 0; j < express.length; j++) {
					map = new HashMap<String, Object>();
					String propertyName = "";
					String propertyValue = "";
					propertyName = express[j].split(":")[0];
					propertyValue = express[j].split(":")[1];
					map.put(name, propertyName);
					map.put(valueString, 0);
					if (singleList.contains(map)) {
						int propertyIndex = singleList.indexOf(map);
						singleList.get(propertyIndex).put(valueString,
								Integer.parseInt(propertyValue));
					}

				}
			}

			resultMap.put(titleName, singleList);
		}

		resultlist.add(resultMap);
		return resultlist;
	}

	public static Map<String, Object> setEntityMap(List<String> entityNameList,
			String name, String valueString, String titleName) {
		Map<String, Object> singleMap = new LinkedHashMap<String, Object>();
		singleMap.put("name", titleName);
		for (int i = 0; i < entityNameList.size(); i++) {
			singleMap.put(entityNameList.get(i).toString(), 0);
		}
		return singleMap;
	}

	public static List<Map<String, Object>> setEntityMap3(
			List<String> entityNameList, String name, String valueString) {
		List<Map<String, Object>> singleListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> singleMap = null;
		for (int i = 0; i < entityNameList.size(); i++) {
			singleMap = new LinkedHashMap<String, Object>();
			singleMap.put(name, entityNameList.get(i).toString());
			singleMap.put(valueString, 0);
			singleListMap.add(singleMap);
		}
		return singleListMap;
	}

	/**
	 * 
	 * 方法简述：设置开始和结束坐标
	 * 
	 * @param property
	 *            属性值（年份，月份，季度）
	 * @param Value
	 *            属性对应的值
	 * @return
	 * @author wangyuanling
	 * @date 2016年12月2日 下午12:52:26
	 */
	public static Map<String, Object> setIndex(int property, String Value) {
		Map<String, Object> indexMap = new LinkedHashMap<String, Object>();
		int endIndex = 0;
		if (property == 0) {// 年份的时候显示所有的月份
			indexMap.put("startIndex", 1);
			indexMap.put("endIndex", 12);
		} else if (property == 1) {// 季度的时候显示相应阶段的月份
			if (Value.split("年")[1].equals("第一季度")) {
				indexMap.put("startIndex", 1);
				indexMap.put("endIndex", 3);
			} else if (Value.split("年")[1].equals("第二季度")) {
				indexMap.put("startIndex", 4);
				indexMap.put("endIndex", 6);
			} else if (Value.split("年")[1].equals("第三季度")) {
				indexMap.put("startIndex", 7);
				indexMap.put("endIndex", 9);
			} else {
				indexMap.put("startIndex", 10);
				indexMap.put("endIndex", 12);
			}
		} else if (property == 2) {
			int year = Integer.parseInt(Value.split("年")[0]);
			int month = Integer.parseInt(Value.split("年")[1].split("月")[0]);
			indexMap.put("startIndex", 1);
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				indexMap.put("endIndex", 31);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				indexMap.put("endIndex", 30);
				break;
			case 2:
				endIndex = isLeapYear(year) ? 29 : 28;
				indexMap.put("endIndex", endIndex);
				break;
			default:
				break;
			}
		} else {
			indexMap.put("startIndex", 0);
			indexMap.put("endIndex", 23);
		}
		return indexMap;
	}

	/**
	 * 
	 * 方法简述：
	 * 
	 * @param keyPointName
	 * @param date
	 * @param faultEarlyWarn
	 * @param start
	 * @param end
	 * @return
	 * @author wangyuanling
	 * @date 2016年12月3日 上午9:27:50
	 */
	public static int getValueByDateAndName(String keyPointName, int date,
			List<Map<String, Object>> faultEarlyWarn, int start, int end) {
		int result = 0;
		for (int i = start; i < end; i++) {

		}

		return result;
	}

	/**
	 * 
	 * 方法简述：判断是平年还是闰年
	 * 
	 * @param year
	 * @return
	 * @author wangyuanling
	 * @date 2016年12月3日 上午9:27:07
	 */
	private static boolean isLeapYear(int year) {
		return ((year % 100 != 0) && (year % 4 == 0)) || (year % 400 == 0);
	}

	public static String getTitle(int property, String Value, int i) {
		String titleName = "";
		if (property == 0) {
			if (i < 10) {
				titleName = Value + "-0" + i;
			} else {
				titleName = Value + "-" + i;
			}
		} else if (property == 1) {
			int year = Integer.parseInt(Value.split("年")[0]);
			if (i < 10) {
				titleName = year + "0" + i;
			} else {
				titleName = year + "-" + i;
			}

		} else if (property == 2) {
			int month = Integer.parseInt(Value.split("年")[1].split("月")[0]);
			String monthString = "";
			if (month < 10) {
				monthString = "0" + month;
			} else {
				monthString = "" + month;
			}
			if (i < 10) {
				titleName = monthString + "-0" + i;
			} else {
				titleName = monthString + "-" + i;
			}
		} else {
			if (i < 10) {
				titleName = "0" + i + "点";
			} else {
				titleName = i + "点";
			}
		}
		return titleName;
	}
}
