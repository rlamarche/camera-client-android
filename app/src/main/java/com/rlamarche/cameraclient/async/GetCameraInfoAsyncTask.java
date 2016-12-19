package com.rlamarche.cameraclient.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.rlamarche.cameraclient.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.api.CameraApi;
import io.swagger.client.model.CameraInfo;

/**
 * Created by romain on 18/12/16.
 */

public class GetCameraInfoAsyncTask extends AsyncTask<Void, Void, CameraInfo> {

    private CameraApi mCameraApi = new CameraApi();

    private Activity mCurrentActivity;

    public GetCameraInfoAsyncTask(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    protected CameraInfo doInBackground(Void... voids) {
        try {
            CameraInfo cameraInfo = mCameraApi.infoGet();

            return cameraInfo;
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

    @Override
    protected void onPostExecute(CameraInfo cameraInfo) {
        super.onPostExecute(cameraInfo);
    }
}
