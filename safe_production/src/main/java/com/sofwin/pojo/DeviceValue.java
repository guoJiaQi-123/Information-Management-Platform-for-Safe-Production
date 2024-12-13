package com.sofwin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * @packageName: com.sofwin.pojo
 * @user: mitu
 * @date: 2024/11/27 11:05
 * @email  
 * @description: TODO
 */
@Data
@TableName("tb_device_value")
public class DeviceValue {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("dev_id")
    private Integer devId;
    @TableField("num")
    private String num;
    @TableField("create_time")
    private String createTime;
}
