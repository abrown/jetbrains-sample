package com.example;

import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.fetch.subphase.highlight.HighlighterContext;
import org.elasticsearch.test.ESTestCase;

public class ExampleHighlighterTests extends ESTestCase {
    public void testIsHighlightable() {
        ExampleHighlighter sut = new ExampleHighlighter();

        assertTrue(sut.canHighlight(null));
    }

    public void testHighlightedString() {
        ExampleHighlighter sut = new ExampleHighlighter();
        HighlighterContext context = new HighlighterContext("field-name", null, null, null, null, null);

        HighlightField highlight = sut.highlight(context);
        Text[] fragments = highlight.fragments();

        assertEquals(fragments.length, 1);
        assertEquals(fragments[0].toString(), "...");
    }
}
