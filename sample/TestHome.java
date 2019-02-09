package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.DBWorker.getDBConnection;

public class TestHome {
    public static void saveTest(Test test) {
        getDBConnection();
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate("insert into test (name, views, questions) values ('" + test.getName() + "', 0, " + test.getQuestions().size() + ");");
            for (int i = 0; i < test.getQuestions().size(); ++i) {
                statement.executeUpdate("insert into question (text, test_id) values ('"
                        + test.getQuestions().get(i).getQuestion()
                        + "', (select max(id) from test));");
                for (int q = 0; q < test.getQuestions().get(i).getAnswerChoices().size(); ++q) {
                    statement.executeUpdate("insert into answer (text, question_id) values ('"
                            + test.getQuestions().get(i).getAnswerChoices().get(q)
                            + "', (select max(id) from question));");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setRatingAndViews(Test test, int rating) {
        int points = 0;
        getDBConnection();
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate("insert into rating (ratings, test_id) values (" + rating + ", " + test.getId() + ");");
            statement.execute("select * from rating where test_id = " + test.getId() + ";");
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                points += rs.getInt("ratings");
            }
            statement.executeUpdate("update test set views = views + 1 where id = " + test.getId() + ";");
            statement.executeUpdate("update test set rating = " + points + "./views where id = " + test.getId() + ";");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteTest(Test test) {
        getDBConnection();
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            for (int i = 0; i < test.getQuestions().size(); i++) {
                statement.executeUpdate("delete from answer where question_id = " + test.getQuestions().get(i).getId() + ";");
            }
            statement.executeUpdate("delete from question where test_id = " + test.getId() + ";");
            statement.executeUpdate("delete from rating where test_id = " + test.getId() + ";");
            statement.executeUpdate("delete from test where id = " + test.getId() + ";");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ObservableList<Test> getTestRecords() {
        ObservableList<Test> list = FXCollections.observableArrayList();
        getDBConnection();
        Connection dbConnection = null;
        String sql = "select * from test";
        try {
            dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            statement.execute(sql);
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                Test test = new Test();
                test.setName(rs.getString("name"));
                test.setViews(rs.getInt("views"));
                if (rs.getDouble("rating") != 0) {
                    test.setRating(Double.parseDouble(String.format("%.3g%n", rs.getDouble("rating"))));
                }
                test.setCountQuestions(rs.getInt("questions"));
                test.setId(rs.getInt("id"));
                list.add(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static long getLastId() {
        long id = 0;
        getDBConnection();
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            statement = dbConnection.createStatement();
            statement.execute("select * from test where id = (select max(id) from test);");
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                id = rs.getLong("id");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public static Test getTest(long id) {
        Test test = new Test();
        test.setId(id);
        getDBConnection();
        Connection dbConnection = null;
        String sqlTest = "select * from test where id = " + id;
        String sqlQuestion = "select * from question where test_id = " + id;
        try {
            dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            statement.execute(sqlTest);
            ResultSet rs = statement.getResultSet();
            if (!rs.next()) {
                return null;
            }
            test.setName(rs.getString("name"));
            statement = dbConnection.createStatement();
            statement.execute(sqlQuestion);
            ResultSet rs2 = statement.getResultSet();
            while (rs2.next()) {
                Question question = new Question();
                question.setId(rs2.getLong("id"));
                question.setQuestion(rs2.getString("text"));
                Statement statement3 = dbConnection.createStatement();
                String sqlAnswer = "select * from answer where question_id = " + question.getId();
                statement3.execute(sqlAnswer);
                ResultSet rs3 = statement3.getResultSet();
                while (rs3.next()) {
                    question.setAnswers(rs3.getString("text"));
                }
                test.addQuestion(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return test;
    }
}
