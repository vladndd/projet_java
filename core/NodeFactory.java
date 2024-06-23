package core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import representation.*;

import univers.base.Character;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeFactory {
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private List<Character> characters;

    public NodeFactory(List<Character> characters) {
        this.characters = characters;
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

        switch (type) {
            case "InnerNode":
                InnerNode innerNode = new InnerNode(id, description);
                if (nodeJson.has("backgroundImage")) {
                    innerNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                return innerNode;

            case "DecisionNode":
                DecisionNode decisionNode = new DecisionNode(id, description);
                if (nodeJson.has("backgroundImage")) {
                    decisionNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                return decisionNode;

            case "ChanceNode":

                ChanceNode chanceNode = new ChanceNode(id, description);

                if (nodeJson.has("backgroundImage")) {
                    chanceNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                return chanceNode;

            case "BattleNode":
                String enemyName = nodeJson.get("enemyName").asText();
                int enemyHealth = nodeJson.get("enemyHealth").asInt();
                int enemyAttack = nodeJson.get("enemyAttack").asInt();
                Character character = characters.get(nodeJson.get("characterId").asInt());
                BattleNode battleNode = new BattleNode(id, description, enemyName, enemyHealth, enemyAttack, character);

                if (nodeJson.has("backgroundImage")) {
                    battleNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                return battleNode;
            case "TradeNode":
                List<String> itemsForSale = new ArrayList<>();
                for (JsonNode item : nodeJson.get("itemsForSale")) {
                    itemsForSale.add(item.asText());
                }
                Character traderCharacter = characters.get(nodeJson.get("characterId").asInt());

                TradeNode tradeNode = new TradeNode(id, description, itemsForSale.toArray(new String[0]),
                        traderCharacter);

                if (nodeJson.has("backgroundImage")) {
                    tradeNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }

                return tradeNode;
            case "TerminalNode":
                TerminalNode terminalNode = new TerminalNode(id, description);
                if (nodeJson.has("backgroundImage")) {
                    terminalNode.setBackgroundImage(nodeJson.get("backgroundImage").asText());
                }
                return terminalNode;

            default:
                throw new IllegalArgumentException("Unknown node type: " + type);
        }
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