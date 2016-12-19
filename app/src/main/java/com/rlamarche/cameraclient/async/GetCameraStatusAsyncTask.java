package com.rlamarche.cameraclient.async;

import android.app.Activity;
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

        loadApertures(cameraStatus);
        loadShutterSpeeds(cameraStatus);
        loadExposureModes(cameraStatus);
        loadIsos(cameraStatus);

        mCurrentActivity.setCaptureMode(CaptureMode.fromValue(cameraStatus.getCaptureMode()));
    }

    private void loadShutterSpeeds(CameraStatus cameraStatus) {
        WheelView shutterSpeedWheel = (WheelView) mCurrentActivity.findViewById(R.id.shutter_speed_wheel);
        List<String> shutterSpeeds = cameraStatus.getShutterSpeeds();
        ArrayWheelAdapter<String> shutterSpeedsAdapter =
                new ArrayWheelAdapter<>(mCurrentActivity.getApplicationContext(), shutterSpeeds.toArray(new String[shutterSpeeds.size()]));
        shutterSpeedWheel.setViewAdapter(shutterSpeedsAdapter);
        shutterSpeedWheel.setCurrentItem(shutterSpeeds.indexOf(cameraStatus.getShutterSpeed()));
    }

    private void loadApertures(CameraStatus cameraStatus) {
        WheelView apertureWheel = (WheelView) mCurrentActivity.findViewById(R.id.aperture_wheel);
        List<String> apertures = cameraStatus.getApertures();
        ArrayWheelAdapter<String> aperturesAdapter =
                new ArrayWheelAdapter<>(mCurrentActivity.getApplicationContext(), apertures.toArray(new String[apertures.size()]));
        apertureWheel.setViewAdapter(aperturesAdapter);
        apertureWheel.setCurrentItem(apertures.indexOf(cameraStatus.getAperture()));
    }

    private void loadExposureModes(CameraStatus cameraStatus) {
        WheelView isoWheel = (WheelView) mCurrentActivity.findViewById(R.id.exposure_mode_wheel);
        List<String> exposureModes = cameraStatus.getExposureModes();
        ArrayWheelAdapter<String> exposureModesAdapter =
                new ArrayWheelAdapter<>(mCurrentActivity.getApplicationContext(), exposureModes.toArray(new String[exposureModes.size()]));
        isoWheel.setViewAdapter(exposureModesAdapter);
        isoWheel.setCurrentItem(exposureModes.indexOf(cameraStatus.getExposureMode()));
    }

    private void loadIsos(CameraStatus cameraStatus) {
        WheelView isoWheel = (WheelView) mCurrentActivity.findViewById(R.id.iso_wheel);
        List<String> isos = new ArrayList<>();
        isos.add("AUTO");
        isos.addAll(cameraStatus.getIsos());
        ArrayWheelAdapter<String> isosAdapter =
                new ArrayWheelAdapter<>(mCurrentActivity.getApplicationContext(), isos.toArray(new String[isos.size()]));
        isoWheel.setViewAdapter(isosAdapter);

        if (cameraStatus.getIsoAuto() != null && cameraStatus.getIsoAuto()) {
            isoWheel.setCurrentItem(0);
        } else {
            isoWheel.setCurrentItem(isos.indexOf(cameraStatus.getIso()));
        }
    }
}
