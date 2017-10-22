package com.yarten.experiment3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yarten.sgbasic.SGBasic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initGoodsList();


    }


    private void initGoodsList()
    {
        GoodsManager.loadGoodsInfo();
        recyclerView = findViewById(R.id.recyclerview);
        GoodsListViewHelper.process(this, recyclerView);
        GoodsListViewHelper.setOnLongClickListener(recyclerView, new SGBasic.OnItemActionListener()
        {
            @Override
            public void onAction(View view, int position)
            {
                ((GoodsListViewHelper.GoodsListAdapter)recyclerView.getAdapter()).removeItem(position);
                makeToast(String.format(ID2S(R.string.removeItemMsg), position+1));
            }
        });
    }

    private void makeToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void makeToast(int id)
    {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

    private String ID2S(int id)
    {
        return getString(id);
    }
}
