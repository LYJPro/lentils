`1. mkdir /opt/tomcat`  
`2. 上传Tomcat安装包，apache-tomcat-9.0.4.tar.gz`  
`3. tar -zxvf /tmp/apache-tomcat-9.0.4.tar.gz -C /opt/tomcat`  

**配置环境变量**  
`4. export CATALINA_HOME=/opt/apache-tomcat-9.0.4`  
`5. source profile`  

**如果遇到Tomcat日期和系统日期不符 修改catalina.sh文件 文件开头添加内容：**  
`export JAVA_OPTS="$JAVA_OPTS -Duser.timezone=Asia/shanghai"`  
然后重启服务器，即可解决此问题。
