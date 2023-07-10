#!C:\Users\Megha\AppData\Local\Programs\Python\Python310\python
# Import modules for CGI handling
import cgi, cgitb, jinja2 
import numpy as np
import cv2
import tensorflow as tf
import keras
from keras.preprocessing.image import ImageDataGenerator
import tensorflow as tf
import os
from DBOperations import *
from Threshold import preprocessInput

def Prediction(filenm="NA",title="NA"):
    UPLOAD_DIR_Model=os.getcwd()+"\\DataSet\\"+title+"\\model\\best_model_dataflair3.h5"
    model = keras.models.load_model(UPLOAD_DIR_Model)
    word_dict=getDictionary()
    background = None
    accumulated_weight = 0.5

    ROI_top = 100
    ROI_bottom = 300
    ROI_right = 150
    ROI_left = 350

    thresholded =  preprocessInput(filenm)
    
    #thresholded = cv2.cvtColor(thresholded, cv2.COLOR_GRAY2RGB)
    #thresholded = np.reshape(thresholded, (1,thresholded.shape[0],thresholded.shape[1],3))
    """
    pred = model.predict(thresholded, verbose=0)
    print(pred)
    print(np.argmax(pred))
    print(word_dict[np.argmax(pred)]) 
"""            



    UPLOAD_DIR=os.getcwd()+"\\InputImg\\temp\\"  
    test_batches = ImageDataGenerator(preprocessing_function=tf.keras.applications.vgg16.preprocess_input).flow_from_directory(directory=UPLOAD_DIR, target_size=(64,64), class_mode='categorical', batch_size=1, shuffle=True)
    imgs, labels = next(test_batches)
    predictions = model.predict(imgs, verbose=0)
    print(predictions)
    print(word_dict[np.argmax(predictions)])
    cv2.destroyAllWindows()
    return word_dict[np.argmax(predictions)]
