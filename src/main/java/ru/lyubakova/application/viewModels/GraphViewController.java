package ru.lyubakova.application.viewModels;

import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lombok.Getter;
import ru.lyubakova.application.models.City;
import ru.lyubakova.application.models.Distance;
import ru.lyubakova.application.utils.DistanceCalculator;
import ru.lyubakova.application.utils.TravelingSalesmanProblemSolver;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Getter
public class GraphViewController {

    private final Random random = new Random();

    private final SmartGraphPanel<City, Distance> panel;

    private final Graph<City, Distance> graph = new GraphEdgeList<>();

    private final SmartGraphDemoContainer container;

    public GraphViewController() {
        panel = new SmartGraphPanel<>(graph, new SmartCircularSortedPlacementStrategy());
        panel.setAutomaticLayout(true);
        panel.setMinHeight(500);
        panel.setMaxHeight(500);
        container = new SmartGraphDemoContainer(panel);
        container.setRight(null);
        makeFooter();
        makeHeader();
    }

    private void makeHeader() {
        HBox header = new HBox();
        header.getStyleClass().setAll("graph-header");
        Text title = new Text("Маршрут");
        title.getStyleClass().setAll("h2");
        header.getChildren().addAll(title);
        container.setTop(header);
    }

    private void makeFooter() {
        HBox bottom = (HBox) container.getBottom();
        bottom.getStyleClass().setAll("graph-footer");
        ObservableList<Node> children = bottom.getChildren();
        Node[] array = children.filtered(node -> !(node instanceof CheckBox)).toArray(Node[]::new);
        Stream.of(array).forEach(children::remove);
        CheckBox checkBox = (CheckBox) children.get(0);
        checkBox.setText("Автоматическая расстановка");
    }

    public void removeNode(City city) {
        graph.vertices()
                .stream()
                .filter(vertex -> vertex.element().equals(city))
                .findFirst()
                .ifPresent(graph::removeVertex);
        update();
    }

    public void addNode(City city) {
        graph.insertVertex(city);
        update();
    }

    private void update() {
        List<City> cities = List.of(graph.vertices().stream().map(Vertex::element).toArray(City[]::new));
        if (cities.size() < 2) {
            return;
        }
        graph.edges().forEach(graph::removeEdge);
        List<List<Double>> distances = DistanceCalculator.makeDistances(cities);
        int[] path = TravelingSalesmanProblemSolver.nearestNeighbor(
                TravelingSalesmanProblemSolver.toMatrix(distances), 0);
        for (int i = 0; i < path.length - 1; i++) {
            int from = path[i];
            int to = path[i + 1];
            City cityFrom = cities.get(from);
            City cityTo = cities.get(to);
            Vertex<City> fromVertex = getVertex(cityFrom);
            Vertex<City> toVertex = getVertex(cityTo);
            graph.insertEdge(fromVertex, toVertex, new Distance(City.distance(cityFrom, cityTo)));
        }
        panel.update();
    }

    private Vertex<City> getVertex(City cityFrom) {
        return graph.vertices()
                .stream()
                .filter(cityVertex -> cityVertex.element().equals(cityFrom))
                .findFirst()
                .orElse(null);
    }


}
