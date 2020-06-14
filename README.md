Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

Developer
Skill
Account
AccountStatus (enum ACTIVE, BANNED, DELETED)

Developer -> Set<Skill> skills + Account account
Account -> AccountStatus

В качестве хранилища данных необходимо использовать текстовые файлы:
developers.txt, skills.txt, accounts.txt

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

Слои:
model - POJO класы
repository - классы, реализующие доступ к текстовым файлам
controller - обработка запросов от пользователя
view - все данные, необходимые для работы с консолью

Например: Developer, DeveloperRepository, DeveloperController, DeveloperView и т.д.


Для репозиторного слоя желательно использовать базовый интерфейс:
interface GenericRepository<T,ID>

interface DeveloperRepository extends GenericRepository<Developer, Long>

class JavaIODeveloperRepositoryImpl implements DeveloperRepository

Результатом выполнения задания должен быть отдельный репозиторий с README.md файлом, который содержит описание задачи, проекта и инструкции запуска приложения через командную строку.
