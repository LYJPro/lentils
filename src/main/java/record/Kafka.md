Zookeeper默认端口号2181  
Kafka默认端口号9092

## QuickStart  

**启动Zookeeper-server**  
`./zookeeper-server-start.sh ../config/zookeeper.properties &`

**启动Kafka-server**  
`./kafka-server-start.sh ../config/server.properties &`

**创建Topic**  (partitioncount：3，replication count：3)  
`./kafka-topics.sh --create --zookeeper 【zookeeper server:port】 --topic 【topic name】 --partitions 【partition count】 --replication-factor 【replication count】`

**查看当前Topic状态**  
`./kafka-topics.sh --describe --zookeeper 【zookeeper server:port】 --topic 【topic name】`

**生产消息**  
`./kafka-console-producer.sh --broker-list 【Kafka server:port】--topic 【topic name】`

**消费者**  
`./kafka-console-consumer.sh --bootstrap-server 【Kafka server:port】 --topic 【topic name】`

**生产者测试吞吐量**  
`./kafka-producer-perf-test.sh --topic 【topic name】 --num-records 500000 --record-size 200 --throughput -1 --producer-props bootstrap.servers=localhost:9092 acks=-1`

**消费者测试吞吐量**  
` ./kafka-consumer-perf-test.sh --broker-list 【Kafka server:port】 --fetch-size 2000 --messages 500000 --topic 【topic name】`

**查看当前分区和副本数**  
`./kafka-topics.sh --zookeeper 【zookeeper server:port】 --describe  --topic 【topic name】`

**修改Topic的分区数、副本因子、Topic级别**  
`./kafka-topics.sh --alter --zookeeper 【zookeeper server:port】 --partitions 【partition count】 --topic 【topic name】`

**GetOffsetShell查看topic分区的消息数**  
`./kafka-run-class.sh  kafka.tools.GetOffsetShell --broker-list 【Kafka server:port】 --topic 【topic name】`

**删除Topic**  
`./bin/kafka-topics --delete --zookeeper 【zookeeper server:port】 --topic 【topic name】`

**查看当前Topic信息**  
`./kafka-topics.sh --zookeeper 【zookeeper server:port】 --list`

**清空Topic**
1. 关闭Kafka服务
2. 查看Kafka中Server.properties配置中的kafka-log位置
3. rm -rf ./kafka-log
4. 开启Kafka服务

