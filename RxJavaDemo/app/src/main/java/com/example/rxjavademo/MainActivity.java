package com.example.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private String TAG = "Lpp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        fun1(); //基本用法

        //      操作符部分
//        fun2(); //创建操作符

//        fun3(); //变换操作符

//        fun4(); //过滤操作符

//        fun5(); //组合操作符

//        fun6(); //辅助操作符

//        fun7(); //错误处理操作符

//        fun8(); //  条件操作符

//        fun9(); // 转换操作符

        fun10(); // RxJava 的线程控制

    }

    private void fun10() {

        // RxJava 内置的五个线程

        Schedulers.immediate(); //直接在当前线程执行

        Schedulers.newThread(); //在新线程执行

        Schedulers.computation();   //计算用的 Scheduler 使用固定线程池大小为CPU的核数
                                    // 不能把io操作放在此线程 否则io操作的等待时间会浪费CPU

        Schedulers.trampoline();    //当我们想在当前线程执行一个任务 并不是立即时
                                    // 可以使用它入队 这个调度器会按序处理任务

        AndroidSchedulers.mainThread(); //  主线程



    }

    private void fun9() {

        // 转换操作符有
        // toList toSortedList toMap toMultiMap getIterator nest 等


        // toList 将发送的数据组合成一个List集合
        Observable.just(1, 2, 3).toList().subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                Log.d(TAG, "toSortedList: " + integers);
            }
        });

        // toSortedList 产生列表排序 若没有实现Comparable接口 会抛出一个异常 可以使用toSortedList(Func2)

        Observable.just(3, 6, 1).toSortedList().subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                Log.d(TAG, "toSortedList: " + integers);
            }
        });

        // toMap 收集原始Observable发射的所有数据项到Map(默认是HashMap)集合里面
        // 然后发射这个Map 可以提供一个生成map 可以的函数

        Observable.from(new String[]{"Lpp", "Pll", "Wss"}).toMap(new Func1<String, Character>() {
            @Override
            public Character call(String s) {
                return s.charAt(0);
            }
        }).subscribe(new Action1<Map<Character, String>>() {
            @Override
            public void call(Map<Character, String> characterStringMap) {
                Log.d(TAG, "toMap: " + characterStringMap);
            }
        });

    }

    private void fun8() {

        // 有两类 ：波尔操作符和条件操作符

        // 布尔操作符有all contains isEmpty exists sequenceEqual

        // all 对发射的数据进行判断最终返回的就是结果

        // contains 判断发射的数据是否包含某一个数据 包含返回true

        // isEmpty 判断是否发射过数据

        Observable.just(1, 2, 3).all(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                Log.d(TAG, "all call: " + integer);
                return integer > 2;
            }
        }).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.d(TAG, "onNext all : " + aBoolean);
            }
        });

        // 条件操作符 有
        // amb defaultEmpty skipUntil skipWhile takeUntil takeWhile

        //  amb 对于给定的多个Observable 他只发射首先发射数据的那个Observable的所有数据

        // defaultEmpty 用于发射一个默认数据

        Observable.amb(Observable.just(1, 2, 3).delay(200, TimeUnit.MILLISECONDS), Observable
                .just(4, 5, 6)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "amb: " + integer);
            }
        });


    }

    private void fun7() {

        // catch操作符有以下几个

        // onErrorReturn
        //  遇到错误时返回原有的Observable行为的备用Observable 备用Observable会忽略原有Observable的onError调用
        //  作为替代 他会发射一个特殊的项并调用onCompleted方法
        // onErrorResumeNext
        //  遇到错误时返回原有的Observable行为的备用Observable 备用Observable会忽略原有Observable的onError调用
        //  作为替代 他会发射备用的Observable的数据
        //onExceptionResumeNext
        //  和onErrorResumeNext类似 但是如果onError收到的Throwable不是exception他会将错误传递给观察者的onError方法
        //  不会调用备用的Observable

        // 以onErrorResumeNext为例 结果为
//        onErrorResumeNext: 0
//        onErrorResumeNext: 1
//        onErrorResumeNext: 2
//        onErrorResumeNext: 11
//        onErrorResumeNext: 12
//        onErrorResumeNext: 13

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 4; i++) {
                    if (i > 2) {
                        subscriber.onError(new Throwable("1"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(Throwable throwable) {
                return Observable.just(11, 12, 13);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "onErrorResumeNext: " + integer);
            }
        });

        // retry 操作符

        //  retry 操作符 不会将Observable的onError通知传递给观察者 
        // 会再给他一次机会完成其数据序列
        //具体的实现有retry 和 retryWhen

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if (i == 1) {
                        subscriber.onError(new Throwable("1"));
                    } else {
                        subscriber.onNext(i);
                    }
                }
                subscriber.onCompleted();
            }
        }).retry(2).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: ");
            }
        });


    }

    private void fun6() {

        //delay操作符用于延时发射
        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {

            }
        }).delay(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "delay: " + (System.currentTimeMillis() - aLong));
                    }
                });


        //Do系列操作符 为Observable的生命周期注册一个回调
        // doOnEach 每当发射一个数据就会调用他
        // doOnNext
        // doOnSubscribe    订阅时
        // doOnUnsubscribe  取消订阅
        // doOnCompleted
        // doOnError
        // doOnTerminate    Observable 终止前调用
        // finallyDo        Observable 终止后调用
        Observable.just(1, 2, 3).doOnTerminate(new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "doOnTerminate: ");
            }
        }).finallyDo(new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "finallyDo: ");

            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "doOnTerminate - finallyDo: " + integer);
            }
        });

        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.d(TAG, "Observable Thread : " + Thread.currentThread().getName());
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        });

        //  subscribeOn 指定被观察者所在的线程
        //  observeOn 指定观察者的线程
        // 执行结果是
        // D/Lpp: Observable Thread : RxNewThreadScheduler-1
        // D/Lpp: Observer Thread : 1main
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "Observer Thread : " + integer + Thread.currentThread()
                                .getName());
                    }
                });

        // timeout操作符 过了指定一段时间后没有发射任何数据会以一个onError通知终止这个Observable
        // 或执行一个备用的Observable

        Observable<Integer> observable1 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 4; i++) {
                    try {
                        Thread.sleep(i * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
            }
        }).timeout(200, TimeUnit.MILLISECONDS, Observable.just(10, 11));
        observable1.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "timeout: " + integer);
            }
        });

    }

    private void fun5() {

        Observable.just(1, 2, 3).startWith(-1, 0)   //123前加-1 0
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "startWith: " + integer);
                    }
                });

        //merge 将两个Observable 合并发射 可能出现交错
        //concat按照严格的顺序发射
        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<Integer> o2 = Observable.just(4, 5, 6);
        Observable.merge(o1, o2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "merge:" + integer);
            }
        });

        //zip 按照指定的函数变化两个Observable
        //combineLastest 操作与最近发射的数据项 下述结果为34 35 36
        Observable.zip(o1, o2, new Func2<Integer, Integer, String>() {
            @Override
            public String call(Integer integer, Integer integer2) {
                return "String : " + integer + integer2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "zip: " + s);
            }
        });

    }

    //过滤操作符
    private void fun4() {


        //过滤操作符
        //类似的还有elementAt返回指定位置的数据
        //distinct操作符去重
        //skip 过滤掉前n项
        //take 只取前n项
        Observable.just(1, 2, 3, 4)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 2;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "filter:" + integer);
            }
        });

        Observable.just(1, 2, 3, 4).ignoreElements().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            //忽略observable产生的结果 只会把onCompleted()onError(Throwable e)通知给订阅者
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: ");
            }
        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
            }
        }).throttleFirst(200, TimeUnit.MILLISECONDS)//定时发送指定时间段的第一个数据
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "throttleFirst :" + integer);
                    }
                });

        // 通过时间来限流 类似的有deounce操作符

    }

    private void fun3() {

        Observable.just("Android").map(new Func1<String, String>() { //map 变化操作符
            // 变换成一个新的Observable发射出去
            @Override
            public String call(String s) {
                return s + "Studio";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "map :" + s);
            }
        });

        List list = new ArrayList();
        list.add("12");
        list.add("34");
        list.add("56");
        Observable.from(list)       //flatMap将发送的集合转化成新的Observable集合
                //cast操作符将Observable发射的数据转化为指定数据
                //concatMap解决flatMap交叉发送的问题
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just("9999" + s);
                    }
                }).cast(String.class)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String String) {
                        Log.d(TAG, "call: flatMap:" + String.toString());
                    }
                });

        Observable.just(1, 2, 3)
                .flatMapIterable(new Func1<Integer, Iterable<Integer>>() {
                    @Override
                    public Iterable<Integer> call(Integer integer) {
                        List list = new ArrayList();
                        list.add(integer + 1);
                        return list;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "flagMapIterable:" + integer);
            }
        });

        Observable.just(1, 2, 3, 4).buffer(2)
                .subscribe(new Action1<List<Integer>>() {   //buffer将数值成组发射
                    @Override
                    public void call(List<Integer> integers) {
                        for (Integer i :
                                integers) {
                            Log.d(TAG, "buffer:" + i);
                        }
                    }
                });


    }


    private void fun2() {
        //创建操作符

        //还有defer start timer 等等
        Observable.interval(3, TimeUnit.SECONDS) //定时发送序列
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "call: long:" + aLong);
                    }
                });

        Observable.range(0, 5)
                .repeat(2)          //range相当于for循环发送一定的序列
                .subscribe(new Action1<Integer>() { // repeat 表示重复的意思
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: range + repeat" + integer);
                    }
                });

    }

    //基本实现
    private void fun1() {

        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s + "onNext——subscriber");
            }

        };

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s + "onNext——observer");
            }
        };


        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("start");
            }

        });

        Observable observable1 = Observable.just("1", "2", "3");//just操作符
        Observable observable2 = Observable.from(new String[]{"a", "b", "c"});
        observable2.subscribe(subscriber);

        observable1.subscribe(subscriber);
        observable.subscribe(subscriber);
        observable.subscribe(observer); //subscriber 是 observer 的拓展


        //不完整回调
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        });


    }
}
