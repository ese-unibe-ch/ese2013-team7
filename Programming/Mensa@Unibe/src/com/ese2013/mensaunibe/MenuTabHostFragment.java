package com.ese2013.mensaunibe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuTabHostFragment extends Fragment{
	private int mMensaId;
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.listViewMenu);

        mTabHost.addTab(mTabHost.newTabSpec("today").setIndicator(getActivity().getString(R.string.today)),
                CurrentDayMenuFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("soon").setIndicator(getActivity().getString(R.string.comingDaysTabTitle)),
                ComingDaysMenuFragment.class, null);
        
        setHasOptionsMenu(true);

        return mTabHost;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
    
	public void update(int mensaId) {
		mMensaId=mensaId;
		CurrentDayMenuFragment.updateMensaId(mMensaId);
		ComingDaysMenuFragment.updateMensaId(mMensaId);
	}
	    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.actionbar_menu_list, menu);
    }

}
