package mybatis;

import java.util.List;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import tk.mybatis.simple.mapper.RoleMapper;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

public class RoleMapperTest extends BaseMapperTest{
  
  @Test
  public void selectAllRoleAndPrivileges(){
	  SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		        List<SysRole> listr= roleMapper.selectAllRoleAndPrivileges();
		        for(SysRole r:listr){
		        System.out.println(r);
		        } 
	}finally{
		sqlSession.close();
	}
  }
  
  @Test
  public void testSelectRoleByUserIdChoose(){
	  SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		        SysRole role= roleMapper.selectRoleById(2L);
		        //System.out.println(role);
		        role.setEnabled(0);
		        roleMapper.updateById(role);
		        role= roleMapper.selectRoleById(2L);
		       // System.out.println(role);
		        List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
		        for(SysRole r:roleList){
		        System.out.println("角色名："+ r.getRoleName());
		        if(r.getId().equals(1L)){
		        	Assert.assertNotNull(r.getPrivilegeList());
		        }else if(r.getId().equals(2L)){
		        	Assert.assertNull(r.getPrivilegeList());
		        	continue;
		        }
		        for(SysPrivilege privilege: r.getPrivilegeList()){
		        	System.out.println("权限名："+ privilege.getPrivilegeName());
		          }
		        } 
	        }finally{
		sqlSession.close();
	}
  }
}
