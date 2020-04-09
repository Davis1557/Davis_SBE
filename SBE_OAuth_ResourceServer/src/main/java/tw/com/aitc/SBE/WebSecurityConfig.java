package tw.com.aitc.SBE;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests() // 配置授權
				.mvcMatchers("/coupon/**")  // 相符的路徑 ( 其他還有 antMatchers, regexMatchers 不同解析路徑的作法 )
//				.hasAuthority("SCOPE_message.read") // 需有指定的授權
				.permitAll()

				.and()
				.csrf().disable()  // 關閉 CSRF 防護


				.oauth2ResourceServer() // 配置 OAuth Resource Server 相關設定 ( since Spring Security 5.1 )
				.jwt() // 配置 OAuth 的 JWT 相關設定
		;
	}
}