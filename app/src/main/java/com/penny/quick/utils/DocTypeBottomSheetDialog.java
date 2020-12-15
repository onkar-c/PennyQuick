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
import com.penny.quick.R;
import com.penny.quick.models.DocType;
import com.penny.quick.ui.activities.money_transfer.SelectRecipientActivity;
import com.penny.quick.ui.adapters.DocTypeBottomSheetAdapter;
import java.util.List;

public class DocTypeBottomSheetDialog extends BottomSheetDialogFragment {

  List<DocType> DocTypeList;
  OnClickListener onCloseClick = view -> dismiss();

  public DocTypeBottomSheetDialog(List<DocType> DocTypeList) {
    this.DocTypeList = DocTypeList;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View bottomSheetView = inflater.inflate(R.layout.bottom_sheet, container, false);
    bottomSheetView.findViewById(R.id.bt_close)
        .setOnClickListener(onCloseClick);
    ((TextView) bottomSheetView.findViewById(R.id.tx_sheet_header)).setText(R.string.recipient);
    RecyclerView bottomSheetRV = bottomSheetView.findViewById(R.id.rv_bottom_sheet_list);
    bottomSheetRV.setLayoutManager(new LinearLayoutManager(getContext()));
    DocTypeBottomSheetAdapter bottomSheetAdapter = new DocTypeBottomSheetAdapter(
        DocTypeList,
        (SelectRecipientActivity) getActivity());
    bottomSheetRV.setAdapter(bottomSheetAdapter);
    return bottomSheetView;
  }
}
