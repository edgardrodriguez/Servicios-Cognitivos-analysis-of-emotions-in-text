/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.model;
import servicio.emociones;
import servicio.traductor;

/**
 *
 * @author EDGARD
 */
@Named(value = "TextoC")
@SessionScoped
public class textoC implements Serializable {

    private model model;
    private emociones emotion;
    private float dataFlotante;
    private String textoFlotante;

    public textoC() {
        model = new model();
        emotion = new emociones();
    }

    public model getModel() {
        return model;
    }

    public void generar() throws Exception {
        try {
            emociones.emoci(model);
            if (model.getAngry() > model.getBored() && model.getAngry() > model.getExcited() && model.getAngry() > model.getFear() && model.getAngry() > model.getHappy() && model.getAngry() > model.getSad()) {
                dataFlotante = model.getAngry();
                textoFlotante = "molesto";
            } else if (model.getBored() > model.getAngry() && model.getBored() > model.getExcited() && model.getBored() > model.getFear() && model.getBored() > model.getHappy() && model.getBored() > model.getSad()) {
                dataFlotante = model.getBored();
                textoFlotante = "aburrido";
            } else if (model.getExcited() > model.getAngry() && model.getExcited() > model.getBored() && model.getExcited() > model.getFear() && model.getExcited() > model.getHappy() && model.getExcited() > model.getSad()) {
                dataFlotante = model.getExcited();
                textoFlotante = "emocionado";
            } else if (model.getFear() > model.getAngry() && model.getFear() > model.getBored() && model.getFear() > model.getExcited() && model.getFear() > model.getHappy() && model.getFear() > model.getSad()) {
                dataFlotante = model.getFear();
                textoFlotante = "miedo";
            } else if (model.getHappy() > model.getAngry() && model.getHappy() > model.getBored() && model.getHappy() > model.getExcited() && model.getHappy() > model.getFear() && model.getHappy() > model.getSad()) {
                dataFlotante = model.getHappy();
                textoFlotante = "feliz";
            } else if (model.getSad() > model.getAngry() && model.getSad() > model.getBored() && model.getSad() > model.getExcited() && model.getSad() > model.getFear() && model.getSad() > model.getHappy()) {
                dataFlotante = model.getSad();
                textoFlotante = "triste";
            }
        } catch (Exception e) {
            System.out.println("Error en generar TextoC/elegir: " + e.getMessage());
        }
    }

    public void setModel(model model) {
        this.model = model;
    }

    public emociones getEmotion() {
        return emotion;
    }

    public void setEmotion(emociones emotion) {
        this.emotion = emotion;
    }

    public float getDataFlotante() {
        return dataFlotante;
    }

    public void setDataFlotante(float dataFlotante) {
        this.dataFlotante = dataFlotante;
    }

    public String getTextoFlotante() {
        return textoFlotante;
    }

    public void setTextoFlotante(String textoFlotante) {
        this.textoFlotante = textoFlotante;
    }

}
