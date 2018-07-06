package ga.dqrjz.marking.config;

import ga.dqrjz.marking.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/**", "/*.html")
				.excludePathPatterns(
						"/login",
						"/api/user/login",
						"/login.html",
						"/**/fonts/*",
						"/**/*.css",
						"/**/*.js",
						"/**/*.png",
						"/**/*.gif",
						"/**/*.jpg",
						"/**/*.jpeg",
						"/assets/*",
						"/img/*",
						"/js/*",
						"/css/*",
						"/themes/*");
	}
}
