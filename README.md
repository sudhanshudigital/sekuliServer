---
#Installation Guide To Sekuli Server
---

Document created by Sudhanshu Singhal on 3-Apr-2015

Document reviewed by Ramandeep Singh on ............

---
#Prerequisites to install Sekuli Server
---
1) Sekuli IDE installation

2) Java

---
#Steps for installation
---
1) Download the Sekuli Server jar and put it
somewhere, let say into d:/mySekuliDir.

2) Open command prompt/terminal and go to the
same dir, let say d:/mySekuliDir.

3) Run the following command "java -jar sekuliServer.jar "
You will get the message 


"Spark has ignited ...
                  ...Listening on 0.0.0.0:4567"


---
#Steps for initialising the application
---
If you running the application first time you must have to provide images on which application will operate.

For this you have to do following steps :

1) Run Sekuli IDE

2) Click images of required sections\elements from the screen and store them to some location.

3) Create a tar file of all those stored image files and upload them to application using Postman tool.

4) Type the following url in postman, upload your newely created TAR file and put key as “imagesTarBall” and make a POST call.


http://xx.xx.xx.xx:4567/sekuliServer?command=initialiseImages
(Assumptions : xx.xx.xx.xx is the ip of desired machine where you want server to be run)

---
#Features currently supported by application
---

Please note that you have to use POST method to make these requests.

1) Initialising images : This is to provide all the images to application.
required parameters are :

Paramater = comamd, Value = initilaiseImages

Paramater = imagesTarBall, Value = YourTarfile.tar.gz

(Assumptions : YourTarfile.tar.gz is the name of your tar ball which contains the .png images. Please make sure the name of images you will pass in subsequent requests should be same as the name of .png files, that you kept in tar ball)

2) Clicking: This functionality is implemented for clicking on an images.
required parameters are :

Paramater = comamd, Value = click

Paramater = images1, Value = x.png

(Assumptions : x.png is the name of image that you want to be clicked)

3) Dobule Clicking: This functionality is implemented for clicking twice on an images.
required parameters are :

Paramater = comamd, Value = dobuleClick

Paramater = images1, Value = x.png

(Assumptions : x.png is the name of image that you want to be double clicked)

4) Typing: This is the functionality for typing some text.
required parameters are :

Paramater = comamd, Value = type

Paramater = images1, Value = abcde..

(Assumptions : abcde... is the name of image that you want to be typed)

5) Existing: This functionality is implemented for finding if an image exists on the screen, the special behaviour of this feature is that you don't need to do exception handling for this method, if required image is not available on the screen.
required parameters are :

Paramater = comamd, Value = exists

Paramater = images1, Value = x.png

(Assumptions : x.png is the name of image that you want to be search)

6) Finding: This feature is simmilar to exists method except that it throws a find failed exception if search pattern/image is not available on the screen.
required parameters are :

Paramater = comamd, Value = find

Paramater = images1, Value = x.png

(Assumptions : x.png is the name of image that you want to be find)

7) Finding All: This is for finding all occurrence of an image.
required parameters are :

Paramater = comamd, Value = findAll

Paramater = images1, Value = x.png

(Assumptions : x.png is the name of image that you want to be find)

8) Wait: This functionality is implemented for achieving  wait feature.
required parameters are :

Paramater = comamd, Value = wait

Paramater = images1, Value = x.png

Paramater = time, Value = Numeric value…

(Assumptions : x.png is the name of image that you want to be clicked)

9) Wait n Vanish: This is opposite feature to wait, waitVanish will wait for a  given time to appear an image on screen.
required parameters are :

Paramater = comamd, Value = waitVanish

Paramater = images1, Value = x.png

Paramater = time, Value = Numeric value…

(Assumptions : x.png is the name of image that you want to be clicked)

10) Drag n Drop: This is dragDrop feature.
required parameters are :

Paramater = comamd, Value = dragDrop

Paramater = images1, Value = x.png

Paramater = images2, Value = y.png

(Assumptions : x.png is the name of image that you want to be move into y.png image representing elemnet)

A sample url for reference : 

http://10.0.9.165:4567/sekuliServer?command=waitVanish&image1=startButton.png&time=4 



