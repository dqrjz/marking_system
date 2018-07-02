package ga.dqrjz.marking.util;

import ga.dqrjz.marking.mapper.DocumentMapper;
import ga.dqrjz.marking.mapper.FactMapper;
import ga.dqrjz.marking.mapper.LawMapper;
import ga.dqrjz.marking.mapper.MarkMapper;
import ga.dqrjz.marking.pojo.Document;
import ga.dqrjz.marking.pojo.Fact;
import ga.dqrjz.marking.pojo.Law;
import ga.dqrjz.marking.pojo.Mark;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ImportUtil {
	private String documentPath;
	@Autowired
	private DocumentMapper documentMapper;
	@Autowired
	private LawMapper lawMapper;
	@Autowired
	private FactMapper factMapper;
	@Autowired
	private MarkMapper markMapper;
	
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
		importAll();
	}
	
	private void importAll() {
		String path = this.getClass().getResource(documentPath).getFile();
		File documentFolder = new File(path);
		List<File> fileList = Arrays.asList(Objects.requireNonNull(documentFolder.listFiles()));
		
		String documentAssignmentStatus; // N=unassigned, Y=assigned
		String documentMarkingStatus, factMarkingStatus; // N=unmarked, Y=marked
		Long userId; // -1=unassigned
		String case_name, case_number, case_reason, case_category;
		case_name = case_number = case_reason = case_category = "..."; // default
		Integer value; // mark value (-1=unmarked, 0=unrelated, 1=related)
		if (documentMapper.selectCount(null) != fileList.size()) {
			Long factId = 0L, lawId = 0L, markId = 0L;
			Collections.sort(fileList);
			System.out.println(fileList);
			for (File file : fileList) {
				//importDocuments
				String filenameXls = file.getName();
				Long documentId = Long.parseLong(filenameXls.substring(0, filenameXls.indexOf("_")));
				String filenameXml = filenameXls.substring(0, filenameXls.lastIndexOf("_"));
				documentAssignmentStatus = documentMarkingStatus = "N";
				userId = -1L;
//				case_name = case_number = case_reason = case_category = "...";
				documentMapper.insert(new Document(documentId, filenameXml, filenameXls, documentAssignmentStatus,
						userId, documentMarkingStatus, case_name, case_number, case_reason, case_category));
				
				//获取表格
				HSSFWorkbook hssfWorkbook = null;
				try {
					hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
				HSSFSheet sheet = Objects.requireNonNull(hssfWorkbook).getSheetAt(0);
				Long factId0 = factId, lawId0 = lawId;
				
				//importLaws
				int col = 1;
				HSSFRow row0 = sheet.getRow(0);
				if (row0 == null) {
					lawMapper.insert(new Law(lawId++, "无", "无", documentId));
				} else {
					while (row0.getCell(col) != null) {
						String[] law = row0.getCell(col).toString().split(":", 2);
						String lawName = law[0].trim();
						String lawContent = law[1].trim();
						lawMapper.insert(new Law(lawId++, lawName, lawContent, documentId));
						col++;
					}
				}
				
				//importFacts
				int row = 1;
				HSSFRow factContentRow;
				while ((factContentRow = sheet.getRow(row)) != null) {
					String factContent = factContentRow.getCell(0).toString().trim();
					factMarkingStatus = "N";
					factMapper.insert(new Fact(factId++, factContent, documentId, factMarkingStatus));
					row++;
				}
				
				//initiateMarks
				for (Long row_factId = factId0; row_factId < factId; row_factId++) { // row fact
					for (Long col_lawId = lawId0; col_lawId < lawId; col_lawId++) { // col law
//					System.out.println("markId=" + markId + ", factId=" + row + ", lawId=" + col
//							+ ", documentId=" + documentId);
						value = -1;
						markMapper.insert(new Mark(markId++, value, row_factId, col_lawId, documentId));
					}
				}
			}
		}
	}
}
