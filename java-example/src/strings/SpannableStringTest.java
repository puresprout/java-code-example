package strings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.Stack;

/**
 * Created by sunghyun on 2017. 3. 13..
 */
public class SpannableStringTest {
    class Info {
        Node n;
        int start;

        Info(Node n, int start) {
            this.n = n;
            this.start = start;
        }
    }

    public static void main(String[] args) throws Exception {
        SpannableStringTest spannableStringTest = new SpannableStringTest();
        spannableStringTest.test();
    }

    public void test() throws Exception {
        String str = "<root>_normal_<B>_Bold_<I>_BoldAndItalic_</I>_Bold_</B>_normal_</root>";
        ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);

        System.out.println(str + "\n");

        Stack<Info> stack = new Stack();
        StringBuilder sb = new StringBuilder();
        Element el = doc.getDocumentElement();
        process(stack, el, sb);

        System.out.println("\n" + sb.toString());
    }

    private void process(Stack<Info> stack, Node node, StringBuilder sb) {
        NodeList childNodes = node.getChildNodes();

        // leaf 노드인 확인
        if (childNodes == null || childNodes.getLength() == 0) {
            // leaft 노드는 텍스트이기 때문에 append로 텍스트만 추가
            sb.append(node.getNodeValue());
            System.out.println("called spannableStringBuilder.append('" + node.getNodeValue() + "')");
            return;
        }

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node n = childNodes.item(i);

            // 태그 노드일 때만 스택에 push, 현 태그가 시작하는 위치로 함께
            if (n.getNodeType() != Node.TEXT_NODE) {
                stack.push(new Info(n, sb.length()));
            }

            // 재귀 호출
            process(stack, n, sb);

            // 태그 노드일 때만 스택에서 pop, 그리고 setSpan으로 시작위치와 현 위치까지 span 설정
            if (n.getNodeType() != Node.TEXT_NODE) {
                Info info = stack.pop();
                System.out.println("called spannableStringBuilder.setSpan('" + info.n.getNodeName() + "', " + info.start + ", " + sb.length() + ")");
            }
        }
    }
}
