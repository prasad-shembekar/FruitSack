from DBConnect import *
 
 
 
def deletedataset() : 
     
    conn = connect()    
    mycursor = conn.cursor()
    sql = "delete from dataset"
    print(sql)
    mycursor.execute ( sql )

    conn.commit ( ) 
def getValues() : 
     
    conn = connect()    
    mycursor = conn.cursor()
    sql = "select from dataset"
    print(sql)
    mycursor.execute ( sql )

    conn.commit ( ) 

 
def insertDataset(keywDic) : 
     
    conn = connect()    
    mycursor = conn.cursor()
    sql = "INSERT into dataset(imgPath,title) VALUES (%s, %s)"
    
    mycursor.executemany ( sql, keywDic )

    conn.commit ( )

    #print ( mycursor.rowcount, "was inserted." )
    conn.commit()


def insertLabels() :  
    conn = connect()    
    cursor = conn.cursor() 
    args1=cursor.callproc('insertTitle')
    conn.commit()
def getDictionary() :  
    conn = connect()    
    cursor = conn.cursor() 
    cursor.execute("select srNo as key1,title as val from labels")
    out = cursor.fetchall()
    variable = {key:val for key,val in out}
    print(variable)
    conn.commit()
    return variable
def getLabelCount() :  
    conn = connect()    
    cursor = conn.cursor() 
    cursor.execute("select count(*) as cnt from labels")
    out = cursor.fetchall() 
    print(out[0][0])
    conn.commit()
    return int(str(out[0][0]).strip())
 