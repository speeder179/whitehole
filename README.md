#Whitehole


## 模块
- Whitehole-auth: 权限认证模块
- Whitehole-core: Whitehole主体API
- Whitehole-swagger: swagger集成到Whitehole


## How to run?

### run with jar
> cd whitehole
> mvn clean package
> java -jar whitehole-core/target/whitehole.jar server whitehole-core/whitehole.yml


### run with IDE
>新建运行Application
>设置main class: www.chinacloud.com.whitehole.core.WhiteholeApplication
>设置Program arguments: server whitehole.yml
>设置运行目录: whitehole-core
>设置classpath of module: whitehole-core

### API Docs
> http://localhost:8080/whitehole/api/v1/docs

### Admin url
>http://localhost:8080/whitehole/admin