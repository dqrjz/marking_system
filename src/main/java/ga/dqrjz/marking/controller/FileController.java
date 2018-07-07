package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestMapping("api/file")
@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private FileService fileService;
	
	@PostMapping("upload")
	@ResponseBody
	public ResultFiles upload(@RequestParam("files") MultipartFile[] files) {
		ResultFiles resultFiles = new ResultFiles();
		List<ResultFile> resultFileList = new ArrayList<>();
		// 文件上传后的路径
		String filePath = "/Users/JZ/Downloads/test/";
		String fileName, suffixName;
		File destFile;
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				logger.info("文件为空");
			}
			// 获取文件名
			fileName = file.getOriginalFilename();
			logger.info("上传的文件名为：" + fileName);
			// 后缀名必须为.xls
			suffixName = fileName.substring(fileName.lastIndexOf("."));
			if (Objects.equals(suffixName, ".xls")) {
				// 解决中文问题，liunx下中文路径，图片显示问题
				// fileName = UUID.randomUUID() + suffixName;
				destFile = new File(filePath + fileName);
				// 检测是否存在目录
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				try {
					file.transferTo(destFile);
					fileService.importFile(destFile);
					resultFileList.add(new ResultFile(fileName, file.getSize()));
					logger.info("上传成功");
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					resultFileList.add(new ResultFileError(fileName, file.getSize(), "Error!"));
					logger.info("上传失败");
				}
			} else {
				resultFileList.add(new ResultFileError(fileName, file.getSize(), "File type not allowed!"));
			}
		}
		resultFiles.setFiles(resultFileList);
//		System.out.println(resultFiles);
		return resultFiles;
	}
	
	//文件下载相关代码
	@GetMapping("/download")
	@ResponseBody
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String fileName = "444.xml_20180705.xls";
		if (fileName != null) {
			//realPath为服务器文件所在地址，会下载到本机的默认下载的目录
			String realPath = "/Users/JZ/Downloads/test";
			File file = new File(realPath, fileName);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition",
						"attachment;fileName=" + fileName);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					System.out.println("success");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null;
	}
	
	class ResultFile {
		private String name;
		private Long size;
		
		ResultFile() {
		}
		
		ResultFile(String name, Long size) {
			this.name = name;
			this.size = size;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Long getSize() {
			return size;
		}
		
		public void setSize(Long size) {
			this.size = size;
		}
		
		@Override
		public String toString() {
			return "ResultFile{" +
					"name='" + name + '\'' +
					", size=" + size +
					'}';
		}
	}
	
	class ResultFileError extends ResultFile {
		private String error;
		
		public ResultFileError(String error) {
			this.error = error;
		}
		
		ResultFileError(String name, Long size, String error) {
			super(name, size);
			this.error = error;
		}
		
		public String getError() {
			return error;
		}
		
		public void setError(String error) {
			this.error = error;
		}
		
		@Override
		public String toString() {
			return "ResultFileError{" +
					"name='" + super.name + '\'' +
					", size=" + super.size +
					", error='" + error + '\'' +
					'}';
		}
	}
	
	class ResultFiles {
		private List<ResultFile> files;
		
		public List<ResultFile> getFiles() {
			return files;
		}
		
		public void setFiles(List<ResultFile> files) {
			this.files = files;
		}
		
		@Override
		public String toString() {
			return "ResultFiles{" +
					"files=" + files +
					'}';
		}
	}
}
