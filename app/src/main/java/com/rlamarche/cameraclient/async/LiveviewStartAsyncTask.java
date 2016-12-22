package com.rlamarche.cameraclient.async;

import android.os.AsyncTask;

import com.rlamarche.cameraclient.FullscreenActivity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.api.CameraApi;

/**
 * Created by romain on 18/12/16.
 */

public class LiveviewStartAsyncTask extends AsyncTask<Void, Void, Void> {

    private CameraApi mCameraApi = new CameraApi();

    private FullscreenActivity mActivity;

    public LiveviewStartAsyncTask(FullscreenActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            mCameraApi.liveviewStartPost();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        mActivity.enableLiveview();
    }
}
