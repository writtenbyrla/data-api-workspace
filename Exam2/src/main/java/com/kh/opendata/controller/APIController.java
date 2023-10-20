package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	public static final String serviceKey = "B89IuQPz4Ros4mmF86h9rCpFFjNL%2Bmi%2FnnjSEhyRRqVRyBqoP3ePHSrjoAwS0sGIhvPVTmbS8q0q8EtQV%2FQqxg%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="api.do", produces="application/json; charset=utf-8")
	public String apiInfo(String stnId)  throws IOException{
		// URL (key값 제외한 주소)
		String url = "https://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst";
		
		url += "?serviceKey=" + serviceKey; 
		url += "&tmFc=202310200600";
		url += "&dataType=JSON"; // 기본값 xml, json으로 지정 가능
		url += "&stnId=" + stnId; // 지점번호(front에서 파라미터값으로 넘겨받아서 호출)
		
		// 파라미터 값이 한글이면 utf-8로 인코딩 해야함
		//String stnName = "서울";
		//url += "stnName=" + URLEncoder.encode(stnName, "UTF-8");
		
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET"); // 요청 방식

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		String responseText = "";
		
		while((line = br.readLine())!=null) {
			responseText += line; // responseText에 한줄씩 담아서 리턴
		} 
		
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		
		return responseText;
	}
}
