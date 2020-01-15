# FSkin 



> 一个通过动态加载外部皮肤包的组件

## 项目介绍

没有任何侵入性，接入只需一行代码




## 演示
#### 1. 下载[demo](https://github.com/fengjundev/Android-Skin-Loader/tree/master/skin-package), 将`BlackFantacy.skin`放在SD卡根目录
#### 2. 效果图
- 换肤前
![sample](./screenshot/demo1.gif)

- 换肤后
![sample](./screenshot/demo2.gif)


## 用法

#### 1. 在`Application`中进行初始化

```java

public class YourApplication extends Application {
	public void onCreate() {
		super.onCreate();
		SkinManager.getInstance().init(this);
	}
}
```


#### 2. 在布局文件中标识需要换肤的View

```xml
...
xmlns:skin="http://schemas.android.com/android/skin"
...
  <TextView
     ...
     skin:enable="true" 
     ... />
```

#### 3. 继承`BaseActivity`或者`BaseFragmentActivity`作为BaseActivity进行开发
  
  
#### 4. 从`.skin`文件中设置皮肤
```java
String SKIN_NAME = "BlackFantacy.skin";
String SKIN_DIR = Environment.getExternalStorageDirectory() + File.separator + SKIN_NAME;
File skin = new File(SKIN_DIR);
SkinManager.getInstance().load(skin.getAbsolutePath(),
				new ILoaderListener() {
					@Override
					public void onStart() {
					}

					@Override
					public void onSuccess() {
					}

					@Override
					public void onFailed() {
					}
				});
```

#### 5. 重设默认皮肤
```java
SkinManager.getInstance().restoreDefaultTheme();
```

#### 6. 对代码中创建的View的换肤支持
主要由`IDynamicNewView`接口实现该功能，在`BaseActivity`，`BaseFragmentActivity`和`BaseFragment`中已经实现该接口.

```java
public interface IDynamicNewView {
	void dynamicAddView(View view, List<DynamicAttr> pDAttrs);
}
```
**用法：**动态创建View后，调用`dynamicAddView`方法注册该View至皮肤映射表即可(如下).详见sample工程

```java
	private void dynamicAddTitleView() {
		TextView textView = new TextView(getActivity());
		textView.setText("Small Article (动态new的View)");
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param.addRule(RelativeLayout.CENTER_IN_PARENT);
		textView.setLayoutParams(param);
		textView.setTextColor(getActivity().getResources().getColor(R.color.color_title_bar_text));
		textView.setTextSize(20);
		titleBarLayout.addView(textView);
		
		List<DynamicAttr> mDynamicAttr = new ArrayList<DynamicAttr>();
		mDynamicAttr.add(new DynamicAttr(AttrFactory.TEXT_COLOR, R.color.color_title_bar_text));
		dynamicAddView(textView, mDynamicAttr);
	}
```



#### 7. 皮肤包是什么？如何生成？
- 皮肤包（后缀名为`.skin`）的本质是一个apk文件，该apk文件不包含代码，只包含资源文件
- 在皮肤包工程中（示例工程为`skin/BlackFantacy`）添加需要换肤的同名的资源文件，直接编译生成apk文件，再更改后缀名为`.skin`j即可（防止用户点击安装）
- 使用gradle的同学，build`android-skin-loader-skin`工程后即可在`skin-package`目录下取皮肤包（修改脚本中`def skinName = "BlackFantacy.skin"`换成自己想要的皮肤名）


---


## License

    Copyright [2015] [FENGJUN]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

