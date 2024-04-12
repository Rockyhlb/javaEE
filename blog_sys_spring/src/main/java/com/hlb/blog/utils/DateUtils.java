package com.hlb.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog
 * @CreateTime : 2024/4/12 11:11
 * @Description: 日期工具类
 * @Author: code_hlb
 */
public class DateUtils {
    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
