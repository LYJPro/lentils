
**修改CentOS的配置**  
```
vim  /etc/yum/pluginconf.d/fastestmirror.conf
```

> [main]  
> enabled=0   //由 1 改成0 ，禁用该插件  
> verbose=0  
> always_print_best_host = true  
> socket_timeout=3  
> \# Relative paths are relative to the cachedir (and so works for users as well  
> \# as root).
> hostfilepath=timedhosts.txt  
> maxhostfileage=10  
> maxthreads=15  
> \# exclude=.gov, facebook  
> \# include_only=.nl,.de,.uk,.ie  


**修改yum配置文件**
```
vim /etc/yum.conf
```

> 9 plugins=0  //不使用插件


**清除缓存并重新构建yum源**
```
yum clean all
yum makecache
```

**安装Redis**
```
yum install redis
```