package com.innovate.itms;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class ConcurrentTest {

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		// 模拟10000人并发请求，用户钱包
		CountDownLatch latch = new CountDownLatch(1);
		// 模拟10000个用户
//		for (int i = 0; i < 500; i++) {
			AnalogUser analogUser = new AnalogUser(latch);
//			analogUser.start();
			analogUser.post();
//		}
		// 计数器減一 所有线程释放 并发访问。
//		latch.countDown();
//		System.out.println("所有模拟请求结束  at " + sdf.format(new Date()));
	}
	
	public static void doPostAsyn(String url,String outStr) throws ParseException, IOException, InterruptedException, ExecutionException{
        CloseableHttpAsyncClient httpAsyncClient =  HttpAsyncClients.createDefault();
        httpAsyncClient.start();
        HttpPost httpost = new HttpPost(url);
//        httpost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        StringEntity se=new StringEntity(outStr,"UTF-8");
        se.setContentType("application/json");
        se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
        httpost.setEntity(se);
        Future<HttpResponse> future = httpAsyncClient.execute(httpost,null);
        System.out.println(future.get().toString());
        //String result = EntityUtils.toString(response.getEntity(),"UTF-8");
        //jsonObject = JSONObject.fromObject(result);
    }
	

	static class AnalogUser extends Thread {
		// 模拟用户姓名
		CountDownLatch latch;

		public AnalogUser(CountDownLatch latch) {
			super();
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				latch.await(); // 一直阻塞当前线程，直到计时器的值为0
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			post();// 发送post 请求
		}

		public void post() {
			String result = doPost("http://localhost:8088/studio/sample/lock.do",null);
			System.out.println("操作结果：" + result);
		}
		
		public String doPost(String url, Map<String, String> param) {
			// 创建Httpclient对象
//			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			// 设置请求配置
			RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD)
					.setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(50000). build();
	 
			// 打开浏览器
			CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();

			
			CloseableHttpResponse response = null;
			String resultString = "";
			try {
				// 创建Http Post请求
				HttpPost httpPost = new HttpPost(url);
				// 创建参数列表
				if (param != null) {
					List<NameValuePair> paramList = new ArrayList<NameValuePair>();
					for (String key : param.keySet()) {
						paramList.add(new BasicNameValuePair(key, param.get(key)));
					}
					// 模拟表单
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
					httpPost.setEntity(entity);
				}
				// 执行http请求
				response = httpClient.execute(httpPost);
				resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return resultString;
		}
	}

}
