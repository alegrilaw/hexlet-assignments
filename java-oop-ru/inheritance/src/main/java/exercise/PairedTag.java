package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {

    protected final String body;
    protected final List<Tag> childs;

    protected PairedTag(String name, Map<String, String> attributes, String body, List<Tag> childs) {
        super(name, attributes);

        this.body = body;
        this.childs = childs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(body);
        childs.forEach(tag -> builder.append(tag.toString()));
        builder.append('<')
            .append('/')
            .append(name)
            .append('>');

        return builder.toString();
    }
}
// END
