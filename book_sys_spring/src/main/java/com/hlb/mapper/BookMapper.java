package com.hlb.mapper;

import com.hlb.model.BookInfo;
import com.hlb.model.PageRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.mapper
 * @CreateTime : 2024/3/26 15:18
 * @Description: 数据访问层：也称为持久层，负责数据的访问操作，包括数据的增、删、查、改
 * @Author: code_hlb
 */
@Mapper
public interface BookMapper {
    @Insert("insert into book_info (book_name,author,count,price,publish,status) " + "values (#{bookName},#{author},#{count},#{price},#{publish},#{status})")
    Integer insertBook(BookInfo bookInfo);

    @Select("select count(1) from book_info where status <> 0")
    Integer countBooks();

    // 通过排序后找最后一位ID
    @Select("select id from book_info where status <> 0 order by id desc limit 1")
    Integer selectLastId();

    @Select("select * from book_info where `status` <> 0 order by `id` asc limit #{offset},#{pageSize}")
    List<BookInfo> queryBooksByPage(PageRequest pageRequest);

    @Select("select * from book_info where id = #{bookId} and status <> 0")
    BookInfo queryBookById(Integer bookId);

    // 由于更新逻辑比较复杂，需要使用动态SQL,因此采用xml的方式来实现更新操作
    Integer updateBook(BookInfo bookInfo);

    // 批量删除(逻辑删除，由于id的不确定性，因此也采用动态SQL)
    Integer batchDelete(List<Integer> ids);
}
