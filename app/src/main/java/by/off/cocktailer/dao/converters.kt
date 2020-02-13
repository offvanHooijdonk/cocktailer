package by.off.cocktailer.dao

import androidx.room.TypeConverter
import by.off.cocktailer.model.ComponentType
import by.off.cocktailer.model.ComponentUnit

class TypeConverters {
    @TypeConverter
    fun codeToComponentUnit(code: Int): ComponentUnit = ComponentUnit.fromCode(code)

    @TypeConverter
    fun componentUnitToCode(unit: ComponentUnit) = unit.code

    @TypeConverter
    fun codeToComponentType(code: Int): ComponentType = ComponentType.fromCode(code)

    @TypeConverter
    fun componentTypeToCode(unit: ComponentType) = unit.code
}