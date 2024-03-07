package ru.edu.mirea.task7.composite;

import org.junit.jupiter.api.Test;

public class CompositeTest {
    @Test
    public void patternCompositeTest() {
        Graphic line = new Line();
        Graphic circle = new Circle();

        CompositeGraphic composite = new CompositeGraphic();
        composite.add(line);
        composite.add(circle);

        CompositeGraphic nestedComposite = new CompositeGraphic();
        nestedComposite.add(new Circle());
        nestedComposite.add(new Line());

        composite.add(nestedComposite);

        composite.draw();
    }
}
