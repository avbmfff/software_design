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
**Пример того, как должны быть оформлены требования:**
Пользователь должен иметь возможность cоздавать проект, передав название проекта.
Пользователь должен иметь возможность создавать задачи в проекте, участником которого является, передавая имя задачи.
Не пытайтесь насытить вашу системы новой функциональностью просто потому, что вам это кажется правильным. Помните, что вы **не должны додумывать** за заказчика, как должны выглядеть/вести себя его система. Руководствуйтесь исключительно тем, что предоставил вам заказчик в виде описания системы. Если вам кажется, что в системе не хватает функциональности, которая будет критична для демонстрации - задавайте вопрос, **аргументируйте** свою точку зрения. Дополнять требования нужно по мере получения четких ответов от заказчика системы.
**Вопросы**, которые вы задали выше должны быть **нацелены на прояснение** этих требований. Ответы на эти вопросы могут приводить к изменению требований.
## Вопросы
**Наши вопросы и функциональные требования выделены <span style="color:purple">фиолетовым</span>.**
Мы уже начали заполнять список вопросов. Продолжите его… 
1. <span style="color:purple">Как вы сейчас планируете свои задачи для проектов?<span>
* <span style="color:purple">Как Вы управляете проекты сейчас? <span>
* <span style="color:purple">Как передаете задачи исполнителю сейчас?<span>
* <span style="color:purple">Как вы формулируете задачи сейчас?<span>
2. <span style="color:purple">Какие сейчас тасктрекеры вы используете? Какие преимущества и недостатки вы в них видите?<span>
* <span style="color:purple">Есть jira, trello - пользовались ли Вы ими?<span>
    * <span style="color:purple">Если да, что вы бы хотели в них улучшить?<span>
    * <span style="color:purple">Если нет, то почему они не подошли?<span>
3. Какие данные пользователя используются для регистрации?
* Никнейм, пароль
4. <span style="color:purple">Какие ограничения на никнейм и пароль должны быть в системе?<span>
5. Если пользователь с таким никнеймом уже существует, как должна вести себя система?
* Отклонить запрос о регистрации, выдать информативное сообщение (формулировку сообщения можете выбрать по своему усмотрению)
6. Тогда, вероятно, стоит добавить функцию проверки, существует ли пользователь с таким никнеймом?
* Да, стоит
7. <span style="color:purple">Есть ли у Вас требования к паролю пользователя? (например - Задается ли пароль пользователем или он выдается администратором/самой системой? Какой должна быть валидация у пароля? Если задается пользователем, то может ли пользователь сбросить свой пароль?)<span>
8. Как происходит процесс входа в систему (аутентификации)
* На данный момент мы не хотим реализовывать никакие механизмы аутентификации и авторизации. Необходимо будет реализовать только добавление пользователей в систему. - а-ля не надо подтверждение по почте
9. Что предполагается показывать пользователю (какую страницу) после успешной аутентификации? 
* Хотим показывать ему страницу со списком проектов, участником которых он является и кнопку "создать проект". - грубо говоря, таблица + в шапке создать
10. Нужна ли навигация по системе? Панель с доступами ко всем проектам, ко всем задачам?
11. <span style="color:purple">Нужно ли отображать в системе пользователю назначенные на него задачи, созданные им задачи?<span>
12. <span style="color:purple">Нужен ли в системе профиль пользователя? Если да то, что должно быть в профиле пользователя? Какие данные о нем?<span>
13. <span style="color:purple">Может ли пользователь редактировать свой профиль? ожно удалить можно нет<span>
14. <span style="color:purple">Может ли пользователь удалить свой аккаунт из системы или это должен сделать только администратор?<span>
15. <span style="color:purple">Какие атрибуты есть у проекта, кроме названия? Например, дата начала и окончания, описание, статусы?<span>
16. Чтобы добавить в проект нового участника нужна возможность искать пользователя. Вероятно, проще всего искать будет по его имени и никнейму?
* Да, стоит добавить в пользователя атрибут “имя” и указывать его при регистрации. В это поле мы помещаем ФИО пользователя.
* Да, стоит реализовать функцию поиска участника. Давайте искать по полному совпадению или **вхождению искомой строки в никнейм или имя пользователя**
17. Могут ли пользователи видеть проекты, участниками которых они не являются
* Да, для реализации минимального продукта мы не делаем никаких механизмов разграничения доступа (авторизации)
18. <span style="color:purple">Может пользователь редактировать/удалить проект? Если да, все ли пользователи могут это делать? (Если да, то может ли это сделать любой пользователь или, например, только создатель проекта?) Есть ли ограничения на редактирование? (Допустим одним пользователям можно удалять весь проект, а другим можно только добавлять свои изменения) Нужно ли отслеживать редактирование (запомнить пользователя-редактора и дату редактирования)? (Это может быть полезно в случае ошибок. Например, пользователь удалил важную часть работы. По пользователю и дате можно восстановить, кто виноват в этом)<span>
19. <span style="color:purple">Какие атрибуты есть у задачи, кроме названия, статуса и исполнителей? Например, сроки выполнения задачи, приоритет, градация исполнителей (исполнитель/проверяющий/ответственный), описание, тип задачи, историю изменения задачи, логирование времени (инструмент по фиксированию промежутков времени потраченные на определенные задачи), оценка задачи (стори поинты, время). Какие статусы должны быть у задачи (например: нужно сделать, в работе, на проверке, готово, приостановлено, переоткрыта, тестируется, интегрируется)? Какие типы задач должны быть в системе (например: задача, история, эпик, подзадача, баг)? <span>
20. <span style="color:purple">Нужны ли уведомления в вашей системе? Какие типы уведомлений может получать пользователь? Например, уведомления о проектах, задачах. От каких сущностей пользователь получает уведомления? Как уведомлять пользователя о новой задаче/проекте, об изменениях в статусе или описании задачи? Должны ли быть уведомления в системе или они посылаются через сторонние сервисы (почта/номер телефона)? Может ли пользователь подписываться на уведомления от задач?<span>
21. <span style="color:purple">Может пользователь редактировать/удалить задачи? Если да, то может ли это сделать любой пользователь или, например, только исполнитель или создатель задачи? Если ли ограничения на редактирование? Нужно ли отслеживать редактирование (запомнить пользователя-редактора и дату редактирования)?<span>

