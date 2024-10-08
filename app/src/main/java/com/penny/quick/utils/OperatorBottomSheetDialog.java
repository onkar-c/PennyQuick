package com.penny.quick.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.penny.database.entities.Operators;
import com.penny.quick.R;
import com.penny.quick.ui.activities.mobile_recharge.MobileRechargeActivity;
import com.penny.quick.ui.adapters.BottomSheetAdapter;
import java.util.List;

public class OperatorBottomSheetDialog extends BottomSheetDialogFragment {

  List<Operators> operators;
  OnClickListener onCloseClick = view -> dismiss();

  public OperatorBottomSheetDialog(List<Operators> operators) {
    this.operators = operators;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View bottomSheetView = inflater.inflate(R.layout.bottom_sheet, container, false);
    bottomSheetView.findViewById(R.id.bt_close)
        .setOnClickListener(onCloseClick);
    ((TextView) bottomSheetView.findViewById(R.id.tx_sheet_header)).setText(R.string.operators);
    RecyclerView bottomSheetRV = bottomSheetView.findViewById(R.id.rv_bottom_sheet_list);
    bottomSheetRV.setLayoutManager(new LinearLayoutManager(getContext()));
    BottomSheetAdapter bottomSheetAdapter = new BottomSheetAdapter(
        operators,
        (MobileRechargeActivity) getActivity());
    bottomSheetRV.setAdapter(bottomSheetAdapter);
    return bottomSheetView;
  }
}
