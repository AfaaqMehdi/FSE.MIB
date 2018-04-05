package com.mindtree.mib.chathistory;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mindtree.mib.model.ChatHistory;

public class ExcelViewTest {

	ExcelView excelView;

	@Before
	public void initialize() {
		excelView = new ExcelView();
	}

	@Test
	public void test() {
		try {
			List<ChatHistory> chatHistoryList = new ArrayList<ChatHistory>();
			ChatHistory chatHistory = new ChatHistory("34320", "Sachin", "MIBUSER", "How many centuries", "49", "Yes",
					"No");
			chatHistoryList.add(chatHistory);

			final HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("chatHistory", chatHistoryList);
			
			HttpServletRequest mockRequest = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);

			HSSFWorkbook workbook = new HSSFWorkbook();
			
			excelView.buildExcelDocument(model, workbook, mockRequest,response);
			
			Assert.assertNotNull(workbook);
			
			Sheet sheet = workbook.getSheet("Chat History");
			
			Row row = sheet.getRow(0);
			
			Assert.assertEquals("Player ID", row.getCell(0).getStringCellValue());
			Assert.assertEquals("Player Name", row.getCell(1).getStringCellValue());
			Assert.assertEquals("User Name", row.getCell(2).getStringCellValue());
			Assert.assertEquals("Question", row.getCell(3).getStringCellValue());
			Assert.assertEquals("Response", row.getCell(4).getStringCellValue());
			Assert.assertEquals("IsAnswerValid", row.getCell(5).getStringCellValue());
			Assert.assertEquals("IsAnswerfromGoogle", row.getCell(6).getStringCellValue());
			Assert.assertEquals("TimeStamp", row.getCell(7).getStringCellValue());
			
			row = sheet.getRow(1);
			
			Assert.assertEquals("34320", row.getCell(0).getStringCellValue());
			Assert.assertEquals("Sachin", row.getCell(1).getStringCellValue());
			Assert.assertEquals("MIBUSER", row.getCell(2).getStringCellValue());
			Assert.assertEquals("How many centuries", row.getCell(3).getStringCellValue());
			Assert.assertEquals("49", row.getCell(4).getStringCellValue());
			Assert.assertEquals("Yes", row.getCell(5).getStringCellValue());
			Assert.assertEquals("No", row.getCell(6).getStringCellValue());
			Assert.assertNotNull(row.getCell(7));
			
		} catch (Exception ex) {
			Assert.fail();
		}
	}

}
