package tvz.videc.zavrsni.webshop.security.error;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

class LowerCaseClassNameResolver extends TypeIdResolverBase {

    @Override
    public String idFromValue(final Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String idFromValueAndType(final Object value, final Class<?> suggestedType) {
        return this.idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }

}
