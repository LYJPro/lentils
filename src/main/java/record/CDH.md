# CDH    
**三台虚拟机**  
> 192.168.0.150 (CentOS6.7)  
> 192.168.0.151 (CentOS6.7)  
> 192.168.0.152 (CentOS6.7)  

**查询Linux  系统版本**  
`head -n 1 /etc/issue`

**下载CDH包**  
地址：[http://archive.cloudera.com/cdh5/parcels/5.10.0/](http://archive.cloudera.com/cdh5/parcels/5.10.0/)  
> CDH-5.10.0-1.cdh5.10.0.p0.41-el6.parcel  
> CDH-5.10.0-1.cdh5.10.0.p0.41-el6.parcel.sha1  
> manifest.json  

**下载Cloudera Manager包**  
地址：[http://archive.cloudera.com/cm5/cm/5/](http://archive.cloudera.com/cm5/cm/5/)  
> cloudera-manager-el6-cm5.10.0_x86_64.tar.gz

**下载MySQL驱动包**  
地址：[http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)  
> mysql-connector-java-5.1.44-bin.jar  

**注意事项**  
1.命令均root用户, 若无root用户权限自行sudo  
2.将下载好的文件上传到Master服务器/tmp目录下  

**修改主机名称（每台机器上都执行）**  
1. vim /etc/sysconfig/network  
`HOSTNAME=192.168.0.150 -> HOSTNAME=hadoop-cluster-master`  
`HOSTNAME=192.168.0.151 -> HOSTNAME=hadoop-cluster-slave-1`  
`HOSTNAME=192.168.0.152 -> HOSTNAME=hadoop-cluster-slave-2`  
2. 重启network服务修改生效  
`service network restart`
3. 查看主机名称  
`hostname`  

**修改主机名配置（每台机器上都执行）**  
1. vim /etc/hosts (集群中各个主机之间通信使用统一的主机名配置)  
> 127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4  
> ::1         localhost localhost.localdomain localhost6 localhost6.localdomain6  
>  
> 192.168.0.150   hadoop-cluster-master  
> 192.168.0.151   hadoop-cluster-slave-1  
> 192.168.0.152   hadoop-cluster-slave-2  

**设置SSH免密登录(每台机器上都执行)**  
1. 生成密钥，按三下回车即可  
`ssh-keygen`  
2. 将公钥发送到其它服务器上  
`ssh-copy-id xxx.xxx.xxx.xxx`  

**关闭防火墙以及SELinux(每台机器上都执行)**  
1. 先关闭防火墙服务  
`service iptables stop`  
2. 设置开机不开启  
`chkconfig iptables off`  
3. 修改SELinux  
`vim /etc/selinux/config`  
`SELINUX=enforcing => SELINUX=disabled`  
4. 重启服务器  
`reboot`  

**配置JDK(每台机器上都执行)**  
1. 创建Java文件夹  
`mkdir -p /usr/java`
2. 解压JDK压缩包  
`tar -zxvf jdk-8u161-linux-x64.tar.gz -C /usr/lib/java/`
3. 将oracle-j2sdk1.7-1.7.0+update67-1.x86_64.rpm文件放入/usr/java  
`cp oracle-j2sdk1.7-1.7.0+update67-1.x86_64.rpm /usr/java`
`chmod +x oracle-j2sdk1.7-1.7.0+update67-1.x86_64.rpm`
4. 安装JDK  
`rpm -ivh oracle-j2sdk1.7-1.7.0+update67-1.x86_64.rpm`
5. 配置环境变量  
`vim /etc/profile`
> export JAVA_HOME=/usr/java/jdk1.7.0_67-cloudera  
> export JRE_HOME=$JAVA_HOME/jre  
> export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib  
> export PATH=$JAVA_HOME/bin:$PATH  
6. 使环境变量生效  
`source /etc/profile`  

**配置NTP(每台机器上都执行)**  
• 安装ntp服务  
`yum install ntp`  
• 查询ntp服务状态  
`service ntpd status`  
• NTP配置修改, 时间以Master Server为基准  
`vim /etc/ntp.conf`  
​
\> driftfile /var/lib/ntp/drift  
	​
\> restrict default kod nomodify notrap nopeer noquery  
\> restrict -6 default kod nomodify notrap nopeer noquery  
	​
\> restrict 127.0.0.1  
\> restrict -6 ::1  
	​
\> restrict 192.168.0.150 mask 255.255.255.0 nomodify notrap  
\> server 192.168.0.150  
	​
\> server 0.centos.pool.ntp.org iburst  
\> server 1.centos.pool.ntp.org iburst  
\> server 2.centos.pool.ntp.org iburst  
\> server 3.centos.pool.ntp.org iburst  
	​
\> server  192.168.0.150     # local clock  
\> fudge   192.168.0.150 stratum 10  
	​
\> includefile /etc/ntp/crypto/pw  
	​
\> keys /etc/ntp/keys  

• 重启服务或开启服务  
`service ntpd start`  
配置MySQL(Master机上安装)
1. 安装MySQL  
`yum install mysql-server`  
2. 设置开机自启  
`chkconfig mysqld on`  
3. 启动MySQL  
`service mysqld start`  
4. 设置密码  
`mysqladmin -u root password 'xxxx'`
5. 进入MySQL  
`mysql -u root -p 'xxxx'`  
​
\>>> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;  
\>>> FLUSH PRIVILEGES;  
\>>> CREATE DATABASE hive       DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  
\>>> CREATE DATABASE amon       DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  
\>>> CREATE DATABASE oozie      DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  
\>>> CREATE DATABASE hue        DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  
\>>> CREATE DATABASE navms      DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  
\>>> CREATE DATABASE nav        DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  
\>>> CREATE DATABASE sentry     DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  
\>>> CREATE DATABASE rman       DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  
\>>> CREATE DATABASE metastore  DEFAULT CHARSET utf8 COLLATE utf8_general_ci;  

**安装Cloudera Manager**  
1. 解压cloudera-manager-centos7-cm5.10.0_x86_64.tar.gz文件到/opt目录下  
`tar -zxvf /tmp/cloudera-manager-el6-cm5.10.0_x86_64.tar.gz -C /opt/`
2. 将mysql-connector-java-5.1.14.jar放到/opt/cm-5.10.0/share/cmf/lib下  
`cp /tmp/mysql-connector-java-5.1.14.jar /opt/cm-5.10.0/share/cmf/lib`
3. 主节点上初始化CM5数据库, 因为CM监控管理配置数据均放在MySQL中存储  
`/opt/cm-5.10.0/share/cmf/schema/scm_prepare_database.sh mysql -hlocalhost -uroot -pxxxx --scm-host localhost cm root xxxx`

参数 | 说明 
-|-
mysql | 数据库用的是MySQL, 如果安装的是Oracle则改为oracle |
-hlocalhost | 数据库建立在Master主机上面, 也就是主节点上面 |
-uroot | 以root用户运行MySQL |
-pxxxx| MySQL用户的密码 |
--scm-host| CMS的主机, 一般是和MySQL安装的主机是在同一个主机上 |
cm, scm, xxxx | 最后三个参数, 分别为数据库名称, 数据库用户名, 数据库密码 |


4. 修改/opt/cm-5.10.0/etc/cloudera-scm-agent/config.ini中的server_host为主节点主机名  
`vim /opt/cm-5.10.0/etc/cloudera-scm-agent/config.ini`  
`server-host=localhost >> server-host=hadoop-cluster-master`  
5. 同步Agent配置到从节点  
`scp -r /opt/cm-5.10.0 root@192.168.0.151:/opt/`  
`scp -r /opt/cm-5.10.0 root@192.168.0.152:/opt/`  
6. 创建cloudera-scm用户(在所有节点)  
`useradd --system --home=/opt/cm5.10.0/run/cloudera-scm-server/ --no-create-home --shell=/bin/false --comment "Cloudera SCM User" cloudera-scm`
7. 准备Parcels, 用以安装CDH5, 将CDH5相关的Parcel包放在主节点的/opt/cloudera/parcel-repo/目录中  
`cp /tmp/CDH-5.10.0-1.cdh5.10.0.p0.41-el6.parcel /opt/cloudera/parcel-repo/`  
`cp /tmp/CDH-5.10.0-1.cdh5.10.0.p0.41-el6.parcel.sha1 /opt/cloudera/parcel-repo/`  
`cp /tmp/manifest.json /opt/cloudera/parcel-repo/`  
8. 最后将CDH-5.10.0-1.cdh5.10.0.p0.41-el6.parcel.sha1重命名为最后将CDH-5.10.0-1.cdh5.10.0.p0.41-el6.parcel.sha  
`mv CDH-5.10.0-1.cdh5.10.0.p0.41-el6.parcel.sha1 CDH-5.10.0-1.cdh5.10.0.p0.41-el6.parcel.sha`
9. 相关启动脚本(只有主节点启动Server, 所有节点都要启动Agent服务)  
`/opt/cm-5.10.0/etc/init.d/cloudera-scm-server start`  
`/opt/cm-5.10.0/etc/init.d/cloudera-scm-agent start`  
	
**CDH5安装配置**  
[https://www.cnblogs.com/bugsbunny/p/7561917.html](https://www.cnblogs.com/bugsbunny/p/7561917.html)  

**问题总结**  
1. Cloudera 建议将 /proc/sys/vm/swappiness设置为最大值10, 当前设置为60  
`echo 0 > /proc/sys/vm/swappiness`
2. 已启用透明大页面压缩
> echo never > /sys/kernel/mm/transparent_hugepage/defrag  
> echo never > /sys/kernel/mm/transparent_hugepage/enabled  

`vim /etc/rc.local`  
> echo never > /sys/kernel/mm/transparent_hugepage/defrag  
> echo never > /sys/kernel/mm/transparent_hugepage/enabled
