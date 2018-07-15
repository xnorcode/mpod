package com.mpod.ui.base;

/**
 * Created by xnorcode on 15/07/2018.
 */
public interface BasePresenter<T> {


    /**
     * Bind view to presenter
     *
     * @param view The view of the presenter
     */
    void setView(T view);


    /**
     * Removes the view
     */
    void dropView();
}
