import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.user.domain.vo.response.NoLoginResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HttpUtil {
	
	
	static int CONNECT_TIME_OUT = 1000;  
  	static int SOCKET_TIME_OUT = 2000;  
  	static int CONNECTION_REQUEST_TIME_OUT = 2000;  
	
	public static String sendPost(String url,List params,String hostName,String port){
		try {  
		     //设置代理
		     HttpHost proxy = new HttpHost(hostName,Integer.parseInt(port));
			
		     /** 超时设置 */  
		     RequestConfig requestConfig = RequestConfig.custom()  
				/** 设置代理 */
				.setProxy(proxy)
			    	/** 设置连接超时时间，单位毫秒 */  
			    	.setConnectTimeout(CONNECT_TIME_OUT)  
			    	/** 请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用 */  
			    	.setSocketTimeout(SOCKET_TIME_OUT)  
			    	/** 设置从connect Manager(连接池)获取Connection 超时时间，单位毫秒,这个属性是新加的属性，因为目前版本是可以共享连接池的 */  
			   	.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT)  
			    	.build();  
              
		      /** 创建 httpClient 实例 */  
		      HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
		      /** 连接池个数 */  
		      httpClientBuilder.setMaxConnTotal(30);  
		      /** 路由并发数 */  
		      httpClientBuilder.setMaxConnPerRoute(2);  
		      /** 使用配置的连接池和路由 */  
		      httpClientBuilder.setDefaultRequestConfig(requestConfig);  

		      /** 构建httpclient对象*/  
		      HttpClient httpClient = httpClientBuilder.build();  

		      /** 创建 post()方法 */  
		      HttpPost httpPost = new HttpPost(url);  
		      /** 请求参数 */
		      httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));

		      /** post请求，返回响应 */  
		      HttpResponse httpResponse = httpClient.execute(httpPost);  
		      HttpEntity entity = httpResponse.getEntity();  
		      /** 状态 */  
		      int state = httpResponse.getStatusLine().getStatusCode();  

		      String result = EntityUtils.toString(entity,"UTF-8");

		      /** 打印 */  
		      log.info("响应状态:"+state+" 内容："+result);
		      return result;
            
        	} catch (ClientProtocolException e) {  
            		log.error(e.getStackTrace());  
        	} catch (ConnectTimeoutException e) {  
            		log.error("温馨提示：连接超时");  
        	} catch (SocketTimeoutException e) {  
            		log.error("温馨提示：响应超时");  
        	} catch (IOException e) {  
            		log.error("error:"+e.getMessage());  
        	}
		return null;  
	}
	
	public static void main(String[] args) {
	  List params = new ArrayList();
	  params.add(new BasicNameValuePair("userCode", "123"));
	  params.add(new BasicNameValuePair("password", "123"));
	  String data = HttpUtil.sendPost("http://www.baidu.com", params, "127.0.0.1", "80");
	}
