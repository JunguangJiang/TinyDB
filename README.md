# TinyDB

### 开发环境
- IDEA
- 使用gradle进行管理
```bash
gradle assemble #编译并生成主程序，Main函数的设置在build.gradle中
java -jar build/libs/TinyDB-1.0.jar #运行主程序Main
```
其他的gradle命令可以通过gradle tasks查看。


### 开发说明
- 单元测试在src/test下
- 系统测试在system_test_data下，通过比较test.sql的输出test.out和正确答案test.ans，来判断系统是否正确执行。 其中只会判断表格形式的输出是否匹配（空格不匹配不影响结果）
- 通过修改build.gradle文件中的
```bash
'Main-Class': 'db.SqlTest'
```
可以改变程序的入口，SqlTest入口是用来进行系统测试的；此后需要实现Server和Client的入口，从而生成服务器和客户端的程序。
