# TodoDemo - 架構與流程圖 (Architecture & Flow Diagrams)

## 系統架構圖 (System Architecture)

```mermaid
graph TB
    subgraph "Presentation Layer"
        Browser[Web Browser]
    end
    
    subgraph "ASP.NET Core 10 MVC"
        subgraph "Controllers"
            TC[TodoController]
        end
        
        subgraph "Views"
            IV[Index.cshtml]
            CV[Create.cshtml]
            EV[Edit.cshtml]
        end
        
        subgraph "Services"
            TS[TodoService<br/>Singleton]
        end
        
        subgraph "Models"
            TM[Todo Model]
        end
    end
    
    subgraph "Data Storage"
        Memory[(In-Memory<br/>Static List)]
    end
    
    Browser -->|HTTP Request| TC
    TC -->|Read/Write| TS
    TC -->|Return View| IV
    TC -->|Return View| CV
    TC -->|Return View| EV
    TS -->|CRUD Operations| Memory
    TS -->|Use| TM
    
    style Browser fill:#e1f5ff
    style TC fill:#fff4e1
    style TS fill:#e8f5e9
    style Memory fill:#fce4ec
```

## 應用程式元件圖 (Application Component Diagram)

```mermaid
graph LR
    subgraph "Todo Model"
        TM[Todo<br/>+Id: int<br/>+Title: string<br/>+Description: string<br/>+IsCompleted: bool<br/>+CreatedAt: DateTime]
    end
    
    subgraph "TodoService"
        TS[TodoService<br/>-_todos: List&lt;Todo&gt;<br/>-_nextId: int<br/>-_lock: object<br/>+GetAll&#40;&#41;<br/>+GetById&#40;int&#41;<br/>+Add&#40;Todo&#41;<br/>+Update&#40;Todo&#41;<br/>+Delete&#40;int&#41;<br/>+ToggleComplete&#40;int&#41;]
    end
    
    subgraph "TodoController"
        TC[TodoController<br/>-_todoService<br/>+Index&#40;&#41;<br/>+Create&#40;&#41;<br/>+Create&#40;Todo&#41;<br/>+Edit&#40;int&#41;<br/>+Edit&#40;int, Todo&#41;<br/>+Delete&#40;int&#41;<br/>+Toggle&#40;int&#41;]
    end
    
    TC -->|Uses| TS
    TS -->|Manages| TM
    
    style TM fill:#e3f2fd
    style TS fill:#f3e5f5
    style TC fill:#fff9c4
```

## CRUD 操作流程圖 (CRUD Operation Flow)

### 1. 查看所有待辦事項 (View All Todos - Index)

```mermaid
sequenceDiagram
    actor User
    participant Browser
    participant Controller as TodoController
    participant Service as TodoService
    participant Memory as In-Memory Storage
    
    User->>Browser: Navigate to /
    Browser->>Controller: GET /Todo/Index
    Controller->>Service: GetAll()
    Service->>Memory: Read all todos
    Memory-->>Service: List<Todo>
    Service-->>Controller: Sorted todos (by CreatedAt DESC)
    Controller-->>Browser: Index.cshtml with todos
    Browser-->>User: Display TODO list
```

### 2. 新增待辦事項 (Create Todo)

```mermaid
sequenceDiagram
    actor User
    participant Browser
    participant Controller as TodoController
    participant Service as TodoService
    participant Memory as In-Memory Storage
    
    User->>Browser: Click "Add New Task"
    Browser->>Controller: GET /Todo/Create
    Controller-->>Browser: Create.cshtml (form)
    Browser-->>User: Display form
    
    User->>Browser: Fill form & submit
    Browser->>Controller: POST /Todo/Create {Todo}
    Controller->>Controller: Validate ModelState
    alt Valid
        Controller->>Service: Add(todo)
        Service->>Service: lock(_lock)
        Service->>Service: Assign ID (_nextId++)
        Service->>Service: Set CreatedAt
        Service->>Memory: Add to _todos list
        Service->>Service: unlock
        Service-->>Controller: Success
        Controller-->>Browser: Redirect to Index
        Browser-->>User: Show updated list
    else Invalid
        Controller-->>Browser: Create.cshtml with errors
        Browser-->>User: Show validation errors
    end
```

