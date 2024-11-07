// Получаем данные из sessionStorage
const user = JSON.parse(sessionStorage.getItem('user'));

// Проверяем, есть ли данные о пользователе в sessionStorage
if (!user) {
    alert('Please login first!');
    window.location.href = '/login.html'; // Переходим на страницу логина, если данных нет
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
    fetch(`/projects/getById/${projectId}`)
        .then(response => response.json())
        .then(project => {
            document.getElementById('project-info-title').textContent = project.name;
            const projectDetails = document.getElementById('project-details');
            projectDetails.innerHTML = `
        <button id="close-project-details" class="close-button" onclick="closeProjectDetails()">×</button>
        <h4>Description</h4>
        <p>${project.description}</p>
        <h4>Start Date</h4>
        <p>${project.startDate}</p>
        <h4>Status</h4>
        <p>${project.status}</p>
      `;
        })
        .catch(error => {
            console.error('Error loading project details:', error);
            alert('Failed to load project details');
        });
}

// Функция для закрытия окна с деталями проекта
function closeProjectDetails() {
    const projectDetails = document.getElementById('project-details');
    projectDetails.innerHTML = ''; // Очищаем содержимое для "закрытия" окна
    document.getElementById('project-info-title').textContent = 'Select a project or Create Project'; // Сбрасываем заголовок
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
        taskStatus: taskStatus
    };

    // Выводим объект в консоль (для проверки)
    //console.log('Project Created:', newProject);

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
    })
        .then(data => {
            alert('Project added successfully!');
        })

    // Закрываем модальное окно
    closeCreateProjectModal();
    window.location.reload();
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
