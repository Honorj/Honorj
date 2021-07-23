# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html

import pymysql
from gz_rsj import settings
# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
import re

class GzRsjPipeline:
     #爬虫开启就连接数据库,只执行一次
    def open_spider(self,spider):
        self.connect = pymysql.connect(
            host=settings.MYSQL_HOST,
            db=settings.MYSQL_DBNAME,
            user=settings.MYSQL_USER,
            passwd=settings.MYSQL_PASSWD,
            charset='utf8',
            use_unicode=True)
        # 通过cursor执行增删查改
        self.cursor = self.connect.cursor()

    #  爬虫关闭的时候断开数据库，只执行一次
    def close_spider(self,spider):
        self.cursor.close()
        self.connect.close()

    # 保存数据到数据库
    def process_item(self, item, spider):
        global st
        item["detail"] = self.content_clear(item["detail"])
        # 时间的处理
        reportdate = self.content_clear(item["reportDate"])
        # 把返回的列表变成字符串,由于日期和时间之间有个空格被正则表达式去掉了，这里要加回来
        reportDate = "".join(reportdate)
        i=1
        st = ""
        for s in reportDate:
            st += s
            if i == 10:
                st += " "
            i +=1
        item["reportDate"] = st
        # 取原公告链接
        index = [i for i, x in enumerate(item["detail"]) if
                 x.find("http://rsj.gz.gov.cn/ywzt/rszdgg/sydwgkzp/sydwzpgg/content") != -1]
        # print(index)
        if index != []:
            # print(index[0])
            item["OriginURL"] = item["detail"][index[0]]
        else:
            item["OriginURL"] = None
            # print(item["OriginURL"])
        # 写入数据库 ###################
        try:
            # 插入数据
            self.cursor.execute(
                "insert into zp(title,reportDate,OriginURL,detail,attachedName,attachedURL,origin) value "
                "(%s, %s, %s,%s, %s, %s,%s)",
                (str(item["title"]),str(item["reportDate"]),str(item["OriginURL"]),str(item["detail"]),
                str(item["attachedName"]),str(item["attachedURL"]),str(item["origin"])
                 ))
            # 提交sql语句
            self.connect.commit()
        except Exception as error:
            # 出现错误时打印错误日志
            print(error)
            self.cursor.close()
            self.connect.close()

        ############################################
        return item

    # 数据的清洗
    def content_clear(self,content):
        content = [re.sub(r"\u3000|\s|\n|None","",i) for i in content]
        content = [i for i in content if len(i)>0]   # 去除列表中空字符串
        return content
