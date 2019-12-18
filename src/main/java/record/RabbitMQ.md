**安装RabbitMQ**  
`apt-get install rabbitmq-server`

**查看服务状态**  
`service rabbitmq-server status`

**日志文件目录**  
`cd /var/log/rabbitmq/`

**开启RabbitMQ管理页面**  
`rabbitmq-plugins enable rabbitmq_management`

**打开管理页面**  
`http://localhost:15672`

**创建用户**  
`./rabbitmqctl add_user liuyongjian 123456`

**给用户赋予权限**  
`rabbitmqctl set_permissions -p / liuyongjian "." "." ".*"`

**设置为管理员角色**  
`rabbitmqctl set_user_tags liuyongjian administrator`

**查看当前用户**  
`rabbitmqctl list_users`

**存放数据**  
`cd /var/lib/rabbitmq/mnesia`

**配置文件存放位置**  
`cd /etc/rabbitmq`
