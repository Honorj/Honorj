# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class GzRsjItem(scrapy.Item):
    # define the fields for your item here like:
    title = scrapy.Field()          # 公告标题
    reportDate = scrapy.Field()     # 公告发布时间
    origin = scrapy.Field()         # 信息来源
    OriginURL = scrapy.Field()      # 公告原来的URL
    detail = scrapy.Field()         # 内容
    attachedName = scrapy.Field()   # 附件名称，多个附件用空格隔开
    attachedURL = scrapy.Field()    # 附件链接
    # detail_URL = scrapy.Field()     # 详情页的URL