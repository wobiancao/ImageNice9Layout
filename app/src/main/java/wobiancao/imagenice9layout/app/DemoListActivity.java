package wobiancao.imagenice9layout.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxy on 2017/6/14.
 */

public class DemoListActivity extends AppCompatActivity {
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    ListAdapter mListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        mToolbar.setTitle("列表");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<DemoEntity> demoEntities = new ArrayList<>();
        String[] pices = getResources().getStringArray(R.array.Pictures);
        for (int i = 8; i >= 0; i--) {
            List<String> picStrings = new ArrayList<>();
            for (int i1 = 0; i1 <= i; i1++) {
                picStrings.add(pices[i1]);
            }
            demoEntities.add(new DemoEntity(picStrings));
        }
        mListAdapter = new ListAdapter(this, demoEntities);
        mRecyclerView.setAdapter(mListAdapter);

    }
}