## Функциональные требования
**<span style="color:red">Функциональные требования составлены по известным данным от заказчика<span>**
**Наши вопросы и функциональные требования выделены <span style="color:purple">фиолетовым<span>**
**Наши вопросы и функциональные требования для 4 ЛР выделены <span style="color:green">зеленым<span>**
Дополните список. Пользователь должен иметь возможность…
<a id="фт1">1.</a> “Гость” имеет возможность узнать зарегистрирован ли пользователь с подобным ником, направив запрос в систему и получив ответ в формате “true/false”.
<a id="фт2">2.</a> Пройти регистрацию в системе, сменив статус “гостя” на “пользователя” системы, передав никнейм, имя и пароль. <span style="color:purple">Исходы события:<span>
* <span style="color:purple">Пользователь имеет возможность ввести желаемый логин. <span>
    * <span style="color:purple">Если логин существует в системе высвечивается сообщение “Логин уже существует в системе, введите другой”. <span>
    * <span style="color:purple">Если логин не соответствует требованиям системы, высвечивается сообщение: “Минимальная длина логина - 5 символов, логин может содержать только буквы латинского алфавита и цифры”<span>
* <span style="color:purple">Пользователь имеет возможность ввести желаемый пароль. <span>
    * <span style="color:purple">Если пароль не соответствует требования системы, высвечивается сообщение: “Минимальная длина пароля - 8 символов”<span>
* <span style="color:purple">Пользователь имеет возможность узнать о результате регистрации. При неуспешной попытке зарегистрироваться (введен некорректный логин или пароль), пользователь получает сообщение от системы “Регистрация прошла неуспешна”. При успешной попытке регистрации происходит redirect пользователя на главную страницу системы.<span>
<a id="фт3">3.</a> <span style="color:purple">Пользователь, имеющий аккаунт в системе, имеет возможность авторизации. Исходы события:<span>
* Операция входа <span style="color:purple">в учетную запись<span> в систему отклоняется с информативным сообщением, если пользователь с таким ником уже существует. <span style="color:purple">(пример - “Пользователь с таким ником уже существует”)<span>
* <span style="color:purple">Операция входа в учетную запись отклоняется с информативным сообщением, если пользователь ввел неверный пароль  (пример - “Вы ввели неправильный пароль”)<span>
* <span style="color:purple">Операция входа в учетную запись проходит успешно с информативным сообщением (пример - “Вы вошли в учетную запись”)<span>
<a id="фт4">4.</a> <span style="color:purple">Авторизованный пользователь может увидеть все проекты, в которых он участвует. Исходы события:<span>
* <span style="color:purple">Если пользователь не участвует ни в одном проекте, то высвечивается сообщение с предложением создать проект. (Пример сообщения, “Создать проект”)<span>
* <span style="color:purple">Если пользователь участвует в 1 и более проектов, то он имеет возможность отсортировать проекты по:<span>
    * <span style="color:purple">дате изменения проекта<span>
    * <span style="color:purple">недавно изменённым проектам<span>
    * <span style="color:purple">проектам, которыми владеет пользователь сначала<span>
