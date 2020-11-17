/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ocris
 */
public class Avaliacao {
    private String materia;
    private String nomeAluno;
    private Integer idAvaliacao;
    private float av1;
    private float aps1;
    private float av2;
    private float aps2;
    private float av3;
    private int matricula;
    private int idDisciplina;

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    } 

    public float getAv1() {
        return av1;
    }

    public void setAv1(float av1) {
        this.av1 = av1;
    }

    public float getAps1() {
        return aps1;
    }

    public void setAps1(float aps1) {
        this.aps1 = aps1;
    }

    public float getAv2() {
        return av2;
    }

    public void setAv2(float av2) {
        this.av2 = av2;
    }

    public float getAps2() {
        return aps2;
    }

    public void setAps2(float aps2) {
        this.aps2 = aps2;
    }

    public float getAv3() {
        return av3;
    }

    public void setAv3(float av3) {
        this.av3 = av3;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
    
    
    
}
