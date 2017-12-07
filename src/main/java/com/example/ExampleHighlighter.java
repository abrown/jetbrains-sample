package com.example;

import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.mapper.FieldMapper;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.fetch.subphase.highlight.Highlighter;
import org.elasticsearch.search.fetch.subphase.highlight.HighlighterContext;

public class ExampleHighlighter implements Highlighter {
    @Override
    public boolean canHighlight(FieldMapper fieldMapper) {
        return true;
    }

    @Override
    public HighlightField highlight(HighlighterContext context) {
        return new HighlightField(context.fieldName, new Text[]{new Text("...")});
    }
}
