package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.MemberDAO2;


@WebServlet("/CheckEmail")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO2 dao = new MemberDAO2();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter resp = response.getWriter();
		
		String email = request.getParameter("email");
		System.out.println("servlet email : " + email);
	
		try {

			boolean result = dao.isEmailExist(email);
			System.out.println("servlet result : " + result);
			
			if(result) { // true 
				resp.println("이미 사용중인 email입니다.");
			}else { // false
				resp.println("사용 가능한 email입니다.");
			}

		} catch (Exception e) {
			resp.println("조회하는 도중 오류가 발생했습니다.");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
