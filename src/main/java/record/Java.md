**安装**  
`mkdir /usr/lib/java`  
`tar -zxvf jdk-8u161-linux-x64.tar.gz -C /usr/lib/java/`

**配置环境变量**  
`1. vim /etc/profile`  
> export JAVA_HOME=/usr/lib/java/jdk1.8.0_161  
> export JRE_HOME=$JAVA_HOME/jre  
> export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib  
> export PATH=$JAVA_HOME/bin:$PATH  

`2. source /etc/profile`


**Java中的Jar的Main方法执行如果希望终端断开，jar后台执行，则用命令:**  
`nohup java -jar xxx.jar&`

## 运算符顺序
单目>算数运算符>移位>比较>按位>逻辑>三目>赋值

单目运算符（一元运算符，即一个变量）：+，-，++，--  
算数运算符：+，-，*，/，%  
移位运算符：<<,>>  
关系运算符：>,<,>=,<=,==,!=  
位运算符：&，|，~，^,  
逻辑运算符：&&，||  
三目运算符：表达式1？表达式2：表达式3;  
赋值运算符：=等   

## BOF和EOF
1. 读写数据：I/O，I即是将磁盘中的数据放入内存中，O即是将内存中的内容放入磁盘中，将数据查询出，内存就会出现一个列表
列表中就是查询结果，这个内存列表就是JDBC中的ResultSet，BOF就表示ResultSet当前指针指在了数据集前面，比如数据集里面
有编号为1, 2, 3, 4的四条记录，单RS指针指向编号-1，这样就符合了BOF情况，EOF就表示RS当前指针指在了数据集后面，当RS
指针指向5，第五条记录并不存在，这样就符合了EOF标准。
2. 如果同时产生了BOF和EOF情况，就是说ResultSet指针既在上界之外也在下界之外，这样只有一种情况，就是ResultSet所代表
的数据为空，这样ResultSet指针无论指到什么地方都是同时具有BOF和EOF的属性，读流时常常出现EOFException，是指当输入过
程中以外到达文件或流的末尾时，抛出此异常，此异常主要被数据输入流来表达流的末尾，注意，其他许多输入操作返回一个特殊值
来表达流的末尾，而不是抛异常。

`int length = 0;`  
`byte[] bytes = new byte[200];`  
`FileInputStream fis = new FileInputStream(readFile);`  
`while((length = fis.read(bytes)) != -1) {`  
    `fos.write(bytes, 0, length);`  
`}`  

## 多线程优先级问题
多线程本身就是多个线程交换执行，并非同时执行，执行的优先级只是概率问题。原本优先级是一样的，概率都是50%，当我们提高
其中一个，那40%的线程有40%几率抢到CPU执行机会。

I/O BufferWriter字符输出流，只有当out调用时flush时，会将缓冲区中的字符输出到目的地。
BufferReader字符输入流，不论是readLine()还是Lines()都是部分读到内存中的。

## 移位符号 <<, >>, >>>
<< 即是数值的二进制向左移n位，如int i = 2;
i << 2 = 8;

\>\> 即是数值的二进制向右移n位，如int i = 16;
i \>\> 2 = 4;

右移位时，正数好说，左侧补齐0，但是负数就考虑是以1还是0补位的问题了。
Java中一个有符号int类型的数是32位，他可以表示的范围是-2^31 -> 2^31 - 1。
那么如何表示负数，左边以1开始就是负数，这就出现了移位操作符有符号和无符号
之分，>>> 为无符号右移，以0补齐。

### Byte为什么会 & 0xFF
定义一个byte数组，放入-127到数组中，计算机存储的补码是1000 0001(8位)
当对array[0]中的byte操作时，计算机将其转换成了int补位后的补码是
11111111 111111111 11111111 10000001
也是 -127正常计算没有问题，但是我们有时候需要保持一致性，让int与byte
的1和0一样，所以我们需要 & 0xFF