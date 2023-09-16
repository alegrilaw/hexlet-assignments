package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {

    protected final String name;
    protected final Map<String, String> attributes;

    protected Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('<').append(name);
        attributes.forEach((k, v) -> builder.append(' ')
            .append(k)
            .append('=')
            .append('"')
            .append(v)
            .append('"'));
        builder.append('>');

        return builder.toString();
    }
}
// END
