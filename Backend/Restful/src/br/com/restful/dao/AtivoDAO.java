package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Ativo;

public class AtivoDAO {

	private static AtivoDAO instance;

	public static AtivoDAO getInstance() {
		if (instance == null)
			instance = new AtivoDAO();
		return instance;
	}

	public ArrayList<Ativo> listarAtivos() {
		//ArrayList<Ativo> ativos = null;

		// teste
		/*ativos = new ArrayList<Ativo>();
		Ativo ativo = new Ativo();
		ativo.setId(01);
		ativo.setDescricao("MacBook Air 13'");
		ativo.setDtCompra(new Date());
		ativo.setFabrincante("Apple");
		ativo.setVlCompra(3900.00);
		ativo.setVlDepreciado(800.00);
		ativos.add(ativo);*/

		String sql = "SELECT * FROM lu2cas01.ATIVO";
		 
		ArrayList<Ativo> ativos = new ArrayList<Ativo>();
		 
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		 
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
		 
			while(rs.next()){
				Ativo ativo = new Ativo();
				 
				ativo.setId(rs.getLong("id"));
				ativo.setDescricao(rs.getString("descricao")); 
				ativo.setDtCompra(rs.getDate("dt_compra"));
				ativo.setFabricante(rs.getString("fabricante"));
				ativo.setVlCompra(rs.getDouble("vl_compra"));
				ativo.setVlDepreciado(rs.getDouble("vl_depreciado"));
				ativo.setCriadoPor(rs.getLong("criado_por"));
				ativo.setDtCriacao(rs.getDate("dt_cricao"));
				ativo.setIdStatus(rs.getLong("id_status"));
				ativo.setModificadoPor(rs.getLong("modificado_por"));
				ativo.setDtModificacao(rs.getDate("dt_modificao"));
				 
				ativos.add(ativo);
			}
		 } catch (Exception e) {
			 e.printStackTrace();
		 }finally{
			 try{
				 if(rs != null){
					 rs.close();
				 }
				 if(pstm != null){
					 pstm.close();
				 }
				 if(conn != null){
					 conn.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		return ativos;
	}

	public Ativo listarAtivo(long id) {
		/*
		 * Connection conexao = null; PreparedStatement pstmt = null; ResultSet
		 * rs = null;
		 */

		// teste
		/*Ativo ativo = new Ativo();
		ativo.setId(01);
		ativo.setDescricao("MacBook Air 13'");
		ativo.setDtCompra(new Date());
		ativo.setFabrincante("Apple");
		ativo.setVlCompra(3900.00);
		ativo.setVlDepreciado(800.00);*/
		
		Ativo ativo = new Ativo();
		
		String sql = "SELECT * FROM lu2cas01.ATIVO WHERE id = ?";
		 
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		 
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);
			
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
		 
			if(rs.next()){
				 
				ativo.setId(rs.getLong("id"));
				ativo.setDescricao(rs.getString("descricao")); 
				ativo.setDtCompra(rs.getDate("dt_compra"));
				ativo.setFabricante(rs.getString("fabricante"));
				ativo.setVlCompra(rs.getDouble("vl_compra"));
				ativo.setVlDepreciado(rs.getDouble("vl_depreciado"));
				ativo.setCriadoPor(rs.getLong("criado_por"));
				ativo.setDtCriacao(rs.getDate("dt_cricao"));
				ativo.setIdStatus(rs.getLong("id_status"));
				ativo.setModificadoPor(rs.getLong("modificado_por"));
				ativo.setDtModificacao(rs.getDate("dt_modificao"));
			}
		 } catch (Exception e) {
			 e.printStackTrace();
		 }finally{
			 try{
				 if(rs != null){
					 rs.close();
				 }
				 if(pstm != null){
					 pstm.close();
				 }
				 if(conn != null){
					 conn.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }

		return ativo;
	}

	public Ativo cadastrarAtivo(Ativo ativo) {
		String sql = "INSERT INTO lu2cas01.ATIVO(descricao,fabricante,dt_compra,vl_compra,vl_depreciado,id_status,criado_por,modificado_por,dt_cricao,dt_modificao)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, ativo.getDescricao());
			pstm.setString(2, ativo.getFabricante());
			pstm.setDate(3, new Date(ativo.getDtCompra().getTime()));
			pstm.setDouble(4, ativo.getVlCompra());
			pstm.setDouble(5, ativo.getVlDepreciado());
			pstm.setLong(6, ativo.getIdStatus());
			pstm.setLong(7, ativo.getCriadoPor());
			pstm.setLong(8, ativo.getModificadoPor());
			pstm.setDate(9, new Date(ativo.getDtCriacao().getTime()));
			pstm.setDate(10, new Date(ativo.getDtModificacao().getTime()));
			
			// Executa a sql para inser��o dos dados
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ativo;
	}

	public Ativo alterarAtivo(Ativo ativo) {
		String sql = "UPDATE lu2cas01.ATIVO SET descricao = ?,fabricante = ?,dt_compra = ?,vl_compra = ?,vl_depreciado = ?,id_status = ?, modificado_por = ?, dt_modificao = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, ativo.getDescricao());
			pstm.setString(2, ativo.getFabricante());
			pstm.setDate(3, new Date(ativo.getDtCompra().getTime()));
			pstm.setDouble(4, ativo.getVlCompra());
			pstm.setDouble(5, ativo.getVlDepreciado());
			pstm.setLong(6, ativo.getIdStatus());
			pstm.setLong(7, ativo.getModificadoPor());
			pstm.setDate(8, new Date(ativo.getDtModificacao().getTime()));
			pstm.setLong(9, ativo.getId());

			// Executa a sql para inser��o dos dados
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ativo;
	}

	public boolean excluirAtivo(long id) {
		boolean sucesso = false;
		String sql = "DELETE FROM lu2cas01.ATIVO WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setLong(1, id);

			// Executa a sql para inser��o dos dados
			if (pstm.execute()) {
				sucesso = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sucesso;
	}

}