package Gui.Town;

import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Gui.BottomPanel;
import Skill.BattleSkillBase;
import item.Item;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Storage extends JPanel {

    private static final long serialVersionUID = 1L;
    static String DEFULT_PATH = "D:/JavaWorkSpace/my_rpg/MyRpg/src/res/storage/";
    static String[] skill_name;
    static String[] item_name;

    public static JList<String> list_1;
    public static JList<String> list_2;
    public static JList<String> list_3;

    public static String content;
    ImageIcon skillIcon = new ImageIcon(DEFULT_PATH + "skill/1.jpg");
    ImageIcon magicIcon = new ImageIcon(DEFULT_PATH + "magic/1.jpg");
    ImageIcon itemIcon = new ImageIcon(DEFULT_PATH + "item/1.gif");
    JTextArea skill_effect;
    JTextArea magic_effect;
    JTextArea item_effect;
    JLabel skill_icon;
    JLabel item_icon;

    public Storage() {
        super();
        setLayout(new BorderLayout());
        skill_name = BattleSkillBase.unlock_skill_name.stream().toArray(String[]::new);
        item_name = Item.unlock_item.stream().toArray(String[]::new);

        JLabel add_skill = new JLabel("選擇技能");
        add_skill.setFont(new Font("Serif", Font.BOLD, 20));
        JButton add_btn_1 = new JButton("裝配技能");
        add_btn_1.setFont(new Font("Serif", Font.BOLD, 16));
        add_btn_1.setMargin(new Insets(5, 5, 5, 5));

        JLabel add_magic = new JLabel("選擇魔法");
        add_magic.setFont(new Font("Serif", Font.BOLD, 20));
        JButton add_btn_2 = new JButton("記憶魔法");
        add_btn_2.setFont(new Font("Serif", Font.BOLD, 16));
        add_btn_2.setMargin(new Insets(5, 5, 5, 5));

        JLabel add_item = new JLabel("選擇寶具");
        add_item.setFont(new Font("Serif", Font.BOLD, 20));
        JButton add_btn_3 = new JButton("攜帶寶具");
        add_btn_3.setFont(new Font("Serif", Font.BOLD, 16));
        add_btn_3.setMargin(new Insets(5, 5, 5, 5));

        list_1 = new JList<String>();
        list_2 = new JList<String>();
        list_3 = new JList<String>();
        JList<String> list_4 = new JList<String>();
        JList<String> list_5 = new JList<String>();
        JList<String> list_6 = new JList<String>();

        // 設置技能選項數據
        list_1.setListData(skill_name);
        list_4.setListData(BattleSkillBase.in_use_name);

        // 設置寶具選項數據
        list_3.setListData(item_name);
        list_6.setListData(Item.in_use_item);

        // 添加选项选中状态被改变的监听器
        list_1.setFont(new Font("Serif", Font.BOLD, 16));
        list_1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 获取所有被选中的选项索引
                int[] indices = list_1.getSelectedIndices();
                // 获取选项数据的 ListModel
                ListModel<String> listModel = list_1.getModel();
                // 输出选中的选项
                for (int index : indices) {
                    add_skill.setText(listModel.getElementAt(index));
                    int i = BattleSkillBase.getIndexByName(add_skill.getText());
                    int rarity = Integer.valueOf(BattleSkillBase.skill_list.get(i)[5].toString());
                    switch (rarity) {
                        case 0:
                            skill_effect.setFont(new Font("Serif", Font.BOLD, 16));
                            skill_effect.setForeground(Color.BLACK);
                            content = "等級：" + '\n' + BattleSkillBase.getRarity(rarity) + '\n' + '\n';
                            break;
                        case 1:
                            skill_effect.setFont(new Font("Serif", Font.BOLD, 16));
                            skill_effect.setForeground(Color.BLUE);
                            content = "等級：" + '\n' + BattleSkillBase.getRarity(rarity) + '\n' + '\n';
                            break;
                        case 2:
                            skill_effect.setFont(new Font("Serif", Font.BOLD, 16));
                            skill_effect.setForeground(Color.RED);
                            content = "等級：" + '\n' + BattleSkillBase.getRarity(rarity) + '\n' + '\n';
                            break;
                    }

                    content += "說明：" + '\n' + BattleSkillBase.skill_list.get(i)[2].toString() + '\n' + '\n';
                    content += "效果：" + '\n' + BattleSkillBase.skill_list.get(i)[3].toString();
                    skill_effect.setText(content);
                    skillIcon = new ImageIcon(DEFULT_PATH + "skill/" + (i) + ".jpg");
                    skill_icon.setIcon(skillIcon);
                    skill_icon.repaint();
                }

            }
        });

        list_3.setFont(new Font("Serif", Font.BOLD, 16));
        list_3.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {             
                int[] indices = list_3.getSelectedIndices();         
                ListModel<String> listModel = list_3.getModel();
                int i = Item.getIndexByName(add_item.getText());
                for (int index : indices) {
                    add_item.setText(listModel.getElementAt(index));
                    content = "等級：" + Item.item_list.get(i)[1].toString() + " 級寶具" +'\n' + '\n';
                    content += "效果：" + '\n' + Item.item_list.get(i)[3].toString() + '\n' + '\n';
                    content += "說明：" + '\n' + Item.item_list.get(i)[4].toString();


                    item_effect.setText(content);
                    item_effect.setFont(new Font("Serif", Font.BOLD, 16));
                    itemIcon = new ImageIcon(DEFULT_PATH + "item/" + (i) + ".gif");
                    item_icon.setIcon(itemIcon);
                    item_icon.repaint();
                }
            }
        });

        list_4.setFont(new Font("Serif", Font.BOLD, 16));
        list_4.setPreferredSize(new Dimension(150, 150));
        list_4.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indices = list_4.getSelectedIndices();
                ListModel<String> listModel = list_4.getModel();
                for (int index : indices) {
                    int i = BattleSkillBase.getIndexByName(listModel.getElementAt(index));
                    if (i != 0) {
                        BottomPanel.content = BattleSkillBase.skill_list.get(i)[2].toString() + '\n' + '\n' + "效果："
                                + BattleSkillBase.skill_list.get(i)[3].toString();
                        BottomPanel.resetTextArea();
                    }
                }
            }
        });

        // TODO: finish
        list_5.setFont(new Font("Serif", Font.BOLD, 16));
        list_5.setPreferredSize(new Dimension(150, 30));

        list_6.setFont(new Font("Serif", Font.BOLD, 16));
        list_6.setPreferredSize(new Dimension(150, 30));
        list_6.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indices = list_6.getSelectedIndices();
                ListModel<String> listModel = list_6.getModel();
                for (int index : indices) {
                    int i = Item.getIndexByName(listModel.getElementAt(index));
                    if (i != 0) {
                        BottomPanel.content = "效果：" + Item.item_list.get(i)[3].toString() + '\n' + '\n'
                                + Item.item_list.get(i)[4].toString();
                        BottomPanel.resetTextArea();
                    }
                }
            }
        });

        add_btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = BattleSkillBase.getIndexByName(add_skill.getText());
                if (i > 0) {
                    // 重設技能
                    BattleSkillBase.setSkillByIndex(i);
                    list_4.setListData(BattleSkillBase.in_use_name);
                    list_4.repaint();
                }
            }
        });

        add_btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = add_item.getText();
                Item.setItemByName(name);
                list_6.setListData(Item.in_use_item);
                list_6.repaint();
            }
        });

        // 添加到内容面板容器 - west

        JPanel storageJPanel = new JPanel();
        storageJPanel.setLayout(new BoxLayout(storageJPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane_1 = new JScrollPane(list_1);
        jScrollPane_1.setPreferredSize(new Dimension(150, 150));

        JScrollPane jScrollPane_2 = new JScrollPane(list_2);
        jScrollPane_2.setPreferredSize(new Dimension(150, 150));

        JScrollPane jScrollPane_3 = new JScrollPane(list_3);
        jScrollPane_3.setPreferredSize(new Dimension(150, 150));

       
   
      





        storageJPanel.add(jScrollPane_1);
        storageJPanel.add(jScrollPane_2);
        storageJPanel.add(jScrollPane_3);

        // 中間 -3
        JPanel chosePanel = new JPanel();
        chosePanel.setOpaque(false);
        chosePanel.setLayout(new MigLayout("wrap 2", "100[]40[]", "30[]55[]55[]"));

        // 中間 1-1
        JPanel chose_1 = new JPanel();
        chose_1.setAlignmentY(Component.TOP_ALIGNMENT);
        chose_1.setBackground(Color.WHITE);
        chose_1.setLayout(new BoxLayout(chose_1, BoxLayout.Y_AXIS));

        skill_icon = new JLabel();
        skill_icon.setIcon(skillIcon);
        skill_icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        add_skill.setAlignmentX(Component.CENTER_ALIGNMENT);
        add_btn_1.setAlignmentX(Component.CENTER_ALIGNMENT);

        chose_1.add(add_skill);
        chose_1.add(Box.createRigidArea(new Dimension(0, 20)));
        chose_1.add(skill_icon);
        chose_1.add(Box.createRigidArea(new Dimension(0, 20)));
        chose_1.add(add_btn_1);

        JPanel chose_1_1 = new JPanel();
        chose_1.setLayout(new BoxLayout(chose_1, BoxLayout.Y_AXIS));
        skill_effect = new JTextArea();
        skill_effect.setLineWrap(true);
        skill_effect.setEditable(false);
        skill_effect.setWrapStyleWord(true);
        skill_effect.setText(content);
        chose_1_1.setOpaque(false);
        JScrollPane jScrollPane_4 = new JScrollPane(skill_effect);
        jScrollPane_4.setPreferredSize(new Dimension(500, 250));
        chose_1_1.add(jScrollPane_4);

        // 中間 1-2
        JPanel chose_2 = new JPanel();
        chose_2.setAlignmentY(Component.CENTER_ALIGNMENT);
        chose_2.setBackground(Color.WHITE);
        chose_2.setLayout(new BoxLayout(chose_2, BoxLayout.Y_AXIS));

        JLabel magic_icon = new JLabel();
        magic_icon.setIcon(magicIcon);
        magic_icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        add_magic.setAlignmentX(Component.CENTER_ALIGNMENT);
        add_btn_2.setAlignmentX(Component.CENTER_ALIGNMENT);

        chose_2.add(add_magic);
        chose_2.add(Box.createRigidArea(new Dimension(0, 20)));
        chose_2.add(magic_icon);
        chose_2.add(Box.createRigidArea(new Dimension(0, 20)));
        chose_2.add(add_btn_2);

        // TODO: finish
        JPanel chose_2_1 = new JPanel();
        magic_effect = new JTextArea(10, 50);
        magic_effect.setEditable(false);
        chose_2_1.setOpaque(false);
        chose_2_1.add(magic_effect);

        // 中間 1-3
        JPanel chose_3 = new JPanel();
        chose_3.setLayout(new BoxLayout(chose_3, BoxLayout.Y_AXIS));
        chose_3.setBackground(Color.WHITE);
        chose_3.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        item_icon = new JLabel();
        item_icon.setIcon(itemIcon);
        item_icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        add_item.setAlignmentX(Component.CENTER_ALIGNMENT);
        add_btn_3.setAlignmentX(Component.CENTER_ALIGNMENT);

        chose_3.add(add_item);
        chose_3.add(Box.createRigidArea(new Dimension(0, 20)));
        chose_3.add(item_icon);
        chose_3.add(Box.createRigidArea(new Dimension(0, 20)));
        chose_3.add(add_btn_3);

        JPanel chose_3_1 = new JPanel();
        item_effect = new JTextArea(50, 50);
        item_effect.setEditable(false);
        chose_3_1.setOpaque(false);
        JScrollPane jScrollPane_6 = new JScrollPane(item_effect);
        jScrollPane_6.setPreferredSize(new Dimension(500, 250));
        chose_3_1.add(jScrollPane_6);

        chosePanel.add(chose_1);
        chosePanel.add(chose_1_1);
        chosePanel.add(chose_2);
        chosePanel.add(chose_2_1);
        chosePanel.add(chose_3);
        chosePanel.add(chose_3_1);

        // 東邊
        JPanel WearJPanel = new JPanel();
        WearJPanel.setLayout(new BoxLayout(WearJPanel, BoxLayout.Y_AXIS));
        WearJPanel.setOpaque(false);

        JButton wear_skill = new JButton("(已裝備)");
        wear_skill.setFont(new Font("Serif", Font.BOLD, 20));
        wear_skill.setAlignmentX(CENTER_ALIGNMENT);

        WearJPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        WearJPanel.add(list_4);
        WearJPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        list_4.setAlignmentX(CENTER_ALIGNMENT);
        WearJPanel.add(wear_skill);


        JButton wear_magic = new JButton("(已裝備)");
        wear_magic.setFont(new Font("Serif", Font.BOLD, 20));
        wear_magic.setAlignmentX(CENTER_ALIGNMENT);

        WearJPanel.add(Box.createRigidArea(new Dimension(0, 130)));
        WearJPanel.add(list_5);
        WearJPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        list_5.setAlignmentX(CENTER_ALIGNMENT);
        WearJPanel.add(wear_magic);

        JButton wear_item = new JButton("(已裝備)");
        wear_item.setFont(new Font("Serif", Font.BOLD, 20));
        wear_item.setAlignmentX(CENTER_ALIGNMENT);

        WearJPanel.add(Box.createRigidArea(new Dimension(0, 130)));
        WearJPanel.add(list_6);
        WearJPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        list_6.setAlignmentX(CENTER_ALIGNMENT);
        WearJPanel.add(wear_item);

 

        // 最後添入
        add(storageJPanel, BorderLayout.WEST);
        add(WearJPanel, BorderLayout.EAST);
        add(chosePanel);

    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawStorage(g);
    }

    protected void drawStorage(final Graphics g) {
        final Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/storage.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
