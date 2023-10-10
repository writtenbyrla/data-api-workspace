package com.kh.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Application2 {

	public static final String serviceKey = "B89IuQPz4Ros4mmF86h9rCpFFjNL%2Bmi%2FnnjSEhyRRqVRyBqoP3ePHSrjoAwS0sGIhvPVTmbS8q0q8EtQV%2FQqxg%3D%3D";
	
	public static void main(String[] args) throws IOException {
		
		// URL (key값 제외한 주소)
		String url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
		
		url += "?serviceKey=" + serviceKey; 
		
		url += "&numOfRows=10"; // 한 페이지 결과 수(데이터 제일 하단 totalCount를 보면 전체 개수 있음)
		url += "&_type=json"; // 기본값 xml, json으로 지정 가능
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET"); // 요청 방식

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		while((line = br.readLine())!=null) {
			System.out.println(line);
		} 
		
		
		br.close();
		urlConnection.disconnect();
	}

}
