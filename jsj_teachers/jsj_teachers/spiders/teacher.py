import scrapy
from jsj_teachers.items import JsjTeachersItem
from urllib.parse import urljoin
import time

class TeacherSpider(scrapy.Spider):
    name = 'teacher'
    allowed_domains = ['gdupt.edu.cn']
    start_urls = ['https://site.gdupt.edu.cn/jsjxy/szdw/jsfc1.htm']

    # 处理详情首页
    def parse_detail(self, response):
        item = response.meta["item"]
        item["detail"] = response.xpath("//div[@class='v_news_content']//p/text()").extract()
        yield item

    def parse(self, response):
        item_list = response.xpath("//div[@class='main_conRCb']/ul/li")
        #print(item_list.extract())
        for list1 in item_list:
            item = JsjTeachersItem()
            print(list1.extract())
            item["name"] = list1.xpath("./a/em/text()").extract_first()
            item["time"] = list1.xpath("./span/text()").extract_first()
            # 处理详情页
            detail_href = urljoin("https://site.gdupt.edu.cn/jsjxy/szdw/jsfc1.htm",list1.xpath("./a/@href").extract_first())
            yield scrapy.Request(detail_href,callback=self.parse_detail,meta={"item":item})
            time.sleep(2)
        # 下一页
        time.sleep(1)
        next_url = "https://site.gdupt.edu.cn/jsjxy/szdw/"+str(response.xpath("//span[@class='p_next p_fun']/a/@href").extract_first())
        yield scrapy.Request(next_url,callback=self.parse2)

    # 处理非首页
    def parse2(self, response):
        item_list = response.xpath("//div[@class='main_conRCb']/ul/li")
        # print(item_list.extract())
        for list1 in item_list:
            item = JsjTeachersItem()
            print(list1.extract())
            item["name"] = list1.xpath("./a/em/text()").extract_first()
            item["time"] = list1.xpath("./span/text()").extract_first()
            # 处理详情页
            detail_href = urljoin("https://site.gdupt.edu.cn/jsjxy/szdw/jsfc1/1.htm",
                                  list1.xpath("./a/@href").extract_first())
            yield scrapy.Request(detail_href, callback=self.parse_detail, meta={"item": item})
            time.sleep(2)


