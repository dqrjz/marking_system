package ga.dqrjz.marking.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my-props")
public class ConfigProp {
	private String staticPath;
	private String uploadPath;
	
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
	}
}
