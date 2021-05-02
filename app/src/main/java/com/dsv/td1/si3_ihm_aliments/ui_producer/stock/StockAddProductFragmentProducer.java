package com.dsv.td1.si3_ihm_aliments.ui_producer.stock;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerListener;
import com.dsv.td1.si3_ihm_aliments.controller.IPermissionRequest;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


public class StockAddProductFragmentProducer extends androidx.fragment.app.Fragment implements IPermissionRequest {

    private Producer producer;
    private IProducerListener listener;
    private ImageView imageView;
    private String imageName;
    private String directoryName;


    public StockAddProductFragmentProducer(Producer producer) {
        this.producer = producer;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        listener = (IProducerListener) getActivity();
        View root = inflater.inflate(R.layout.fragment_producer_stock_add_product, container, false);

        ContextWrapper cw = new ContextWrapper(getContext());
        directoryName = (cw.getDir("imageDir", Context.MODE_PRIVATE)).getPath();    // path to /data/user/0/etu.demo.camera/app_imageDir


        EditText editTextProductName = root.findViewById(R.id.editTextProductNameAddProductProducer);
        EditText editTextProductQuantity = root.findViewById(R.id.editTextQuantityAddProductProducer);
        EditText editTextProductPrice = root.findViewById(R.id.editTextPriceAddProductProducer);

        imageName = UUID.randomUUID().toString();
        imageView = root.findViewById(R.id.imageViewProductAddProductProducer);

        Button buttonTakePicture = root.findViewById(R.id.takePicture);
        Button submitButton = root.findViewById(R.id.submitFormAddProductProducer);


        buttonTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                } else {
                    takePicture();
                }
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap picture = listener.getPictureToSave();
                if (picture != null) {
                    //manage authorizations
                    int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, IPermissionRequest.REQUEST_MEDIA_WRITE);
                    } else {
                        Toast.makeText(getContext(), "Picture saved", Toast.LENGTH_LONG).show();
                        saveToInternalStorage(picture);
                    }
                }

                Bundle bundle = new Bundle();
                bundle.putString("editTextProductName", editTextProductName.getText().toString());
                bundle.putString("editTextProductQuantity", editTextProductQuantity.getText().toString());
                bundle.putString("editTextProductPrice", editTextProductPrice.getText().toString());
                bundle.putString("productImageName", imageName);

                listener.onSubmitaddProductClicked(producer, bundle);
            }
        });
        return root;
    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void setImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public void saveToInternalStorage(Bitmap picture) {
        OutputStream outputStream = null;
        try {
            File file = new File(directoryName, imageName + ".jpg");
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        picture.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


