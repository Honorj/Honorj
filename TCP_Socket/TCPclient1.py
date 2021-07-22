import codecs
import json
import os
import socket
import sys
import serial     #实现串口通信

def chuankou():
    ser = serial.Serial(
    port='COM2',
    baudrate=9600,
    parity=serial.PARITY_ODD, # 校验位
    stopbits=serial.STOPBITS_TWO, # 停止位
    bytesize=serial.SEVENBITS # 数据位
    )
    name = ["鞋名","码数","价格","库存"]
    dit={}
    print("正在串口接收数据....")
 #   while True:
    data = ser.readline().decode("utf-8").split(" ")
    print(data)
    #合并两个列表为字典，然后将字典写入json文件,注意转换过程中要将data的值转换成我们需要的类型，要不然写入数据库会报错
    for i in range(len(name)):
        if i==1 or i==3:
            data[i]=int(data[i])
            dit[name[i]] = data[i]
        elif i==2:
            data[i]=float(data[i])
            dit[name[i]] = data[i]
        else:
            dit[name[i]] = data[i]
    print(dit)
    #写入json文件,注意：json.dumps()方法将dict的数据转换为string数据，然后将string写入到文本中，
    # 但是json.dumps()方法会默认将其中unicode码以ascii编码的方式输入到string，所以要加上ensure_ascii=False
    with codecs.open("Data.json", "wb", "utf-8") as f:
        f.write(json.dumps(dit,ensure_ascii=False))

def main():
    # 1. 创建tcp的套接字
    tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # 2. 链接服务器
    # tcp_socket.connect(("192.168.33.11", 7890))
    server_addr = ("127.0.0.1", 8008)#服务器的地址和端口号
    try:
        tcp_socket.connect(server_addr)
    except Exception:
        print("连接服务器失败，请查看服务器是否打开！")
        sys.exit()
    while True:
        # 3. 发送数据
        print("------------------")
        print("  1、发送json文件   ")
        print("  2、退出          ")
        print("------------------")
        a=int(input())
        if a==2:
            send_data = "quit"
            tcp_socket.send(send_data.encode("utf-8"))
            break
        elif a==1:
            def file_put(filedir):
                if os.path.isfile(filedir):
                    file_name = filedir  # 指定文件名称
                    file_size = os.stat(file_name).st_size  # 计算文件大小
                    file_msg = {"name": file_name, "size": file_size}  # 构建一个json文件
                    tcp_socket.send(bytes(json.dumps(file_msg), encoding="utf-8"))  # 发送传输需要的数据
                    print("文件名: %s --> 文件大小: %s " % (file_name, file_size))
                    with open(file_name, "rb") as f:
                        for line in f:
                            tcp_socket.send(line)
                            print("文件已发送: %s" % len(line))
                        print("文件发送完成...")
            file_put("Data.json")
        else:
            print("输入错误！请重新输入!")

    tcp_socket.close()
if __name__ == "__main__":
    chuankou()
    main()
