package test.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import test.model.SysPrivilege;
import test.model.SysPrivilegeExample;

public interface SysPrivilegeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int countByExample(SysPrivilegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int deleteByExample(SysPrivilegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int insert(SysPrivilege record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int insertSelective(SysPrivilege record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    List<SysPrivilege> selectByExample(SysPrivilegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    SysPrivilege selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysPrivilege record, @Param("example") SysPrivilegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysPrivilege record, @Param("example") SysPrivilegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysPrivilege record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysPrivilege record);
}