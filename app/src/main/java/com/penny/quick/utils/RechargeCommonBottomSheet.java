package com.penny.quick.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.penny.quick.R;
import com.penny.quick.models.BottomSheetListObject;
import com.penny.quick.ui.activities.mobile_recharge.MobileRechargeActivity;
import com.penny.quick.ui.adapters.BottomSheetAdapter;

public class RechargeCommonBottomSheet extends BottomSheetDialogFragment {
  private View bottomSheetView;
  private RecyclerView bottomSheetRV;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    bottomSheetView = inflater.inflate(R.layout.bottom_sheet,container,false );
    bottomSheetView.findViewById(R.id.bt_close)
        .setOnClickListener(onCloseClick);
    bottomSheetRV = bottomSheetView.findViewById(R.id.rv_bottom_sheet_list);
    bottomSheetRV.setLayoutManager(new LinearLayoutManager(getContext()));
    BottomSheetAdapter bottomSheetAdapter = new BottomSheetAdapter(BottomSheetListObject.getObjectList(),
        (MobileRechargeActivity) getActivity());
    bottomSheetRV.setAdapter(bottomSheetAdapter);
    return bottomSheetView;
  }

  OnClickListener onCloseClick = view -> {
    dismiss();
  };
}
