package com.ese2013.mensaunibe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.api.AppUtils;
import com.ese2013.mensaunibe.model.mensa.Mensa;

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
        builder.setIcon(R.drawable.ic_action_about_dark)
        	.setTitle(mMensa.getName())
        	.setMessage(buildMessage())
               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                      dialog.dismiss();
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    
    private String buildMessage(){
    	String message = getString(R.string.address) + ":\n";
    		message += mMensa.getStreet() + ", ";
    		message += mMensa.getPlz();
    	return message;
    }
}