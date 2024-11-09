// Получаем данные из sessionStorage
const user = JSON.parse(sessionStorage.getItem('user'));

// Проверяем, есть ли данные о пользователе в sessionStorage
if (!user) {
    alert('Please login first!');
    window.location.href = '/index.html'; // Переходим на страницу логина, если данных нет
} else {
    // Отображаем имя и никнейм пользователя
    document.getElementById('user-name').textContent = user.name;
    document.getElementById('user-nickname').textContent = `@${user.nickname}`;

    // Загружаем список проектов, в которых участвует пользователь
    loadProjects(user.id);
}

// Функция для загрузки проектов пользователя
function loadProjects(userId) {
    fetch(`/projects/getUserProjects/${userId}`)  // Пример API для получения проектов
        .then(response => response.json())
        .then(projects => {
            const projectList = document.getElementById('project-list');
            projects.forEach(project => {
                const projectItem = document.createElement('li');
                projectItem.classList.add('project-item');
                projectItem.textContent = project.title;
                projectItem.addEventListener('click', () => showProjectDetails(project.id));
                projectList.appendChild(projectItem);
            });
        })
        .catch(error => {
            console.error('Error loading projects:', error);
            alert('Failed to load projects');
        });
}

// Функция для отображения подробностей проекта
function showProjectDetails(projectId) {
    const taskListContainer = document.getElementById('task-list');
    taskListContainer.innerHTML = '';
    taskListContainer.style.display = 'none';
    fetch(`/projects/getById/${projectId}`)
        .then(response => response.json())
        .then(project => {
            const projectAuthor = project.authorId;
            document.getElementById('project-info-title').textContent = project.name;
            const projectDetails = document.getElementById('project-details');
            projectDetails.innerHTML = `
        <button id="close-project-details" class="close-button" onclick="closeProjectDetails()">×</button>
        <h4>Description</h4>
        <p>${project.description}</p>
        <h4>Start Date</h4>
        <p>${project.startDate}</p>
      `;
            sessionStorage.setItem("currentProjectId", projectId);
            document.getElementById('view-tasks-btn').style.display = 'inline';
            document.getElementById('view-tasks-btn').setAttribute('data-project-id', projectId);
            document.getElementById('create-task-btn').style.display = 'flex';
            document.getElementById('add-status-btn').style.display = 'flex';
            document.getElementById('remove-status-btn').style.display = 'flex';
            if(JSON.parse(sessionStorage.getItem("user")).id === projectAuthor) {
                document.getElementById('add-user-btn').style.display = 'flex';
            }
        })
        .catch(error => {
            console.error('Error loading project details:', error);
            alert('Failed to load project details');
        });
}

// Функция для закрытия окна с деталями проекта
function closeProjectDetails() {
    document.getElementById('create-task-btn').style.display = 'none';
    document.getElementById('add-status-btn').style.display = 'none';
    const projectDetails = document.getElementById('project-details');
    projectDetails.innerHTML = ''; // Очищаем содержимое для "закрытия" окна
    document.getElementById('task-list').style.display = 'none';
    document.getElementById('view-tasks-btn').style.display = 'none';
    document.getElementById('remove-status-btn').style.display = 'none';
    document.getElementById('add-user-btn').style.display = 'none';
    const projectInfoTitle = document.getElementById('project-info-title');
    projectInfoTitle.innerHTML = 'Select a project or<span id="create-project-button" onclick="openCreateProjectModal()">Create Project</span>';
}

// Функция для открытия модального окна создания проекта
function openCreateProjectModal() {
    document.getElementById('create-project-modal').style.display = 'flex';
}

// Функция для закрытия модального окна создания проекта
function closeCreateProjectModal() {
    document.getElementById('create-project-modal').style.display = 'none';
}

