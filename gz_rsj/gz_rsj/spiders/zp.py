import scrapy
from gz_rsj.items import GzRsjItem

class ZpSpider(scrapy.Spider):
    name = 'zp'
    allowed_domains = ['gz.gov.cn']
    start_urls = ['http://rsj.gz.gov.cn/ywzt/rszdgg/sydwgkzp/sydwzpgg/']

    def parse(self, response):
        print("程序在爬取信息中...")
        li_list = response.xpath("//div[@class='pageList']/ul[@class='infoList clearfix']/li")
        for List in li_list:
            item = GzRsjItem()
            item["title"] = List.xpath(".//a/text()").get()
            detail_url = List.xpath(".//a/@href").get()   # 详情页地址
            yield scrapy.Request(
                detail_url,
                callback=self.parse_detail,
                meta={"item":item}
            )

        #翻页
        next_url = response.xpath("//div[@class='pagediv clearfix']/span/a[@class='next']/@href").get()
        if next_url is not None:
            yield scrapy.Request(
                next_url,
                callback=self.parse
            )


    # 处理详情页
    def parse_detail(self, response):
        item = response.meta["item"]
        item["reportDate"] = response.xpath("//div[@class='time clearfix']/span[@class='publishTime']/publishtime/text()").extract_first()
        item["origin"] = response.xpath("//div[@class='time clearfix']/span[@class='resourse']/font/text()").extract_first()
        item["detail"] = response.xpath("//div[@class='content']/ucapcontent/p//text()").extract()
        content_list = response.xpath("//div[@class='content']/ucapcontent/p")
        attachedName_list = []
        attachedURL_list = []
        item["attachedName"] = None
        item["attachedURL"] = None
        for content in content_list:
            if (content.xpath("./a/@href").get()) is not None:
                attachedName = content.xpath("./a[@class='nfw-cms-attachment']/text()").extract_first()
                attachedName_list.append(attachedName)
                item["attachedName"] = attachedName_list

                attachedURL = content.xpath("./a[@class='nfw-cms-attachment']/@href").extract_first()
                attachedURL_list.append(attachedURL)
                item["attachedURL"] = attachedURL_list

        yield item



