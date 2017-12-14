# HappyBubble
[![GitHub release](https://img.shields.io/badge/Download-demo--apk-brightgreen.svg)](https://github.com/xujiaji/HappyBubble/releases) [![GitHub release](https://img.shields.io/badge/bintray-1.0.1-brightgreen.svg)](https://bintray.com/xujiaji/maven/happy-bubble/1.0.1)

![bubble](display/img5.png)

Bubble layout change at will;

Dialog according to click View position display;

[Old README](README-old.md)

## How to get started?
Add HappyBubble dependency into your build.gradle
```
compile 'com.github.xujiaji:happy-bubble:1.0.1'
```

## How to use HappyBubble-BubbleDialog?
> I am writing related doc.Stay tuned.

> Method reference table

|Description|Method|Param|
|:-|:-|:-:|
|Fill content view|addContentView|View|
|Clicked view|setClickedView|View|
|BubbleDialog relative to the location of the view being clicked|setPosition|enum BubbleDialog.Position:LEFT, TOP, RIGHT, BOTTOM|
|Whether to calculate the status bar|calBar|boolean|
|If you are not satisfied with the x position, you need to adjust.|setOffsetX|int|
|If you are not satisfied with the y position, you need to adjust.|setOffsetY|int|
|Custom BubbleLayout|setBubbleLayout|BubbleLayout|
|Transparent background|setTransParentBackground|-|
|When EditText gets the focus, you want it to move up.|softShowUp|-|
|display|show|-|

## How to use HappyBubble-BubbleLayout?
### Define attributes in XML code.
> Attributes reference table

|Description|Attrs|Value|
|:-|:-|:-:|
|Arrow pointing|lookAt|left, top, right, bottom|
|Arrow length|lookLength|dimension|
|Arrow relative x or y axis position|lookPosition|dimension|
|Arrow width|lookWidth|dimension|
|Bubble color|bubbleColor|color|
|Bubble arc|bubbleRadius|dimension|
|Bubble border to content distance|bubblePadding|dimension|
|Shadow radius|shadowRadius|dimension|
|Shading offset in the x-axis|shadowX|dimension|
|Shading offset in the y-axis|shadowY|dimension|
|Shades of color|shadowColor|color|

> xml example

``` xml
    <com.xujiaji.happybubble.BubbleLayout
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/bubbleLayout"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_margin="16dp"
        app:lookAt="left"
        app:lookLength="16dp"
        app:lookPosition="20dp"
        app:lookWidth="16dp" />
```

### Define attributes in java code.
> BubbleLayout by calling the 'set + Attr' method and invalidate method.As follows.

``` java
mBubbleLayout.setLook(BubbleLayout.Look.LEFT);
```
> See more

[MainActivity code](app/src/main/java/com/xujiaji/happybubbletest/MainActivity.java)

# License
```
   Copyright 2016 XuJiaji

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
