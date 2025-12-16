using Microsoft.AspNetCore.Mvc;
using TodoDemo.Models;
using TodoDemo.Services;

namespace TodoDemo.Controllers;

public class TodoController : Controller
{
    private readonly TodoService _todoService;

    public TodoController(TodoService todoService)
    {
        _todoService = todoService;
    }

    // GET: Todo
    public IActionResult Index()
    {
        var todos = _todoService.GetAll();
        return View(todos);
    }

    // GET: Todo/Create
    public IActionResult Create()
    {
        return View();
    }

    // POST: Todo/Create
    [HttpPost]
    [ValidateAntiForgeryToken]
    public IActionResult Create(Todo todo)
    {
        if (ModelState.IsValid)
        {
            _todoService.Add(todo);
            return RedirectToAction(nameof(Index));
        }
        return View(todo);
    }

    // GET: Todo/Edit/5
    public IActionResult Edit(int id)
    {
        var todo = _todoService.GetById(id);
        if (todo == null)
        {
            return NotFound();
        }
        return View(todo);
    }

    // POST: Todo/Edit/5
    [HttpPost]
    [ValidateAntiForgeryToken]
    public IActionResult Edit(int id, Todo todo)
    {
        if (id != todo.Id)
        {
            return NotFound();
        }

        if (ModelState.IsValid)
        {
            _todoService.Update(todo);
            return RedirectToAction(nameof(Index));
        }
        return View(todo);
    }

    // POST: Todo/Delete/5
    [HttpPost]
    [ValidateAntiForgeryToken]
    public IActionResult Delete(int id)
    {
        _todoService.Delete(id);
        return RedirectToAction(nameof(Index));
    }

    // POST: Todo/Toggle/5
    [HttpPost]
    [ValidateAntiForgeryToken]
    public IActionResult Toggle(int id)
    {
        _todoService.ToggleComplete(id);
        return RedirectToAction(nameof(Index));
    }
}
