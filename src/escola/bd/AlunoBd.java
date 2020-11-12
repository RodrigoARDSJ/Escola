package escola.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import escola.Alunos;

public class AlunoBd {
	private Connection conn;

	public AlunoBd() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "xxxxxx",
					"xxxxx");

		} catch (ClassNotFoundException e) {
			System.err.printf("Não foi possivel localizar o driver: %s", e.getMessage());
		} catch (SQLException e) {
			System.err.println("Ocorreu um erro na conexão com o Banco de Dados");
			System.err.println(e.getMessage());
		}
	}

	public void salva(Alunos aluno) {
		try {
			String sql = String.format(
					"insert into tb_aluno(id, rm, nome, ativo, nota1, nota2)"
							+ "values(sq_aluno.nextval, %s, '%s', %s, %s, %s)",
					aluno.getRm(), aluno.getNome(), aluno.getAtivo() ? 1 : 0, aluno.getNota1(), aluno.getNota2());

			Statement stmt = this.conn.createStatement();
			stmt.executeUpdate(sql);

			this.desconecta(this.conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void desconecta(Connection conn) throws Exception {
		if (conn != null)
			conn.close();

	}

	public List<Alunos> consularTodos() {
		try {
			List<Alunos> alunos = new ArrayList<>();
			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tb_aluno");
			while(rs.next()) {
				Integer id = rs.getInt("id");
				Integer rm = rs.getInt("rm");
				String nome = rs.getString("nome");
				Boolean ativo = rs.getBoolean("ativo");
				Double nota1 = rs.getDouble("nota1");
				Double nota2 = rs.getDouble("nota2");
				
				alunos.add(new Alunos(id, rm, nome, ativo, nota1, nota2));
				
				
			}

			this.desconecta(this.conn);
			return alunos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
