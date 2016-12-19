package com.rlamarche.cameraclient.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.rlamarche.cameraclient.CaptureMode;
import com.rlamarche.cameraclient.FullscreenActivity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.api.CameraApi;
import io.swagger.client.model.CameraSettings;
import io.swagger.client.model.CameraStatus;

/**
 * Created by romain on 18/12/16.
 */

public class PostSettingsAsyncTask extends AsyncTask<CameraSettings, Void, CameraSettings> {

    private CameraApi mCameraApi = new CameraApi();

    private FullscreenActivity mCurrentActivity;

    public PostSettingsAsyncTask(FullscreenActivity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    protected CameraSettings doInBackground(CameraSettings... cameraSettingses) {
        try {
            for (CameraSettings cameraSettings : cameraSettingses) {
                mCameraApi.settingsPost(cameraSettings);
            }

            // return the last settings
            return cameraSettingses[cameraSettingses.length - 1];
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
    protected void onPostExecute(CameraSettings cameraSettings) {
        super.onPostExecute(cameraSettings);

        if (cameraSettings.getCaptureMode() != null) {
            mCurrentActivity.setCaptureMode(CaptureMode.fromValue(cameraSettings.getCaptureMode()));
        }
    }
}
