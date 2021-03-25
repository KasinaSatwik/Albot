package com.example.albot;

import android.database.Cursor;
import android.os.Bundle;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.AIMLProcessorExtension;
import org.alicebot.ab.ParseState;
import org.alicebot.ab.Utilities;
import org.w3c.dom.Node;

import java.util.Set;



class TestAIMLExtenstion implements AIMLProcessorExtension
{

    DBHelper DB;
    public Set<String> extensionTagNames = Utilities.stringSet("name");
    public Set <String> extensionTagSet() {
        return extensionTagNames;
    }

    public String recursEval(Node node, ParseState ps) {
        try {
            String nodeName = node.getNodeName();
            if (nodeName.equals("name"))
                return getname(node, ps);

            else return (AIMLProcessor.genericXML(node, ps));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public String getname(Node node, ParseState ps) {
        Cursor res = DB.getdata();
        String name = new String();
        while (res.moveToNext()) {
            name = res.getString(0);
        }
        return name;
    }



}