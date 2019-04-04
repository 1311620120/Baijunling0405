package com.example.baijunling0405.presenter;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 08:42:50
 * @Description:
 */
public class BasePresenter<V> {
      private  V view;


    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

    public void detach(){
        this.view=null;
    }
}
