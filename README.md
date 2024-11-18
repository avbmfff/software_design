# software_design
---
# Лабораторная работа 1
Выполнили: Борздун Анна, Слепцова Элла, Мироненко Егор, Поддубная Полина, Демина Валерия, группа М34051
# Урок: Domain Driven Design (DDD) basics - Практика Task Manager, сбор и прояснение требований
## Задание к уроку
### Вопросы заказчику
Ваша первая задача - сформулировать список вопросов для прояснения требований к системе. Задайте вопросы, которые помогут вам спроектировать и разработать минимальное готовое решение, удовлетворяющее требованиям заказчика.
Задавая вопросы, держите в голове, что ваша конечная цель разработать минимальную рабочую реализацию, которая продемонстрирует заказчику то, чего он хотел, но при этом затратить минимум ресурсов.
Вопросы добавьте на страницу notion. По мере ответов от "заказчика", вы должны исправлять функциональные требования, речь о которых пойдет ниже.
### Функциональные требования
Предлагаю оформить функциональные требования к разрабатываемой системе. Вы можете оформить их в виде списка, где каждый пункт будет начинаться с фразы "Пользователь должен иметь возможность ...".
 Пример того, как должны быть оформлены требования: 
Пользователь должен иметь возможность cоздавать проект, передав название проекта.
Пользователь должен иметь возможность создавать задачи в проекте, участником которого является, передавая имя задачи.
Не пытайтесь насытить вашу системы новой функциональностью просто потому, что вам это кажется правильным. Помните, что вы  не должны додумывать  за заказчика, как должны выглядеть/вести себя его система. Руководствуйтесь исключительно тем, что предоставил вам заказчик в виде описания системы. Если вам кажется, что в системе не хватает функциональности, которая будет критична для демонстрации - задавайте вопрос,  аргументируйте  свою точку зрения. Дополнять требования нужно по мере получения четких ответов от заказчика системы.
 Вопросы , которые вы задали выше должны быть  нацелены на прояснение  этих требований. Ответы на эти вопросы могут приводить к изменению требований.
## Вопросы
 Наши вопросы и функциональные требования выделены $${\color{purple} \textbf{"color:purple">фиолетовым}}$$ . 
Мы уже начали заполнять список вопросов. Продолжите его… 
1. $${\color{purple} \textbf{Как вы сейчас планируете свои задачи для проектов?}}$$ 
* $${\color{purple} \textbf{Как Вы управляете проекты сейчас? }}$$ 
* $${\color{purple} \textbf{Как передаете задачи исполнителю сейчас?}}$$ 
* $${\color{purple} \textbf{Как вы формулируете задачи сейчас?}}$$ 
2. $${\color{purple} \textbf{Какие сейчас тасктрекеры вы используете? Какие преимущества и недостатки вы в них видите?}}$$ 
* $${\color{purple} \textbf{Есть jira, trello - пользовались ли Вы ими?}}$$ 
    * $${\color{purple} \textbf{Если да, что вы бы хотели в них улучшить?}}$$ 
    * $${\color{purple} \textbf{Если нет, то почему они не подошли?}}$$ 
