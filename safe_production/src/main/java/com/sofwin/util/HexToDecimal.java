package com.sofwin.util;

public class HexToDecimal {
    public static void main(String[] args) {  
        String hexString = "0044"; // 示例十六进制字符串
        int decimalValue = Integer.parseInt(hexString, 16); // 将十六进制转换为十进制  
        System.out.println("十六进制数 " + hexString + " 对应的十进制数是: " + decimalValue);
    }  
}