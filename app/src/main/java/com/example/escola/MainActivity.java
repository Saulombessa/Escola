package com.example.escola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //criando variaveis para atribuir nos componentes.
    EditText edtN1, edtN2;
    TextView txtM, txtSit;
    ImageView imgSit;
    LinearLayout layResult;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //atribuindo as variaveis nos componentes.
        edtN1 = findViewById(R.id.edtN1);
        edtN2 = findViewById(R.id.edtN2);
        txtM = findViewById(R.id.txtMedia);
        txtSit = findViewById(R.id.txtSit);
        layResult = findViewById(R.id.layResultado);
        imgSit = findViewById(R.id.imgSit);
        //colocando o layout do resultado invisivel.
        layResult.setVisibility(View.INVISIBLE);
        //teclado
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    public void calcular(View view) {
        //validação com o boolean "ok" para so executar o app se os valores forem passados corretamente
        boolean ok = true;
        if (edtN1.getText().toString().trim().isEmpty()){
            ok = false;
            //mandando msg de error para o usuario
            edtN1.setError(getString(R.string.msgError));
        }
        if (edtN2.getText().toString().trim().isEmpty()){
            ok = false;
            //mandando msg de error para o usuario
            edtN2.setError(getString(R.string.msgError));
        }
        if(ok){
        //colocando o layout do resultado visivel.
        layResult.setVisibility(View.VISIBLE);
        //fazendo o teclado sumir.
        imm.hideSoftInputFromWindow(edtN1.getWindowToken(),0);
        // pegando oq foi calculado e atribuindo a uma variavel.
        float n1 = Float.parseFloat(edtN1.getText().toString());
        float n2 = Float.parseFloat(edtN2.getText().toString());
        // pegando os valores e calculando a media.
        float m = (n1 + n2) /2;

        //colocando a media no local reservado para media.
        txtM.setText(String.format("%.1f", m));
        //criando a logica condicional do app.
        if (m < 5) {
            //pegando a mensagem do TRANSLATION EDITOR e colocando no txtSit.
            txtSit.setText(getString(R.string.strSituRep));
            //mundando a cor do texto para cor generica.
            txtSit.setTextColor(Color.RED);
            //criando uma mensagem TOAST com texto em aspas
            Toast.makeText(getApplicationContext(),":,((", Toast.LENGTH_SHORT).show();
            //colocando imagem da pasta drawable.
            imgSit.setImageResource(R.drawable.emojireprovado);
        } else if (m < 7) {
            txtSit.setText(getString(R.string.strSituRec));
            //mundando a cor do texto para cor personalizada, com numero da cor entre aspas.
            txtSit.setTextColor(Color.parseColor("#0e801b"));
            //criando uma mensagem TOAST usando uma msg do TRANSLATION EDITOR
            Toast.makeText(getApplicationContext(),getString(R.string.strSituRec), Toast.LENGTH_SHORT).show();
            imgSit.setImageResource(R.drawable.emojirecuperacao);
        } else {
            txtSit.setText(getString(R.string.strSituApr));
            //mudando a cor do texto usando uma cor editada no VALUES
            txtSit.setTextColor(getResources().getColor(R.color.AzulAprovado));
            //criando uma mensagem TOAST usando uma msg do TRANSLATION EDITOR
            Toast.makeText(getApplicationContext(),getString(R.string.strSituApr), Toast.LENGTH_SHORT).show();
            imgSit.setImageResource(R.drawable.emojiaprovado);
        }}


    }
}