3. Какие данные пользователя используются для регистрации?
* Никнейм, пароль
4. $${\color{purple} \textbf{Какие ограничения на никнейм и пароль должны быть в системе?}}$$ 
5. Если пользователь с таким никнеймом уже существует, как должна вести себя система?
* Отклонить запрос о регистрации, выдать информативное сообщение (формулировку сообщения можете выбрать по своему усмотрению)
6. Тогда, вероятно, стоит добавить функцию проверки, существует ли пользователь с таким никнеймом?
* Да, стоит
7. $${\color{purple} \textbf{Есть ли у Вас требования к паролю пользователя? (например - Задается ли пароль пользователем или он выдается администратором/самой системой? Какой должна быть валидация у пароля? Если задается пользователем, то может ли пользователь сбросить свой пароль?)}}$$ 
8. Как происходит процесс входа в систему (аутентификации)
* На данный момент мы не хотим реализовывать никакие механизмы аутентификации и авторизации. Необходимо будет реализовать только добавление пользователей в систему. - а-ля не надо подтверждение по почте
9. Что предполагается показывать пользователю (какую страницу) после успешной аутентификации? 
* Хотим показывать ему страницу со списком проектов, участником которых он является и кнопку "создать проект". - грубо говоря, таблица + в шапке создать
10. Нужна ли навигация по системе? Панель с доступами ко всем проектам, ко всем задачам?
11. $${\color{purple} \textbf{Нужно ли отображать в системе пользователю назначенные на него задачи, созданные им задачи?}}$$ 
12. $${\color{purple} \textbf{Нужен ли в системе профиль пользователя? Если да то, что должно быть в профиле пользователя? Какие данные о нем?}}$$ 
13. $${\color{purple} \textbf{Может ли пользователь редактировать свой профиль? ожно удалить можно нет}}$$ 
14. $${\color{purple} \textbf{Может ли пользователь удалить свой аккаунт из системы или это должен сделать только администратор?}}$$ 
15. $${\color{purple} \textbf{Какие атрибуты есть у проекта, кроме названия? Например, дата начала и окончания, описание, статусы?}}$$ 
16. Чтобы добавить в проект нового участника нужна возможность искать пользователя. Вероятно, проще всего искать будет по его имени и никнейму?
* Да, стоит добавить в пользователя атрибут “имя” и указывать его при регистрации. В это поле мы помещаем ФИО пользователя.
* Да, стоит реализовать функцию поиска участника. Давайте искать по полному совпадению или  вхождению искомой строки в никнейм или имя пользователя 
17. Могут ли пользователи видеть проекты, участниками которых они не являются
* Да, для реализации минимального продукта мы не делаем никаких механизмов разграничения доступа (авторизации)
18. $${\color{purple} \textbf{Может пользователь редактировать/удалить проект? Если да, все ли пользователи могут это делать? (Если да, то может ли это сделать любой пользователь или, например, только создатель проекта?) Есть ли ограничения на редактирование? (Допустим одним пользователям можно удалять весь проект, а другим можно только добавлять свои изменения) Нужно ли отслеживать редактирование (запомнить пользователя-редактора и дату редактирования)? (Это может быть полезно в случае ошибок. Например, пользователь удалил важную часть работы. По пользователю и дате можно восстановить, кто виноват в этом)}}$$ 
19. $${\color{purple} \textbf{Какие атрибуты есть у задачи, кроме названия, статуса и исполнителей? Например, сроки выполнения задачи, приоритет, градация исполнителей (исполнитель/проверяющий/ответственный), описание, тип задачи, историю изменения задачи, логирование времени (инструмент по фиксированию промежутков времени потраченные на определенные задачи), оценка задачи (стори поинты, время). Какие статусы должны быть у задачи (например: нужно сделать, в работе, на проверке, готово, приостановлено, переоткрыта, тестируется, интегрируется)? Какие типы задач должны быть в системе (например: задача, история, эпик, подзадача, баг)? }}$$ 
20. $${\color{purple} \textbf{Нужны ли уведомления в вашей системе? Какие типы уведомлений может получать пользователь? Например, уведомления о проектах, задачах. От каких сущностей пользователь получает уведомления? Как уведомлять пользователя о новой задаче/проекте, об изменениях в статусе или описании задачи? Должны ли быть уведомления в системе или они посылаются через сторонние сервисы (почта/номер телефона)? Может ли пользователь подписываться на уведомления от задач?}}$$ 
21. $${\color{purple} \textbf{Может пользователь редактировать/удалить задачи? Если да, то может ли это сделать любой пользователь или, например, только исполнитель или создатель задачи? Если ли ограничения на редактирование? Нужно ли отслеживать редактирование (запомнить пользователя-редактора и дату редактирования)?}}$$ 

## Функциональные требования
${\color{red} \textbf{Функциональные требования составлены по известным данным от заказчика}}$  
Наши вопросы и функциональные требования выделены $${\color{purple} \textbf{фиолетовым}}$$  
Наши вопросы и функциональные требования для 4 ЛР выделены $${\color{green} \textbf{зеленым}}$$  
Дополните список. Пользователь должен иметь возможность…

<a id="фт1">1.</a> “Гость” имеет возможность узнать зарегистрирован ли пользователь с подобным ником, направив запрос в систему и получив ответ в формате “true/false”.

<a id="фт2">2.</a> Пройти регистрацию в системе, сменив статус “гостя” на “пользователя” системы, передав никнейм, имя и пароль. 
${\color{purple} \textbf{Исходы события:}}$ 
* $${\color{purple} \textbf{Пользователь имеет возможность ввести желаемый логин. }}$$ 
    * $${\color{purple} \textbf{Если логин существует в системе высвечивается сообщение “Логин уже существует в системе, введите другой”. }}$$ 
    * $${\color{purple} \textbf{Если логин не соответствует требованиям системы, высвечивается сообщение: “Минимальная длина логина - 5 символов, логин может содержать только буквы латинского алфавита и цифры”}}$$ 
* $${\color{purple} \textbf{Пользователь имеет возможность ввести желаемый пароль. }}$$ 
    * $${\color{purple} \textbf{Если пароль не соответствует требованиям системы, высвечивается сообщение: “Минимальная длина пароля - 8 символов”}}$$ 