<a id="фт5">5.</a> <span style="color:purple">Авторизованный пользователь может увидеть проекты, где пользователь указан в качестве участника<span>
* <span style="color:purple">Если проектов нет, то высвечивается информационное сообщение с предложением создать проект<span>
* <span style="color:purple">Если проекты есть, то показывается список проектов<span>
<a id="фт6">6.</a> <span style="color:purple">Создать проект, передав название проекта<span>
* <span style="color:purple">Операция отклоняется с информативным сообщением<span>
* <span style="color:purple">Операция проходит успешно с информативным сообщением<span>
<a id="фт7">7.</a> <span style="color:purple">Добавить других пользователей к проекту, передав ID проекта и пользователя <span>
* <span style="color:purple">Операция отклоняется с информативным сообщением (пользователь уже добавлен к проекту, просто ошибка)<span>
* <span style="color:purple">Операция проходит успешно с информативным сообщением<span>
<a id="фт8">8.</a> <span style="color:purple">Найти других пользователей в системе, предав имя или никнейм пользователя<span>
* <span style="color:purple">Если пользователей с указанными данными не найдено, выводится информационное сообщение: “пользователя с таким ником или именем не найдено”<span>
* <span style="color:purple">Если совпадения по поиску есть, то выводится список пользователей<span>
<a id="фт9">9.</a> <span style="color:purple">Создать задачу, передав название задачи, ID проекта и пользователя-создателя задачи, информацию о задаче<sapn>
* <span style="color:green">Задача создается со статусом по умолчанию<span>
* <span style="color:purple">Операция отклоняется с информативным сообщением<span>
* <span style="color:purple">Операция проходит успешно с информативным сообщением<span>
<a id="фт10">10.</a> <span style="color:purple">Редактировать задачу, передав ID задачи, новое имя задачи, информацию о задаче и ID пользователя-редактора задачи<span>
* <span style="color:purple">Операция отклоняется с информативным сообщением<span>
* <span style="color:purple">Операция проходит успешно с информативным сообщением<span>
<a id="фт11">11.</a> <span style="color:purple">Добавить исполнителей к задачи, передав ID задачи и ID пользователей<span>
* <span style="color:purple">Операция отклоняется с информативным сообщением (пользователь уже добавлен к проекту, просто ошибка)<span>
* <span style="color:purple">Операция проходит успешно с информативным сообщением<span>
<a id="фт12">12.</a> <span style="color:purple">Изменить статус задачи, передав ID задачи и новый статус<span>
* <span style="color:purple">Операция отклоняется с информативным сообщением<span>
* <span style="color:purple">Операция проходит успешно с информативным сообщением<span>
<a id="фт13">13.</a> <span style="color:purple">Любой участник проекта может удалить любую задачу проекта.<span>
* <span style="color:purple">Операция отклоняется с информативным сообщением<span>
* <span style="color:purple">Операция проходит успешно с информативным сообщением<span>
<a id="фт14">14.</a> <span style="color:green">Участники проекта имеют возможность задать статусы для задач в проекте, передав название и цвет<span>
* <span style="color:green">По умолчанию в проекте задана статусная сетка с одним статусом: "CREATED"<span>
* <span style="color:green">Операция отклоняется с информативным сообщением<span>
* <span style="color:green">Операция проходит успешно с информативным сообщением<span>
<a id="фт15">15.</a> <span style="color:green">Участники проекта имеют возможность изменить статусы для задач в проекте, передав название и цвет<span>
* <span style="color:green">Операция отклоняется, если изменение статуса затрагивает задачи, с информативным сообщением<span>
* <span style="color:green">Операция проходит успешно с информативным сообщением<span>
<a id="фт16">16.</a> <span style="color:green">Участники проекта имеют возможность удалить статусы для задач в проекте, передав название и цвет<span>
* <span style="color:green">Операция отклоняется, если статус присвоен одной из задач, с информативным сообщением<span>
* <span style="color:green">Операция отклоняется, если в проекте остался только один статус, с информативным сообщением<span>
* <span style="color:green">Операция проходит успешно с информативным сообщением<span>
<a id="фт17">17.</a> <span style="color:green">При просмотре проекта задачи отображаются в колонках<span>
* <span style="color:green">Название колонок - название статусов<span>
* <span style="color:green">В колонке с определенным статусом отображаются только те задачи, у которых статус совпадает с колонкой<span>
<a id="фт18">18.</a> <span style="color:green">Задача не может существовать без статуса<span>

[Вернуться к заданию из 4 ЛР](#back)

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

# Лабораторная работа №4
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