package cn.lsr.user.mapper.permission;

import java.util.List;

import cn.lsr.core.config.mapper.MyMapper;
import cn.lsr.user.entity.permission.Permission;
import cn.lsr.user.entity.permission.PermissionExample;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper extends MyMapper<Permission> {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long pid);
    @Override
    int insert(Permission record);
    @Override
    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long pid);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);
    @Override
    int updateByPrimaryKeySelective(Permission record);
    @Override
    int updateByPrimaryKey(Permission record);
}