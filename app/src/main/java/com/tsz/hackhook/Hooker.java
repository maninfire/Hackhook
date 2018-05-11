package com.tsz.hackhook;

import android.text.SpannableStringBuilder;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Hooker implements IXposedHookLoadPackage {

    private static final String PNAME="com.android.mms";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // TODO Auto-generated method stub
        XposedBridge.log("handleLoadPackage执行了");
        if(!lpparam.packageName.equals(PNAME)){
            //XposedBridge.log("无法找到"+PNAME);
            return ;
        }
        XposedBridge.log("----------目前在包com.android.mms.data中------------");
        final Class<?> clazz= XposedHelpers.findClass(
                "com.android.mms.data.WorkingMessage", lpparam.classLoader);
        findAndHookMethod(clazz, "send", String.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)
                            throws Throwable {
                        // TODO Auto-generated method stub
                        //super.beforeHookedMethod(param);
                        XposedBridge.log("----开始拦截send方法-------");
                        Field f=XposedHelpers.findField(clazz, "mText");
                        SpannableStringBuilder text=(SpannableStringBuilder) f.get(param.thisObject);
                        String origMsg=text.toString();

                        //简单加密运算
                        char array[]=origMsg.toCharArray();//获取字符数组
                        for(int i=0;i<array.length;i++){
                            array[i]=(char) (array[i]^20000);//对每个数组元素进行异或运算
                        }
                        String secretMsg=new String(array);
                        f.set(param.thisObject,"原始短信内容："+origMsg+"\n"+"加密后内容："+secretMsg);
                        XposedBridge.log("------成功拦截send方法并进行加密------");
                    }
                });
    }
    private static final String class_name = "com.android.internal.telephony.gsm.SmsMessage$PduParser";

    public static void GetHookSmsMessage(XC_LoadPackage.LoadPackageParam lpparam) {


        findAndHookMethod(class_name, lpparam.classLoader, "getUserDataUCS2", int.class, new XC_MethodHook() {

            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("beforeHookedMethod");
                // this will be called before the clock was updated by the original method
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                // this will be called after the clock was updated by the original method
                try {
                    String message = param.getResult().toString();
                    XposedBridge.log("the message is : " + message);
                    //新增其他操作
                } catch (Exception e) {
                    XposedBridge.log(e);
                }
            }
        });
    }

}