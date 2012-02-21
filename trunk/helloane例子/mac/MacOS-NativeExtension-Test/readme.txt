===========
 Build方法 
===========

0. 説明
このpatchはAdobeの以下の記事のコードを最適化するものです。
ネイティブコードの実行速度が最適化前に比べて5倍くらい速くなります。

http://www.adobe.com/devnet/air/articles/hello-world-native-extensions-for-mac.html

1. オリジナルコードを取得
以下のURLからソースコードを取得して、zipを解凍してください。

http://download.macromedia.com/pub/developer/air/MacOS-NativeExtension-Test.zip

2. patchを当てる
以下のフォルダーにpatchファイルを置いて、patchコマンドを実行します。

$ mv Advanced.patch MacOS-NativeExtension-Test/NativeExtensions/01 - create swc/src/com/airrt/extensions/
$ cd MacOS-NativeExtension/NativeExtensions/01 - create swc/src/com/airrt/extensions
$ patch < Advanced.patch

$ mv TestNativeExtension.patch MacOS-NativeExtension/NativeExtensions/02 - create platform extension/mac/TestNativeExtension
$ cd MacOS-NativeExtension/NativeExtensions/02 - create platform extension/mac/TestNativeExtension
$ patch < TestNativeExtension.patch

3. ビルド
各フォルダーにあるgo.shを順番に実行してください。ネイティブコードのビルドで問題が
出ると思いますので、その際は以下の記事を参考にしてください。

http://frog-on-air.blogspot.com/2011/11/macosxane.html
