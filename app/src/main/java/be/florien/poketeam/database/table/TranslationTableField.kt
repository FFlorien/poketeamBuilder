package be.florien.poketeam.database.table

import be.florien.joinorm.annotation.JoCustomJoin
import be.florien.joinorm.architecture.DBData
import be.florien.joinorm.architecture.DBTable
import be.florien.joinorm.architecture.WhereStatement
import be.florien.joinorm.primitivefield.IntField
import be.florien.poketeam.model.DualStringTranslation
import java.lang.reflect.Field
import java.util.*

class TranslationTableField : DBTable<DualStringTranslation> {

    constructor(tableName: String, fieldIdName: String, translationFieldName: String) : super(tableName, DualStringTranslation::class.java) {
        dataName = tableName
        mTranslationTableName = tableName
        mFieldIdName = fieldIdName
        selectId()
        selectString(translationFieldName)
    }

    constructor(tableName: String, fieldIdName: String, translationFieldName: String, translationFieldName2: String) : super(tableName, DualStringTranslation::class.java) {
        dataName = tableName
        mTranslationTableName = tableName
        mFieldIdName = fieldIdName
        selectId()
        selectString(translationFieldName)
        selectString(translationFieldName2)
    }

    private var mTranslationTableName: String? = null
    private var mFieldIdName: String? = null
    private var mIsFirstParsed = false

    @JoCustomJoin(getParams = "getCompleteId()")
    fun getJoinToTranslatedTable(idOfTable: List<String>): String {
        return "LEFT JOIN " + mTranslationTableName + " ON " + mTranslationTableName + "." + mFieldIdName + " = " + idOfTable[0]
    }

    public override fun getJoinToInnerTable(foreignTable: DBTable<*>): String {
        return ""
    }

    public override fun getId(): List<String> {
        return listOf(mTranslationTableName + "." + mFieldIdName)
    }

    //TODO use addWhere

    override fun getWhere(): String {
        val userLanguage = 9//Constant.getLanguageId()
        val selection: String
        if (mCompleteTranslations[mTranslationTableName] != null && mCompleteTranslations[mTranslationTableName]!!.contains(userLanguage)) {
            selection = userLanguage.toString()
        } else if (mIncompleteTranslations[mTranslationTableName] != null && mIncompleteTranslations[mTranslationTableName]!!.contains(userLanguage)) {
            selection = userLanguage.toString() + ", 9"
        } else {
            selection = "9"
        }
        return "($mTranslationTableName.local_language_id IN ($selection) OR $mTranslationTableName.local_language_id IS NULL)"
    }

    override fun addWhere(statement: WhereStatement): TranslationTableField {
        return this
    }

    override fun getOrderBy(): String {
        var orderby = mTranslationTableName!! + ".local_language_id"
//        if (Constant.getLanguageId() < 9) {
//            orderby += " DESC"
//        }
        return orderby
    }

    override fun selectId(): TranslationTableField {
        selectId("local_language_id")
        return this
    }

    public override fun resetCurrentParsing() {
        super.resetCurrentParsing()
        mIsFirstParsed = false
    }

    @Throws(NoSuchFieldException::class)
    override fun getFieldToSet(fieldToSet: DBData<*>): Field {
        val field: Field
        // Log.d("POKEMON", mTableName + ".getFieldToSet("+ fieldToSet.getFieldName() + ") / mCurrent == " + currentObject.id + " - " +
        // currentObject.first + " - " + currentObject.second);
        if (fieldToSet is IntField) {
            field = pojoClass.getField("id")
        } else if (!mIsFirstParsed) {
            mIsFirstParsed = true
            field = pojoClass.getField("first")
        } else {
            field = pojoClass.getField("second")
        }
        return field
    }

    companion object {

        //TODO add fairy translation...

        fun forGeneration(): TranslationTableField {
            return TranslationTableField("generation_names", "generation_id", "name")
        }

        fun forItem(): TranslationTableField {
            return TranslationTableField("item_names", "item_id", "name")
        }

        fun forLanguage(): TranslationTableField {
            return TranslationTableField("language_names", "language_id", "name")
        }

        fun forMove(): TranslationTableField {
            return TranslationTableField("move_names", "move_id", "name")
        }

        fun forMoveEffect(): TranslationTableField {
            return TranslationTableField("move_effect_prose", "move_effect_id", "short_effect", "effect")
        }

        fun forMoveDamageClass(): TranslationTableField {
            return TranslationTableField("move_damage_class_prose", "move_damage_class_id", "name", "description")
        }

        fun forMoveMetaAilment(): TranslationTableField {
            return TranslationTableField("move_meta_ailment_names", "move_meta_ailment_id", "name")
        }

        fun forPokemonForm(): TranslationTableField {
            return TranslationTableField("pokemon_form_names", "pokemon_form_id", "form_name", "pokemon_name")
        }

        fun forPokemonSpecie(): TranslationTableField {
            return TranslationTableField("pokemon_species_names", "pokemon_species_id", "name")
        }

        fun forType(): TranslationTableField {
            return TranslationTableField("type_names", "type_id", "name")
        }

        private val mCompleteTranslations: HashMap<String, List<Int>>

        init {
            mCompleteTranslations = HashMap<String, List<Int>>()
            mCompleteTranslations.put("move_damage_class_names", Arrays.asList(1, 9, 10))
            mCompleteTranslations.put("move_effect_names", listOf(9))
            mCompleteTranslations.put("move_meta_ailment", listOf(9))
            mCompleteTranslations.put("move_names", Arrays.asList(1, 5, 9))
            mCompleteTranslations.put("pokemon_form_names", Arrays.asList(5, 9))
            mCompleteTranslations.put("pokemon_species_names", Arrays.asList(1, 5, 6, 9))
            mCompleteTranslations.put(TypeTableTmpForPokemon.TABLE_LANGUAGE_NAME, listOf(9))
            mCompleteTranslations.put("item_names", listOf(9))
        }

        private val mIncompleteTranslations: HashMap<String, List<Int>>

        init {
            mIncompleteTranslations = HashMap<String, List<Int>>()
            mIncompleteTranslations.put("move_effect_names", listOf(10))
            mIncompleteTranslations.put("move_meta_ailment_names", listOf(10))
            mIncompleteTranslations.put("move_names", Arrays.asList(6, 7, 8, 10))
            mIncompleteTranslations.put("pokemon_species_names", Arrays.asList(2, 3, 4, 10))
            mIncompleteTranslations.put(TypeTableTmpForPokemon.TABLE_LANGUAGE_NAME, Arrays.asList(1, 5, 6, 7, 8, 10))
            mIncompleteTranslations.put("item_names", Arrays.asList(1, 5, 6, 7, 8, 10))
        }
    }
}
