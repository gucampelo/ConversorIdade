package com.example.calcularidade;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.estudo.conversoridade.R;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class MainActivity extends AppCompatActivity {

    private EditText inDia;
    private EditText inMes;
    private EditText inAno;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inAno = findViewById(R.id.inAno);
        inAno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        inDia = findViewById(R.id.inDia);
        inDia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        inMes = findViewById(R.id.inMes);
        inMes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        Button btnCalc = findViewById(R.id.btnCalcular);

        btnCalc.setOnClickListener(op -> calc());

    }
    private void calc() {
        try {
            // Obtém os valores de dia, mês e ano de nascimento
            int dia = Integer.parseInt(inDia.getText().toString());
            int mes = Integer.parseInt(inMes.getText().toString());
            int ano = Integer.parseInt(inAno.getText().toString());

            // Cria a data de nascimento
            LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

            // Verifica se a data de nascimento é válida
            if (dataNascimento.isAfter(LocalDate.now())) {
                Toast.makeText(this, "A data de nascimento não pode ser uma data futura.", Toast.LENGTH_LONG).show();
                return;
            }

            // Data atual
            LocalDate dataAtual = LocalDate.now();

            // Calcula a idade usando a classe Period
            Period idade = Period.between(dataNascimento, dataAtual);

            // Exibe a idade em anos, meses e dias
            txtResultado.setText(String.format("Sua idade é de %d anos, %d meses e %d dias.",
                    idade.getYears(), idade.getMonths(), idade.getDays()));

        } catch (DateTimeParseException | IllegalArgumentException e) {
            Toast.makeText(this, "Data de nascimento inválida. Por favor, tente novamente.", Toast.LENGTH_LONG).show();
        }
    }
}
