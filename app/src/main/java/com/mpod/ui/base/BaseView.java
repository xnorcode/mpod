package com.mpod.ui.base;

/**
 * Created by xnorcode on 15/07/2018.
 */
public interface BaseView<T> {


    /**
     * Pass presenter reference to view
     *
     * @param presenter The view presenter
     */
    void setPresenter(T presenter);
}
