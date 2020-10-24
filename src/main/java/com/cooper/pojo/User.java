package com.cooper.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @TableId(type = IdType.AUTO)//如果数据库中的key有自动填补的策略，那么可以直接type为IdType.AUTO ，如果不写TableId,那么mybatis-plus会默认自动填补Id,算法为雪花算法
    private Long id;
    private String username;
    private String password;

    //注意驼峰命名与数据库对应的命名
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)//updateTime字段插入和更新自动填充 相比较而言createTime只需要插入的时候自动更新就可以了
    private Date updateTime;

    @Version//乐观锁注解
    private int version;

    @TableLogic //逻辑删除
    private int deleted;
}
