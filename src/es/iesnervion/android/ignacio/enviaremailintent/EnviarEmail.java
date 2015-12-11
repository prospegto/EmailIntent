package es.iesnervion.android.ignacio.enviaremailintent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EnviarEmail extends Activity implements OnItemSelectedListener {
	
	Button btnEnviar;
	EditText mensaje;
	Spinner asunto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enviar_email);
		btnEnviar = (Button) findViewById(R.id.btnEnviar);
		mensaje = (EditText) findViewById(R.id.mensaje);
		
		asunto = (Spinner) findViewById(R.id.asunto);
		asunto.setOnItemSelectedListener(this);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.asunto, android.R.layout.simple_spinner_item);
		asunto.setAdapter(adapter);
		
		
		btnEnviar.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				
				String sMensaje = mensaje.getText().toString();
				
				Intent intent = new Intent(Intent.ACTION_SEND);  
				
				intent.setType("message/rfc822");   
				intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "x@gmail.com"});
				intent.putExtra(Intent.EXTRA_SUBJECT, asunto.getSelectedItem().toString());
				intent.putExtra(Intent.EXTRA_TEXT, sMensaje);
				try{
					startActivity(Intent.createChooser(intent, "Elegir"));
				}catch (ActivityNotFoundException ex){
					Log.e("Error lanzado email", "No se ha podido lanzar" + ex.getMessage());
				}
				
				
			}
		});
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enviar_email, menu);
		return true;
	}



	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
