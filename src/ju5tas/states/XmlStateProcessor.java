package ju5tas.states;

import ju5tas.states.handlers.CustomStateHandler;
import ju5tas.states.handlers.StateHandler;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlStateProcessor extends StateProcessor {

    private final String filename;

    public XmlStateProcessor(String filename) {
        this.filename = filename;
        configure();
    }

    @Override
    public void configure() {
        SAXBuilder builder = new SAXBuilder();
        Document document;
        try {
            document = builder.build(new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Ошибка в конфигурационном файле " + filename + ": " + e.getMessage() + "\nПрименены настройки по-умолчанию.");
            loadDefault();
            return;
        }

        Element root = document.getRootElement();
        List<Element> elements = root.getChildren();
        Map<String, StateHandler> handlers = new HashMap<>();

        boolean hasMain = false;

        for (Element e : elements) {
            CustomStateHandler handler = new CustomStateHandler();
            String handlerName = e.getAttributeValue("name");
            handler.setName(handlerName);

            if (Boolean.valueOf(e.getAttributeValue("main"))) {
                setHandler(handler);
                hasMain = true;
            }

            String includeLast = e.getAttributeValue("includeLast");
            boolean endChar = !"false".equals(includeLast);
            handler.includeEndChar(endChar);

            String handlerAfter = e.getAttributeValue("charAfter");
            if (handlerAfter != null) {
                char customChar = getEscapeSymbol(handlerAfter);
                handler.printCustomChar(customChar, true);
            }
            handlers.put(handlerName, handler);
        }

        for (Element node : elements) {
            CustomStateHandler h = (CustomStateHandler) handlers.get(node.getAttributeValue("name"));
            for (Element e : node.getChildren("rule")) {
                Character symbol;
                StateHandler link;
                State state;


                link = handlers.get(e.getAttributeValue("link"));
                symbol = getEscapeSymbol(e.getAttributeValue("symbol"));
                state = getStateByName(e.getAttributeValue("state"));

                h.addRule(new CustomStateHandler.Rule(symbol, link, state));
            }
        }

        if (!hasMain) throw new RuntimeException("Нет описан начальный обрабочтик в XML");
    }


    private State getStateByName(String param) {
        String st = param.toUpperCase();
        if ("COMMENT".equals(st)) {
            return State.COMMENT;
        }
        if ("TEXT".equals(st)) {
            return State.TEXT;
        }
        throw new RuntimeException("Incorrect state in xml file");
    }

    private Character getEscapeSymbol(String param) {
        if (param == null || "".equals(param)) return null;
        char customChar = param.charAt(0);
        if (customChar == '\\' && param.length() > 1) {
            char c = param.charAt(1);
            switch (c) {
                case 'n':
                    customChar = '\n';
                    break;
                case 'r':
                    customChar = '\r';
                    break;
                case 't':
                    customChar = '\t';
                    break;
            }
        }
        return customChar;
    }

}
