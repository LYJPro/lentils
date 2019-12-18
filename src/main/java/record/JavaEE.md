## Servlet
Servlet是Java提供的接口，其实也就是Server Applet的对象。用于Java编写的服务端程序。
接口包含init(), service(), destory()方法。
1. Tomcat启动时读取Web.xml文件
2. 找到Servlet配置的Servlet并调用其init()方法
3. 当有请求时，Servlet容器创建ServletRequest对象和ServletResponse对象，
根据路由传给响应的Servlet的Service方法。

destory方法是在Servlet容器关闭时调用的。
1. 整个生命周期中，创建实例，调用init()，destory()都调用一次。
2. init()出现异常，就不会执行service()
3. init()方法出现异常，Web服务器就不会接受请求
4. 当需要释放内存或者容器关闭时，容器会调用Destory()方法，随后实例会被GC回收，如果再次需要则会创建一个新的实例。