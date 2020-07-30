package cn.thg.community.util;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.util.CommunityUtils
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: tanghg
 * @date: 2020/7/27 0027 下午 4:57
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/7/27 0027      tanghg          v1.0.0               修改原因
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-27 16:57
 **/
public class CommunityUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
