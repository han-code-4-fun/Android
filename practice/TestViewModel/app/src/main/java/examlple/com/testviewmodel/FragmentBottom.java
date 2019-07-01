package examlple.com.testviewmodel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *terface
 * to handle interaction events.
 * Use the {@link FragmentBottom#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBottom extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btn;

    Button btn_create;

     CrossVM crossVM;
     CrossVM crossTest;
     CrossVM cross3AsCrossVM;
    TextView textView;
    FragmentBottomListener listener;

    public FragmentBottom() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBottom.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBottom newInstance(String param1, String param2) {
        FragmentBottom fragment = new FragmentBottom();
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
        crossVM= ViewModelProviders.of(getActivity()).get(CrossVM.class);
        crossTest = ViewModelProviders.of(this).get(CrossVM.class);
        cross3AsCrossVM = ViewModelProviders.of(getActivity()).get(CrossVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_bottom, container, false);


        textView = root.findViewById(R.id.textView_top);

        crossVM.getCountLiveData().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }

        });

        btn= root.findViewById(R.id.button2);
        btn.setText("Remove");

        btn_create = root.findViewById(R.id.button_create_infragment);

        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onResume() {
        super.onResume();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crossVM.countLessOne();
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("testtest",  "value for crossTest ->"+crossTest.getCountLiveData().getValue());
                Log.d("testtest",  "value for crossVM ->"+crossVM.getCountLiveData().getValue());

                Log.d("testtest",  "value for cross3AsCrossVM ->"+cross3AsCrossVM.getCountLiveData().getValue());
            }
        });
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

public interface FragmentBottomListener{
    void onBottomClicked();
    }

}
