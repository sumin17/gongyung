package parking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Service.map.ParkinglotService;
import Service.map.ParkinglotServiceImpl;

@WebServlet({"/api/parkinglot","/api/parkinglot/*"})
public class ParkinglotServlet extends HttpServlet{
	private ParkinglotService parkingservice;

	@Override
	public void init(ServletConfig config) throws ServletException {
		parkingservice = new ParkinglotServiceImpl(new ParkingDAOImpl());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST, OPTION");
		resp.setHeader("Access-Control-Allow-Headers", "*");
		
		List<Parkinglot> list = parkingservice.readPark();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		
		PrintWriter pw = resp.getWriter();
		pw.println(json);
		pw.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		resp.setHeader("Access-Control-Allow-Headers", "*");
		
		req.setCharacterEncoding("UTF-8");
		
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		
		String storename = req.getParameter("storename");
		System.out.println("storename" + storename);
		
		List<Parkinglot> list = parkingservice.readParkByStoreName(storename);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		
		PrintWriter pw = resp.getWriter();
		pw.println(json);
		pw.flush();
		
	}

	private Parkinglot jsonToParkinglot(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Parkinglot.class);
	}
	
	private String readBody(HttpServletRequest req) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
	

}
