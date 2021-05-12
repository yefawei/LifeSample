# LifeSample

>  以一种更简单的方式展现官方 `Lifecycle` `LiveData` 的基本实现原里

##### 说明

该项目将  `Lifecycle` 和 `LiveData` 的原理以简单的逻辑展现出来，与官方的相比，线程不保证安全，`LiveData` 共享资源、数据版本等都是有所缺失的，仅仅以更简单的代码实现它们的核心功能

##### 原理版 `Lifecycle` 使用

1. 将 `Activity` 继承至 `BaseActivity` 或 将 `Fragment` 继承至 `BaseFragment`

2. 声明生命周期监听者：

   ```kotlin
   private val onLifecycle = object : AbsLifecycle() {
       override fun onViewLifeStateChange(state: LifeState) {
           Log.e("onLifecycle", state.name)
       }
   }
   ```

3. 在合适的时机将监听者添加

   ```kotlin
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
       getLifecycleProvider().addLifeListener(onLifecycle)
   }
   // or
   override fun onStart() {
       super.onStart()
       getLifecycleProvider().addLifeListener(onLifecycle)
   }
   ```

4. 查看生命周期日志

   ```
   E/onLifecycle: CREATE
   E/onLifecycle: START
   E/onLifecycle: RESUME
   E/onLifecycle: PAUSE
   E/onLifecycle: STOP
   E/onLifecycle: DESTORY
   ```

##### 原理版 `LiveData` 使用

1. 将需要观察的数据对象使用 `DataListenContainer` 声明

   ```kotlin
   val name = DataListenContainer<String>()
   ```

2. 在合适的时机对可观察数据进行监听

   ```kotlin
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
       name.addListener(this) {
           Log.e("DataListener", "change: $it")
       }
   }
   ```

3. 当 `name` 的值发生变化时，就会自动触发该回调

4. 当界面不可见时回调不会发生





