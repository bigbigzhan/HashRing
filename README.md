   ###
   快速开始
   [普通hash方法启动测试类](https://github.com/bigbigzhan/HashRing/tree/master/src/main/java/com/github/bigbigzhan/normal/Run.java)
   
   [一致性hash方法启动测试类](https://github.com/bigbigzhan/HashRing/tree/master/src/main/java/com/github/bigbigzhan/normal/Run.java)
   ```
   我们知道使用hash方式定位最大的问题就是 扩/缩容 需要rehash.带来的数据迁移是非常痛苦的 如何不迁移或减少迁移是一个有意思的事情
   
   分别运行普通hash取模方式和一致性hash方式 测试添加节点或删除节点的命中率可以清晰的看到 一致性hash比普通hash取模方式命中率提高了至少50个百分点
   
   与之相对应的就是在生产环境中如果有扩/缩容 减少移动数据的量
   ```
   

