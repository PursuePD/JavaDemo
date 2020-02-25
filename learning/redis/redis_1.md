#### SDS（简单动态字符串）

问题：为什么redis没有直接使用C语言的字符串而是使用了SDS（simple dynamic string）呢？
		
- 获取字符串长度复杂度：因为C语言获取长度会遍历整个字符串，复杂度为O(N)。而SDS则记录了使用大小len和剩余大小free
- 杜绝缓冲区溢出：C语言的字符串有缓冲区溢出的情况（一个字符串S1被一个更长的字符串替换（未重新分配内存） 导致后面的字符串S2被替换了部分。）
- 减少修改字符串带来的内存重新分配的次数 多次改动字符串不会改动分配的长度（空间预分配+空间惰性释放）。
- 二进制安全



#### 链表

链表的实现

```c
typedef struct list{
    //表头
    listNode *head;
    //表尾
    listNode *tail;
    //包含的节点数量
    unsigned long len;
    
    //节点值复制函数
    void *(*dup)(void *ptr);
    //节点值释放函数
    void *(*free)(void *ptr);
    //节点值对比函数
    int (*match)(void *ptr,void *key);
} list;

typedef struct listNode{
    //前置节点
    struct listNode *prev;
    //后置节点
    struct listNode *next;
    //值
    void *value;
} listNode;
```

特点：
- 双向无环链表

- 带表头表尾指针

- 长度计数器

- 多态 使用的void*指针，可以保存不同类型的值

  

#### 字典（map,一直键值对抽象数据结构）

字典的实现：

```c
//字典
typedef struct dict{
    //类型特点函数
    dictType *type;
    //私有数据
    void *privdata;
    
    //哈希表
    dictht ht[2];
    
    //rehash索引 当不在进行rehash时 值为-1
    int trehashidx;
} dict;

//哈希表
typedef struct dictht{
    //哈希表数组
    dictEntry **table;
    
    //哈希表大小
    unsigned long size;
    
    //哈希表大小掩码，用于计算索引 总是等于size-1
    unsigned long sizemask;
    
    //该哈希表已有节点数量
    unsigned long used;
} dictht;

//哈希表节点
typedef struct dictEntity{
  //键
  void *key;
  //值  可能是指针、uint64_t整数或int64整数
  union{
      void *val;
      uint64_t;
      int64_t;
  } v;
} dictEntity;
```

展示图：![dict](img\dict.jpg)

rehash步骤：

1. 为字典的ht[1]哈希表分配空间，这个哈希表的空间大小取决于要执行的操作，以及ht[0]当前包含的键值对数量(也即是ht[0] .used属性的值):
   - 如果执行的是扩展操作，那么ht[1]的大小为第-一个大于等于ht [0] .used*2的2^n(2的n次方幂);
   - 如果执行的是收缩操作，那么ht[1]的大小为第- 个大于等于ht[0] .used的2^n
2. 将保存在ht[0]中的所有键值对rehash到ht[1]上面: rehash指的是重新计算键的哈希值和索引值，然后将键值对放置到ht[1]哈希表的指定位置上。
3. 当ht[0]包含的所有键值对都迁移到了ht[1]之后(ht[0]变为空表)，释放ht[0]，将ht[1]设置为ht[0]，并在ht[1]新创建-一个空白哈希表，为下一次rehash .做准备。

渐进式rehash：扩展或收缩哈希表需要将ht[0]里面的所有键值对rehash到ht[1]里面，但是，这个rehash动作并不是一次性、集中式地完成的，而是分多次、渐进式地完成的。