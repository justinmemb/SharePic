package com.deme.sharepic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import com.deme.sharepic.utils.AppUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import cn.jarlen.photoedit.crop.CropImageType;
import cn.jarlen.photoedit.crop.CropImageView;
import cn.jarlen.photoedit.crop.CropOverlayView;

public class CropActivity extends AppCompatActivity implements View.OnClickListener ,CropOverlayView.TouchOutSideListener {

    public static String path;
    private ImageButton btnRotate;
    private ImageButton btnResize;
    private Bitmap tempBitmap;

    private Button btnBack;
    private Button btnNext;
    private Button btnCrop;

    private CropImageView cropImageView;

    private LinearLayout layoutResize;
    private Button btnCustom;
    private Button btnSquare;
    private Button btn23;
    private Button btn34;
    private Button btn35;
    private Button btn916;
    private Button btn32;
    private Button btn43;
    private Button btn53;
    private Button btn169;

    private KProgressHUD hud;

    int currentSelectedView = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        initAllViews();
        gridViewSettings();
        hud = new KProgressHUD(CropActivity.this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
    }

    private void gridViewSettings(){
        cropImageView.setGuidelines(CropImageType.CROPIMAGE_GRID_ON);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.crop_button);
        cropImageView.setCropOverlayCornerBitmap(bm);
        cropImageView.setFixedAspectRatio(false);
        path = getIntent().getStringExtra("filePath");

        Bitmap bit = BitmapFactory.decodeFile(path);
        if (AppUtils.getExifRotation(path) == 90) {
            tempBitmap = AppUtils.rotateBitmap(bit,90);
        } else {
            tempBitmap = bit;
        }

        cropImageView.setImageBitmap(tempBitmap);

        getSupportActionBar().hide();

        setCropImageEditStatus(false);
    }

    private void initAllViews(){
        btnRotate = (ImageButton) findViewById(R.id.btn_rotate);
        btnRotate.setOnClickListener(this);
        btnResize = (ImageButton) findViewById(R.id.btn_resize);
        btnResize.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btn_back_crop);
        btnBack.setOnClickListener(this);
        btnNext = (Button) findViewById(R.id.btn_next_crop);
        btnNext.setOnClickListener(this);
        btnCrop = (Button) findViewById(R.id.btn_crop_crop);
        btnCrop.setOnClickListener(this);

        cropImageView = (CropImageView) findViewById(R.id.cropmageView);
        cropImageView.setOnClickListener(this);
        cropImageView.getmCropOverlayView().setmTouchOutSideListener(this);

        layoutResize = (LinearLayout) findViewById(R.id.layout_ratio_crop);

        btnCustom = (Button) findViewById(R.id.btn_ratio_custom);
        btnCustom.setOnClickListener(this);
        btnSquare = (Button) findViewById(R.id.btn_ratio_square);
        btnSquare.setOnClickListener(this);
        btn23 = (Button) findViewById(R.id.btn_ratio_23);
        btn23.setOnClickListener(this);
        btn34 = (Button) findViewById(R.id.btn_ratio_34);
        btn34.setOnClickListener(this);
        btn35 = (Button) findViewById(R.id.btn_ratio_35);
        btn35.setOnClickListener(this);
        btn916 = (Button) findViewById(R.id.btn_ratio_916);
        btn916.setOnClickListener(this);
        btn32 = (Button) findViewById(R.id.btn_ratio_32);
        btn32.setOnClickListener(this);
        btn43 = (Button) findViewById(R.id.btn_ratio_43);
        btn43.setOnClickListener(this);
        btn53 = (Button) findViewById(R.id.btn_ratio_53);
        btn53.setOnClickListener(this);
        btn169 = (Button) findViewById(R.id.btn_ratio_169);
        btn169.setOnClickListener(this);

        //init views
        layoutResize.setVisibility(View.GONE);
        btnResize.setImageResource(R.drawable.resizepress);

    }

    @Override
    public void onClick(View v) {
        layoutResize.setVisibility(View.GONE);
        btnResize.setImageResource(R.drawable.resizepress);
        switch (v.getId()) {
            //Bottom Bar button click
            case R.id.btn_rotate:
                cropImageView.rotateImage(-90);
                tempBitmap = rotateBitmap(tempBitmap, -90);
                break;
            case R.id.btn_resize:
                if (currentSelectedView == v.getId()) {
                    layoutResize.setVisibility(View.GONE);
                    currentSelectedView = 0;
                    return;
                } else {
                    layoutResize.setVisibility(View.VISIBLE);
                    btnResize.setImageResource(R.drawable.resize);
                }
                break;
            //Navigation bar item click
            case R.id.btn_back_crop:
                this.finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.btn_next_crop:
                Intent okData = new Intent(this, FilterActivity.class);
                FilterActivity.tempBitmap = tempBitmap;
                okData.putExtra("filePath", path);
                startActivity(okData);
                overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
                break;
            case R.id.btn_crop_crop:
                setCropImageEditStatus(false);
                tempBitmap = cropImageView.getCroppedImage();
                cropImageView.setImageBitmap(tempBitmap);
                break;
            //crop size button click
            case R.id.btn_ratio_custom:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(false);
                break;
            case R.id.btn_ratio_square:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(10, 10);
                break;
            case R.id.btn_ratio_23:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(20, 30);
                break;
            case R.id.btn_ratio_34:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(30, 40);
                break;
            case R.id.btn_ratio_35:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(30, 50);
                break;
            case R.id.btn_ratio_916:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(90, 160);
                break;
            case R.id.btn_ratio_32:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(30, 20);
                break;
            case R.id.btn_ratio_43:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(40, 30);
                break;
            case R.id.btn_ratio_53:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(50, 30);
                break;
            case R.id.btn_ratio_169:
                setCropImageEditStatus(true);
                cropImageView.setFixedAspectRatio(true);
                cropImageView.setAspectRatio(160, 90);
                break;
            default:
                setCropImageEditStatus(false);
                break;
        }
        currentSelectedView = v.getId();
    }

    private Bitmap rotateBitmap(Bitmap bitmap, int angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        Bitmap rotatedImage = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
        return rotatedImage;
    }

    private void setCropImageEditStatus(boolean status){
        if (status) {
            cropImageView.getmCropOverlayView().setVisibility(View.VISIBLE);
            btnCrop.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
        } else {
            cropImageView.getmCropOverlayView().setVisibility(View.INVISIBLE);
            btnCrop.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTouchOutSide(View view) {
        btnResize.setImageResource(R.drawable.resizepress);
        currentSelectedView = 0;
        setCropImageEditStatus(false);
        layoutResize.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        layoutResize.setVisibility(View.GONE);
        btnResize.setImageResource(R.drawable.resizepress);
        cropImageView.getmCropOverlayView().setVisibility(View.INVISIBLE);
        btnCrop.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.VISIBLE);
    }
}
