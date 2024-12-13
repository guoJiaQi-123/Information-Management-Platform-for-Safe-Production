package com.sofwin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.facade.ModbusSerialMaster;
import com.ghgande.j2mod.modbus.procimg.Register;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import com.sofwin.mapper.DeviceMapper;
import com.sofwin.mapper.DeviceValueMapper;
import com.sofwin.pojo.Device;
import com.sofwin.pojo.DeviceValue;
import com.sofwin.service.DeviceValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @packageName: com.sofwin.service.impl.impl
 * @user: mitu
 * @date: 2024/11/27 11:10
 * @email  
 * @description: TODO
 */
@Service
public class DeviceValueServiceImpl extends ServiceImpl<DeviceValueMapper, DeviceValue> implements DeviceValueService {
    @Autowired
    private DeviceValueMapper valueMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Override
//    @EventListener(ApplicationReadyEvent.class)
    public void mockData() {
        System.out.println("mock方法开始执行........");
        // 默认所有设备都是未上线
        LambdaUpdateWrapper<Device> updateWrapper = new LambdaUpdateWrapper();
        updateWrapper.in(Device::getFlag,Arrays.asList(0,1));
        updateWrapper.set(Device::getFlag,0);
        deviceMapper.update(updateWrapper);
        // 从未上线中随机出多台设备上线
        Integer unlineNumber = deviceMapper.selectList(null).size();
        Integer planeOnLineNumber = new Random().nextInt(unlineNumber);
        // 所有未上线的设备中找计划上线的上线
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Device::getFlag,0);
        List<Device> devices = deviceMapper.selectList(queryWrapper);
        for(int i=0;i<planeOnLineNumber;i++){
            Device d = devices.get(i);
            d.setFlag(1);
            deviceMapper.updateById(d);
            d.startReadData();
        }
        // 随机出的设备开始传输数据，随机

        // 每隔2分钟在以上线的设备中随机1台下线，并在未上线中随机1台上线
        new Thread(()->{
            while(true) {
                LambdaQueryWrapper<Device> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Device::getFlag, 1);
                List<Device> devices1 = deviceMapper.selectList(queryWrapper1);
                Device readDown = devices1.get(new Random().nextInt(devices1.size()));
                readDown.setFlag(0);
                deviceMapper.updateById(readDown);
                try {
                    Thread.sleep(2000*60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 判断当所有设备都上线，持续10分钟，随机一半及以上下线
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void getAirData() {
        System.out.println("getAirData方法开始执行........");
        // 获取最新的设备id
        Device devices = valueMapper.selectNew();
        //修改该设备flag为1
        devices.setFlag(1);
        valueMapper.updateDevices(devices);
        Integer devId = devices.getId();
        SerialParameters params = new SerialParameters();
        params.setPortName("COM3"); // 设置串口名称，根据实际情况修改
        params.setBaudRate(9600); // 设置波特率
        params.setDatabits(8); // 设置数据位
        params.setParity("None"); // 设置校验位
        params.setStopbits(1); // 设置停止位
        params.setEncoding("rtu"); // 设置编码方式为RTU
        params.setEcho(false); // 设置是否回显
        // 创建ModbusRTU传输对象
        ModbusSerialMaster master = null;
        try {
            master = new ModbusSerialMaster(params);
            master.connect(); // 连接串口
            int unitId = 1; // Modbus设备地址
            int ref = 0; // 寄存器起始地址
            int count = 2; // 读取的寄存器数量
            while (true) {
                // 读取保持寄存器
                Register[] registerValues = master.readMultipleRegisters(unitId, ref, count);
                System.out.println("Register value: " + registerValues[0]+","+registerValues[1]);
                DeviceValue value = new DeviceValue();
                value.setNum(registerValues[0].toString());
                value.setDevId(devId);
                value.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                valueMapper.insert(value);

            }
        } catch (ModbusException e) {
            devices.setFlag(0);
            valueMapper.updateDevices(devices);
            e.printStackTrace();
        } catch (Exception exception) {
            devices.setFlag(0);
            valueMapper.updateDevices(devices);
            exception.printStackTrace();
        } finally {
            if (master != null) {
                devices.setFlag(0);
                valueMapper.updateDevices(devices);
                master.disconnect(); // 断开连接
            }
        }
    }
}