### 3. 編輯待辦事項 (Edit Todo)

```mermaid
sequenceDiagram
    actor User
    participant Browser
    participant Controller as TodoController
    participant Service as TodoService
    participant Memory as In-Memory Storage
    
    User->>Browser: Click Edit button
    Browser->>Controller: GET /Todo/Edit/{id}
    Controller->>Service: GetById(id)
    Service->>Memory: Find todo by ID
    Memory-->>Service: Todo or null
    Service-->>Controller: Todo
    
    alt Todo found
        Controller-->>Browser: Edit.cshtml with todo
        Browser-->>User: Display edit form
        
        User->>Browser: Update & submit
        Browser->>Controller: POST /Todo/Edit/{id} {Todo}
        Controller->>Controller: Validate ModelState
        alt Valid
            Controller->>Service: Update(todo)
            Service->>Service: lock(_lock)
            Service->>Service: Find existing todo
            Service->>Service: Update properties
            Service->>Service: unlock
            Service-->>Controller: Success
            Controller-->>Browser: Redirect to Index
            Browser-->>User: Show updated list
        else Invalid
            Controller-->>Browser: Edit.cshtml with errors
            Browser-->>User: Show validation errors
        end
    else Todo not found
        Controller-->>Browser: 404 Not Found
        Browser-->>User: Error page
    end
```

### 4. 刪除待辦事項 (Delete Todo)

```mermaid
sequenceDiagram
    actor User
    participant Browser
    participant Controller as TodoController
    participant Service as TodoService
    participant Memory as In-Memory Storage
    
    User->>Browser: Click Delete button
    Browser->>User: Show confirmation dialog
    User->>Browser: Confirm deletion
    Browser->>Controller: POST /Todo/Delete/{id}
    Controller->>Service: Delete(id)
    Service->>Service: lock(_lock)
    Service->>Service: Find todo by ID
    Service->>Memory: Remove from _todos list
    Service->>Service: unlock
    Service-->>Controller: Success
    Controller-->>Browser: Redirect to Index
    Browser-->>User: Show updated list
```

### 5. 切換完成狀態 (Toggle Completion Status)

```mermaid
sequenceDiagram
    actor User
    participant Browser
    participant Controller as TodoController
    participant Service as TodoService
    participant Memory as In-Memory Storage
    
    User->>Browser: Click status toggle button
    Browser->>Controller: POST /Todo/Toggle/{id}
    Controller->>Service: ToggleComplete(id)
    Service->>Service: lock(_lock)
    Service->>Service: Find todo by ID
    Service->>Service: Toggle IsCompleted
    Service->>Service: unlock
    Service-->>Controller: Success
    Controller-->>Browser: Redirect to Index
    Browser-->>User: Show updated list with new status
```

## 資料流程圖 (Data Flow Diagram)

```mermaid
flowchart TD
    Start([使用者訪問網站]) --> Index[Index Action]
    Index --> GetAll[GetAll from Service]
    GetAll --> Display[顯示 TODO 列表]
    
    Display --> Choice{使用者操作?}
    
    Choice -->|新增| CreateGet[Create GET]
    CreateGet --> CreateForm[顯示建立表單]
    CreateForm --> CreatePost[Create POST]
    CreatePost --> Validate1{驗證?}
    Validate1 -->|有效| AddService[Service.Add]
    Validate1 -->|無效| CreateForm
    AddService --> Index
    
    Choice -->|編輯| EditGet[Edit GET]
    EditGet --> EditForm[顯示編輯表單]
    EditForm --> EditPost[Edit POST]
    EditPost --> Validate2{驗證?}
    Validate2 -->|有效| UpdateService[Service.Update]
    Validate2 -->|無效| EditForm
    UpdateService --> Index
    
    Choice -->|刪除| DeletePost[Delete POST]
    DeletePost --> DeleteService[Service.Delete]
    DeleteService --> Index
    
    Choice -->|切換狀態| TogglePost[Toggle POST]
    TogglePost --> ToggleService[Service.ToggleComplete]
    ToggleService --> Index
    
    Choice -->|離開| End([結束])
    
    style Start fill:#c8e6c9
    style End fill:#ffcdd2
    style Display fill:#fff9c4
    style CreateForm fill:#e1bee7
    style EditForm fill:#e1bee7
```

