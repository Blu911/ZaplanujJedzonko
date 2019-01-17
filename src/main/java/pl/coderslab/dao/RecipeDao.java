package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    // ZAPYTANIA SQL
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name, ingredients, description, created, updated, preparation_time, admin_id, preparation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?";
    private static final String FIND_ALL_RECIPE_QUERY = "SELECT * FROM recipe";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?";
    private static final String COUNT_USER_RECIPES = "SELECT COUNT(*) FROM recipe WHERE recipe.admin_id = ?";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE   recipe SET name = ?, ingredients = ?, description = ?, created = ?, updated = ?, preparation_time = ?, admin_id = ?, preparation = ? WHERE id = ?";


    /**
     * Get recipe by id
     *
     * @param recipeId
     * @return
     */
    public static Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)) {

            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getTimestamp("created"));
                    recipe.setUpdated(resultSet.getTimestamp("updated"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setAdmin_id(resultSet.getInt("admin_id"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return recipe;
    }

    /**
     * Return all recipe
     *
     * @return
     */
    public static List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getTimestamp("created"));
                recipeToAdd.setUpdated(resultSet.getTimestamp("updated"));
                recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                recipeToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeList.add(recipeToAdd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    /**
     * Create recipe
     *
     * @param recipe
     * @return
     */
    public static Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setTimestamp(4, recipe.getCreated());
            insertStm.setTimestamp(5, recipe.getUpdated());
            insertStm.setInt(6, recipe.getPreparation_time());
            insertStm.setInt(7, recipe.getAdmin_id());
            insertStm.setString(7, recipe.getPreparation());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Remove recipe by id
     *
     * @param recipeId
     */
    public static void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY);) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            if (statement.executeUpdate() == 0) {
                throw new NotFoundException("Recipe not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update recipe
     *
     * @param recipe
     */
    public static void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY);) {
            statement.setInt(9, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setTimestamp(4, recipe.getCreated());
            statement.setTimestamp(5, recipe.getUpdated());
            statement.setInt(6, recipe.getPreparation_time());
            statement.setInt(7, recipe.getAdmin_id());
            statement.setString(8, recipe.getPreparation());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int countUserRecipes(int userId) {
        int count = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_USER_RECIPES);) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("COUNT(*)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}