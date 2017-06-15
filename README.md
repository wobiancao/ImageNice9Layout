>写在开头：

为什么要实现这么个功能，当然不是我闲得慌，当然是产品的需求。身为码农你只能想方设法去实现，即使留给你的时间已经不多了，想起一句话：这个需求很简单，怎么实现我不管，月底上线

![警察.png](http://upload-images.jianshu.io/upload_images/1216032-680fc083ad9d4b8a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>看看产品需要的什么吧


![image.png](http://upload-images.jianshu.io/upload_images/1216032-7cc47a8c9537487e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

效果图太多就不杀猫了，直接上之前实现的demo效果图吧


![demo.gif](http://upload-images.jianshu.io/upload_images/1216032-cf847b6bce96d784.gif?imageMogr2/auto-orient/strip)


>分析Or撕逼


初一看，恩9种样式，宫格布局的，这个应该很简单，还要实现拖拽，
RecyclerView + GridLayoutManager设置spanSize + ItemTouchHelper 一波带走；
再一看，我擦这3张的和6张的怎么这么是这样的？

![3.png](http://upload-images.jianshu.io/upload_images/1216032-c7698630649b9884.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![6.png](http://upload-images.jianshu.io/upload_images/1216032-5d800b799525789d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

左边的好说，右边的喂？？有点类似瀑布流的样子

![头大.png](http://upload-images.jianshu.io/upload_images/1216032-c8c4763374bcde45.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后，大话说出去了，解决呗。猫：然而事情并没有这么简单

![coder.png](http://upload-images.jianshu.io/upload_images/1216032-327f757b2353fdbc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
>实现步骤Or踩坑过程


首先，时间上，规定时间需要上线版本，这个布局留给我的时间有且仅有充裕的1天，对于实现过类似功能的人来说，一天确实很充裕；

其次，功能上，逻辑并不复杂，条理也很清晰，就是9张图，9种排列方式，用到的地方两处：

1）.发布的时候需要拖拽，

2）显示详情的时候需要展示，不能拖拽；

方案有：

1.写9种静态布局;

2.addview的方式动态添加布局;

3.万能的recyclerView

最后排除1、2方案，采用方案3

时间上，自定义LayoutManager可能来不及，不知里面有什么坑，只好去找轮子

![车技.jpg](http://upload-images.jianshu.io/upload_images/1216032-4c35b66a2eecc47b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

让我们看看有什么轮子：

[FreeSizeDraggableLayout](https://github.com/alivebao/FreeSizeDraggableLayout)

[Android 布局之GridLayout](http://www.cnblogs.com/skywang12345/p/3154150.html)

我发现这两个，都不是我想要的,具体可去看源码和实现

然后又找到两个关于自定义recyclerview的库

[two-way](https://github.com/lucasr/twoway-view)

[vlayout](https://github.com/alibaba/vlayout)

找到以上库的时候，半天已经过去，只剩下半天“充裕”的时间了

![fuck.png](http://upload-images.jianshu.io/upload_images/1216032-9c577e3dc55809bd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

当时导入two-way库的时候出现了问题，一直build不起，只好选择vlayout，毕竟时间不等人；

上面的demo.gif是用vlayout实现的;后面我试了two-way库，也实现了这个效果，喜欢的朋友可以去试试

>贴上最终实现效果：

![列表.gif](https://github.com/wobiancao/ImageNice9Layout/blob/master/screenshot/list9.gif)

![拖拽.gif](https://github.com/wobiancao/ImageNice9Layout/blob/master/screenshot/drag9.gif)

 拖拽动画不是很理想，希望有小伙伴能提点意见怎么修改这个拖拽动画，我简单的把这个控件封装了一下，便于以后使用
 
>使用方法

1.git clone 或下载本控件，修改响应版本然后依赖
  `compile project(':imagenice9lib')`
	
2.属性：
  `app:nice9_itemMargin="5dp"//每个图片之间的间距
	
   app:nice9_candrag="false"//是否支持拖拽，默认false`
	 
3.Item点击接口:

`mImageNice9Layout.setItemDelegate(new ImageNice9Layout.ItemDelegate());`

4.使用，直接xml布局就行：

  ` <wobiancao.nice9.lib.ImageNice9Layout
  
        android:id="@+id/item_nice9_image"
	
        android:orientation="vertical"
	
        android:layout_width="match_parent"
	
        android:layout_height="wrap_content"
	
        android:layout_marginTop="8dp"
	
        app:nice9_itemMargin="5dp"
	
        app:nice9_candrag="false"/>`
				


[简书地址](http://www.jianshu.com/p/0ea96b952170)

如果觉得本文或本库对您有所帮助，就点个star吧？
[体验apk](https://fir.im/nice9)

![emmm.png](http://upload-images.jianshu.io/upload_images/1216032-512efea90ab7705b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>感谢

[vlayout](https://github.com/alibaba/vlayout)

[pixabay](https://pixabay.com/)

[glide](https://github.com/bumptech/glide)
