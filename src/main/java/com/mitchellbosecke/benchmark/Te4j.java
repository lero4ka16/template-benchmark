package com.mitchellbosecke.benchmark;

import com.mitchellbosecke.benchmark.model.Stock;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import te4j.template.Template;
import te4j.template.context.TemplateContext;
import te4j.template.option.output.Output;

import java.io.IOException;
import java.util.List;

public class Te4j extends BaseBenchmark {

    private Pojo pojo;
    private Template<Pojo> template;

    @Setup
    public void setup() throws IOException {
        this.pojo = new Pojo(Stock.dummyItems());

        TemplateContext ctx = te4j.Te4j.custom()
                .useResources()
                .output(Output.STRING)
                .disableAutoReloading()
                .build();

        template = ctx.load(Pojo.class).from("templates/stocks.te4j.html");
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
