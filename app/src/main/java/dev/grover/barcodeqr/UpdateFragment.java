package dev.grover.barcodeqr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateFragment extends DialogFragment {

    String codigo, state;
    Button btnActivarCodigo;
    TextView txtCodeFinded, txtStateFinded;

    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update, container, false);

        codigo = getArguments().getString("code");
        state = getArguments().getString("state");

        txtCodeFinded = v.findViewById(R.id.txtCodeFinded);
        txtStateFinded = v.findViewById(R.id.txtStateFinded);
        btnActivarCodigo = v.findViewById(R.id.btnActivarCodigo);

        txtCodeFinded.setText( codigo );
        txtStateFinded.setText( state );

        btnActivarCodigo.setOnClickListener( view -> {
            getFirebase( codigo );
        });

        return v;
    }

    private void getFirebase(String code_) {
        mDatabase = FirebaseDatabase.getInstance().getReference("codigos");
        mDatabase.child( code_ ).child("estado").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error al obtener la información", task.getException());
                } else {
                    try {
                        if ( Integer.parseInt(task.getResult().getValue().toString() ) == 0 ){
                            mDatabase.child(code_).child("estado").setValue(1);
                            Toast.makeText( getContext(), "Ticket "+ code_ +" activado nuevamente", Toast.LENGTH_LONG).show();
                            getActivity().finish();
                        }else {
                            Toast.makeText( getContext(), "El ticket "+ code_ +" aún no se consume", Toast.LENGTH_LONG).show();
                        }
//                        Toast.makeText(getApplicationContext(), task.getResult().toString(), Toast.LENGTH_LONG).show();
//                        Log.e("DATA----->", task.getResult().toString() );
                    } catch (Exception e) {
                        Log.e("ERROR----->", e.getMessage());
                        Toast.makeText( getContext(), "EL CODIGO NO EXISTE", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