* $${\color{purple} \textbf{Пользователь имеет возможность узнать о результате регистрации. При неуспешной попытке зарегистрироваться (введен некорректный логин или пароль), пользователь получает сообщение от системы “Регистрация прошла неуспешна”. При успешной попытке регистрации происходит redirect пользователя на главную страницу системы.}}$$ 

<a id="фт3">3.</a> $${\color{purple} \textbf{Пользователь, имеющий аккаунт в системе, имеет возможность авторизации. Исходы события:}}$$ 
* Операция входа $${\color{purple} \textbf{в учетную запись}}$$ в систему отклоняется с информативным сообщением, если пользователь с таким ником уже существует. $${\color{purple} \textbf{(пример - “Пользователь с таким ником уже существует”)}}$$ 
* $${\color{purple} \textbf{Операция входа в учетную запись отклоняется с информативным сообщением, если пользователь ввел неверный пароль  (пример - “Вы ввели неправильный пароль”)}}$$ 
* $${\color{purple} \textbf{Операция входа в учетную запись проходит успешно с информативным сообщением (пример - “Вы вошли в учетную запись”)}}$$ 

<a id="фт4">4.</a> $${\color{purple} \textbf{Авторизованный пользователь может увидеть все проекты, в которых он участвует. Исходы события:}}$$ 
* $${\color{purple} \textbf{Если пользователь не участвует ни в одном проекте, то высвечивается сообщение с предложением создать проект. (Пример сообщения, “Создать проект”)}}$$ 
* $${\color{purple} \textbf{Если пользователь участвует в 1 и более проектов, то он имеет возможность отсортировать проекты по:}}$$ 
    * $${\color{purple} \textbf{дате изменения проекта}}$$ 
    * $${\color{purple} \textbf{недавно изменённым проектам}}$$ 
    * $${\color{purple} \textbf{проектам, которыми владеет пользователь сначала}}$$ 

<a id="фт5">5.</a> $${\color{purple} \textbf{Авторизованный пользователь может увидеть проекты, где пользователь указан в качестве участника}}$$ 
* $${\color{purple} \textbf{Если проектов нет, то высвечивается информационное сообщение с предложением создать проект}}$$ 
* $${\color{purple} \textbf{Если проекты есть, то показывается список проектов}}$$ 

