功能  
反向代理，负载均衡作用  

Nginx安装  
`apt-get install nginx`

配置文件位置  
`/etc/nginx`

日志文件位置  
`/var/log/redis`

服务启动文件  
`/etc/init.d/nginx`

配置本机host  
`C:\Windows\System32\drivers\etc\host`  

解决刷新404错误  
> /etc/nginx/sites-available/default   
> root /var/www/html -> /usr/share/nginx/html local 替换成  
> root html; index index.html index.html; try_files $uri $uri/ /index.html;
`