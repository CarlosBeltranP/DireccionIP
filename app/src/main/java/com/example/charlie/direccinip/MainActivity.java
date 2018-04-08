package com.example.charlie.direccinip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declarando variables
    EditText octal1, octal2, octal3, octal4, m;
    int m1, m2, m3, m4, cantH, pRed, pHost;
    float x, y, r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        octal1=(EditText) findViewById(R.id.Oct1);
        octal2=(EditText) findViewById(R.id.Oct2);
        octal3=(EditText) findViewById(R.id.Oct3);
        octal4=(EditText) findViewById(R.id.Oct4);

        m=(EditText)findViewById(R.id.mask);

        Button botonCalcular = findViewById(R.id.btnCalcular);
        ImageButton botonLimpiar = findViewById(R.id.btnLimpiar);

        //Declarando nuestras View variables
        final TextView nId1 = (TextView) findViewById(R.id.NetID1);
        final TextView nId2 = (TextView) findViewById(R.id.NetID2);
        final TextView nId3 = (TextView) findViewById(R.id.NetID3);
        final TextView nId4 = (TextView) findViewById(R.id.NetID4);

        final TextView mask1 = (TextView) findViewById(R.id.M1);
        final TextView mask2 = (TextView) findViewById(R.id.M2);
        final TextView mask3 = (TextView) findViewById(R.id.M3);
        final TextView mask4 = (TextView) findViewById(R.id.M4);

        final TextView bd1 = (TextView) findViewById(R.id.broadId1);
        final TextView bd2 = (TextView) findViewById(R.id.broadId2);
        final TextView bd3 = (TextView) findViewById(R.id.broadId3);
        final TextView bd4 = (TextView) findViewById(R.id.broadId4);

        final TextView cantHost = (TextView) findViewById(R.id.cantHost);
        final TextView parteRed = (TextView) findViewById((R.id.parteRed));
        final TextView parteHost = (TextView) findViewById((R.id.parteHost));


        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mascara = Integer.valueOf(m.getText().toString());
                int o1 = Integer.valueOf(octal1.getText().toString());
                int o2 = Integer.valueOf(octal2.getText().toString());
                int o3 = Integer.valueOf(octal3.getText().toString());
                int o4 = Integer.valueOf(octal4.getText().toString());

                //calcula parte entera (octetos 255) y residuo (para calcular el valor de la mascara del ultimo octeto)
                x = mascara / 8;
                y = mascara % 8;
                r = 8 - y;

                if(x==0){ //cuando el valor de la mascara sea 8 o menos
                    m1 = (int) (256 - (Math.pow(2, (8-y)))); //calculo el valor del primer octeto de la mascara
                    m2 = 0; //los demás octetos serán cero
                    m3 = 0;
                    m4 = 0;

                    //ingreso a las variables mask el valor calculado de los octetos de la mascara
                    mask1.setText(""+m1);
                    mask2.setText(""+m2);
                    mask3.setText(""+m3);
                    mask4.setText(""+m4);

                    //CALCULO DE NETID
                    /*realizo la operacion binaria AND a nivel de bits entre los valores de cada octeto de la mascara
                    con los valores de cada octeto de la direccion IP ingresada*/
                    int n1 = o1 & m1;
                    int n2 = o2 & m2;
                    int n3 = o3 & m3;
                    int n4 = o4 & m4;
                    //muestro en los TextView los resultados de la NetId
                    nId1.setText(""+n1);
                    nId2.setText(""+n2);
                    nId3.setText(""+n3);
                    nId4.setText(""+n4);

                    //CALCULO DE BROADCASTID
                    int b1 = (int) (n1 + (Math.pow(2, r) -1));
                    int b2 = 255;
                    int b3 = 255;
                    int b4 = 255;
                    //muestro en los TextView los resultados de la BroadcastID
                    bd1.setText(""+b1);
                    bd2.setText(""+b2);
                    bd3.setText(""+b3);
                    bd4.setText(""+b4);

                    //CALCULO DE cantidad de Host en la red
                    cantH = (int) ((Math.pow(2, (32-mascara)))-2);
                    //muestro en el Txtview el resultado de la cantidad de Host
                    cantHost.setText(""+cantH);

                    //CALCULO DE PARTE DE RED
                    pRed = (int) Math.pow(2, mascara);
                    parteRed.setText(""+pRed);

                    //CALCULO DE PARTE DE HOST
                    pHost = (int) Math.pow(2, (32 - mascara));
                    parteHost.setText(""+pHost);

                }else{
                    if(x==1){
                        m1 = 255;
                        m2 = (int) (256 - (Math.pow(2, (8-y))));
                        m3 = 0;
                        m4 = 0;

                        mask1.setText(""+m1);
                        mask2.setText(""+m2);
                        mask3.setText(""+m3);
                        mask4.setText(""+m4);

                        int n1 = o1 & m1;
                        int n2 = o2 & m2;
                        int n3 = o3 & m3;
                        int n4 = o4 & m4;

                        nId1.setText(""+n1);
                        nId2.setText(""+n2);
                        nId3.setText(""+n3);
                        nId4.setText(""+n4);

                        int b1 = n1;
                        int b2 = (int) (n2 + (Math.pow(2, r) -1));
                        int b3 = 255;
                        int b4 = 255;

                        bd1.setText(""+b1);
                        bd2.setText(""+b2);
                        bd3.setText(""+b3);
                        bd4.setText(""+b4);

                        cantH = (int) ((Math.pow(2, (32-mascara)))-2);
                        cantHost.setText(""+cantH);

                        pRed = (int) Math.pow(2, mascara);
                        parteRed.setText(""+pRed);

                        pHost = (int) Math.pow(2, (32 - mascara));
                        parteHost.setText(""+pHost);

                    }else{
                        if(x==2){

                            m1 = 255;
                            m2 = 255;
                            m3 = (int) (256 - (Math.pow(2, (8-y))));
                            m4 = 0;

                            mask1.setText(""+m1);
                            mask2.setText(""+m2);
                            mask3.setText(""+m3);
                            mask4.setText(""+m4);

                            int n1 = o1 & m1;
                            int n2 = o2 & m2;
                            int n3 = o3 & m3;
                            int n4 = o4 & m4;

                            nId1.setText(""+n1);
                            nId2.setText(""+n2);
                            nId3.setText(""+n3);
                            nId4.setText(""+n4);

                            int b1 = n1;
                            int b2 = n2;
                            int b3 = (int) (n3 + (Math.pow(2, r) -1));
                            int b4 = 255;

                            bd1.setText(""+b1);
                            bd2.setText(""+b2);
                            bd3.setText(""+b3);
                            bd4.setText(""+b4);

                            cantH = (int) ((Math.pow(2, (32-mascara)))-2);
                            cantHost.setText(""+cantH);

                            pRed = (int) Math.pow(2, mascara);
                            parteRed.setText(""+pRed);

                            pHost = (int) Math.pow(2, (32 - mascara));
                            parteHost.setText(""+pHost);

                        }else{
                            if(x==3){

                                m1 = 255;
                                m2 = 255;
                                m3 = 255;
                                m4 = (int) (256 - (Math.pow(2, (8-y))));

                                mask1.setText(""+m1);
                                mask2.setText(""+m2);
                                mask3.setText(""+m3);
                                mask4.setText(""+m4);

                                int n1 = o1 & m1;
                                int n2 = o2 & m2;
                                int n3 = o3 & m3;
                                int n4 = o4 & m4;

                                nId1.setText(""+n1);
                                nId2.setText(""+n2);
                                nId3.setText(""+n3);
                                nId4.setText(""+n4);

                                int b1 = n1;
                                int b2 = n2;
                                int b3 = n3;
                                int b4 = (int) (n4 + (Math.pow(2, r) -1));

                                bd1.setText(""+b1);
                                bd2.setText(""+b2);
                                bd3.setText(""+b3);
                                bd4.setText(""+b4);

                                cantH = (int) ((Math.pow(2, (32-mascara)))-2);
                                cantHost.setText(""+cantH);

                                pRed = (int) Math.pow(2, mascara);
                                parteRed.setText(""+pRed);

                                pHost = (int) Math.pow(2, (32 - mascara));
                                parteHost.setText(""+pHost);

                            }else{
                                if(x==4){
                                    m1 = 255;
                                    m2 = 255;
                                    m3 = 255;
                                    m4 = 255;

                                    mask1.setText(""+m1);
                                    mask2.setText(""+m2);
                                    mask3.setText(""+m3);
                                    mask4.setText(""+m4);

                                    int n1 = o1 & m1;
                                    int n2 = o2 & m2;
                                    int n3 = o3 & m3;
                                    int n4 = o4 & m4;

                                    nId1.setText(""+n1);
                                    nId2.setText(""+n2);
                                    nId3.setText(""+n3);
                                    nId4.setText(""+n4);

                                    int b1 = n1;
                                    int b2 = n2;
                                    int b3 = n3;
                                    int b4 = n4;

                                    bd1.setText(""+b1);
                                    bd2.setText(""+b2);
                                    bd3.setText(""+b3);
                                    bd4.setText(""+b4);

                                    cantH = (int) ((Math.pow(2, (32-mascara)))-2);
                                    cantHost.setText(""+cantH);

                                    pRed = (int) Math.pow(2, mascara);
                                    parteRed.setText(""+pRed);

                                    pHost = (int) Math.pow(2, (32 - mascara));
                                    parteHost.setText(""+pHost);

                                }
                            }
                        }
                    }
                }
            }
        });

        //boton limpiar campos
        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                octal1.setText("");
                octal2.setText("");
                octal3.setText("");
                octal4.setText("");
                m.setText("");

                mask1.setText("");
                mask2.setText("");
                mask3.setText("");
                mask4.setText("");

                nId1.setText("");
                nId2.setText("");
                nId3.setText("");
                nId4.setText("");

                bd1.setText("");
                bd2.setText("");
                bd3.setText("");
                bd4.setText("");

                cantHost.setText("");
                parteHost.setText("");
                parteRed.setText("");


            }
        });
    }
}
