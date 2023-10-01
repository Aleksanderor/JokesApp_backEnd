package scheduler;

import com.example.jokes.domain.ExternalJokeDto;
import com.example.jokes.mapper.ExternalJokeMapper;
import com.example.jokes.service.JokeDbService;
import com.example.jokes.service.externalApiDbService.DadJokeDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JokesImportScheduler {
    private final DadJokeDbService dadJokeDbService;
    private final JokeDbService jokeDbService;
    private final ExternalJokeMapper externalJokeMapper;

    @Autowired
    public JokesImportScheduler(DadJokeDbService dadJokeDbService, JokeDbService jokeDbService, ExternalJokeMapper externalJokeMapper) {
        this.dadJokeDbService = dadJokeDbService;
        this.jokeDbService = jokeDbService;
        this.externalJokeMapper = externalJokeMapper;
    }

    @Scheduled(cron = "${import.joke.schedule}")
    void importJoke() {
        ExternalJokeDto externalJokeDto = dadJokeDbService.getRandomJoke();
        jokeDbService.addJoke(externalJokeMapper.toJoke(externalJokeDto));
    }
}
