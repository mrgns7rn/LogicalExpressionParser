package com.company.parser;

/**
 * Created by mrgnstrn on 18.04.2015.
 */

import com.company.logicalexpr.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpressionTree {
    private Node root;

    public boolean solve() {
        return recursiveSolve(root);

    }

    public void parseStringToTree(String input) {
        ReversePolandNotationParser rpnp = new ReversePolandNotationParser();
        input = rpnp.parseExprToPolandNotation(input);
        parseToBinnaryTree(input);

    }

    public HashMap<Character, VariableToPut> getAllVariables() {
        HashMap<Character, VariableToPut> variables = new HashMap<Character, VariableToPut>();
        getRecursiveAllVariables(root, variables);


        return variables;

    }

    private void parseToBinnaryTree(String expression) {
        char[] expr = expression.toCharArray();
        for (int i = expr.length - 1; i >= 0; i--) {

            add(expr[i]);
        }

    }

    private void add(char ch) {


        if (root == null) {
            root = new Node();
            if (charIsOperator(ch)) {
                root.setVal(new Operator(ch));


            } else {
                String number = "[0-9]";
                if (String.valueOf(ch).matches(number)) {

                    root.setVal(new NumConstant(Character.getNumericValue(ch)));

                }
                String variable = "[a-zA-Z]";
                if (String.valueOf(ch).matches(variable)) {
                    root.setVal(new Variable(ch));
                }
            }
        } else {

            insert(ch, root);
        }
    }

    private void insert(char ch, Node root) {

        Node point = root;
        ArrayList<Integer> visitedNodes = new ArrayList<Integer>();

        while (true) {

            if (point.getLeftChild() == null) {
                point.setLeftChild(new Node());
                point.getLeftChild().setParent(point);
                addAtom(point.getLeftChild(), ch);
                break;
            }

            if (point.getLeftChild().getVal().getType() == 3 && !(visitedNodes.contains(point.getLeftChild().getId()))) {
                point = point.getLeftChild();
                continue;
            } else {
                if (point.getRightChild() == null) {
                    point.setRightChild(new Node());
                    point.getRightChild().setParent(point);
                    addAtom(point.getRightChild(), ch);
                    break;
                } else {
                    if (point.getRightChild().getVal().getType() == 3 && !(visitedNodes.contains(point.getRightChild().getId()))) {
                        point = point.getRightChild();
                        continue;
                    } else {
                        visitedNodes.add(point.getId());
                        point = point.getParent();
                        continue;


                    }
                }
            }
        }
    }

    private void addAtom(Node node, char ch) {
        String number = "[0-9]";
        String variable = "[a-zA-Z]";
        // for (char ch : rpnStringCharArr) {
        if (charIsOperator(ch)) {

            node.setVal(new Operator(ch));
            setRightChildIfOperatorIsNOT(node);

        } else {
            if (String.valueOf(ch).matches(number)) {

                node.setVal(new NumConstant(Character.getNumericValue(ch)));
                setRightChildIfOperatorIsNOT(node);

            }
            if (String.valueOf(ch).matches(variable)) {
                node.setVal(new Variable(ch));
                setRightChildIfOperatorIsNOT(node);

            }
        }

    }

    private void setRightChildIfOperatorIsNOT(Node node) {

        if (node.getParent().getVal().getStringValue().equals("NOT")) {
            node.getParent().setRightChild((new Node()));
            node.getParent().getRightChild().setVal(new NumConstant(0));
        }

    }

    private boolean charIsOperator(char ch) {
        if (ch == '&' ||
                ch == '|' ||
                ch == '!' ||
                ch == '<' ||
                ch == '>'
                ) return true;
        else return false;

    }

    public void setAllVariables(HashMap<Character, VariableToPut> variables) {
        setRecursiveAllVariables(root, variables);

    }

    private void setRecursiveAllVariables(Node node, HashMap<Character, VariableToPut> variables) {
        if (node.getVal().getType() == 4) {
            char key = node.getVal().getStringValue().charAt(0);
            if (variables.get(key).isNum()) {
                node.setVal(new NumConstant(variables.get(key).getNum()));
            } else {
                node.setVal(new BoolConstant(variables.get(key).getBol()));
            }


        }
        if (node.getRightChild() != null) {
            setRecursiveAllVariables(node.getRightChild(), variables);
        }
        if (node.getLeftChild() != null) {
            setRecursiveAllVariables(node.getLeftChild(), variables);
        }
    }

    private void getRecursiveAllVariables(Node node, HashMap<Character, VariableToPut> variables) {
        if (node.getVal().getType() == 4) {
            variables.put(node.getVal().getStringValue().charAt(0), null);
        }
        if (node.getRightChild() != null) {
            getRecursiveAllVariables(node.getRightChild(), variables);
        }
        if (node.getLeftChild() != null) {
            getRecursiveAllVariables(node.getLeftChild(), variables);
        }


    }

    private boolean recursiveSolve(Node node) {
        boolean res;
        if (hasTwoChildsNotOperators(node)) {
            String operator;
            operator = node.getVal().getStringValue();


            if (operator.equals("<")) {
                int rightOp = Integer.parseInt(node.getLeftChild().getVal().getStringValue());
                int leftOp = Integer.parseInt(node.getRightChild().getVal().getStringValue());
                res = leftOp < rightOp;
                //OUT
                //System.out.println(Boolean.toString(res) + " = " + leftOp + " < " + rightOp);

                node.setVal(new BoolConstant(res));

            }
            if (operator.equals(">")) {
                int rightOp = Integer.parseInt(node.getLeftChild().getVal().getStringValue());
                int leftOp = Integer.parseInt(node.getRightChild().getVal().getStringValue());


                res = leftOp > rightOp;
                //OUT
                //System.out.println(Boolean.toString(res)+ " = "+leftOp+" > "+ rightOp);

                node.setVal(new BoolConstant(res));


            }
            if (operator.equals("AND")) {
                boolean leftOp;
                boolean rightOp;
                if (node.getLeftChild().getVal().getStringValue().equals("true")) {
                    rightOp = true;
                } else {
                    rightOp = false;
                }
                if (node.getRightChild().getVal().getStringValue().equals("true")) {
                    leftOp = true;
                } else {
                    leftOp = false;
                }


                res = leftOp & rightOp;
                //OUT
                //System.out.println(Boolean.toString(res)+ " = "+Boolean.toString(leftOp)+" & "+Boolean.toString(rightOp));

                node.setVal(new BoolConstant(res));


            }
            if (operator.equals("OR")) {
                boolean leftOp;
                boolean rightOp;
                if (node.getLeftChild().getVal().getStringValue().equals("true")) {
                    rightOp = true;
                } else {
                    rightOp = false;
                }
                if (node.getRightChild().getVal().getStringValue().equals("true")) {
                    leftOp = true;
                } else {
                    leftOp = false;
                }


                res = leftOp | rightOp;
                //OUT
                //System.out.println(Boolean.toString(res) + " = " + Boolean.toString(leftOp) + " | " + Boolean.toString(rightOp));

                node.setVal(new BoolConstant(res));

            }
            if (operator.equals("NOT")) {
                boolean operand;

                if (node.getLeftChild().getVal().getStringValue().equals("true")) {
                    operand = true;
                } else {
                    operand = false;
                }

                res = !operand;
                //OUT
                //System.out.println(Boolean.toString(res)+ " = "+"!"+Boolean.toString(operand));

                node.setVal(new BoolConstant(res));


            }


            if (node.getParent() == null) {
                if (node.getVal().getStringValue().equals("true")) {
                    return true;
                } else {
                    return false;
                }

            } else {
                recursiveSolve(node.getParent());
            }
        }
        if (node.getLeftChild().getVal().getType() == 3) {
            recursiveSolve(node.getLeftChild());
        }
        if (node.getRightChild().getVal().getType() == 3) {
            recursiveSolve(node.getRightChild());
        }
        return true;
    }

    private boolean hasTwoChildsNotOperators(Node node) {
        return node.getLeftChild().getVal().getType() != 3 && (node.getRightChild().getVal().getType() != 3);
    }

}

class Node {
    private static int counter = 0;
    private Atom val;
    private int id;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    Node() {
        id = counter++;
    }

    public int getId() {
        return id;
    }

    public Atom getVal() {

        return val;
    }

    public void setVal(Atom val) {
        this.val = val;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }


}

