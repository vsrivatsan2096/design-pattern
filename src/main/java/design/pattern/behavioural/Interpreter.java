package design.pattern.behavioural;

import common.helper.context.UserContext;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * {@link Interpreter} is used when it is needed to process a simple language with rules or grammar
 * */
public interface Interpreter {}

class ExpressionParser {
    public Expression parseExpression(String expression) {
        String postfixExp = convertToPostfix(expression);

        return parsePostfix(postfixExp);
    }

    private String convertToPostfix(String expression) {
        StringTokenizer stringTokenizer = new StringTokenizer(expression);

        Stack<String> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();

            switch (token) {
                case "AND":
                    stack.push(token);
                    break;
                case "NOT":
                    while (!stack.isEmpty() && stack.peek().equals("AND")) {
                        postfix.append(" ").append(stack.pop());
                    }
                    stack.push(token);
                    break;
                default:
                    postfix.append(" ").append(token);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(" ").append(stack.pop());
        }

        return postfix.toString();
    }

    private Expression parsePostfix(String postfixExp) {
        StringTokenizer stringTokenizer = new StringTokenizer(postfixExp);
        Stack<Expression> stack = new Stack<>();

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();

            switch (token) {
                case "AND":
                    Expression expression1 = stack.pop();
                    Expression expression2 = stack.pop();

                    AndExpression expression = new AndExpression(expression1, expression2);
                    stack.push(expression);
                    break;
                case "NOT":
                    Expression expression3 = stack.pop();

                    NotExpression notExpression = new NotExpression(expression3);
                    stack.push(notExpression);
                    break;
                default:
                    PermissionExpression permissionExpression = new PermissionExpression(token);
                    stack.push(permissionExpression);
            }
        }

        return stack.pop();
    }
}

abstract class Expression implements Interpreter {
    abstract boolean interpret(UserContext userContext);
    public static void main(String[] args) {
        UserContext userContext1 = new UserContext("Srivatsan", "ADMIN", "USER");
        UserContext userContext2 = new UserContext("Sam", "USER");

        String samplePermissionExpression = "USER AND ADMIN";

        ExpressionParser expressionParser = new ExpressionParser();
        Expression expression = expressionParser.parseExpression(samplePermissionExpression);


        System.out.println(expression);

        System.out.println(expression.interpret(userContext1));
        System.out.println(expression.interpret(userContext2));

    }
}

class PermissionExpression extends Expression {
    private final String permission;

    public PermissionExpression(String permission) {
        this.permission = permission.toUpperCase();
    }

    @Override
    public boolean interpret(UserContext userContext) {
        return userContext.getRoleList().contains(this.permission);
    }

    @Override
    public String toString() {
        return permission;
    }
}

class AndExpression extends Expression {
    private final Expression permissionExpression1;
    private final Expression permissionExpression2;
    public AndExpression(Expression permissionExpression1, Expression permissionExpression2) {
        this.permissionExpression1 = permissionExpression1;
        this.permissionExpression2 = permissionExpression2;
    }

    @Override
    public boolean interpret(UserContext userContext) {
        return permissionExpression1.interpret(userContext) && permissionExpression2.interpret(userContext);
    }

    @Override
    public String toString() {
        return permissionExpression1 + " && " + permissionExpression2;
    }
}

class NotExpression extends Expression {
    private final Expression permissionExpression;

    NotExpression(Expression permissionExpression) {
        this.permissionExpression = permissionExpression;
    }

    @Override
    public boolean interpret(UserContext userContext) {
        return !permissionExpression.interpret(userContext);
    }

    @Override
    public String toString() {
        return "! " + permissionExpression;
    }
}