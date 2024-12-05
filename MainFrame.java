/**
 * MainFrame es la ventana principal de la aplicación de tareas. Extiende JFrame y proporciona
 * una interfaz de usuario para gestionar tareas. La interfaz incluye campos de entrada para añadir nuevas tareas,
 * una lista de tareas desplazable y botones para editar, eliminar y alternar la finalización de tareas.
 * 
 * Componentes:
 * - TaskManager taskManager: Gestiona la lista de tareas.
 * - JPanel tasksContainer: Contenedor para mostrar la lista de tareas.
 * - JTextField titleField: Campo de entrada para el título de la tarea.
 * - JTextField descriptionField: Campo de entrada para la descripción de la tarea.
 * - JTextField dateField: Campo de entrada para la fecha de la tarea.
 * 
 * Métodos:
 * - MainFrame(): Constructor que inicializa el gestor de tareas y la interfaz de usuario.
 * - void initializeUI(): Configura el marco principal y sus componentes.
 * - JPanel createInputPanel(): Crea y devuelve el panel de entrada para añadir nuevas tareas.
 * - void addNewTask(): Añade una nueva tarea al gestor de tareas y actualiza la lista de tareas.
 * - void clearInputFields(): Limpia los campos de entrada.
 * - void refreshTaskList(): Actualiza la lista de tareas mostrada en el contenedor de tareas.
 * - void editTask(int index): Edita el título de la tarea en el índice especificado.
 * - void deleteTask(int index): Elimina la tarea en el índice especificado después de la confirmación.
 * - void toggleTaskCompletion(int index): Alterna el estado de finalización de la tarea en el índice especificado.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    private TaskManager taskManager;
    private JPanel tasksContainer;
    private JTextField titleField;
    private JTextField descriptionField;
    private JTextField dateField;

    public MainFrame() {
        taskManager = new TaskManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gestor de Tareas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Panel superior para añadir tareas
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Panel central con scroll para la lista de tareas
        tasksContainer = new JPanel();
        tasksContainer.setLayout(new BoxLayout(tasksContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(tasksContainer);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos de entrada
        titleField = new JTextField(15);
        descriptionField = new JTextField(20);
        dateField = new JTextField(10);
        JButton addButton = new JButton("Añadir Tarea");

        // Añadir componentes al panel
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(descriptionField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Fecha:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(dateField, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        inputPanel.add(addButton, gbc);

        addButton.addActionListener(e -> addNewTask());

        return inputPanel;
    }

    private void addNewTask() {
        String title = titleField.getText().trim();
        String description = descriptionField.getText().trim();
        String date = dateField.getText().trim();

        if (!title.isEmpty() && !date.isEmpty()) {
            Task newTask = new Task(title, description, date);
            taskManager.addTask(newTask);
            refreshTaskList();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Por favor, complete al menos el título y la fecha",
                "Campo requerido", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearInputFields() {
        titleField.setText("");
        descriptionField.setText("");
        dateField.setText("");
    }

    private void refreshTaskList() {
        tasksContainer.removeAll();
        List<Task> tasks = taskManager.getAllTasks();
        
        for (int i = 0; i < tasks.size(); i++) {
            final int index = i;
            Task task = tasks.get(i);
            
            TaskPanel taskPanel = new TaskPanel(
                task,
                e -> editTask(index),
                e -> deleteTask(index),
                e -> toggleTaskCompletion(index)
            );
            
            tasksContainer.add(taskPanel);
            tasksContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        tasksContainer.revalidate();
        tasksContainer.repaint();
    }

    private void editTask(int index) {
        Task task = taskManager.getTask(index);
        String newTitle = JOptionPane.showInputDialog(this, "Nuevo título:", task.getTitle());
        if (newTitle != null && !newTitle.trim().isEmpty()) {
            task.setTitle(newTitle.trim());
            refreshTaskList();
        }
    }

    private void deleteTask(int index) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea eliminar esta tarea?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            taskManager.removeTask(index);
            refreshTaskList();
        }
    }

    private void toggleTaskCompletion(int index) {
        taskManager.toggleTaskCompletion(index);
        refreshTaskList();
    }
}