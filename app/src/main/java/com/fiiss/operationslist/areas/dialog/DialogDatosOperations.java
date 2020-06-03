package com.fiiss.operationslist.areas.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fiiss.operationslist.R;

public class DialogDatosOperations extends Dialog {

    private String title;

    private EditText editParametroUno;
    private EditText EditParametroDos;

    private Button btnClear;
    private Button btnCalculate;

    private TextView txtResult;

    private TextView txtParametroUno;
    private TextView txtParametroDos;

    private LinearLayout linContent;

    public DialogDatosOperations(Context context, String title) {
        super(context);
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogo_datos_operation);

        TextView txttitle = findViewById(R.id.title);
        txttitle.setText(title);

        editParametroUno = findViewById(R.id.editParametroUno);
        EditParametroDos = findViewById(R.id.EditParametroDos);
        txtParametroUno = findViewById(R.id.txtParametroUno);
        txtParametroDos = findViewById(R.id.txtParametroDos);

        linContent = findViewById(R.id.linContent);

        btnClear = findViewById(R.id.btnClear);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Button getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(Button btnClear) {
        this.btnClear = btnClear;
    }

    public Button getBtnCalculate() {
        return btnCalculate;
    }

    public void setBtnCalculate(Button btnCalculate) {
        this.btnCalculate = btnCalculate;
    }

    public TextView getTxtResult() {
        return txtResult;
    }

    public void setTxtResult(TextView txtResult) {
        this.txtResult = txtResult;
    }

    public EditText getEditParametroUno() {
        return editParametroUno;
    }

    public void setEditParametroUno(EditText editParametroUno) {
        this.editParametroUno = editParametroUno;
    }

    public EditText getEditParametroDos() {
        return EditParametroDos;
    }

    public void setEditParametroDos(EditText editParametroDos) {
        EditParametroDos = editParametroDos;
    }

    public TextView getTxtParametroUno() {
        return txtParametroUno;
    }

    public void setTxtParametroUno(TextView txtParametroUno) {
        this.txtParametroUno = txtParametroUno;
    }

    public TextView getTxtParametroDos() {
        return txtParametroDos;
    }

    public void setTxtParametroDos(TextView txtParametroDos) {
        this.txtParametroDos = txtParametroDos;
    }

    public LinearLayout getLinContent() {
        return linContent;
    }

    public void setLinContent(LinearLayout linContent) {
        this.linContent = linContent;
    }
}
