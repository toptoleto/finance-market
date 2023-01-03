# Приложения для проведения операций на финансовом рынке

Приложение для проведения операций на торговых площадках (покупка, продажа, прогнозирование).

Для запуска приложения необходимо:
- запустить docker-compose.yml (создание БД)
- запустить FinanceMarketApplication

| модуль | описание |
| --- | --- |
| [dao](dao) | слой доступа к данным |
| [service](service) | сервисы с бизнес логикой |
| [domain](domain) | доменная модель для DTO | 
| [postgres](postgres) | конфигурация для работы с БД |

Локально запущенное приложение доступно по адресу:
```
http://localhost:8080/finance/
```

Загрузить тестовые данные можно с помощью request.http или с помощью команды curl:
```
curl -X POST http://localhost:8080/finance/user/add -d @request.json --header "Content-Type: application/json"
```
Просмотр тестовых данных:
```
curl -X GET http://localhost:8080/finance/user/get --header "Content-Type: application/json"
```