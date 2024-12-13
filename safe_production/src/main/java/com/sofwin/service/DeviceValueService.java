package com.sofwin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofwin.pojo.DeviceValue;

/**
 * @packageName: com.sofwin.service
 * @user: mitu
 * @date: 2024/11/27 11:08
 * @email  
 * @description: TODO
 */

public interface DeviceValueService extends IService<DeviceValue> {

    void mockData();

    void getAirData();
}
