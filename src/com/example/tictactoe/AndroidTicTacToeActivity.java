package com.example.tictactoe;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AndroidTicTacToeActivity extends Activity implements OnClickListener {

	private TicTacToeGame mGame;
	private Button mBoardButtons[];
	private TextView mInfoTextView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_tic_tac_toe);
		
		mGame = new TicTacToeGame();
		
		mBoardButtons = new Button[9];
	
		mBoardButtons[0] = (Button)findViewById(R.id.one);
		mBoardButtons[1] = (Button)findViewById(R.id.two);
		mBoardButtons[2] = (Button)findViewById(R.id.three);
		mBoardButtons[3] = (Button)findViewById(R.id.four);
		mBoardButtons[4] = (Button)findViewById(R.id.five);
		mBoardButtons[5] = (Button)findViewById(R.id.six);
		mBoardButtons[6] = (Button)findViewById(R.id.seven);
		mBoardButtons[7] = (Button)findViewById(R.id.eight);
		mBoardButtons[8] = (Button)findViewById(R.id.nine);
		
		startNewGame();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	
	
	private void startNewGame(){
		mGame.clearBoard();
		
		for(int i=0; i< mBoardButtons.length; i++){
			mBoardButtons[i].setText(String.valueOf(TicTacToeGame.OPEN_SPOT));
			mBoardButtons[i].setEnabled(true);
			mBoardButtons[i].setOnClickListener(this);
		}
		TextView tv = (TextView)findViewById(R.id.information);
		tv.setText(R.string.player_turn);
	}
	
	private void setMove(char player, int location){
		mGame.setMove(player, location); 
		mBoardButtons[location].setEnabled(false); 
		mBoardButtons[location].setText(String.valueOf(player)); 
		if (player == TicTacToeGame.HUMAN_PLAYER) 
			mBoardButtons[location].setTextColor(Color.rgb(0, 200, 0)); 
		else 
			mBoardButtons[location].setTextColor(Color.rgb(200, 0, 0)); 
	}

	@Override
	public void onClick(View v) {
		int index = -1;
		Button clickedButton;
		for(int i=0; i<mBoardButtons.length; i++){
			if(v.getId() == mBoardButtons[i].getId()){
				index = i;
				clickedButton = (Button)findViewById(mBoardButtons[i].getId());
				if(clickedButton.isEnabled()){
					setMove(TicTacToeGame.HUMAN_PLAYER, i);
					TextView tv = (TextView)findViewById(R.id.information);
					switch(mGame.checkForWinnerPublic()){
					case 0: // game not over, computer's turn
						tv.setText("Now it's the Computer's Turn");
						setMove(TicTacToeGame.COMPUTER_PLAYER, mGame.getComputerMovePublic());
						if(mGame.checkForWinnerPublic() == 3){
							tv.setText("Game over.  Computer won!");
						}
						else{
							tv.setText("Your move");
						}
						break;
					case 1:
						tv.setText("Game tied.");
						break;
					case 2:
						tv.setText("Game over, you won!");
						break;
					case 3:
						tv.setText("Game over. Computer won!");
						break;
					}
					break;
				}
			}
		}
		
	}

}
