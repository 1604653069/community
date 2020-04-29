package com.example.community.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

/*支付工具*/
public class ApliyUtils {
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2016101800716276";
    /**
     * 私钥
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1/31Rg/WKQjiVk3rHcvat9i7RjxdqsyarmKyEkt8x4WccwFxD5jKp7iMTmtwloTVYPKLGB2i+vd+EsZc7XqNZSJpqxwwkjMR5XXLH9j6Qud1rlJmw+CPuoIvJCKokaEBuuateq3X4WHP5JbpxoguBtnYNUTVrr4cnCc5Mwi0fnArZamyzoZY6j5impirLZOOqqOt+GG5zIJpKhv5o67H7Ll8gg3JpEUQb1uFiXLaYOJH43tXuvpynQ2AjyEWMhnGDZd3hy3Aw9o1InZe15ip1+ZMCeEd/x+uWcfVrWQti80XbQZDoXxA6CIQAxNQzr1mXb+wojO3IwE+DLctLKP4fAgMBAAECggEBAIy8qDT7/UlQEh+gryZCwX0ezW+h3nLVXMb/RhEy3kPdUPZLzk13WqBLlXZYn3q94dl88kZ+fkJZ49nh1tfDREALw9VkXQB6lohfeh4lTpZuFEpX3E2cZXTnU5E/EQscTw8QpvOLVauZAv2cbkPnEkzZQwrjTJMcZkAK9iwnT+uLWfobemGhJk5RK/VftSfsHfY4hLTA0C2lYhiuah8b3bTGX7V0vadoAAD5BCn9sQdTxQVNxS5za85Q9lRUam5ERJ/XBI2Xx+UhFOSmyYwltIo5cVRGA8yAH2EvtqHGd0mAhuFfZRnvDFD/87tG1rtgCkxQNzJIslMx6FuYobZKPakCgYEA5Jg82TgU5gUNTyqZRzuE7Ubnd3tf+MNDkpdzmwphW8el8VEXcsV3+jgGWn059jgQy5ZqYZPu+wXln5CeL2GNKGYE3ObKSWMJUeGo9qX4AtPQ/wkLak52QYnfaDGQADVHW0HxvGOM+l6PLfal+POLdyrP2Bi4aoGbu8SZ1ux83WsCgYEAy9EpMGODJs77t2AESeuh10V1wh58SF+iWrLi4WtZzcURhs98OmqbBjTQTdG5VB+8YWBaxU6wkF77W5c/Sd2Smevm+aH2NTD1pHuXmLKLMw4U8L/y226iIO6q7j7bP6R2FOjw1MJ3mni2cG/fgwbX4K9SSMFq8g5CA49KtSrE+x0CgYAkOuix+Mx/JF/Wo0bF3m4T+T/oVPqB/9iu3Cgrc3q4xCzN0W9o9lkspEKFhwFvv7URv1Y+/uZKjMW3A4G+urRXqcutgzqeZP7uJljUNrvXHuEp+rH/eE4P1x08c1aCYe9cTFhW5mRKrj/9JfFqQpdhHLzyYACiiMY4/VDwmmyz3wKBgBAEKbZxu3arQUCRvQSKDbe675wHhUCyszFpaUbJhs8Ss2PiniKBChmcwNm8p1GjBMyqI7fpjRprPFlgM0r8rG6ICSiIv65CYKtCQxFuxl2Gt8KLcuwnb3ojBy+dpOEFbXoLLBGsDnYHEl8Xi3riaMhxPogVGvBWin15HyfJap1JAoGAAyJEUHUv7yM0ReYAuCzRPE/dxqztRZzUuX3f1u9tGV3NEh5DI5kNHbDDhG0nh5WWDbzKHRehy1dcZNbykuuyJW31PZhnHj4OJ1Sxlovn2XIe3XV4HCXdn11zOfH2j/GaEmXdbGf2iM1f4mh9qFh0+iwiCySJMSG7/89otuedI2Q=";
    public static final String RSA_PRIVATE = "";
    private static final int SDK_PAY_FLAG = 1;
    /**
     * 支付宝支付业务示例
     */
    public static void payV2(Activity activity, int money, Handler handler) {
      /*  if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(this, getString(R.string.error_missing_appid_rsa_private));
            return;
        }*/
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap2(APPID, rsa2,money);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
