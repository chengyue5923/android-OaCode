package com.idxk.mobileoa.android.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.CommandAdapter;
import com.idxk.mobileoa.model.bean.CommandListItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/9.
 */
public class CommandFragment extends Fragment {
    private ListView commandListView;
    private List<CommandListItemBean> commandListItemBeans;
    private CommandAdapter commandAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_command, container, false);

        //通过type区分加载指令还是审批 type==0 指令
        Bundle mBundle = getArguments();
        int type = mBundle.getInt("type");

        commandListView = (ListView) view.findViewById(R.id.commandListView);
        commandListItemBeans = new ArrayList<CommandListItemBean>();
        CommandListItemBean commandListItemBean = new CommandListItemBean();
        commandListItemBean.personName = "test";
        commandListItemBeans.add(commandListItemBean);
        commandAdapter = new CommandAdapter(getActivity(), commandListItemBeans);
        commandListView.setAdapter(commandAdapter);
        return view;
    }
}
