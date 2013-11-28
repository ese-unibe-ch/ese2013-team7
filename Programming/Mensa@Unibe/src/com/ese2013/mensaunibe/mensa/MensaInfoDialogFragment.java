package com.ese2013.mensaunibe.mensa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.R.drawable;
import com.ese2013.mensaunibe.R.id;
import com.ese2013.mensaunibe.R.layout;
import com.ese2013.mensaunibe.R.string;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.utils.AppUtils;

/**
 * @author group17
 * @author Sandor Torok
 */

public class MensaInfoDialogFragment extends DialogFragment {
	private static final String TAG = "MensaInfoDialogFragment";
	private int mMensaId;
	private Mensa mMensa;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments(); 
		if (arguments == null)
			Log.e(TAG, "Arguments is NULL");
		else
			mMensaId = getArguments().getInt(AppUtils.MENSA_ID);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		mMensa = Model.getInstance().getMensaById(mMensaId);

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.mensa_info_dialog, null);

		TextView textView = (TextView) view.findViewById(R.id.address_detail);
		textView.setText(buildMessage());

		builder.setIcon(R.drawable.ic_action_about_dark)
		.setTitle(mMensa.getName())
		.setView(view)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});
		// Create the AlertDialog object and return it
		return builder.create();
	}
	
	/**
	 * Builds the information message of the mensas
	 * it includes the location and in future the opening times
	 * @return a full string of all informations of the mensa
	 */
	private String buildMessage(){
		String message = mMensa.getStreet() + ",\n";
		message += mMensa.getPlz();
		return message;
	}
}