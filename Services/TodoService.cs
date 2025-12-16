using TodoDemo.Models;

namespace TodoDemo.Services;

public class TodoService
{
    private static readonly List<Todo> _todos = new();
    private static int _nextId = 1;

    public List<Todo> GetAll()
    {
        return _todos.OrderByDescending(t => t.CreatedAt).ToList();
    }

    public Todo? GetById(int id)
    {
        return _todos.FirstOrDefault(t => t.Id == id);
    }

    public void Add(Todo todo)
    {
        todo.Id = _nextId++;
        todo.CreatedAt = DateTime.Now;
        _todos.Add(todo);
    }

    public void Update(Todo todo)
    {
        var existing = GetById(todo.Id);
        if (existing != null)
        {
            existing.Title = todo.Title;
            existing.Description = todo.Description;
            existing.IsCompleted = todo.IsCompleted;
        }
    }

    public void Delete(int id)
    {
        var todo = GetById(id);
        if (todo != null)
        {
            _todos.Remove(todo);
        }
    }

    public void ToggleComplete(int id)
    {
        var todo = GetById(id);
        if (todo != null)
        {
            todo.IsCompleted = !todo.IsCompleted;
        }
    }
}
