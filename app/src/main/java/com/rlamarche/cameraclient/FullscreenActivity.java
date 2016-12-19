package com.rlamarche.cameraclient;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegInputStream;
import com.github.niqdev.mjpeg.MjpegSurfaceView;
import com.rlamarche.cameraclient.async.CapturePhotoAsyncTask;
import com.rlamarche.cameraclient.async.CaptureStartAsyncTask;
import com.rlamarche.cameraclient.async.CaptureStopAsyncTask;
import com.rlamarche.cameraclient.async.GetCameraInfoAsyncTask;
import com.rlamarche.cameraclient.async.GetCameraStatusAsyncTask;
import com.rlamarche.cameraclient.async.PostAutofocusAsyncTask;
import com.rlamarche.cameraclient.async.PostSettingsAsyncTask;

import io.swagger.client.model.CameraSettings;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import rx.Observer;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private static final String TAG = "FullscreenActivity";

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private MjpegSurfaceView mContentView;
    private View mControlsView;
    private Button mRefreshButton;
    private Button mCaptureButton;

    private Button mCaptureModePhotoButton;
    private Button mCaptureModeVideoButton;

    private WheelView mApertureWheel;
    private WheelView mShutterSpeedWheel;
    private WheelView mExposureModeWheel;
    private WheelView mIsoWheel;



    // State fields
    private boolean mVisible;
    private CaptureMode mCaptureMode;
    private boolean mIsRecording;


    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = (MjpegSurfaceView) findViewById(R.id.fullscreen_content);
        mApertureWheel = (WheelView) findViewById(R.id.aperture_wheel);
        mShutterSpeedWheel = (WheelView) findViewById(R.id.shutter_speed_wheel);
        mExposureModeWheel = (WheelView) findViewById(R.id.exposure_mode_wheel);
        mIsoWheel = (WheelView) findViewById(R.id.iso_wheel);
        mRefreshButton = (Button) findViewById(R.id.refresh_button);
        mCaptureButton = (Button) findViewById(R.id.capture_button);

        mCaptureModePhotoButton = (Button) findViewById(R.id.capture_mode_photo);
        mCaptureModeVideoButton = (Button) findViewById(R.id.capture_mode_video);

        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadLiveview();
                readCameraInfo();
                refreshCameraStatus();
            }
        });

        mCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mCaptureMode) {
                    case PHOTO:
                        new CapturePhotoAsyncTask(FullscreenActivity.this).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                        break;
                    case VIDEO:
                        if (mIsRecording) {
                            new CaptureStopAsyncTask(FullscreenActivity.this).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                        } else {
                            new CaptureStartAsyncTask(FullscreenActivity.this).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                        }

                        break;
                }
            }
        });

        mContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    return true;
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Log.i(TAG, "DOWNTIME = " + (motionEvent.getEventTime() - motionEvent.getDownTime()));

                    if (motionEvent.getEventTime() - motionEvent.getDownTime() > 200) {
                        toggle();
                    } else {

                        float xFloat = motionEvent.getX();
                        float yFloat = motionEvent.getY();

                        int x = (int) (xFloat * 640.0f / view.getWidth());
                        int y = (int) (yFloat * 426.0f / view.getHeight());

                        Log.i(TAG, "TOUCH x = " + x + " y = " + y);
                        new PostAutofocusAsyncTask(FullscreenActivity.this).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new PostAutofocusAsyncTask.AutofocusPoint(x, y));
                    }
                    return true;
                }

                return false;
            }
        });


        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });

