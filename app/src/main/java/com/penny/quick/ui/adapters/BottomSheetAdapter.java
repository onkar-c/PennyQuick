package com.penny.quick.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.quick.R;
import com.penny.quick.models.BottomSheetListObject;
import java.util.List;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.BottomSheetListItemVH> {
  private List<BottomSheetListObject> objectList;
  private Context context;
  private BottomSheetListItemClickListener clickListener;

  public BottomSheetAdapter(Context context,List<BottomSheetListObject> objectList,BottomSheetListItemClickListener clickListener) {
    this.objectList = objectList;
    this.context = context;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public BottomSheetListItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.mobile_operator_list_item,parent,false);
    return new BottomSheetListItemVH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BottomSheetListItemVH holder, int position) {
    BottomSheetListObject object = objectList.get(position);
    holder.txtItemName.setText(object.getName());
//    holder.txtItemName.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context,R.drawable.airtel),null,null,null);
    holder.setDetails(object,clickListener);
  }

  @Override
  public int getItemCount() {

    return objectList.size();
  }

  public class BottomSheetListItemVH extends ViewHolder {
    private ImageView imgIcon;
    private TextView txtItemName;

    public BottomSheetListItemVH(@NonNull View itemView) {
      super(itemView);
      imgIcon = itemView.findViewById(R.id.iv_icon);
      txtItemName = itemView.findViewById(R.id.tv_name);
    }

    public void setDetails(BottomSheetListObject object,BottomSheetListItemClickListener clickListener) {
      itemView.setOnClickListener(view -> clickListener.onBottomSheetListItemClick(object.getId()));
    }
  }

  public interface BottomSheetListItemClickListener{
    void onBottomSheetListItemClick(int id);
  }
}
