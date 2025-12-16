# TodoDemo - .NET 10 MVC TODO Website

A simple and elegant TODO management application built with ASP.NET Core 10 MVC, featuring a clean Bootstrap UI and in-memory data storage.

## Features

- ✨ **Create Tasks**: Add new TODO items with title, description, and completion status
- 📋 **View Tasks**: Display all tasks in a clean, organized table layout
- ✏️ **Edit Tasks**: Update existing task details
- 🗑️ **Delete Tasks**: Remove tasks with confirmation dialog
- ✅ **Toggle Completion**: Quick toggle for marking tasks as complete/incomplete
- 📊 **Statistics**: Real-time display of total, completed, and pending tasks
- 🎨 **Modern UI**: Bootstrap 5 with icons for a polished user experience

## Technology Stack

- **Framework**: ASP.NET Core 10 MVC
- **Language**: C# 13
- **UI**: Bootstrap 5.3 with Bootstrap Icons
- **Storage**: In-memory (static list)

## Getting Started

### Prerequisites

- [.NET 10 SDK](https://dotnet.microsoft.com/download/dotnet/10.0) or later

### Installation & Running

1. Clone the repository:
   ```bash
   git clone https://github.com/MoneyDemo/20251216-GH300.git
   cd 20251216-GH300
   ```

2. Build the project:
   ```bash
   dotnet build
   ```

3. Run the application:
   ```bash
   dotnet run
   ```

4. Open your browser and navigate to:
   ```
   http://localhost:5220
   ```

## Project Structure

```
TodoDemo/
├── Controllers/
│   ├── HomeController.cs      # Default home controller
│   └── TodoController.cs      # Main TODO CRUD operations
├── Models/
│   ├── Todo.cs               # TODO item model
│   └── ErrorViewModel.cs     # Error handling model
├── Services/
│   └── TodoService.cs        # In-memory data management
├── Views/
│   ├── Todo/
│   │   ├── Index.cshtml      # List all TODOs
│   │   ├── Create.cshtml     # Create new TODO
│   │   └── Edit.cshtml       # Edit existing TODO
│   └── Shared/
│       └── _Layout.cshtml    # Main layout template
├── wwwroot/                  # Static files (CSS, JS, libraries)
└── Program.cs                # Application entry point
```

## Usage

### Creating a Task
1. Click the "Add New Task" button
2. Enter a title (required)
3. Optionally add a description
4. Check "Mark as completed" if the task is already done
5. Click "Create Task"

### Viewing Tasks
- All tasks are displayed in a table format
- Completed tasks have a green background and strikethrough text
- Statistics show total, completed, and pending task counts

### Editing a Task
1. Click the edit icon (pencil) next to any task
2. Modify the task details
3. Click "Save Changes"

### Toggling Completion
- Click the status button (circle/check icon) to quickly toggle between complete and incomplete

### Deleting a Task
1. Click the delete icon (trash) next to any task
2. Confirm the deletion in the dialog

## Screenshots

### Empty TODO List
![Empty TODO List](https://github.com/user-attachments/assets/0748d153-5cc7-4e0e-adf1-1e88f2eb611d)

### Create New Task
![Create New Task](https://github.com/user-attachments/assets/ec6b7064-6db1-4492-9fec-0793fbc7656b)

### TODO List with Items
![TODO List with Items](https://github.com/user-attachments/assets/636fe5ad-e2a0-4f45-8def-1cd07390f6f3)

### Edit Task
![Edit Task](https://github.com/user-attachments/assets/f20afd6b-f93a-4f28-97d7-d09e42c64711)

### Completed Tasks
![Completed Tasks](https://github.com/user-attachments/assets/c4532eb4-7ed4-4d5f-89ff-ea5000c41bab)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Notes

- Data is stored in-memory and will be lost when the application restarts
- This is a demonstration project intended for learning and showcasing ASP.NET Core MVC features
- For production use, consider implementing persistent storage (e.g., SQL Server, PostgreSQL, SQLite)