// Функция для обработки отправки формы создания проекта
function submitProjectForm(event) {
    event.preventDefault(); // Отменяем стандартное поведение формы

    // Получаем значения введенных данных
    const projectName = document.getElementById('project-name').value;
    const projectDescription = document.getElementById('project-description').value;
    const startDate = document.getElementById('start-date').value;
    const endDate = document.getElementById('end-date').value;
    const taskStatus = document.getElementById('task-status').value;

    // Создаем объект с данными проекта
    const newProject = {
        authorId: JSON.parse(sessionStorage.getItem("user")).id,
        title: projectName,
        description: projectDescription,
        startDate: startDate,
        endDate: endDate,
        taskStatus: taskStatus,
        taskStatuses: [taskStatus]
    };

    // Выводим объект в консоль (для проверки)
    console.log('Project Created:', newProject);

    fetch("/projects/addProject", {
        method: 'POST', // Метод запроса
        headers: {
            'Content-Type': 'application/json', // Указываем, что отправляем JSON
        },
        body: JSON.stringify(newProject), // Преобразуем объект в строку JSON
    }).then(response => {
        if (!response.ok) {
            throw new Error('Failed to create project');
        }
        return response.json(); // Возвращаем ответ в формате JSON
    }).then(data => {
        alert('Project added successfully!');
        window.location.reload();
    })

    // Закрываем модальное окно
    closeCreateProjectModal();
    // Очищаем поля формы
    document.getElementById('create-project-form').reset();
}


// Функции для работы с модальным окном
function openModal() {
    document.getElementById('logout-modal').style.display = 'flex';
}

function closeModal() {
    document.getElementById('logout-modal').style.display = 'none';
}

// Функция для выхода из аккаунта
function logout() {
    sessionStorage.clear();
    window.location.href = '/index.html';
}

// Функция для загрузки задач проекта
function loadProjectTasks() {
    const projectId = JSON.parse(sessionStorage.getItem("currentProjectId"));
    fetch(`/projects/getById/${projectId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to load project details');
            }
            return response.json();
        })
        .then(project => {
            const taskIds = project.tasksIds; // Предполагается, что это массив ID задач
            const taskPromises = taskIds.map(taskId => fetch(`/tasks/getById/${taskId}`).then(res => res.json()));
            // Загружаем все задачи параллельно и отрисовываем их
            Promise.all(taskPromises)
                .then(tasks => {
                    const taskListContainer = document.getElementById('task-list');
                    taskListContainer.style.display = 'block';
                    taskListContainer.innerHTML = ''; // Очищаем список перед добавлением

                    tasks.forEach(task => {
                        const taskItem = document.createElement('div');
                        taskItem.classList.add('task-item');
                        taskItem.textContent = `Task: ${task.title} - Status: ${task.status}`;
                        taskItem.addEventListener('click', () => showTaskDetails(task.id));
                        taskListContainer.appendChild(taskItem);
                    });
                })
                .catch(error => {
                    console.error('Error loading tasks:', error);
                    alert('Failed to load tasks');
                });
        })
        .catch(error => {
            console.error('Error loading project details:', error);
            alert('Failed to load project details');
        });
}

// Функция для отображения информации о задаче
function showTaskDetails(taskId) {
    fetch(`/tasks/getById/${taskId}`)
        .then(response => response.json())
        .then(async task => {
            document.getElementById('task-title').textContent = task.name;
            const taskDetails = document.getElementById('task-details');
            const workerNicknames = await Promise.all(task.workersIds.map(workerId => fetchWorkerNickname(workerId)));

            taskDetails.innerHTML = `
        <h4>Description</h4>
        <p>${task.description}</p>
        <h4>Assigned Workers</h4>
        <ul>
          ${workerNicknames.map(nickname => `<li>${nickname}</li>`).join('')}
        </ul>
        <button id="join-task-btn" style="display: none;" onclick="joinTask(${task.id})">Join Task</button>
      `;
            if(!task.workersIds.includes(JSON.parse(sessionStorage.getItem("user")).id)){
                document.getElementById('join-task-btn').style.display = 'flex';
                document.getElementById('set-status-btn').style.display = 'none';
                document.getElementById('task-status-select').style.display = 'none';
            }
            else{
                document.getElementById('set-status-btn').style.display = 'flex';
                document.getElementById('task-status-select').style.display = 'flex';
            }
            loadProjectStatuses(JSON.parse(sessionStorage.getItem("currentProjectId")), task.status, task.id);

            document.getElementById('task-info-modal').style.display = 'flex';
        })
        .catch(error => {
            console.error('Error loading task details:', error);
            alert('Failed to load task details');
        });
}

// Функция для закрытия модального окна
function closeTaskInfoModal() {
    document.getElementById('join-task-btn').style.display = 'none';
    document.getElementById('task-info-modal').style.display = 'none';
}

// Функция для получения никнейма пользователя по workerId
function fetchWorkerNickname(workerId) {
    return fetch(`/users/getById/${workerId}`)
        .then(response => response.json())
        .then(user => user.nickname)
        .catch(error => {
            console.error(`Error loading nickname for worker ${workerId}:`, error);
            return 'Unknown'; // В случае ошибки добавляем 'Unknown'
        });
}

// Функция для загрузки и отображения статусов проекта в выпадающем списке
function loadProjectStatuses(projectId, currentStatus, taskId) {
    fetch(`/projects/getById/${projectId}`)
        .then(response => response.json())
        .then(project => {
            const statusSelect = document.getElementById('task-status-select');
            statusSelect.innerHTML = ''; // Очищаем список статусов

            const defaultOption = document.createElement('option');
            defaultOption.value = project.defaultStatus;
            defaultOption.textContent = defaultOption.value;
            statusSelect.appendChild(defaultOption);
            project.taskStatuses.forEach(status => {
                const option = document.createElement('option');
                option.value = status;
                option.textContent = status;
                if (status === currentStatus) option.selected = true;
                statusSelect.appendChild(option);
            });

            // Добавляем обработчик изменения статуса
            statusSelect.addEventListener('change', () => updateTaskStatus(taskId, statusSelect.value));
        })
        .catch(error => {
            console.error('Error loading project statuses:', error);
            alert('Failed to load statuses');
        });
}

// Функция для обновления статуса задачи
function updateTaskStatus(taskId, newStatus) {
    // Сначала получаем текущие данные задачи
    fetch(`/tasks/getById/${taskId}`)
        .then(response => {
            if (!response.ok) throw new Error('Failed to fetch task details');
            return response.json();
        })
        .then(task => {
            // Обновляем поле status на newStatus
            const updatedTask = {...task, status: newStatus};

            // Отправляем обновленные данные задачи на сервер
            return fetch(`/tasks/updateTask/${taskId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedTask)
            });
        })
        .then(response => {
            if (!response.ok) throw new Error('Failed to update task');
            alert('Task status updated successfully!');
            loadProjectTasks();
        })
        .catch(error => {
            console.error('Error updating task status:', error);
            alert('Failed to update task status');
        });
}

