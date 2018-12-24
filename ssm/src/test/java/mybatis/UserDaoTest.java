package mybatis;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wangxuegang.dao.UserDao;
import com.wangxuegang.enitiy.UserEo;
import com.wangxuegang.model.UserVo;
import com.wangxuegang.service.UserService;

/**
 * 
 * @类描述：UserDao 单元测试
 * @项目名称：ssm
 * @包名： mybatis
 * @类名称：UserDao
 * @创建人：wangxuegang
 * @创建时间：2018年12月24日下午2:08:46
 * @mail 15510235102@163.com
 * @version v1.0
 
 */
public class UserDaoTest {
	
	ApplicationContext ac = null;
	
	
	@Before
	public void start(){
		ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	@Test
	public void dateSoureTest(){
		DataSource dataSource = (DataSource) ac.getBean("dataSource");
		System.out.println(dataSource);
	}
	
	@Test
	public void userDaoTest(){
		
		UserDao userDao = (UserDao) ac.getBean("userDao");
		UserEo userEo = new UserEo();
		userEo.setLoginName("wangxuegang");
		userEo.setUserName("王雪刚");
		userEo.setPassword("root");
		userEo.setPhone("13766677799");
		userEo.setEmail("123@163.com");
		userDao.insertUser(userEo);
	}
	
	@Test
	public void userServiceTest(){
		UserService userService = (UserService) ac.getBean("userService");
		UserVo userVo = new UserVo();
		userService.saveUser(userVo);
	}
}
