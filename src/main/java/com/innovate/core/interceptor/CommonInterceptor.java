package com.innovate.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.innovate.util.SessionUtils;

/**
 * @desc:菜单访问权限过滤
 * @time: 2017年6月19日 下午7:14:37
 * @author IvanHsu
 */
public class CommonInterceptor extends HandlerInterceptorAdapter 
{
	private PathMatcher pathMatcher = new AntPathMatcher();
	// 排除不过滤的菜单链接列表
	private List<String> excludedUrls;
	public void setExcludedUrls(List<String> excludedUrls)
	{
		this.excludedUrls = excludedUrls;
	}

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String uri = "";
		if (StringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
			String path = requestUri.substring(contextPath.length());
			uri = !StringUtils.isEmpty(path) ? path : "/";
		}
		for (String url : this.excludedUrls) {
			if (pathsMatch(url, uri)) {
				return true;
			}
		}
		String userId = SessionUtils.getCurrentUserId();
		if (StringUtils.isEmpty(userId)) {
			response.sendRedirect(request.getContextPath() + "/rest/login");
			return false;
		}
		return true;
	}

	protected boolean pathsMatch(String pattern, String path)
	{
		return this.pathMatcher.match(pattern, path);
	}

}
