package cn.lsr.user.mapper.role;

import java.util.List;

import cn.lsr.core.config.mapper.MyMapper;
import cn.lsr.user.entity.role.Role;
import cn.lsr.user.entity.role.RoleExample;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends MyMapper<Role> {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(String rid);
    @Override
    int insert(Role record);
    @Override
    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(String rid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);
    @Override
    int updateByPrimaryKeySelective(Role record);
    @Override
    int updateByPrimaryKey(Role record);
}