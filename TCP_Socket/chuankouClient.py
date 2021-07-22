# -*- codeing = utf-8 -*-
# @Time : 2021/5/23 22:20
# @Author : LHJ
# @File : chuankouClient.py
# @Sotfware : PyCharm
import serial

# 打开串口
serialPort = "COM1"  # 串口
baudRate = 9600  # 波特率
ser = serial.Serial(serialPort, baudRate, timeout=0.5)
#print("参数设置：串口=%s ，波特率=%d" % (serialPort, baudRate))

# 收发数据
#while 1:
str = input("请输入要发送的数据: ")
#由于在客户端使用的readLine()来读取用户输入，所以当用户按下回车键是，readLine() 返回读取内容，
#但此时返回的内容并不包含换行符（查看readLine()源码，会将\r，\n或者\r\n去除掉），而当在服务器端用readLine()再次读取时，
#由于读取的内容没有换行符，所以readLine()方法会一直阻塞等待换行符，这就是服务器端没有输出的原因,所以这里一定要加上"\n"
ser.write((str+" "+"\n").encode("utf-8"))
print("发送成功！")
ser.close()
