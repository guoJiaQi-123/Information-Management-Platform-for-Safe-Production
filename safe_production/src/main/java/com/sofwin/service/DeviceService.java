package com.sofwin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofwin.pojo.Device;

import java.util.List;

/**
 * @packageName: com.sofwin.service
 * @user: mitu
 * @date: 2024/11/27 11:08
 * @email  
 * @description: TODO
 */

public interface DeviceService  {

    List<Device> selectDeviceAll();
}
