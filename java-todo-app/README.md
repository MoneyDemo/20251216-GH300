# Java TODO App - Spring Boot with Tailwind CSS

A modern, elegant TODO management application built with Spring Boot 3, Thymeleaf, and Tailwind CSS. This is the Java version based on the .NET TodoDemo application, featuring a completely redesigned UI using Tailwind CSS.

## Features

- ✨ **Create Tasks**: Add new TODO items with title, description, and completion status
- 📋 **View Tasks**: Display all tasks in a clean, organized table layout
- ✏️ **Edit Tasks**: Update existing task details
- 🗑️ **Delete Tasks**: Remove tasks with confirmation dialog
- ✅ **Toggle Completion**: Quick toggle for marking tasks as complete/incomplete
- 📊 **Statistics**: Real-time display of total, completed, and pending tasks
- 🎨 **Modern UI**: Tailwind CSS for a polished, responsive user experience
- 🚀 **Fast & Lightweight**: No heavy CSS frameworks, just Tailwind via CDN

## Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17+
- **Template Engine**: Thymeleaf
- **UI**: Tailwind CSS 3 (via CDN)
- **Build Tool**: Maven
- **Storage**: In-memory (thread-safe with ReentrantLock)

## Key Differences from .NET Version

1. **CSS Framework**: Replaced Bootstrap 5 with Tailwind CSS for a more modern, utility-first approach
2. **Template Engine**: Using Thymeleaf instead of Razor
3. **Thread Safety**: Implemented with Java's ReentrantLock instead of C#'s lock statement
4. **Styling**: Custom Tailwind design with improved UI/UX
5. **Responsive Design**: Enhanced mobile-first responsive layout

## Getting Started

### Prerequisites

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or later
- [Maven 3.6+](https://maven.apache.org/download.cgi)

### Installation & Running

1. Clone the repository:
   ```bash
   git clone https://github.com/MoneyDemo/20251216-GH300.git
   cd 20251216-GH300/java-todo-app
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```

## Project Structure

```
java-todo-app/
├── src/
│   └── main/
│       ├── java/com/tododemo/
│       │   ├── TodoApplication.java          # Main Spring Boot application
│       │   ├── controller/
│       │   │   └── TodoController.java       # Main TODO CRUD operations
│       │   ├── model/
│       │   │   └── Todo.java                 # TODO item model
│       │   └── service/
│       │       └── TodoService.java          # In-memory data management
│       └── resources/
│           ├── templates/
│           │   ├── index.html                # List all TODOs
│           │   ├── create.html               # Create new TODO
│           │   └── edit.html                 # Edit existing TODO
│           └── application.properties        # Application configuration
├── pom.xml                                   # Maven dependencies
└── README.md                                 # This file
```

## API Endpoints

| Method | Path          | Description                |
|--------|---------------|----------------------------|
| GET    | `/`           | List all TODOs             |
| GET    | `/create`     | Show create form           |
| POST   | `/create`     | Create new TODO            |
| GET    | `/edit/{id}`  | Show edit form             |
| POST   | `/edit/{id}`  | Update existing TODO       |
| POST   | `/delete/{id}`| Delete TODO                |
| POST   | `/toggle/{id}`| Toggle completion status   |

## Usage

### Creating a Task
1. Click the "Add New Task" button
2. Enter a title (required)
3. Optionally add a description
4. Check "Mark as completed" if the task is already done
5. Click "Create Task"

### Viewing Tasks
- All tasks are displayed in a responsive table
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

## Tailwind CSS Features Used

- **Utility-First CSS**: Rapid development with utility classes
- **Responsive Design**: Mobile-first breakpoints (sm, md, lg)
- **Colors**: Custom color palette with primary, secondary, success, warning, danger
- **Shadows & Borders**: Card shadows, border styling for depth
- **Hover Effects**: Smooth transitions and hover states
- **Forms**: Styled inputs, textareas, checkboxes with focus states
- **Typography**: Font weights, sizes, and colors
- **Spacing**: Consistent margin and padding
- **Flexbox & Grid**: Modern layout techniques

## Thread Safety

The application uses `ReentrantLock` to ensure thread-safe operations on the in-memory todo list:
- All write operations (add, update, delete, toggle) are protected
- Prevents race conditions during concurrent access
- Ensures ID generation atomicity

## Development

### Hot Reload
The project includes Spring Boot DevTools for automatic restart on code changes:
```bash
mvn spring-boot:run
```

### Building for Production
```bash
mvn clean package
java -jar target/java-todo-app-1.0.0.jar
```

## Future Enhancements

- [ ] Add database persistence (PostgreSQL, MySQL, or H2)
- [ ] Implement user authentication and authorization
- [ ] Add REST API endpoints for mobile apps
- [ ] Implement task categories/tags
- [ ] Add due dates and priority levels
- [ ] Implement search and filter functionality
- [ ] Add unit and integration tests
- [ ] Implement pagination for large task lists

## Comparison with .NET Version

| Feature              | .NET Version      | Java Version           |
|---------------------|-------------------|------------------------|
| Framework           | ASP.NET Core 10   | Spring Boot 3.2       |
| Language            | C# 13             | Java 17               |
| Template Engine     | Razor             | Thymeleaf             |
| CSS Framework       | Bootstrap 5       | Tailwind CSS 3        |
| Icons               | Bootstrap Icons   | Heroicons (SVG)       |
| Thread Safety       | lock statement    | ReentrantLock         |
| Build Tool          | dotnet CLI        | Maven                 |
| Port                | 5220              | 8080                  |

## License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.

## Notes

- Data is stored in-memory and will be lost when the application restarts
- This is a demonstration project intended for learning and showcasing Spring Boot with Tailwind CSS
- For production use, consider implementing persistent storage (e.g., PostgreSQL, MySQL, H2)

## Architecture

The application follows the MVC pattern:
- **Model**: `Todo.java` - represents the data structure
- **View**: Thymeleaf templates with Tailwind CSS
- **Controller**: `TodoController.java` - handles HTTP requests
- **Service**: `TodoService.java` - business logic and data management

Thread safety is ensured through `ReentrantLock` for all mutable operations on the shared todo list.
