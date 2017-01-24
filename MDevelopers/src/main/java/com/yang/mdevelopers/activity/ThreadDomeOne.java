package com.yang.mdevelopers.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.yang.mdevelopers.R;


public class ThreadDomeOne extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_dome_one);
        SyncStack mSyncStack = new SyncStack();

        new Thread(new Consume(mSyncStack)).start();
        new Thread(new Producer(mSyncStack)).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.thread_dome_one_menu, menu);
        return true;
    }

    class StreamBread {
        int id; //馒头编号

        void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "streamBread:" + id;
        }
    }

    //装馒头的框，栈结构
    class SyncStack {
        int index = 0;
        StreamBread[] stb = new StreamBread[6]; // 构造馒头数组，相当于馒头框，容量6

        //放入框中，相当于入栈
        public synchronized void push(StreamBread sb) {
            while (index == stb.length) { //筐满了，即栈满
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            sb.setId(index);
            this.notify(); //唤醒在此对象监视器上等待的单个线程，消费者
            stb[index] = sb;
            this.index++;
        }

        //从框中拿出，相当于出栈
        public synchronized StreamBread pop() {
            while (index == 0) {//筐空了，即栈空
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            this.notify();
            this.index--;//push第n个之后，this.index++，使栈顶为n+1，故return之前要减一
            return stb[index];
        }

}


    //生产类
    class Producer implements Runnable {

        SyncStack ss = null;

        Producer(SyncStack ss) {
            this.ss = ss;
        }


        @Override
        public void run() {
            //开始生产馒头
            for(;;) {
                StreamBread std = new StreamBread();
                ss.push(std);
                System.out.println("生产后总数：" +std);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //消费类

    class Consume implements Runnable {
        SyncStack ss = null;

        Consume(SyncStack ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            while (true) {
                StreamBread sb = ss.pop();
                System.out.println("消费后的总数："+ sb);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
