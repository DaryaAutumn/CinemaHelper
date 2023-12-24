# Приложение для работников кинотеатра 
### ДЗ по КПО, Судакова Дарья, БПИ227
# Для чего?
В приложении можно управлять единственным залом кинотеатра. Можно продавать билеты на различные сеансы, оформлять возврат билетов, редактировать данные о фильмах и сеансах, а также проверять билеты покупателей при входе на фильм.
# Как пользоваться?
При входе открывается текстовое меню. Необходимо выбрать одну из опций, напечатав ее номер:
1. Продать билет.
2. Вернуть билет.
3. Изменить информацию о филььмах или сеансах.
4. Проверить билет.
5. Выйти.
Рассмотрим каждую опцию отдельно.
### Продать билет
Для того, чтобы продать билет, необходимо сначала выбрать фильм, а затем сеанс. Отображаются только те сеансы, которые идут позже, чем текущее время.
После отображается схема зала. Необходимо сначала указать ряд, а затем место, которое вы хотите выбрать. 
Получите свой билет!
### Вернуть билет 
Для того, чтобы вернуть билет, также выберете фильм и сеанс, а затем место, на которое хотите вернуть билет. Отображается схема зала с занятыми местами. Нельзя вернуть незанятое место! 
Билет возвращен!
### Изменить информацию о фильмах и сеансах
Для того, чтобы изменить информацию о фильмах или сеасах, выберете опцию из меню, напечатав ее номер:
1. Изменить информауию о фильмах
2. Изменить информацию о сеансах
3. Добавить новый фильм
4. Добавить новый сеанс
В опции номер 1 можно изменить описание фильма, его название, и добавить актеров. Чтобы добавить актера, просто напечатайте его имя, чтобы добавить больше актеров, напечаатйте имя или нажмите '0' чтобы вернуть в главное меню.
В опции номер 2 можно изменить дату и время сеансов. Выберете фильм, а затем один из его сеансов, а затем напечатайте новую дату в формате "год-месяц-день-часы:минуты" (например 23-12-1-23:59). Год обязательно 4 цифры, месяц, день, чаты и минуты - 2 цифры.
В опции номер 3 можно добавить новый сеанс, выбрав фильм и напечатав время сеанса в таком же формате.
В опции номер 4 можно добавить новый фильм, напечатав его название и описание.
### Проверить билет
Для того, чтобы проверить билет, выберете фильм, сеанс, и номер места - билет, по которому хочет пройти покупатель. Билет нельзя провреить дважды, нельзя проверить не проданный билет. При выборе места отображается схема зала с занятыми местами.
### Выйти
Нажмите '0' чтобы выйти
# Хранение данных
Для хранения данных используется формат JSON, так как в проекте используется сериализация, а для сериализации из предложенных форматов проще всего использовать JSON. Файлф хранятся в папке [json](src/main/resourses/json)
# UML-диаграммы
use-case диаграмма
