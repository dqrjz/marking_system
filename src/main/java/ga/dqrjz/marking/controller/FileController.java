package ga.dqrjz.marking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/file")
@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@PostMapping("upload")
	@ResponseBody
	public ResultFiles upload(@RequestParam("files") MultipartFile[] files) {
		ResultFiles resultFiles = new ResultFiles();
		List<ResultFile> resultFileList = new ArrayList<>();
		// 文件上传后的路径
		String filePath = "/Users/JZ/Downloads/test/";
		String fileName;
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				logger.info("文件为空");
			}
			// 获取文件名
			fileName = file.getOriginalFilename();
			logger.info("上传的文件名为：" + fileName);
			// 解决中文问题，liunx下中文路径，图片显示问题
			// fileName = UUID.randomUUID() + suffixName;
			File dest = new File(filePath + fileName);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			try {
				file.transferTo(dest);
				resultFileList.add(new ResultFile(fileName, file.getSize(), null));
				logger.info("上传成功");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				logger.info("上传失败");
			}
		}
		resultFiles.setFiles(resultFileList);
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
		private String url;
		
		ResultFile() {
		}
		
		ResultFile(String name, Long size, String url) {
			this.name = name;
			this.size = size;
			this.url = url;
		}
		
		String getName() {
			return name;
		}
		
		void setName(String name) {
			this.name = name;
		}
		
		Long getSize() {
			return size;
		}
		
		void setSize(Long size) {
			this.size = size;
		}
		
		String getUrl() {
			return url;
		}
		
		void setUrl(String url) {
			this.url = url;
		}
	}
	
	class ResultFiles {
		private List<ResultFile> files;
		
		List<ResultFile> getFiles() {
			return files;
		}
		
		void setFiles(List<ResultFile> files) {
			this.files = files;
		}
	}
}
