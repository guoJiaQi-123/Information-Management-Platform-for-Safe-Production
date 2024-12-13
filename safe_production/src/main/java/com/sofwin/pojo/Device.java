package com.sofwin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofwin.service.DeviceValueService;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @packageName: com.sofwin.pojo
 * @user: mitu
 * @date: 2024/11/27 10:58
 * @email  
 * @description: TODO
 */
@Data
@TableName("tb_device")
public class Device {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private String name;
    @TableField("dev_port")
    private Integer devPort;
    @TableField
    private String lng;
    @TableField
    private String lat;
    @TableField
    private Integer flag;
    @TableField("max_warn")
    private String maxWarn;
    @TableField("min_warn")
    private String minWarn;
    @TableField("create_time")
    private Date createTime;

    public void startReadData(){
        synchronized(this){
            if(this.flag==1){
                new Thread(()->{
                    synchronized (Device.this){
                        while(this.flag==1){
                            DeviceValue value = new DeviceValue();
                            value.setDevId(this.id);

                            value.setNum(String.valueOf(1 + (new Random().nextFloat() * (100 - 1))));
                            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            value.setCreateTime(dateTimeFormat.format(new Date()));
                            DeviceValueService bean = ApplicationContextHolder.getApplicationContext().getBean(DeviceValueService.class);
                            bean.save(value);
                        }

                    }

                }).start();
            }else{
                System.out.println("关闭不执行任何操作");
            }
        }
    }
}
