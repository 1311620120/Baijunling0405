package com.example.baijunling0405.presenter;

import com.example.baijunling0405.model.Contante;
import com.example.baijunling0405.model.HttpUitls;
import com.example.baijunling0405.model.ShowJson;
import com.example.baijunling0405.view.fragment.OneFragment;
import com.example.baijunling0405.view.interfaces.IMainView;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 08:43:01
 * @Description:
 */
public class ShowPresenter extends BasePresenter<IMainView<ShowJson>> {

    private final HttpUitls instance;

    public ShowPresenter(OneFragment oneFragment){
        instance = HttpUitls.getInstance();
     }

      public void ShowData(){
                 instance.getData(Contante.SHOW, ShowJson.class, new HttpUitls.CallBackData() {
                     @Override
                     public void onResponse(Object o) {
                        getView().onSuccess((ShowJson)o);
                     }

                     @Override
                     public void onFail(String err) {

                     }
                 });
      }
}
