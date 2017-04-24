package com.chyang.marithmetic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chyang.marithmetic.R;

import java.util.LinkedList;
import java.util.Random;

public class SingleNodeActivity extends AppCompatActivity implements View.OnClickListener {



    class NodeObj {
        int num;
        NodeObj next;
    }

    private NodeObj  mNodeHead;
    private NodeObj  mNodeEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_node);
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_print).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                NodeObj obj = new NodeObj();
                obj.num = new Random().nextInt();
                addNode(obj);
                break;
            case R.id.bt_print:
                printNode();
                break;
        }
    }


    public void addNode(NodeObj nodeObj) {
        if(mNodeHead == null) {
            mNodeHead = nodeObj;
            mNodeEnd = mNodeHead;
        } else {
            mNodeEnd.next = nodeObj;
            mNodeEnd = nodeObj;
        }
    }

    public void printNode() {
        NodeObj printNode;
        if(mNodeHead != null) {
            printNode = mNodeHead;
           do{
               System.out.println(printNode.num+"========");
               printNode = printNode.next;
           } while(printNode.next != null);
        }
    }
}
