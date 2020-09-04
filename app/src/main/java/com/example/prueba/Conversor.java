package com.example.prueba;

import java.text.DecimalFormat;

/* compiled from: MainActivity */
class Conversor {
    double[] valores = {1.0d, 10.7639d, 1.4285714286d, 1.19599d, 0.0022883295d, 1.428571E-4d, 1.0E-4d};

    Conversor() {
    }

    public double convertir_area(int de, int a, double cantidad) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        double[] dArr = this.valores;
        return Double.parseDouble(twoDForm.format((dArr[a] / dArr[de]) * cantidad));
    }
}
