package com.grupo2.restaurante_inteligente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstadoRestaurante extends AppCompatActivity {
    private LineChart lineChart;
    DatabaseReference db_reference;
    //String[] names = new String[]{"Ene", "Feb", "Mar"};
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Entry> entries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_restaurante);
        iniciarBaseDeDatos();
        lineChart = (LineChart)findViewById(R.id.lineChart);
        consultar();

    }

    public void iniciarBaseDeDatos(){
        db_reference = FirebaseDatabase.getInstance().getReference();
    }

    public void consultar(){

        final Context that = this;
        DatabaseReference registros = db_reference.child("Registros");
        registros.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Integer> mesas_cuenta = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        HashMap<String, Object> reg = (HashMap<String, Object>) snapshot.getValue();
                        if (reg.get("Mesa") != null && reg.get("Silla") != null) {
                            String mesa = reg.get("Mesa").toString();
                            String silla = reg.get("Silla").toString();
                            String nombre = String.format("M %s, S %s",mesa,silla);
                            Integer i = 0;
                            if (mesas_cuenta.get(nombre) != null) {
                                i = mesas_cuenta.get(nombre);
                            }
                            i++;
                            mesas_cuenta.put(nombre, i);
                        }
                    } catch (Exception e) {
                        throw e;
                    }
                }
                int contador = 0;
                for (Map.Entry<String, Integer> entry : mesas_cuenta.entrySet()) {
                    names.add(entry.getKey());
                    entries.add(new Entry(contador, entry.getValue()/2));
                    contador++;
                }

                LineDataSet lineDataSet = new LineDataSet(entries, "Uso de las sillas");
                lineDataSet.setColor(ContextCompat.getColor(that, R.color.colorPrimary));
                lineDataSet.setValueTextColor(ContextCompat.getColor(that, R.color.colorPrimaryDark));
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                ValueFormatter formatter = new ValueFormatter() {
                    @Override
                    public String getAxisLabel(float value, AxisBase axis) {
                        return names.get((int) value);
                    }
                };
                xAxis.setGranularity(1f);
                xAxis.setValueFormatter(formatter);

                YAxis yAxisRight = lineChart.getAxisRight();
                yAxisRight.setEnabled(false);

                YAxis yAxisLeft = lineChart.getAxisLeft();
                yAxisLeft.setGranularity(1f);

                LineData data = new LineData(lineDataSet);
                lineChart.setData(data);
                lineChart.getDescription().setText("Veces que se ocupa una silla");
                lineChart.setExtraBottomOffset(50);
                lineChart.animateX(1500);
                lineChart.invalidate();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
