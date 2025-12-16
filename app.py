from flask import Flask, render_template, request, redirect, url_for
from datetime import datetime

app = Flask(__name__)

# In-memory storage for TODO items
todos = []
next_id = 1

@app.route('/')
def index():
    """Render the main TODO page"""
    return render_template('index.html', todos=todos)

@app.route('/add', methods=['POST'])
def add_todo():
    """Add a new TODO item"""
    global next_id
    
    task = request.form.get('task', '').strip()
    if task:
        todo = {
            'id': next_id,
            'task': task,
            'completed': False,
            'created_at': datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        }
        todos.append(todo)
        next_id += 1
    
    return redirect(url_for('index'))

@app.route('/toggle/<int:todo_id>', methods=['POST'])
def toggle_todo(todo_id):
    """Toggle the completed status of a TODO item"""
    for todo in todos:
        if todo['id'] == todo_id:
            todo['completed'] = not todo['completed']
            break
    
    return redirect(url_for('index'))

@app.route('/delete/<int:todo_id>', methods=['POST'])
def delete_todo(todo_id):
    """Delete a TODO item"""
    global todos
    todos = [todo for todo in todos if todo['id'] != todo_id]
    
    return redirect(url_for('index'))

@app.route('/clear', methods=['POST'])
def clear_completed():
    """Clear all completed TODO items"""
    global todos
    todos = [todo for todo in todos if not todo['completed']]
    
    return redirect(url_for('index'))

if __name__ == '__main__':
    import os
    debug_mode = os.environ.get('FLASK_DEBUG', 'False').lower() == 'true'
    app.run(debug=debug_mode, host='0.0.0.0', port=5000)