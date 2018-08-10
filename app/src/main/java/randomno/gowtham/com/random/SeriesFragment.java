package randomno.gowtham.com.random;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static android.content.Context.INPUT_METHOD_SERVICE;


public class SeriesFragment extends Fragment {
    EditText min_et, max_et;
    Button mGenerateBtn;
    String[] ListElements=new String[]{};
    String[] ShuffledElements=new String[]{};
    ListView listView1,listView2;
AdView adViewseries;
    public SeriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        try  {
            InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_series, container, false);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        min_et = view.findViewById(R.id.et1series);
        max_et = view.findViewById(R.id.et2series);
        mGenerateBtn = view.findViewById(R.id.genseries);
        listView1=(ListView)view.findViewById(R.id.list1);
        listView2=(ListView)view.findViewById(R.id.list2);
        final List< String > ListElementsArrayList = new ArrayList< String >
                (Arrays.asList(ListElements));

        final List< String > ShuffledListElements = new ArrayList< String >
                (Arrays.asList(ShuffledElements));
        adViewseries=(AdView)view.findViewById(R.id.adViewseries);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewseries.loadAd(adRequest);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                view.getWindowVisibleDisplayFrame(r);
                int screenHeight = view.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;

                Log.d(TAG, "keypadHeight = " + keypadHeight);

                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                    adViewseries.setVisibility(View.INVISIBLE);
                }
                else {
                    // keyboard is closed
                    adViewseries.setVisibility(View.VISIBLE);
                }
            }
        });

        mGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ListElementsArrayList.clear();
                ShuffledListElements.clear();
                String tempmin, tempmax;
                tempmin = min_et.getText().toString();
                tempmax = max_et.getText().toString();
                if (!tempmin.equals("") && (!tempmax.equals(""))) {
                    int min = Integer.parseInt(tempmin);
                    int max = Integer.parseInt(tempmax);
                    int i;
                    int[] list = new int[1000];
                    for (i = min; i <= max; i++) {
                        list[i] = i;
                        ListElementsArrayList.add(String.valueOf(list[i]));
                        ShuffledListElements.add(String.valueOf(list[i]));
                    }
                    final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                            (Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1,
                                    ListElementsArrayList);
                    listView1.setAdapter(adapter);
                    Collections.shuffle(ShuffledListElements);
                    final ArrayAdapter< String > adapter1 = new ArrayAdapter < String >
                            (Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1,
                                    ShuffledListElements);
                    listView2.setAdapter(adapter1);

                }
                try  {
                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}