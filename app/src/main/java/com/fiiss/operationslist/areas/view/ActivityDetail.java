package com.fiiss.operationslist.areas.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fiiss.operationslist.R;
import com.fiiss.operationslist.areas.adapter.DetailRecycler;
import com.fiiss.operationslist.areas.dialog.DialogDatosOperations;
import com.fiiss.operationslist.areas.interfaces.OnclicRecycleAdapterDetail;
import com.fiiss.operationslist.entities.Areas;
import com.fiiss.operationslist.entities.MenuApp;

import java.util.Locale;

public class ActivityDetail extends AppCompatActivity implements OnclicRecycleAdapterDetail {

    private MenuApp menuApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        RecyclerView rcyDetail = findViewById(R.id.rcyDetail);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        menuApp = (MenuApp) bundle.getSerializable("data");
        assert menuApp != null;

        DetailRecycler mAdapter = new DetailRecycler(this, menuApp.getAreas(), this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rcyDetail.setLayoutManager(mLayoutManager);
        rcyDetail.setItemAnimator(new DefaultItemAnimator());
        rcyDetail.setAdapter(mAdapter);
    }

    //region panel to open the calculate volume dialog
    private void callDialogArea(String title, final Areas areas) {

        final DialogDatosOperations dialogDatosCodigo = new DialogDatosOperations(this, title);
        dialogDatosCodigo.setCancelable(false);
        dialogDatosCodigo.show();
        if (areas.getUid() == 1) {
            // Cuadado
            dialogDatosCodigo.getTxtParametroUno().setText(getString(R.string.base_altura));
            dialogDatosCodigo.getLinContent().setVisibility(View.GONE);

        } else if (areas.getUid() == 2 || areas.getUid() == 3) {
            // Rectangulo // Triangulo
            dialogDatosCodigo.getTxtParametroUno().setText(getString(R.string.indica_base));
            dialogDatosCodigo.getTxtParametroDos().setText(getString(R.string.indica_altura));

            dialogDatosCodigo.getLinContent().setVisibility(View.VISIBLE);

        } else if (areas.getUid() == 4) {
            // Circulo
            dialogDatosCodigo.getTxtParametroUno().setText(getString(R.string.indica_radio));
            dialogDatosCodigo.getLinContent().setVisibility(View.GONE);
        }

        dialogDatosCodigo.getBtnCalculate().setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (areas.getUid() == 1) {
                    // Cuadado
                    if (dialogDatosCodigo.getEditParametroUno().getText().toString().isEmpty()) {
                        dialogDatosCodigo.getEditParametroUno().setFocusable(true);
                        dialogDatosCodigo.getEditParametroUno().setFocusableInTouchMode(true);
                        dialogDatosCodigo.getEditParametroUno().requestFocus();
                        dialogDatosCodigo.getEditParametroUno().setError(getString(R.string.file_required));
                    } else {
                        double result = Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString()) *
                                Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString());
                        dialogDatosCodigo.getTxtResult().setText(String.format("%1s %1.2f", getString(R.string.area_cuadrado), result));
                    }
                } else if (areas.getUid() == 2 || areas.getUid() == 3) {

                    if (dialogDatosCodigo.getEditParametroUno().getText().toString().isEmpty()) {
                        dialogDatosCodigo.getEditParametroUno().setFocusable(true);
                        dialogDatosCodigo.getEditParametroUno().setFocusableInTouchMode(true);
                        dialogDatosCodigo.getEditParametroUno().requestFocus();
                        dialogDatosCodigo.getEditParametroUno().setError(getString(R.string.file_required));

                    } else if (dialogDatosCodigo.getEditParametroDos().getText().toString().isEmpty()) {
                        dialogDatosCodigo.getEditParametroDos().setFocusable(true);
                        dialogDatosCodigo.getEditParametroDos().setFocusableInTouchMode(true);
                        dialogDatosCodigo.getEditParametroDos().requestFocus();
                        dialogDatosCodigo.getEditParametroDos().setError(getString(R.string.file_required));
                    } else {
                        // Rectangulo // Triangulo
                        if (areas.getUid() == 2) {
                            //rectangulo
                            double result =  Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString()) *
                                    Double.parseDouble(dialogDatosCodigo.getEditParametroDos().getText().toString());

                            dialogDatosCodigo.getTxtResult().setText(String.format("%1s %1.2f", getString(R.string.area_rectangulo), result));

                        } else {
                            //triangulo
                            double result = (Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString()) *
                                    Double.parseDouble(dialogDatosCodigo.getEditParametroDos().getText().toString())/2);

                            dialogDatosCodigo.getTxtResult().setText(String.format("%1s %1.2f", getString(R.string.area_triangulo), result));
                        }
                    }

                } else if (areas.getUid() == 4) {
                    // Circulo
                    if (dialogDatosCodigo.getEditParametroUno().getText().toString().isEmpty()) {
                        dialogDatosCodigo.getEditParametroUno().setFocusable(true);
                        dialogDatosCodigo.getEditParametroUno().setFocusableInTouchMode(true);
                        dialogDatosCodigo.getEditParametroUno().requestFocus();
                        dialogDatosCodigo.getEditParametroUno().setError(getString(R.string.file_required));

                    } else {
                        dialogDatosCodigo.getTxtResult().setText(String.format("%1s %1.2f", getString(R.string.area_circulo),
                                Math.PI*(Math.pow(Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString()), 2))));
                    }
                }

            }
        });
        dialogDatosCodigo.getBtnClear().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDatosCodigo.dismiss();
            }
        });

    }
    //endregion

    //region panel to open the calculate area dialog
    @SuppressLint("DefaultLocale")
    private void callDialogVolumen(String title, final Areas areas) {
        final DialogDatosOperations dialogDatosCodigo = new DialogDatosOperations(this, title);
        dialogDatosCodigo.setCancelable(false);
        dialogDatosCodigo.show();

        if (areas.getUid() == 1) {
            //Esfera
            dialogDatosCodigo.getTxtParametroUno().setText(getString(R.string.volumen_esfera));
            dialogDatosCodigo.getLinContent().setVisibility(View.GONE);

        } else if (areas.getUid() == 2) {
            //Cubo
            dialogDatosCodigo.getTxtParametroUno().setText(getString(R.string.volumen_cubo));
            dialogDatosCodigo.getLinContent().setVisibility(View.GONE);

        } else if (areas.getUid() == 3) {
            //Cono
            dialogDatosCodigo.getTxtParametroUno().setText(getString(R.string.indica_radio));
            dialogDatosCodigo.getTxtParametroDos().setText(getString(R.string.indica_altura));

            dialogDatosCodigo.getLinContent().setVisibility(View.VISIBLE);

        } else if (areas.getUid() == 4) {
            //Cilindro
            dialogDatosCodigo.getTxtParametroUno().setText(getString(R.string.indica_radio));
            dialogDatosCodigo.getTxtParametroDos().setText(getString(R.string.indica_altura));

            dialogDatosCodigo.getLinContent().setVisibility(View.VISIBLE);

        }

        dialogDatosCodigo.getBtnCalculate().setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (areas.getUid() == 1) {
                    //Esfera
                    double radio = calcularVolumenEsfera(Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString()));
                    dialogDatosCodigo.getTxtResult().setText(String.format("%1s %1.2f", getString(R.string.volumen_esfera_parameter), radio));
                } else if (areas.getUid() == 2) {
                    //Cubo
                    double arista = calcularVolumenCubo(Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString()));
                    dialogDatosCodigo.getTxtResult().setText(String.format("%1s %1.2f", getString(R.string.volumen_cubo_parameter), arista));
                } else if (areas.getUid() == 3) {
                    //Cono
                    double volumen = calcularVolumenCono(Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString()), Double.parseDouble(dialogDatosCodigo.getEditParametroDos().getText().toString()));
                    dialogDatosCodigo.getTxtResult().setText(String.format("%1s %1.2f", getString(R.string.volumen_cono_parameter), volumen));
                } else if (areas.getUid() == 4) {
                    //Cilindro
                    double volumen = calcularVolumenCilindro(Double.parseDouble(dialogDatosCodigo.getEditParametroUno().getText().toString()), Double.parseDouble(dialogDatosCodigo.getEditParametroDos().getText().toString()));
                    dialogDatosCodigo.getTxtResult().setText(String.format("%1s %1.2f", getString(R.string.volumen_cilindro_parameter), volumen));
                }
            }

        });

        dialogDatosCodigo.getBtnClear().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDatosCodigo.dismiss();
            }
        });

    }
    //endregion

    @Override
    public void onClicRecycle(Areas areas) {

        if (menuApp.getUid() == 1) {
            if (Locale.getDefault().getLanguage().equals("en")) {
                callDialogArea(areas.getNameUS(), areas);
            } else {
                callDialogArea(areas.getName(), areas);
            }
        } else {
            if (Locale.getDefault().getLanguage().equals("en")) {
                callDialogVolumen(areas.getNameUS(), areas);
            } else {
                callDialogVolumen(areas.getName(), areas);
            }
        }
    }

    static double calcularVolumenEsfera(Double radio) {
        return Math.pow(radio,3);
    }

    static double calcularVolumenCubo(double arista) {
        return Math.pow(arista, 3);
    }

    static double calcularVolumenCono(double radio, double altura) {
        return (Math.PI*(radio*radio)*altura)/3;
    }

    static double calcularVolumenCilindro(double radio, double altura) {
        return Math.PI*(radio*radio)*altura;
    }

}