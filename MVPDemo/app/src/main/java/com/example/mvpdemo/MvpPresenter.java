package com.example.mvpdemo;

public class MvpPresenter {

    private MvpView mMvpView;

    public MvpPresenter(MvpView view) {
        this.mMvpView = view;
    }

    public void getData(String params) {
        //显示进度条
        mMvpView.showLoading();

        MvpModel.getData(params, new Callbak() {
            @Override
            public void onSuccess(String data) {
                mMvpView.showData(data);
            }

            @Override
            public void onFailure(String data) {
                mMvpView.showFailureMessage(data);
            }

            @Override
            public void onError(String data) {
                mMvpView.showErrorMessage();
            }

            @Override
            public void onComplete() {
                mMvpView.hidLoading();
            }
        });
    }

}
