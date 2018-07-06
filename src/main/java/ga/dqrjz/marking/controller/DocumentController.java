package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.pojo.Document;
import ga.dqrjz.marking.pojo.DocumentVO;
import ga.dqrjz.marking.pojo.ResultInfo;
import ga.dqrjz.marking.pojo.User;
import ga.dqrjz.marking.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RequestMapping("api/document")
@RestController
public class DocumentController {
	@Autowired
	private DocumentService documentService;
	
	/**
	 * 按id查询文书
	 *
	 * @return 按id查询出的document
	 */
	@GetMapping(value = "oneDocument")
	public ResponseEntity<Document> selectDocument(Document document, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		document.setUser(user);
		Document selectedDocument = documentService.selectDocument(document);
		if (selectedDocument == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(selectedDocument);
	}
	
	/**
	 * 用户选择文书
	 *
	 * @return 符合筛选要求的文书
	 */
	@GetMapping(value = "documents")
	public ResponseEntity<List<Document>> selectDocuments(Document document, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		document.setUser(user);
		List<Document> selectedDocumentList = documentService.selectDocuments(document);
		if (selectedDocumentList == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(selectedDocumentList);
	}
	
	/**
	 * 按did和userId更新文书(将文书分配给用户)
	 * 需管理员权限
	 *
	 * @param document document
	 * @return ResultInfo
	 */
	@PatchMapping(value = "oneDocument")
	public ResultInfo updateDocument(Document document, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		if (!Objects.equals(user.getPrivilege(), "admin")) {
			return new ResultInfo(false, null, "非管理员权限");
		}
		documentService.updateDocument(document);
		return new ResultInfo(true);
	}
	
	@PatchMapping(value = "documents")
	public ResultInfo updateDocuments(DocumentVO documentVO) {
		System.out.println(documentVO);
		documentService.updateDocuments(documentVO.getDocumentList());
		return new ResultInfo(true);
	}
	
}
