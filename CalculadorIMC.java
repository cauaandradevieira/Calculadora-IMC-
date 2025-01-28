package calculadora_imc;

import javax.swing.*;
import java.awt.*;

public class CalculadorIMC extends JFrame
{
    JTextField inputPeso;
    JTextField inputAltura;
    JTextField mostrarCategoriaImc;

    public CalculadorIMC()
    {
        // janela
        setTitle("Calculadora IMC");
        setSize(360,250);
        setLocationRelativeTo(null);
        setResizable(false);

        //PAINEL
        JPanel painel = new JPanel();
        painel.setLayout(null);

        // TEXT
        JLabel textoPeso = new JLabel("Peso: ");
        textoPeso.setBounds(20,20,100,30);

        JLabel textoAltura = new JLabel("Altura: ");
        textoAltura.setBounds(20, 50,100,30);

        JLabel textoSeuImc = new JLabel("IMC: ");
        textoSeuImc.setBounds(20, 130,100,30);

        JLabel textoSuaCategoria = new JLabel("Categoria: ");
        textoSuaCategoria.setBounds(20, 160,100,30);

        // TEXT INPUT
        inputPeso = new JTextField();
        inputPeso.setBounds(65,20,100,30);

        inputAltura = new JTextField();
        inputAltura.setBounds(65,50,100,30);

        JTextField mostrarResultadoImc = new JTextField();
        mostrarResultadoImc.setEnabled(true);
        mostrarResultadoImc.setBounds(65,130,100,30);

        mostrarCategoriaImc = new JTextField();
        mostrarCategoriaImc.setEnabled(true);
        mostrarCategoriaImc.setBounds(170,130,165,30);

        // BUTTON
        JButton botaoEnviar = new JButton("Calcular");
        botaoEnviar.setBounds(190, 20, 100, 60);
        botaoEnviar.setBackground(Color.lightGray);

        botaoEnviar.addActionListener(e -> {
            if(!verificarSeStringEhNumero()) return;
            mostrarResultadoImc.setText(String.format("%.1f",calcularIMC()));
            mostrarCategoriaImc.setText(retornarCategoriaImc());
            corCategoriaPerigosa(mostrarCategoriaImc.getText());
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        painel.add(textoSeuImc);
        painel.add(mostrarCategoriaImc);
        painel.add(mostrarResultadoImc);
        painel.add(botaoEnviar);
        painel.add(inputAltura);
        painel.add(inputPeso);
        painel.add(textoAltura);
        painel.add(textoPeso);
        add(painel);
    }

    public double calcularIMC()
    {
        try
        {
            double peso = Double.parseDouble(inputPeso.getText());
            double altura = Double.parseDouble(inputAltura.getText());
            return peso / (altura * altura);
        }

        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "DIGITE SOMENTE NUMEROS","ERRO", JOptionPane.ERROR_MESSAGE);

            throw new RuntimeException(e);
        }
    }

    public String retornarCategoriaImc()
    {
        double imc = calcularIMC();
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc >= 18.5 && imc <= 24.9) {
            return "Peso normal";
        } else if (imc >= 25 && imc <= 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30 && imc <= 34.9) {
            return "Obesidade Grau I";
        } else if (imc >= 35 && imc <= 39.9) {
            return "Obesidade Grau II (severa)";
        } else {
            return "Obesidade Grau III (mórbida)";
        }
    }

    public boolean verificarSeStringEhNumero()
    {
        char[] caracteresPeso = inputPeso.getText().toCharArray();
        char[] caracteresAltura = inputAltura.getText().toCharArray();

        for(char caracter : caracteresPeso)
        {
           if(!ehNumeroCaracter(caracter))return false;

        }

        for(char caracter : caracteresAltura)
        {
            if(!ehNumeroCaracter(caracter))return false;
        }

        System.out.println(caracteresPeso);
        System.out.println(caracteresAltura);
        return true;
    }


    public boolean ehNumeroCaracter(char caracter)
    {
        if(caracter >= '0' && caracter <= '9' || caracter == '.') return true;
        JOptionPane.showMessageDialog(null, "SOMENTE NUMEROS !!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void corCategoriaPerigosa(String categoria)
    {
        if(categoria.equals("Obesidade Grau III (mórbida)") || categoria.equals("Obesidade Grau II (severa)"))
        {
            mostrarCategoriaImc.setForeground(new Color(142, 2, 6, 255));
        }
        else if (categoria.equals("Obesidade Grau I"))
        {
            mostrarCategoriaImc.setForeground(new Color(168, 158, 4));
        }
        else
        {
            mostrarCategoriaImc.setForeground(Color.black);
        }
    }
}

