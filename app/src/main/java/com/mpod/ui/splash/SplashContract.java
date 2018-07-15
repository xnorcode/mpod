package com.mpod.ui.splash;

import com.mpod.ui.base.BasePresenter;
import com.mpod.ui.base.BaseView;

/**
 * Created by xnorcode on 15/07/2018.
 */
public interface SplashContract {

    interface View extends BaseView<Presenter> {


        /**
         * start next activity
         */
        void startNextActivity();
    }

    interface Presenter extends BasePresenter<View> {


        /**
         * proceed to home activity
         */
        void proceed();
    }
}
