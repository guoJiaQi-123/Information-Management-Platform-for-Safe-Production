package com.sofwin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofwin.mapper.DeviceMapper;
import com.sofwin.mapper.DeviceValueMapper;
import com.sofwin.pojo.Device;
import com.sofwin.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @packageName: com.sofwin.service.impl
 * @user: mitu
 * @date: 2024/11/27 11:08
 * @email  
 * @description: TODO
 */
@Service
public class DeviceServiceImpl  implements DeviceService {
    @Autowired
    DeviceMapper deviceMapper;


    @Override
    public List<Device> selectDeviceAll() {
        List<Device> devices = deviceMapper.selectList(null);
        return devices;
    }
}
