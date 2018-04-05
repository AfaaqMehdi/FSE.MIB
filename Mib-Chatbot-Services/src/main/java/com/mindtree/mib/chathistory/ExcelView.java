package com.mindtree.mib.chathistory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.mindtree.mib.model.ChatHistory;

public class ExcelView extends AbstractXlsView {

	private static final Logger LOGGER = Logger.getLogger(ExcelView.class);

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"ChatHistory.xls\"");
	
		LOGGER.info("Entering method buildExcelDocument ");
		
		@SuppressWarnings("unchecked")
		List<ChatHistory> chatHistoryList = (List<ChatHistory>) model.get("chatHistory");

		// create excel xls sheet
		Sheet sheet = workbook.createSheet("Chat History");
		sheet.setDefaultColumnWidth(30);

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		font.setBold(true);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Player ID");
		header.createCell(1).setCellValue("Player Name");
		header.createCell(2).setCellValue("User Name");
		header.createCell(3).setCellValue("Question");
		header.createCell(4).setCellValue("Response");
		header.createCell(5).setCellValue("IsAnswerValid");
		header.createCell(6).setCellValue("IsAnswerfromGoogle");
		header.createCell(7).setCellValue("TimeStamp");

		int rowCount = 1;

		for (ChatHistory chatHistory : chatHistoryList) {
			Row userRow = sheet.createRow(rowCount++);
			userRow.createCell(0).setCellValue(chatHistory.getPid());
			userRow.createCell(1).setCellValue(chatHistory.getPlayerName());
			userRow.createCell(2).setCellValue(chatHistory.getUserName());
			userRow.createCell(3).setCellValue(chatHistory.getQuestion());
			userRow.createCell(4).setCellValue(chatHistory.getResponse());
			userRow.createCell(5).setCellValue(chatHistory.getIsAnswerValid());
			userRow.createCell(6).setCellValue(chatHistory.getIsAnswerfromGoogle());
			userRow.createCell(7).setCellValue(chatHistory.getTimeStamp());
			LOGGER.info(rowCount + "-" + chatHistory.toString());
		}

	}

}