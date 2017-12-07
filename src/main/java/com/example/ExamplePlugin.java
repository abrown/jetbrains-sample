package com.example;

import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.SearchPlugin;
import org.elasticsearch.search.fetch.subphase.highlight.Highlighter;

import java.util.Collections;
import java.util.Map;

public class ExamplePlugin extends Plugin implements SearchPlugin {
    @Override
    public Map<String, Highlighter> getHighlighters() {
        return Collections.singletonMap("example", new ExampleHighlighter());
    }
}
