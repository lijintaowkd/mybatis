package tk.mybatis.simple.mapper;

import java.util.List;

import tk.mybatis.simple.model.SysRole;

public interface RoleMapper {
	
	SysRole selectRoleById(Long id);
	
	List<SysRole> selectAllRoleAndPrivileges();
	
	List<SysRole> selectRoleByUserIdChoose(Long userId);
	
	int updateById(SysRole role);
}
