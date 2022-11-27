import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.bson.BSONObject;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Interface extends JFrame implements ActionListener {

    private ArrayList<String> cssSelector = new ArrayList<>();
    private DefaultListModel<Element> listModel = new DefaultListModel();

    DBObject dbo = new DBObject() {
        @Override
        public void markAsPartialObject() {

        }

        @Override
        public boolean isPartialObject() {
            return false;
        }

        @Override
        public Object put(String s, Object o) {
            return null;
        }

        @Override
        public void putAll(BSONObject bsonObject) {

        }

        @Override
        public void putAll(Map map) {

        }

        @Override
        public Object get(String s) {
            return null;
        }

        @Override
        public Map toMap() {
            return null;
        }

        @Override
        public Object removeField(String s) {
            return null;
        }

        @Override
        public boolean containsKey(String s) {
            return false;
        }

        @Override
        public boolean containsField(String s) {
            return false;
        }

        @Override
        public Set<String> keySet() {
            return null;
        }
    };

    ObjectMapper mapper = new ObjectMapper();

    private JComboBox jcb;
    private JList<Elements> jl;

    Document doc;
    Selector selector;

    {
        try {
            doc = Jsoup.connect("http://www.unimol.it/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //cssQuery con utilizzo degli cssSelector
    Elements links = doc.select("a[href]");
    Elements pngs = doc.select("img[src$=.png]");
    Elements selectorClass = doc.select(".revsliderstyles");
    Elements id_selector = doc.select("#cookie-notice");
    Elements select_script = doc.select("body > script:nth-child(7)");
    Elements select_attribute = doc.select("[^data-]");
    Elements select_attribute2 = doc.select("[href*=#]");




    public void GUI() {
        JPanel panel = new JPanel(new BorderLayout());
        creaElencoSelettori();
        jcb = new JComboBox();
        jl = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane();

        jcb = aggiunta();
        jcb.addActionListener(this);

        scrollPane.setViewportView(jl);
        jl.setLayoutOrientation(JList.VERTICAL);

        panel.add(jcb, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JFrame frame = new JFrame("Selettori");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public void creaElencoSelettori() {
        cssSelector.add("selector links");
        cssSelector.add("selector png");
        cssSelector.add("selector class");
        cssSelector.add("selector id");
        cssSelector.add("selector script");
        cssSelector.add("selector attribute 1");
        cssSelector.add("selector attribute 2");
    }


    public JComboBox aggiunta() {
        for (int i = 0; i < cssSelector.size(); i++) {
            jcb.addItem(cssSelector.get(i));
        }
        return jcb;
    }

    public void output0() {
        JSONObject j = new JSONObject();
        for (Element e : links) {
            listModel.addElement(e);
            j.put("a[href]", e);
            try {
                DBObject dbObject = (DBObject) JSON.parse(j.toString());
                selector.collection.insert(dbObject);
            } catch (Exception ex) {
                System.out.println(ex);
           }
        }
    }

    public void output1() {
        JSONObject j = new JSONObject();
        for (Element e : pngs) {
            listModel.addElement(e);
            j.put("img[src$=.png]", e);
            try{
                DBObject dbObject = (DBObject) JSON.parse(j.toString());
                selector.collection.insert(dbObject);
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    public void output2() {
        JSONObject j = new JSONObject();
        for (Element e : selectorClass) {
            listModel.addElement(e);
            j.put(".revsliderstyles", e);
            try{
                DBObject dbObject = (DBObject) JSON.parse(j.toString());
                selector.collection.insert(dbObject);
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    public void output3() {
        JSONObject j = new JSONObject();
        for (Element e : id_selector) {
            listModel.addElement(e);
            j.put("#cookie-notice", e);
            try{
                DBObject dbObject = (DBObject) JSON.parse(j.toString());
                selector.collection.insert(dbObject);
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    public void output4() {
        JSONObject j = new JSONObject();
        for (Element e : select_script) {
            listModel.addElement(e);
            j.put("body > script:nth-child(7)", e);
            try{
                DBObject dbObject = (DBObject) JSON.parse(j.toString());
                selector.collection.insert(dbObject);
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    public void output5() {
        JSONObject j = new JSONObject();
        for (Element e : select_attribute) {
            listModel.addElement(e);
            j.put("[^data-]", e);
            try{
                DBObject dbObject = (DBObject) JSON.parse(j.toString());
                selector.collection.insert(dbObject);
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    public void output6() {
        JSONObject j = new JSONObject();
        for (Element e : select_attribute2) {
            listModel.addElement(e);
            j.put("[href*=#]",e);
            try{
                DBObject dbObject = (DBObject) JSON.parse(j.toString());
                selector.collection.insert(dbObject);
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();
        listModel.clear();
        if (o == jcb) {
            int i = jcb.getSelectedIndex();
            if (i == 0)
                output0();
            if (i == 1)
                output1();
            if (i == 2)
                output2();
            if (i == 3)
                output3();
            if (i == 4)
                output4();
            if (i == 5)
                output5();
            if (i == 6)
                output6();
        }
    }
}