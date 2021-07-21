# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
import pymysql
from jsj_teachers import settings

class JsjTeachersPipeline:
    def __init__(self):
        # 连接数据库
        self.connect = pymysql.connect(
            host=settings.MYSQL_HOST,
            db=settings.MYSQL_DBNAME,
            user=settings.MYSQL_USER,
            passwd=settings.MYSQL_PASSWD,
            charset='utf8',
            use_unicode=True)
        # 通过cursor执行增删查改
        self.cursor = self.connect.cursor();

    def process_item(self, item, spider):
        itemname = str(item["name"])
        itemdetail = str(item["detail"])
        itemtime = str(item["time"])
        try:
            # 插入数据
            self.cursor.execute(
                "insert into teacher_info(name,detail,time) value (%s, %s, %s)",
                (itemname,
                 itemdetail,
                 itemtime
                 ))
            # 提交sql语句
            self.connect.commit()
        except Exception as error:
            # 出现错误时打印错误日志
            print(error)
            self.connect.close()
        return item
