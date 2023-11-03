import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CadastroProdutosApp {
    private JFrame frame;
    private JPanel cards;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nomeProdutoField;
    private JTextField descricaoProdutoField;
    private JTextField valorProdutoField;
    private JCheckBox simField;

    public CadastroProdutosApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cadastro de Produtos");
        frame.setBounds(100, 100, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cards = new JPanel(new CardLayout());
        frame.getContentPane().add(cards, BorderLayout.CENTER);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("Descrição");
        tableModel.addColumn("Valor");
        tableModel.addColumn("Disponivel");

        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);

        JPanel panel = new JPanel();
        cards.add(panel,"cadastro");
        panel.setLayout(new GridLayout(6, 3, 10, 10));

        JLabel lblNomeDoProduto = new JLabel("Nome do Produto:");
        panel.add(lblNomeDoProduto);

        nomeProdutoField = new JTextField();
        panel.add(nomeProdutoField);
        nomeProdutoField.setColumns(10);

        JLabel lblDescricaoDoProduto = new JLabel("Descrição do Produto:");
        panel.add(lblDescricaoDoProduto);

        descricaoProdutoField = new JTextField();
        panel.add(descricaoProdutoField);
        descricaoProdutoField.setColumns(10);

        JLabel lblValorDoProduto = new JLabel("Valor do Produto:");
        panel.add(lblValorDoProduto);

        valorProdutoField = new JTextField();
        panel.add(valorProdutoField);
        valorProdutoField.setColumns(10);

        JLabel disponivel = new JLabel("Disponivel para venda:");
        panel.add(disponivel);

        simField = new JCheckBox("SIM");
        panel.add(simField);


        JButton btnCadastrar = new JButton("Cadastrar");
        panel.add(btnCadastrar);



        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto();
            }
        });

        JButton listButton = new JButton("Listagem");
        panel.add(listButton);

        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showList();
            }
        });

    }

    private void cadastrarProduto() {
        //futuramente uma classe produto para modularizar melhor o código
        Produto produto = new Produto(nomeProdutoField.getText(),descricaoProdutoField.getText(),Double.parseDouble(valorProdutoField.getText()), simField.isSelected());
        String nome = nomeProdutoField.getText();
        String valorStr = valorProdutoField.getText();


        // Validar campos não vazios
        if (nome.isEmpty() || valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nome e valor do produto são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String disponivelEstoque = "Não";
        if(simField.isSelected()){
            disponivelEstoque = "Sim";
        }

        Object[] row = {nomeProdutoField.getText(),descricaoProdutoField.getText(),Double.parseDouble(valorProdutoField.getText()), disponivelEstoque};
        tableModel.addRow(row);

        nomeProdutoField.setText("");
        descricaoProdutoField.setText("");
        valorProdutoField.setText("");
        showList();
    }

    private void showList(){
        JPanel listPanel = new JPanel();
        CardLayout cl = (CardLayout) (cards.getLayout());
        listPanel.setLayout(new GridLayout(3, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(table);

        listPanel.add(scrollPane, BorderLayout.SOUTH);
        scrollPane.setVisible(true);
        JButton btnCadastrar = new JButton("Cadastrar um novo produto");
        listPanel.add(btnCadastrar);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               cl.show(cards,"cadastro");
            }
        });
        cards.add(listPanel, "painel");
        cl.show(cards, "painel");

    }
    public void show() {
        frame.setVisible(true);
    }


}
