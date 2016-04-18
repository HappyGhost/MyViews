package com.mysource.myview.util;

import android.content.Context;

import com.mysource.myview.R;
import com.mysource.myview.model.GlobalAccountModel;
import com.mysource.myview.model.SimpleItemSearch;
import com.mysource.myview.ui.adapter.BaseAdapterListener;
import com.mysource.myview.ui.adapter.SimpleItemAdapter;
import com.mysource.myview.ui.dialog.SearchListDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by thong.nguyen on 01/12/2016.
 * Version: 1.0
 */
public class DialogUtils {

    public static SearchListDialog getGlobalAccountFilterDialog(Context context, BaseAdapterListener onItemClickListener, List<GlobalAccountModel> globalAccountList) {
        SearchListDialog searchListDialog = new SearchListDialog(context);
        SimpleItemAdapter adapter = new SimpleItemAdapter(context, onItemClickListener);
        List<SimpleItemSearch> listItem = new ArrayList<>();
        for (GlobalAccountModel model : globalAccountList) {
            SimpleItemSearch simpleItemSearch = new SimpleItemSearch();
            simpleItemSearch.setContent(model.getCity());
            listItem.add(simpleItemSearch);
        }
        adapter.setData(listItem);
        searchListDialog.setTitle(R.string.dialog_title_select_location);
        searchListDialog.setAdapter(adapter);
        return searchListDialog;
    }
}
