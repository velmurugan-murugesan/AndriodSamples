// "Therefore those skilled at the unorthodox
// are infinite as heaven and earth,
// inexhaustible as the great rivers.
// When they come to an end,
// they begin again,
// like the days and months;
// they die and are reborn,
// like the four seasons."
//
// - Sun Tsu,
// "The Art of War"

package com.velmurugan.imagecropperlib;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.velmurugan.imagecropperlib.lib.CropImage;
import com.velmurugan.imagecropperlib.lib.CropImageView;

public class MainActivity extends AppCompatActivity {
  Button uploadBtn;
  ImageView croppedImage;
  int MY_PERMISSION_STORAGE = 100;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    uploadBtn = findViewById(R.id.upload_btn);
    croppedImage = findViewById(R.id.cropped_img);
    uploadBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
          ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_STORAGE);
          ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_STORAGE);
          //showToast("Need Permission to access and upload your Image");
        } else {
          CropImage.activity()
                  .setGuidelines(CropImageView.Guidelines.ON)
                  .setAspectRatio(1, 1) //You can skip this for free form aspect ratio)
                  .start(MainActivity.this);
        }

      }
    });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
      CropImage.ActivityResult result = CropImage.getActivityResult(data);
      if (resultCode == RESULT_OK) {
        Uri resultUri = result.getUri();
        // Set uri as Image in the ImageView:
        croppedImage.setImageURI(resultUri);
      } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
        Exception error = result.getError();
      }
    }
  }
}

 /* // region: Fields and Consts

  DrawerLayout mDrawerLayout;

  private ActionBarDrawerToggle mDrawerToggle;

  private MainFragment mCurrentFragment;

  private Uri mCropImageUri;

  private CropImageViewOptions mCropImageViewOptions = new CropImageViewOptions();
  // endregion

  public void setCurrentFragment(MainFragment fragment) {
    mCurrentFragment = fragment;
  }

  public void setCurrentOptions(CropImageViewOptions options) {
    mCropImageViewOptions = options;
    updateDrawerTogglesByOptions(options);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    mDrawerToggle =
            new ActionBarDrawerToggle(
                    this, mDrawerLayout, R.string.main_drawer_open, R.string.main_drawer_close);
    mDrawerToggle.setDrawerIndicatorEnabled(true);
    mDrawerLayout.setDrawerListener(mDrawerToggle);

    if (savedInstanceState == null) {
      setMainFragmentByPreset(CropDemoPreset.RECT);
    }
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mDrawerToggle.syncState();
    mCurrentFragment.updateCurrentCropViewOptions();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    if (mCurrentFragment != null && mCurrentFragment.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  @SuppressLint("NewApi")
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
            && resultCode == AppCompatActivity.RESULT_OK) {
      Uri imageUri = CropImage.getPickImageResultUri(this, data);

      // For API >= 23 we need to check specifically that we have permissions to read external
      // storage,
      // but we don't know if we need to for the URI so the simplest is to try open the stream and
      // see if we get error.
      boolean requirePermissions = false;
      if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {

        // request permissions and handle the result in onRequestPermissionsResult()
        requirePermissions = true;
        mCropImageUri = imageUri;
        requestPermissions(
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
      } else {

        mCurrentFragment.setImageUri(imageUri);
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(
          int requestCode, String permissions[], int[] grantResults) {
    if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        CropImage.startPickImageActivity(this);
      } else {
        Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
                .show();
      }
    }
    if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
      if (mCropImageUri != null
              && grantResults.length > 0
              && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        mCurrentFragment.setImageUri(mCropImageUri);
      } else {
        Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
                .show();
      }
    }
  }

  @SuppressLint("NewApi")
  public void onDrawerOptionClicked(View view) {
    switch (view.getId()) {
      case R.id.drawer_option_load:
        if (CropImage.isExplicitCameraPermissionRequired(this)) {
          requestPermissions(
                  new String[]{Manifest.permission.CAMERA},
                  CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
        } else {
          CropImage.startPickImageActivity(this);
        }
        mDrawerLayout.closeDrawers();
        break;
      case R.id.drawer_option_oval:
        setMainFragmentByPreset(CropDemoPreset.CIRCULAR);
        mDrawerLayout.closeDrawers();
        break;
      case R.id.drawer_option_rect:
        setMainFragmentByPreset(CropDemoPreset.RECT);
        mDrawerLayout.closeDrawers();
        break;
      case R.id.drawer_option_customized_overlay:
        setMainFragmentByPreset(CropDemoPreset.CUSTOMIZED_OVERLAY);
        mDrawerLayout.closeDrawers();
        break;
      case R.id.drawer_option_min_max_override:
        setMainFragmentByPreset(CropDemoPreset.MIN_MAX_OVERRIDE);
        mDrawerLayout.closeDrawers();
        break;
      case R.id.drawer_option_scale_center:
        setMainFragmentByPreset(CropDemoPreset.SCALE_CENTER_INSIDE);
        mDrawerLayout.closeDrawers();
        break;
      case R.id.drawer_option_toggle_scale:
        *//*mCropImageViewOptions.scaleType =
                mCropImageViewOptions.scaleType == CropImageView.ScaleType.FIT_CENTER
                        ? CropImageView.ScaleType.CENTER_INSIDE
                        : mCropImageViewOptions.scaleType == CropImageView.ScaleType.CENTER_INSIDE
                        ? CropImageView.ScaleType.CENTER
                        : mCropImageViewOptions.scaleType == CropImageView.ScaleType.CENTER
                        ? CropImageView.ScaleType.CENTER_CROP
                        : CropImageView.ScaleType.FIT_CENTER;
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);*//*
        break;
      case R.id.drawer_option_toggle_shape:
        *//*mCropImageViewOptions.cropShape = mCropImageViewOptions.cropShape == CropImageView.CropShape.RECTANGLE
                ? CropImageView.CropShape.OVAL
                : CropImageView.CropShape.RECTANGLE;
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);*//*
        break;
      case R.id.drawer_option_toggle_guidelines:
        *//*mCropImageViewOptions.guidelines =
                (CropImageView.Guidelines.OFF == mCropImageViewOptions.guidelines)
                        ? CropImageView.Guidelines.ON
                        : ((mCropImageViewOptions.guidelines == CropImageView.Guidelines.ON)
                        ? CropImageView.Guidelines.ON_TOUCH
                        : CropImageView.Guidelines.OFF);
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);*//*
        break;
      case R.id.drawer_option_toggle_aspect_ratio:
        if (!mCropImageViewOptions.fixAspectRatio) {
          mCropImageViewOptions.fixAspectRatio = true;
          mCropImageViewOptions.aspectRatio = new Pair<>(1, 1);
        } else {
          if (mCropImageViewOptions.aspectRatio.first == 1
                  && mCropImageViewOptions.aspectRatio.second == 1) {
            mCropImageViewOptions.aspectRatio = new Pair<>(4, 3);
          } else if (mCropImageViewOptions.aspectRatio.first == 4
                  && mCropImageViewOptions.aspectRatio.second == 3) {
            mCropImageViewOptions.aspectRatio = new Pair<>(16, 9);
          } else if (mCropImageViewOptions.aspectRatio.first == 16
                  && mCropImageViewOptions.aspectRatio.second == 9) {
            mCropImageViewOptions.aspectRatio = new Pair<>(9, 16);
          } else {
            mCropImageViewOptions.fixAspectRatio = false;
          }
        }
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);
        break;
      case R.id.drawer_option_toggle_auto_zoom:
        mCropImageViewOptions.autoZoomEnabled = !mCropImageViewOptions.autoZoomEnabled;
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);
        break;
      case R.id.drawer_option_toggle_max_zoom:
        mCropImageViewOptions.maxZoomLevel =
                mCropImageViewOptions.maxZoomLevel == 4
                        ? 8
                        : mCropImageViewOptions.maxZoomLevel == 8 ? 2 : 4;
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);
        break;
      case R.id.drawer_option_set_initial_crop_rect:
        mCurrentFragment.setInitialCropRect();
        mDrawerLayout.closeDrawers();
        break;
      case R.id.drawer_option_reset_crop_rect:
        mCurrentFragment.resetCropRect();
        mDrawerLayout.closeDrawers();
        break;
      case R.id.drawer_option_toggle_multitouch:
        mCropImageViewOptions.multitouch = !mCropImageViewOptions.multitouch;
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);
        break;
      case R.id.drawer_option_toggle_show_overlay:
        mCropImageViewOptions.showCropOverlay = !mCropImageViewOptions.showCropOverlay;
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);
        break;
      case R.id.drawer_option_toggle_show_progress_bar:
        mCropImageViewOptions.showProgressBar = !mCropImageViewOptions.showProgressBar;
        mCurrentFragment.setCropImageViewOptions(mCropImageViewOptions);
        updateDrawerTogglesByOptions(mCropImageViewOptions);
        break;
      default:
        Toast.makeText(this, "Unknown drawer option clicked", Toast.LENGTH_LONG).show();
    }
  }

  private void setMainFragmentByPreset(CropDemoPreset demoPreset) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment.newInstance(demoPreset))
            .commit();
  }

  private void updateDrawerTogglesByOptions(CropImageViewOptions options) {
    *//*((TextView) findViewById(R.id.drawer_option_toggle_scale))
        .setText(
            getResources()
                .getString(R.string.drawer_option_toggle_scale, options.scaleType.name()));
    ((TextView) findViewById(R.id.drawer_option_toggle_shape))
        .setText(
            getResources()
                .getString(R.string.drawer_option_toggle_shape, options.cropShape.na()));
    ((TextView) findViewById(R.id.drawer_option_toggle_guidelines))
        .setText(
            getResources()
                .getString(R.string.drawer_option_toggle_guidelines, options.guidelines.name()));
    ((TextView) findViewById(R.id.drawer_option_toggle_multitouch))
        .setText(
            getResources()
                .getString(
                    R.string.drawer_option_toggle_multitouch,
                    Boolean.toString(options.multitouch)));
    ((TextView) findViewById(R.id.drawer_option_toggle_show_overlay))
        .setText(
            getResources()
                .getString(
                    R.string.drawer_option_toggle_show_overlay,
                    Boolean.toString(options.showCropOverlay)));
    ((TextView) findViewById(R.id.drawer_option_toggle_show_progress_bar))
        .setText(
            getResources()
                .getString(
                    R.string.drawer_option_toggle_show_progress_bar,
                    Boolean.toString(options.showProgressBar)));

    String aspectRatio = "FREE";
    if (options.fixAspectRatio) {
      aspectRatio = options.aspectRatio.first + ":" + options.aspectRatio.second;
    }
    ((TextView) findViewById(R.id.drawer_option_toggle_aspect_ratio))
        .setText(getResources().getString(R.string.drawer_option_toggle_aspect_ratio, aspectRatio));

    ((TextView) findViewById(R.id.drawer_option_toggle_auto_zoom))
        .setText(
            getResources()
                .getString(
                    R.string.drawer_option_toggle_auto_zoom,
                    options.autoZoomEnabled ? "Enabled" : "Disabled"));
    ((TextView) findViewById(R.id.drawer_option_toggle_max_zoom))
        .setText(
            getResources().getString(R.string.drawer_option_toggle_max_zoom, options.maxZoomLevel));
  }*//*
  }*/

