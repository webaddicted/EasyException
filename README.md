# ErrorHandle
Handle runtime  exception.


# Use Library

**Step 1**

Initialize library in application class

    TAErrorHandler.init(this);
    TAErrorHandler.AlertType(AlertType.POP_UP);//, ErrorEnable.ENABLE);

**AlertType**

    POP_UP,
    TOAST,
    SNACKBAR,
    NONE

**Step 2**

     TAErrorHandler.handler(this, exp);

# ScreenShot
<img src="https://github.com/webaddicted/EasyException/blob/master/screenshot/home.png" width="400">   <img src="https://github.com/webaddicted/EasyException/blob/master/screenshot/exception.png" width="400">

<!--
![demo](https://github.com/webaddicted/EasyException/blob/master/screenshot/home.png)
![demo](https://github.com/webaddicted/EasyException/blob/master/screenshot/exception.png)
-->


