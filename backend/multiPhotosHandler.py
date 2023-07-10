#!D:\Programs\Python3.10\python

import shutil
import cgi, os
from FunFactory import *
from DBInsertion import *
from Threshold import *
UPLOAD_DIR=os.getcwd()+"\\DataSet\\temp\\" 
UPLOAD_DIR1=os.getcwd()+"\\DataSet" 
print("""\
Content-Type: text/html\n
<html>
<body>

""")


form = cgi.FieldStorage()
title=form.getvalue("title") 
category=form.getvalue("category")
try:
   os.mkdir(UPLOAD_DIR+"/"+title) 
except FileExistsError:
   print("directory exist")
try:
    os.mkdir(UPLOAD_DIR1+"/"+title+"/") 
except FileExistsError:
    print("directory exist") 
try:
    os.mkdir(UPLOAD_DIR1+"/"+title+"/train/") 
except FileExistsError:
    print("directory exist")
try:
    os.mkdir(UPLOAD_DIR1+"/"+title+"/test/") 
except FileExistsError:
    print("directory exist") 
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
      with open(UPLOAD_DIR+"/"+title+"/" + filename, 'wb') as f:
         print("saved")
         shutil.copyfileobj(fileitem.file, f)
      print("saved")
      print("filename="+filename +" "+category+" "+title)
      #insertCarPartPhotos(docid1,title,docid,userid,fn,dt,tm,category)
      img_preprocessing1(filename,category,title)
 
print("<html>")
print("<head>")
print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/datasetInsrtPython?sts=success'/>")
print("</head>")
print("</html>")