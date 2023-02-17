package GasStation;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Service.map.GasStationService;
import Service.map.GasStationServiceImpl;


public class GasStationServletImpl extends HttpServlet {
	private GasStationService gasService;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		gasService = new GasStationServiceImpl(new GasStationDAOImpl());
		
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String json;
//		String doby = readBody(req);
//		try {
//			GasStation gasStation = jsonToGasStation(gasStation);
//		}
	}


	
	private GasStation jsonToGasStation(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, GasStation.class);// 읽고 book class에 맵핑 후 반환
	}
	
	

	private String readBody(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("UTF-8");
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

}
