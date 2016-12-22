# Camera client Android

A client to the Camera Toolbox server running from any android phone.

Current implemented features :
* remote connection to Camera Toolbox (Running on a RPI3 you can use it outdoor through WiFi connection -- RPI3 sd image to come)
* liveview
* change camera modes
* change aperture / shutterspeed / iso ...
* touch focus (soem interpolation problems to fix)
* capture photo
* record video
* and more to come

Working in progress...

Roadmap :
* fix touch focus
* grab camera events & download photos / videos
* helpers (histogram, ...)
* tethering mode for live broadcasting of photos
* ideas ?


Notes : Camera Toolbox / Client Android has currently only been testing with a Nikon D800 camera, but it is using libgphoto so more cameras should be supported.

Screenshot :

![alt tag](https://github.com/rlamarche/camera-client-android/blob/master/screenshot/Screenshot_2016-12-19-23-37-00.jpg?raw=true)
