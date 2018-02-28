# HappyBubble
[![GitHub release](https://img.shields.io/badge/Download-demo--apk-brightgreen.svg)](https://github.com/xujiaji/HappyBubble/releases) [![GitHub release](https://img.shields.io/badge/bintray-1.1.2-brightgreen.svg)](https://bintray.com/xujiaji/maven/happy-bubble/1.1.2)

![bubble](display/img5.png)

Bubble layout change at will;

Dialog according to click View position display;

[中文文档](README-CN.md)

 [Old README（旧文档）](README-old.md)
## update
- 1.1.2: Fix default values does not adaptation screen.

- 1.1.1:Repair the size of the change, there is no response to change the location; repair contact offset the top of the problem;

- 1.1.0:<br>① Dialog interactive events passed to the Activity to achieve not without closing the dialog box,can opreate Activity.<br>② Add automatically according to click the distance from the edge of the screen to determine the location of the dialog box.<br>③Added "autoPosition" and "setThroughEvent" methods, please refer to "BubbleDialog method reference table"
![1.1.0.gif](display/1.1.0.gif)

- 1.0.3:Continue to optimize the click outside the bubble will be dismiss; fix some Dialog around Dialog can not dismiss;

- 1.0.2:Fix click on the dialog edge can not be canceled.

## How to get started?
Add HappyBubble dependency into your build.gradle
```
compile 'com.github.xujiaji:happy-bubble:1.1.2'
```

## How to use HappyBubble-BubbleDialog?
> Method reference table

|Method|Param|Description|
|:-|:-:|:-|
|addContentView|View|Fill content view|
|setClickedView|View|Clicked view|
|setPosition|enum BubbleDialog.Position:LEFT, TOP, RIGHT, BOTTOM|BubbleDialog relative to the location of the view being clicked|
|calBar|boolean|Whether to calculate the status bar|
|setOffsetX|int|If you are not satisfied with the x position, you need to adjust.|
|setOffsetY|int|If you are not satisfied with the y position, you need to adjust.|
|setBubbleLayout|BubbleLayout|Custom BubbleLayout|
|setTransParentBackground|-|Transparent background|
|softShowUp|-|When EditText gets the focus, you want it to move up.|
|show|-|display|
|autoPosition|boolean|Whether to enable the automatic position determination function is enabled, the "setPosition" function is disabled|
|setThroughEvent|boolean, boolean|The first parameter, "isThroughEvent", sets whether or not to penetrate the Dialog gesture interaction. <br>The second argument, "cancelable", clicks whether the blank can cancel Dialog, only valid if "isThroughEvent = false".|


### The easiest to achieve.
|||
|-|-|
|![exampel1](display/img_example1.png)|![exampel2](display/img_example2.png)|

> Need to provide Context, fill View, clicked View.</br>
> If the layout is not full screen then you need to calculate the status bar.

``` java
new BubbleDialog(this)
        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
        .setClickedView(mButton)
        .calBar(true)
        .show();
```
### Off 8dp down.
![exampel3](display/img_example3.png)
``` java
new BubbleDialog(this)
        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
        .setClickedView(mButton4)
        .setPosition(mPosition)
        .setOffsetY(8)
        .calBar(true)
        .show();
```
### When the input box is covered by the keyboard.
![exampel4](display/gif_example4.gif)
``` java
new BubbleDialog(this)
        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view, null))
        .setClickedView(mButton12)
        .setPosition(mPosition)
        .calBar(true)
        .softShowUp()
        .show();
```
### Custom BubbleLayout.
![exampel5](display/img_example5.png)

``` java
BubbleLayout bl = new BubbleLayout(this);
bl.setBubbleColor(Color.BLUE);
bl.setShadowColor(Color.RED);
bl.setLookLength(Util.dpToPx(this, 54));
bl.setLookWidth(Util.dpToPx(this, 48));
new BubbleDialog(this)
        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view5, null))
        .setClickedView(mButton8)
        .setPosition(mPosition)
        .calBar(true)
        .setBubbleLayout(bl)
        .show();
```
### Custom BubbleDialog, actionable BubbleDialog.
![exampel6](display/gif_example6.gif)
> 1.layout

``` xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="160dp"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/button13"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button1" />

    <Button
        android:id="@+id/button14"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button2" />

    <Button
        android:id="@+id/button15"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button3" />

</LinearLayout>
```
> 2.Custom BubbleDialog

``` java

/**
 * 自定义可操作性dialog
 * Created by JiajiXu on 17-12-11.
 */

public class CustomOperateDialog extends BubbleDialog implements View.OnClickListener
{
    private ViewHolder mViewHolder;
    private OnClickCustomButtonListener mListener;

    public CustomOperateDialog(Context context)
    {
        super(context);
        calBar(true);
        setTransParentBackground();
        setPosition(Position.TOP);
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_view4, null);
        mViewHolder = new ViewHolder(rootView);
        addContentView(rootView);
        mViewHolder.btn13.setOnClickListener(this);
        mViewHolder.btn14.setOnClickListener(this);
        mViewHolder.btn15.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (mListener != null)
        {
            mListener.onClick(((Button)v).getText().toString());
        }
    }

    private static class ViewHolder
    {
        Button btn13, btn14, btn15;
        public ViewHolder(View rootView)
        {
            btn13 = rootView.findViewById(R.id.button13);
            btn14 = rootView.findViewById(R.id.button14);
            btn15 = rootView.findViewById(R.id.button15);
        }
    }

    public void setClickListener(OnClickCustomButtonListener l)
    {
        this.mListener = l;
    }

    public interface OnClickCustomButtonListener
    {
        void onClick(String str);
    }
}

```

> 3.display

``` java
CustomOperateDialog codDialog = new CustomOperateDialog(this)
        .setPosition(mPosition)
        .setClickedView(mButton10);
codDialog.setClickListener(new CustomOperateDialog.OnClickCustomButtonListener()
{
    @Override
    public void onClick(String str)
    {
        mButton10.setText("点击了：" + str);
    }
});
codDialog.show();
```
### See more code.
[TestDialogActivity code](app/src/main/java/com/xujiaji/happybubbletest/TestDialogActivity.java)

### Code advice
According to [@hm] (https://juejin.im/user/57bda1ada633bd005d4bc2a9) the friend in the [article] (https://juejin.im/post/5a333f0af265da431523f408) feedback, multiple clicks Show BubbleDialog, the location is not correct problem. Due to multiple settings BappyDialog lead, it is recommended that the following wording. (Of course, if you need to set a different clicked control to repeatedly call the setClickedView () method to update the location, you need to write it out.)

``` java
if(mBubbleDialog == null)
{
    mBubbleDialog = new BubbleDialog(this)
        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
        .setClickedView(mButton4)
        .setPosition(mPosition)
        .setOffsetY(8)
        .calBar(true);
}
mBubbleDialog.show();
```


---


## How to use HappyBubble-BubbleLayout?
### Define attributes in XML code.
> Attributes reference table

|Attrs|Value|Description|
|:-|:-:|:-|
|lookAt|left, top, right, bottom|Arrow pointing|
|lookLength|dimension|Arrow length|
|lookPosition|dimension|Arrow relative x or y axis position|
|lookWidth|dimension|Arrow width|
|bubbleColor|color|Bubble color|
|bubbleRadius|dimension|Bubble arc|
|bubblePadding|dimension|Bubble border to content distance|
|shadowRadius|dimension|Shadow radius|
|shadowX|dimension|Shading offset in the x-axis|
|shadowY|dimension|Shading offset in the y-axis|
|shadowColor|color|Shades of color|

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

![GIF](display/gif1.gif)

### demo download.
[![GitHub release](https://img.shields.io/badge/Download-demo--apk-brightgreen.svg)](https://github.com/xujiaji/HappyBubble/releases)

---

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
