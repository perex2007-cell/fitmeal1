/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.service;

import com.fitmeal.model.InfoItem;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Yesid Ocampo
 */

public class InfoService {

    private List<InfoItem> infoItems = new ArrayList<>();

    public InfoService() {

        infoItems.add(new InfoItem(
                "Importancia de la hidratación ",
                "Beber suficiente agua ayuda a regular la temperatura corporal y mejora el rendimiento físico."
        ));

        infoItems.add(new InfoItem(
                "Horas de sueño ",
                "Dormir entre 7 y 9 horas favorece la recuperación muscular y el bienestar general."
        ));

        infoItems.add(new InfoItem(
                "Alimentación balanceada ",
                "Una dieta equilibrada debe incluir proteínas, carbohidratos y grasas saludables."
        ));

        infoItems.add(new InfoItem(
                "Actividad física regular ",
                "Realizar ejercicio al menos 3 veces por semana mejora la salud cardiovascular."
        ));

        infoItems.add(new InfoItem(
                "Errores comunes",
                "Saltarse comidas o entrenar sin descanso puede afectar negativamente los resultados."
        ));
    }

    public List<InfoItem> getAllInfo() {
        return infoItems;
    }
}

