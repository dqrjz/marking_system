package ga.dqrjz.marking.service.impl;

import ga.dqrjz.marking.mapper.DocumentMapper;
import ga.dqrjz.marking.mapper.EvidenceMapper;
import ga.dqrjz.marking.mapper.FactMapper;
import ga.dqrjz.marking.mapper.MarkMapper;
import ga.dqrjz.marking.pojo.Document;
import ga.dqrjz.marking.pojo.Evidence;
import ga.dqrjz.marking.pojo.Fact;
import ga.dqrjz.marking.pojo.Mark;
import ga.dqrjz.marking.service.FileService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service("fileService")
public class FileServiceImpl implements FileService {
	@Autowired
	private DocumentMapper documentMapper;
	//	@Autowired
//	private  LawMapper lawMapper;
	@Autowired
	private EvidenceMapper evidenceMapper;
	@Autowired
	private FactMapper factMapper;
	@Autowired
	private MarkMapper markMapper;
	private Long documentId = 0L, factId = 0L, evidenceId = 0L, markId = 0L;
	private boolean idInitialized;
	
	@Override
	public void importFile(File file) {
		initializeIds();
		String documentAssignmentStatus; // N=unassigned, Y=assigned
		String documentMarkingStatus, factMarkingStatus; // N=unmarked, Y=marked
		Long userId; // -1=unassigned
		String case_name, case_number, case_reason, case_category;
		case_name = case_number = case_reason = case_category = "..."; // default
		
		//importDocuments
		String filenameXls = file.getName();
//		Long documentId = Long.parseLong(filenameXls.substring(0, filenameXls.indexOf("_")));
		String filenameXml = filenameXls.substring(0, filenameXls.lastIndexOf("_"));
		documentAssignmentStatus = documentMarkingStatus = "N";
		userId = -1L;
//		case_name = case_number = case_reason = case_category = "...";
		documentMapper.insert(new Document(documentId, filenameXml, filenameXls, documentAssignmentStatus,
				userId, documentMarkingStatus, case_name, case_number, case_reason, case_category));
		
		//获取表格
		HSSFWorkbook hssfWorkbook = null;
		try {
			hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = Objects.requireNonNull(hssfWorkbook).getSheetAt(0); //获取第一个sheet
		Long factId0 = factId, evidenceId0 = evidenceId;
		
		//importEvidences
		int col = 1;
		HSSFRow row0 = sheet.getRow(0);
		if (row0 == null) {
			evidenceMapper.insert(new Evidence(evidenceId++, "无", documentId));
		} else {
			while (row0.getCell(col) != null) {
				String evidenceContent = row0.getCell(col).toString();
//				String lawName = law[0].trim();
//				String lawContent = law[1].trim();
				evidenceMapper.insert(new Evidence(evidenceId++, evidenceContent, documentId));
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
		HSSFCell markValueCell;
		Integer markValue;
		Integer value; // mark value (-1=unmarked, 0=unrelated, 1=related)
		for (Long row_factId = factId0; row_factId < factId; row_factId++) { // row fact
			for (Long col_evidenceId = evidenceId0; col_evidenceId < evidenceId; col_evidenceId++) { // col evidence
//					System.out.println("markId=" + markId + ", factId=" + row + ", lawId=" + col
//							+ ", documentId=" + documentId);
				int rowIndex = (int) (row_factId - factId0 + 1);
				int colIndex = (int) (col_evidenceId - evidenceId0 + 1);
				markValueCell = sheet.getRow(rowIndex).getCell(colIndex);
				if (markValueCell == null || StringUtils.isEmpty(markValueCell.toString())) {
					value = -1;
				} else {
					markValue = (int) Double.parseDouble(markValueCell.toString());
					if (markValue == 0) {
						value = 0;
					} else if (markValue == 1) {
						value = 1;
					} else {
						value = -1;
					}
				}
				markMapper.insert(new Mark(markId++, value, row_factId, col_evidenceId, documentId));
			}
		}
		documentId++;
	}
	
	private void initializeIds() {
		if (!idInitialized) {
			documentId = (long) documentMapper.selectCount(null);
			factId = (long) factMapper.selectCount(null);
			evidenceId = (long) evidenceMapper.selectCount(null);
			markId = (long) markMapper.selectCount(null);
			idInitialized = true;
		}
	}
}