<a id="фт6">6.</a> $${\color{purple} \textbf{Создать проект, передав название проекта}}$$ 
* $${\color{purple} \textbf{Операция отклоняется с информативным сообщением}}$$ 
* $${\color{purple} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт7">7.</a> $${\color{purple} \textbf{Добавить других пользователей к проекту, передав ID проекта и пользователя }}$$ 
* $${\color{purple} \textbf{Операция отклоняется с информативным сообщением (пользователь уже добавлен к проекту, просто ошибка)}}$$ 
* $${\color{purple} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт8">8.</a> $${\color{purple} \textbf{Найти других пользователей в системе, предав имя или никнейм пользователя}}$$ 
* $${\color{purple} \textbf{Если пользователей с указанными данными не найдено, выводится информационное сообщение: “пользователя с таким ником или именем не найдено”}}$$ 
* $${\color{purple} \textbf{Если совпадения по поиску есть, то выводится список пользователей}}$$ 

<a id="фт9">9.</a> $${\color{purple} \textbf{Создать задачу, передав название задачи, ID проекта и пользователя-создателя задачи, информацию о задаче}}$$ 
* $${\color{green} \textbf{Задача создается со статусом по умолчанию}}$$ 
* $${\color{purple} \textbf{Операция отклоняется с информативным сообщением}}$$ 
* $${\color{purple} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт10">10.</a> $${\color{purple} \textbf{Редактировать задачу, передав ID задачи, новое имя задачи, информацию о задаче и ID пользователя-редактора задачи}}$$ 
* $${\color{purple} \textbf{Операция отклоняется с информативным сообщением}}$$ 
* $${\color{purple} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт11">11.</a> $${\color{purple} \textbf{Добавить исполнителей к задачи, передав ID задачи и ID пользователей}}$$ 
* $${\color{purple} \textbf{Операция отклоняется с информативным сообщением (пользователь уже добавлен к проекту, просто ошибка)}}$$ 
* $${\color{purple} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт12">12.</a> $${\color{purple} \textbf{Изменить статус задачи, передав ID задачи и новый статус}}$$ 
* $${\color{purple} \textbf{Операция отклоняется с информативным сообщением}}$$ 
* $${\color{purple} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт13">13.</a> $${\color{purple} \textbf{Любой участник проекта может удалить любую задачу проекта.}}$$ 
* $${\color{purple} \textbf{Операция отклоняется с информативным сообщением}}$$ 
* $${\color{purple} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт14">14.</a> $${\color{green} \textbf{Участники проекта имеют возможность задать статусы для задач в проекте, передав название и цвет}}$$ 
*  $${\color{green} \textbf{По умолчанию в проекте задана статусная сетка с одним статусом: "CREATED"}}$$ 
*  $${\color{green} \textbf{Операция отклоняется с информативным сообщением}}$$ 
*  $${\color{green} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт15">15.</a>  $${\color{green} \textbf{Участники проекта имеют возможность изменить статусы для задач в проекте, передав название и цвет}}$$ 
*  $${\color{green} \textbf{Операция отклоняется, если изменение статуса затрагивает задачи, с информативным сообщением}}$$ 
*  $${\color{green} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт16">16.</a>  $${\color{green} \textbf{Участники проекта имеют возможность удалить статусы для задач в проекте, передав название и цвет}}$$ 
*  $${\color{green} \textbf{Операция отклоняется, если статус присвоен одной из задач, с информативным сообщением}}$$ 
*  $${\color{green} \textbf{Операция отклоняется, если в проекте остался только один статус, с информативным сообщением}}$$ 
*  $${\color{green} \textbf{Операция проходит успешно с информативным сообщением}}$$ 

<a id="фт17">17.</a>  $${\color{green} \textbf{При просмотре проекта задачи отображаются в колонках}}$$ 
*  $${\color{green} \textbf{Название колонок - название статусов}}$$ 
*  $${\color{green} \textbf{В колонке с определенным статусом отображаются только те задачи, у которых статус совпадает с колонкой}}$$ 

<a id="фт18">18.</a>  $${\color{green} \textbf{Задача не может существовать без статуса}}$$

[Вернуться к заданию из 4 ЛР](#back)

[Вернуться к заданию из 5 ЛР](#back2)

---

# Лабораторная работа 2
Выполнили: Борздун Анна, Слепцова Элла, Мироненко Егор, Поддубная Полина, Демина Валерия, группа М34051
# Урок: Domain Driven Design (DDD) basics - Практика Task Manager, ограниченные контексты и языки домена
## Задание к уроку
### Ubiquitous language
Надо составить наш(и) Ubiquitous language(s). Выделите важные термины, которые использовались нами при обсуждении вопросов, в требованиях. Далее вы можете составить словарь-таблицу, где будет краткое толкование этих терминов, атрибуты данных терминов  (что-то, что описывает их количественно/качественно или какие-то другие предметы из предметной области, которые по вашему мнению неотделимы от них), не забудьте включить туда и описания действий, которые могут совершаться над предметами.

Вместо словаря можете создать набор иллюстраций с той же семантикой - предметы, атрибуты и действия. Не бойтесь делать это в свободной форме. Важно не количество написанного, наоборот, надо не внести в языки ничего лишнего, только то, что действительно используется в бизнес-языке.
### Bounded contexts
Исходя из сформированных нами языков, выделите ограниченные контексты, которые будут присутствовать в вашем приложении. Дайте им имена и укажите, какие термины будут входить в эти контексты.
## Ubiquitous language
Продолжите заполнение таблицы

| Термин | Краткое пояснение | Атрибуты | Ассоциированные термины | Действия над предметом |
|:-:|:-|:-|:-|:-|
| Пользователь (User) | существующий (зарегистрированный) в системе пользователь | • Имя<br>• Никнейм<br>• Пароль | Гость, проект, задача | • Добавление в проект (участник проекта → добавляет в проект → пользователя (пользователь становится участником)). |
| Гость (Guest) | Посетитель сайта, который не аутенцифицирован. Ему нужно пройти регистрацию или ввести свои данные (никнейм и пароль). Не имеет идентичности все гости одинаковы | - | Пользователь, проект | Регистрация, запрос на существование пользователя с указанным никнеймом |
| Проект (Project) | логическая сущность, которая объединяет в себе некоторое количество участников проекта (они являются пользователями), которые могут создавать задачи внутри этого проекта, а так же задачи проекта. Соответственно, только участники проекта могут просматривать его содержимое и изменять его. | • Имя<br> • участники<br> • задачи<br> • описание<br> • дата начала<br> • дата окончания | • Участник<br> • Задача<br> В проекте есть участники и задачи, которые относятся к данному проекту | • Создание (пользователь → создает → проект).<br> • Просмотр (участник проекта → просматривает → проект). |
| Задача (Task) | Единица работы в проекте, назначенная пользователю | Имя задачи, статус, исполнитель, сроки выполнения, приоритет, описание, тип задачи, история изменений, время на задачу | Проект, пользователь, статус | Создать задачу, назначить исполнителя, изменить статус задачи, редактировать задачу, удалять задачу, добавлять комментарии, логировать время |
| История изменений (Change history) | Лог изменений, сделанных с задачей | Дата изменения, пользователь, измененные атрибуты | Задача, проект | Записать изменения, просматривать историю |
| Время выполнения задачи (Task time log) | Время, затраченное на выполнение задачи | Дата начала работы, дата окончания работы, потраченное время | Задача, пользователь | Логировать время работы, просматривать затраченное время |


## Ограниченные контексты
Заполните таблицу
| Название контекста | Краткое пояснение, если требуется | Язык контекста |
|-|:-|-|
| Взаимодействие с системой | Действия, не связанные напрямую с проектами и задачами, совершаемые пользователем в системе | User<br> • ID<br> • name<br> • nikname<br> • password |
| Управление задачами | При выполнении задачи пользователь будет исполнителем. Соответственно, исполнитель будет изменять статус задачи и работать над ней | User<br> • name<br> • nikname <br><br> Task<br> • name<br> • status<br> • startDate<br> • endDate<br> • priority<br> • description<br> • taskTimeLog|
| Управление проектами | В этом случае пользователь будет ответственным. Он будет следить за выполнением задач, но не редактировать их | User<br> • name<br> • nikname <br><br> Project<br> • name<br> • contrebutors<br> • tasks<br> • description<br> • startDate<br> • endDate<br><br> Task<br> • name<br> • contrebutor<br> • startDate<br> • endDate<br> • status<br> • description<br> • priority<br> • taskTimeLog |

---

# Лабораторная работа №3
Выполнили: Борздун Анна, Слепцова Элла, Мироненко Егор, Поддубная Полина, Демина Валерия, группа М34051
## Урок: Designing Entities and Value objects in DDD
### Задание к уроку
В прошлом уроке вы занимались сбором требований, формированием языков и выделением ограниченных контекстов.

Воспользуйтесь вашими наработками, чтобы выделить список терминов, которые по вашему мнению должны стать сущностями (Entity).

Обоснуйте ваш выбор парой предложений. Выделите атрибуты этих сущностей, укажите, к какому ограниченному контексту они относятся.

## Сущности
Продолжите заполнение таблицы

Что важно помнить!

> Сущность не включает в себя другие сущности целиком! Если есть обоснованная необходимость задать связь между сущностями, то можно использовать идентификатор. То есть вы можете включать идентификаторы одной сущности в другую.

| Сущность | Атрибуты | Пояснения, если необходимы по вашему мнению | Контекст |
|-|-|-|-|
| Пользователь (User) | • ID: UUID<br> • Имя: String<br> •  Никнейм: String | Существующий (зарегистрированный) пользователь системы. | Управление задачами, Управление проектами, Взаимодействие с системой |
| Проект (Project) | • ID: UUID<br> • Имя: String<br> • Описание: String<br> • Автор проекта: UUID<br> • Дата начала: Date<br> • Дата окончания: Date<br> • Список участников: List\<UUID\><br> • Список задач: List\<UUID\><br> • История изменений: List\<UUID\> | Логическая сущность, объединяющая пользователей и задачи. | Управление проектами |
| Задача (Task) | • ID: UUID<br> • Имя: String<br> • Статус: String<br> • Автор задачи: UUID<br> • Исполнитель: List\<UUID\><br> • Сроки выполнения: Date<br> • Приоритет: String<br> • Описание: String<br> • История изменений: List\<UUID\><br> • Время выполнения: List\<UUID\> | Единица работы, назначенная пользователю в рамках проекта. | Управление задачами, Управление проектами |
| История изменений (Change history) |  • ID: UUID<br> • Автор изменений: UUID<br> • Дата изменения: Date<br> •  Пользователь: UUID<br> • Измененные атрибуты: String | Лог изменений, сделанных с задачей. | Управление задачами |
| Время выполнения задачи (Task time log) |  • ID: UUID<br> • Пользователь, выполняющий работы: UUID<br> • Дата начала работы: Date<br> • Дата окончания работы: Date<br> • Потраченное время: Integer | Время, затраченное на выполнение задачи. | Управление задачами |

---

# Лабораторная работа №4 и 5
Выполнили: Борздун Анна, Слепцова Элла, Мироненко Егор, Поддубная Полина, Демина Валерия, группа М34051
## Урок: Designing Aggregates in DDD

### Заказчик дополнил требования к TaskManager

Участники проекта могут создавать “статусы” внутри конкретного проекта, передавая название статуса и цвет для отображения в UI. “Статус” отображает, в какой стадии выполнения находятся задачи с таким статусом. Поэтому участники могут присваивать статус задаче и изменять статус задачи в дальнейшем.
__( статусы - это для задач )__

В каждом проекте есть статус по умолчанию (default status). Его значение "CREATED".

На UI это планируется визуализировать как “доску” / таблицу, где названия колонок - это имена статусов в проекте, а содержимое колонок - задачи с соответствующим статусом.

Конечно, статусы можно и удалять. Но удалить можно только такие статусы, которые не присвоены ни одной задаче в проекте - (колонка пустая).

<span style="color:gray">Кроме того, статусы в проекте отсортированы по какому-то критерию и участники в любой момент могут поменять порядок статусов внутри проекта (порядок колонок). [Опциональное требование - вы не обязаны его выполнять]<span>

### Задание к уроку

1. Дополните функциональные требования, языки, контексты и сущности, в соответствие с этими требованиями.
2. Выделите агрегаты для Task Manager по следующему сценарию:
    1. Пройдитесь по всем операциям в функциональных требованиях, для каждой из них укажите, какие сущности требуют изменений для ее выполнения. Возможно, есть сущности не изменяются в рамках операции, но тем не менее влияют на ее выполнение?
    2. Аналогично, укажите названия ограниченных контекстов, которые будут задействованы в каждой функциональной операции.
    3. Проанализируйте, какие инварианты требуется поддержать при обновлении сущностей. Есть ли такие инварианты или нет, в чем они заключаются. Если инварианты есть - выпишите их там же, рядом с функциональной операцией. Напоминаю, что инвариант - это некоторое условие, которое должно оставаться неизменным в любой момент времени.
    
    Для операций, которые требуют обновления более одной сущности, необходимо понять - требуются ли свойства транзакционности для их обновления.

    4. Теперь ваша задача - понять, требуют ли ваши инварианты строгой согласованности или бизнес вполне может перенести и  согласованность в конечном счете. Для этого вы можете описать ваше мнение по этому поводу и воспользоваться помощью ментора!!!, обсудить какая согласованность и где может быть применена.
    5. И последнее - на основе проделанного вами анализа вы можете принять решение какие сущности будут объединены в какие агрегаты. Определив состав агрегатов, соберите их в таблицу, указав их название, состав (сущность / сущности), ограниченный контекст. Состав сущностей тоже может претерпеть изменения, это нормально.

## Изменение проектной документации после дополнения требований

### Вопросы

_Поместите ниже список с дополнительными вопросами по новой функциональности_
1. Может ли существовать задача без статуса? 
2. Должны ли быть дефолтные статусы, кроме CREATED у задачи? Например: IN PROCESS, DONE
3. Есть ли минимальное и максимальное ограничение на количество статусов?
4. Можем ли мы изменить статус по умолчанию?
5. Можем ли мы удалить статус по умолчанию, если у нас нет задач в этом статусе? Можем ли мы удалить статус, если у нас вообще нет задач в проекте?
6. Есть ли ограничения по длине названия статуса или цвету (например, допустимые форматы)?
7. В какой момент пользователь должен создать свои статусы? Например, пользователь после создания проекта должен задать статусы для задач
8. Можно ли изменять статусы? Как пользователи будут изменять? Что произойдет с задачами, если статус будет изменен?
9. Нужна ли подтверждающая функция перед удалением?
10. Есть ли ограничения по длине названия статуса или цвету (например, допустимые форматы)?
11. Должны ли быть статусы уникальны в рамках проекта?


### Ubiquitous language

Дополните таблицу новыми терминами.

| Термин | Краткое пояснение | Атрибуты | Ассоциированные термины | Действия над предметом |
|-|-|-|-|-|
| Статус | Отображает стадию выполнения задачи в проекте | Название, цвет | Задача, проект | Создать, изменить, удалить |
| Статус по умолчанию | Статус, который автоматически присваивается новой задаче | Название, цвет | Статус, задача | Установить, изменить |

### Ограниченные контексты

Дополните таблицу новыми контекстами, если требуется

| Название контекста | Кратное пояснение, если требуется | Язык контекста |
|-|-|-|
| Управление проектами | В этом случае пользователь будет ответственным. Он будет следить за выполнением задач, но не редактировать их | User<br> • name<br> • nikname <br><br> Project<br> • name<br> • contrebutors<br> • tasks<br> • description<br> • startDate<br> • endDate<br> • __statusForTasks__<br><br> Task<br> • name<br> • contrebutor<br> • startDate<br> • endDate<br> • __status__<br> • description<br> • priority<br> • taskTimeLog |

### Сущности

Дополните таблицу новыми сущностями, внесите изменения в существующие, если считаете нужным

__Что важно помнить!__

> Сущность не включает в себя другие сущности целиком! Если есть обоснованная необходимость задать связь между сущностями, то можно использовать идентификатор. То есть вы можете включать идентификаторы одной сущности в другую.

| Сущность | Атрибуты | Пояснения, если необходимы по вашему мнению | Контекст |
|:-:|-|-|-|
| Проект (Project) | • ID: UUID<br> • Имя: String<br> • Описание: String<br> • Автор проекта: UUID<br> • Дата начала: Date<br> • Дата окончания: Date<br> • Список участников: List\<UUID\><br> • Список задач: List\<UUID\><br> • История изменений: List\<UUID\><br> • __Список статусов__ для задач: List\<String\> | Логическая сущность, объединяющая пользователей и задачи. | Управление проектами |

> Мы решили не выделять статус в отдельную сущность, тк это противоречи примечанию выше и здравому смыслу

### Выделение агрегатов

#### Какие сущности и контексты задействованы в функциональных операциях системы

Дополните таблицу, прикрепив ссылки на функциональные требования, сущности, которые задействованы в процессе, существующие инварианты.
<a id='back'></a>
| Ссылка на функциональное требование | Сущности, которые меняются, инварианты, вид согласованности | Контекст |
|-|-|-|
| ‣ [1](#фт1), [2](#фт2), [3](#фт3) | User<br>Происходит процесс регистрации, авторизации, аутентификации, идентификации. Гость становится зарегестрированным пользователем<br>ACID | Взаимодействие с системой |
| ‣ [8](#фт8) | User<br>Поиск пользователей в системе<br>Strong consistency | Взаимодействие с системой |
| ‣ [4](#фт4), [5](#фт5) | User, Project<br>Поиск проектов ассоциированных с пользователем<br>Strong consistency | Управление проектами |
| ‣ [6](#фт6) | Project<br>Создание проекта<br>ACID | Управление проектами |
| ‣ [7](#фт7) | Project<br>Добавление пользователей к проекту<br>ACID | Управление проектами |
| ‣ [14](#фт14), [15](#фт15) | Project, Task<br>Задание/Изменение статусов для задач проекта<br>ACID | Управление проектами, Управление задачами |
| ‣ [16](#фт16) | Project, Task<br>Удаление статусов для задач проекта<br>ACID | Управление проектами, Управление задачами |
| ‣ [9](#фт9), [10](#фт10) | Task<br>Создание/Редактирование задачи<br>ACID | Управление задачами |
| ‣ [11](#фт11) | User, Task<br>Добавление исполнителей к задаче<br>ACID | Управление задачами |
| ‣ [12](#фт12) | Task<br>Изменение статусов задачи<br>ACID | Управление задачами |
| ‣ [13](#фт13) | Task<br>Удаление задачи<br>ACID | Управление задачами |

[мем](https://lh5.googleusercontent.com/proxy/pP2yA4Mf551F9eMpFbYGLodocs1iXG7GmOgrRiCB593CMy-9eo0rhlUwCOrUiOk3Hhz9H7u7cgLyGBYhlXX2nIy7iWPzDu79WFqFDWX8J1H4VyCuajbilY5eR7af3VA)

#### Сущности, изменения

Если в процессе анализа ваши сущности претерпели изменения, то продублируйте таблицу сущностей из предыдущего задания и отметьте новые изменения зеленым фоном.

| Сущность | Атрибуты | Пояснения, если необходимы по вашему мнению | Контекст |
|-|-|-|-|
| Пользователь (User) | • ID: UUID<br> • Имя: String<br> •  Никнейм: String | Существующий (зарегистрированный) пользователь системы. | Управление задачами, Управление проектами, Взаимодействие с системой |
| Проект (Project) | • ID: UUID<br> • Имя: String<br> • Описание: String<br> • Автор проекта: UUID<br> • Дата начала: Date<br> • Дата окончания: Date<br> • Список участников: List\<UUID\><br> • Список задач: List\<UUID\><br> • История изменений: List\<UUID\><br><span style="color:green"> • Список статусов для задач: List\<String\><span> | Логическая сущность, объединяющая пользователей и задачи. | Управление проектами |
| Задача (Task) | • ID: UUID<br> • Имя: String<br> • Статус: String<br> • Автор задачи: UUID<br> • Исполнитель: List\<UUID\><br> • Сроки выполнения: Date<br> • Приоритет: String<br> • Описание: String<br> • История изменений: List\<UUID\><br> • Время выполнения: List\<UUID\> | Единица работы, назначенная пользователю в рамках проекта. | Управление задачами, Управление проектами |
| История изменений (Change history) |  • ID: UUID<br> • Автор изменений: UUID<br> • Дата изменения: Date<br> •  Пользователь: UUID<br> • Измененные атрибуты: String | Лог изменений, сделанных с задачей. | Управление задачами |
| Время выполнения задачи (Task time log) |  • ID: UUID<br> • Пользователь, выполняющий работы: UUID<br> • Дата начала работы: Date<br> • Дата окончания работы: Date<br> • Потраченное время: Integer | Время, затраченное на выполнение задачи. | Управление задачами |

#### Агрегаты

Продолжите заполнять таблицу с агрегатами

| Агрегат | Атрибуты и сущности |Пояснения | Контекст |
|-|-|-|-|
| Пользователь | Сущности: User <br> Все атрибуты упомянутых сущностей | Агрегат, отвечающий за взаимодействие пользователя непосредственно с системой приложения (например, поиск других пользователей)  | Взаимодействие пользователя с частью системы, не связанной с агрегатом Проект |
| Проект | Сущности: User, Project, Task, Change history, Task time log. <br> Все атрибуты упомянутых сущностей | Агрегат, отвечающий за взаимодействие с проектами и внутри них, в отличие от агрегата Пользователь, затрагивает только напрямую связанную с проектами деятельность | Реализация таск трекера для проектов, включает весь внутренний функционал, связанный с проектами |

---

# Лабораторная №6

## Урок: Implementing Event Driven system. Transactional outbox pattern

### Задание к уроку

В следующем уроке мы начнем писать код. Но пока нам нужно продолжать планомерно проектировать Task Manager.

Мы ближе познакомились с понятиями "событие" и "команда". Сейчас я попрошу вас вернуться к вашим функциональным требованиями и для каждого из них отметить, подразумевает ли это требование запрос данных (запрос на чтение) или подразумевает обновление какого-то (каких-то) агрегатов, то есть подразумевает запрос на запись. Заполните две таблицы - для операций чтения и записи.

### Для "запросов на запись" я попрошу вас:

Понять, какие агрегаты вовлечены в обновление в рамках этого требования

К каждой операции добавить соответствующую команду с указанием входных параметров. Команды можете поместить в таблицу или список рядом с агрегатами, к которым они относятся.

Для команд определите, какой(ие) события (events) будут порождаться в результате выполнения данной команды.

Это все, что нам нужно. Теперь попробуйте понять, есть ли баланс между запросами на запись и запросами на чтение. Все ли данные, которые мы обновляем, участвуют хотя бы в одном запросе на чтение? Если нет, то предложите, какие операции на чтение могли бы быть заинтересованы в таких данных.

## Проектирование команд и событий

### Таблица требований с указанием операций чтения и агрегатов, которые в них задействованы

Продолжите заполнение таблицы
<a id='back2'></a>
| Ссылка на функциональное требование | Агрегаты |
|-|-|
| [1](#фт1) | User |
| [3](#фт3) | User |
| [4](#фт4) | Project, User |
| [5](#фт5) | Project, User |
| [8](#фт8) | User |
| [17](#фт17) | Project: List\<String\> |


### Таблица требований с указанием операций записи и агрегатов, которые в них задействованы

Продолжите заполнение таблицы

| Ссылка на функциональное требование | Запись, агрегаты | Название команды и ее параметры | Событие(я), создаваемые командой | Используются ли данные в запросах на чтение |
|-|-|-|-|-|
| [2](#фт2) | User: никнейм, имя и пароль  | createUser(id, nickname, name, password) | UserCreatedEvent | Да (поиск пользвателей) |
| [6](#фт6) | Project: название | createProject(id, title, creatorId) | ProjectCreatedEvent | Да (список проектов) |
| [7](#фт7) | Project: ID проекта, user | addUserToProject(projectId, userId) | UserAddedToProjectEvent | Да(когда отображаем информацию о проекте) |
| [9](#фт9) | Task: название задачи, ID проекта, user-автор, статус, user-исполнители, сроки выполнения, приоритет, описание | createTask(id, title, creatorId, status, doersID, deadlines, priority, description) | TaskCreatedEvent | Да (список задач) |
| [10](#фт10) | Task: название задачи, ID проекта, user-редактор, сроки выполнения, приоритет, описание | updateTask(id, title, creatorId, deadlines, priority, description) | TaskContentUpdatedEvent | Да (список задач, история изменения) |
| [11](#фт11) | Task: ID задачи, ID пользователя| addUserToTask(taskId, userId) | UserAddedToTaskEvent | Да(когда отображаем информацию о задаче, история изменения)
| [12](#фт12) | Task: ID задачи, новый статус | updateTask(id, status) | TaskStatusUpdatedEvent | Да (список задач, история изменения) |
| [13](#фт13) | Task: ID задачи | deleteTask(id, deleterId) | TaskDeletedEvent | Да (история изменения)
| [14](#фт14) | Project: List\<String\> | createProjectStatus(id, statusNameAndColor) | ProjectStatusAddedEvent | Да(отображение статусов)
| [15](#фт15) | Project: List\<String\> | updateProjectStatus(id, statusNameAndColor) | ProjectStatusUpdateEvent | Да(отображение статусов)
| [16](#фт16) | Project: List\<String\> | updateProjectStatus(id, statusNameAndColor) | ProjectStatusUpdateEvent | Да(отображение статусов)

> Комментарий к последней строчке: при удалении статусов прокта, пользователь не может удалить все статусы полностью (__хотя бы один__ должен остаться), получается пользователь обновляет статусы, когда удаляет их.
