# QuickJedis
Quick start using jedis

快速上手Jedis，更多的关注业务开发

``` xml
<?xml version="1.0" encoding="utf-8" ?>
<redis-root>
    <redis-node name="HelloRedis" server="127.0.0.1:6379" db="2" encoding="UTF-8" />
    <redis-node name="WorldRedis" server="127.0.0.1:6379" db="0" encoding="GBK" />
    ...
</redis-root>
```
<br />
<h4>配置说明：</h4>
>>name : redis实例名称

>>server : redis服务器IP:redis服务器端口

>>db : redis数据库，从0到15

>>encoding : 数据编码
