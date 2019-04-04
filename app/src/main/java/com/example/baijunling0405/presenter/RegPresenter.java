package com.example.baijunling0405.presenter;

import com.example.baijunling0405.model.Contante;
import com.example.baijunling0405.model.HttpUitls;
import com.example.baijunling0405.model.RegJson;
import com.example.baijunling0405.view.activiti.RegActivity;
import com.example.baijunling0405.view.interfaces.IMainView;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 08:43:01
 * @Description:
 */
public class RegPresenter extends BasePresenter<IMainView<RegJson>> {

    private final HttpUitls instance;

    public RegPresenter(RegActivity regActivity){
        instance = HttpUitls.getInstance();
     }

      public void RegData(String name,String pwd){
           instance.setData(Contante.REG, RegJson.class, name, pwd, new HttpUitls.CallBackData() {
               @Override
               public void onResponse(Object o) {
                   getView().onSuccess((RegJson)o);
               }

               @Override
               public void onFail(String err) {

               }
           });
      }
}
