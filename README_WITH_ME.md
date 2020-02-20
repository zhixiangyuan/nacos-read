
```shell script
# 编译命令
# 编译后生成的文件在 distribution/target 目录下
mvn -Prelease-nacos clean install -U -Dmaven.test.skip=true

# 启动命令
bin/startup.sh -m standalone
```

