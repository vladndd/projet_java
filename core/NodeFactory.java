package core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import representation.*;

import univers.base.Item;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeFactory implements Serializable {
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private Game game;

    public NodeFactory(Game game) {
        this.game = game;
    }

    public Map<Integer, Node> createNodes(String jsonFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(jsonFilePath));

        // First pass: create all nodes
        for (JsonNode nodeJson : root.get("nodes")) {
            Node node = createNodeFromJson(nodeJson);
            nodeMap.put(node.getId(), node);
        }

        // Second pass: link nodes
        for (JsonNode nodeJson : root.get("nodes")) {
            linkNodes(nodeJson);
        }

        return nodeMap;
    }

    private Node createNodeFromJson(JsonNode nodeJson) {
        String type = nodeJson.get("type").asText();
        int id = nodeJson.get("id").asInt();
        String description = nodeJson.get("description").asText();
        String soundFilePath = nodeJson.has("soundFilePath") ? nodeJson.get("soundFilePath").asText() : null;

        Node node;

        switch (type) {
            case "InnerNode":
                InnerNode innerNode = new InnerNode(id, description);
                if (nodeJson.has("backgroundImage")) {
                    innerNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                node = innerNode;
                break;
            case "DecisionNode":
                DecisionNode decisionNode = new DecisionNode(id, description);
                if (nodeJson.has("backgroundImage")) {
                    decisionNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                node = decisionNode;
                break;
            case "ChanceNode":
                ChanceNode chanceNode = new ChanceNode(id, description);
                if (nodeJson.has("backgroundImage")) {
                    chanceNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                node = chanceNode;
                break;
            case "BattleNode":
                String enemyName = nodeJson.get("enemyName").asText();
                int enemyHealth = nodeJson.get("enemyHealth").asInt();
                int enemyAttack = nodeJson.get("enemyAttack").asInt();
                BattleNode battleNode = new BattleNode(id, description, enemyName, enemyHealth, enemyAttack, game);
                if (nodeJson.has("backgroundImage")) {
                    battleNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                node = battleNode;
                break;
            case "TradeNode":
                List<Item> itemsForSale = new ArrayList<>();
                for (JsonNode itemJson : nodeJson.get("itemsForSale")) {
                    String itemName = itemJson.get("name").asText();
                    int itemPrice = itemJson.get("price").asInt();
                    int itemWeight = itemJson.get("weight").asInt();
                    int itemHealth = itemJson.get("health").asInt();
                    int itemAttack = itemJson.get("attack").asInt();
                    itemsForSale.add(new Item(itemName, itemPrice, itemWeight, itemHealth, itemAttack, 1));
                }
                TradeNode tradeNode = new TradeNode(id, description, itemsForSale, game);
                if (nodeJson.has("backgroundImage")) {
                    tradeNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                node = tradeNode;
                break;
            case "TerminalNode":
                TerminalNode terminalNode = new TerminalNode(id, description);
                if (nodeJson.has("backgroundImage")) {
                    terminalNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                node = terminalNode;
                break;
            default:
                throw new IllegalArgumentException("Unknown node type: " + type);
        }

        if (soundFilePath != null) {
            node.setSoundFilePath(soundFilePath);
        }

        return node;
    }

    private void linkNodes(JsonNode nodeJson) {
        int id = nodeJson.get("id").asInt();
        Node node = nodeMap.get(id);

        if (nodeJson.has("nextNodeId")) {
            int nextNodeId = nodeJson.get("nextNodeId").asInt();
            ((InnerNode) node).addNextNode(nodeMap.get(nextNodeId));
        }

        if (node instanceof DecisionNode && nodeJson.has("options")) {
            for (JsonNode optionJson : nodeJson.get("options")) {
                int optionId = optionJson.get("id").asInt();
                Node optionNode = nodeMap.get(optionId);
                if (optionNode instanceof InnerNode) {
                    ((DecisionNode) node).addOption(optionNode);
                } else {
                    throw new IllegalArgumentException("Options of DecisionNode must be of type InnerNode");
                }
            }
        }

        if (node instanceof ChanceNode && nodeJson.has("outcomes")) {
            for (JsonNode outcomeJson : nodeJson.get("outcomes")) {
                int outcomeId = outcomeJson.get("id").asInt();
                ((ChanceNode) node).addOutcome(nodeMap.get(outcomeId));
            }
        }

        if (node instanceof BattleNode && nodeJson.has("chanceOptions")) {
            for (JsonNode chanceOptionJson : nodeJson.get("chanceOptions")) {
                int chanceOptionId = chanceOptionJson.get("id").asInt();
                ((BattleNode) node).addChanceOption(nodeMap.get(chanceOptionId));
            }
        }

        if (node instanceof TradeNode && nodeJson.has("chanceOptions")) {
            for (JsonNode chanceOptionJson : nodeJson.get("chanceOptions")) {
                int chanceOptionId = chanceOptionJson.get("id").asInt();
                ((TradeNode) node).addChanceOption(nodeMap.get(chanceOptionId));
            }
        }
    }

    public Node getNode(int id) {
        return nodeMap.get(id);
    }
}