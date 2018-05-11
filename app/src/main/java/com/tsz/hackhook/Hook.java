package com.tsz.hackhook;

import android.app.PendingIntent;
import android.text.SpannableStringBuilder;

import java.lang.reflect.Field;
import java.security.PrivateKey;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    private static final String TARGET_PACKAGE = "com.a360.zhangzhenguo.hack";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // TODO Auto-generated method stub
        //XposedBridge.log("--------loaded app:"+lpparam.packageName);
//      if(!lpparam.packageName.equals("com.android.mms"))
//          return;

        if (!TARGET_PACKAGE.equals(lpparam.packageName)) {
            //XposedBridge.log("SendRawSMSMod: ignoring package: " +
            //lpparam.packageName);
            return;
        }

//      /**
//       * 拦截SmsMessage的内部类PduParser的getUserData方法，
//       * byte[] getUserData(){}
//       * 该方法不带参数
//       */
//      final Class<?> recvClazz=XposedHelpers.findClass("com.android.internal.telephony.gsm"
//      +".SmsMessage$PduParser",lpparam.classLoader);
//
//      XposedBridge.log("==========开始进入拦截----");
//
//      XposedHelpers.findAndHookMethod(recvClazz, "getUserData",
//              new XC_MethodHook(){
//
//          @Override
//          protected void afterHookedMethod(MethodHookParam param)
//                  throws Throwable {
//              // TODO Auto-generated method stub
//              //super.beforeHookedMethod(param);
//
//              XposedBridge.log("=========getUserData被调用");
//              byte[] recvByteSms=(byte[]) param.getResult();
//              String strRecvSms="";
//              strRecvSms+=new String(recvByteSms);
//
//              //byte[] srtbyte = strRecvSms.getBytes();
//              //String lsx="6666666666666666666666666666666666";
//              param.setResult(strRecvSms.getBytes());
//              //SmsMessage msg=new SmsMessage();
//
//              XposedBridge.log("========接收的短信内容为："+strRecvSms);
//              return;
//          }
//
//
//      });





        //XposedBridge.log("-------开始拦截");
//      findAndHookMethod("com.android.internal.telephony.gsm.SmsMessage",lpparam.classLoader,
//              "getSubmitPdu",String.class,
//              String.class, String.class, boolean.class, byte[].class,
//              int.class, int.class, int.class, new XC_MethodHook(){
//
//          /**
//           * 拦截SmsMessage的getSubmitPdu方法，其有5个参数
//           * String scAddress,
//           * String destinationAddress,
//           * String message,
//           * boolean statusReportRequested,
//           * byte[] header
//           *
//           */
//
//          /**
//           * Get an SMS-SUBMIT PDU for a destination address and a message
//           *
//           * @param scAddress Service Centre address.  Null means use default.
//           * @return a <code>SubmitPdu</code> containing the encoded SC
//           *         address, if applicable, and the encoded message.
//           *         Returns null on encode error.
//           */
//          @Override
//          protected void beforeHookedMethod(MethodHookParam param)
//                  throws Throwable {
//              // TODO Auto-generated method stub
//          //  super.beforeHookedMethod(param);
//              XposedBridge.log("getSubmitPdu被调用");
//              if(param.args[2]==null){
//                  return;
//              }
//              String message=(String) param.args[2];
//              XposedBridge.log("======before：SMS message："+message);
//              SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//              message+=df.format(new Date());
//              XposedBridge.log("========after   SMS message："+message);
//              SubmitPdu rawPdu=new SubmitPdu();
//              //StringTokenizer stringTokenizer=new StringTokenizer(string, delimiters, returnDelimiters)
//              param.setResult(rawPdu);
//              XposedBridge.log("=============hook替换成功");
//
//              return;
//
//          }
//      });


