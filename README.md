# MemoryLeaksDemo
android内存泄露例子 
  
[匿名内部类泄露](https://github.com/BaronZ/MemoryLeaksDemo/blob/master/app/src/main/java/com/zzb/memoryleak/leaks/AnonymousClassLeakActivity.java)  
[单例引用Context类泄露](https://github.com/BaronZ/MemoryLeaksDemo/blob/master/app/src/main/java/com/zzb/memoryleak/leaks/SingletonContextLeakActivity.java)   
  
在Android中，如果你泄露了Activity实例，那么和Activity相关的对象都不会被释放。比如Activity里ImageView显示的图片，bitmap占用很大的内存都不会被回收。  
下面的图片很好的显示了内存泄露,我在一个Activity中显示一张大图片，分别看看有内存泄露和无内存泄露触发gc的情况  

下图是有内存泄露的，离开Activity后，无伦怎么触发gc，内存都不怎么会降下来  
![memory leak](https://github.com/BaronZ/MemoryLeaksDemo/blob/master/app/src/main/res/drawable-xhdpi/memory_leaks.png)
下图是没有内存泄露的，离开Activity后，触发gc，有很大的内存降下来了(bitmap)
![no leak](https://github.com/BaronZ/MemoryLeaksDemo/blob/master/app/src/main/res/drawable-xhdpi/no_leak.png)
