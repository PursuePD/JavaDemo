## 对象

​	redis没有使用sds，链表，字典，跳跃表，整数集合，压缩列表这些数据结构直接实现键值对数据库，而且构建了5种对象（字符串对象，列表对象，哈希对象，集合对象，有序集合对象），每种对象至少包含一种数据结构。

​	好处是在不同场景使用不同的数据结构，提高效率。

#### 对象的类型和编码

| 类型         | 对象                          | 编码                                           | OBJECT ENCODING输出 |
| ------------ | ----------------------------- | ---------------------------------------------- | ------------------- |
| REDIS_STRING | REDIS_ENCODING_INT            | 使用整数值实现的字符串对象                     | int                 |
| REDIS_STRING | REDIS_ENCODING_EMBSTR         | 使用embstr编码的简单动态字符串实现的字符串对象 | embstr              |
| REDIS_STRING | **REDIS_ENCODING_RAW**        | 使用简单动态字符串实现的字符串对象             | raw                 |
| REDIS_LIST   | **REDIS_ENCODING_ZIPLIST**    | 使用压缩列表实现的列表对象                     | ziplist             |
| REDIS_LIST   | **REDIS_ENCODING_LINKEDLIST** | 使用双端链表实现的列表对象                     | linkedlist          |
| REDIS_HASH   | **REDISl_ENCODING_ZIPLIST**   | 使用压缩列表实现的哈希对象                     | ziplist             |
| REDIS_HASH   | **REDIS_ENCODING_HT**         | 使用字典实现的哈希对象                         | hashtable           |
| REDIS_SET    | **REDIS_ENCODING_INTSET**     | 使用整数集合实现的集合对象                     | intset              |
| REDIS_SET    | **REDIS_ENCODING_HT**         | 使用字典实现的集合对象                         | hashtable           |
| REDIS_ZSET   | **REDISl_ENCODING_ZIPLIST**   | 使用压缩列表实现的有序集合对象                 | ziplist             |
| REDIS_ZSET   | **REDISl_ENCODING_SKIPLIST**  | 使用跳跃表和字典实现的有序集合对象             | skiplist            |

#### 字符串对象（REDIS_STRING）

| 对象                   | 编码                                           | 备注                                                      |
| ---------------------- | ---------------------------------------------- | --------------------------------------------------------- |
| REDIS_ENCODING_INT     | 使用整数值实现的字符串对象                     | 当保存对象是整数值并且可以使用long类型表示，对应编码为int |
| REDIS_ENCODING_EMBSTR  | 使用embstr编码的简单动态字符串实现的字符串对象 | 保存对象是字符串时，且字符串长度小于等于32字节            |
| **REDIS_ENCODING_RAW** | 使用简单动态字符串实现的字符串对象             | 保存对象是字符串时，且字符串长度大于32字节                |

**字符串命令:**

![ziplist](img\string_commd.png)