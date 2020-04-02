## 单机数据库机制

#### 服务器中的数据库

redis服务器将所有数据库都保存在数据库状态redis.h/redisServer结构的db数组中。db中每个都是redis.h/redisDb结构，每个这个结构都代表一个数据库。默认创建16个数据库。

示例：

![redisServer](img\redisServer.png)

切换数据库:SELECT x (x:第x+1个数据库)

#### 数据库键控件

redisDb结构的dict字典保存了数据库中的所有键值对，我们将这个字典称为键空间。

- 键空间的键就是数据库的键，每个键都是一个字符串对象。
- 键空间的值就是数据库的值，每个值可以是字符串对象、列表对象、哈希表对象、集合对象、和有序集合对象中的任意一种redis对象。

示例：

![redisDb](img\redisDb.png)