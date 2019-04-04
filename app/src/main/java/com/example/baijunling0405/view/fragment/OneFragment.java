package com.example.baijunling0405.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.baijunling0405.R;
import com.example.baijunling0405.model.ShowJson;
import com.example.baijunling0405.presenter.ShowPresenter;
import com.example.baijunling0405.view.adapter.MyAdapter;
import com.example.baijunling0405.view.interfaces.IMainView;

import java.util.List;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 10:11:14
 * @Description:
 */
public class OneFragment extends Fragment implements IMainView {

    private View view;
    private ExpandableListView expean;
    private CheckBox checkAll;
    private TextView priceAll;
    private ShowPresenter showPresenter;
    private MyAdapter myAdapter;
    private ShowJson showJson;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.onefragment, null);
        initView();
        initData();
        return view;
    }

    private void initView() {
        expean = view.findViewById(R.id.expean);
        checkAll = view.findViewById(R.id.CheckAll);
        priceAll = view.findViewById(R.id.priceAll);

    }

    private void initData() {
        showPresenter = new ShowPresenter(this);
        showPresenter.ShowData();
        showPresenter.setView(this);

         checkAll.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (checkAll.isChecked()){
                     setCheckAll(true);
                 }else {
                     setCheckAll(false);
                 }
                 myAdapter.notifyDataSetChanged();

             }
         });
    }

    @Override
    public void onSuccess(Object o) {
        showJson = (ShowJson)o;

        myAdapter = new MyAdapter(getActivity(), showJson);
        expean.setAdapter(myAdapter);
        for (int i = 0; i < showJson.getData().size(); i++) {
           expean.expandGroup(i);
        }
    }

    @Override
    public void onFail(String err) {

    }
    public  void setCheckAll(boolean ischeck){
        List<ShowJson.DataBean> data = showJson.getData();

        for (int i = 0; i < data.size() ; i++) {
            ShowJson.DataBean dataBean = data.get(i);
            dataBean.ischeck(ischeck);
            List<ShowJson.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).ischeck(ischeck);
            }
        }
        myAdapter.notifyDataSetChanged();
    }

}
