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

![demo](https://github.com/webaddicted/EasyException/blob/master/screenshot/home.png)
![demo](https://github.com/webaddicted/EasyException/blob/master/screenshot/exception.png)


