package ga.dqrjz.marking.pojo;

import ga.dqrjz.marking.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my-props")
public class ConfigProp {
	private String staticPath;
	private String uploadPath;
	@Autowired
	private FileService fileService;
	
	public String getStaticPath() {
		return staticPath;
	}
	
	public void setStaticPath(String staticPath) {
		this.staticPath = staticPath;
	}
	
	public String getUploadPath() {
		return uploadPath;
	}
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
		//启动时加载upload文件夹的已有文书
		fileService.importAll(uploadPath);
	}
}
