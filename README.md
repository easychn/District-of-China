# District-of-China

中华人民共和国行政区划：省级、地级、县级。

## 数据来源

*   民政部、国家统计局：
    * [中华人民共和国民政部-中华人民共和国行政区划代码](http://www.mca.gov.cn/article/sj/xzqh/)
    * [中华人民共和国国家统计局-统计用区划和城乡划分代码](http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/)
    * [中华人民共和国国家统计局-统计用区划代码和城乡划分代码编制规则](http://www.stats.gov.cn/tjsj/tjbz/200911/t20091125_8667.html)

## 数据处理
1. 将最新的中华人民共和国行政区划代码整理到 /src/main/resources/data/district-of-China-mainland.csv文件中，参照如下格式：

~~~
110000,北京市
110101,东城区
110102,西城区
110105,朝阳区
110106,丰台区
110107,石景山区
110108,海淀区
110109,门头沟区
110111,房山区
110112,通州区
110113,顺义区
110114,昌平区
110115,大兴区
110116,怀柔区
110117,平谷区
110118,密云区
110119,延庆区
~~~

2. 启动项目，解析后的数据分别写入到/target/classes/data/目录的provinces.csv、cities.csv、areas.csv文件中，可根据自身项目数据结构将解析到的数据进行转换。
