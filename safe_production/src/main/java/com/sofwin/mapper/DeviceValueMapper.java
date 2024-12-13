package com.sofwin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sofwin.pojo.Device;
import com.sofwin.pojo.DeviceValue;

import java.util.List;
import java.util.Map;

/**
 * @packageName: com.sofwin.mapper
 * @user: mitu
 * @date: 2024/11/27 11:07
 * @email  
 * @description: TODO
 */

public interface DeviceValueMapper extends BaseMapper<DeviceValue> {

    List<Map> selectValues();

    /**
     * 获取真实读取的日期
     * @return
     */
    List<String> selectCgqDate();
    // 获取真实的数据
    List<Float> selectCgqValue();
    // 获取最新的
    Float devNewValue();

    List<Device> seletAllDevices();

    List<Device> selectOnlineDevices();

    List<Device> selectOfflineDevices();

    Device selectNew();

    void updateDevices(Device device);



    long countDevices();

    List<Device> selectDevicesWithPagination(int offset, int size);
}
