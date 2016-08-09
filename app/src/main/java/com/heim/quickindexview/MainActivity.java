package com.heim.quickindexview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;

import com.heim.quickindexview.adapter.MyAdapter;
import com.heim.quickindexview.bean.Person;
import com.heim.quickindexview.util.Cheeses;
import com.heim.quickindexview.util.Util;
import com.heim.quickindexview.view.QuickIndexView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements QuickIndexView.OnLetterChangedListener {

    @Bind(R.id.lv)
    ListView       mLv;
    @Bind(R.id.qiv)
    QuickIndexView mQiv;
    private List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        MyAdapter adapter = new MyAdapter(persons);
        mLv.setAdapter(adapter);

        mQiv.setOnLetterChangedListener(this);
    }

    private void initData() {
        for (int i = 0; i < Cheeses.NAMES.length; i++) {
            persons.add(new Person(Cheeses.NAMES[i]));
        }

        //排序
        Collections.sort(persons);
    }

    @Override
    public void onLetterChange(String letter) {
        Util.showToast(MainActivity.this,letter);

        //点击索引上的字母后,listview滑到相应的索引
        for (int i = 0; i < persons.size(); i++) {
            if (TextUtils.equals(letter, persons.get(i).letter)) {
                mLv.setSelection(i);
                break;

            }
        }
    }
}
