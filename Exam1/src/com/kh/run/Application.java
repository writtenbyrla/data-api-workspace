package com.kh.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	
	// serviceKey 값(공공데이터 사이트에서 승인 받은 serviceKey값)
	public static final String serviceKey = "B89IuQPz4Ros4mmF86h9rCpFFjNL%2Bmi%2FnnjSEhyRRqVRyBqoP3ePHSrjoAwS0sGIhvPVTmbS8q0q8EtQV%2FQqxg%3D%3D";

	public static void main(String[] args) throws IOException {
		
		// URL(key값 제외한 주소)
		String url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
		
		url += "?serviceKey=" + serviceKey; // 첫번째 파라미터값 serviceKey값 필수이므로 무조건 넣어주면서 상단 serviceKey값 넘김
		url += "&numOfRows=5"; // 한 페이지 결과 수(데이터 제일 하단 totalCount를 보면 전체 개수 있음)
		url += "&_type=json"; // 기본값 xml, json으로 지정 가능
		
		URL requestUrl = new URL(url); // URL import 후 IOException 던짐
		// HttpURLConnection import 후 형변환
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET"); // 요청 방식
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		// 파싱할 때 한줄씩 담기 위해서 선언
		StringBuffer responseBuffer = new StringBuffer();
		
		while((line = br.readLine())!=null) {
//			System.out.println(line);
			responseBuffer.append(line); // 한줄씩 담음
		} 
		
		br.close();
		urlConnection.disconnect();
		
		// JSON 파싱 시작
		String responseData = responseBuffer.toString(); // StringBuffer -> 문자열 타입 변환
		JSONObject jsonResponse = new JSONObject(responseData); // json 라이브러리에 있으므로 import / 문자열 -> json 객체 타입 변환
		
		// 구조 파악
		JSONObject response = jsonResponse.getJSONObject("response");
		JSONObject body = response.getJSONObject("body");
		JSONObject items = body.getJSONObject("items");
		JSONArray item = items.getJSONArray("item");
		
		for(int i=0; i<item.length(); i++){
			JSONObject result = item.getJSONObject(i);
			String careNm = result.getString("careNm");
			String careAddr = result.getString("careAddr");
			System.out.println("동물보호센터명 : " + careNm);
			System.out.println("주소 : " + careAddr);
			System.out.println();
			
		}
	}

}
