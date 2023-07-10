from DBConnect import *
def insert(partId1=0,title='NA',userid="NA",cpath="NA",dt="NA",tm="NA",cate='NA',
    sts="NA") : 
    conn = connect()    
    cursor = conn.cursor()
    args = [partId1,title,userid,cpath,dt,tm,cate]
    args1=cursor.callproc('insertDataset', args)
    print("Return value:", args1)
    #for result in cursor.stored_results():
     #       print(result.fetchall())
    cnt=cursor.rowcount 
    conn.commit()
def insertPrediction(filepath='NA',orderid=0,porderid=0,category="NA") : 
    conn = connect()    
    cursor = conn.cursor()
    args = [filepath,orderid,porderid,category]
    args1=cursor.callproc('insertPrediction', args)
    print("Return value:", args1)
    #for result in cursor.stored_results():
     #       print(result.fetchall())
    cnt=cursor.rowcount 
    conn.commit()
def getSrno(cate='NA'):
    conn = connect()
    #integrated security 
    cursor = conn.cursor() 
    print("select srNo as mxid from labels where title='"+cate+"';")
    cursor.execute("select srNo as mxid from labels where title='"+cate+"';")
    mxid=0
    for row in cursor: 
        mxid=row[0]
        print(int(mxid)+1)
    return mxid 

    #args = [userid,title,docPath,docDesc,dt,tm,key]
    #args1=cursor.callproc('insertDoc', args)
    #print("Return value:", args1)
    #for result in cursor.stored_results():
    #        print(result.fetchall())
    #cnt=cursor.rowcount
    conn.commit()
    #return cnt
 
def getMaxId():
    conn = connect()
    #integrated security 
    cursor = conn.cursor() 
    cursor.execute('select (ifnull(max(imgId),1000)+1) as mxid from dataset;')
    mxid=0
    for row in cursor: 
        mxid=row[0]
        print(int(mxid)+1)
    return mxid
