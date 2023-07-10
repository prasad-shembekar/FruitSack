#!D:\Programs\Python3.10\python
import mysql.connector as mycon

def connect() : 
    #con=mycon.connect(host='localhost',user='root',password='crosspolo',database='fruitqualitypredDB')
    con=mycon.connect(host='bhgst1pbhtweryaf9yje-mysql.services.clever-cloud.com',user='ucgxw7r1kroqfofr',password='j6vXyV03OPORtZJaxVWj',database='bhgst1pbhtweryaf9yje')
    return con