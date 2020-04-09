package tw.com.aitc.SBE;//package tw.com.aitc.SBE;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) {
		web
				.ignoring()
				.antMatchers("/webjars/**")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.oauth2Client()  // 配置 OAuth Client 相關設定 ( since Spring Security 5.1 )
		;
	}
}