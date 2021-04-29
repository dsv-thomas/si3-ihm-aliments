package com.dsv.td1.si3_ihm_aliments.ui_consumer.profile;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IConsumerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.consumer.Consumer;
import com.dsv.td1.si3_ihm_aliments.controller.IPermissionRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class ProfileEditFragment extends Fragment implements IPermissionRequest {
    private IConsumerAdapterListener listener;
    private Consumer consumer;
    private ImageView imageView;
    private String pictureName;
    private String directoryName;

    public ProfileEditFragment(Consumer consumer) {
        this.consumer = consumer;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listener = (IConsumerAdapterListener) getActivity();
        View root = inflater.inflate(R.layout.fragment_profile_consumer_edit, container, false);

        pictureName = "_test.jpg";
        ContextWrapper cw = new ContextWrapper(getContext());
        directoryName = (cw.getDir("imageDir", Context.MODE_PRIVATE)).getPath();    // path to /data/user/0/etu.demo.camera/app_imageDir

        EditText editText = root.findViewById(R.id.editTextPersonName);

        imageView = root.findViewById(R.id.avatarConsumerEdit);

        Button button = root.findViewById(R.id.submitForm);

        Button buttonTakePicture = root.findViewById(R.id.takePicture);
        buttonTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    Log.d("TESTTEST", "OK");
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                } else { //permission still GRANTED
                    takePicture();
                }
            }
        });

        Button saveImage = root.findViewById(R.id.saveImage);
        saveImage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap picture = listener.getPictureToSave();
                if (picture != null) {
                    //manage authorizations
                    int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                IPermissionRequest.REQUEST_MEDIA_WRITE);
                    } else {
                        saveToInternalStorage(picture);
                    }
                }
            }
        });

        Button loadImage = root.findViewById(R.id.loadImage);

        loadImage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //manage authorizations
                int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            IPermissionRequest.REQUEST_MEDIA_READ);
                } else {
                    listener.onPictureLoad(loadImageFromStorage());
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name", editText.getText().toString());
                listener.onSubmitSettingsClicked(consumer, bundle);
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
            File file = new File(directoryName, pictureName);
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


    private Bitmap loadImageFromStorage() {
        try {
            File f = new File(directoryName, pictureName);
            Toast.makeText(getContext(), "Picture loaded", Toast.LENGTH_LONG).show();
            return BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}

