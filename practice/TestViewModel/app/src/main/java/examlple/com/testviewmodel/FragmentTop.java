package examlple.com.testviewmodel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@lierface
 * to handle interaction events.
 * Use the {@link FragmentTop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTop extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public FragmentTop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTop.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTop newInstance(String param1, String param2) {
        FragmentTop fragment = new FragmentTop();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_top, container, false);

        final CrossVM crossVM = ViewModelProviders.of(getActivity()).get(CrossVM.class);

        final TextView textView = root.findViewById(R.id.textView_top);

        crossVM.getCountLiveData().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });

        crossVM.getSparseBooleanArray().observe(getActivity(), new Observer<SparseBooleanArray>() {
            @Override
            public void onChanged(SparseBooleanArray sparseBooleanArray) {
                Log.d("test", "onChanged: "+ sparseBooleanArray.get(2));
            }
        });


        Button btn = root.findViewById(R.id.button2);
        btn.setText("Add");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crossVM.countAddOne();
            }
        });

        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface FragmentTopListener{
        void onTopClicked();
    }

}
