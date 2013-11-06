package com.ese2013.mensaunibe;

import com.ese2013.mensaunibe.model.Model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class NewRatingActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_rating);
				
		setTitle( "New Rating" );
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Button submit = (Button) findViewById(R.id.submit_rating);
		submit.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View view) {
				RatingBar rating = (RatingBar) findViewById(R.id.new_rating_bar); 
				if( rating.getRating() != 0.0 ) {
					EditText comment = (EditText) findViewById(R.id.rating_comment);
					
					Model.getInstance().saveRating( "user", comment.getText().toString(), (int) rating.getRating() );
				}
			}
		
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.new_rating, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
				onBackPressed();
				return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}
	}

}
