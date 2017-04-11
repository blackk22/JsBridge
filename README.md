# JsBridge
此demo，集成腾讯浏览器服务x5内核

简单封装js与android交互.
1.js调用android中sendmessage方法传入的数据格式如下

{
    "action": "call",//js和app约定的命令字段
    "callback": "nativeCallback",//app回调js的方法名称
    "data": {//传给app的数据
        "number": "10010"
    }
}

2.app回调给js，调用 nativeCallback 方法。
