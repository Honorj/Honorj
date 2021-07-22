import json
import socket
import pymysql
import codecs

def InsertMysql():
    # 要使用codecs进行编码，否则会发生乱码错误
    data = json.load(codecs.open("./jsonFile.json", "r","utf-8"))
    # 连接数据库，一定要加上host，user，password,database属性，如果直接写它们的值会报错，原因还没找到
    db = pymysql.connect(host="localhost", user="root", password="200808", database="web01")
    try:
        result = []
        result.append((data['鞋名'], data['码数'],data['价格'], data['库存']))
        print(result)
        # sql语句的value格式均改为%s（无论要插入的数据是什么类型），可正常运行,否则会报错
        inesrt_re = "insert into tcp(鞋名,码数,价格,库存) values (%s,%s,%s,%s)"
        cursor = db.cursor()
        cursor.executemany(inesrt_re, result)
        db.commit()
    except Exception as e:
        db.rollback()
        print(str(e))
        db.close()


def main():
    # 1. 买个手机(创建套接字 socket)
    tcp_server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # 2. 插入手机卡(绑定本地信息 bind)
    tcp_server_socket.bind(("127.0.0.1", 8008))

    # 3. 将手机设置为正常的 响铃模式(让默认的套接字由主动变为被动 listen)
    tcp_server_socket.listen(128)

    # 循环目的：调用多次accept,从而为多个客户端服务
    while True:
        print("等待一个新的客户端的到来...")
        # 4. 等待别人的电话到来(等待客户端的链接 accept)
        new_client_socket, client_addr = tcp_server_socket.accept()

        print("一个新的客户端已经到来%s" % str(client_addr))

        # 循环目的: 为同一个客户端 服务多次
        while True:
            # 接收客户端发送过来的请求
            recv_data = new_client_socket.recv(1024)
            if recv_data.decode("utf-8") == "quit":
                # 客户端传输数据结束后，把文件内容写入数据库
                InsertMysql()
                break
            try:
                msg_data = json.loads(recv_data.decode('utf-8'))  # 注意编解码方式相同
                file_name = msg_data.get("name")
                file_size = msg_data.get("size")
                recv_size = 0
                with open("./jsonFile.json", "wb") as f:
                    while recv_size != file_size:
                        data = new_client_socket.recv(1024)
                        f.write(data)
                        recv_size += len(data)
                        print("文件大小: %s 传输大小: %s" % (file_size, recv_size))
                        print("文件 %s 传输成功..." % file_name)

            except:
                break
        # 关闭套接字
        # 关闭accept返回的套接字 意味着 不会在为这个客户端服务
        new_client_socket.close()
        print("已经为这个客户端服务完毕。。。。")
    #关闭服务端socket
    tcp_server_socket.close()

if __name__ == "__main__":
    main()
