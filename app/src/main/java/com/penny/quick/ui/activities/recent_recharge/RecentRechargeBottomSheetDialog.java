package com.penny.quick.ui.activities.recent_recharge;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.penny.quick.R;
import com.penny.quick.adapters.RecentRechargeBottomSheetAdapter;
import com.penny.quick.models.BottomSheetCheckBox;
import java.util.List;

public class RecentRechargeBottomSheetDialog extends BottomSheetDialogFragment {

  private List<BottomSheetCheckBox> bottomSheetCheckBoxes;
  private BottomSheetListener bottomSheetListener;
  private Context context;

  public RecentRechargeBottomSheetDialog(
      List<BottomSheetCheckBox> bottomSheetCheckBoxes) {
    this.bottomSheetCheckBoxes = bottomSheetCheckBoxes;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.recent_recharge_month_bottom_sheet, container, false);
    RecyclerView valuesRv = v.findViewById(R.id.rv_month_list);
    valuesRv.setLayoutManager(new LinearLayoutManager(context));
    RecentRechargeBottomSheetAdapter bottomSheetValuesAdapter = new RecentRechargeBottomSheetAdapter(
        bottomSheetCheckBoxes, context);
    valuesRv.setAdapter(bottomSheetValuesAdapter);
    v.findViewById(R.id.apply).setOnClickListener(view -> dismiss());
    v.findViewById(R.id.bt_close).setOnClickListener(view -> dismiss());
    return v;
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    bottomSheetListener = (BottomSheetListener) context;
//    this.context = context;

  }

  public interface BottomSheetListener {

    void onButtonClick(String text);
  }
}