// Функция для открытия модального окна создания задачи
function openCreateTaskModal() {
    const createTaskModal = document.getElementById('create-task-modal');
    createTaskModal.style.display = 'flex';
}

// Функция для закрытия модального окна создания задачи
function closeCreateTaskModal() {
    const createTaskModal = document.getElementById('create-task-modal');
    createTaskModal.style.display = 'none';
}

// Обработчик для формы создания новой задачи
document.getElementById('create-task-form').addEventListener('submit', function (event) {
    event.preventDefault(); // Отменяем стандартное поведение формы

    // Получаем данные из формы
    const taskName = document.getElementById('task-name').value;
    const taskDescription = document.getElementById('task-description').value;

    // Получаем ID проекта, с которым связан пользователь
    const projectId = JSON.parse(sessionStorage.getItem("currentProjectId")); // Предполагается, что ID проекта передается в элемент

    // Создаем задачу
    createTask(projectId, taskName, taskDescription);
});

// Функция для создания новой задачи
function createTask(projectId, taskName, taskDescription) {
    // Получаем стандартный статус проекта через запрос
    fetch(`/projects/getById/${projectId}`)
        .then(response => {
            if (!response.ok) throw new Error('Failed to fetch project details');
            return response.json();
        })
        .then(project => {
            // Извлекаем defaultStatus из проекта
            const defaultStatus = project.defaultStatus;

            // Создаем задачу с этим статусом
            const newTask = {
                title: taskName,
                authorId: JSON.parse(sessionStorage.getItem("user")).id,
                dedicatedTime: null,
                priority: "default",
                description: taskDescription,
                projectId: projectId,
                workersIds: [JSON.parse(sessionStorage.getItem("user")).id],  // Здесь можно добавить логику для выбора работников
                status: defaultStatus,  // Устанавливаем статус как defaultStatus проекта
                changeLogsIds: []
            };

            // Отправляем новую задачу на сервер
            return fetch('/tasks/addTask', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newTask)
            });
        })
        .then(async response => {
            const taskId = (await response.json()).id;
            if (!response.ok) throw new Error('Failed to create task');
            // После создания задачи, обновляем проект
            // Получаем проект и добавляем ID новой задачи в tasksIds
            return fetch(`/projects/getById/${projectId}`)
                .then(response => {
                    if (!response.ok) throw new Error('Failed to fetch project details for update');
                    return response.json();
                })
                .then(project => {
                    // Добавляем новый taskId в список tasksIds проекта
                    project.tasksIds.push(taskId);  // Добавляем ID новой задачи в tasksIds

                    // Обновляем проект, отправляя его на сервер
                    return fetch(`/projects/updateProject/${projectId}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(project)
                    });
                });
        })
        .then(() => {
            alert('Task created successfully!');
            closeCreateTaskModal();  // Закрываем окно создания задачи
        })
        .catch(error => {
            console.error('Error creating task:', error);
            alert('Failed to create task');
        });
}

// Открыть модальное окно для добавления статуса
function openAddStatusModal() {
    document.getElementById('add-status-modal').style.display = 'block';
}

// Закрыть модальное окно для добавления статуса
function closeAddStatusModal() {
    document.getElementById('add-status-modal').style.display = 'none';
    document.getElementById('new-status-input').value = '';  // Очищаем поле ввода
}

// Добавить новый статус к проекту
function addStatus() {
    const newStatus = document.getElementById('new-status-input').value.trim();

    if (!newStatus) {
        alert('Please enter a status name');
        return;
    }

    // Получаем ID текущего проекта
    const projectId = sessionStorage.getItem("currentProjectId");

    // Получаем проект, добавляем новый статус и обновляем его на сервере
    fetch(`/projects/getById/${projectId}`)
        .then(response => {
            if (!response.ok) throw new Error('Failed to fetch project details');
            return response.json();
        })
        .then(project => {
            // Добавляем новый статус к списку taskStatuses
            project.taskStatuses.push(newStatus);

            // Отправляем обновленный проект на сервер
            return fetch(`/projects/updateProject/${projectId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(project)
            });
        })
        .then(response => {
            if (!response.ok) throw new Error('Failed to update project');
            alert('Status added successfully!');
            closeAddStatusModal();  // Закрываем модальное окно
        })
        .catch(error => {
            console.error('Error adding status:', error);
            alert('Failed to add status');
        });
}
// Открыть модальное окно для удаления статуса
function openRemoveStatusModal() {
    const projectId = sessionStorage.getItem("currentProjectId");

    // Получаем данные проекта и загружаем статусы в выпадающий список
    fetch(`/projects/getById/${projectId}`)
        .then(response => {
            if (!response.ok) throw new Error('Failed to fetch project details');
            return response.json();
        })
        .then(project => {
            const statusSelect = document.getElementById('status-select');
            statusSelect.innerHTML = ''; // Очищаем список

            // Добавляем каждый статус в выпадающий список
            project.taskStatuses.forEach(status => {
                const option = document.createElement('option');
                option.value = status;
                option.textContent = status;
                statusSelect.appendChild(option);
            });

            // Показываем модальное окно
            document.getElementById('remove-status-modal').style.display = 'block';
        })
        .catch(error => {
            console.error('Error loading project statuses:', error);
            alert('Failed to load statuses');
        });
}

// Закрыть модальное окно для удаления статуса
function closeRemoveStatusModal() {
    document.getElementById('remove-status-modal').style.display = 'none';
}

// Удалить выбранный статус из проекта
function removeStatus() {
    const selectedStatus = document.getElementById('status-select').value;
    const projectId = sessionStorage.getItem("currentProjectId");

    if (!selectedStatus) {
        alert('Please select a status to remove');
        return;
    }

    // Получаем данные проекта, удаляем выбранный статус и обновляем его на сервере
    fetch(`/projects/getById/${projectId}`)
        .then(response => {
            if (!response.ok) throw new Error('Failed to fetch project details');
            return response.json();
        })
        .then(project => {
            // Удаляем выбранный статус из массива taskStatuses
            project.taskStatuses = project.taskStatuses.filter(status => status !== selectedStatus);

            // Отправляем обновленный проект на сервер
            return fetch(`/projects/updateProject/${projectId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(project)
            });
        })
        .then(response => {
            if (!response.ok) throw new Error('Failed to update project');
            alert('Status removed successfully!');
            closeRemoveStatusModal();  // Закрываем модальное окно
        })
        .catch(error => {
            console.error('Error removing status:', error);
            alert('Failed to remove status');
        });
}
// Открыть модальное окно для добавления пользователя
function openAddUserModal() {
    document.getElementById('add-user-modal').style.display = 'block';
}

// Закрыть модальное окно для добавления пользователя
function closeAddUserModal() {
    document.getElementById('add-user-modal').style.display = 'none';
}

// Добавить пользователя в проект по никнейму
function addUserToProject() {
    const username = document.getElementById('username-input').value.trim();
    if (!username) {
        alert('Please enter a user\'s nickname');
        return;
    }

    const projectId = sessionStorage.getItem("currentProjectId");

    // Поиск пользователя по никнейму
    fetch(`/users/getByNickname/${username}`)
        .then(response => {
            if (!response.ok) throw new Error('User not found');
            return response.json();
        })
        .then(user => {
            const userId = user.id; // Получаем ID найденного пользователя

            // Получаем проект и добавляем пользователя в workersIds
            return fetch(`/projects/getById/${projectId}`)
                .then(response => {
                    if (!response.ok) throw new Error('Failed to fetch project details');
                    return response.json();
                })
                .then(project => {
                    // Добавляем userId в массив workersIds, если его там еще нет
                    if (!project.workersIds.includes(userId)) {
                        project.workersIds.push(userId);

                        // Отправляем обновленный проект на сервер
                        return fetch(`/projects/updateProject/${projectId}`, {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(project)
                        });
                    } else {
                        alert('User is already a member of this project');
                        return Promise.reject('User already added');
                    }
                });
        })
        .then(response => {
            if (!response.ok) throw new Error('Failed to update project');
            alert('User added successfully!');
            closeAddUserModal();  // Закрываем модальное окно
        })
        .catch(error => {
            console.error('Error adding user to project:', error);
            if (error !== 'User already added') alert('Failed to add user to project');
        });
}

// Открыть модальное окно для поиска пользователей
function openSearchUsersModal() {
    document.getElementById('search-users-modal').style.display = 'block';
}

// Закрыть модальное окно для поиска пользователей
function closeSearchUsersModal() {
    document.getElementById('search-users-modal').style.display = 'none';
    document.getElementById('search-username-input').value = ''; // Очистить поле ввода
    document.getElementById('user-details').style.display = 'none'; // Скрыть детали пользователя
}

// Функция для поиска пользователя по никнейму
function searchUser() {
    const username = document.getElementById('search-username-input').value.trim();
    if (!username) {
        alert('Please enter a username');
        return;
    }

    fetch(`/users/getByNickname/${username}`)
        .then(response => {
            if (!response.ok) throw new Error('User not found');
            return response.json();
        })
        .then(user => {
            // Отображаем информацию о пользователе
            document.getElementById('user-details-name').textContent = user.name;
            document.getElementById('user-details-nickname').textContent = user.nickname;

            document.getElementById('user-details').style.display = 'block'; // Показать блок с информацией о пользователе
        })
        .catch(error => {
            console.error('Error finding user:', error);
            alert('User not found');
            document.getElementById('user-details').style.display = 'none'; // Скрыть блок с информацией о пользователе
        });
}

function joinTask(taskId) {
    const userId = JSON.parse(sessionStorage.getItem('user')).id;

    // Сначала получаем данные задачи
    fetch(`/tasks/getById/${taskId}`)
        .then(response => response.json())
        .then(task => {
            // Добавляем текущего пользователя в список работников
            task.workersIds.push(userId);

            // Отправляем обновленные данные задачи на сервер
            return fetch(`/tasks/updateTask/${taskId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(task)
            });
        })
        .then(response => {
            if (!response.ok) throw new Error('Failed to join task');
            alert('Successfully joined the task');
            document.getElementById('join-task-btn').style.display = 'none'; // Скрываем кнопку
            showTaskDetails(taskId); // Обновляем информацию о задаче
        })
        .catch(error => {
            console.error('Error joining task:', error);
            alert('Failed to join task');
        });
}
