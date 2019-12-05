package com.threadAndParse.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 代码测试：线程嵌套
 * 先开启三个线程任务：每个线程里面再调用方法
 *
 * 每调用一次方法创建一个线程池-3条线程，分配10个任务，交给这三条线程去跑
 *
 * 最后结果：同时九条线程在奔跑，至于三条父线程早就先跑了
 *
 * 父线程再加一个任务D，会等待A，B，C某个跑完让出来再执行的
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor= Executors.newFixedThreadPool(3);


        FutureTask<Integer> resultA= new FutureTask(()->{
            System.out.println("任务A");
            taskA();
           return 1;
        });

        FutureTask<Integer> resultB= new FutureTask(()->{
            System.out.println("任务B");
            taskB();
            return 1;
        });

        FutureTask<Integer> resultC= new FutureTask(()->{
            System.out.println("任务C");
            taskC();
            return 1;
        });

        FutureTask<Integer> resultD= new FutureTask(()->{
            System.out.println("任务D");
            taskD();
            return 1;
        });

        executor.submit(resultA);
        executor.submit(resultB);
        executor.submit(resultC);
        executor.submit(resultD);


        executor.shutdown();

    }

    public static void taskA() throws ExecutionException, InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(3);

        List<Integer> list =new ArrayList<>();

        List<FutureTask> futureTasks= new ArrayList<>();

        //循环创建10个线程任务
        for(int i=0;i<10;i++){
            FutureTask<Integer> result= new FutureTask(()->{
                int t=0;
                for(int j=0;j<10;j++){
                    t += j;
                }
                Thread.sleep(1000);
                return t;
            });
            futureTasks.add(result);
            executor.submit(result);
        }

        //get()方法会阻塞，所以不能放在上面的循环里
        for (int i=0;i<futureTasks.size();i++){
            System.out.println("任务A"+i+"---："+futureTasks.get(i).get());
        }
        executor.shutdown();
    }

    public static void taskB() throws ExecutionException, InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(3);

        List<Integer> list =new ArrayList<>();

        List<FutureTask> futureTasks= new ArrayList<>();

        //循环创建10个线程任务
        for(int i=0;i<10;i++){
            FutureTask<Integer> result= new FutureTask(()->{
                int t=0;
                for(int j=0;j<9;j++){
                    t += j;
                }
                Thread.sleep(1000);
                return t;
            });
            futureTasks.add(result);
            executor.submit(result);
        }

        //get()方法会阻塞，所以不能放在上面的循环里
        for (int i=0;i<futureTasks.size();i++){
            System.out.println("任务B"+i+"---："+futureTasks.get(i).get());
        }
        executor.shutdown();
    }

    public static void taskC() throws ExecutionException, InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(3);

        List<Integer> list =new ArrayList<>();

        List<FutureTask> futureTasks= new ArrayList<>();

        //循环创建10个线程任务
        for(int i=0;i<10;i++){
            FutureTask<Integer> result= new FutureTask(()->{
                int t=0;
                for(int j=0;j<8;j++){
                    t += j;
                }
                Thread.sleep(1000);
                return t;
            });
            futureTasks.add(result);
            executor.submit(result);
        }

        //get()方法会阻塞，所以不能放在上面的循环里
        for (int i=0;i<futureTasks.size();i++){
            System.out.println("任务C"+i+"---："+futureTasks.get(i).get());
        }
        executor.shutdown();
    }

    public static void taskD() throws ExecutionException, InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(3);

        List<Integer> list =new ArrayList<>();

        List<FutureTask> futureTasks= new ArrayList<>();

        //循环创建10个线程任务
        for(int i=0;i<10;i++){
            FutureTask<Integer> result= new FutureTask(()->{
                int t=0;
                for(int j=0;j<7;j++){
                    t += j;
                }
                Thread.sleep(1000);
                return t;
            });
            futureTasks.add(result);
            executor.submit(result);
        }

        //get()方法会阻塞，所以不能放在上面的循环里
        for (int i=0;i<futureTasks.size();i++){
            System.out.println("任务D"+i+"---："+futureTasks.get(i).get());
        }
        executor.shutdown();
    }
}
