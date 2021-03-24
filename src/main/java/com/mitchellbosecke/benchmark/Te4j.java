package com.mitchellbosecke.benchmark;

import com.github.lero4ka16.te4j.template.Template;
import com.mitchellbosecke.benchmark.model.Stock;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.io.IOException;
import java.util.List;


public class Te4j extends BaseBenchmark {

    private Pojo pojo;
    private Template<Pojo> template;

    @Setup
    public void setup() throws IOException {
        this.pojo = new Pojo(Stock.dummyItems());

        this.template = com.github.lero4ka16.te4j.Te4j.custom().useResources().build()
                .load(Pojo.class, "templates/stocks.te4j.html");
    }

    @Benchmark
    public String benchmark() throws IOException {
        return template.renderAsString(pojo);
    }

    public static class Pojo {
        private final List<Stock> items;

        public Pojo(List<Stock> items) {
            this.items = items;
        }

        public List<Stock> getItems() {
            return items;
        }
    }
}
