package com.example;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.test.ESIntegTestCase;
import org.elasticsearch.test.InternalSettingsPlugin;

import java.util.Arrays;
import java.util.Collection;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.test.hamcrest.ElasticsearchAssertions.assertAcked;
import static org.elasticsearch.test.hamcrest.ElasticsearchAssertions.assertHighlight;
import static org.hamcrest.Matchers.equalTo;

public class ExampleIT extends ESIntegTestCase {

    @Override
    protected Collection<Class<? extends Plugin>> nodePlugins() {
        return Arrays.asList(InternalSettingsPlugin.class, ExamplePlugin.class);
    }

    public void testMatchingHighlights() throws Exception {
        assertAcked(prepareCreate("test").addMapping("type1"));
        ensureGreen();

        indexRandom(true, client().prepareIndex("test", "type1")
                .setSource("a", "1", "b", "2"));

        SearchResponse search = client().prepareSearch()
                .setQuery(boolQuery().should(termQuery("a", "1")).should(termQuery("b", "2")))
                .highlighter(new HighlightBuilder().field("a").field("b").highlighterType("example"))
                .get();

        assertHighlight(search, 0, "a", 0, 1, equalTo("..."));
        assertHighlight(search, 0, "b", 0, 1, equalTo("..."));
    }
}
