package com.example.baijunling0405.view.interfaces;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 08:41:23
 * @Description:
 */
public interface IMainView<T> {

     void  onSuccess(T t);
     void  onFail(String err);
}
