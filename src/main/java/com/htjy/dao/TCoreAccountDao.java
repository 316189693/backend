package com.htjy.dao;

import com.htjy.entity.TCoreAccount;
import com.htjy.entity.TCoreAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TCoreAccountDao {
    long countByExample(TCoreAccountExample example);

    int deleteByExample(TCoreAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCoreAccount record);

    int insertSelective(TCoreAccount record);

    List<TCoreAccount> selectByExample(TCoreAccountExample example);

    TCoreAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCoreAccount record, @Param("example") TCoreAccountExample example);

    int updateByExample(@Param("record") TCoreAccount record, @Param("example") TCoreAccountExample example);

    int updateByPrimaryKeySelective(TCoreAccount record);

    int updateByPrimaryKey(TCoreAccount record);
}