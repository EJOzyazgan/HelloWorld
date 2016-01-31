package ej.example.com.helloworld;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageEditorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImageEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ImageEditorFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ImageView userPic;
    private ImageButton negativeButton;
    private FloatingActionButton cameraFAB;
    private final int CAMERA_REQUEST = 1888;
    private final int UPLOAD_REQUEST = 1000;
    private FloatingActionButton saveFAB;
    private ImageButton originalButton;
    private ImageButton grayScaleButton;
    private ImageButton mirrorHorizontalButton;
    private ImageButton mirrorVerticalButton;
    private FloatingActionButton uploadFAB;

    public ImageEditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageEditorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageEditorFragment newInstance(String param1, String param2) {
        ImageEditorFragment fragment = new ImageEditorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_editor, container, false);
        userPic = (ImageView) view.findViewById(R.id.userPic);
        negativeButton = (ImageButton) view.findViewById(R.id.negativeButton);
        cameraFAB = (FloatingActionButton) view.findViewById(R.id.cameraFAB);
        saveFAB = (FloatingActionButton) view.findViewById(R.id.saveFAB);
        originalButton = (ImageButton) view.findViewById(R.id.originalButton);
        grayScaleButton = (ImageButton) view.findViewById(R.id.grayScaleButton);
        mirrorHorizontalButton = (ImageButton) view.findViewById(R.id.mirrorHorizontalButton);
        mirrorVerticalButton = (ImageButton) view.findViewById(R.id.mirrorVerticalButton);
        uploadFAB = (FloatingActionButton) view.findViewById(R.id.uploadFAB);

        negativeButton.setOnClickListener(this);
        cameraFAB.setOnClickListener(this);
        saveFAB.setOnClickListener(this);
        originalButton.setOnClickListener(this);
        grayScaleButton.setOnClickListener(this);
        mirrorHorizontalButton.setOnClickListener(this);
        mirrorVerticalButton.setOnClickListener(this);
        uploadFAB.setOnClickListener(this);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cameraFAB:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;
            case R.id.uploadFAB:
                Intent uploadIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(uploadIntent, UPLOAD_REQUEST);
            break;
            case R.id.negativeButton:
                negativeButton.destroyDrawingCache();
                negativeButton.buildDrawingCache();
                Bitmap bmp1 = negativeButton.getDrawingCache();
                userPic.setImageBitmap(bmp1);
                break;
            case R.id.originalButton:
                originalButton.destroyDrawingCache();
                originalButton.buildDrawingCache();
                Bitmap bmp2 = originalButton.getDrawingCache();
                userPic.setImageBitmap(bmp2);
                break;
            case R.id.grayScaleButton:
                grayScaleButton.destroyDrawingCache();
                grayScaleButton.buildDrawingCache();
                Bitmap bmp3 = grayScaleButton.getDrawingCache();
                userPic.setImageBitmap(bmp3);
                break;
            case R.id.mirrorHorizontalButton:
                mirrorHorizontalButton.destroyDrawingCache();
                mirrorHorizontalButton.buildDrawingCache();
                Bitmap bmp4 = mirrorHorizontalButton.getDrawingCache();
                userPic.setImageBitmap(bmp4);
                break;
            case R.id.mirrorVerticalButton:
                mirrorVerticalButton.destroyDrawingCache();
                mirrorVerticalButton.buildDrawingCache();
                Bitmap bmp5 = mirrorVerticalButton.getDrawingCache();
                userPic.setImageBitmap(bmp5);
                break;
            case R.id.saveFAB:
                saveImage();
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap cameraPhoto = (Bitmap) data.getExtras().get("data");
            userPic.setImageBitmap(cameraPhoto);
            originalButton.setImageBitmap(cameraPhoto);
            fillImageButtons();

        }else if(requestCode == UPLOAD_REQUEST && resultCode == Activity.RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap galleryPhoto = BitmapFactory.decodeFile(picturePath);
            userPic.setImageBitmap(galleryPhoto);
            originalButton.setImageBitmap(galleryPhoto);
            fillImageButtons();
        }
    }

    private void fillImageButtons(){
        negativeButton.setImageBitmap(negateImage());
        grayScaleButton.setImageBitmap(grayScaleImage());
        mirrorHorizontalButton.setImageBitmap(MirrorImageHorizontal());
        mirrorVerticalButton.setImageBitmap(MirrorImageVertical());
    }

    private Bitmap negateImage(){
        originalButton.destroyDrawingCache();
        originalButton.buildDrawingCache();
        Bitmap negativeBitmap = originalButton.getDrawingCache();

        int width = negativeBitmap.getWidth();
        int height = negativeBitmap.getHeight();
        int[] pixels = new int[width * height];
        //get pixels
        negativeBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        for(int x = 0; x < pixels.length; ++x) {
            int r = 255 - ((pixels[x] >> 16) & 0xff);
            int g = 255 - ((pixels[x] >> 8) & 0xff);
            int b = 255 - (pixels[x] & 0xff);

            pixels[x] = Color.rgb(r, g, b);
        }
        // create result bitmap output
        Bitmap negativeResult = Bitmap.createBitmap(width, height, negativeBitmap.getConfig());
        //set pixels
        negativeResult.setPixels(pixels, 0, width, 0, 0, width, height);

         return negativeResult;
    }

    private Bitmap grayScaleImage(){
        originalButton.destroyDrawingCache();
        originalButton.buildDrawingCache();
        Bitmap grayBitmap = originalButton.getDrawingCache();

        int width = grayBitmap.getWidth();
        int height = grayBitmap.getHeight();
        int[] pixels = new int[width * height];

        grayBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        for(int x = 0; x < pixels.length; ++x) {
            int r = ((pixels[x] >> 16) & 0xff);
            int g = ((pixels[x] >> 8) & 0xff);
            int b = (pixels[x] & 0xff);

            int grayColor = (int) (0.21*r + 0.72*g + 0.07*b);

            pixels[x] = Color.rgb(grayColor, grayColor, grayColor);
            //Log.d("EJ", "Pixcel int Color: " + pixels[x]);
        }
        // create result bitmap output
        Bitmap grayResult = Bitmap.createBitmap(width, height, grayBitmap.getConfig());
        //set pixels
        grayResult.setPixels(pixels, 0, width, 0, 0, width, height);

        return grayResult;
    }

    private Bitmap MirrorImageHorizontal(){
        originalButton.destroyDrawingCache();
        originalButton.buildDrawingCache();
        Bitmap mirrorHorizontalBitmap = originalButton.getDrawingCache();

        int width = mirrorHorizontalBitmap.getWidth();
        int height = mirrorHorizontalBitmap.getHeight();
        int[] pixels = new int[width * height];

        for(int r = 0; r < width; r++)
            for(int c = 0; c < height/2; c++)
                mirrorHorizontalBitmap.setPixel(r,c,mirrorHorizontalBitmap.getPixel(r, width-1-c));

        mirrorHorizontalBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        Bitmap mirrorHorizontal = Bitmap.createBitmap(mirrorHorizontalBitmap.getWidth(), mirrorHorizontalBitmap.getHeight(), mirrorHorizontalBitmap.getConfig());
        mirrorHorizontal.setPixels(pixels, 0, width, 0, 0, width, height);

        return mirrorHorizontal;
    }

    private Bitmap MirrorImageVertical(){
        originalButton.destroyDrawingCache();
        originalButton.buildDrawingCache();
        Bitmap mirrorVirticalBitmap = originalButton.getDrawingCache();

        int width = mirrorVirticalBitmap.getWidth();
        int height = mirrorVirticalBitmap.getHeight();
        int[] pixels = new int[width * height];

        for(int r = 0; r < width; r++)
            for(int c = 0; c < height; c++)
                mirrorVirticalBitmap.setPixel(r,c,mirrorVirticalBitmap.getPixel(width-1-r, c));

        mirrorVirticalBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        Bitmap mirrorHorizontal = Bitmap.createBitmap(mirrorVirticalBitmap.getWidth(), mirrorVirticalBitmap.getHeight(), mirrorVirticalBitmap.getConfig());
        mirrorHorizontal.setPixels(pixels, 0, width, 0, 0, width, height);

        return mirrorHorizontal;
    }

    private void saveImage(){
        userPic.destroyDrawingCache();
        userPic.buildDrawingCache();
        Bitmap saveBitmap = userPic.getDrawingCache();

        OutputStream fOut = null;
        //Uri outputFileUri;
        try {
            File root = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "Edited Images" + File.separator);
            root.mkdirs();
            int count = 0;
            File sdImageMainDirectory = new File(root, "editedImage(" + count + ").jpg");
            while (sdImageMainDirectory.exists()) {
                count++;
                sdImageMainDirectory = new File(root, "editedImage(" + count + ").jpg");
            }
            //outputFileUri = Uri.fromFile(sdImageMainDirectory);
            fOut = new FileOutputStream(sdImageMainDirectory);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error occured saving. Please try again later.",
                    Toast.LENGTH_SHORT).show();
        }

        try {
            saveBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            Toast.makeText(getActivity(), "Image Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
