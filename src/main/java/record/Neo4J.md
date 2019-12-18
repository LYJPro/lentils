**Neo4j基础**
1. Neo4j安装之后浏览器访问：   
   需要取消config中的注释：
   > dbms.connectors.default_listen_address=0.0.0.0  
http://192.168.91.133:7474  
用户名：neo4j 
密码：123456
2. 高可用HA，事务传播
   若在主机上执行写操作，该事务会推送到集群从机上。若在从机上进行写操作，  
   它首先会在主机上提交事务，成功以后才会在从机上提交。  
3. Neo4j-HA集群  
   Neo4j集群启动，起初主从并没有确认，所以需要所有的集群成员都启动才可以全部启动，再根据所执行事务的多少，
   进行集群之间自身选举。而Redis并不是，因为起初就已经定义好主从，再由哨兵监听。
4. 配置Neo4j因果集群，无法选举Leader问题： 
   查看日志考虑到有可能日志索引和任期发生出现混乱，最后暴力解决，全部删除，重启服务器，重新生成索引。就成功了。
   下图为输入命令：
  `（:sysinfo） `  
5. 因果集群协议 
   因果集群支持bolt+routing智能路由协议，官方驱动可将读写服务器分配，实现高可用，以及负载均衡，对于服务器的伸缩性也有很大的作用。
   因果集群伸缩性也很方便，如果新增服务器，只需要将新的服务器配置因果集群，开启服务即可连接其他成员。
6. 空间索引 
   Neo4j支持空间索引。支持GEO相关工具，对二维数据进行索引。例如地图信息，二维信息等。
7. 因果集群支持点到点的发现 例如： 
   > server1配置：cluster_size = 2; server1:5000, server2:5000;  
   > server2配置：cluster_size = 2; server2:5000, server3:5000;  
   > server3配置：cluster_size = 2; server3:5000, server1:5000; 
 
   也可以发现核心服务器中的所有成员。  
8. Neo4j-因果集群事务传播 
   通过Raft算法，保证事务传播的数据一致性。
9. Neo4j中Cypher是存在Cypher注入的 
   对于cypher的注入，驱动并没有拦截。

**Neo4j高可用配置**
1. 在企业版才可配置高可用集群
2. 配置文件中，HA Configuration配置栏中
3. dbms.mode=HA，现在为高可用模式。
4. ha.server=1，当前服务器在集群中的编号。
5. ha.initial_hosts=192.168.91.133,192.168.91.134,192.168.91.135，初始化集群中的成员。

**Neo4j因果集群配置**
1. 在企业版才可配置因果集群
2. 配置文件中，Causal Clustering Configuration配置栏中
3. dbms.mode=CORE，说明在因果集群中，属于核心成员还是只读副本。
4. causal_clustering.expected_core_cluster_size=3，初始核心成员数量。
5. causal_clustering.initial_discovery_members=192.168.91.133:5000,192.168.91.134:5000,192.168.91.135:5000 
   因果集群初始化应该发现的成员。  

**官网考题**  
`
https://www.classmarker.com/online-test/start/?quiz=kvf5988792c136d6&cm_user_id=auth0|5a97dc725f813f27fc5607e4&cm_fn=liu&cm_ln=yongjian&cm_eliuyongjian@sqhtech.com `  
用户名：liuyongjian@sqhtech.com   
密码：normal