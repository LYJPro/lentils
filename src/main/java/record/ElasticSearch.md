**启动ElasticSearch**  
`
安装时需要分配2G以上内存，不能用root身份启动Elasticsearch，因为不允许也很危险，需要用普通用户启动。如果Elasticsearch中的文件都属于root，普通用户是无权限启动的。所以需要使用chown -R liuyongjian:root ./elasticsearch命令，将Elasticsearch文件夹下所有的文件权限都转移至普通用户。如果需要非本地访问，则修改配置文件config/elasticsearch.yml中network.host改为：network.host：0.0.0.0，启动有可能出现：node validation exception bootstrap checks failed max virtual memory areas vm.max_map_count [65530] is too low, increase to at least，需要切换root用户，vim /etc/sysctl.conf，增加一行 vm.max_map_count=655360，执行sysctl -p，执行bash /bin/elasticsearch，后台启动需要执行bash /bin/elasticsearch -d，可访问9200端口查看WEBUI  
`  

**关闭ElasticSearch**  
`ps aux|grep elasticsearch 找到进程，kill进程`  

**配置Kibana**  
`Kibana与Elasticsearch结合使用的，对于数据的可视化平台。下载安装包，修改配置文件，server.host选项为：server.host: 0.0.0.0，elasticsearch.url选项改为：elasticsearch.url:"192.168.91.133:9200"，执行bash /bin/kibana &，记录一下进程ID，以便后续kill进程，可访问5601 端口查看WEBUI。配置日志输出logging.dest，日志输出默认是stdout，修改成会很麻烦，所以可以配置成一个日志文件。
`
