import java.util.ArrayList;
import java.util.List;

class Article {
    final String title;
    Article(final String title) {
        this.title = title;
    }
}

interface Subscriber {
    // создайте интерфейс Subscriber
    void update(Article article);
// интерфейс должен иметь всего один метод `void update()`, который принимает на вход объект класса `Article`
}

class OfflineSubscriber implements Subscriber{ /* реализуйте интерфейс Subscriber */
    private final String address;
    public OfflineSubscriber(final String address) {
        this.address = address;
    }
    @Override
    public void update(Article article) {
        System.out.printf("%s была доставлена по адресу: %s\n", article.title, address);
// метод update должен выводить текст "{article.title} была доставлена по адресу: {address}"
    }
}

class WebSite implements Subscriber { /* реализуйте интерфейс Subscriber */
    private final String url;
    public WebSite(final String url) {
        this.url = url;
    }
    @Override
    public void update(Article article) {
        System.out.printf("%s опубликована на страничке: %s\n", article.title, url);
// метод update должен выводить текст "{article.title} опубликована на страничке: {url}"
    }
}

class NewspaperPublisher {
    List<Subscriber> subscribers = new ArrayList<>();
    // создайте список подписчиков List<Subscriber> с именем subscribers
// для отправки статей подписчикам вам нужно хранить их в списке
    private final List<Article> articles;
    public NewspaperPublisher(final List<Article> articles) {
        this.articles = articles;
    }

    public void subscribe(Subscriber subscriber) {
// метод void subscribe() должен принимать на вход любой объект, реализующий интерфейс Subscriber
        if (!subscribers.contains(subscriber)){
// в списке подписчиков не должно быть дубликатов. Вы можете проверить, есть ли уже этот подписчик в списке методом List.contains()
            subscribers.add(subscriber);
// при вызове метода subscribe() новый подписчик должен добавляться в список подписчиков.
        }
    }

    public void unsubscribe(Subscriber subscriber) {
// метод void unsubscribe() должен принимать на вход любой объект, реализующий интерфейс Subscriber
        subscribers.remove(subscriber);
// при вызове данного метода подписчик должен удаляться из списка подписчиков
    }

    public void startWork() {
// метод void startWork() должен отправлять все статьи, которые хранятся в данный момент в списке articles, всем подписчикам
        for (Subscriber subscriber : subscribers) {
            for (Article article : articles) {
                subscriber.update (article);
            }
        }
    }

    public void publishNewArticle(final Article article) {
// метод void publishNewArticle() принимает на вход объект класса Article
        articles.add (article);
// метод должен добавлять новую статью в список статей articles, а также делать рассылку новой статьи всем активным подписчикам
        for (Subscriber subscriber : subscribers) {
            subscriber.update (article);
        }
    }
}
