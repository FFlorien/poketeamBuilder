
package be.florien.poketeam.database.table;

import java.util.Collections;
import java.util.List;

import be.florien.joinorm.annotation.JoCustomJoin;
import be.florien.joinorm.architecture.DBTable;
import be.florien.poketeam.model.Type;
import be.florien.poketeam.model.table.GenerationTable;
import be.florien.poketeam.model.table.TypeEfficacyAsAttackTable;
import be.florien.poketeam.model.table.TypeEfficacyAsDefenseTable;

public class TypeTableTmpForPokemon extends DBTable<Type> {

    public static final String TABLE_NAME = "types";
    public static final String TABLE_LANGUAGE_NAME = "type_names";
    public static final String TABLE_EFFICACITY = "type_efficacity";

    public static final String COLUMN_TYPE_ID = "id";
    private static final String COLUMN_TYPE_IDENTIFIER = "identifier";
    private static final String COLUMN_TYPE_GENERATION_ID = "generation_id";

    private static final String COLUMN_LANGUAGE_TYPE_ID = "type_id";
    private static final String COLUMN_LANGUAGE_NAME = "name";

    public TypeTableTmpForPokemon() {
        super(TABLE_NAME, Type.class);
    }

    @JoCustomJoin(getParams = "this, (TypeTableTmpForPokemon) innerTable", getTableFor = be.florien.poketeam.model.table.PokemonTable.class)
    public String getTypeJoin(be.florien.poketeam.model.table.PokemonTable tablePokemon, be.florien.poketeam.database.table.TypeTableTmpForPokemon field) {
        return "JOIN pokemon_types ON " + tablePokemon.getDataName() + "." + tablePokemon.getId() + " = pokemon_types.pokemon_id" +
                " JOIN " + field.getDataName() + " ON " + field.getDataName() + ".id = pokemon_types.type_id";
    }

    @Override
    public TypeTableTmpForPokemon selectId() {
        selectId(COLUMN_TYPE_ID);
        return this;
    }

    public TypeTableTmpForPokemon selectIdentifier() {
        selectString(COLUMN_TYPE_IDENTIFIER);
        return this;
    }

    public TypeTableTmpForPokemon selectGeneration(GenerationTable genTable) {
        selectTable(genTable);
        return this;
    }
    
    public TypeTableTmpForPokemon selectTypeEfficacityAsAttack(TypeEfficacyAsAttackTable table) {
        table.setAlias("attack");
        selectTable(table);
        return this;
    }
    
    public TypeTableTmpForPokemon selectTypeEfficacityAsDefense(TypeEfficacyAsDefenseTable table) {
        table.setAlias("defense");
        selectTable(table);
        return this;
    }

    @Override
    public String getJoinToInnerTable(DBTable<?> field) {
        if (field instanceof GenerationTable) {
            return getJoinOnRef(field, false, COLUMN_TYPE_GENERATION_ID);
        }else if(field instanceof TranslationTableField){
            return ((TranslationTableField)field).getJoinToTranslatedTable(getCompleteId());
        }else if(field instanceof TypeEfficacyAsAttackTable){
            return getJoinOnId(field, false, "damage_type_id");
        }else if(field instanceof TypeEfficacyAsDefenseTable){
            return getJoinOnId(field, false, "target_type_id");
        }
        return "";
    }

    @Override
    public List<String> getId() {
        return Collections.singletonList(COLUMN_TYPE_ID);
    }

    public TypeTableTmpForPokemon selectName() {
        selectTable(new TranslationTableField(TABLE_LANGUAGE_NAME, COLUMN_LANGUAGE_TYPE_ID, COLUMN_LANGUAGE_NAME));
        return this;
    }

}
