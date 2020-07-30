package cn.thg.community.util;

public interface CommunityConstant {

    //激活成功
    int ACTIVATION_SUCCESS=0;

    //重复激活
    int ACTIVATOIN_REPEAT=1;

    //激活失败
    int ACTIVATION_DAILURE=2;

    //默认登录过期时长为12个小时
    int DEFAULT_EXPIRED_SECONDS=3600*12;

    //记住密码的登录过期时长为3个月
    int REMEMBER_EXPIRED_SECONDS=3600*24*90;
}
