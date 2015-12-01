#Whitehole


## 模块
- Whitehole-auth: 权限认证模块
- Whitehole-core: Whitehole主体API
- Whitehole-swagger: swagger集成到Whitehole


## How to run?

### Database setup
```shell
mysql -uroot -proot;
CREATE USER 'whitehole'@'%' IDENTIFIED BY 'wh123';
GRANT ALL PRIVILEGES ON `whitehole`.* TO 'whitehole'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```

### run with jar
```shell
cd whitehole
mvn clean package
java -jar whitehole-core/target/whitehole.jar server whitehole-core/whitehole.yml
```

### run with IDE
1. 新建运行Application
2. 设置main class: [www.chinacloud.com.whitehole.core.WhiteholeApplication](https://github.com/speeder179/whitehole/blob/develop/whitehole-core/src/main/java/www/chinacloud/com/whitehole/core/WhiteholeApplication.java)
3. 设置Program arguments: server whitehole.yml
4. 设置运行目录: whitehole-core
5. 设置classpath of module: whitehole-core
6. run

### API Docs
>http://localhost:8080/whitehole/api/v1/docs

### Admin url
>http://localhost:8080/whitehole/admin



## TODOs
- @Auth 集成One-AA
- 集成工作流引擎(activiti)
- 集成分布式任务队列(jesque)
- 集成消息推送(atmosphere)