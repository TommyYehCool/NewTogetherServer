package com.exfantasy.util;

public class GeoUtil {
	/**
	 * 計算兩個經緯度的距離, 單位：公尺
	 * 
	 * http://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
	 * 
	 * http://www.scriptscoop.net/t/8484eb8acab8/javascript-distance-direction-of-longitude-latitude-relative-to-a-center-point.html
	 * 
	 * 下面用 sql 好像比較好
	 * 
	 * http://janmatuschek.de/LatitudeLongitudeBoundingCoordinates
	 * 
	 * https://developers.google.com/maps/articles/phpsqlsearch_v3?hl=zh-TW
	 * 
	 * http://www.tamabc.com/article/98835.html
	 * 
	 * http://zengqingmeng.cn/2015/04/08/mysqldistance/
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * 
	 * @return 兩個經緯度的距離, 單位：公尺
	 */
	public static float distFrom(Double lat1, Double lng1, Double lat2, Double lng2) {
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist;
	}

}
