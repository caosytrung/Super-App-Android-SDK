 This simple demo project in order to give an overview of how the super app and the mini app connect to each other. You can refer it as the base product to build your own Super-App.
 
**More detail about the SuperApp-MiniApp Intergration**: https://medium.com/@caotrung.kk/comprehensive-guide-to-creating-super-app-with-different-types-of-mini-apps-b0ff7a17b84d

## Project Structure 
<img width="1107" alt="Screenshot 2024-04-10 at 00 28 30" src="https://github.com/caosytrung/Super-App-Android-SDK/assets/17381611/0d90f90b-64d1-4a12-8add-bed0409f6264">


- SuperApp Module: The Super App that displays all mini-apps to users.
- miniAppBridge Module: provide a friendly interface to help Super to open mini-apps.
- nativeMiniAppBridge Module: Contains source code to open **Android Native** Mini Apps specifically.
- webMiniAppBridge Module: Contains source code to open **Web** Mini Apps specifically.
- demoAndroidMiniApp Module: Demo Mini App module.
- 
