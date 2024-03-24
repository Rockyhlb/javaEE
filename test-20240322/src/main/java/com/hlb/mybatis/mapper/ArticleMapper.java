package com.hlb.mybatis.mapper;

import com.hlb.mybatis.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @BelongsProject: test-20240322
 * @BelongsPackage: com.hlb.mybatis.mapper
 * @CreateTime : 2024/3/24 21:03
 * @Description: TODO
 * @Author: code_hlb
 */
@Mapper
public interface ArticleMapper {
    // 多表查询，和单表查询的用法相同
    // 通过uid查询作者信息
    @Select("select a1.*,u1.username,u1.age,u1.gender\n" +
            "    from article_info a1\n" +
            "left join user_info u1 on a1.uid = u1.id\n" +
            "where a1.id=#{id}")
    ArticleInfo queryArticleInfo(Integer id);

}
