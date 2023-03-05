import java.util.ArrayList;
import java.util.List;

class Article {
    final String title;
    Article(final String title) {
        this.title = title;
    }
}

interface Subscriber {// создайте интерфейс Subscriber
    public void update(Article article); // интерфейс должен иметь всего один метод `void update()`, который принимает на вход объект класса `Article`
}

class OfflineSubscriber implements Subscriber{ /* реализуйте интерфейс Subscriber */
    private final String address;
    public OfflineSubscriber(final String address) {
        this.address = address;
    }
    @Override
    public void update(Article article) {
        System.out.println("{article.title} + была доставлена по адресу: {address}"); // метод update должен выводить текст "{article.title} была доставлена по адресу: {address}"
    }
}

class WebSite implements Subscriber { /* реализуйте интерфейс Subscriber */
    private final String url;
    public WebSite(final String url) {
        this.url = url;
    }
    @Override
    public void update(Article article) {
        System.out.println("{article.title} опубликована на страничке: {url}"); // метод update должен выводить текст "{article.title} опубликована на страничке: {url}"
    }
}

class NewspaperPublisher {
    ArrayList<Subscriber> subscribers = new ArrayList<>(); // создайте список подписчиков List<Subscriber> с именем subscribers
    // для отправки статей подписчикам вам нужно хранить их в списке
    private final List<Article> articles;
    public NewspaperPublisher(final List<Article> articles) {
        this.articles = articles;
    }

    public void subscribe(final Subscriber subscribers) {
        // метод void subscribe() должен принимать на вход любой объект, реализующий интерфейс Subscriber
        for (Subscriber subscriber : subscribers) {
            subscribers.add(subscriber); // при вызове метода subscribe() новый подписчик должен добавляться в список подписчиков.
            // в списке подписчиков не должно быть дубликатов. Вы можете проверить, есть ли уже этот подписчик в списке методом List.contains()
        }
    }

    public void unsubscribe(final Subscriber subscribers) { // метод void unsubscribe() должен принимать на вход любой объект, реализующий интерфейс Subscriber
        // при вызове данного метода подписчик должен удаляться из списка подписчиков
        for (Subscriber subscriber : subscribers) {
            subscribers.remove(subscriber);
}
    }

    public void startWork() { // метод void startWork() должен отправлять все статьи, которые хранятся в данный момент в списке articles, всем подписчикам
        for (Article article : articles) {
            for (Subscriber subscriber : subscribers) {
                OfflineSubscriber.update (article);
                WebSite.update (article);
            }
        }
    }

    public void publishNewArticle(final Article article) { // метод void publishNewArticle() принимает на вход объект класса Article
        articles.add (article); // метод должен добавлять новую статью в список статей articles, а также делать рассылку новой статьи всем активным подписчикам
        for (Subscriber subscriber : subscribers) {
            OfflineSubscriber.update (article);
            WebSite.update (article);
        }
    }
}




