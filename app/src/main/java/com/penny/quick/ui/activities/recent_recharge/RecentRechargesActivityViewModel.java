package com.penny.quick.ui.activities.recent_recharge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.models.DateFormatModel;
import com.penny.core.repositories.RechargeRepository;
import com.penny.database.entities.RecentRecharge;
import com.penny.quick.models.BottomSheetCheckBox;
import java.util.ArrayList;
import java.util.List;

public class RecentRechargesActivityViewModel extends ViewModel {

  RecentRechargesActivityViewModel() {
    super();
  }

  LiveData<List<RecentRecharge>> getRecentRecharges() {
    return new RechargeRepository().getRecentRecharges();
  }

  LiveData<WorkInfo> getRecentRechargesFromServer(List<DateFormatModel> dateFormatModels,
      List<BottomSheetCheckBox> bottomSheetCategoriesCheckBoxes,
      List<BottomSheetCheckBox> statusCheckBoxes) {
    List<DateFormatModel> extractedDateFormatModels = new ArrayList<>();
    if (dateFormatModels != null && dateFormatModels.size() > 0) {
      for (DateFormatModel dateFormatModel : dateFormatModels) {
        if (dateFormatModel.isChecked()) {
          extractedDateFormatModels.add(dateFormatModel);
        }
      }
    }

    List<String> extractedCategories = new ArrayList<>();
    if (dateFormatModels != null && dateFormatModels.size() > 0) {
      for (BottomSheetCheckBox dateFormatModel : bottomSheetCategoriesCheckBoxes) {
        if (dateFormatModel.isChecked()) {
          extractedCategories.add(dateFormatModel.getActualName());
        }
      }
    }

    List<String> statusCategories = new ArrayList<>();
    if (dateFormatModels != null && dateFormatModels.size() > 0) {
      for (BottomSheetCheckBox dateFormatModel : statusCheckBoxes) {
        if (dateFormatModel.isChecked()) {
          statusCategories.add(dateFormatModel.getActualName());
        }
      }
    }
    return new RechargeRepository()
        .getRecentRechargeWorkManager(extractedDateFormatModels, extractedCategories,
            statusCategories);
  }
}