//      final Class<?> recvClazz=XposedHelpers.findClass("com.android.internal.telephony.gsm"
//              +".SmsMessage$PduParser",lpparam.classLoader);
/*
        XposedBridge.log("=========开始进入拦截");
        XposedHelpers.findAndHookMethod("com.android.internal.telephony.gsm"+".SmsMessage$PduParser",
                lpparam.classLoader,"getUserDataUCS2",int.class,
                new XC_MethodHook(){
                    /**
                     * Interprets the user data payload as UCS2 characters, and
                     * decodes them into a String.
                     *
                     * @param byteCount the number of bytes in the user data payload
                     * @return a String with the decoded characters
                     */
                    /**
                     * 拦截SmsMessage的内部类PduParser的getUserDataUCS2方法，该方法返回类型为String
                     * String getUserDataUCS2(int byteCount)
                     *
                     */
                    /*@Override
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        // TODO Auto-generated method stub
                        //  super.afterHookedMethod(param);
                        try {
                            String strMms=(String) param.getResult();
                            XposedBridge.log("=========before:"+strMms);
                            //String after="666666666666666";
                            char[] recvArray=strMms.toCharArray();
                            for(int i=0;i<recvArray.length;i++){
                                recvArray[i]=(char) (recvArray[i]^20000);
                            }
                            String enCodeSms=new String(recvArray);
                            param.setResult(enCodeSms);

                            XposedBridge.log("=========after:"+param.getResult());

                            //return;
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                            XposedBridge.log(e);
                        }

                    }
                });*/
        XposedBridge.log("----------拦截exec------------");
        final Class<?> clazs=XposedHelpers.findClass(
                "java.lang.Runtime", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(clazs, "exec", String.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)
                            throws Throwable {
                        // TODO Auto-generated method stub
                        //super.beforeHookedMethod(param);
                        XposedBridge.log("----开始拦截exec方法-------");
                        //Field f=XposedHelpers.findField(clazz, "mText");
                        //SpannableStringBuilder text=(SpannableStringBuilder) f.get(param.thisObject);
                        String origMsg=(String) param.args[0];
                        XposedBridge.log("command is ："+origMsg+"\n");
                        XposedBridge.log("------成功拦截exec方法------");
                    }
                });
        XposedBridge.log("----------拦截sendmessage------------");
        final Class<?> clazz=XposedHelpers.findClass(
                "android.telephony.SmsManager", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(clazz, "sendTextMessage", String.class,
                String.class, String.class, PendingIntent.class,PendingIntent.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)
                            throws Throwable {
                        // TODO Auto-generated method stub
                        //super.beforeHookedMethod(param);
                        XposedBridge.log("----开始拦截send方法-------");
                        //Field f=XposedHelpers.findField(clazz, "mText");
                        //SpannableStringBuilder text=(SpannableStringBuilder) f.get(param.thisObject);
                        String origMsg=(String) param.args[2];

                        //简单加密运算
                        char array[]=origMsg.toCharArray();//获取字符数组
                        for(int i=0;i<array.length;i++){
                            array[i]=(char) (array[i]^20000);//对每个数组元素进行异或运算
                        }
                        String secretMsg=new String(array);
                        XposedBridge.log("原始短信内容："+origMsg+"\n"+"加密后内容："+secretMsg);
                        XposedBridge.log("------成功拦截send方法并进行加密------");
                    }
                });
        XposedBridge.log("=========开始进入拦截");
        XposedHelpers.findAndHookMethod("com.a360.zhangzhenguo.hack.RSACryptography",
                lpparam.classLoader,"decrypt", byte[].class, PrivateKey.class,
                new XC_MethodHook(){
                    /**
                     * Interprets the user data payload as UCS2 characters, and
                     * decodes them into a String.
                     *
                     * @param byteCount the number of bytes in the user data payload
                     * @return a String with the decoded characters
                     */
                    /**
                     * 拦截SmsMessage的内部类PduParser的getUserDataUCS2方法，该方法返回类型为String
                     * String getUserDataUCS2(int byteCount)
                     *
                     */
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("处理解密方法前");
                        XposedBridge.log("处理解密方法前");
                        XposedBridge.log("beforeHookedMethod args0:" + param.args[0]);
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        // TODO Auto-generated method stub
                        //  super.afterHookedMethod(param);
                        XposedBridge.log("处理setText方法后");
                        XposedBridge.log("afterHookedMethod args1:" + param.args[1]);
                        XposedBridge.log("afterHookedMethod result:" + new String( (byte[])param.getResult()));
                        //return param.getResult();
                    }
                });
    }
}
