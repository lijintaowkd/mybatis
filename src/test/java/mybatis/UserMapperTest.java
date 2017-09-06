package mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class UserMapperTest extends BaseMapperTest {
	@SuppressWarnings("deprecation")
	@Test
	public void testSelsctById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectById(1L);
			Assert.assertNotNull(user);
			Assert.assertEquals("admin", user.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = userMapper.selectAll();
			Assert.assertNotNull(userList);
			Assert.assertTrue(userList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSelectRolesByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			int result = userMapper.insert(user);
			Assert.assertEquals(1, result);
			Assert.assertNull(user.getId());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	@Test
	public void testInsert2(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			int result = userMapper.insert2(user);
			Assert.assertEquals(1, result);
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	@Test
	public void testUpdateById(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("admin", user.getUserName());
			user.setUserName("admin_test");
			user.setUserEmail("test@mybatis.tk");
		    int result = userMapper.updateById(user);
			Assert.assertEquals(1, result);
			user = userMapper.selectById(1L);
			Assert.assertEquals("admin_test", user.getUserName());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	@Test
	public void testDeleteById(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user1 = userMapper.selectById(1L);
			Assert.assertNotNull(user1);
			Assert.assertEquals(1, userMapper.deleteById(1L));
			Assert.assertNull(userMapper.selectById(1L));
			SysUser user2 = userMapper.selectById(1001L);
			Assert.assertNotNull(user2);
			Assert.assertEquals(1, userMapper.deleteById(user2));
			Assert.assertNull(userMapper.selectById(1001L));
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	@Test
	public void testSelectRolesByUserIdAndRoleEnabled(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
			Assert.assertNotNull(userList);
			Assert.assertTrue(userList.size()>0);
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	@Test
	public void testSelectByUser(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	         SysUser query = new SysUser();
	         query.setUserName("ad");
	         List<SysUser> userList = userMapper.selectByUser(query);
	         Assert.assertTrue(userList.size()>0);
	         
	         query = new SysUser();
	         query.setUserEmail("test@mybatis.tk");
	         userList = userMapper.selectByUser(query);
	         Assert.assertTrue(userList.size()>0);
	       
	         query = new SysUser();
	         query.setUserName("ad");
	         query.setUserEmail("test@mybatis.tk");
	         userList = userMapper.selectByUser(query);
	         Assert.assertTrue(userList.size()==0);
	  }finally{
		  sqlSession.close();
	  }
		
	}
	
	@Test
	public void testUpdateByIdSelective(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	        SysUser user = new SysUser();
	        user.setId(1L);
	        user.setUserEmail("test@mybatis.tk");
	        int result = userMapper.updateByIdSelective(user);
	        Assert.assertEquals(1, result);
	        user = userMapper.selectById(1L);
	        Assert.assertEquals("admin", user.getUserName());
	        Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
	}finally{
	  sqlSession.rollback();
	  sqlSession.close();
	}
	}
	
	@Test
	public void testInsert2Selective(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test-selective");
			user.setUserPassword("123456");
			user.setUserInfo("test info");
			user.setCreateTime(new Date());
			userMapper.insert2(user);
			user = userMapper.selectById(user.getId());
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	@Test
	public void testSelectByIdOrUserName(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	        SysUser query = new SysUser();
	        query.setId(1L);
	        query.setUserName("admin");
	        SysUser user=userMapper.selectByIdOrUserName(query);
	        Assert.assertNotNull(user);
	        query.setId(null);
	        user = userMapper.selectByIdOrUserName(query);
	        Assert.assertNotNull(user);
	        query.setUserName(null);
	        user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
		}finally{
	  sqlSession.close();
	}
	}
	
	@Test
	public void testSelectByIdList(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	        List<Long> idList = new ArrayList<Long>();
	        idList.add(1L);
	        idList.add(1001L);
	        List<SysUser> userList = userMapper.selectByIdList(idList);
	        Assert.assertEquals(2, userList.size());
		}finally{
			  sqlSession.close();
			}
	}
	
	@Test
	public void testInsertList(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	        List<SysUser> userList = new ArrayList<SysUser>();
	        for(int i=0; i<2 ; i++){
	        	SysUser user = new SysUser();
	        	user.setUserName("test"+i);
	        	user.setUserPassword("123456");
	        	user.setUserEmail("test@mybatis.tk");
	        	userList.add(user);
	        }
	        int result = userMapper.insertList(userList);
	        Assert.assertEquals(2, result);
	        for(SysUser user:userList){
	        	System.out.println(user.getId());
	        }
		}finally{
			  sqlSession.rollback();
			  sqlSession.close();
			}
	}
	
	@Test
	public void testUpdateByMap(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",1L);
            map.put("user_email", "test@mybatis.tk");
            userMapper.updateByMap(map);
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		}finally{
			  sqlSession.rollback();
			  sqlSession.close();
			}
	}
	
	@Test
	public void testSelectUserAndRoleByIdSelect(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
            Assert.assertNotNull(user);
            System.out.println("调用user.getRole()");
            Assert.assertNotNull(user.getRole());
	}finally{
		  sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAllUserAndRoles(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数：" + userList.size());
            for(SysUser user:userList){
            	System.out.println("用户名："+ user.getUserName());
            	for(SysRole role:user.getRoleList()){
            		System.out.println("角色名："+ role.getRoleName());
            		for(SysPrivilege privilege : role.getPrivilegeList()){
            			System.out.println("权限名：" + privilege.getPrivilegeName());
            			
            		}
            	}
            }
	}finally{
		  sqlSession.close();
		}
	}
	
	@Test
	public void testselectAllUserAndRolesSelect(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectAllUserAndRolesSelect(1L);
            System.out.println("用户名：" + user.getUserName());
             	for(SysRole role:user.getRoleList()){
            		System.out.println("角色名："+ role.getRoleName());
            		for(SysPrivilege privilege : role.getPrivilegeList()){
            			System.out.println("权限名：" + privilege.getPrivilegeName());
            			
            		}
                 }
	}finally{
		  sqlSession.close();
		}
	}
	
	@Test
	public void testSelectUserById(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
            user.setId(1L);
            userMapper.selectUserById(user);
			Assert.assertNotNull(user.getUserName());
			System.out.println("用户名："+ user.getUserName());
	}finally{
		  sqlSession.close();
		}
	}
	
	@Test
	public void testSelectUserPage(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userName", "ad");
			params.put("offset", 0);
			params.put("limit", 10);
			List<SysUser> userList = userMapper.selectUserPage(params);
			Long total = (Long) params.get("total");
            System.out.println("总数："+ total);
            for(SysUser user: userList){
			System.out.println("用户名："+ user.getUserName());
            }
	}finally{
		  sqlSession.close();
		}
	}
	@Test
	public void testInsertAndDelete(){
		SqlSession sqlSession = getSqlSession();
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1,2,3});
            userMapper.insertUserAndRoles(user, "1,2");
            Assert.assertNotNull(user.getId());
			Assert.assertNotNull(user.getCreateTime());
			sqlSession.commit();
			userMapper.deleteUserById(user.getId());
           
	}finally{
		  sqlSession.close();
		}
	}
	
}
