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
import model.Avaliacao;
import model.Usuario;

/**
 *
 * @author ocris
 */
public class AvaliacaoDao {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public List<Avaliacao> getAvalicaoAluno(int idAluno) throws ClassNotFoundException, SQLException {
        List<Avaliacao> ListAvalicao = new ArrayList<>();
        Avaliacao avalicao = null;
        sql = "SELECT aluno.MATRICULA\n"
                + "	,aluno.ALUNO_NOME\n"
                + "	,disciplina.DISCIPLINA_NOME\n"
                + "	,avaliacao.*\n"
                + "FROM aluno\n"
                + "LEFT JOIN avaliacao ON aluno.MATRICULA = avaliacao.idMATRICULA\n"
                + "LEFT JOIN disciplina ON disciplina.idDISCIPLINA = avaliacao.idDISCIPLINA\n"
                + "WHERE aluno.MATRICULA = '" + idAluno + "'";
        con = DbCon.openCon();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            avalicao = new Avaliacao();
            avalicao.setMateria(rs.getString("DISCIPLINA_NOME"));
            avalicao.setAv1(rs.getFloat("TRABALHO_ACADEMICO_AV1"));
            avalicao.setAv2(rs.getFloat("TRABALHO_ACADEMICO_AV2"));
            avalicao.setAv3(rs.getFloat("TRABALHO_ACADEMICO_AV3"));
            avalicao.setAps1(rs.getFloat("APS1"));
            avalicao.setAps2(rs.getFloat("APS2"));
            avalicao.setIdAvaliacao(rs.getInt("idAVALIACAO"));
            avalicao.setNomeAluno(rs.getString("ALUNO_NOME"));
            avalicao.setMatricula(rs.getInt("MATRICULA"));
            avalicao.setIdDisciplina(rs.getInt("idDISCIPLINA"));
            ListAvalicao.add(avalicao);
        }
        DbCon.closeCon();
        return ListAvalicao;
    }

    public int atualizar(Avaliacao avalicao) throws SQLException, ClassNotFoundException {

        //TODO: rotina de atualizar a avaliação;  
        sql = "UPDATE avaliacao SET \n"
                + "TRABALHO_ACADEMICO_AV1 = ?,\n"
                + "TRABALHO_ACADEMICO_AV2 = ?,\n"
                + "TRABALHO_ACADEMICO_AV3 = ?,\n"
                + "APS1 = ?,\n"
                + "APS2 = ?\n"
                + "WHERE idAVALIACAO = ?";
        con = DbCon.openCon();
        pst = con.prepareStatement(sql);
        pst.setFloat(1, avalicao.getAv1());
        pst.setFloat(2, avalicao.getAv2());
        pst.setFloat(3, avalicao.getAv3());
        pst.setFloat(4, avalicao.getAps1());
        pst.setFloat(5, avalicao.getAps2());
        pst.setInt(6, avalicao.getIdAvaliacao());

        int result = pst.executeUpdate();
        DbCon.closeCon();

        return result;
    }

    public int salvar(Avaliacao avalicao) throws ClassNotFoundException, SQLException {
        try {
            //TODO: rotina para inserir uma nova avaliação;
            sql = "INSERT INTO avaliacao ("
                    + "`TRABALHO_ACADEMICO_AV1`,"
                    + "`APS1`,"
                    + "`TRABALHO_ACADEMICO_AV2`,"
                    + "`APS2`,"
                    + "`TRABALHO_ACADEMICO_AV3`,"
                    + "`idMATRICULA`,"
                    + "`idDISCIPLINA`)"
                    + " VALUES (?,?,?,?,?,?,?);"
                    + "";

            con = DbCon.openCon();
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, avalicao.getAv1());
            pst.setDouble(2, avalicao.getAps1());
            pst.setDouble(3, avalicao.getAv2());
            pst.setDouble(4, avalicao.getAps2());
            pst.setDouble(5, avalicao.getAv3());
            pst.setInt(6, avalicao.getMatricula());
            pst.setInt(7, avalicao.getIdDisciplina());

            int result = pst.executeUpdate();
            DbCon.closeCon();

            return result;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int delete(int idAvaliacao) throws ClassNotFoundException, SQLException {
        try {
            //TODO: rotina para inserir uma nova avaliação;
            sql = "DELETE FROM avaliacao WHERE idAVALIACAO = ?";

            con = DbCon.openCon();
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, 1);
            int result = pst.executeUpdate();
            DbCon.closeCon();

            return result;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
