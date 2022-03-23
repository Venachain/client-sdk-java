package com.venachain.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static com.venachain.utils.Collection.join;

public class CollectionTest {

    @Test
    public void testTail() {
        assertThat(Collection.tail(Collection.EMPTY_STRING_ARRAY), is(Collection.EMPTY_STRING_ARRAY));
        assertThat(Collection.tail(Collection.create("a", "b", "c")), is(Collection.create("b", "c")));
        assertThat(Collection.tail(Collection.create("a")), is(Collection.EMPTY_STRING_ARRAY));
    }

    @Test
    public void testCreate() {
        assertThat(Collection.create("a"), is(new String[] { "a" }));
        assertThat(Collection.create(""), is(new String[] { "" }));
        assertThat(Collection.create("a", "b"), is(new String[] { "a", "b" }));
    }

    @Test
    public void testJoin() {
        assertThat(Collection.join(Arrays.asList("a  ", "b ", " c "), ","), is("a,b,c"));
        assertThat(Collection.join(Arrays.asList("a", "b", "c", "d"), ","), is("a,b,c,d"));
        assertThat(Collection.join(Arrays.asList("a  ", "b ", " c "), ", "), is("a, b, c"));
        assertThat(Collection.join(Arrays.asList("a", "b", "c", "d"), ", "), is("a, b, c, d"));
    }

    @Test
    public void testJoinWithFunction() {
        final List<FakeSpec> specs1 = Arrays.asList(
                new FakeSpec("a"),
                new FakeSpec("b"),
                new FakeSpec("c"));
        assertThat(Collection.join(specs1, ",", FakeSpec::getName), is("a,b,c"));

        final List<FakeSpec> specs2 = Arrays.asList(
                new FakeSpec("a"),
                new FakeSpec("b"),
                new FakeSpec("c"));
        assertThat(Collection.join(specs2, ", ", FakeSpec::getName), is("a, b, c"));

        final List<FakeSpec> specs3 = Arrays.asList(
                new FakeSpec(" a"),
                new FakeSpec("b  "),
                new FakeSpec(" c "));
        assertThat(Collection.join(specs3, ",", FakeSpec::getName), is("a,b,c"));

        final List<FakeSpec> specs4 = Arrays.asList(
                new FakeSpec(" a"),
                new FakeSpec("b  "),
                new FakeSpec(" c "));
        assertThat(Collection.join(specs4, ", ", FakeSpec::getName), is("a, b, c"));
    }

    /**
     * Fake object to test {@link Collection#join(List, String, Collection.Function)}.
     */
    private final class FakeSpec {
        private final String name;

        private FakeSpec(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
