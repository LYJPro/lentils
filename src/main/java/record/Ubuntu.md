**查看操作系统版本：**
`head -n 1 /etc/issue`  

**安装VIM** 
`sudo apt-get install vim`

**安装SSH**
`sudo apt-get install ssh`

**安装unzip**
`sudo apt-get install unzip`

**如果安装时没有给root设置密码**  
`sudo passwd root`  
**提权**：`sudo`  
**到root**：`su`  

**检查DISK**  
`lsblk`  
**查询版本**  
`lsb_release -a`  
**查看CPU**  
`cat /proc/cpuinfo | grep "model name"`  
**查看CPU核数**  
`cat /proc/cpuinfo | grep "cpu cores" `  
**查看内存RAM**  
`cat /proc/meminfo | grep emTotal" `  
 
**Ubuntu根目录下文件夹作用**  
> /bin, binary二进制文件，可执行的命令，非管理的命令  
> /sbin,管理类的命令，通常只有管理员才能使用   
> /lib,存放库文件  
> /etc,配置文件的存放位置   
>    /etc/sysconfig,服务额外配置文件，及网络设备相关配置文件  
>    /etc/init.d 服务管理脚本  
> /usr,类似于windows的program file系统日常管理软件的安装路径  
>    /usr/include  头文件存放位置  
>    /usr/local(安装第三方软件的路径）   
>    /usr/local/bin  
> /boot,引导文件，系统启动，kernel，bootloader(grub)  
> /dev ,设备文件所在目录  
> /home,/root,用户主目录  
> lost+found 系统意外关机 未保存的文件  
> media（挂载便携性设备）,/mnt,挂载点  
> /misc ,杂项  
> /opt,option有些第三方软件，把此目录当作默认安装位置  
> /proc,内存中的内核相关信息的映射  
> /sys，像磁盘这样的存储设备或某些总线设备的驱动程序相关属性信息；  
> /srv,service,服务运行中中间的存放位置  
> /tmp,存放临时文件的目录刚刚上传的文件，或者临时的文件存放该文件夹中。临时文件目录。  
> /var,  
>    /var/log,日志文件  
>    /var/run,pid文件  
>    /var/mail,用户邮件的存放位置


**更新镜像源为阿里云源内容：**
> deb http://mirrors.163.com/ubuntu/ xenial main restricted universe multiverse  
> deb http://mirrors.163.com/ubuntu/ xenial-security main restricted universe multiverse   
> deb http://mirrors.163.com/ubuntu/ xenial-updates main restricted universe multiverse  
> deb http://mirrors.163.com/ubuntu/ xenial-proposed main restricted universe multiverse  
> deb http://mirrors.163.com/ubuntu/ xenial-backports main restricted universe multiverse  
> deb-src http://mirrors.163.com/ubuntu/ xenial main restricted universe multiverse  
> deb-src http://mirrors.163.com/ubuntu/ xenial-security main restricted universe multiverse  
> deb-src http://mirrors.163.com/ubuntu/ xenial-updates main restricted universe multiverse  
> deb-src http://mirrors.163.com/ubuntu/ xenial-proposed main restricted universe multiverse  
> deb-src http://mirrors.163.com/ubuntu/ xenial-backports main restricted universe multiverse  

**更新源**  
`vim /etc/apt/sources.list`  
`apt-get update`  
`apt-get upgrade`  

**查看所有服务**
`service --status-all`
<!-- [+]正在运行，[-]未运行，[?]未知状态 -->

**修改权限**
`chmod 777 directory`

**改变文件所有者**
`chown -R liuyongjian:root ./directory`
<!-- -R 处理指定目录以及其子目录下的所有文件 -->

