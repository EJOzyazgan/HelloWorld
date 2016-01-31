package ej.example.com.helloworld;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalculatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CalculatorFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView textView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button buttonPlus;
    Button buttonEqual;
    Button buttonDiv;
    Button buttonMult;
    Button buttonMin;
    Button buttonClear;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
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
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button6 = (Button) view.findViewById(R.id.button6);
        button7 = (Button) view.findViewById(R.id.button7);
        button8 = (Button) view.findViewById(R.id.button8);
        button9 = (Button) view.findViewById(R.id.button9);
        button0 = (Button) view.findViewById(R.id.button0);
        buttonPlus = (Button) view.findViewById(R.id.buttonPlus);
        buttonEqual = (Button) view.findViewById(R.id.buttonEqual);
        buttonDiv = (Button) view.findViewById(R.id.buttonDiv);
        buttonMult = (Button) view.findViewById(R.id.buttonMult);
        buttonMin = (Button) view.findViewById(R.id.buttonMin);
        buttonClear = (Button) view.findViewById(R.id.buttonClear);

        textView.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMin.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonMult.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
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
            case R.id.button0:
                textView.setText(textView.getText() + "0");
                break;
            case R.id.button1:
                textView.setText(textView.getText() + "1");
                break;
            case R.id.button2:
                textView.setText(textView.getText() + "2");
                break;
            case R.id.button3:
                textView.setText(textView.getText() + "3");
                break;
            case R.id.button4:
                textView.setText(textView.getText() + "4");
                break;
            case R.id.button5:
                textView.setText(textView.getText() + "5");
                break;
            case R.id.button6:
                textView.setText(textView.getText() + "6");
                break;
            case R.id.button7:
                textView.setText(textView.getText() + "7");
                break;
            case R.id.button8:
                textView.setText(textView.getText() + "8");
                break;
            case R.id.button9:
                textView.setText(textView.getText() + "9");
                break;
            case R.id.buttonDiv:
                textView.setText(textView.getText() + " / ");
                break;
            case R.id.buttonPlus:
                textView.setText(textView.getText() + " + ");
                break;
            case R.id.buttonMin:
                textView.setText(textView.getText() + " - ");
                break;
            case R.id.buttonMult:
                textView.setText(textView.getText() + " * ");
                break;
            case R.id.buttonClear:
                textView.setText("");
                break;
            case R.id.buttonEqual:
                ArrayList<String> nums = new ArrayList<String>(Arrays.asList(textView.getText().toString().split(" ")));
                //textView.setText(nums.toString());
                while (nums.size() > 1)
                    for (int i = 1; i < nums.size(); i++)
                        if (nums.get(i).equals("/")) {
                            double num1 = Double.parseDouble(nums.get(i - 1));
                            double num2 = Double.parseDouble(nums.get(i + 1));
                            nums.set(i, "" + (num1 / num2));
                            nums.remove(i - 1);
                            i = i - 1;
                            nums.remove(i + 1);
                            if (nums.size() >= 3)
                                i = i + 1;
                        } else if (nums.get(i).equals("*")) {
                            double num1 = Double.parseDouble(nums.get(i - 1));
                            double num2 = Double.parseDouble(nums.get(i + 1));
                            nums.set(i, "" + (num1 * num2));
                            nums.remove(i - 1);
                            i = i - 1;
                            nums.remove(i + 1);
                            if (nums.size() >= 3)
                                i = i + 1;
                        } else if (nums.get(i).equals("-") && !Arrays.asList(nums.toArray()).contains("*") && !Arrays.asList(nums.toArray()).contains("/")) {
                            double num1 = Double.parseDouble(nums.get(i - 1));
                            double num2 = Double.parseDouble(nums.get(i + 1));
                            nums.set(i, "" + (num1 - num2));
                            nums.remove(i - 1);
                            i = i - 1;
                            nums.remove(i + 1);
                            if (nums.size() >= 3)
                                i = i + 1;
                        } else if (nums.get(i).equals("+") && !Arrays.asList(nums.toArray()).contains("*") && !Arrays.asList(nums.toArray()).contains("/")) {
                            double num1 = Double.parseDouble(nums.get(i - 1));
                            double num2 = Double.parseDouble(nums.get(i + 1));
                            nums.set(i, "" + (num1 + num2));
                            nums.remove(i - 1);
                            i = i - 1;
                            nums.remove(i + 1);
                            if (nums.size() >= 3)
                                i = i + 1;
                        }
                textView.setText(nums.toString().replace("[", "").replace("]", ""));
                break;
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
