package tk.mybatis.simple.model;

import java.util.Date;
import java.util.List;

public class SysRole {

	private Long id;

	private String roleName;

	private Integer enabled;

	private Integer createBy;

	private Date createTime;
	
	private SysUser user;

	private List<SysPrivilege> privilegeList;
	
	@Override
	public String toString() {
		return "SysRole [id=" + id + ", roleName=" + roleName + ", enabled="
				+ enabled + ", createBy=" + createBy + ", createTime="
				+ createTime + ", user=" + user + ", privilegeList="
				+ privilegeList + "]";
	}

	public List<SysPrivilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<SysPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
