package com.example.baijunling0405.presenter;

import com.example.baijunling0405.model.Contante;
import com.example.baijunling0405.model.HttpUitls;
import com.example.baijunling0405.model.LoginJson;
import com.example.baijunling0405.view.activiti.LoginActivity;
import com.example.baijunling0405.view.interfaces.IMainView;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 08:43:01
 * @Description:
 */
public class LoginPresenter extends BasePresenter <IMainView<LoginJson>> {

    private final HttpUitls instance;

    public LoginPresenter(LoginActivity loginActivity){
        instance = HttpUitls.getInstance();

     }

      public void LoginData(String name,String pwd){
         instance.setData(Contante.LOGIN,LoginJson.class, name,pwd, new HttpUitls.CallBackData() {
             @Override
             public void onResponse(Object o) {
                 getView().onSuccess((LoginJson)o);
             }

             @Override
             public void onFail(String err) {

             }
         });

      }
}
