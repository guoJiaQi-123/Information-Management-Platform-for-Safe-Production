package com.sofwin.controller;

import com.sofwin.mapper.DeviceValueMapper;
import com.sofwin.pojo.Device;
import com.sofwin.service.DeviceService;
import com.sofwin.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @packageName: com.sofwin
 * @user: mitu
 * @date: 2024/11/27 11:11
 * @email
 * @description: TODO
 */
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceValueMapper valueMapper;


    @GetMapping("/indexData")
    public AjaxResult indexData() {
        // 获取所有设备列表信息
        List<Device> devices = valueMapper.seletAllDevices();
        // 在线设备
        List<Device> online = valueMapper.selectOnlineDevices();
        // 报警设备
        List<Device> alarmDevices = valueMapper.selectOfflineDevices();
        Map map = new HashMap();
        map.put("total", devices.size());
        map.put("online", online.size());
        map.put("offline", (devices.size() - online.size()));
        map.put("alarmDevices", alarmDevices.size());
        return AjaxResult.success(map);
    }


    @GetMapping("/list")
    public List<Device> search() {
        List<Device> devices = deviceService.selectDeviceAll();
        return devices;
    }


}
