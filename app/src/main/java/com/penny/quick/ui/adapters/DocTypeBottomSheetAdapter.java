package com.penny.quick.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.quick.R;
import com.penny.quick.models.DocType;
import java.util.List;

public class DocTypeBottomSheetAdapter extends
    RecyclerView.Adapter<DocTypeBottomSheetAdapter.BottomSheetListItemVH> {

  private final List<DocType> objectList;
  private final DocTypeBottomSheetListItemClickListener clickListener;

  public DocTypeBottomSheetAdapter(List<DocType> objectList,
      DocTypeBottomSheetListItemClickListener clickListener) {
    this.objectList = objectList;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public BottomSheetListItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.mobile_operator_list_item, parent, false);
    return new BottomSheetListItemVH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BottomSheetListItemVH holder, int position) {
    DocType object = objectList.get(position);
    holder.txtItemName.setText(object.getDisplayName());
    holder.imgIcon.setVisibility(View.GONE);
    holder.setDetails(object, clickListener);
  }

  @Override
  public int getItemCount() {

    return objectList.size();
  }

  public interface DocTypeBottomSheetListItemClickListener {

    void onDocTypeBottomSheetListItemClick(DocType obj);
  }

  public static class BottomSheetListItemVH extends ViewHolder {

    private final TextView txtItemName;
    private final ImageView imgIcon;

    public BottomSheetListItemVH(@NonNull View itemView) {
      super(itemView);
      imgIcon = itemView.findViewById(R.id.iv_icon);
      txtItemName = itemView.findViewById(R.id.tv_name);
    }

    public void setDetails(DocType object, DocTypeBottomSheetListItemClickListener clickListener) {
      itemView.setOnClickListener(view -> clickListener.onDocTypeBottomSheetListItemClick(object));
    }
  }
}
