package app.anjo.moviesboss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Start extends Activity {

    private EditText texto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btnOne = (Button) findViewById(R.id.boton1);

        texto = (EditText) findViewById(R.id.pelicula);
        ImageView imagen = (ImageView) findViewById(R.id.uc3m);

        Picasso.with(getApplicationContext()).load("http://www.uc3m.es/ss/Satellite?blobcol=urldata&blobheadername1=cache-control&blobheadervalue1=public%2C+max-age%3D63072000&blobkey=id&blobtable=MungoBlobs&blobwhere=1371546350999&ssbinary=true").into(imagen);


        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                final String texto_cadena = texto.getText().toString();


                intent.putExtra("nombre_peli",texto_cadena);
                startActivity(intent);


            }
        });


    }
}

