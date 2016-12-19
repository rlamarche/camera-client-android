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

public class PostAutofocusAsyncTask extends AsyncTask<PostAutofocusAsyncTask.AutofocusPoint, Void, Void> {

    public static class AutofocusPoint {
        public AutofocusPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        final int x;
        final int y;
    }

    private CameraApi mCameraApi = new CameraApi();

    private FullscreenActivity mCurrentActivity;

    public PostAutofocusAsyncTask(FullscreenActivity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    protected Void doInBackground(AutofocusPoint... autofocusPoints) {
        try {
            for (AutofocusPoint autofocusPoint : autofocusPoints) {
                mCameraApi.autofocusPost(autofocusPoint.x, autofocusPoint.y);
            }

            // return the last settings
            return null;
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
