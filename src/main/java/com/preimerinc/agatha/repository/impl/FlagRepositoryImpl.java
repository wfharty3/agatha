package com.preimerinc.agatha.repository.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Subscription;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import com.preimerinc.agatha.model.AgathaFlag;
import com.preimerinc.agatha.repository.FlagRepository;
import com.preimerinc.agatha.util.FhirServersEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class FlagRepositoryImpl implements FlagRepository {

	FhirContext ctx;

	@Autowired
	public FlagRepositoryImpl(FhirContext ctx) {
		this.ctx = ctx;
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	public MethodOutcome createFlagRemote(Flag f) {
		IGenericClient client = ctx.newRestfulGenericClient(System.getProperty("serverBase"));

		return client.create()
				.resource(f)
				.prettyPrint()
				.encodedJson()
				.execute();

	}


	public Bundle listFlagsLocal() {
		Bundle b = new Bundle();


		String sql = "select * from AGATHA_FLAGS";
		List<AgathaFlag> aFlags = jdbcTemplate.query(sql, new BeanPropertyRowMapper(AgathaFlag.class));
		for (AgathaFlag af: aFlags) {
			Flag f = ctx.newJsonParser().parseResource(Flag.class, af.getFlagContent().toString());
			b.addEntry().setResource(f);
		}
		return b;
	}


	public void createFlagLocal(Flag f) 	{
		final String sql = "insert into AGATHA_FLAGS(flag_id, patient_identifier, flag_content) values(?,?,?)";

		String fStr = ctx.newJsonParser().encodeResourceToString(f);

		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, null);
				ps.setString(2, f.getSubject().getReference().getIdPart().toString());
				ps.setString(3, fStr);
				return ps;
			}
		}, holder);
	}

}

class AgathaFlagRowMapper implements RowMapper<AgathaFlag> {

	@Override
	public AgathaFlag mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgathaFlag flag = new AgathaFlag();
		flag.setFlagId(rs.getInt("FLAG_ID"));
		flag.setPatientIdentifier(rs.getString("PATIENT_IDENTIFIER"));
		flag.setFlagContent(rs.getClob("FLAG_CONTENT"));
		return flag;
	}
}
