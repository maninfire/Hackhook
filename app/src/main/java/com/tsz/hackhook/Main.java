package com.tsz.hackhook;

import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.PrivateKey;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Main implements IXposedHookLoadPackage {
    private void hook_method(String className,ClassLoader classLoader,String methodName,Object... parameterTypesAndCallback){
        try{
            XposedHelpers.findAndHookMethod(className,classLoader,methodName,parameterTypesAndCallback);
        }catch (Exception e){
            XposedBridge.log(e);
        }
    }

    private void hook_methods(String className,String methodName,XC_MethodHook xmh){
        try{

            Class<?> clazz=Class.forName(className);
            for(Method method:clazz.getDeclaredMethods()){
                if(method.getName().equals(method.getModifiers())
                        &&!Modifier.isAbstract(method.getModifiers())
                        &&Modifier.isPublic(method.getModifiers())){
                    XposedBridge.hookMethod(method,xmh);
                }
            }
        }catch (Exception e){
            XposedBridge.log(e);
        }
    }


    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam){

        XposedBridge.log("handleLoadPackage执行了");
        XposedBridge.log("loadPackageParam.packageName");
        if(loadPackageParam.packageName.equals("com.a360.zhangzhenguo.hack")){
            XposedBridge.log("开始hook测试程序！");
            XposedHelpers.findAndHookMethod("com.a360.zhangzhenguo.hack.RSACryptography",loadPackageParam.classLoader,"decrypt", byte[].class, PrivateKey.class,
                    new XC_MethodHook(){
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            XposedBridge.log("处理解密方法前");
                            XposedBridge.log("处理解密方法前");
                            XposedBridge.log("afterHookedMethod result:" + param.args[0]);
                        }
                        //@Override
                        protected void afterHookMethod(MethodHookParam param){
                            XposedBridge.log("处理setText方法后");
                            XposedBridge.log("afterHookedMethod result:" + param.args[0]);
                            XposedBridge.log("afterHookedMethod result:" + param.getResult());
                            //return param.getResult();
                        }
                    }
            );
        }

        Log.i("jw","pkg:"+loadPackageParam.packageName);
        /*
        hook_method("android.telphony.TelephyManager", loadPackageParam.classLoader, "getDeviceId", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.i("jw","hook getDeviceId...");
                Object obj = param.getResult();
                Log.i("jw","imei args:"+obj);
                param.setResult("jiangwei");
            }
        });*/
    }


}
