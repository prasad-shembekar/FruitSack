#!D:\Programs\Python3.10\python

import shutil
import cgi, os
from FunFactory import *
from DBInsertion import *
from Threshold import *
from prediction import Prediction
 
UPLOAD_DIR=os.getcwd()+"\\InputImg\\" 
 
print("""\
Content-Type: text/html\n
<html>
<body>

""")


form = cgi.FieldStorage()
title=form.getvalue("title") 
orderid=form.getvalue("orderid") 
porderid=form.getvalue("porderid") 
def removeDir():
    try:
        for file in os.listdir(UPLOAD_DIR):
            print(file)
            os.remove(UPLOAD_DIR+"/"+file)  
    except Exception:
        print("directory exist")
    try:
        for file in os.listdir(UPLOAD_DIR+"\\temp\\1\\"):
            print(file)
            os.remove(UPLOAD_DIR+"\\temp\\1\\"+file)  
    except Exception:
        print("directory exist") 
removeDir()
print(title) 
 
fileitem = form['file']

if 'file' in form:

   filefield = form['file']
   if not isinstance(filefield, list):
      filefield = [filefield]
    
   for fileitem in filefield:
      print("f"+fileitem.filename)
       #if fileitem.filename:
          #fn = os.path.basename(fileitem.filename)
      #docid=getMaxIdParts1()
      #print("id="+str(docid))
      #nm,ext=os.path.basename(fileitem.filename).split('.')
      #fn=str(docid1)+"_"+str(docid)+"."+ext
      #print("file")
      #print(fn)
      filename=os.path.basename(fileitem.filename)
      print(filename)
      print("file")
          # save file
      with open(UPLOAD_DIR+"/"+ filename, 'wb') as f:
         print("saved")
         shutil.copyfileobj(fileitem.file, f)
      print("saved")
      print("filename="+filename +" "+title)
      #insertCarPartPhotos(docid1,title,docid,userid,fn,dt,tm,category)
      pred=Prediction(filename,title)
      insertPrediction(filename,orderid,porderid,pred)
      removeDir()
 
print("<html>")
print("<head>")
print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/dataPrediction?orderid="+orderid+"&porderid="+porderid+"&sts=success'/>")
print("</head>")
print("</html>")
 