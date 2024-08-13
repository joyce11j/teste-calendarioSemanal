import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class CalendarioSemanal {

    private static Calendar calendar = Calendar.getInstance(); // Variável global para manter o estado da data inicial
    private static JPanel panel;
    private static JLabel[] labelsDias = new JLabel[5]; // Array para manter as referências aos labels dos dias

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendário Semanal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        // Painel principal de calendário
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 6)); // 5 linhas (horários) x 6 colunas (dias + horários)

        // Painel de navegação
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnPrevWeek = new JButton("Semana Anterior");
        JButton btnNextWeek = new JButton("Próxima Semana");

        navigationPanel.add(btnPrevWeek);
        navigationPanel.add(btnNextWeek);

        // Títulos das colunas
        panel.add(new JLabel("Horário", SwingConstants.CENTER));

        // Adicionando os dias da semana com numeração dos dias
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        for (int i = 0; i < dias.length; i++) {
            labelsDias[i] = new JLabel("", SwingConstants.CENTER);
            panel.add(labelsDias[i]);
        }

        // Horários
        String[] horarios = {"07h", "08h", "09h", "10h"};

        // Adicionando os horários e células de tempo
        for (String horario : horarios) {
            // Coluna de horários
            JLabel labelHorario = new JLabel(horario, SwingConstants.CENTER);
            panel.add(labelHorario);

            // Colunas dos dias
            for (int i = 0; i < dias.length; i++) {
                JButton botao = new JButton(" ");
                panel.add(botao);
            }
        }

        // Atualiza os dias da semana com a data inicial
        atualizarDiasDaSemana();

        // Ação para o botão "Semana Anterior"
        btnPrevWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
                atualizarDiasDaSemana();
            }
        });

        // Ação para o botão "Próxima Semana"
        btnNextWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                atualizarDiasDaSemana();
            }
        });

        frame.add(navigationPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Função para atualizar os dias da semana com a data correspondente
    private static void atualizarDiasDaSemana() {
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Definindo a data para segunda-feira

        for (int i = 0; i < dias.length; i++) {
            String diaComNumero = dias[i] + " - " + tempCalendar.get(Calendar.DAY_OF_MONTH);
            labelsDias[i].setText(diaComNumero);
            tempCalendar.add(Calendar.DATE, 1); // Avançar para o próximo dia
        }
    }
}
