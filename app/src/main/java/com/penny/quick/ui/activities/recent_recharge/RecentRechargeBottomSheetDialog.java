package com.penny.quick.ui.activities.recent_recharge;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.penny.quick.R;
import com.penny.quick.adapters.RecentRechargeBottomSheetAdapter;
import com.penny.quick.models.BottomSheetCheckBox;
import java.util.ArrayList;
import java.util.List;

public class RecentRechargeBottomSheetDialog extends BottomSheetDialogFragment {

  private List<BottomSheetCheckBox> bottomSheetCheckBoxes;
  private BottomSheetListener bottomSheetListener;
  private String Header;

  public RecentRechargeBottomSheetDialog(
      List<BottomSheetCheckBox> bottomSheetCheckBoxes, String header,
      BottomSheetListener bottomSheetListener) {
    this.bottomSheetCheckBoxes = bottomSheetCheckBoxes;
    this.bottomSheetListener = bottomSheetListener;
    Header = header;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.recent_recharge_month_bottom_sheet, container, false);
    RecyclerView valuesRv = v.findViewById(R.id.rv_month_list);
    ((TextView) v.findViewById(R.id.tx_sheet_header)).setText(Header);
    valuesRv.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
    RecentRechargeBottomSheetAdapter bottomSheetValuesAdapter = new RecentRechargeBottomSheetAdapter(
        bottomSheetCheckBoxes);
    valuesRv.setAdapter(bottomSheetValuesAdapter);
    v.findViewById(R.id.apply).setOnClickListener(view -> {
      List<BottomSheetCheckBox> bottomSheetListeners = new ArrayList<>();
      for (BottomSheetCheckBox bottomSheetCheckBox : bottomSheetValuesAdapter.getValues()) {
        if (bottomSheetCheckBox.isChecked()) {
          bottomSheetListeners.add(bottomSheetCheckBox);
        }
      }
      bottomSheetListener.onButtonClick(bottomSheetListeners);
      dismiss();
    });
    v.findViewById(R.id.clear).setOnClickListener(view -> {
      List<BottomSheetCheckBox> bottomSheetCheckBoxes = bottomSheetValuesAdapter.getValues();
      for (BottomSheetCheckBox bottomSheetCheckBox : bottomSheetCheckBoxes) {
        bottomSheetCheckBox.setChecked(false);
      }
      bottomSheetValuesAdapter.setList(bottomSheetCheckBoxes);
    });
    v.findViewById(R.id.bt_close).setOnClickListener(view -> dismiss());
    return v;
  }


  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }


  public interface BottomSheetListener {

    void onButtonClick(List<BottomSheetCheckBox> bottomSheetCheckBoxes);
  }
}