//        mContentView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                toggle();
//
//                return true;
//            }
//        });


        mCaptureModePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraSettings cameraSettings = new CameraSettings();
                cameraSettings.setCaptureMode(0);
                applyCameraSettings(cameraSettings);
            }
        });

        mCaptureModeVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraSettings cameraSettings = new CameraSettings();
                cameraSettings.setCaptureMode(1);
                applyCameraSettings(cameraSettings);
            }
        });

        mApertureWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wheel.requestLayout();
                ArrayWheelAdapter adapter = (ArrayWheelAdapter) wheel.getViewAdapter();
                String newAperture = String.valueOf(adapter.getItemText(newValue));

                if (newAperture != null) {
                    CameraSettings cameraSettings = new CameraSettings();
                    cameraSettings.setAperture(newAperture);
                    applyCameraSettings(cameraSettings);
                }
            }
        });

        mShutterSpeedWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wheel.requestLayout();
                ArrayWheelAdapter adapter = (ArrayWheelAdapter) wheel.getViewAdapter();
                String newShutterSpeed = String.valueOf(adapter.getItemText(newValue));

                if (newShutterSpeed != null) {
                    CameraSettings cameraSettings = new CameraSettings();
                    cameraSettings.setShutterSpeed(newShutterSpeed);
                    applyCameraSettings(cameraSettings);
                }
            }
        });

        mExposureModeWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wheel.requestLayout();
                ArrayWheelAdapter adapter = (ArrayWheelAdapter) wheel.getViewAdapter();
                String newExposureMode = String.valueOf(adapter.getItemText(newValue));

                if (newExposureMode != null) {
                    CameraSettings cameraSettings = new CameraSettings();
                    cameraSettings.setExposureMode(newExposureMode);
                    applyCameraSettings(cameraSettings);
                }
            }
        });

        mIsoWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wheel.requestLayout();
                ArrayWheelAdapter adapter = (ArrayWheelAdapter) wheel.getViewAdapter();
                String newIso = String.valueOf(adapter.getItemText(newValue));

                if (newIso != null) {
                    CameraSettings cameraSettings = new CameraSettings();
                    if ("AUTO".equals(newIso)) {
                        cameraSettings.setIsoAuto(true);
                    } else {
                        cameraSettings.setIso(newIso);
                    }
                    applyCameraSettings(cameraSettings);
                }
            }
        });

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    private void applyCameraSettings(CameraSettings cameraSettings) {
        PostSettingsAsyncTask postSettingsAsyncTask = new PostSettingsAsyncTask(FullscreenActivity.this);
        postSettingsAsyncTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, cameraSettings);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        loadLiveview();
        readCameraInfo();
        refreshCameraStatus();
    }


    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;
    }

    @SuppressLint("InlinedApi")
    private void show() {
        mControlsView.setVisibility(View.VISIBLE);
        // Show the system bar
//        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//        mApertureWheel.setVisibility(View.VISIBLE);
        mVisible = true;
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void loadLiveview() {
        Mjpeg.newInstance()
                .open("http://172.24.1.1:8080/api/v1/liveview.mjpg", 5)
                .subscribe(new Observer<MjpegInputStream>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(getClass().getSimpleName(), "mjpeg error", e);
                        Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(MjpegInputStream mjpegInputStream) {
                        mContentView.setSource(mjpegInputStream);
                        mContentView.setDisplayMode(calculateDisplayMode());
                        mContentView.showFps(true);
                    }
                });
    }

    private void readCameraInfo() {
        GetCameraInfoAsyncTask getCameraInfoAsyncTask = new GetCameraInfoAsyncTask(this);
        getCameraInfoAsyncTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

    private void refreshCameraStatus() {
        GetCameraInfoAsyncTask getCameraInfoAsyncTask = new GetCameraInfoAsyncTask(this);
        getCameraInfoAsyncTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);

        GetCameraStatusAsyncTask getCameraStatusAsyncTask = new GetCameraStatusAsyncTask(this);
        getCameraStatusAsyncTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }


    private DisplayMode calculateDisplayMode() {
        return DisplayMode.BEST_FIT;
//        int orientation = getResources().getConfiguration().orientation;
//        return orientation == Configuration.ORIENTATION_LANDSCAPE ?
//                DisplayMode.FULLSCREEN : DisplayMode.BEST_FIT;
    }


    public void setCaptureMode(CaptureMode captureMode) {
        if (captureMode != mCaptureMode) {
            mCaptureMode = captureMode;

            switch (mCaptureMode) {
                case PHOTO:
                    break;

                case VIDEO:
                    break;
            }
        }
    }

    public void setIsRecording(boolean isRecording) {
        mIsRecording = isRecording;

        // TODO show/hide recording marker + timer
    }
}
