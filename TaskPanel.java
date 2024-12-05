import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TaskPanel extends JPanel {
    private Task task;
    private JCheckBox checkBox;
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JButton editButton;
    private JButton deleteButton;

    public TaskPanel(Task task, ActionListener editListener, ActionListener deleteListener, ActionListener checkListener) {
        this.task = task;
        setLayout(new BorderLayout(10, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Panel izquierdo para checkbox y título
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkBox = new JCheckBox();
        checkBox.setSelected(task.isCompleted());
        checkBox.addActionListener(checkListener);
        
        titleLabel = new JLabel(task.getTitle());
        leftPanel.add(checkBox);
        leftPanel.add(titleLabel);

        // Panel derecho para fecha y botones
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        dateLabel = new JLabel(task.getDueDate());
        editButton = new JButton("Editar");
        deleteButton = new JButton("Eliminar");

        editButton.addActionListener(editListener);
        deleteButton.addActionListener(deleteListener);

        rightPanel.add(dateLabel);
        rightPanel.add(editButton);
        rightPanel.add(deleteButton);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        updateStyle();
    }

    public void updateTask(Task task) {
        this.task = task;
        titleLabel.setText(task.getTitle());
        dateLabel.setText(task.getDueDate());
        checkBox.setSelected(task.isCompleted());
        updateStyle();
    }

    private void updateStyle() {
        if (task.isCompleted()) {
            titleLabel.setForeground(Color.GRAY);
        } else {
            titleLabel.setForeground(Color.BLACK);
        }
    }

    public Task getTask() {
        return task;
    }
}