## 執行緒安全機制 (Thread Safety Mechanism)

```mermaid
graph TD
    subgraph "TodoService - Thread Safe Operations"
        Lock[_lock object]
        
        subgraph "Add Method"
            A1[lock&#40;_lock&#41;] --> A2[Assign _nextId++]
            A2 --> A3[Set CreatedAt]
            A3 --> A4[Add to _todos]
            A4 --> A5[unlock]
        end
        
        subgraph "Update Method"
            U1[lock&#40;_lock&#41;] --> U2[Find existing]
            U2 --> U3[Update properties]
            U3 --> U4[unlock]
        end
        
        subgraph "Delete Method"
            D1[lock&#40;_lock&#41;] --> D2[Find todo]
            D2 --> D3[Remove from _todos]
            D3 --> D4[unlock]
        end
        
        subgraph "Toggle Method"
            T1[lock&#40;_lock&#41;] --> T2[Find todo]
            T2 --> T3[Toggle IsCompleted]
            T3 --> T4[unlock]
        end
        
        Lock -.-> A1
        Lock -.-> U1
        Lock -.-> D1
        Lock -.-> T1
    end
    
    style Lock fill:#ffeb3b
    style A1 fill:#c8e6c9
    style U1 fill:#c8e6c9
    style D1 fill:#c8e6c9
    style T1 fill:#c8e6c9
```

## 技術堆疊 (Technology Stack)

```mermaid
graph TB
    subgraph "Frontend"
        HTML[HTML5]
        CSS[CSS3 / Bootstrap 5]
        JS[JavaScript / jQuery]
        Icons[Bootstrap Icons]
    end
    
    subgraph "Backend"
        NET[.NET 10]
        MVC[ASP.NET Core MVC]
        CS[C# 13]
    end
    
    subgraph "Patterns"
        DI[Dependency Injection]
        Singleton[Singleton Pattern]
        MVC_Pattern[MVC Pattern]
    end
    
    subgraph "Data"
        Memory[In-Memory Storage]
        Static[Static Fields]
        Lock[Thread Lock]
    end
    
    HTML --> MVC
    CSS --> MVC
    JS --> MVC
    Icons --> HTML
    
    NET --> MVC
    CS --> MVC
    
    MVC --> DI
    MVC --> MVC_Pattern
    DI --> Singleton
    
    Singleton --> Memory
    Memory --> Static
    Memory --> Lock
    
    style NET fill:#512bd4
    style MVC fill:#68217a
    style Bootstrap fill:#7952b3
    style Memory fill:#ff6b6b
```

## 部署流程 (Deployment Flow)

```mermaid
flowchart LR
    Dev[開發環境] -->|dotnet build| Build[建置]
    Build -->|dotnet run| Local[本地執行]
    Local -->|測試| Test{測試通過?}
    Test -->|是| Prod[生產環境]
    Test -->|否| Dev
    
    Prod -->|http://localhost:5220| Running[應用程式運行中]
    
    style Dev fill:#e3f2fd
    style Build fill:#fff3e0
    style Local fill:#f3e5f5
    style Prod fill:#c8e6c9
    style Running fill:#b2dfdb
```

## 注意事項 (Important Notes)

### 資料持久性 (Data Persistence)
- ⚠️ 目前使用 **In-Memory** 儲存
- 應用程式重啟後資料會遺失
- 生產環境建議使用：
  - SQL Server
  - PostgreSQL
  - SQLite
  - Entity Framework Core

### 執行緒安全 (Thread Safety)
- ✅ 所有寫入操作使用 `lock(_lock)` 保護
- ✅ 防止並發存取衝突
- ✅ 確保 ID 生成的原子性

### 擴展性建議 (Scalability Recommendations)
1. 實作資料庫持久化層
2. 加入身份驗證與授權
3. 實作 RESTful API
4. 加入單元測試與整合測試
5. 實作日誌記錄機制
