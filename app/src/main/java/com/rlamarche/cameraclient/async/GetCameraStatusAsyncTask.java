package com.rlamarche.cameraclient.async;

import android.os.AsyncTask;

import com.rlamarche.cameraclient.CaptureMode;
import com.rlamarche.cameraclient.FullscreenActivity;
import com.rlamarche.cameraclient.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.api.CameraApi;
import io.swagger.client.model.CameraStatus;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * Created by romain on 18/12/16.
 */

public class GetCameraStatusAsyncTask extends AsyncTask<Void, Void, CameraStatus> {

    private CameraApi mCameraApi = new CameraApi();

    private FullscreenActivity mCurrentActivity;

    public GetCameraStatusAsyncTask(FullscreenActivity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    protected CameraStatus doInBackground(Void... voids) {
        try {
            CameraStatus cameraStatus = mCameraApi.statusGet();

            return cameraStatus;
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
    protected void onPostExecute(CameraStatus cameraStatus) {
        super.onPostExecute(cameraStatus);

        mCurrentActivity.updateStatus(cameraStatus);
    }

}
