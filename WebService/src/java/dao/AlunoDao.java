/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.DbCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;
import model.Nota;

/**
 *
 * @author ocris
 */
public class AlunoDao {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public List<Aluno> getAlunos() throws SQLException, ClassNotFoundException {
        sql = "SELECT * FROM aluno \n" +
            "INNER JOIN curso \n" +
            "ON aluno.CURSO_idCURSO = curso.idCURSO \n" +
            " ORDER BY MATRICULA ASC ";

        List<Aluno> Alunos = new ArrayList<>();
        con = DbCon.openCon();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setMatricula(rs.getInt("MATRICULA"));
            aluno.setAlunoNome(rs.getString("ALUNO_NOME"));
            aluno.setIdCurso(rs.getInt("CURSO_idCURSO"));
            aluno.setIdUsuario(rs.getInt("idusuario"));
            aluno.setNomeCurso(rs.getString("NOME_CURSO"));
            Alunos.add(aluno);
        }
        return Alunos;
    }

    public int atualizar(Aluno aluno) throws SQLException, ClassNotFoundException {

        //TODO: rotina de atualizar a avaliação;  
        sql = "UPDATE aluno SET \n"
                + "ALUNO_NOME = ?,\n"
                + "idusuario = ? \n"
                + "WHERE MATRICULA = ?";
        con = DbCon.openCon();
        pst = con.prepareStatement(sql);
        pst.setString(1, aluno.getAlunoNome());
        pst.setInt(2, aluno.getIdUsuario());
        pst.setInt(3, aluno.getMatricula());
        int result = pst.executeUpdate();
        DbCon.closeCon();
        return result;
    }

    public int salvar(Aluno aluno) throws ClassNotFoundException, SQLException {
        try {
            //TODO: rotina para inserir uma nova avaliação;
            sql = "INSERT INTO aluno ("
                    + "ALUNO_NOME,"
                    + "CURSO_idCURSO,"
                    + "idusuario"
                    + ") VALUES (?,?,?);"
                    + "";

            con = DbCon.openCon();
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, aluno.getAlunoNome());
            pst.setInt(2, aluno.getIdCurso());
            pst.setInt(3, aluno.getIdUsuario());
            int result = pst.executeUpdate();
            DbCon.closeCon();

            return result;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
     public int delete(int idAluno) throws ClassNotFoundException, SQLException {
        try {
            //TODO: rotina para inserir uma nova avaliação;
            sql = "DELETE FROM aluno WHERE MATRICULA = ?";

            con = DbCon.openCon();
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, idAluno);
            int result = pst.executeUpdate();
            DbCon.closeCon();

            return result;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public List<Nota> getNotasAluno(String matricula) throws ClassNotFoundException, SQLException {
        sql = "SELECT aluno.ALUNO_NOME , d.DISCIPLINA_NOME AS materia\n"
                + "	,b.*\n"
                + "	,CAST(((IFNULL(b.TRABALHO_ACADEMICO_AV1, 0) + IFNULL(b.APS1, 0) + IFNULL(b.TRABALHO_ACADEMICO_AV2, 0) + IFNULL(b.APS2, 0) + IFNULL(b.TRABALHO_ACADEMICO_AV3, 0)) / (IF(b.TRABALHO_ACADEMICO_AV1 IS NULL, 0, 1) + IF(b.TRABALHO_ACADEMICO_AV2 IS NULL, 0, 1) + IF(b.TRABALHO_ACADEMICO_AV3 IS NULL, 0, 1))) AS DECIMAL(19,1)) AS media\n"
                + "	,IF(((IFNULL(b.TRABALHO_ACADEMICO_AV1, 0) + IFNULL(b.APS1, 0) + IFNULL(b.TRABALHO_ACADEMICO_AV2, 0) + IFNULL(b.APS2, 0) + IFNULL(b.TRABALHO_ACADEMICO_AV3, 0)) / (IF(b.TRABALHO_ACADEMICO_AV1 IS NULL, 0, 1) + IF(b.TRABALHO_ACADEMICO_AV2 IS NULL, 0, 1) + IF(b.TRABALHO_ACADEMICO_AV3 IS NULL, 0, 1))) > 7 ,\"Aprovado\", \"Reprovado\") AS resultado\n"
                + "FROM curso_has_aluno AS a\n"
                + "INNER JOIN avaliacao AS b ON a.ALUNO_MATRICULA = b.idMATRICULA\n"
                + "INNER JOIN curso AS c ON a.curso_idcurso = c.idcurso\n"
                + "INNER JOIN disciplina AS d ON d.idDISCIPLINA = b.idDISCIPLINA\n"
                + "INNER JOIN aluno AS aluno ON a.ALUNO_MATRICULA = aluno.matricula\n"
                + "WHERE aluno.idusuario = '" + matricula + "'";

        List<Nota> notas = new ArrayList<>();
        con = DbCon.openCon();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            Nota anota = new Nota();
            anota.setMateria(rs.getString("materia"));
            anota.setNotaAv1(rs.getFloat("TRABALHO_ACADEMICO_AV1"));
            anota.setNotaAv2(rs.getFloat("TRABALHO_ACADEMICO_AV2"));
            anota.setNotaAv3(rs.getFloat("TRABALHO_ACADEMICO_AV3"));
            anota.setNotaAps1(rs.getFloat("APS1"));
            anota.setNotaAps2(rs.getFloat("APS2"));
            anota.setMedia(rs.getFloat("media"));
            anota.setResultado(rs.getString("resultado"));
            anota.setNomeAluno(rs.getString("ALUNO_NOME"));
            notas.add(anota);
        }
        return notas;
    }

}
