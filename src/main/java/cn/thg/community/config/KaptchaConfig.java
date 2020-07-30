package cn.thg.community.config;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.config.KaptchaConfig
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: tanghg
 * @date: 2020/7/29 0029 下午 2:47
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/7/29 0029      tanghg          v1.0.0               修改原因
 */

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-29 14:47
 **/
@Configuration
public class KaptchaConfig {
    @Bean
    public Producer kaptchaProducer(){
        Properties properties=new Properties();
        properties.setProperty("kaptcha.image.width", "100");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        Config config=new Config(properties);
        DefaultKaptcha producer=new DefaultKaptcha();
        producer.setConfig(config);
        return producer;
    }
}
