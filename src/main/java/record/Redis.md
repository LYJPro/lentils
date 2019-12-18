**将Redis服务器添加至windows服务**  
`redis-server.exe --service-install redis.windows.conf`

**Linux安装Redis**  
`apt-get install redis-server`  

**查看服务状态**  
`/etc/init.d/redis-server status`  
`service redis-server status`  

**访问Redis服务**  
`redis-cli -h 127.0.0.1 -p 6379 -a 111111`  

**修改配置文件**  
`vim /etc/redis/redis.conf`  
修改连接Redis密码：
> requirepass ""  

默认情况下不允许远程访问，需要注释  
> \# bind 127.0.0.1  

**重启Redis**  
`/etc/init.d/redis-server restart`  
`service redis-server restart`  

**快照dump.rdb存放路径**  
`/var/lib/redis`  

**日志文件**  
`/var/log/redis/redis-server.log `

**AOF文件存放位置**  
`/var/lib/redis`

**配置database数量**
> databases 16  

**Redis禁用命令，因为有些命令是很危险的**  
KEYS *: 数据量规模很大时，影响性能  
FLUSHALL、FLUSHDB清空数据  
> rename-command FLUSHALL ""  
> rename-command FLUSHDB ""  
> rename-command KEYS ""   

**Redis空间索引**  
GEO命令可以创建空间索引，根据经纬度创建索引，也可以查询地图位置  

**Redis全称**  
Remote Dictionary Server

# 哨兵
**Linux安装哨兵**
`apt-get install redis-sentinel`

**配置文件存放位置**
/etc/redis

**日志文件存放位置**
/var/log/redis

**哨兵与Redis启动规范**  
启动首先启动master-redis，稍后启动slave-redis，然后启动三台sentinel。最好sentinel与redis-server不在同一台服务器上。关闭时，先要关闭三台哨兵服务，否则会出现配置混乱，然后关闭slave-server，接下来关闭master-server。

**哨兵负载均衡**  
哨兵是为了满足Redis集群的高可用性，实施故障转移(failover)，并不能做到负载均衡的作用。如果想要实现Redis的负载均衡，需要三方工具帮助。
数据一致性
Redis并不能保证数据一致性，因为复制是异步的

**Redis持久化**  
1. 快照是Redis默认的持久化操作，间隔一定时间保存当前数据，可通过save 900 1 save "" 配置。 但是没有即时性。
2. Appendonly File，aof文件具有即时性，记录Redis数据插入、删除、修改记录
3. appendonly yes/no 配置。 yes：将操作记录写入appendonly.aof文件中，并且重启时读取 appendonly.aof文件，如果文件已被删除，会发现Redis数据丢失。no：默认使用快照存储，重启之后读取快照文件。

# Redis主从  
**配置文件**  
从Redis配置文件中slaveof <masterip> <masterport>配置，若主Redis有密码，则配置masterauth <masterpass>。

**主从同步流程**  
1. 从服务器启动之后，向主服务器发送SYNC命令。
2. 主服务器开始执行BGSAVE命令。
3. 主服务器将快照发送给从服务器。
4. 从服务器丢弃所有旧数据，开始载入主服务器发来的快照文件，如果有AOF也重写AOF文件。
5. 主服务器每执行一个写命令成功之后，就向从服务器发送相同的命令，从服务器的AOF文件也会出现写操作记录。
6. slave服务器是只读的，如果向其中进行写操作，是不允许的。但是可以对从进行配置，使其可以进行write操作，但是不会传播事务。

**Redis哨兵配置**  
1. 主观不可用，SDOWN
2. 客观不可用，ODOWN
3. sentinel failover-timeout <master-name> <millseconds> 给多久时间写配置文件内容
4. sentinel down-after-milliseconds <master-name> <milliseconds> 无响应多久之后才进行主从切换
5. sentinel parallel-syncs <master-name> <numslaves> 


# Redis 集群分区
