package ru.reybos.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.AccidentType;
import ru.reybos.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO accident (name, address, text, accident_type_id) "
                + "VALUES (?, ?, ?, ?)";
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getAddress());
            ps.setString(3, accident.getText());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((int) keyHolder.getKeys().get("id"));

        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "INSERT INTO accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(), rule.getId()
            );
        }

        return accident;
    }

    public void update(Accident accident) {
        String sql = "UPDATE accident "
                + "SET name = ?, address = ?, text = ?, accident_type_id = ? "
                + "WHERE id = ?";
        jdbc.update(
                sql,
                accident.getName(), accident.getAddress(),
                accident.getText(), accident.getType().getId(),
                accident.getId()
        );
        jdbc.update(
                "delete from accident_rule where accident_id = ?",
                accident.getId());
        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "INSERT INTO accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(), rule.getId()
            );
        }
    }

    public List<Accident> findAllAccident() {
        String sql = "SELECT accident.id AS accident_id, accident.name AS accident_name, "
                + "accident.address AS accident_address, accident.text AS accident_text, "
                + "accident_type.id AS accident_type_id, accident_type.name AS accident_type_name "
                + "FROM accident "
                + "LEFT JOIN accident_type ON accident.accident_type_id = accident_type.id";
        List<Accident> accidents = jdbc.query(sql, (resultSet, rowNum) -> {
            Accident accidentDb = new Accident();
            accidentDb.setId(resultSet.getInt("accident_id"));
            accidentDb.setName(resultSet.getString("accident_name"));
            accidentDb.setAddress(resultSet.getString("accident_address"));
            accidentDb.setText(resultSet.getString("accident_text"));
            AccidentType type = new AccidentType();
            type.setId(resultSet.getInt("accident_type_id"));
            type.setName(resultSet.getString("accident_type_name"));
            accidentDb.setType(type);
            return accidentDb;
        });

        for (Accident accident : accidents) {
            sql = "SELECT rule.* FROM rule "
                    + "LEFT JOIN accident_rule ON rule.id = accident_rule.rule_id "
                    + "WHERE accident_rule.accident_id = ?";
            List<Rule> rules = jdbc.query(sql, (resultSet, rowNum) -> {
                Rule rule = new Rule();
                rule.setId(resultSet.getInt("id"));
                rule.setName(resultSet.getString("name"));
                return rule;
            }, accident.getId());
            for (Rule rule : rules) {
                accident.addRule(rule);
            }
        }

        return accidents;
    }

    public Optional<Accident> findById(int id) {
        String sql = "SELECT accident.id AS accident_id, accident.name AS accident_name, "
                + "accident.address AS accident_address, accident.text AS accident_text, "
                + "accident_type.id AS accident_type_id, accident_type.name AS accident_type_name "
                + "FROM accident "
                + "LEFT JOIN accident_type ON accident.accident_type_id = accident_type.id "
                + "WHERE accident.id = ?";
        Accident accident = jdbc.queryForObject(sql, (resultSet, rowNum) -> {
            Accident accidentDb = new Accident();
            accidentDb.setId(resultSet.getInt("accident_id"));
            accidentDb.setName(resultSet.getString("accident_name"));
            accidentDb.setAddress(resultSet.getString("accident_address"));
            accidentDb.setText(resultSet.getString("accident_text"));
            AccidentType type = new AccidentType();
            type.setId(resultSet.getInt("accident_type_id"));
            type.setName(resultSet.getString("accident_type_name"));
            accidentDb.setType(type);
            return accidentDb;
        }, id);

        if (accident == null) {
            return Optional.empty();
        }

        sql = "SELECT rule.* FROM rule "
                + "LEFT JOIN accident_rule ON rule.id = accident_rule.rule_id "
                + "WHERE accident_rule.accident_id = ?";
        List<Rule> rules = jdbc.query(sql, (resultSet, rowNum) -> {
            Rule rule = new Rule();
            rule.setId(resultSet.getInt("id"));
            rule.setName(resultSet.getString("name"));
            return rule;
        }, accident.getId());
        for (Rule rule : rules) {
            accident.addRule(rule);
        }

        return Optional.of(accident);
    }

    public List<AccidentType> findAllAccidentType() {
        String sql = "SELECT * FROM accident_type";
        return jdbc.query(sql, (resultSet, rowNum) -> {
            AccidentType type = new AccidentType();
            type.setId(resultSet.getInt("id"));
            type.setName(resultSet.getString("name"));
            return type;
        });
    }

    public List<Rule> findAllRule() {
        String sql = "SELECT * FROM rule";
        return jdbc.query(sql, (resultSet, rowNum) -> {
            Rule rule = new Rule();
            rule.setId(resultSet.getInt("id"));
            rule.setName(resultSet.getString("name"));
            return rule;
        });
    }
}