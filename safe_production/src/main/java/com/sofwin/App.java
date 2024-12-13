package com.sofwin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @packageName: com.sofwin
 * @user: mitu
 * @date: 2024/11/27 9:38
 * @email  
 * @description: TODO
 */
@SpringBootApplication
@MapperScan(basePackages = "com.sofwin.mapper")
@Slf4j
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class,args);
        log.info("\n ________       ___  ___      ________      ________      _______       _______       ________     \n" +
                "|\\   ____\\     |\\  \\|\\  \\    |\\   ____\\    |\\   ____\\    |\\  ___ \\     |\\  ___ \\     |\\   ___ \\    \n" +
                "\\ \\  \\___|_    \\ \\  \\\\\\  \\   \\ \\  \\___|    \\ \\  \\___|    \\ \\   __/|    \\ \\   __/|    \\ \\  \\_|\\ \\   \n" +
                " \\ \\_____  \\    \\ \\  \\\\\\  \\   \\ \\  \\        \\ \\  \\        \\ \\  \\_|/__   \\ \\  \\_|/__   \\ \\  \\ \\\\ \\  \n" +
                "  \\|____|\\  \\    \\ \\  \\\\\\  \\   \\ \\  \\____    \\ \\  \\____    \\ \\  \\_|\\ \\   \\ \\  \\_|\\ \\   \\ \\  \\_\\\\ \\ \n" +
                "    ____\\_\\  \\    \\ \\_______\\   \\ \\_______\\   \\ \\_______\\   \\ \\_______\\   \\ \\_______\\   \\ \\_______\\\n" +
                "   |\\_________\\    \\|_______|    \\|_______|    \\|_______|    \\|_______|    \\|_______|    \\|_______|\n" +
                "   \\|_________|                                                                                    \n" +
                "                                                                                       ");
    }
}