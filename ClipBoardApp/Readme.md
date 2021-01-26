## ClipBoardApp 剪贴板小工具

### 基本结构 structure of this tool
> font-end: FileCenter.html

> backend: FileCenter.java

base on **Ajax** and **SpringBoot**




### 核心功能 Main Function

- Display a clip-board in fontend textbox
```java
/clipboard    // display content 
/save         // save the content from fontend
```


- Manage static files stored in server

```java
/filelist     // list files in server side
/upfile       // upload a single file
/del          // delete a single file
/getFile      // download a single file
```


### 启动类 Start
- Main Entrance:
    - com.etsoft.ETEnglish.App
- Url to use it:
    - http://localhost:8080/FileCenter.html
